package sim.simulations.formosa.setup

import pen.now
import pen.toHex
import pen.tests.Admin
import pen.tests.Credmin
import pen.tests.Contacts
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
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

               /* Consumer councils */
               command {
                  appendRole {
                     accountId = "artysan@artysan"
                     roleName = "council"
                  }
               }
               command {
                  appendRole {
                     accountId = "crowbeach@crowbeach"
                     roleName = "council"
                  }
               }

               /* Persons */
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
               
               /* Clothes shop */
               command {
                  appendRole {
                     accountId = "clothesshop@store"
                     roleName = "council"
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
               command {
                  appendRole {
                     accountId = "clothesshop@store"
                     roleName = "debitor"
                  }
               }

               /* Creating the credits. */
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
