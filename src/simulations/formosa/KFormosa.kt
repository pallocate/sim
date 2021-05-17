package sim.simulations.formosa

import javax.swing.ImageIcon
import pen.Log
import kick.KSetuper
import sim.SimulationPanel
import sim.States
import sim.simulations.formosa.setup.*
import sim.simulations.formosa.shopping.KShopping
import sim.simulations.formosa.administration.KAdministration

class KFormosa () : SimulationPanel()
{
   override val background = ImageIcon( "build/dist/resources/images/formosa.png" ).getImage()

   init
   {
      Log.info( "Setting up the economy.." )

      KSetuper().setup(
         /* Accounts used in the simulation has to be created as soon as possible.
          * Otherwise operations relying on the existence of thoose accounts will fail. */
         CREATE_ACCOUNTS,
         listOf( WORKERS_COUNCILS, ETC )
      )
   }

   override val states by lazy {mapOf<String, States>(
      "Shopping" to KShopping(), "Administration" to KAdministration()
   )}
}
