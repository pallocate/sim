package sim.simulations.formosa.transfers

import pen.now
import pen.tests.Farmlands
import pen.tests.University
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.addAssetQuantity
import iroha.protocol.transferAsset
import iroha.protocol.subtractAssetQuantity
import kick.*

val INVOICE = TxPair(

   first =
   Transaction {
      payload {
         reducedPayload {
            creatorAccountId = "university@commons"
            createdTime = now()
            quorum = 1

            commands {
               command {
                  addAssetQuantity {
                     assetId = "credit#commons"
                     amount = "10000.0000"
                  }
               }
               command {
                  transferAsset {
                     srcAccountId = "university@commons"
                     destAccountId = "farmlands@commons"
                     assetId = "credit#commons"
                     description = "Invoice settlement"
                     amount = "10000.0000"
                  }
               }
            }
         }
      }
      sign( University.irohaSigner() )
   },

   second =
   Transaction {
      payload {
         reducedPayload {
            creatorAccountId = "farmlands@commons"
            createdTime = now()
            quorum = 1

            commands {
               command {
                  subtractAssetQuantity {
                     assetId = "credit#commons"
                     amount = "10000.0000"
                  }
               }
            }
         }
      }
      sign( Farmlands.irohaSigner() )
   }
)
