package sim.gui

import java.awt.Dimension
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import javax.swing.event.ListSelectionEvent
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JLabel
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JToolBar
import javax.swing.ImageIcon
import sim.Aspect
import sim.StateMachine

/** The application toolbar. */
class KToolbar () : JToolBar(), ActionListener
{
   internal val doButton : JButton
   internal val objectiveCombo = KStatesComboBox( GUI.simulation.states.keys.toTypedArray() )
   internal val aspectCombo = KAspectComboBox(arrayOf( Aspect.ACCOUNT, Aspect.ASSETS, Aspect.DETAIL, Aspect.SIGNATORIES ))
   internal var selectedAspect = Aspect.ACCOUNT

   private val START_ICON = ImageIcon( "build/dist/resources/icons/media-playback-start.png" )
   private val NEXT_ICON = ImageIcon( "build/dist/resources/icons/media-seek-backward-rtl.png" )

   init
   {
      setFloatable( false )

      addSeparator(Dimension( 8, 24 ))
      doButton = JButton().apply {
         setActionCommand( StateMachine.START )
         setBorder( null )
         setIcon( START_ICON )
      }.also {
         it.addActionListener( this )
         add( it )
      }

      addSeparator(Dimension( 8, 24 ))
      add( JButton().apply {
         setActionCommand( StateMachine.STOP )
         setBorder( null )
         setIcon(ImageIcon( "build/dist/resources/icons/media-playback-stop.png" ))
      }.also {it.addActionListener( this )} )

      addSeparator(Dimension( 24, 24 ))
      aspectCombo.setMaximumSize(Dimension( 100, 24 ))
      add( aspectCombo )
      aspectCombo.addItemListener( aspectCombo )

      addSeparator(Dimension( 24, 24 ))
      add(JLabel( "States:" ))

      addSeparator(Dimension( 8, 24 ))
      objectiveCombo.setMaximumSize(Dimension( 175, 24 ))
      add( objectiveCombo )
      objectiveCombo.addItemListener( objectiveCombo )
   }

   fun startButton ()
   {
      doButton.setActionCommand( StateMachine.START )
      doButton.setIcon( START_ICON )
   }

   fun stepButton ()
   {
      doButton.setActionCommand( StateMachine.NEXT )
      doButton.setIcon( NEXT_ICON )
   }

   inner class KAspectComboBox (aspects : Array<Aspect>) : JComboBox<Aspect>( aspects ), ItemListener
   {
      override fun itemStateChanged (e : ItemEvent)
      {
         if (e.getStateChange() == ItemEvent.SELECTED)
         {
            selectedAspect = getSelectedItem() as Aspect
            GUI.panels.contacts.valueChanged(ListSelectionEvent( Any(), 0, 0, false ))
         }
      }
   }

   inner class KStatesComboBox (stringArray : Array<String?>) : JComboBox<String>( stringArray ), ItemListener
   {
      override fun itemStateChanged (e : ItemEvent)
      {
         if (e.getStateChange() == ItemEvent.SELECTED)
            startButton()
      }
   }

   override fun actionPerformed (e : ActionEvent)
   {
      StateMachine.respond( e.getActionCommand() )
   }
}
