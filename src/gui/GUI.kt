package sim.gui

import java.awt.Color
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.border.LineBorder
import sim.Simulation
import sim.VoidSimulation

object GUI
{
   internal var simulation : Simulation                 = VoidSimulation
   internal var frame : KFrame?                         = null
   internal val toolbar by lazy { KToolbar() }

   internal object panels
   {
      val info                                          = KInfoPanel()
      val contacts                                      = KContactsPanel()
      val log                                           = KLogPanel()
      val transactions                                  = KTransactionPanel()
   }

   internal var statusBar                               = object : JLabel( "  Ready" )
   {
      override fun setText (text : String) = super.setText( "  $text" )
      override fun getBorder() = LineBorder( Color.white, 1 )
      override fun getPreferredSize() = Dimension( 800, 16 )
   }
}
