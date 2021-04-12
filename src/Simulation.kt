package sim

import pen.Log
import pen.par.KContact

interface States
{
   fun hasNext () : Boolean
   fun next ()
   fun cancel ()
   fun contacts () : Array<KContact>
}

object VoidStates : States
{
   override fun hasNext () = false
   override fun next () = logMessage()
   override fun cancel () {}
   override fun contacts () = emptyArray<KContact>()
   private fun logMessage ()
   {Log.warn( "No objective loaded" )}
}

interface Simulation
{ val states : Map<String, States> }

object VoidSimulation : Simulation
{ override val states = mapOf<String, States>() }
