package sim

import java.awt.Dimension
import java.awt.BorderLayout
import javax.swing.WindowConstants
import javax.swing.JFrame
import javax.swing.JSplitPane
import java.awt.event.WindowListener
import java.awt.event.WindowEvent

class KFrame () : JFrame(), WindowListener
{
   init
   {
      with( getContentPane() ) {
         add(JSplitPane( JSplitPane.VERTICAL_SPLIT ).apply {
            setTopComponent( JSplitPane().apply {

               setLeftComponent( JSplitPane().apply {
                  setLeftComponent( GUI.contactsPanel )
                  setRightComponent( GUI.presentationPanel )
                  setDividerLocation( 150 )
                  setDividerSize( 4 )
               })

               setRightComponent( GUI.infoPanel )
               setDividerLocation( 650 )
               setDividerSize( 4 )
            })

            setBottomComponent( GUI.outputPane )
            setDividerLocation( 400 )
            setDividerSize( 6 )
            
         }, BorderLayout.CENTER )

         add( GUI.toolbar, BorderLayout.NORTH )
         add(GUI.statusBar, BorderLayout.SOUTH)
      }

      setTitle( "Simulator" )
      setSize( Dimension( 800, 600 ) )
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
