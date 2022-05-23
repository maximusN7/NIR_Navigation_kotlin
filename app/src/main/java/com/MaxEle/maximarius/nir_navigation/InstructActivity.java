package com.MaxEle.maximarius.nir_navigation;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class InstructActivity extends AppCompatActivity {

    Spinner spinType;
    String spinText;

    TextView destext1;
    TextView destext2;
    TextView destext3;
    TextView destext4;
    TextView destext5;
    TextView destext6;
    TextView destext7;
    TextView destext8;
    TextView destext9;
    TextView destext10;
    TextView destext11;
    TextView destext12;
    TextView destext13;
    TextView destext14;
    ImageView desIm1;
    ImageView desIm2;
    ImageView desIm3;
    ImageView desIm4;
    ImageView desIm5;
    ImageView desIm6;
    ImageView desIm7;
    ImageView desIm8;
    ImageView desIm9;
    ImageView desIm10;
    ImageView desIm11;
    ImageView desIm12;
    ImageView desIm13;
    ImageView desIm14;


    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_LEVEL = "level";
    public static final String DATA_FILE_FIRSTENTER3 = "firstent3";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";
    int AvaLevel;
    boolean Theme_Light;
    SharedPreferences mDataFiles;

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

        setContentView(R.layout.activity_instruct);

        AdView mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        }else{
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);

        AvaLevel = mDataFiles.getInt(DATA_FILE_LEVEL, 0);

        ScrollView viewansw = findViewById(R.id.viewbasic);
        if (Theme_Light){
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview));
        }else {
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
        }

        MakeINVISIBLEALL();

        spinType = findViewById(R.id.taskslist);
        final String[] typetask = getResources().getStringArray(R.array.taskstypesinstr);

        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                spinText = spinType.getSelectedItem().toString();
                if (spinText.equals(typetask[0])){DescAbbrev();}

                if (spinText.equals(typetask[1])){DescPoiskYglaRazvorota();}
                if (spinText.equals(typetask[2])){DescPoiskVremeniRazvorota();}
                if (spinText.equals(typetask[3])){DescPoiskLYR();}

                if (spinText.equals(typetask[4])){DescPoiskPytevoiSkorosti();}
                if (spinText.equals(typetask[5])){DescPoiskSkorostVetra();}
                if (spinText.equals(typetask[6])){DescPoiskNapravleniaVetra();}

                if (spinText.equals(typetask[7])){DescPoiskKyrsi();}
                if (spinText.equals(typetask[8])){DescPoiskFIPY();}
                if (spinText.equals(typetask[9])){DescPoiskKyrsiPoVetry();}

                if (spinText.equals(typetask[10])){DescPoiskZadRybesha();}
                if (spinText.equals(typetask[11])){DescPoiskZadRybeshaOgr();}
                if (spinText.equals(typetask[12])){DescPoiskZadRybeshaIzmen();}

                if (spinText.equals(typetask[13])){DescPoiskIstenSkorost();}
                if (spinText.equals(typetask[14])){DescPoiskPriborSkorost();}
                if (spinText.equals(typetask[15])){DescPoiskIstenSkorostKYS();}

                if (spinText.equals(typetask[16])){DescPoiskIsprPutLBYmB();}
                if (spinText.equals(typetask[17])){DescPoiskIsprPutLBYbB();}
                if (spinText.equals(typetask[18])){DescPoiskIsprPutLBYbB2();}

                if (spinText.equals(typetask[19])){DescTreningLZP();}
                if (spinText.equals(typetask[20])){DescEnterHold();}
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        InstructActivity.this.finish();
        Intent intent = new Intent(InstructActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }
    public void onClickBack(View view){
        InstructActivity.this.finish();
        Intent intent = new Intent(InstructActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }

    public void MakeINVISIBLEALL(){
        destext1 = findViewById(R.id.textViewDescr1);
        destext1.setText("");
        destext2 = findViewById(R.id.textViewDescr2);
        destext2.setText("");
        destext3 = findViewById(R.id.textViewDescr3);
        destext3.setText("");
        destext4 = findViewById(R.id.textViewDescr4);
        destext4.setText("");
        destext5 = findViewById(R.id.textViewDescr5);
        destext5.setText("");
        destext6 = findViewById(R.id.textViewDescr6);
        destext6.setText("");
        destext7 = findViewById(R.id.textViewDescr7);
        destext7.setText("");
        destext8 = findViewById(R.id.textViewDescr8);
        destext8.setText("");
        destext9 = findViewById(R.id.textViewDescr9);
        destext9.setText("");
        destext10 = findViewById(R.id.textViewDescr10);
        destext10.setText("");
        destext11 = findViewById(R.id.textViewDescr11);
        destext11.setText("");
        destext12 = findViewById(R.id.textViewDescr12);
        destext12.setText("");
        destext13 = findViewById(R.id.textViewDescr13);
        destext13.setText("");
        destext14 = findViewById(R.id.textViewDescr14);
        destext14.setText("");
        destext1.setVisibility(View.INVISIBLE);
        destext2.setVisibility(View.INVISIBLE);
        destext3.setVisibility(View.INVISIBLE);
        destext4.setVisibility(View.INVISIBLE);
        destext5.setVisibility(View.INVISIBLE);
        destext6.setVisibility(View.INVISIBLE);
        destext7.setVisibility(View.INVISIBLE);
        destext8.setVisibility(View.INVISIBLE);
        destext9.setVisibility(View.INVISIBLE);
        destext10.setVisibility(View.INVISIBLE);
        destext11.setVisibility(View.INVISIBLE);
        destext12.setVisibility(View.INVISIBLE);
        destext13.setVisibility(View.INVISIBLE);
        destext14.setVisibility(View.INVISIBLE);
        desIm1 = findViewById(R.id.imageViewIm1);
        desIm2 = findViewById(R.id.imageViewIm2);
        desIm3 = findViewById(R.id.imageViewIm3);
        desIm4 = findViewById(R.id.imageViewIm4);
        desIm5 = findViewById(R.id.imageViewIm5);
        desIm6 = findViewById(R.id.imageViewIm6);
        desIm7 = findViewById(R.id.imageViewIm7);
        desIm8 = findViewById(R.id.imageViewIm8);
        desIm9 = findViewById(R.id.imageViewIm9);
        desIm10 = findViewById(R.id.imageViewIm10);
        desIm11= findViewById(R.id.imageViewIm11);
        desIm12 = findViewById(R.id.imageViewIm12);
        desIm13 = findViewById(R.id.imageViewIm13);
        desIm14 = findViewById(R.id.imageViewIm14);
        desIm1.setImageDrawable(null);
        desIm2.setImageDrawable(null);
        desIm3.setImageDrawable(null);
        desIm4.setImageDrawable(null);
        desIm5.setImageDrawable(null);
        desIm6.setImageDrawable(null);
        desIm7.setImageDrawable(null);
        desIm8.setImageDrawable(null);
        desIm9.setImageDrawable(null);
        desIm10.setImageDrawable(null);
        desIm11.setImageDrawable(null);
        desIm12.setImageDrawable(null);
        desIm13.setImageDrawable(null);
        desIm14.setImageDrawable(null);
        desIm1.setVisibility(View.INVISIBLE);
        desIm2.setVisibility(View.INVISIBLE);
        desIm3.setVisibility(View.INVISIBLE);
        desIm4.setVisibility(View.INVISIBLE);
        desIm5.setVisibility(View.INVISIBLE);
        desIm6.setVisibility(View.INVISIBLE);
        desIm7.setVisibility(View.INVISIBLE);
        desIm8.setVisibility(View.INVISIBLE);
        desIm9.setVisibility(View.INVISIBLE);
        desIm10.setVisibility(View.INVISIBLE);
        desIm11.setVisibility(View.INVISIBLE);
        desIm12.setVisibility(View.INVISIBLE);
        desIm13.setVisibility(View.INVISIBLE);
        desIm14.setVisibility(View.INVISIBLE);
    }

    //---------------------------------------------------------------------
    public void DescAbbrev(){
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);

        destext1.setText(R.string.descrtask0_1);
        destext2.setText(R.string.descrtask0_2);
    }

    public void DescPoiskYglaRazvorota(){
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        desIm1.setVisibility(View.VISIBLE);

        destext1.setText(R.string.descrtask1_1);
        desIm1.setImageResource(R.drawable.imgdiscr1);
    }
    private void DescPoiskVremeniRazvorota() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        desIm2.setVisibility(View.VISIBLE);

        destext1.setText(R.string.descrtask2_1);
        desIm1.setImageResource(R.drawable.imgdiscr1);
        destext2.setText(R.string.descrtask2_2);
        desIm2.setImageResource(R.drawable.imgdiscr2);
    }
    private void DescPoiskLYR() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        desIm2.setVisibility(View.VISIBLE);

        destext1.setText(R.string.descrtask3_1);
        desIm1.setImageResource(R.drawable.imgdiscr1);
        destext2.setText(R.string.descrtask3_2);
        desIm2.setImageResource(R.drawable.imgdiscr3);
    }

    public void DescPoiskPytevoiSkorosti(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask4_1);
            desIm1.setImageResource(R.drawable.imgdiscr41);
            destext2.setText(R.string.descrtask4_2);
            desIm2.setImageResource(R.drawable.imgdiscr42);
            destext3.setText(R.string.descrtask4_3);
            desIm3.setImageResource(R.drawable.imgdiscr43);
            destext4.setText(R.string.descrtask4_4);
            desIm4.setImageResource(R.drawable.imgdiscr44);
    }
    public void DescPoiskSkorostVetra(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask5_1);
            desIm1.setImageResource(R.drawable.imgdiscr51);
            destext2.setText(R.string.descrtask5_2);
            desIm2.setImageResource(R.drawable.imgdiscr52);
            destext3.setText(R.string.descrtask5_3);
            desIm3.setImageResource(R.drawable.imgdiscr53);
    }
    public void DescPoiskNapravleniaVetra(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask6_1);
            desIm1.setImageResource(R.drawable.imgdiscr51);
            destext2.setText(R.string.descrtask6_2);
            desIm2.setImageResource(R.drawable.imgdiscr52);
            destext3.setText(R.string.descrtask6_3);
            desIm3.setImageResource(R.drawable.imgdiscr53);
            destext4.setText(R.string.descrtask6_4);
            desIm4.setImageResource(R.drawable.imgdiscr64);
    }

    public void DescPoiskKyrsi(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask7_1);
            desIm1.setImageResource(R.drawable.imgdiscr71);
            destext2.setText(R.string.descrtask7_2);
    }
    public void DescPoiskFIPY(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask8_1);
            desIm1.setImageResource(R.drawable.imgdiscr71);
            destext2.setText(R.string.descrtask8_2);
            desIm2.setImageResource(R.drawable.imgdiscr82);
            destext3.setText(R.string.descrtask8_3);
            desIm3.setImageResource(R.drawable.imgdiscr83);
            destext4.setText(R.string.descrtask8_4);
            desIm4.setImageResource(R.drawable.imgdiscr84);
            desIm5.setImageResource(R.drawable.imgdiscr82);
    }
    public void DescPoiskKyrsiPoVetry(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            desIm5.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask9_1);
            desIm1.setImageResource(R.drawable.imgdiscr41);
            destext2.setText(R.string.descrtask9_2);
            desIm2.setImageResource(R.drawable.imgdiscr92);
            destext3.setText(R.string.descrtask9_3);
            desIm3.setImageResource(R.drawable.imgdiscr42);
            destext4.setText(R.string.descrtask9_4);
            desIm4.setImageResource(R.drawable.imgdiscr43);
            destext5.setText(R.string.descrtask9_5);
            desIm5.setImageResource(R.drawable.imgdiscr95);
    }

    public void DescPoiskZadRybesha(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            desIm5.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask10_1);
            desIm1.setImageResource(R.drawable.imgdiscr101);
            destext2.setText(R.string.descrtask10_2);
            desIm2.setImageResource(R.drawable.imgdiscr102);
            destext3.setText(R.string.descrtask10_3);
            desIm3.setImageResource(R.drawable.imgdiscr103);
            desIm4.setImageResource(R.drawable.imgdiscr104);
            destext5.setText(R.string.descrtask10_4);
            desIm5.setImageResource(R.drawable.imgdiscr105);
            desIm6.setImageResource(R.drawable.imgdiscr106);
    }
    public void DescPoiskZadRybeshaOgr(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);
            desIm5.setVisibility(View.VISIBLE);
            destext6.setVisibility(View.VISIBLE);
            desIm6.setVisibility(View.VISIBLE);
            destext7.setVisibility(View.VISIBLE);
            desIm7.setVisibility(View.VISIBLE);
            destext8.setVisibility(View.VISIBLE);
            desIm8.setVisibility(View.VISIBLE);
            desIm9.setVisibility(View.VISIBLE);
            destext10.setVisibility(View.VISIBLE);
            desIm10.setVisibility(View.VISIBLE);
            desIm11.setVisibility(View.VISIBLE);
            destext12.setVisibility(View.VISIBLE);
            desIm12.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask11_1);
            desIm1.setImageResource(R.drawable.imgdiscr111);
            destext2.setText(R.string.descrtask11_2);
            desIm2.setImageResource(R.drawable.imgdiscr103);
            desIm3.setImageResource(R.drawable.imgdiscr112);
            destext4.setText(R.string.descrtask11_3);
            desIm4.setImageResource(R.drawable.imgdiscr105);
            desIm5.setImageResource(R.drawable.imgdiscr113);
            destext6.setText(R.string.descrtask11_4);
            desIm6.setImageResource(R.drawable.imgdiscr101);
            destext7.setText(R.string.descrtask11_5);
            desIm7.setImageResource(R.drawable.imgdiscr115);
            destext8.setText(R.string.descrtask11_6);
            desIm8.setImageResource(R.drawable.imgdiscr103);
            desIm9.setImageResource(R.drawable.imgdiscr115);
            destext10.setText(R.string.descrtask11_7);
            desIm10.setImageResource(R.drawable.imgdiscr105);
            desIm11.setImageResource(R.drawable.imgdiscr116);
            destext12.setText(R.string.descrtask11_8);
            desIm12.setImageResource(R.drawable.imgdiscr117);
    }
    public void DescPoiskZadRybeshaIzmen(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            desIm5.setVisibility(View.VISIBLE);
            destext6.setVisibility(View.VISIBLE);
            desIm6.setVisibility(View.VISIBLE);
            destext7.setVisibility(View.VISIBLE);
            desIm7.setVisibility(View.VISIBLE);
            destext8.setVisibility(View.VISIBLE);
            desIm8.setVisibility(View.VISIBLE);
            destext9.setVisibility(View.VISIBLE);
            desIm9.setVisibility(View.VISIBLE);
            destext10.setVisibility(View.VISIBLE);
            desIm10.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask12_1);
            desIm1.setImageResource(R.drawable.imgdiscr121);
            destext2.setText(R.string.descrtask12_2);
            desIm2.setImageResource(R.drawable.imgdiscr122);
            destext3.setText(R.string.descrtask12_3);
            desIm3.setImageResource(R.drawable.imgdiscr123);
            destext4.setText(R.string.descrtask12_4);
            desIm4.setImageResource(R.drawable.imgdiscr124);
            destext5.setText(R.string.descrtask12_5);
            desIm5.setImageResource(R.drawable.imgdiscr125);
            destext6.setText(R.string.descrtask12_6);
            desIm6.setImageResource(R.drawable.imgdiscr126);
            destext7.setText(R.string.descrtask12_7);
            desIm7.setImageResource(R.drawable.imgdiscr127);
            destext8.setText(R.string.descrtask12_8);
            desIm8.setImageResource(R.drawable.imgdiscr128);
            destext9.setText(R.string.descrtask12_9);
            desIm9.setImageResource(R.drawable.imgdiscr129);
            destext10.setText(R.string.descrtask12_10);
            desIm10.setImageResource(R.drawable.imgdiscr1210);
    }

    public void DescPoiskIstenSkorost(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            desIm5.setVisibility(View.VISIBLE);
            destext6.setVisibility(View.VISIBLE);
            desIm6.setVisibility(View.VISIBLE);
            destext7.setVisibility(View.VISIBLE);
            desIm7.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask13_1);
            desIm1.setImageResource(R.drawable.imgdiscr133);
            destext2.setText(R.string.descrtask13_2);
            desIm2.setImageResource(R.drawable.imgdiscr135);
            destext3.setText(R.string.descrtask13_3);
            desIm3.setImageResource(R.drawable.imgdiscr131);
            destext4.setText(R.string.descrtask13_4);
            desIm4.setImageResource(R.drawable.imgdiscr132);
            destext5.setText(R.string.descrtask13_5);
            desIm5.setImageResource(R.drawable.imgdiscr134);
            destext6.setText(R.string.descrtask13_6);
            desIm6.setImageResource(R.drawable.imgdiscr136);
            destext7.setText(R.string.descrtask13_7);
            desIm7.setImageResource(R.drawable.imgdiscr131);
    }
    public void DescPoiskPriborSkorost(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            desIm5.setVisibility(View.VISIBLE);
            destext6.setVisibility(View.VISIBLE);
            desIm6.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask14_1);
            desIm1.setImageResource(R.drawable.imgdiscr141);
            destext2.setText(R.string.descrtask14_2);
            desIm2.setImageResource(R.drawable.imgdiscr134);
            destext3.setText(R.string.descrtask14_3);
            desIm3.setImageResource(R.drawable.imgdiscr136);
            destext4.setText(R.string.descrtask14_4);
            desIm4.setImageResource(R.drawable.imgdiscr141);
            destext5.setText(R.string.descrtask14_5);
            desIm5.setImageResource(R.drawable.imgdiscr133);
            destext6.setText(R.string.descrtask14_6);
            desIm6.setImageResource(R.drawable.imgdiscr142);
    }
    public void DescPoiskIstenSkorostKYS(){
            MakeINVISIBLEALL();
            destext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            desIm5.setVisibility(View.VISIBLE);

            destext1.setText(R.string.descrtask15_1);
            desIm1.setImageResource(R.drawable.imgdiscr152);
            destext2.setText(R.string.descrtask15_2);
            desIm2.setImageResource(R.drawable.imgdiscr151);
            destext3.setText(R.string.descrtask15_3);
            desIm3.setImageResource(R.drawable.imgdiscr153);
            destext4.setText(R.string.descrtask15_4);
            desIm4.setImageResource(R.drawable.imgdiscr136);
            destext5.setText(R.string.descrtask15_5);
            desIm5.setImageResource(R.drawable.imgdiscr151);
    }

    public void DescPoiskIsprPutLBYmB(){
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        desIm5.setVisibility(View.VISIBLE);
        destext6.setVisibility(View.VISIBLE);
        desIm6.setVisibility(View.VISIBLE);
        destext7.setVisibility(View.VISIBLE);
        desIm7.setVisibility(View.VISIBLE);

        destext1.setText(R.string.descrtask16_1);
        desIm1.setImageResource(R.drawable.imgdiscr161);
        destext2.setText(R.string.descrtask16_2);
        desIm2.setImageResource(R.drawable.imgdiscr162);
        destext3.setText(R.string.descrtask16_3);
        desIm3.setImageResource(R.drawable.imgdiscr163);
        destext4.setText(R.string.descrtask16_4);
        desIm4.setImageResource(R.drawable.imgdiscr164);
        destext5.setText(R.string.descrtask16_5);
        desIm5.setImageResource(R.drawable.imgdiscr165);
        destext6.setText(R.string.descrtask16_6);
        desIm6.setImageResource(R.drawable.imgdiscr166);
        destext7.setText(R.string.descrtask16_7);
        desIm7.setImageResource(R.drawable.imgdiscr167);
    }
    public void DescPoiskIsprPutLBYbB(){
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        desIm5.setVisibility(View.VISIBLE);
        destext6.setVisibility(View.VISIBLE);
        desIm6.setVisibility(View.VISIBLE);
        destext7.setVisibility(View.VISIBLE);
        desIm7.setVisibility(View.VISIBLE);

        destext1.setText(R.string.descrtask17_1);
        desIm1.setImageResource(R.drawable.imgdiscr171);
        destext2.setText(R.string.descrtask17_2);
        desIm2.setImageResource(R.drawable.imgdiscr172);
        destext3.setText(R.string.descrtask17_3);
        desIm3.setImageResource(R.drawable.imgdiscr173);
        destext4.setText(R.string.descrtask17_4);
        desIm4.setImageResource(R.drawable.imgdiscr174);
        destext5.setText(R.string.descrtask17_5);
        desIm5.setImageResource(R.drawable.imgdiscr175);
        destext6.setText(R.string.descrtask17_6);
        desIm6.setImageResource(R.drawable.imgdiscr176);
        destext7.setText(R.string.descrtask17_7);
        desIm7.setImageResource(R.drawable.imgdiscr177);
    }
    public void DescPoiskIsprPutLBYbB2(){
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        desIm5.setVisibility(View.VISIBLE);

        destext1.setText(R.string.descrtask18_1);
        desIm1.setImageResource(R.drawable.imgdiscr181);
        destext2.setText(R.string.descrtask18_2);
        desIm2.setImageResource(R.drawable.imgdiscr182);
        destext3.setText(R.string.descrtask18_3);
        desIm3.setImageResource(R.drawable.imgdiscr183);
        desIm4.setImageResource(R.drawable.imgdiscr184);
        destext5.setText(R.string.descrtask18_4);
        desIm5.setImageResource(R.drawable.imgdiscr185);
    }

    public void DescTreningLZP(){
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        desIm3.setVisibility(View.VISIBLE);

        destext1.setText(R.string.descrtask19_1);
        desIm1.setImageResource(R.drawable.imgdiscr191);
        destext2.setText(R.string.descrtask19_2);
        desIm2.setImageResource(R.drawable.imgdiscr192);
        destext3.setText(R.string.descrtask19_3);
        desIm3.setImageResource(R.drawable.imgdiscr193);
    }

    public void DescEnterHold(){
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        desIm4.setVisibility(View.VISIBLE);

        destext1.setText(R.string.descrtask20_1);
        desIm1.setImageResource(R.drawable.instr_krug1);
        destext2.setText(R.string.descrtask20_2);
        desIm2.setImageResource(R.drawable.instr_krug2);
        destext3.setText(R.string.descrtask20_3);
        desIm3.setImageResource(R.drawable.instr_krug3);
        destext4.setText(R.string.descrtask20_4);
        desIm4.setImageResource(R.drawable.instr_krug4);
    }

    public void onClickObcAgain(View view) {
        SharedPreferences.Editor editor = mDataFiles.edit();
        editor.putBoolean(DATA_FILE_FIRSTENTER3, true);
        editor.apply();
        InstructActivity.this.finish();
        Intent intent = new Intent(InstructActivity.this, MainActivity.class);
        startActivity(intent);
    }
    //---------------------------------------------------------------------

}
