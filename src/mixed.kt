package sim

import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.Graphics
import javax.swing.JPanel
import sim.gui.GUI

abstract class SimulationPanel : JPanel()
{
   override fun paintComponent (g : Graphics)
   {
      val popupVisible = GUI.toolbar.objectiveCombo.isPopupVisible()

      if (!popupVisible)
         g.drawImage( background, 0, 0, null )
   }

   abstract val states : Map<String, States>
   abstract val background : Image
}
object VoidSimulationPanel : SimulationPanel()
{
   override val states=mapOf<String,States>()
   override val background = BufferedImage(1,1,BufferedImage.TYPE_INT_RGB)
}

/** What aspect of an account to query. */
enum class Aspect
{ ACCOUNT, ACCOUNT_TX, ASSETS, ASSET_TX, DETAIL, SIGNATORIES }

internal var queryCounter = (Math.random()*1000000).toLong()
