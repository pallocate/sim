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

      try
      {
         GUI.frame
      }
      catch (t : Throwable)
      { Log.critical( "" + t.message ) }
   }
}
