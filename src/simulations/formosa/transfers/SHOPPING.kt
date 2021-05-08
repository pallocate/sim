package sim.simulations.formosa.transfers

import pen.now
import pen.tests.Patricia
import pen.tests.Store
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.addAssetQuantity
import iroha.protocol.transferAsset
import iroha.protocol.subtractAssetQuantity
import kick.*

val SHOPPING = TxPair(

   first =
   Transaction {
      payload {
         reducedPayload {
            creatorAccountId = "patricia@artysan"
            createdTime = now()
            quorum = 1

            commands {
               command {
                  transferAsset {
                     srcAccountId = "patricia@artysan"
                     destAccountId = "store@supplier"
                     assetId = "credit#artysan"
                     description = "Jeans + sweater"
                     amount = "3000"
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
            creatorAccountId = "store@supplier"
            createdTime = now()
            quorum = 1

            commands {
               /* The clothing store recieves the credits and destroys them, since they have been used. */
               command {
                  subtractAssetQuantity {
                     assetId = "credit#artsan"
                     amount = "3000"
                  }
               }
            }
         }
      }
      sign( Store.irohaSigner() )
   }
)
