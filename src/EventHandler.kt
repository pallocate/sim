package sim

import kotlin.reflect.KClass
import pen.Log

object EventHandler
{
   val SETUP                                    = "SETUP"
   val NEXT                                     = "NEXT"
   val STOP                                     = "STOP"
   var simulation : Simulation                  = VoidSimulation
   var counter                                  = 0

   fun handle (command : String)
   {
      when (command)
      {
         SETUP ->
         {
            if (!(simulation is VoidSimulation))
               stopSimulation()

            val simulationName = GUI.toolbar.simulationCombo.getSelectedItem() as String
            val selected = Simulations.get( simulationName )

            if (selected != null)
            {
               val constructors = selected.constructors

               if (!constructors.isEmpty())
               {
                  simulation = constructors.first().call()

                  with (GUI) {
                     frame.setTitle( "Simulator - " + selected.simpleName )
                     contactsPanel.list.setListData( simulation.contacts() )
//                     presentationPanel.setImage()
                     outputPane.logPanel.clear()
                     outputPane.txPanel.clear()
                     toolbar.stepButton()
                     counter = 0

                     try
                     {
                        statusBar.setText( "Simulation started" )
                        simulation.setup()
                     }
                     catch (e : Exception)
                     {
                        stopSimulation()
                        println( e )
                     }
                  }
               }
            }
         }
         NEXT ->
         {
//            GUI.presentationPanel.setImage()
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
               stopSimulation()
               println( e )
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
}
