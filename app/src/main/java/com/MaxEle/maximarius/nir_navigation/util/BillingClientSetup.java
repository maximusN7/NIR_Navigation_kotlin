package com.MaxEle.maximarius.nir_navigation.util;

import android.content.Context;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.PurchasesUpdatedListener;

public class BillingClientSetup {
    private static BillingClient instance;

    public static BillingClient getInstance(Context context, PurchasesUpdatedListener listener) {
        return instance == null ? setupBillingClient(context,listener):instance;
    }

    private static BillingClient setupBillingClient(Context context, PurchasesUpdatedListener listener) {
        return BillingClient.newBuilder(context)
                .enablePendingPurchases()
                .setListener(listener)
                .build();
    }
}
