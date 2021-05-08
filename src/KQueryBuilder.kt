package sim

import pen.now
import pen.IrohaSigner
import iroha.protocol.Queries.Query.Payload
import iroha.protocol.Queries.*
import kick.createSignature
import pen.tests.Credmin

class KQueryBuilder (val subject : String, val domain : String, val selection : Aspect)
{
   companion object
   { private var counter = (Math.random()*1000000).toLong() }

   /** Builds a Iroha protocol query */
   fun build () : Query
   {
      val payload = payloadBuilder().build()
      val signature = signature( payload.toByteArray() )

      return Query.newBuilder()
         .setPayload( payload )
         .setSignature( signature )
         .build()
   }

   private fun signature (bytes : ByteArray) = createSignature( bytes, Credmin.irohaSigner() )

   private fun payloadBuilder () : Payload.Builder
   {
      val builder = Payload.newBuilder().setMeta( meta() )

      with (builder) { when (selection) {
         Aspect.ACCOUNT -> setGetAccount( getAccount() )
         Aspect.ASSETS -> setGetAccountAssets( getAccountAssets() )
         Aspect.DETAIL -> setGetAccountDetail( getAccountDetail() )
         Aspect.SIGNATORIES -> setGetSignatories( getSignatories() )
      }}

      return builder
   }

   private fun meta () = QueryPayloadMeta.newBuilder()
      .setCreatorAccountId( "credmin@system" )
      .setCreatedTime( now() )
      .setQueryCounter( counter++ )

   private fun accountId () = "$subject@$domain"

   private fun getAccount () = GetAccount.newBuilder().setAccountId( accountId() )
   private fun getAccountAssets () = GetAccountAssets.newBuilder().setAccountId( accountId() )
   private fun getAccountDetail () = GetAccountDetail.newBuilder().setAccountId( accountId() )
   private fun getSignatories () = GetSignatories.newBuilder().setAccountId( accountId() )
}
