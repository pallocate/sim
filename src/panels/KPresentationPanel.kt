package sim.panels

import java.awt.Insets
import java.awt.GridLayout
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.JScrollPane

class KPresentationPanel () : JPanel()
{
   val textArea = JTextArea()

   init
   {
      textArea.setMargin(Insets( 5, 10, 0, 0 ))
      textArea.setLineWrap( true )
      setLayout(GridLayout( 1, 1 ))
      add(JScrollPane( textArea ))
   }
}
