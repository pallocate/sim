package sim.simulations.formosa.administration

import sim.Aspect
import sim.KAccountQueryBuilder

val READ_TRANSACTIONS = KAccountQueryBuilder( "patricia", "artysan", Aspect.ASSET_TX ).build()
