package com.MaxEle.maximarius.nir_navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;


public class StatActivity extends AppCompatActivity {

    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_CORRECT = "correct";
    public static final String DATA_FILE_WRONG = "wrong";
    public static final String DATA_FILE_CORRECT_NOW = "correctnow";
    public static final String DATA_FILE_WRONG_NOW = "wrongnow";
    public static final String DATA_FILE_LEVEL = "level";
    public static final String DATA_FILE_PREMIUM = "premakk";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";

    public static final String DATA_FILE_ST1 = "st1";
    public static final String DATA_FILE_ST2 = "st2";
    public static final String DATA_FILE_ST3 = "st3";
    public static final String DATA_FILE_ST4 = "st4";
    public static final String DATA_FILE_ST5 = "st5";
    public static final String DATA_FILE_ST6 = "st6";
    public static final String DATA_FILE_ST7 = "st7";
    public static final String DATA_FILE_ST8 = "st8";
    public static final String DATA_FILE_ST9 = "st9";
    public static final String DATA_FILE_ST10 = "st10";
    public static final String DATA_FILE_ST11 = "st11";
    public static final String DATA_FILE_ST12 = "st12";
    public static final String DATA_FILE_ST13 = "st13";
    public static final String DATA_FILE_ST14 = "st14";
    public static final String DATA_FILE_ST15 = "st15";
    public static final String DATA_FILE_ST16 = "st16";
    public static final String DATA_FILE_ST17 = "st17";
    public static final String DATA_FILE_ST18 = "st18";

    boolean Theme_Light;
    int typeoftaskglob;

    Spinner spinType;
    String spinText;

    SharedPreferences mDataFiles;

    int AvaLevel;
    int corransw;
    int wrongansw;
    int corranswnow;
    int wronganswnow;

    boolean PremAkk;
    Dialog Password;

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

        setContentView(R.layout.activity_stat);

        AdView mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        }else{
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);

        Password = new Dialog(StatActivity.this);
        Password.setTitle(getResources().getString(R.string.EntPas));
        Password.setContentView(R.layout.dialog_view);

        ScrollView viewansw = findViewById(R.id.view1);
        if (Theme_Light){
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            viewansw = findViewById(R.id.view2);
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview));
        }else {
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
            viewansw = findViewById(R.id.view2);
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
        }

        ProverkaDataFiles();

        TextView TextStatAll;
        String a;
        TextStatAll = findViewById(R.id.textViewContainAll1);
        a = getResources().getString(R.string.scorecorr) + corransw;
        TextStatAll.setText(a);
        TextStatAll = findViewById(R.id.textViewContainAll2);
        a = getResources().getString(R.string.scorewrong) + wrongansw;
        TextStatAll.setText(a);
        TextStatAll = findViewById(R.id.textViewContainAll3);
        a = getResources().getString(R.string.scorelvl) + AvaLevel;
        TextStatAll.setText(a);
        TextStatAll = findViewById(R.id.textViewContainAll4);
        double Dops;
        if (wrongansw+corransw!=0){Dops = (double)(wrongansw*100/(wrongansw+corransw));}else{Dops = 100;}
        a = getResources().getString(R.string.scordopspos) + String.format(Locale.getDefault(), "%.0f", +Dops)+"%";
        TextStatAll.setText(a);

        TextStatAll = findViewById(R.id.textViewContainNow1);
        a = getResources().getString(R.string.scorecorr) + corranswnow;
        TextStatAll.setText(a);
        TextStatAll = findViewById(R.id.textViewContainNow2);
        a = getResources().getString(R.string.scorewrong) + wronganswnow;
        TextStatAll.setText(a);

        spinType = findViewById(R.id.taskslist);
        final String[] typetask = getResources().getStringArray(R.array.taskstypes);
        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                spinText = spinType.getSelectedItem().toString();
                if (spinText.equals(typetask[0])){typeoftaskglob=1;VivodStatZad();}
                if (spinText.equals(typetask[1])){typeoftaskglob=2;VivodStatZad();}
                if (spinText.equals(typetask[2])){typeoftaskglob=3;VivodStatZad();}

                if (spinText.equals(typetask[3])){typeoftaskglob=4;VivodStatZad();}
                if (spinText.equals(typetask[4])){typeoftaskglob=5;VivodStatZad();}
                if (spinText.equals(typetask[5])){typeoftaskglob=6;VivodStatZad();}

                if (spinText.equals(typetask[6])){typeoftaskglob=7;VivodStatZad();}
                if (spinText.equals(typetask[7])){typeoftaskglob=8;VivodStatZad();}
                if (spinText.equals(typetask[8])){typeoftaskglob=9;VivodStatZad();}

                if (spinText.equals(typetask[9])){typeoftaskglob=10;VivodStatZad();}
                if (spinText.equals(typetask[10])){typeoftaskglob=11;VivodStatZad();}
                if (spinText.equals(typetask[11])){typeoftaskglob=12;VivodStatZad();}

                if (spinText.equals(typetask[12])){typeoftaskglob=13;VivodStatZad();}
                if (spinText.equals(typetask[13])){typeoftaskglob=14;VivodStatZad();}
                if (spinText.equals(typetask[14])){typeoftaskglob=15;VivodStatZad();}

                if (spinText.equals(typetask[15])){typeoftaskglob=16;VivodStatZad();}
                if (spinText.equals(typetask[16])){typeoftaskglob=17;VivodStatZad();}
                if (spinText.equals(typetask[17])){typeoftaskglob=18;VivodStatZad();}
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

}

    public void Obnyl(){
        TextView TextSt;
        TextSt=findViewById(R.id.textViewContainZad7);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt=findViewById(R.id.textViewContainZad8);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt=findViewById(R.id.textViewContainZad9);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt=findViewById(R.id.textViewContainZad10);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt=findViewById(R.id.textViewContainZad11);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt=findViewById(R.id.textViewContainZad12);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt=findViewById(R.id.textViewContainZad13);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt=findViewById(R.id.textViewContainZad14);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt=findViewById(R.id.textViewContainZad15);
        TextSt.setVisibility(View.INVISIBLE);

        TextSt=findViewById(R.id.textViewContainRec1);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));
        TextSt=findViewById(R.id.textViewContainRec2);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));
        TextSt=findViewById(R.id.textViewContainRec3);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));
        TextSt=findViewById(R.id.textViewContainRec4);
        TextSt.setVisibility(View.INVISIBLE);
        TextSt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));
    }
    public void VivodStatZad(){
        String numb;
        TextView TextStatAll;
        String[] separated;
        Obnyl();
        boolean flagOnly=true;
        switch (typeoftaskglob){
            case 1:
                numb=mDataFiles.getString(DATA_FILE_ST1, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomen0));
                }
                break;
            case 2:
                numb=mDataFiles.getString(DATA_FILE_ST2, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomen0));
                }
                break;
            case 3:
                numb=mDataFiles.getString(DATA_FILE_ST3, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomen0));
                }
                break;
            case 4:
                numb=mDataFiles.getString(DATA_FILE_ST4, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasBen));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasYV));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);
                TextStatAll = findViewById(R.id.textViewContainZad13);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasYS));
                TextStatAll = findViewById(R.id.textViewContainZad14);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[6]);
                TextStatAll = findViewById(R.id.textViewContainZad15);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[7]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenBen1));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenYV));
                }
                if (Integer.parseInt(separated[6])<Integer.parseInt(separated[7])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec4);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenYS));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 5:
                numb=mDataFiles.getString(DATA_FILE_ST5, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasUekv));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasYV));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenUekv));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenYV2));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 6:
                numb=mDataFiles.getString(DATA_FILE_ST6, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasUekv));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasYV));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);
                TextStatAll = findViewById(R.id.textViewContainZad13);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasBen));
                TextStatAll = findViewById(R.id.textViewContainZad14);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[6]);
                TextStatAll = findViewById(R.id.textViewContainZad15);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[7]);
                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenUekv));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenYV2));
                }
                if (Integer.parseInt(separated[6])<Integer.parseInt(separated[7])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec4);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenBen));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 7:
                numb=mDataFiles.getString(DATA_FILE_ST7, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomen0));
                }
                break;
            case 8:
                numb=mDataFiles.getString(DATA_FILE_ST8, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);

                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomen0));
                }
                break;
            case 9:
                numb=mDataFiles.getString(DATA_FILE_ST9, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasYV));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasYS));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenYV3));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenYS));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 10:
                numb=mDataFiles.getString(DATA_FILE_ST10, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasHbar));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasdH));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);
                TextStatAll = findViewById(R.id.textViewContainZad13);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprastpr));
                TextStatAll = findViewById(R.id.textViewContainZad14);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[6]);
                TextStatAll = findViewById(R.id.textViewContainZad15);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[7]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenHbar));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomendH));
                }
                if (Integer.parseInt(separated[6])<Integer.parseInt(separated[7])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec4);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomentpr));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 11:
                numb=mDataFiles.getString(DATA_FILE_ST11, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasdH1));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasHbar));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);
                TextStatAll = findViewById(R.id.textViewContainZad13);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasdH2));
                TextStatAll = findViewById(R.id.textViewContainZad14);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[6]);
                TextStatAll = findViewById(R.id.textViewContainZad15);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[7]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomendH1));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenHbar));
                }
                if (Integer.parseInt(separated[6])<Integer.parseInt(separated[7])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec4);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomendH2));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 12:
                numb=mDataFiles.getString(DATA_FILE_ST12, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasSnach));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasdHsht));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);
                TextStatAll = findViewById(R.id.textViewContainZad13);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasSrasp));
                TextStatAll = findViewById(R.id.textViewContainZad14);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[6]);
                TextStatAll = findViewById(R.id.textViewContainZad15);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[7]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenSnach));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomendHsht));
                }
                if (Integer.parseInt(separated[6])<Integer.parseInt(separated[7])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec4);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenSrasp));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 13:
                numb=mDataFiles.getString(DATA_FILE_ST13, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasdVsh));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasVprisp));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomendVsh));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenVprisp));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 14:
                numb=mDataFiles.getString(DATA_FILE_ST14, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasVprisp));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasdVsh));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenVprisp2));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomendVsh));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 15:
                numb=mDataFiles.getString(DATA_FILE_ST15, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasVprispKYS));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenVprispKYS));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 16:
                numb=mDataFiles.getString(DATA_FILE_ST16, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasDP));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasLBY));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);
                TextStatAll = findViewById(R.id.textViewContainZad13);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasBY));
                TextStatAll = findViewById(R.id.textViewContainZad14);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[6]);
                TextStatAll = findViewById(R.id.textViewContainZad15);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[7]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenDP));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenLBY));
                }
                if (Integer.parseInt(separated[6])<Integer.parseInt(separated[7])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec4);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenBY));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 17:
                numb=mDataFiles.getString(DATA_FILE_ST17, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasDP));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasLBY));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);
                TextStatAll = findViewById(R.id.textViewContainZad13);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasBY));
                TextStatAll = findViewById(R.id.textViewContainZad14);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[6]);
                TextStatAll = findViewById(R.id.textViewContainZad15);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[7]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenDP));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenLBY));
                }
                if (Integer.parseInt(separated[6])<Integer.parseInt(separated[7])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec4);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenBY));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
            case 18:
                numb=mDataFiles.getString(DATA_FILE_ST18, "");
                separated = numb.split("/");
                TextStatAll = findViewById(R.id.textViewContainZad5);
                TextStatAll.setText(separated[0]);
                TextStatAll = findViewById(R.id.textViewContainZad6);
                TextStatAll.setText(separated[1]);
                TextStatAll = findViewById(R.id.textViewContainZad7);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasDP));
                TextStatAll = findViewById(R.id.textViewContainZad8);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[2]);
                TextStatAll = findViewById(R.id.textViewContainZad9);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[3]);
                TextStatAll = findViewById(R.id.textViewContainZad10);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasLBY));
                TextStatAll = findViewById(R.id.textViewContainZad11);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[4]);
                TextStatAll = findViewById(R.id.textViewContainZad12);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[5]);
                TextStatAll = findViewById(R.id.textViewContainZad13);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(getResources().getString(R.string.statdoprasBY));
                TextStatAll = findViewById(R.id.textViewContainZad14);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[6]);
                TextStatAll = findViewById(R.id.textViewContainZad15);
                TextStatAll.setVisibility(View.VISIBLE);
                TextStatAll.setText(separated[7]);

                if (Integer.parseInt(separated[2])<Integer.parseInt(separated[3])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec2);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenDP));
                }
                if (Integer.parseInt(separated[4])<Integer.parseInt(separated[5])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec3);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenLBY));
                }
                if (Integer.parseInt(separated[6])<Integer.parseInt(separated[7])) {
                    flagOnly=false;
                    TextStatAll = findViewById(R.id.textViewContainRec4);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextStatAll.setText(getResources().getString(R.string.recomenBY));
                }
                if (Integer.parseInt(separated[0])<Integer.parseInt(separated[1])) {
                    TextStatAll = findViewById(R.id.textViewContainRec1);
                    TextStatAll.setVisibility(View.VISIBLE);
                    TextStatAll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    if (flagOnly) {
                        TextStatAll.setText(getResources().getString(R.string.recomen1));
                    }else{
                        TextStatAll.setText(getResources().getString(R.string.recomen2));
                    }
                }
                break;
        }
    }
    public void ProverkaDataFiles(){
        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);

        AvaLevel = mDataFiles.getInt(DATA_FILE_LEVEL, 0);
        corransw = mDataFiles.getInt(DATA_FILE_CORRECT, 0);
        wrongansw = mDataFiles.getInt(DATA_FILE_WRONG, 0);
        corranswnow = mDataFiles.getInt(DATA_FILE_CORRECT_NOW, 0);
        wronganswnow = mDataFiles.getInt(DATA_FILE_WRONG_NOW, 0);
        PremAkk = mDataFiles.getBoolean(DATA_FILE_PREMIUM, false);
    }

    @Override
    public void onBackPressed() {
        StatActivity.this.finish();
        Intent intent = new Intent(StatActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }
    public void onClickBack(View view){
        StatActivity.this.finish();
        Intent intent = new Intent(StatActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }


}
