package sim.simulations.formosa.administration

import pen.now
import pen.tests.Patricia
import pen.tests.Artysan
import pen.tests.David
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.setAccountQuorum
import iroha.protocol.addSignatory
import iroha.protocol.removeSignatory
import kick.*

val QUORUM = TxPair(

   first =
   Transaction {
      payload {
         reducedPayload {
            creatorAccountId = "patricia@artysan"
            createdTime = now()
            quorum = 1

            commands {
               command {
                  addSignatory {
                     accountId = "patricia@artysan"
                     publicKey = David.irohaSigner().publicKey()
                  }
               }
               command {
                  addSignatory {
                     accountId = "patricia@artysan"
                     publicKey = Artysan.irohaSigner().publicKey()
                  }
               }
               command {
                  setAccountQuorum {
                     accountId = "patricia@artysan"
                     quorum = 2
                  }
               }
            }
         }
      }
      sign( Patricia.irohaSigner() )
   },

   second =
   Transaction {
      payload {
         reducedPayload {
            creatorAccountId = "patricia@artysan"
            createdTime = now()
            quorum = 2

            commands {
               command {
                  setAccountQuorum {
                     accountId = "patricia@artysan"
                     quorum = 1
                  }
               }
               command {
                  removeSignatory {
                     accountId = "patricia@artysan"
                     publicKey = Artysan.irohaSigner().publicKey()
                  }
               }
            }
         }
      }
      sign( David.irohaSigner() )
      sign( Artysan.irohaSigner() )
   }
)
