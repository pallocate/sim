package sim.simulations.formosa.shopping

import pef.now
import pef.eco.*
import pef.utils.ClothesShop
import kick.*
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.subtractAssetQuantity
import iroha.protocol.setAccountDetail

/** Patricias consumption list. What she has consumed so far in the clotesshop during the year. */
val consumptionList = serializePQ(
   KProductQuantities(KMeta( year = 2021 )).apply {
      plus( 53101500L, 1L )                                                     // A pair of troucers
      plus( 53101604L, 2L )                                                     // Two blouses
   }
)

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
            /* The consumption list is stored in Patricias account detail. */
            command {
               setAccountDetail {
                  accountId = "patricia@artysan"
                  key = "clothesshop@store"
                  value = consumptionList
               }
            }
         }
      }
   }

   sign( ClothesShop.irohaSignatory() )
}
