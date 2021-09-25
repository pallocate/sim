package sim.simulations.formosa.administration

import pef.now
import pef.utils.Patricia
import pef.utils.Artysan
import pef.utils.David
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
                     publicKey = David.irohaSignatory().publicKey()
                  }
               }
               command {
                  addSignatory {
                     accountId = "patricia@artysan"
                     publicKey = Artysan.irohaSignatory().publicKey()
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
      sign( Patricia.irohaSignatory() )
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
                     publicKey = Artysan.irohaSignatory().publicKey()
                  }
               }
            }
         }
      }
      sign( David.irohaSignatory() )
      sign( Artysan.irohaSignatory() )
   }
)
