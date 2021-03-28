package sim.simulations.formosa

import pen.now
import pen.toHex
import pen.tests.Admin
import pen.tests.Credmin
import pen.tests.HardwareStore
import kick.*
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.createAccount
import iroha.protocol.appendRole

val TX_1 = Transaction {
   payload {
      reducedPayload {
         creatorAccountId = "admin@system"
         createdTime = now()
         quorum = 1

         commands {
            command {
               createAccount {
                  accountName = "library"
                  domainId = "commons"
                  publicKey = HardwareStore.contact.address.publicKey.toHex()
               }
            }
         }
      }
   }

   sign( Admin.irohaSigner() )
}

val TX_2 = Transaction {
   payload {
      reducedPayload {
         creatorAccountId = "credmin@system"
         createdTime = now()
         quorum = 1

         commands {
            command {
               appendRole {
                  accountId = "library@commons"
                  roleName = "creditor"
               }
            }
         }
      }
   }

   sign( Credmin.irohaSigner() )
}
