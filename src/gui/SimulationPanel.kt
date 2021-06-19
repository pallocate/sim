package sim.gui

import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.Graphics
import javax.swing.JPanel
import sim.Simulation

/** Base class for creating simulations. */
abstract class SimulationPanel : JPanel()
{
   override fun paintComponent (g : Graphics)
   {
      val popupVisible = GUI.toolbar.simulationCombo.isPopupVisible()

      if (!popupVisible)
         g.drawImage( background, 0, 0, null )
   }

   abstract val simulations : Map<String, Simulation>
   abstract val background : Image
}
object VoidSimulationPanel : SimulationPanel()
{
   override val simulations=mapOf<String,Simulation>()
   override val background = BufferedImage(1,1,BufferedImage.TYPE_INT_RGB)
}
