package sim.simulations.formosa.administration

import pen.tests.Contacts
import kick.*
import sim.*
import sim.KAnimator.KAnimation
import sim.gui.GUI

class KAdministration () : States()
{
   override fun states () = listOf(
      KCommitableState(
         KTransactionPair( QUORUM ),
         "Patricia adds and removes signatories and changes the number of required."
      ),
      KSubmitableState(
         KQuery( READ_TRANSACTIONS ),
         "Artysan reads Patricias credit transactions and checks that her consumption keeps within the plan."
      )
   )

   override fun contacts () = Contacts.toArray()
}
