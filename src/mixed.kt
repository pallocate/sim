package sim

/** What aspect of an account to query. */
enum class Aspect
{
   /** Query account information. */
   ACCOUNT,
   /** Query a accounts transactions. */
   ACCOUNT_TX,
   /** Query a accounts assets. */
   ASSETS,
   /** Query a accounts asset transactions. */
   ASSET_TX,
   /** Query a accounts account detail. */
   DETAIL,
   /** Query a accounts account signatories. */
   SIGNATORIES
}

internal var queryCounter = (Math.random()*1000000).toLong()
