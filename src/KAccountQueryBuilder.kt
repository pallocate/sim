package sim

import pef.now
import pef.IrohaSignatory
import iroha.protocol.Queries.Query.Payload
import iroha.protocol.Queries.*
import kick.createSignature
import pef.utils.Credmin

/** Builds account related queries. */
class KAccountQueryBuilder (
   private val accountName : String,
   private val domainName : String,
   private val aspect : Aspect
)
{
   fun build () : Query
   {
      val payload = payloadBuilder().build()
      val signature = signature( payload.toByteArray() )

      return Query.newBuilder()
         .setPayload( payload )
         .setSignature( signature )
         .build()
   }

   private fun signature (bytes : ByteArray) = createSignature( bytes, Credmin.irohaSignatory() )

   private fun payloadBuilder () : Payload.Builder
   {
      val builder = Payload.newBuilder().setMeta( meta() )

      with (builder) { when (aspect) {
         Aspect.ACCOUNT -> setGetAccount( getAccount() )
         Aspect.ACCOUNT_TX -> setGetAccountTransactions( getAccountTransactions() )
         Aspect.ASSETS -> setGetAccountAssets( getAccountAssets() )
         Aspect.ASSET_TX -> setGetAccountAssetTransactions( getAccountAssetTransactions() )
         Aspect.DETAIL -> setGetAccountDetail( getAccountDetail() )
         Aspect.SIGNATORIES -> setGetSignatories( getSignatories() )
      }}

      return builder
   }

   private fun meta () = QueryPayloadMeta.newBuilder()
      .setCreatorAccountId( "credmin@system" )
      .setCreatedTime( now() )
      .setQueryCounter( queryCounter++ )

   private fun accountId () = "$accountName@$domainName"

   private fun getAccount () = GetAccount.newBuilder().setAccountId( accountId() )
   private fun getAccountTransactions () = GetAccountTransactions.newBuilder().setAccountId( accountId() )
   private fun getAccountAssets () = GetAccountAssets.newBuilder().setAccountId( accountId() )
   private fun getAccountAssetTransactions () = GetAccountAssetTransactions.newBuilder()
      .setAccountId( accountId() )
      .setAssetId( "credit#$domainName" )
      .setPaginationMeta(
         TxPaginationMeta.newBuilder().setPageSize( 1 )
      )
   private fun getAccountDetail () = GetAccountDetail.newBuilder().setAccountId( accountId() )
   private fun getSignatories () = GetSignatories.newBuilder().setAccountId( accountId() )
}
