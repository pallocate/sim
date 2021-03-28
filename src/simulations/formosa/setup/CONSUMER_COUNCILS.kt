package sim.simulations.formosa.setup

import pen.now
import pen.toHex
import pen.tests.Admin
import pen.tests.Credmin
import pen.tests.Contacts
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.appendRole
import kick.*

/** In this minimalistic example there are only two consumer councils, each with only one member. */
val CONSUMER_COUNCILS = TxPair(

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
                     accountId = "artysan@commons"
                     roleName = "council"
                  }
               }
               command {
                  appendRole {
                     accountId = "crowbeach@commons"
                     roleName = "council"
                  }
               }
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
               /* Councils are creditors, in the meaning that they can issue credits.
                * They are not allowed to issue more credits then what the common plan states.
                * Enforcement of this is not performed by the block chain, it must be done elsewise. */
               command {
                  appendRole {
                     accountId = "artysan@commons"
                     roleName = "creditor"
                  }
               }
               command {
                  appendRole {
                     accountId = "crowbeach@commons"
                     roleName = "creditor"
                  }
               }
            }
         }
      }

      sign( Credmin.irohaSigner() )
   }
)
