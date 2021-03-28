package sim.simulations.formosa.setup

import pen.now
import pen.toHex
import pen.tests.Admin
import pen.tests.Credmin
import pen.tests.Contacts
import iroha.protocol.Transaction
import iroha.protocol.payload
import iroha.protocol.reducedPayload
import iroha.protocol.createAccount
import iroha.protocol.createDomain
import kick.*

//factory@commons, hardware@supplier

/** In this minimalistic example there are only two consumer councils, each with only one member. */
val CREATE_ACCOUNTS = Transaction {
   payload {
      reducedPayload {
         creatorAccountId = "admin@system"
         createdTime = now()
         quorum = 1

         commands {
            /* Consumer councils are a part of the commons. */
            command {
               createAccount {
                  accountName = "artysan"
                  domainId = "commons"
                  publicKey = Contacts.artysan.address.publicKey.toHex()
               }
            }
            command {
               createAccount {
                  accountName = "crowbeach"
                  domainId = "commons"
                  publicKey = Contacts.crowbeach.address.publicKey.toHex()
               }
            }
            /* But they also have their own domain. */
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
            /* All persons in the economy is a member of exactly one consumption council. */
            command {
               createAccount {
                  accountName = "patricia"
                  domainId = "artysan"
                  publicKey = Contacts.patricia.address.publicKey.toHex()
               }
            }
            command {
               createAccount {
                  accountName = "david"
                  domainId = "crowbeach"
                  publicKey = Contacts.david.address.publicKey.toHex()
               }
            }

            /* Other participants */
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
            command {
               createAccount {
                  accountName = "hardware"
                  domainId = "supplier"
                  publicKey = Contacts.hardwarestore.address.publicKey.toHex()
               }
            }
         }
      }
   }

   sign( Admin.irohaSigner() )
}