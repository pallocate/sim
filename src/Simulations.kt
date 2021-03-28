package sim

import pen.Log
import pen.par.KContact
import sim.simulations.formosa.KFormosa

enum class State { UNDEFINED, RUNNING, DONE }

interface Simulation
{
   var state : State
   fun setup ()
   fun cancel ()
   fun next (step : Int)
   fun contacts () : Array<KContact>
}

object VoidSimulation : Simulation
{
   override var state = State.UNDEFINED
   override fun setup () = logMessage()
   override fun cancel () {}
   override fun next (step : Int) = logMessage()
   override fun contacts () = emptyArray<KContact>()
   private fun logMessage ()
   { Log.warn( "No simulation loaded" ) }
}

object Simulations
{
   val simulations = arrayOf( KFormosa::class )
   fun stringArray () : Array<String?> = simulations.map({ it.simpleName }).toTypedArray()
}
