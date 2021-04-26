package sim

import pen.Log
import pen.par.KContact
import kick.KCommiter
import kick.Commitable
import sim.KAnimator.KAnimation
import sim.gui.GUI

data class KState (val commitable : Commitable, val text : String = "", val animation : KAnimation = KAnimation.void()) {}

abstract class States
{
   protected val commiter = KCommiter()
   protected val animator = KAnimator()

   val iterator = states().listIterator()

   fun next ()
   {
      if (iterator.hasNext())
      {
         val state = iterator.next()

         if (state.text != "")
            Log.info( state.text + "\n\n" )

         if (!state.animation.isVoid())
            animator.runAnimation( state.animation )

         commiter.commit( state.commitable, GUI.panels.transactions )
      }
   }

   fun hasNext () = iterator.hasNext()
   fun cancel ()
   {
      animator.cancel()
      commiter.cancel()
   }

   abstract fun states () : List<KState> 
   abstract fun contacts () : Array<KContact>
}
object VoidStates : States()
{
   override fun states () = emptyList<KState>()
   override fun contacts () = emptyArray<KContact>()
}

interface Simulation
{ val states : Map<String, States> }
object VoidSimulation : Simulation
{ override val states = mapOf<String, States>() }
