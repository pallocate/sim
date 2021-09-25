package sim.simulations.formosa.setup

import pef.now
import pef.toHex
import pef.utils.Admin
import pef.utils.Credmin
import pef.utils.Contacts
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.createAccount
import iroha.protocol.createDomain
import kick.*

/** In this minimalistic example there are only two consumer councils, each with only one member. */
val CREATE_ACCOUNTS = Transaction {
   payload {
      reducedPayload {
         creatorAccountId = "admin@system"
         createdTime = now()
         quorum = 1

         commands {

            /* Artysan consumer council */
            command {
               createDomain {
                  domainId = "artysan"
                  defaultRole = "default"
               }
            }
            command {
               createAccount {
                  accountName = "artysan"
                  domainId = "artysan"
                  publicKey = Contacts.artysan.address.publicKey.toHex()
               }
            }
            command {
               createAccount {
                  accountName = "patricia"
                  domainId = "artysan"
                  publicKey = Contacts.patricia.address.publicKey.toHex()
               }
            }

            /* Crow beach consumer council */
            command {
               createDomain {
                  domainId = "crowbeach"
                  defaultRole = "default"
               }
            }
            command {
               createAccount {
                  accountName = "crowbeach"
                  domainId = "crowbeach"
                  publicKey = Contacts.crowbeach.address.publicKey.toHex()
               }
            }
            command {
               createAccount {
                  accountName = "david"
                  domainId = "crowbeach"
                  publicKey = Contacts.david.address.publicKey.toHex()
               }
            }

            /* Workers councils */
            command {
               createAccount {
                  accountName = "factory"
                  domainId = "commons"
                  publicKey = Contacts.factory.address.publicKey.toHex()
               }
            }
            command {
               createAccount {
                  accountName = "farmlands"
                  domainId = "commons"
                  publicKey = Contacts.farmlands.address.publicKey.toHex()
               }
            }
            command {
               createAccount {
                  accountName = "hospital"
                  domainId = "commons"
                  publicKey = Contacts.hospital.address.publicKey.toHex()
               }
            }
            command {
               createAccount {
                  accountName = "university"
                  domainId = "commons"
                  publicKey = Contacts.university.address.publicKey.toHex()
               }
            }
            
            /* A clothing store */
            command {
               createAccount {
                  accountName = "clothesshop"
                  domainId = "store"
                  publicKey = Contacts.clothesshop.address.publicKey.toHex()
               }
            }
         }
      }
   }

   sign( Admin.irohaSignatory() )
}
