package sim

import pen.Log

object EventHandler
{
   val INIT                                     = "INIT"
   val NEXT                                     = "NEXT"
   val STOP                                     = "STOP"
   var simulation : Simulation                  = VoidSimulation
   var counter                                  = 0

   fun handle (command : String)
   {
      when (command)
      {
         INIT ->
         {
            val selectedIndex = GUI.toolbar.simulationCombo.getSelectedIndex()

            if (selectedIndex >= 0)
            {
               if (!(simulation is VoidSimulation))
                  stopSimulation()

               val selected = Simulations.simulations[selectedIndex]
               val constructors = selected.constructors
               if (!constructors.isEmpty())
               {
                  simulation = constructors.first().call()

                  with (GUI) {
                     present({ "" })
                     outputPane.logPanel.clear()
                     outputPane.txPanel.clear()
                     toolbar.stepButton()
                     counter = 0

                     try
                     {
                        simulation.setup()
                        val contacts = simulation.contacts()
                        statusBar.setText( "Simulation started" )
                        contactsPanel.list.setListData( contacts )
                        frame.setTitle( "Simulator - " + selected.simpleName )
                     }
                     catch (e : Exception)
                     {
                        printStackTrace( e )
                        stopSimulation()
                     }
                  }
               }
            }
         }
         NEXT ->
         {
            GUI.present({ "" })
            try
            {
               if (simulation.state == State.DONE)
                  stopSimulation()
               else
               {
                  simulation.next( counter )
                  if (simulation.state == State.DONE)
                     GUI.statusBar.setText( "All steps completed" )
                  else
                     simulation.state = State.RUNNING
               }

               counter += 1
            }
            catch (e : Exception)
            {
               printStackTrace( e )
               stopSimulation()
            }
         }
         STOP -> stopSimulation()

         else -> {}
      }
   }

   private fun stopSimulation ()
   {
      simulation.cancel()
      simulation.state = State.UNDEFINED
      GUI.statusBar.setText( "Simulation stopped " )
      GUI.toolbar.startButton()
   }

   private fun printStackTrace (e : Exception)
   {
      val delim = "\n=============================================================\n"
      val msg = "${e::class.simpleName}: ${e.message}"

      println( delim )
      Log.error( msg )
      println( msg )
      for (stackTraceElement in e.getStackTrace())
         if (!stackTraceElement.isNativeMethod())
            println( stackTraceElement.toString() )

      println( delim )
   }
}
