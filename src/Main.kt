package sim

import pef.Log
import pef.LogLevel
import sim.gui.GUI
import sim.gui.KFrame
import sim.simulations.formosa.KFormosa
import sim.gui.VoidSimulationPanel

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
         GUI.simulation = when (args.last().lowercase()) {
            "formosa" -> KFormosa()
            else -> VoidSimulationPanel
         }

         if (GUI.simulation !is VoidSimulationPanel)
         {
            try
            {GUI.frame = KFrame()}
            catch (t : Throwable)
            { Log.critical( t.message.toString() ) }
         }
      }
   }
}
