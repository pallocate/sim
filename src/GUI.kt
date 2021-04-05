package sim

import java.awt.Color
import java.awt.Canvas
import java.awt.Graphics
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.ImageIcon
import javax.swing.border.LineBorder
import sim.panels.*

object GUI
{
   internal val frame by lazy { KFrame() }
   internal val infoPanel                               = KInfoPanel()
   internal val contactsPanel                           = KContactsPanel()
   internal val outputPane                              = KOutputPane()
   internal val toolbar                                 = KToolbar()
   internal val animationCanvas                         = createAnimationCanvas()

   internal var statusBar                               = object : JLabel( "  Ready" )
   {
      override fun setText (text : String) = super.setText( "  $text" )
      override fun getBorder() = LineBorder( Color.white, 1 )
      override fun getPreferredSize() = Dimension( 800, 16 )
   }
}

fun createAnimationCanvas () = object : Canvas()
{
   var defaultBackground = ImageIcon( "build/dist/resources/animations/splash.png" ).getImage()

   override fun paint (g : Graphics)
   {g.drawImage( defaultBackground, 0, 0, null )}
}
