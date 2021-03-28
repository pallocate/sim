package sim

import java.awt.Color
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.border.LineBorder
import sim.panels.*

object GUI
{
   internal val frame by lazy { KFrame() }
   internal val infoPanel                               = KInfoPanel()
   internal val contactsPanel                           = KContactsPanel()
   internal val presentationPanel                       = KPresentationPanel()
   internal val outputPane                              = KOutputPane()
   internal val toolbar                                 = KToolbar()
   internal var statusBar                               = object : JLabel( "  Ready" )
   {
      override fun setText (s : String) = super.setText( "  $s" )
      override fun getBorder() = LineBorder( Color.white, 1 )
      override fun getPreferredSize() = Dimension( 800, 16 )
   }

   internal fun present ( output : () -> String, append : Boolean = false) = if (append)
      presentationPanel.textArea.append( output() )
   else
      presentationPanel.textArea.setText( output() )

   internal fun start () = frame
}
