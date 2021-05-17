package sim.simulations.formosa.shopping

import pen.now
import pen.tests.ClothesShop
import kick.*
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.subtractAssetQuantity
import iroha.protocol.setAccountDetail

val RECEIPT = Transaction {
   payload {
      reducedPayload {
         creatorAccountId = "clothesshop@store"
         createdTime = now()
         quorum = 1

         commands {
            command {
               subtractAssetQuantity {
                  assetId = "credit#artysan"
                  amount = "3000"
               }
            }
            command {
               setAccountDetail {
                  accountId = "patricia@artysan"
                  key = "clothes"
                  value = "{fashion_synthetic_kg: 1.25, shoes_pair: 1}"
               }
            }
         }
      }
   }
   sign( ClothesShop.irohaSigner() )
}
