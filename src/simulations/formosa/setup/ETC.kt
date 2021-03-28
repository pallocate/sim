package sim.simulations.formosa.setup

import pen.now
import pen.toHex
import pen.tests.Admin
import pen.tests.Credmin
import pen.tests.Contacts
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.createDomain
import iroha.protocol.createAccount
import iroha.protocol.createAsset
import iroha.protocol.appendRole
import kick.*

val ETC = TxPair(

   first =
   Transaction {
      payload {
         reducedPayload {
            creatorAccountId = "admin@system"
            createdTime = now()
            quorum = 1

            commands {
               command {
                  appendRole {
                     accountId = "patricia@artysan"
                     roleName = "member"
                  }
               }
               command {
                  appendRole {
                     accountId = "david@crowbeach"
                     roleName = "member"
                  }
               }
            }
         }
      }
      sign( Admin.irohaSigner() )
   },

   second =
   Transaction {
      payload {
         reducedPayload {
            creatorAccountId = "credmin@system"
            createdTime = now()
            quorum = 1

            commands {

               /* Hardware store is a debitor in the meaning that it receives and destroys credits. */
               command {
                  appendRole {
                     accountId = "hardware@supplier"
                     roleName = "debitor"
                  }
               }


               command {
                  appendRole {
                     accountId = "patricia@artysan"
                     roleName = "user"
                  }
               }
               command {
                  appendRole {
                     accountId = "david@crowbeach"
                     roleName = "user"
                  }
               }

               /* Creating the credits. */
               command {
                  createAsset {
                     assetName = "credit"
                     domainId = "commons"
                     precision = 4
                  }
               }
               command {
                  createAsset {
                     assetName = "credit"
                     domainId = "artysan"
                     precision = 0
                  }
               }
               command {
                  createAsset {
                     assetName = "credit"
                     domainId = "crowbeach"
                     precision = 0
                  }
               }
            }
         }
      }
      sign( Credmin.irohaSigner() )
   }
)