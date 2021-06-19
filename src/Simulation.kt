package sim

import pen.Log
import pen.par.KContact
import kick.KSubmiter
import kick.KCommiter
import sim.gui.GUI

abstract class Simulation
{
   private val submiter = KSubmiter()
   private val commiter = KCommiter()
   private val animator = KAnimator()

   private val iterator = bundles().listIterator()

   fun next ()
   {
      if (iterator.hasNext())
      {
         val bundle = iterator.next()

         if (bundle.text != "")
            Log.info( bundle.text + "\n\n" )

         if (!bundle.animation.isVoid())
            animator.runAnimation( bundle.animation )

         when (bundle)
         {
            is KCommitBundle -> commiter.commit( bundle.commitable, GUI.panels.transactions )
            is KSubmitBundle -> submiter.submit( bundle.submitable, GUI.panels.info )
         }
      }
   }

   fun hasNext () = iterator.hasNext()
   fun cancel ()
   {
      animator.cancel()
      commiter.cancel()
   }

   internal abstract fun bundles () : List<Bundle>
   internal abstract fun contacts () : Array<KContact>
}

object VoidSimulation : Simulation()
{override fun bundles()=emptyList<Bundle>();override fun contacts()=emptyArray<KContact>()}
