package sim.simulations.formosa.administration

import sim.Aspect
import sim.KAccountQuery

val READ_TRANSACTIONS = KAccountQuery( "patricia", "artysan", Aspect.ASSET_TX ).build()
