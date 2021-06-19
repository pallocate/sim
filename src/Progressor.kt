package sim

import pen.Log
import sim.gui.GUI

/** Steps through a animation. */
object Progressor
{
   val START                                           = "START"
   val NEXT                                            = "NEXT"
   val STOP                                            = "STOP"
   private var simulations : Simulation                = VoidSimulation

   fun command (action : String)
   {
      when (action)
      {
         START ->
         {
            if (!(simulations is VoidSimulation))
               simulations.cancel()

            val simulationName = GUI.toolbar.simulationCombo.getSelectedItem() as String
            val selected = GUI.simulation.simulations.get( simulationName )

            if (selected != null)
            {
               simulations = selected
               GUI.frame?.setTitle( "Simulator - " + simulationName )
               GUI.panels.contacts.list.setListData( simulations.contacts() )
               GUI.panels.log.clear()
               GUI.panels.transactions.clear()
               GUI.toolbar.stepButton()

               try
               {
                  simulations.next()
                  GUI.statusBar.setText( "Simulation started" )
               }
               catch (e : Exception)
               {
                  stopSimulation()
                  println( e )
               }
            }
         }

         NEXT ->
         {
            try
            {
               if (!simulations.hasNext())
                  stopSimulation()
               else
               {
                  simulations.next()
                  if (!simulations.hasNext())
                     GUI.statusBar.setText( "All steps completed" )
               }
            }
            catch (e : Exception)
            {
               stopSimulation()
               println( e )
            }
         }

         STOP ->
            stopSimulation()

         else -> {}
      }
   }

   private fun stopSimulation ()
   {
      simulations.cancel()
      GUI.statusBar.setText( "Simulation stopped " )
      GUI.toolbar.startButton()
   }
}
