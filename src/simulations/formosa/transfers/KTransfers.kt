package sim.simulations.formosa.transfers

//import kotlinx.coroutines.*
import pen.tests.Contacts
import kick.KTransactionPair
import kick.KTransactionList
import sim.*
import sim.KAnimator.KAnimation
import sim.gui.GUI

class KTransfers () : States()
{
   override fun states () = listOf(
      KState(
         KTransactionList( SALARY ),
         "The Factory pays Patricia her salary.\n" + 
         "The Hospital pays David his salary.",
         KAnimation( "formosa/scene1", 36, GUI.simulation )
      ),
      KState(
         KTransactionPair( SHOPPING ),
         "Patricia buys some clothes in a shop.\n" +
         "The shop then destroys the used credits."
      ),
      KState(
         KTransactionPair( INVOICE ),
         "The University pays Farmlands for the daily fruit basket.\n" +
         "Farmlands then destroys the used credits."
      )
   )

   override fun contacts () = Contacts.toArray()
}
