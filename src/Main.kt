package sim

import pen.Log
import pen.LogLevel
import sim.gui.GUI
import sim.gui.KFrame
import sim.simulations.formosa.KFormosa

object Main
{
   @JvmStatic
   fun main (args : Array<String>)
   {
      Log.level = LogLevel.INFO

      if (args.isEmpty())
         println( "Usage: sim SIMULATION\n" )
      else
      {
         GUI.simulation = when (args.last().toLowerCase()) {
            "formosa" -> KFormosa()
            else -> VoidSimulation
         }

         if (GUI.simulation !is VoidSimulation)
         {
            try
            {GUI.frame = KFrame( GUI.simulation )}
            catch (t : Throwable)
            { Log.critical( t.message.toString() ) }
         }
      }
   }
}
