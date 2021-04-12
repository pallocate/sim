package sim.simulations.formosa

import java.awt.Canvas
import java.awt.Graphics
import javax.swing.ImageIcon
import kotlin.reflect.KClass
import pen.Log
import kick.KSetuper
import sim.Simulation
import sim.States
import sim.simulations.formosa.setup.*
import sim.simulations.formosa.transfers.KTransfer

/* Accounts used in the simulation has to be created as soon as possible.
 * Otherwise operations relying on the existence of thoose accounts will fail. */
class KFormosa () : Canvas(), Simulation
{
   var defaultBackground = ImageIcon( "build/dist/resources/animations/splash.png" ).getImage()

   init
   {
      Log.info( "Setting up the economy.." )

      KSetuper().setup(
         CREATE_ACCOUNTS,
         listOf( CONSUMER_COUNCILS, WORKERS_COUNCILS, ETC )
      )
   }

   override fun paint (g : Graphics)
   {g.drawImage( defaultBackground, 0, 0, null )}

   override val states by lazy { mapOf<String, States>(
      "Transfers" to KTransfer()
   ) }
}
