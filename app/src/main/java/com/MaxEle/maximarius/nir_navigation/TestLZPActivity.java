package com.MaxEle.maximarius.nir_navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class TestLZPActivity extends AppCompatActivity {

    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";
    boolean Theme_Light;
    SharedPreferences mDataFiles;

    Button NextBut;
    TextView RezView;
    byte Answ;
    byte Nom;

    byte corr;
    byte wron;
    private Chronometer mChronometer;
    private boolean running;
    private long elapsedMillis;
    short obshVR;
    boolean flag;

    Dialog Instract;

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

        setContentView(R.layout.activity_test_lzp);

        AdView mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        }else{
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        Nom=1;
        corr=0;
        wron=0;
        NextBut=findViewById(R.id.button2);
        RezView=findViewById(R.id.textViewRez);

        Instract = new Dialog(TestLZPActivity.this);
        Instract.setTitle(getResources().getString(R.string.instructions));
        Instract.setContentView(R.layout.dialog_view);

        LinearLayout viewansw = findViewById(R.id.viewcond);
        LinearLayout viewmain = Instract.findViewById(R.id.viewMain);
        ScrollView scrolldialog = Instract.findViewById(R.id.scrolldia);
        if (Theme_Light){
            viewansw.setBackgroundColor(getColor(R.color.backgroundview));
            viewmain.setBackgroundColor(getColor(R.color.backgroundview));
            scrolldialog.setBackgroundColor(getColor(R.color.backgroundview));
        }else {
            viewansw.setBackgroundColor(getColor(R.color.backgroundview1));
            viewmain.setBackgroundColor(getColor(R.color.background1));
            scrolldialog.setBackgroundColor(getColor(R.color.background1));
        }

        TextView destext1 = Instract.findViewById(R.id.textViewDescr1);
        destext1.setText(R.string.descrtask19_1);
        TextView destext2 = Instract.findViewById(R.id.textViewDescr2);
        destext2.setText(R.string.descrtask19_2);
        TextView destext3 = Instract.findViewById(R.id.textViewDescr3);
        destext3.setText(R.string.descrtask19_3);
        ImageView desIm1 = Instract.findViewById(R.id.imageViewIm1);
        ImageView desIm2 = Instract.findViewById(R.id.imageViewIm2);
        ImageView desIm3 = Instract.findViewById(R.id.imageViewIm3);
        desIm1.setImageResource(R.drawable.imgdiscr191);
        desIm2.setImageResource(R.drawable.imgdiscr192);
        desIm3.setImageResource(R.drawable.imgdiscr193);


        mChronometer = findViewById(R.id.chronometer);

        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности
        Button ButBack = findViewById(R.id.buttonBack);
        ButBack.setOnClickListener(view -> {
            TestLZPActivity.this.finish();
            Intent intent = new Intent(TestLZPActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
        });
        Button ButSettings = findViewById(R.id.buttonInstr);
        ButSettings.setOnClickListener(view -> Instract.show());
        //-------------------------------------------------------------------------
    }

    @Override
    public void onBackPressed() {
        TestLZPActivity.this.finish();
        Intent intent = new Intent(TestLZPActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }

    public void onClickStart_Next(View view) {
        flag=true;
        String s1;
        TextView textchisl;
        if (Nom==21){
            flag=false;
            textchisl = findViewById(R.id.textViewEdIzm1);
            textchisl.setText("");
            textchisl = findViewById(R.id.textViewEdIzm2);
            textchisl.setText("");
            textchisl = findViewById(R.id.textViewEdIzm3);
            textchisl.setText("");
            mChronometer.setBase(SystemClock.elapsedRealtime());
            NextBut.setText(getResources().getString(R.string.testLZPstart));
            byte b;
            b=(byte)(corr*5);
            String massage=getResources().getString(R.string.testLZP_totalcorr)+ b+getResources().getString(R.string.testLZP_perccorr)+ obshVR +getResources().getString(R.string.testLZP_sec);
            RezView.setText(massage);
            Nom=1;
            obshVR=0;
        }else {
            String s;
            s = getResources().getString(R.string.testLZP_zad) + Nom + getResources().getString(R.string.testLZP_of20);
            RezView.setText(s);

            short ZMPY;
            short KYR;
            short pom1;
            short MK = (short) (Math.random() * 360);
            textchisl = findViewById(R.id.textViewEdIzm2);
            s1 = ""+MK;
            textchisl.setText(s1);
            byte r1 = (byte)(1 + (int) (Math.random() * 3));
            short razn = (short)((-30) + (int) (Math.random() * 61));

            if (r1 == 1) {
                KYR = (short)(330 + (int) (Math.random() * 30));
                textchisl = findViewById(R.id.textViewEdIzm3);
                s1 = ""+KYR;
                textchisl.setText(s1);
                pom1 = (short)(MK + KYR);
                pom1 = makeYgol0_360(pom1);
                ZMPY = (short)(pom1 + razn);

                if (pom1 < ZMPY) {
                    Answ = 2;
                }
                if (pom1 > ZMPY) {
                    Answ = 1;
                }
                if (pom1 == ZMPY) {
                    Answ = 3;
                }
                ZMPY = makeYgol0_360(ZMPY);
                textchisl = findViewById(R.id.textViewEdIzm1);
                s1 = ""+ZMPY;
                textchisl.setText(s1);
            }
            if (r1 == 2) {
                KYR = (short) (Math.random() * 31);
                textchisl = findViewById(R.id.textViewEdIzm3);
                s1 = ""+KYR;
                textchisl.setText(s1);
                pom1 = (short)(MK + KYR);
                pom1 = makeYgol0_360(pom1);
                ZMPY = (short)(pom1 + razn);

                if (pom1 < ZMPY) {
                    Answ = 2;
                }
                if (pom1 > ZMPY) {
                    Answ = 1;
                }
                if (pom1 == ZMPY) {
                    Answ = 3;
                }
                ZMPY = makeYgol0_360(ZMPY);
                textchisl = findViewById(R.id.textViewEdIzm1);
                s1 = ""+ZMPY;
                textchisl.setText(s1);
            }
            if (r1 == 3) {
                KYR = (short)(170 + (int) (Math.random() * 21));
                textchisl = findViewById(R.id.textViewEdIzm3);
                s1 = ""+KYR;
                textchisl.setText(s1);
                pom1 = (short)(MK + KYR-180);
                pom1 = makeYgol0_360(pom1);
                ZMPY = (short)(pom1 + razn);

                if (pom1 < ZMPY) {
                    Answ = 1;
                }
                if (pom1 > ZMPY) {
                    Answ = 2;
                }
                if (pom1 == ZMPY) {
                    Answ = 3;
                }
                ZMPY = makeYgol0_360(ZMPY);
                textchisl = findViewById(R.id.textViewEdIzm1);
                s1 = ""+ZMPY;
                textchisl.setText(s1);
            }
            NextBut.setVisibility(View.INVISIBLE);
            if (!running) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.start();
                running = true;
            }
        }
    }

    public void onClickLeft(View view) {
        if (flag) {
            short a;
            String message;
            elapsedMillis = SystemClock.elapsedRealtime()- mChronometer.getBase();
            a=(short)(elapsedMillis/1000);
            obshVR=(short)(obshVR+a);
            if (running) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
                elapsedMillis=0;
                mChronometer.stop();
                running = false;
            }
            NextBut.setText(getResources().getString(R.string.testLZPnext));
            NextBut.setVisibility(View.VISIBLE);
            if (Answ == 1) {
                message=getResources().getString(R.string.testLZP_corr)+getResources().getString(R.string.testLZP_answL)+ a + getResources().getString(R.string.testLZP_sec);
                RezView.setText(message);
                corr++;
            } else {
                message=getResources().getString(R.string.testLZP_wron)+getResources().getString(R.string.testLZP_answL)+ a + getResources().getString(R.string.testLZP_sec);
                RezView.setText(message);
                wron++;
            }
            Nom++;
            flag=false;
        }
    }
    public void onClickRight(View view) {
        if (flag) {
            short a;
            String message;
            elapsedMillis = SystemClock.elapsedRealtime()- mChronometer.getBase();
            a=(short)(elapsedMillis/1000);
            obshVR=(short)(obshVR+a);
            if (running) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
                elapsedMillis=0;
                mChronometer.stop();
                running = false;
            }
            NextBut.setText(getResources().getString(R.string.testLZPnext));
            NextBut.setVisibility(View.VISIBLE);
            if (Answ == 2) {
                message=getResources().getString(R.string.testLZP_corr)+getResources().getString(R.string.testLZP_answR)+ a + getResources().getString(R.string.testLZP_sec);
                RezView.setText(message);
                corr++;
            } else {
                message=getResources().getString(R.string.testLZP_wron)+getResources().getString(R.string.testLZP_answR)+ a + getResources().getString(R.string.testLZP_sec);
                RezView.setText(message);
                wron++;
            }
            Nom++;
            flag=false;
        }
    }
    public void onClickOn(View view) {
        if (flag) {
            short a;
            String message;
            elapsedMillis = SystemClock.elapsedRealtime()- mChronometer.getBase();
            a=(short)(elapsedMillis/1000);
            obshVR=(short)(obshVR+a);
            if (running) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
                elapsedMillis=0;
                mChronometer.stop();
                running = false;
            }
            NextBut.setText(getResources().getString(R.string.testLZPnext));
            NextBut.setVisibility(View.VISIBLE);
            if (Answ == 3) {
                message=getResources().getString(R.string.testLZP_corr)+getResources().getString(R.string.testLZP_answO)+ a + getResources().getString(R.string.testLZP_sec);
                RezView.setText(message);
                corr++;
            } else {
                message=getResources().getString(R.string.testLZP_wron)+getResources().getString(R.string.testLZP_answO)+ a + getResources().getString(R.string.testLZP_sec);
                RezView.setText(message);
                wron++;
            }
            Nom++;
            flag=false;
        }
    }

    public short makeYgol0_360(short x){
        if (x>=360){
            x=(short)(x-360);
        }
        if (x<0){
            x=(short)(x+360);
        }
        return x;
    }

    public void onClickBackDial(View view) {
        Instract.dismiss();
    }
}
