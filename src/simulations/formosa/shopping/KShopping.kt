package sim.simulations.formosa.shopping

import pen.tests.Contacts
import kick.*
import sim.*
import sim.KAnimator.KAnimation
import sim.gui.GUI

class KShopping () : States()
{
   override fun states () = listOf(
      KCommitableState(
         KTransaction( SALARY ),
         "The Factory pays Patricia her salary.",
         KAnimation( "formosa/scene1", 36, GUI.simulation )
      ),

      KSubmitableState(
         KQuery( READ_DETAIL ),
         "Patricia vists a shop to buy some clothes. She 'blips' with her mobile and behind the scenes,\n" +
         "the shopping list is transfered using NFC, BLE or HTTPS and the total gets presented to her.\n" +
         "The shop retrieves Patricias previous consumption in the shop(if any) from her account detail."
      ),

      KCommitableState(
         KTransaction( PAYING ),
         "The required credits are transfered and the shop gets write access to her account detail."
      ),

      KCommitableState(
         KTransaction( RECEIPT ),
         "The shop receives the credits and updates Patricias account detail with the new products."
      )
   )

   override fun contacts () = Contacts.toArray()
}
