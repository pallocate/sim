package sim.simulations.formosa

import kotlinx.coroutines.*
import pen.Log
import pen.tests.Contacts
import kick.*
import sim.GUI
import sim.KAnimator
import sim.KAnimator.KAnimation
import sim.Simulation
import sim.State
import sim.simulations.formosa.setup.*
import sim.simulations.formosa.tests.*
import sim.simulations.formosa.queries.ROLES

class KFormosa : Simulation
{
   override var state = State.UNDEFINED
   private val commiter = KCommiter( GUI.outputPane.txPanel )
   private val animator = KAnimator()

   init
   {
      /* Accounts has to be created ASAP so that they exist in the blockchain when we try to modify them. */
      commiter.commit( CREATE_ACCOUNTS )
   }

   override fun setup ()
   {
      Log.info( "Setting up the economy.." )

      animator.runAnimation(KAnimation( "formosa/scene1", 28, GUI.animationCanvas ))
      KListCommiter().commit(listOf( CONSUMER_COUNCILS, WORKERS_COUNCILS, ETC ))
   }

   override fun next (step : Int)
   {
      when (step)
      {
         0 ->
         {
            Log.info( "Basic transactions test." )
//            Log.info( "Farmlands workers council issues 500k commons credits.\n" )
//            Log.info( "And uses some of it to buy tools from the hardware store.\n" )
//            Log.info( "It also issues and transfers 100k crow beach credits to David as salary.\n\n" )
//            Log.info( "The hardware store recieves the credits and destroys them, since they have been used." )

            animator.runAnimation(KAnimation( "formosa/scene2", 28, GUI.animationCanvas ))
            commiter.commit( CREDIT_TRANSACTIONS )
         }
         1 ->
         {
            Log.info( "Testing changing Patricias signatories and Davids account details" )
            commiter.commit( MULTI_SIGN )
            commiter.commit( ACCOUNT_DETAIL )
            state = State.DONE
         }
         else -> {}
      }
   }

   override fun cancel ()
   {
      animator.cancel()
      commiter.cancel()
   }
   override fun contacts () = Contacts.toArray()
}
