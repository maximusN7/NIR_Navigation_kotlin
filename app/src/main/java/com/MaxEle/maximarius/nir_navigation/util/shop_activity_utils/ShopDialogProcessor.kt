package com.MaxEle.maximarius.nir_navigation.util.shop_activity_utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.adapter.MyProductAdapter
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.android.billingclient.api.*

class ShopDialogProcessor(
    val context: Context,
    val activity: Activity,
    private val appComAct: AppCompatActivity,
    private val billingFunctions: BillingFunctions
) {

    private lateinit var shopList: Dialog


    private var loadProduct: Button? = null
    private var recyclerView: RecyclerView? = null
    private var isShopListReady = false

    fun loadShopListDialog() {
        shopList = Dialog(context)
        shopList.setTitle(context.resources.getString(R.string.shoplist))
        shopList.setContentView(R.layout.dialog_shoplist)


        billingFunctions.setupBillingClient()
        init()
    }

    private fun init() {
        val mDataFiles = SharedPreferencesProcessor(context)
        val isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)

        loadProduct = this.activity.findViewById(R.id.buttonShopList)
        recyclerView = shopList.findViewById(R.id.recycler_product)
        recyclerView?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))

        //Event
        loadProduct?.setOnClickListener {
            if (!isShopListReady) {
                if (billingFunctions.billingClient!!.isReady) {
                    val viewmain = shopList.findViewById<LinearLayout>(R.id.viewMain)
                    if (isThemeLight) {
                        viewmain.setBackgroundColor(context.getColor(R.color.backgroundview))
                        recyclerView?.setBackgroundColor(context.getColor(R.color.backgroundview))
                    } else {
                        viewmain.setBackgroundColor(context.getColor(R.color.background1))
                        recyclerView?.setBackgroundColor(context.getColor(R.color.background1))
                    }
                    isShopListReady = true
                    val params = SkuDetailsParams.newBuilder()
                        .setSkusList(
                            listOf(
                                "prem_pass",
                                "perm_max_lvl",
                                "donation",
                                "na_pecheni"
                            )
                        )
                        .setType(BillingClient.SkuType.INAPP)
                        .build()
                    billingFunctions.billingClient!!.querySkuDetailsAsync(params) { billingResult: BillingResult, list: List<SkuDetails>? ->
                        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) loadProductToRecyclerView(
                            list
                        ) else Toast.makeText(
                            context,
                            "Error code: " + billingResult.responseCode,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            shopList.show()
            val progressBar = shopList.findViewById<ProgressBar>(R.id.progressBar)
            progressBar.visibility = View.GONE
        }
    }

    private fun loadProductToRecyclerView(list: List<SkuDetails>?) {
        val adapter = MyProductAdapter(appComAct, list, billingFunctions.billingClient)
        recyclerView!!.adapter = adapter
    }

    fun closeDialog() {
        shopList.dismiss()
    }
}