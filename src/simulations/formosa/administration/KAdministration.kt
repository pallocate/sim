package sim.simulations.formosa.administration

import pef.utils.Contacts
import kick.*
import sim.*
import sim.KAnimator.KAnimation
import sim.gui.GUI

class KAdministration () : Simulation()
{
   override fun bundles () = listOf(
      KCommitBundle(
         KTransactionPair( QUORUM ),
         "Patricia adds and removes signatories and changes the number of required."
      ),
      KSubmitBundle(
         KQuery( READ_TRANSACTIONS ),
         "Artysan reads Patricias credit transactions and checks that her consumption keeps within the plan."
      )
   )

   override fun contacts () = Contacts.toArray()
}
