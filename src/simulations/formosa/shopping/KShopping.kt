package sim.simulations.formosa.shopping

import pef.utils.Contacts
import kick.*
import sim.*
import sim.KAnimator.KAnimation
import sim.gui.GUI

class KShopping () : Simulation()
{
   override fun bundles () = listOf(
      KCommitBundle(
         KTransaction( SALARY ),
         "The Factory pays Patricia her salary.",
         KAnimation( "formosa/scene1", 36, GUI.simulation )
      ),

      KSubmitBundle(
         KQuery( READ_DETAIL ),
         "Patricia vists a shop to buy some clothes. She 'blips' with her mobile and behind the scenes,\n" +
         "the shopping list is transfered using NFC, BLE or HTTPS and the total gets presented to her.\n" +
         "The shop retrieves Patricias previous consumption in the shop(if any) from her account detail.",
         KAnimation( "formosa/scene2", 54, GUI.simulation )
      ),

      KCommitBundle(
         KTransaction( PAYING ),
         "The required credits are transfered and the shop gets write access to her account detail.",
         KAnimation( "formosa/scene3", 35, GUI.simulation )
      ),

      KCommitBundle(
         KTransaction( RECEIPT ),
         "The shop verifies the transfer and updates Patricias consumption list with the new products."
      )
   )

   override fun contacts () = Contacts.toArray()
}
