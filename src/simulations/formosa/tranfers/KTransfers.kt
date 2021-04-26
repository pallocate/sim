package sim.simulations.formosa.transfers

//import kotlinx.coroutines.*
import pen.tests.Contacts
import kick.KTransactionPair
import kick.KTransactionList
import sim.*
import sim.KAnimator.KAnimation
import sim.gui.GUI

/* TODO: Move stuff to States interface or abstract class */
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
         "The shop destroys the used credits."
      ),
      KState(
         KTransactionPair( INVOICE ),
         "The University pays Farmlands for the daily fruit basket.\n" +
         "Farmlands destroys the used credits."
      )
   )

   override fun contacts () = Contacts.toArray()
}
//      Log.info( "Farmlands workers council issues 500k commons credits.\n" )
//      Log.info( "And uses some of it to buy tools from the hardware store.\n" )
//      Log.info( "It also issues and transfers 100k crow beach credits to David as salary.\n\n" )
//      Log.info( "The hardware store recieves the credits and destroys them, since they have been used." )
