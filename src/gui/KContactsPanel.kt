package sim.gui

import java.awt.GridLayout
import javax.swing.JPanel
import javax.swing.JList
import javax.swing.JScrollPane
import javax.swing.ScrollPaneConstants
import javax.swing.ListSelectionModel
import javax.swing.event.ListSelectionListener
import javax.swing.event.ListSelectionEvent
import pef.par.KContact
import kick.KSubmiter
import sim.KAccountQueryBuilder

class KContactsPanel () : JPanel(), ListSelectionListener
{
   internal val list = JList<KContact>()
   internal val submitter = KSubmiter()

   init
   {
      list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION )
      list.addListSelectionListener( this )

      setLayout(GridLayout( 1, 1 ))
      add(JScrollPane( list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER ))
   }

   override fun valueChanged (e : ListSelectionEvent)
   {
      if (!e.getValueIsAdjusting() && list.selectedValue is KContact)
      {
         val address = (list.selectedValue as KContact).address
         val aspect = GUI.toolbar.selectedAspect

         val queryBuilder = KAccountQueryBuilder( address.member, address.council, aspect )
         submitter.submit( queryBuilder.build(), GUI.panels.info )
      }
   }
}
