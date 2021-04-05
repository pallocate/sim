package sim

import java.awt.Image
import java.awt.Canvas
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import kotlinx.coroutines.*
import pen.newScope

class KAnimator ()
{
   class KAnimation (val name : String, val lastIdx : Int, val animationCanvas : Canvas)
   {
      val backgroundImage = ImageIcon( "build/dist/resources/animations/${name}/bg.png" ).getImage()
      val images = ArrayList<Image>()

      init
      {
         for (imageNr in 0..lastIdx)
         {
            var num = imageNr.toString().padStart( 5, '0' )
            val filename = "build/dist/resources/animations/${name}/animation/${num}.png"
            images.add(ImageIcon( filename ).getImage())
         }
      }

      suspend fun animate ()
      {
         for (imageNr in 0..lastIdx)
         {
            val bufferedImage = BufferedImage( 800, 600, BufferedImage.TYPE_INT_RGB )
            val bGraphics = bufferedImage.createGraphics()

            bGraphics.drawImage( backgroundImage, 0, 0, null )
            bGraphics.drawImage( images[imageNr], 0, 0, null )
            delay( 100L )

            animationCanvas.graphics.drawImage( bufferedImage, 0, 0, null )
         }
      }
   }

   private var scope = newScope()

   fun runAnimation (animation : KAnimation)
   {
      if (!scope.isActive)
         scope = newScope()

      scope.launch {
         animation.animate()
      }
   }

   fun cancel () = scope.cancel()
}
//val scaledBgImage = bufferedImage.getScaledInstance( width, height, Image.SCALE_DEFAULT )
