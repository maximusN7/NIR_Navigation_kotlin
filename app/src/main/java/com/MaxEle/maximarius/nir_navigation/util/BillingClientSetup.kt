package com.MaxEle.maximarius.nir_navigation.util

import android.content.Context
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.PurchasesUpdatedListener
import com.MaxEle.maximarius.nir_navigation.util.BillingClientSetup

object BillingClientSetup {
    private val instance: BillingClient? = null
    @JvmStatic
    fun getInstance(context: Context, listener: PurchasesUpdatedListener): BillingClient {
        return instance ?: setupBillingClient(context, listener)
    }

    private fun setupBillingClient(
        context: Context,
        listener: PurchasesUpdatedListener
    ): BillingClient {
        return BillingClient.newBuilder(context)
            .enablePendingPurchases()
            .setListener(listener)
            .build()
    }
}