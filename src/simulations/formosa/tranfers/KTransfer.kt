package sim.simulations.formosa.transfers

import kotlinx.coroutines.*
import pen.Log
import pen.tests.Contacts
import kick.*
import sim.gui.GUI
import sim.KAnimator
import sim.KAnimator.KAnimation
import sim.States

data class KState (val txPair : TxPair, val text : String = "", val animation : KAnimation = KAnimation.void()) {}

class KTransfer () : States
{
   private val commiterator = KCommiter()
   private val animator = KAnimator()

   val states = listOf(
      KState(CREDIT_TRANSACTIONS, "Basic transactions test.", KAnimation( "formosa/scene1", 28, GUI.simulation )),
      KState( ACCOUNT_DETAIL, "Changing Davids account details" ),
      KState( MULTI_SIGN, "Changing Patricias signatories and qourum" )
   )
   val iterator = states.listIterator()

   override fun next ()
   {
      if (iterator.hasNext())
      {
         val state = iterator.next()

         if (state.text != "")
            Log.info( state.text )

         if (!state.animation.isVoid())
            animator.runAnimation( state.animation )

         commiterator.commit( state.txPair, GUI.panels.transactions )
      }
   }

   override fun cancel ()
   {
      animator.cancel()
      commiterator.cancel()
   }

   override fun contacts () = Contacts.toArray()
   override fun hasNext () = iterator.hasNext()
}
//      Log.info( "Farmlands workers council issues 500k commons credits.\n" )
//      Log.info( "And uses some of it to buy tools from the hardware store.\n" )
//      Log.info( "It also issues and transfers 100k crow beach credits to David as salary.\n\n" )
//      Log.info( "The hardware store recieves the credits and destroys them, since they have been used." )
