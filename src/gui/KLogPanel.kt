package sim.gui

import java.awt.Color
import java.awt.Insets
import java.awt.GridLayout
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.text.DefaultCaret
import javax.swing.JScrollPane
import pen.LogManager
import pen.LogAgent
import pen.LogLevel

class KLogPanel : JPanel(), LogAgent
{
   private val textArea = JTextArea()
   private var lastMessage = ""
   private var duplicationCounter = 1

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

      LogManager.logAgent = this
   }

   override fun logMessage (message : String, severity : LogLevel)
   {
      if (message == lastMessage)
      {
         duplicationCounter++

         if (duplicationCounter == 2)
            textArea.append( " (2) " )
         else
            if (duplicationCounter <= 99)
            {
               val docLength = textArea.getDocument().getLength()

               var padding = ""
               if (duplicationCounter < 10)
                  padding = " "

               textArea.replaceRange( "${duplicationCounter})${padding}", docLength - 3, docLength )
            }
      }
      else
      {
         var preMsg = "\n"
         if (lastMessage == "")
            preMsg = ""

         textArea.append( preMsg + message )

         lastMessage = message
         duplicationCounter = 1
      }
   }

   fun clear () = textArea.setText( "" )
}
