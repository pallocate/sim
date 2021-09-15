package sim

import java.awt.Image
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import kotlinx.coroutines.*
import pen.newScope
import sim.gui.SimulationPanel
import sim.gui.VoidSimulationPanel

/** Runs a animation on a simulation panel. */
class KAnimator ()
{
   /** A instance of this animates a series of png images onto a background.
     * @param lastIdx last index number of png images in a animation "0000.png" being the first. */
   class KAnimation (private val name : String, private val lastIdx : Int, private val simulation : SimulationPanel)
   {
      companion object
      { fun void () = KAnimation( "", 0, VoidSimulationPanel ) }

      private val backgroundImage = ImageIcon( "build/dist/resources/animations/${name}/bg.png" ).getImage()
      private val images = ArrayList<Image>()

      internal suspend fun animate ()
      {
         for (imageNr in 0..lastIdx)
         {
            var num = imageNr.toString().padStart( 4, '0' )
            val filename = "build/dist/resources/animations/${name}/animation/${num}.png"
            images.add(ImageIcon( filename ).getImage())
         }

         for (imageNr in 0..lastIdx)
         {
            val bufferedImage = BufferedImage( 800, 600, BufferedImage.TYPE_INT_RGB )
            val biGraphics = bufferedImage.createGraphics()

            biGraphics.drawImage( backgroundImage, 0, 0, null )
            biGraphics.drawImage( images[imageNr], 0, 0, null )
            delay( 100L )

            simulation.graphics.drawImage( bufferedImage, 0, 0, null )
         }
         
         images.clear()
      }

      fun isVoid() = simulation is VoidSimulationPanel
   }

   private var scope = newScope()

   fun runAnimation (animation : KAnimation)
   {
      if (!scope.isActive)
         scope = newScope()

      scope.launch { animation.animate() }
   }

   fun cancel () = scope.cancel()
}
//val scaledBgImage = bufferedImage.getScaledInstance( width, height, Image.SCALE_DEFAULT )
