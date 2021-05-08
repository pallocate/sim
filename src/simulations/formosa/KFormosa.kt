package sim.simulations.formosa

import java.awt.Canvas
import java.awt.Graphics
import javax.swing.ImageIcon
import javax.swing.JPanel
import kotlin.reflect.KClass
import pen.Log
import kick.KSetuper
import sim.Simulation
import sim.States
import sim.gui.GUI
import sim.simulations.formosa.setup.*
import sim.simulations.formosa.transfers.KTransfers
import sim.simulations.formosa.administration.KAdministration

class KFormosa () : JPanel(), Simulation
{
   val defaultBackground = ImageIcon( "build/dist/resources/images/formosa.png" ).getImage()

   init
   {
      Log.info( "Setting up the economy.." )

      KSetuper().setup(
         /* Accounts used in the simulation has to be created as soon as possible.
          * Otherwise operations relying on the existence of thoose accounts will fail. */
         CREATE_ACCOUNTS,
         listOf( CONSUMER_COUNCILS, WORKERS_COUNCILS, ETC )
      )
   }

   override fun paintComponent (g : Graphics)
   {
      val popupVisible = GUI.toolbar.objectiveCombo.isPopupVisible()
      
      if (!popupVisible)
         g.drawImage( defaultBackground, 0, 0, null )
   }

   override val states by lazy {mapOf<String, States>(
      "Transfers" to KTransfers(), "Administration" to KAdministration()
   )}
}
