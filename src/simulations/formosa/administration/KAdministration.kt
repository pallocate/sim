package sim.simulations.formosa.administration

//import kotlinx.coroutines.*
import pen.tests.Contacts
import kick.KTransactionPair
//import kick.KTransactionList
import sim.*
import sim.KAnimator.KAnimation
import sim.gui.GUI

class KAdministration () : States()
{
   override fun states () = listOf(
      KState(
         KTransactionPair( RECEIPT ),
         "Patricia grants the store permission to set her account detail,\n" +
         "The store add products to the receipt in Patricias account detail."
      ),
      KState(
         KTransactionPair( QUORUM ),
         "Patricia adds and removes signatories and changes the number of required."
      )
   )

   override fun contacts () = Contacts.toArray()
}
