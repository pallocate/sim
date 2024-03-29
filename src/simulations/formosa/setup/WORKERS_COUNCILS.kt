package sim.simulations.formosa.setup

import pef.now
import pef.toHex
import pef.utils.Admin
import pef.utils.Credmin
import pef.utils.Contacts
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.appendRole
import kick.*

val WORKERS_COUNCILS = TxPair(

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
                     accountId = "factory@commons"
                     roleName = "council"
                  }
               }
               command {
                  appendRole {
                     accountId = "farmlands@commons"
                     roleName = "council"
                  }
               }
               command {
                  appendRole {
                     accountId = "hospital@commons"
                     roleName = "council"
                  }
               }
               command {
                  appendRole {
                     accountId = "university@commons"
                     roleName = "council"
                  }
               }
            }
         }
      }
      
      sign( Admin.irohaSignatory() )
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
                     accountId = "factory@commons"
                     roleName = "creditor"
                  }
               }
               command {
                  appendRole {
                     accountId = "farmlands@commons"
                     roleName = "creditor"
                  }
               }
               command {
                  appendRole {
                     accountId = "hospital@commons"
                     roleName = "creditor"
                  }
               }
               command {
                  appendRole {
                     accountId = "university@commons"
                     roleName = "creditor"
                  }
               }
            }
         }
      }
      
      sign( Credmin.irohaSignatory() )
   }
)
