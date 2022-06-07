package com.MaxEle.maximarius.nir_navigation.adapter

import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.BillingClient
import androidx.recyclerview.widget.RecyclerView
import com.MaxEle.maximarius.nir_navigation.adapter.MyProductAdapter.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.listener.IRecyclerClickListener
import com.android.billingclient.api.BillingFlowParams
import android.widget.Toast
import android.widget.TextView

class MyProductAdapter(
    var appCompatActivity: AppCompatActivity,
    var skuDetailsList: List<SkuDetails>?,
    var billingClient: BillingClient?
) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(appCompatActivity.baseContext)
                .inflate(R.layout.layout_product_display, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var TitleCorrected = skuDetailsList?.get(position)?.title
        TitleCorrected = TitleCorrected?.replace("\\(НЛ-10 задачи по аэронавигации\\)".toRegex(), "")
        TitleCorrected =
            TitleCorrected?.replace("\\(NL-10 Air navigation tasks pilot's helper\\)".toRegex(), "")
        holder.txt_product_name.text = TitleCorrected
        holder.txt_descriprion.text = skuDetailsList?.get(position)?.description
        holder.txt_price.text = skuDetailsList?.get(position)?.price

        //Product click
        holder.setListener { view: View?, position1: Int ->
            //Launch billingflow
            val billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skuDetailsList!!.get(position1))
                .build()
            val reponse = billingClient!!.launchBillingFlow(appCompatActivity, billingFlowParams)
                .responseCode
            when (reponse) {
                BillingClient.BillingResponseCode.BILLING_UNAVAILABLE -> Toast.makeText(
                    appCompatActivity, "BILLING_UNAVAILABLE", Toast.LENGTH_SHORT
                ).show()
                BillingClient.BillingResponseCode.DEVELOPER_ERROR -> Toast.makeText(
                    appCompatActivity,
                    "DEVELOPER_ERROR",
                    Toast.LENGTH_SHORT
                ).show()
                BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED -> Toast.makeText(
                    appCompatActivity, "FEATURE_NOT_SUPPORTED", Toast.LENGTH_SHORT
                ).show()
                BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> Toast.makeText(
                    appCompatActivity,
                    "ITEM_ALREADY_OWNED",
                    Toast.LENGTH_SHORT
                ).show()
                BillingClient.BillingResponseCode.SERVICE_DISCONNECTED -> Toast.makeText(
                    appCompatActivity, "SERVICE_DISCONNECTED", Toast.LENGTH_SHORT
                ).show()
                BillingClient.BillingResponseCode.SERVICE_TIMEOUT -> Toast.makeText(
                    appCompatActivity,
                    "SERVICE_TIMEOUT",
                    Toast.LENGTH_SHORT
                ).show()
                BillingClient.BillingResponseCode.ITEM_UNAVAILABLE -> Toast.makeText(
                    appCompatActivity,
                    "ITEM_UNAVAILABLE",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {}
            }
        }
    }

    override fun getItemCount(): Int {
        return skuDetailsList!!.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var txt_product_name: TextView
        var txt_price: TextView
        var txt_descriprion: TextView
        var listenerClass: IRecyclerClickListener? = null
        fun setListener(listener: IRecyclerClickListener?) {
            listenerClass = listener
        }

        override fun onClick(view: View) {
            listenerClass!!.onClick(view, absoluteAdapterPosition)
        }

        init {
            txt_descriprion = itemView.findViewById<View>(R.id.txt_description) as TextView
            txt_product_name = itemView.findViewById<View>(R.id.txt_product_name) as TextView
            txt_price = itemView.findViewById<View>(R.id.txt_price) as TextView
            itemView.setOnClickListener(this)
        }
    }
}