package sim.simulations.formosa

import pen.Log
import pen.tests.Contacts
import kick.*
import sim.GUI
import sim.Simulation
import sim.State
import sim.simulations.formosa.setup.*
import sim.simulations.formosa.tests.*
import sim.simulations.formosa.queries.ROLES

class KFormosa : Simulation
{
   val commiter = KCommiter( GUI.outputPane.txPanel )
   val submitter = KSubmitter( GUI.outputPane.txPanel )
   override var state = State.UNDEFINED

   init
   {commiter.commit( CREATE_ACCOUNTS )}

   override fun setup ()
   {
      Log.info( "Setting up the economy.." )
      KListCommiter().commit(listOf( CONSUMER_COUNCILS, WORKERS_COUNCILS, ETC ))
   }

   @kotlinx.coroutines.ExperimentalCoroutinesApi
   override fun next (step : Int)
   {
      when (step)
      {
         0 ->
         {
            Log.info( "Basic transactions test." )
            GUI.present( {"Farmlands workers council issues 500k commons credits."} )
            GUI.present( {"And uses some of it to buy tools from the hardware store."}, true )
            GUI.present( {"It also issues and transfers 100k crow beach credits to David as salary."}, true )
            GUI.present( {"The hardware store recieves the credits and destroys them, since they have been used."}, true )

            commiter.commit( CREDIT_TRANSACTIONS )
         }
         1 ->
         {
            Log.info( "Testing change in Patricias signatories and Davids Account details" )
            commiter.commit( MULTI_SIGN )
            commiter.commit( ACCOUNT_DETAIL )
            state = State.DONE
         }
         else -> {}
      }
   }

   override fun cancel () = commiter.cancel()
   override fun contacts () = Contacts.toArray()
}
