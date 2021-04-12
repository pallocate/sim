package sim.gui

import java.awt.Color
import java.awt.Insets
import java.awt.GridLayout
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.text.DefaultCaret
import javax.swing.JScrollPane
import kick.Textable

class KTransactionPanel : JPanel(), Textable
{
   private val textArea = JTextArea()

   init
   {
      setLayout(GridLayout( 1, 1 ))

      with (textArea) {
         textArea.setMargin(Insets( 0, 10, 0, 0 ))
         setBackground( Color.BLACK )
         setForeground( Color.GREEN )
         setCaretColor( Color.WHITE )
      }
      val caret = textArea.getCaret() as DefaultCaret
      caret.setUpdatePolicy( DefaultCaret.ALWAYS_UPDATE )

      add(JScrollPane( textArea ))
   }

   override fun text (string : String) = textArea.append( string + "\n" )
   fun clear () = textArea.setText( "" )
}
