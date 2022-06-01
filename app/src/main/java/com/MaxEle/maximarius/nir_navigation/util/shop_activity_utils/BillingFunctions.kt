package com.MaxEle.maximarius.nir_navigation.util.shop_activity_utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.*
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.BillingClientSetup
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.android.billingclient.api.*
import com.google.android.gms.ads.AdView

class BillingFunctions(val context: Context, private val activity: Activity, private val purchaseUpdateListener: PurchasesUpdatedListener) : PurchasesUpdatedListener{
    
    var billingClient: BillingClient? = null
    private var listener: ConsumeResponseListener? = null
    private var listenerNonConsume: AcknowledgePurchaseResponseListener? = null
    private var isPurchaseNow = false
    
    fun setupBillingClient() {
        listener = ConsumeResponseListener { billingResult: BillingResult, _: String? ->
            val message = context.resources.getString(R.string.toastconsumeOk)
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
        listenerNonConsume = AcknowledgePurchaseResponseListener { billingResult: BillingResult ->
            val message = context.resources.getString(R.string.toastconsumeOk)
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
        billingClient = BillingClientSetup.getInstance(context, purchaseUpdateListener)
        billingClient!!.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    // Toast.makeText(ShopActivity.this, "Success to connect billing", Toast.LENGTH_SHORT).show();
                    //Query
                    val purchases = billingClient!!.queryPurchases(BillingClient.SkuType.INAPP)
                        .purchasesList
                    purchases?.let { handleItemAlreadyPurchase(it) }
                }
                // else Toast.makeText(ShopActivity.this, "Error code: "+billingResult.getResponseCode(), Toast.LENGTH_SHORT).show();
            }

            override fun onBillingServiceDisconnected() {
                //Toast.makeText(ShopActivity.this, "You are disconnect from billing service", Toast.LENGTH_SHORT).show();
            }
        })
    }

    fun setupBillingClientForMainActivity() {
        billingClient = BillingClientSetup.getInstance(context, purchaseUpdateListener)
        billingClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    //Query
                    val purchases = billingClient?.queryPurchases(BillingClient.SkuType.INAPP)
                        ?.purchasesList
                    purchases?.let { handleItemAlreadyPurchaseForMainActivity(it) }
                }
            }

            override fun onBillingServiceDisconnected() {}
        })
    }

    fun handleItemAlreadyPurchase(purchases: List<Purchase>) {
        for (purchase in purchases) {
            var message = ""
            val mDataFiles = SharedPreferencesProcessor(context)
            if (purchase.sku == "donation") //Consume item
            {
                val consumeParams = ConsumeParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()
                billingClient!!.consumeAsync(consumeParams, listener!!)
                message = context.resources.getString(R.string.toastDonation)
                val mAdView: AdView = this.activity.findViewById(R.id.banner_ad)
                mAdView.visibility = View.INVISIBLE
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, true)
                val secr = this.activity.findViewById<TextView>(R.id.textViewSecret)
                secr.visibility = View.VISIBLE
            }
            if (purchase.sku == "prem_pass") {
                if (!purchase.isAcknowledged) {
                    val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.purchaseToken)
                        .build()
                    billingClient!!.acknowledgePurchase(
                        acknowledgePurchaseParams,
                        listenerNonConsume!!
                    )
                }
                message = context.resources.getString(R.string.toastPrembuy)
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_PREMIUM, true)
            }
            if (purchase.sku == "perm_max_lvl") {
                if (!purchase.isAcknowledged) {
                    val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.purchaseToken)
                        .build()
                    billingClient!!.acknowledgePurchase(
                        acknowledgePurchaseParams,
                        listenerNonConsume!!
                    )
                }
                message = context.resources.getString(R.string.toastMlvlbuy)
                mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_LEVEL, 7)
            }
            if (purchase.sku == "na_pecheni") {
                if (!purchase.isAcknowledged) {
                    val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.purchaseToken)
                        .build()
                    billingClient!!.acknowledgePurchase(
                        acknowledgePurchaseParams,
                        listenerNonConsume!!
                    )
                }
                message = context.resources.getString(R.string.toastPechebuy)
                val mAdView: AdView = this.activity.findViewById(R.id.banner_ad)
                mAdView.visibility = View.INVISIBLE
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, true)
            }
            if (isPurchaseNow) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                isPurchaseNow = false
            }
        }
    }

    fun handleItemAlreadyPurchaseForMainActivity(purchases: List<Purchase>) {
        val mDataFiles = SharedPreferencesProcessor(context)
        val mAdView = this.activity.findViewById<AdView>(R.id.banner_ad)

        for (purchase in purchases) {
            if (purchase.sku == "donation") //Consume item
            {
                mAdView.visibility = View.INVISIBLE
                mDataFiles.setBoolean(
                    SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE,
                    true
                )
            }
            if (purchase.sku == "prem_pass") {
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_PREMIUM, true)
            }
            if (purchase.sku == "perm_max_lvl") {
                mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_LEVEL, 7)
            }
            if (purchase.sku == "na_pecheni") {
                mAdView.visibility = View.INVISIBLE
                mDataFiles.setBoolean(
                    SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE,
                    true
                )
            }
        }
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, list: List<Purchase>?) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && list != null) {
            isPurchaseNow = true
            handleItemAlreadyPurchase(list)
        } else if (billingResult.responseCode != BillingClient.BillingResponseCode.USER_CANCELED) Toast.makeText(
            context,
            context.resources.getString(R.string.toastosmtgwentwrong),
            Toast.LENGTH_SHORT
        ).show()
        //Toast.makeText(this, "Error: "+billingResult.getResponseCode(), Toast.LENGTH_SHORT).show();
    }
}