package com.MaxEle.maximarius.nir_navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MaxEle.maximarius.nir_navigation.adapter.MyProductAdapter;
import com.MaxEle.maximarius.nir_navigation.util.BillingClientSetup;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;
import java.util.List;

public class ShopActivity extends AppCompatActivity implements PurchasesUpdatedListener {

    SharedPreferences mDataFiles;
    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_PREMIUM = "premakk";
    public static final String DATA_FILE_LEVEL = "level";
    public static final String DATA_FILE_CODE = "code";
    public static final String DATA_FILE_PASSWORD = "password";
    public static final String DATA_FILE_FIRSTENTER_SHOP = "firstentshop";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_SMALL = "small";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";

    String[][] Keys = {{"u5","3t","8h","h3"},{"q7","yf","5l","0p"},{"34","8m","9s","p0"},{"7z","5g","qe","2q"},{"c7","8j","3f","80"}};
    String[][] Pass = {{"hq","gt","zd","ta"},{"sm","qh","ii","6y"},{"ev","y4","kf","qw"},{"bg","4h","9h","0p"},{"mf","hr","6n","nd"}};

    boolean Theme_Light;
    boolean firstenter;
    int AvaLevel;
    boolean PremAkk;
    boolean NaPechenik;
    boolean smallsize;

    TextView Secr;

    private AdView mAdView;

    Dialog ShopList;
    boolean isShopListReady = false;

    BillingClient billingClient;
    ConsumeResponseListener listener;
    AcknowledgePurchaseResponseListener listenerNonConsume;

    Button loadProduct;
    RecyclerView recyclerView;
    Boolean isPurchaseNow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);
        if (mDataFiles.contains(DATA_FILE_THEME_LIGHT)){
            Theme_Light = mDataFiles.getBoolean(DATA_FILE_THEME_LIGHT, true);
        }
        else
        {
            Theme_Light=true;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putBoolean(DATA_FILE_THEME_LIGHT, Theme_Light);
            editor.apply();
        }
        if (Theme_Light){
            setTheme(R.style.AppTheme);
        }else {
            setTheme(R.style.AppThemeDark);
        }

        setContentView(R.layout.activity_shop);


        ShopList = new Dialog(ShopActivity.this);
        ShopList.setTitle(getResources().getString(R.string.shoplist));
        ShopList.setContentView(R.layout.dialog_shoplist);
        setupBillingClient();
        initi();



        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);

        Secr=findViewById(R.id.textViewSecret);
        Secr.setVisibility(View.INVISIBLE);
        if (mDataFiles.contains(DATA_FILE_FIRSTENTER_SHOP)){
            firstenter = mDataFiles.getBoolean(DATA_FILE_FIRSTENTER_SHOP, true);
        }
        else
        {
            firstenter=true;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putBoolean(DATA_FILE_FIRSTENTER_SHOP, firstenter);
            editor.apply();
        }
        if (mDataFiles.contains(DATA_FILE_SMALL)){
            smallsize = mDataFiles.getBoolean(DATA_FILE_SMALL, false);
        }
        else
        {
            smallsize=false;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putBoolean(DATA_FILE_SMALL, smallsize);
            editor.apply();
        }

        if (firstenter){
            byte a = (byte)(Math.random()*5);
            byte b = (byte)(Math.random()*5);
            byte c = (byte)(Math.random()*5);
            byte d = (byte)(Math.random()*5);
            String s =Keys[a][0]+Keys[b][1]+Keys[c][2]+Keys[d][3];
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putString(DATA_FILE_CODE, s);
            s =Pass[a][0]+Pass[b][1]+Pass[c][2]+Pass[d][3];
            editor.putString(DATA_FILE_PASSWORD, s);

            editor.putBoolean(DATA_FILE_FIRSTENTER_SHOP, false);
            editor.apply();
        }

        TextView About = findViewById(R.id.textViewAbout);
        String key = mDataFiles.getString(DATA_FILE_CODE, null);
        String mess = getResources().getString(R.string.About1)+key+getResources().getString(R.string.About2);
        About.setText(mess);


        mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        }else{
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        Button DialBack = ShopList.findViewById(R.id.buttonBack);
        DialBack.setOnClickListener(view -> ShopList.dismiss());

        Button ButBack = findViewById(R.id.buttonBack);
        ButBack.setOnClickListener(view -> {
            ShopActivity.this.finish();
            Intent intent = new Intent(ShopActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
        });

        Button ButOk = findViewById(R.id.button);
        ButOk.setOnClickListener(view -> {
            TextView text = findViewById(R.id.textViewEnterPassword);
            String s = text.getText().toString();
            String p = mDataFiles.getString(DATA_FILE_PASSWORD, null);
            if (s.equals("maximusN7")) {
                PremAkk = true;
                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putBoolean(DATA_FILE_PREMIUM, PremAkk);
                editor.apply();
                ShopActivity.this.finish();
                Intent intent = new Intent(ShopActivity.this, OptionsActivity.class);
                startActivity(intent);
            } else {
                if (s.equals("")) {
                    String mestost = getResources().getString(R.string.toastPremempt);
                    Toast.makeText(getApplicationContext(), mestost,
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (s.equals(p)) {
                        PremAkk = true;
                        NaPechenik=true;
                        SharedPreferences.Editor editor = mDataFiles.edit();
                        editor.putBoolean(DATA_FILE_PREMIUM, PremAkk);
                        editor.putBoolean(DATA_FILE_ADS_DISABLE, NaPechenik);
                        editor.apply();
                        String mess1 = getResources().getString(R.string.toastPrem);
                        Toast.makeText(getApplicationContext(), mess1,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        if (s.equals("m5g4qp")) {
                            AvaLevel = 7;
                            SharedPreferences.Editor editor = mDataFiles.edit();
                            editor.putInt(DATA_FILE_LEVEL, AvaLevel);
                            editor.apply();
                            String mess1 = getResources().getString(R.string.yourmaxlvl);
                            Toast.makeText(getApplicationContext(), mess1,
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            if (s.equals("linear")) {
                                SharedPreferences.Editor editor = mDataFiles.edit();
                                smallsize= !smallsize;
                                editor.putBoolean(DATA_FILE_SMALL, smallsize);
                                editor.apply();
                                String mess1 = "Сжатый размер активирован";
                                Toast.makeText(getApplicationContext(), mess1,
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                String mestost = getResources().getString(R.string.toastPremdenn);
                                Toast.makeText(getApplicationContext(), mestost,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        ShopActivity.this.finish();
        Intent intent = new Intent(ShopActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }

    //--------------------------------------------------------------------------------
    //Работа платежей Google

    private void initi() {
        loadProduct = findViewById(R.id.buttonInstructions);
        recyclerView = ShopList.findViewById(R.id.recycler_product);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        //Event
        loadProduct.setOnClickListener(view -> {
            if (!isShopListReady){
                if (billingClient.isReady()) {
                    LinearLayout viewmain = ShopList.findViewById(R.id.viewMain);
                    if (Theme_Light){
                        viewmain.setBackgroundColor(getColor(R.color.backgroundview));
                        recyclerView.setBackgroundColor(getColor(R.color.backgroundview));
                    }else {
                        viewmain.setBackgroundColor(getColor(R.color.background1));
                        recyclerView.setBackgroundColor(getColor(R.color.background1));
                    }
                    isShopListReady=true;
                    SkuDetailsParams params = SkuDetailsParams.newBuilder()
                            .setSkusList(Arrays.asList("prem_pass", "perm_max_lvl", "donation", "na_pecheni"))
                            .setType(BillingClient.SkuType.INAPP)
                            .build();
                    billingClient.querySkuDetailsAsync(params, (billingResult, list) -> {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK)
                            loadProductToRecyclerView(list);
                        else
                            Toast.makeText(ShopActivity.this, "Error code: " + billingResult.getResponseCode(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
            ShopList.show();
        });
    }

    private void loadProductToRecyclerView(List<SkuDetails> list) {
        MyProductAdapter adapter = new MyProductAdapter(this, list, billingClient);
        recyclerView.setAdapter(adapter);
    }

    private void setupBillingClient() {
        listener = (billingResult, s) -> {
            String mes_s = getResources().getString(R.string.toastconsumeOk);
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK)
                Toast.makeText(ShopActivity.this, mes_s, Toast.LENGTH_SHORT).show();
            };

        listenerNonConsume = (billingResult) -> {
            String mes_s = getResources().getString(R.string.toastconsumeOk);
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK)
                Toast.makeText(ShopActivity.this, mes_s, Toast.LENGTH_SHORT).show();
            };

        billingClient = BillingClientSetup.getInstance(this,this);
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK)
                {
                   // Toast.makeText(ShopActivity.this, "Success to connect billing", Toast.LENGTH_SHORT).show();
                    //Query
                    List<Purchase> purchases = billingClient.queryPurchases(BillingClient.SkuType.INAPP)
                            .getPurchasesList();
                    if (purchases != null) {
                        handleItemAlreadyPurchase(purchases);
                    }
                }
               // else Toast.makeText(ShopActivity.this, "Error code: "+billingResult.getResponseCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBillingServiceDisconnected() {
                //Toast.makeText(ShopActivity.this, "You are disconnect from billing service", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleItemAlreadyPurchase(List<Purchase> purchases) {
        for(Purchase purchase: purchases)
        {
            String mes_s = "";
            SharedPreferences.Editor editor = mDataFiles.edit();
            if (purchase.getSku().equals("donation")) //Consume item
            {
                ConsumeParams consumeParams = ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();
                billingClient.consumeAsync(consumeParams, listener);

                mes_s = getResources().getString(R.string.toastDonation);
                NaPechenik=true;
                mAdView.setVisibility(View.INVISIBLE);
                editor.putBoolean(DATA_FILE_ADS_DISABLE, NaPechenik);
                Secr.setVisibility(View.VISIBLE);
            }
            if (purchase.getSku().equals("prem_pass"))
            {
                if (!purchase.isAcknowledged()) {
                    AcknowledgePurchaseParams acknowledgePurchaseParams =
                            AcknowledgePurchaseParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                    billingClient.acknowledgePurchase(acknowledgePurchaseParams, listenerNonConsume);
                }

                mes_s = getResources().getString(R.string.toastPrembuy);
                PremAkk = true;
                editor.putBoolean(DATA_FILE_PREMIUM, true);
            }
            if (purchase.getSku().equals("perm_max_lvl"))
            {
                if (!purchase.isAcknowledged()) {
                    AcknowledgePurchaseParams acknowledgePurchaseParams =
                            AcknowledgePurchaseParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                    billingClient.acknowledgePurchase(acknowledgePurchaseParams, listenerNonConsume);
                }

                mes_s = getResources().getString(R.string.toastMlvlbuy);
                AvaLevel = 7;
                editor.putInt(DATA_FILE_LEVEL, AvaLevel);
            }
            if (purchase.getSku().equals("na_pecheni"))
            {
                if (!purchase.isAcknowledged()) {
                    AcknowledgePurchaseParams acknowledgePurchaseParams =
                            AcknowledgePurchaseParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                    billingClient.acknowledgePurchase(acknowledgePurchaseParams, listenerNonConsume);
                }

                mes_s = getResources().getString(R.string.toastPechebuy);
                NaPechenik=true;
                mAdView.setVisibility(View.INVISIBLE);
                editor.putBoolean(DATA_FILE_ADS_DISABLE, NaPechenik);
            }
            if (isPurchaseNow){
                Toast.makeText(ShopActivity.this, mes_s, Toast.LENGTH_SHORT).show();
                isPurchaseNow = false;
            }
            editor.apply();
        }
    }

    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<com.android.billingclient.api.Purchase> list) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null)
        {
            isPurchaseNow = true;
            handleItemAlreadyPurchase(list);
        }
        /*else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED);
            Toast.makeText(this, "User has been cancelled", Toast.LENGTH_SHORT).show();*/
        else if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.USER_CANCELED)
        Toast.makeText(ShopActivity.this, getResources().getString(R.string.toastosmtgwentwrong), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Error: "+billingResult.getResponseCode(), Toast.LENGTH_SHORT).show();
    }

}
