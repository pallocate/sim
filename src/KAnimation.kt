package sim

import java.awt.Image
import java.awt.Canvas
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import javax.swing.JPanel
import kotlinx.coroutines.*
import pen.newScope

class KAnimator ()
{
   class KAnimation (val name : String, val lastIdx : Int, val simulation : Simulation)
   {
      companion object
      { fun void () = KAnimation( "", 0, VoidSimulation ) }

      val backgroundImage = ImageIcon( "build/dist/resources/animations/${name}/bg.png" ).getImage()
      val images = ArrayList<Image>()

      init
      {
         for (imageNr in 0..lastIdx)
         {
            var num = imageNr.toString().padStart( 4, '0' )
            val filename = "build/dist/resources/animations/${name}/animation/${num}.png"
            images.add(ImageIcon( filename ).getImage())
         }
      }

      suspend fun animate ()
      {
         if (simulation is JPanel)
            for (imageNr in 0..lastIdx)
            {
               val bufferedImage = BufferedImage( 800, 600, BufferedImage.TYPE_INT_RGB )
               val biGraphics = bufferedImage.createGraphics()

               biGraphics.drawImage( backgroundImage, 0, 0, null )
               biGraphics.drawImage( images[imageNr], 0, 0, null )
               delay( 100L )

               simulation.graphics.drawImage( bufferedImage, 0, 0, null )
            }
      }

      fun isVoid() = simulation is VoidSimulation
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
