package sim

import pen.Log
import sim.gui.GUI

object StateMachine
{
   val START                                           = "START"
   val NEXT                                            = "NEXT"
   val STOP                                            = "STOP"
   var states : States                                 = VoidStates

   fun respond (command : String)
   {
      when (command)
      {
         START ->
         {
            if (!(states is VoidStates))
               states.cancel()

            val stateName = GUI.toolbar.objectiveCombo.getSelectedItem() as String
            val selected = GUI.simulation.states.get( stateName )

            if (selected != null)
            {
               states = selected
               GUI.frame?.setTitle( "Simulator - " + stateName )
               GUI.panels.contacts.list.setListData( states.contacts() )
               GUI.panels.log.clear()
               GUI.panels.transactions.clear()
               GUI.toolbar.stepButton()

               try
               {
                  states.next()
                  GUI.statusBar.setText( "States started" )
               }
               catch (e : Exception)
               {
                  stopStates()
                  println( e )
               }
            }
         }

         NEXT ->
         {
            try
            {
               if (!states.hasNext())
                  stopStates()
               else
               {
                  states.next()
                  if (!states.hasNext())
                     GUI.statusBar.setText( "All steps completed" )
               }
            }
            catch (e : Exception)
            {
               stopStates()
               println( e )
            }
         }

         STOP ->
            stopStates()

         else -> {}
      }
   }

   private fun stopStates ()
   {
      states.cancel()
      GUI.statusBar.setText( "States stopped " )
      GUI.toolbar.startButton()
   }
}
