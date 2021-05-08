package sim.simulations.formosa.transfers

import pen.now
import pen.tests.Factory
import pen.tests.Hospital
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.addAssetQuantity
import iroha.protocol.transferAsset
import iroha.protocol.subtractAssetQuantity
import kick.*

/** The Farmlands council issues some credits, buys some stuff and doles out salary. */
val SALARY = listOf (
   Transaction {
      payload {
         reducedPayload {
            creatorAccountId = "factory@commons"
            createdTime = now()
            quorum = 1

            commands {
               command {
                  addAssetQuantity {
                     assetId = "credit#artysan"
                     amount = "75000"
                  }
               }
               command {
                  transferAsset {
                     srcAccountId = "factory@commons"
                     destAccountId = "patricia@artysan"
                     assetId = "credit#artysan"
                     description = "salary"
                     amount = "75000"
                  }
               }
            }
         }
      }
      sign( Factory.irohaSigner() )
   },

   Transaction {
      payload {
         reducedPayload {
            creatorAccountId = "hospital@commons"
            createdTime = now()
            quorum = 1

            commands {
               command {
                  addAssetQuantity {
                     assetId = "credit#crowbeach"
                     amount = "50000"
                  }
               }
               command {
                  transferAsset {
                     srcAccountId = "hospital@commons"
                     destAccountId = "david@crowbeach"
                     assetId = "credit#crowbeach"
                     description = "salary"
                     amount = "50000"
                  }
               }
            }
         }
      }
      sign( Hospital.irohaSigner() )
   }
)
