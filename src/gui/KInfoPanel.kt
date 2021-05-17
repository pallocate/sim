package sim.gui

import java.awt.Font
import java.awt.Color
import java.awt.Insets
import java.awt.GridLayout
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JEditorPane
import kick.Textable

class KInfoPanel () : JPanel(), Textable
{
   private val editorPane : JEditorPane

   init
   {
      setLayout(GridLayout( 1, 1 ))

      editorPane = JEditorPane().apply {
         setMargin(Insets( 2, 5, 0, 0 ))
         setContentType( "text/html" )
      }.also {
         add(JScrollPane( it ))
      }
   }

   override fun text (string : String) =
      editorPane.setText( string )
}
// putClientProperty( JEditorPane.HONOR_DISPLAY_PROPERTIES, true )
