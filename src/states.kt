package sim

import pen.Log
import pen.par.KContact
import kick.KSubmiter
import kick.KCommiter
import kick.Commitable
import kick.Submitable
import sim.KAnimator.KAnimation
import sim.gui.GUI

sealed class State (val text : String, val animation : KAnimation) {}

class KCommitableState (
   val commitable : Commitable, text : String = "", animation : KAnimation = KAnimation.void()
) : State( text, animation ) {}
class KSubmitableState (
   val submitable : Submitable, text : String = "", animation : KAnimation = KAnimation.void()
) : State( text, animation ) {}

abstract class States
{
   private val submiter = KSubmiter()
   private val commiter = KCommiter()
   private val animator = KAnimator()

   private val iterator = states().listIterator()

   fun next ()
   {
      if (iterator.hasNext())
      {
         val state = iterator.next()

         if (state.text != "")
            Log.info( state.text + "\n\n" )

         if (!state.animation.isVoid())
            animator.runAnimation( state.animation )

         when (state)
         {
            is KCommitableState -> commiter.commit( state.commitable, GUI.panels.transactions )
            is KSubmitableState -> submiter.submit( state.submitable, GUI.panels.info )
         }
      }
   }

   fun hasNext () = iterator.hasNext()
   fun cancel ()
   {
      animator.cancel()
      commiter.cancel()
   }

   abstract fun states () : List<State>
   abstract fun contacts () : Array<KContact>
}

object VoidStates : States()
{override fun states()=emptyList<State>();override fun contacts()=emptyArray<KContact>()}
