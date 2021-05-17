package sim.simulations.formosa.shopping

import pen.now
import pen.tests.Patricia
import kick.*
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.transferAsset
import iroha.protocol.grantPermission
import iroha.protocol.Primitive.GrantablePermission

val PAYING = Transaction {
   payload {
      reducedPayload {
         creatorAccountId = "patricia@artysan"
         createdTime = now()
         quorum = 1

         commands {
            command {
               transferAsset {
                  srcAccountId = "patricia@artysan"
                  destAccountId = "clothesshop@store"
                  assetId = "credit#artysan"
                  description = "53101504,53110602" // Trousers, shoes
                  amount = "3000"
               }
            }
            command {
               grantPermission {
                  accountId = "clothesshop@store"
                  permission = GrantablePermission.can_set_my_account_detail
               }
            }
         }
      }
   }
   sign( Patricia.irohaSigner() )
}