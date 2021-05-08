package sim.simulations.formosa.administration

import pen.now
import pen.tests.Store
import pen.tests.Patricia
import iroha.protocol.Primitive.GrantablePermission
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.grantPermission
import iroha.protocol.setAccountDetail
import kick.*

val RECEIPT = TxPair(
   first =
   Transaction {
      payload {
         reducedPayload {
            creatorAccountId = "patricia@artysan"
            createdTime = now()
            quorum = 1

            commands {
               command {
                  grantPermission {
                     accountId = "store@supplier"
                     permission = GrantablePermission.can_set_my_account_detail 
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
               command {
                  setAccountDetail {
                     accountId = "patricia@artysan"
                     key = "clothes"
                     value = "1.25kg"
                  }
               }
               command {
                  setAccountDetail {
                     accountId = "patricia@artysan"
                     key = "shoes"
                     value = "2pc"
                  }
               }
            }

         }
      }
      sign( Store.irohaSigner() )
   }
)
