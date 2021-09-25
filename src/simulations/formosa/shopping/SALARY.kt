package sim.simulations.formosa.shopping

import pef.now
import pef.utils.Factory
import pef.utils.Hospital
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.addAssetQuantity
import iroha.protocol.transferAsset
import iroha.protocol.subtractAssetQuantity
import kick.*

val SALARY = Transaction {

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
   
   sign( Factory.irohaSignatory() )
}
