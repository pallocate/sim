package sim

import java.awt.GridLayout
import javax.swing.JTabbedPane
import javax.swing.event.ChangeListener
import javax.swing.event.ChangeEvent
import sim.panels.KLogPanel
import sim.panels.KTransactionPanel

class KOutputPane : JTabbedPane( JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT ), ChangeListener
{
   val logPanel = KLogPanel()
   val txPanel = KTransactionPanel()

   init
   {
      add( "Log", logPanel )
      add( "Transactions", txPanel )
   }

   override fun stateChanged (e : ChangeEvent)
   {
      val selected = getSelectedComponent()
      println( selected )
   }
}
