package sim.gui

import java.awt.Component
import java.awt.Dimension
import java.awt.BorderLayout
import javax.swing.WindowConstants
import javax.swing.JFrame
import javax.swing.JSplitPane
import javax.swing.JTabbedPane
import java.awt.event.WindowListener
import java.awt.event.WindowEvent
import sim.Simulation

class KFrame (simulation : Simulation) : JFrame(), WindowListener
{
   init
   {
      with( contentPane ) {
         add(JSplitPane( JSplitPane.VERTICAL_SPLIT ).apply {
            setTopComponent( JSplitPane().apply {

               setLeftComponent( JSplitPane().apply {
                  setLeftComponent( GUI.panels.contacts )
                  setRightComponent( GUI.simulation as Component )
                  setDividerLocation( 175 )
                  setDividerSize( 4 )
               })

               setRightComponent( GUI.panels.info )
               setDividerLocation( 981 )
               setDividerSize( 4 )
            })

            setBottomComponent(JTabbedPane( JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT ).apply {
               add( "Log", GUI.panels.log )
               add( "Transactions", GUI.panels.transactions )
            })
            setDividerLocation( 605 )
            setDividerSize( 6 )

         }, BorderLayout.CENTER )

         add( GUI.toolbar, BorderLayout.NORTH )
         add(GUI.statusBar, BorderLayout.SOUTH)
      }

      setTitle( "Simulator" )
      setSize( Dimension( 1170, 808 ) )
      addWindowListener( this )
      setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE )
      setVisible( true )
   }

   override fun windowClosing (e : WindowEvent)
   {
      setVisible( false )
      dispose()
   }
   override fun windowActivated (e : WindowEvent) {}
   override fun windowClosed (e : WindowEvent) {}
   override fun windowDeactivated (e : WindowEvent) {}
   override fun windowDeiconified (e : WindowEvent) {}
   override fun windowIconified (e : WindowEvent) {}
   override fun windowOpened (e : WindowEvent) {}
}
