package sim

import sim.simulations.formosa.KFormosa
import pen.Log
import pen.LogLevel

object Main
{
   @JvmStatic
   fun main (args : Array<String>)
   {
      Log.level = LogLevel.INFO

      if (!args.isEmpty())
      {
         val simulation = when (args.last().toLowerCase())
         {
            "formosa" -> KFormosa()
            else -> VoidSimulation
         }

         GUI.start()
      }
      else
         println( "Usage: sim SIMULATION\n" )
   }
}
