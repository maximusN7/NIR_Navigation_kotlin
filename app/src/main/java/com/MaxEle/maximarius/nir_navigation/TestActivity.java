package com.MaxEle.maximarius.nir_navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.MaxEle.maximarius.nir_navigation.newpack.Ben_p;
import com.MaxEle.maximarius.nir_navigation.newpack.LYRRazvr;
import com.MaxEle.maximarius.nir_navigation.newpack.RadRazv;
import com.MaxEle.maximarius.nir_navigation.newpack.U_p;
import com.MaxEle.maximarius.nir_navigation.newpack.VremyaRazvor;
import com.MaxEle.maximarius.nir_navigation.newpack.W_p;
import com.MaxEle.maximarius.nir_navigation.newpack.YS_p;
import com.MaxEle.maximarius.nir_navigation.newpack.YV_p;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class TestActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_CORRECT = "correct";
    public static final String DATA_FILE_WRONG = "wrong";
    public static final String DATA_FILE_CORRECT_NOW = "correctnow";
    public static final String DATA_FILE_WRONG_NOW = "wrongnow";
    public static final String DATA_FILE_LEVEL = "level";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";

    public static final String DATA_FILE_KOLRESH1_1 = "kr1_1";
    public static final String DATA_FILE_KOLRESH1_2 = "kr1_2";
    public static final String DATA_FILE_KOLRESH1_3 = "kr1_3";

    public static final String DATA_FILE_KOLNYSHN1_1 = "kn1_1";
    public static final String DATA_FILE_KOLNYSHN1_2 = "kn1_2";
    public static final String DATA_FILE_KOLNYSHN1_3 = "kn1_3";

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

    int typeoftaskglob;

    boolean Theme_Light;
    boolean isNewSeans;

    TextView ConditionTask;
    TextView CorrAnswerCalc;
    TextView TypeIzm;
    EditText AnswerPers;
    Spinner spinType;
    String spinText;

    EditText AnswerDop1;
    EditText AnswerDop2;
    EditText AnswerDop3;

    double AnswCalcDop1;
    double AnswCalcDop2;
    double AnswCalcDop3;

    double diap;
    String AnswerText;
    double AnswerCalc;
    int randtype;

    boolean compl1;
    boolean compl2;
    boolean compl3;

    int AvaLevel;
    int corransw;
    int wrongansw;
    int corranswnow;
    int wronganswnow;
    SharedPreferences mDataFiles;

    boolean Unfliped = true;
    NestedScrollView NLCenter;
    RelativeLayout NLlay;
    ImageView NL1;
    ImageView NL2;
    ImageView NL3;
    ImageView Visisr;

    float dX = 0;
    float dY = 0;

    int[][] arrShat = {{0,300,400,500,600,700,800,900},{2000,1,2,3,4,7,9,10},{4000,2,4,6,10,16,23,24},{6000,3,6,11,18,27,39,40},{8000,4,9,17,28,41,53,54},{10000,6,13,24,40,56,80,81},{12000,9,19,34,56,78,98,99},{14000,12,25,48,73,97,118,119}};

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

        setContentView(R.layout.activity_test);

        AdView mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        }else{
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        isNewSeans = true;

        Obnyl();

        ProverkaDataFiles();

        NLlay = findViewById(R.id.RelayLay);
        NL1 = findViewById(R.id.imageViewNL1);
        NL2 = findViewById(R.id.imageViewNL2);
        NL3 = findViewById(R.id.imageViewNL3);

        NLCenter = findViewById(R.id.scrollCenter);
        NLCenter.post(new Runnable() {
            public void run() {
                int cent = NL2.getBottom()/3;
                NLCenter.smoothScrollTo(0, cent);
            }
        });

        spinType =  findViewById(R.id.levellist);
        AnswerPers = findViewById(R.id.textViewEnterAnswer);
        AnswerPers.setOnEditorActionListener(this);
        AnswerPers.setFocusableInTouchMode(false);
        ConditionTask = findViewById(R.id.textViewCondition);
        CorrAnswerCalc = findViewById(R.id.textViewAnswerCalcul);
        TypeIzm = findViewById(R.id.textViewTypeIzm);
        CorrAnswerCalc.setVisibility(View.INVISIBLE);

        ScrollView viewansw = findViewById(R.id.viewmain);
        LinearLayout viewneed = findViewById(R.id.viewneed);
        if (Theme_Light){
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            viewneed.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            CorrAnswerCalc.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            AnswerPers.setBackgroundResource(R.color.standartansw);
        }else {
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
            viewneed.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
            CorrAnswerCalc.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
            AnswerPers.setBackgroundResource(R.color.standartansw1);
        }

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.leveltypes, android.R.layout.simple_spinner_item);
        if (Theme_Light){
            adapter.setDropDownViewResource(R.layout.spinner_light);
        }else {
            adapter.setDropDownViewResource(R.layout.spinner_dark);
        }

        spinType.setAdapter(adapter);

        spinType.setSelection(AvaLevel-1);

        final String[] typetask = getResources().getStringArray(R.array.leveltypes);
        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                ConditionTask.setText(R.string.condition);
                AnswerPers.setText("");
                TypeIzm.setText("");
                CorrAnswerCalc.setText(R.string.corranswer);
                if (Theme_Light){
                    AnswerPers.setBackgroundResource(R.color.standartansw);
                }else {
                    AnswerPers.setBackgroundResource(R.color.standartansw1);
                }
                AnswerPers.setFocusableInTouchMode(false);
                CorrAnswerCalc.setVisibility(View.INVISIBLE);

                String s;
                spinText = spinType.getSelectedItem().toString();
                if (spinText.equals(typetask[1])){
                    if (AvaLevel<2){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[2])){
                    if (AvaLevel<3){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[3])){
                    if (AvaLevel<4){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[4])){
                    if (AvaLevel<5){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[5])){
                    if (AvaLevel<6){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[6])){
                    if (AvaLevel<7){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }

                OnNextLevel();
                Obnyl();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        OnNextLevel();

        Visisr = findViewById(R.id.imageViewVisir);

        Visisr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        view.animate()
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

     /*   Button Button1=findViewById(R.id.buttoGen);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);
        Button1=findViewById(R.id.buttonBack);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);
        Button1=findViewById(R.id.buttonNL);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);
        Button1=findViewById(R.id.buttonNLclose);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);
        Button1=findViewById(R.id.buttonNLflip);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);
        Button1=findViewById(R.id.buttonVIS);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);*/
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            String stringAnsw = AnswerPers.getText().toString().replace(',','.');
            boolean isNumAns= isNumeric(stringAnsw);
            if (isNumAns) {
                AnswerDop1 = findViewById(R.id.textViewEnterAnswerdop1);
                AnswerDop1.setFocusableInTouchMode(false);
                AnswerDop2 = findViewById(R.id.textViewEnterAnswerdop2);
                AnswerDop2.setFocusableInTouchMode(false);
                AnswerDop3 = findViewById(R.id.textViewEnterAnswerdop3);
                AnswerDop3.setFocusableInTouchMode(false);
                CorrAnswerCalc.setVisibility(View.VISIBLE);
                double answerfrpers = Double.parseDouble(AnswerPers.getText().toString().replace(',','.'));
                int pointPlusCorr;

                if (answerfrpers <= AnswerCalc + diap && answerfrpers >= AnswerCalc - diap) {
                    AnswerPers.setBackgroundResource(R.color.correctansw);
                    corransw=corransw+1;
                    corranswnow=corranswnow+1;
                    SharedPreferences.Editor editor = mDataFiles.edit();
                    editor.putInt(DATA_FILE_CORRECT, corransw);
                    editor.putInt(DATA_FILE_CORRECT_NOW, corranswnow);
                    if (randtype==1){
                        pointPlusCorr= mDataFiles.getInt(DATA_FILE_KOLRESH1_1, 0);
                        pointPlusCorr = pointPlusCorr+1;
                        editor.putInt(DATA_FILE_KOLRESH1_1, pointPlusCorr);
                    }
                    if (randtype==2){
                        pointPlusCorr= mDataFiles.getInt(DATA_FILE_KOLRESH1_2, 0);
                        pointPlusCorr = pointPlusCorr+1;
                        editor.putInt(DATA_FILE_KOLRESH1_2, pointPlusCorr);
                    }
                    if (randtype==3){
                        pointPlusCorr= mDataFiles.getInt(DATA_FILE_KOLRESH1_3, 0);
                        pointPlusCorr = pointPlusCorr+1;
                        editor.putInt(DATA_FILE_KOLRESH1_3, pointPlusCorr);
                    }

                    String numb;
                    String numbnew;
                    int a;
                    String dopans;
                    boolean isNumAnsd;
                    String[] separated;
                    TextView CalcVivDop;
                    switch (typeoftaskglob){
                        case 1:
                            numb=mDataFiles.getString(DATA_FILE_ST1, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            editor.putString(DATA_FILE_ST1,numbnew);
                            break;
                        case 2:
                            numb=mDataFiles.getString(DATA_FILE_ST2, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            editor.putString(DATA_FILE_ST2,numbnew);
                            break;
                        case 3:
                            numb=mDataFiles.getString(DATA_FILE_ST3, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            editor.putString(DATA_FILE_ST3,numbnew);
                            break;
                        case 4:
                            numb=mDataFiles.getString(DATA_FILE_ST4, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) == AnswCalcDop1) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) == AnswCalcDop2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST4,numbnew);
                            break;
                        case 5:
                            numb=mDataFiles.getString(DATA_FILE_ST5, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) == AnswCalcDop1) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            editor.putString(DATA_FILE_ST5,numbnew);
                            break;
                        case 6:
                            numb=mDataFiles.getString(DATA_FILE_ST6, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) == AnswCalcDop1) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST6,numbnew);
                            break;
                        case 7:
                            numb=mDataFiles.getString(DATA_FILE_ST7, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            editor.putString(DATA_FILE_ST7,numbnew);
                            break;
                        case 8:
                            numb=mDataFiles.getString(DATA_FILE_ST8, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            editor.putString(DATA_FILE_ST8,numbnew);
                            break;
                        case 9:
                            numb=mDataFiles.getString(DATA_FILE_ST9, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            editor.putString(DATA_FILE_ST9,numbnew);
                            break;
                        case 10:
                            numb=mDataFiles.getString(DATA_FILE_ST10, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST10,numbnew);
                            break;
                        case 11:
                            numb=mDataFiles.getString(DATA_FILE_ST11, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST11,numbnew);
                            break;
                        case 12:
                            numb=mDataFiles.getString(DATA_FILE_ST12, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST12,numbnew);
                            break;
                        case 13:
                            numb=mDataFiles.getString(DATA_FILE_ST13, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            editor.putString(DATA_FILE_ST13,numbnew);
                            break;
                        case 14:
                            numb=mDataFiles.getString(DATA_FILE_ST14, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            editor.putString(DATA_FILE_ST14,numbnew);
                            break;
                        case 15:
                            numb=mDataFiles.getString(DATA_FILE_ST15, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            editor.putString(DATA_FILE_ST15,numbnew);
                            break;
                        case 16:
                            numb=mDataFiles.getString(DATA_FILE_ST16, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST16,numbnew);
                            break;
                        case 17:
                            numb=mDataFiles.getString(DATA_FILE_ST17, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST17,numbnew);
                            break;
                        case 18:
                            numb=mDataFiles.getString(DATA_FILE_ST18, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[0]);
                            a++;
                            numbnew=a+"/";
                            numbnew=numbnew+separated[1];
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST18,numbnew);
                            break;
                    }
                    editor.apply();
                } else {
                    AnswerPers.setBackgroundResource(R.color.wrongansw);
                    wrongansw=wrongansw+1;
                    wronganswnow=wronganswnow+1;
                    SharedPreferences.Editor editor = mDataFiles.edit();
                    editor.putInt(DATA_FILE_WRONG, wrongansw);
                    editor.putInt(DATA_FILE_WRONG_NOW, wronganswnow);
                    if (randtype==1){
                        pointPlusCorr= mDataFiles.getInt(DATA_FILE_KOLNYSHN1_1, 0);
                        pointPlusCorr = pointPlusCorr+2;
                        editor.putInt(DATA_FILE_KOLNYSHN1_1, pointPlusCorr);
                    }
                    if (randtype==2){
                        pointPlusCorr= mDataFiles.getInt(DATA_FILE_KOLNYSHN1_2, 0);
                        pointPlusCorr = pointPlusCorr+2;
                        editor.putInt(DATA_FILE_KOLNYSHN1_2, pointPlusCorr);
                    }
                    if (randtype==3){
                        pointPlusCorr= mDataFiles.getInt(DATA_FILE_KOLNYSHN1_3, 0);
                        pointPlusCorr = pointPlusCorr+2;
                        editor.putInt(DATA_FILE_KOLNYSHN1_3, pointPlusCorr);
                    }

                    String numb;
                    String numbnew;
                    int a;
                    String dopans;
                    boolean isNumAnsd;
                    String[] separated;
                    TextView CalcVivDop;
                    switch (typeoftaskglob){
                        case 1:
                            numb=mDataFiles.getString(DATA_FILE_ST1, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            editor.putString(DATA_FILE_ST1,numbnew);
                            break;
                        case 2:
                            numb=mDataFiles.getString(DATA_FILE_ST2, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            editor.putString(DATA_FILE_ST2,numbnew);
                            break;
                        case 3:
                            numb=mDataFiles.getString(DATA_FILE_ST3, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            editor.putString(DATA_FILE_ST3,numbnew);
                            break;
                        case 4:
                            numb=mDataFiles.getString(DATA_FILE_ST4, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) == AnswCalcDop1) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) == AnswCalcDop2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST4,numbnew);
                            break;
                        case 5:
                            numb=mDataFiles.getString(DATA_FILE_ST5, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) == AnswCalcDop1) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            editor.putString(DATA_FILE_ST5,numbnew);
                            break;
                        case 6:
                            numb=mDataFiles.getString(DATA_FILE_ST6, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) == AnswCalcDop1) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST6,numbnew);
                            break;
                        case 7:
                            numb=mDataFiles.getString(DATA_FILE_ST7, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            editor.putString(DATA_FILE_ST7,numbnew);
                            break;
                        case 8:
                            numb=mDataFiles.getString(DATA_FILE_ST8, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            editor.putString(DATA_FILE_ST8,numbnew);
                            break;
                        case 9:
                            numb=mDataFiles.getString(DATA_FILE_ST9, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            editor.putString(DATA_FILE_ST9,numbnew);
                            break;
                        case 10:
                            numb=mDataFiles.getString(DATA_FILE_ST10, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST10,numbnew);
                            break;
                        case 11:
                            numb=mDataFiles.getString(DATA_FILE_ST11, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST11,numbnew);
                            break;
                        case 12:
                            numb=mDataFiles.getString(DATA_FILE_ST12, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST12,numbnew);
                            break;
                        case 13:
                            numb=mDataFiles.getString(DATA_FILE_ST13, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            editor.putString(DATA_FILE_ST13,numbnew);
                            break;
                        case 14:
                            numb=mDataFiles.getString(DATA_FILE_ST14, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            editor.putString(DATA_FILE_ST14,numbnew);
                            break;
                        case 15:
                            numb=mDataFiles.getString(DATA_FILE_ST15, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            editor.putString(DATA_FILE_ST15,numbnew);
                            break;
                        case 16:
                            numb=mDataFiles.getString(DATA_FILE_ST16, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST16,numbnew);
                            break;
                        case 17:
                            numb=mDataFiles.getString(DATA_FILE_ST17, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST17,numbnew);
                            break;
                        case 18:
                            numb=mDataFiles.getString(DATA_FILE_ST18, "");
                            separated = numb.split("/");
                            a=Integer.parseInt(separated[1]);
                            a++;
                            numbnew=separated[0]+"/";
                            numbnew=numbnew+a;
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop1);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop1)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop1.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop1+2 && Double.parseDouble(dopans) >= AnswCalcDop1-2) {
                                    AnswerDop1.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[2]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[3];
                                }else{
                                    AnswerDop1.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[3]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[2]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[2]+"/"+separated[3];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop2);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop2)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop2.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop2+2 && Double.parseDouble(dopans) >= AnswCalcDop2-2) {
                                    AnswerDop2.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[4]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[5];
                                }else{
                                    AnswerDop2.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[5]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[4]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[4]+"/"+separated[5];
                            }
                            CalcVivDop = findViewById(R.id.textViewAnswerCalculdop3);
                            CalcVivDop.setVisibility(View.VISIBLE);
                            CalcVivDop.setText(String.format("%.1f", +AnswCalcDop3)+getResources().getString(R.string.corranswer));
                            dopans=AnswerDop3.getText().toString().replace(',','.');
                            isNumAnsd= isNumeric(dopans);
                            if (isNumAnsd) {
                                if (Double.parseDouble(dopans) <= AnswCalcDop3+2 && Double.parseDouble(dopans) >= AnswCalcDop3-2) {
                                    AnswerDop3.setBackgroundResource(R.color.correctansw);
                                    a=Integer.parseInt(separated[6]);
                                    a++;
                                    numbnew=numbnew+"/"+a+"/"+separated[7];
                                }else{
                                    AnswerDop3.setBackgroundResource(R.color.wrongansw);
                                    a=Integer.parseInt(separated[7]);
                                    a++;
                                    numbnew=numbnew+"/"+separated[6]+"/"+a;
                                }
                            }else{
                                numbnew=numbnew+"/"+separated[6]+"/"+separated[7];
                            }
                            editor.putString(DATA_FILE_ST18,numbnew);
                            break;
                    }
                    editor.apply();
                }
                CorrAnswerCalc.setText(AnswerText+getResources().getString(R.string.corranswer));
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                AnswerPers.setFocusable(false);
            }
            OnNextLevel();
            return true;
        }
        return false;
    }

    boolean NLshowed = false;

    @Override
    public void onBackPressed() {
        if (NLshowed){
            NLshowed = false;
            NLlay.setVisibility(View.INVISIBLE);
            Unfliped = true;
            Button NLshow = findViewById(R.id.buttonNL);
            NLshow.setVisibility(View.VISIBLE);
            Button ButtonBack = findViewById(R.id.buttonBack);
            ButtonBack.setVisibility(View.VISIBLE);
        }else {
            TestActivity.this.finish();
            Intent intent = new Intent(TestActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.act_in_back,R.anim.act_out_back);
        }
    }
    public void onClickBack(View view){
        TestActivity.this.finish();
        Intent intent = new Intent(TestActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_in_back,R.anim.act_out_back);
    }
/*
    public void onClickOrient(View view) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }*/

    public void onClickNLshow(View view) {
        Button ButtonBack = findViewById(R.id.buttonBack);
        ButtonBack.setVisibility(View.INVISIBLE);
        NLshowed = true;
        NL1.setImageResource(R.drawable.nl1_1);
        NL2.setImageResource(R.drawable.nl1_2);
        NL3.setImageResource(R.drawable.nl1_3);
        NLlay.setVisibility(View.VISIBLE);
        Button NLshow = findViewById(R.id.buttonNL);
        NLshow.setVisibility(View.INVISIBLE);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toast.makeText(TestActivity.this, getResources().getString(R.string.toastNLFixed), Toast.LENGTH_SHORT).show();
    }
    public void onClickNLclose(View view) {
        NLshowed = false;
        NLlay.setVisibility(View.INVISIBLE);
        Unfliped = true;
        Button NLshow = findViewById(R.id.buttonNL);
        NLshow.setVisibility(View.VISIBLE);
        Button ButtonBack = findViewById(R.id.buttonBack);
        ButtonBack.setVisibility(View.VISIBLE);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        Toast.makeText(TestActivity.this, getResources().getString(R.string.toastNLUnspeci), Toast.LENGTH_SHORT).show();
    }
    public void onClickNLflip(View view) {
        Unfliped = !Unfliped;
        if (Unfliped){
            NL1.setImageResource(R.drawable.nl1_1);
            NL2.setImageResource(R.drawable.nl1_2);
            NL3.setImageResource(R.drawable.nl1_3);
        }else{
            NL1.setImageResource(R.drawable.nl2_1);
            NL2.setImageResource(R.drawable.nl2_2);
            NL3.setImageResource(R.drawable.nl2_3);
        }
    }
    public void onClickVisir(View view) {
        Visisr = findViewById(R.id.imageViewVisir);
        Visisr.setY(0);
    }

    public void ProverkaDataFiles(){
        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);

        AvaLevel = mDataFiles.getInt(DATA_FILE_LEVEL, 0);
        corransw = mDataFiles.getInt(DATA_FILE_CORRECT, 0);
        wrongansw = mDataFiles.getInt(DATA_FILE_WRONG, 0);
        corranswnow = mDataFiles.getInt(DATA_FILE_CORRECT_NOW, 0);
        wronganswnow = mDataFiles.getInt(DATA_FILE_WRONG_NOW, 0);
    }

    public void OnNextLevel(){
        int resh;
        int nysh;
        TextView ReshNyshText;
        String s;
        compl1 = false;
        compl2 = false;
        compl3 = false;
        if (AvaLevel==6){
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_1, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_1, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup1);
            ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            s=getResources().getString(R.string.RNtask6_1);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl1 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_2, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_2, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup2);
            ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            s=getResources().getString(R.string.RNtask6_2);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl2 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_3, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_3, 0);
            ReshNyshText=findViewById(R.id.textViewCondToLevelup3);
            ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            s=getResources().getString(R.string.RNtask6_3);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl3 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}

            if (compl1 && compl2 && compl3){
                AvaLevel = 7;
                String mes = getResources().getString(R.string.toastlvlup);
                Toast.makeText(getApplicationContext(), mes,
                        Toast.LENGTH_SHORT).show();
                ConditionTask.setText(R.string.condition);
                AnswerPers.setText("");
                TypeIzm.setText("");
                CorrAnswerCalc.setText(R.string.corranswer);
                if (Theme_Light){
                    AnswerPers.setBackgroundResource(R.color.standartansw);
                }else {
                    AnswerPers.setBackgroundResource(R.color.standartansw1);
                }
                AnswerPers.setFocusableInTouchMode(false);
                CorrAnswerCalc.setVisibility(View.INVISIBLE);
                Obnyl();

                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_LEVEL, AvaLevel);

                editor.putInt(DATA_FILE_KOLRESH1_1, 0);
                editor.putInt(DATA_FILE_KOLRESH1_2, 0);
                editor.putInt(DATA_FILE_KOLRESH1_3, 0);

                editor.putInt(DATA_FILE_KOLNYSHN1_1, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_2, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_3, 5);

                editor.apply();
            }
        }
        if (AvaLevel==5){
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_1, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_1, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup1);
            ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            s=getResources().getString(R.string.RNtask5_1);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl1 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_2, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_2, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup2);
            ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            s=getResources().getString(R.string.RNtask5_2);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl2 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_3, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_3, 0);
            ReshNyshText=findViewById(R.id.textViewCondToLevelup3);
            ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            s=getResources().getString(R.string.RNtask5_3);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl3 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}

            if (compl1 && compl2 && compl3){
                AvaLevel = 6;
                String mes = getResources().getString(R.string.toastlvlup);
                Toast.makeText(getApplicationContext(), mes,
                        Toast.LENGTH_SHORT).show();
                ConditionTask.setText(R.string.condition);
                AnswerPers.setText("");
                TypeIzm.setText("");
                CorrAnswerCalc.setText(R.string.corranswer);
                if (Theme_Light){
                    AnswerPers.setBackgroundResource(R.color.standartansw);
                }else {
                    AnswerPers.setBackgroundResource(R.color.standartansw1);
                }
                AnswerPers.setFocusableInTouchMode(false);
                CorrAnswerCalc.setVisibility(View.INVISIBLE);
                Obnyl();

                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_LEVEL, AvaLevel);

                editor.putInt(DATA_FILE_KOLRESH1_1, 0);
                editor.putInt(DATA_FILE_KOLRESH1_2, 0);
                editor.putInt(DATA_FILE_KOLRESH1_3, 0);

                editor.putInt(DATA_FILE_KOLNYSHN1_1, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_2, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_3, 5);

                editor.apply();
            }
        }
        if (AvaLevel==4){
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_1, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_1, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup1);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask4_1);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl1 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_2, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_2, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup2);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask4_2);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl2 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_3, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_3, 0);
            ReshNyshText=findViewById(R.id.textViewCondToLevelup3);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask4_3);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl3 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}

            if (compl1 && compl2 && compl3){
                AvaLevel = 5;
                String mes = getResources().getString(R.string.toastlvlup);
                Toast.makeText(getApplicationContext(), mes,
                        Toast.LENGTH_SHORT).show();
                ConditionTask.setText(R.string.condition);
                AnswerPers.setText("");
                TypeIzm.setText("");
                CorrAnswerCalc.setText(R.string.corranswer);
                if (Theme_Light){
                    AnswerPers.setBackgroundResource(R.color.standartansw);
                }else {
                    AnswerPers.setBackgroundResource(R.color.standartansw1);
                }
                AnswerPers.setFocusableInTouchMode(false);
                CorrAnswerCalc.setVisibility(View.INVISIBLE);
                Obnyl();

                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_LEVEL, AvaLevel);

                editor.putInt(DATA_FILE_KOLRESH1_1, 0);
                editor.putInt(DATA_FILE_KOLRESH1_2, 0);
                editor.putInt(DATA_FILE_KOLRESH1_3, 0);

                editor.putInt(DATA_FILE_KOLNYSHN1_1, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_2, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_3, 5);

                editor.apply();
            }
        }
        if (AvaLevel==3){
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_1, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_1, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup1);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask3_1);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl1 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_2, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_2, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup2);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask3_2);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl2 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_3, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_3, 0);
            ReshNyshText=findViewById(R.id.textViewCondToLevelup3);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask3_3);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl3 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}

            if (compl1 && compl2 && compl3){
                AvaLevel = 4;
                String mes = getResources().getString(R.string.toastlvlup);
                Toast.makeText(getApplicationContext(), mes,
                        Toast.LENGTH_SHORT).show();
                ConditionTask.setText(R.string.condition);
                AnswerPers.setText("");
                TypeIzm.setText("");
                CorrAnswerCalc.setText(R.string.corranswer);
                if (Theme_Light){
                    AnswerPers.setBackgroundResource(R.color.standartansw);
                }else {
                    AnswerPers.setBackgroundResource(R.color.standartansw1);
                }
                AnswerPers.setFocusableInTouchMode(false);
                CorrAnswerCalc.setVisibility(View.INVISIBLE);
                Obnyl();

                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_LEVEL, AvaLevel);

                editor.putInt(DATA_FILE_KOLRESH1_1, 0);
                editor.putInt(DATA_FILE_KOLRESH1_2, 0);
                editor.putInt(DATA_FILE_KOLRESH1_3, 0);

                editor.putInt(DATA_FILE_KOLNYSHN1_1, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_2, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_3, 5);

                editor.apply();
            }
        }
        if (AvaLevel==2){
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_1, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_1, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup1);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask2_1);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl1 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_2, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_2, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup2);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask2_2);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl2 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_3, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_3, 0);
            ReshNyshText=findViewById(R.id.textViewCondToLevelup3);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask2_3);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl3 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}

            if (compl1 && compl2 && compl3){
                AvaLevel = 3;
                String mes = getResources().getString(R.string.toastlvlup);
                Toast.makeText(getApplicationContext(), mes,
                        Toast.LENGTH_SHORT).show();
                ConditionTask.setText(R.string.condition);
                AnswerPers.setText("");
                TypeIzm.setText("");
                CorrAnswerCalc.setText(R.string.corranswer);
                if (Theme_Light){
                    AnswerPers.setBackgroundResource(R.color.standartansw);
                }else {
                    AnswerPers.setBackgroundResource(R.color.standartansw1);
                }
                AnswerPers.setFocusableInTouchMode(false);
                CorrAnswerCalc.setVisibility(View.INVISIBLE);
                Obnyl();

                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_LEVEL, AvaLevel);

                editor.putInt(DATA_FILE_KOLRESH1_1, 0);
                editor.putInt(DATA_FILE_KOLRESH1_2, 0);
                editor.putInt(DATA_FILE_KOLRESH1_3, 0);

                editor.putInt(DATA_FILE_KOLNYSHN1_1, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_2, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_3, 5);

                editor.apply();
            }
        }
        if (AvaLevel==1){
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_1, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_1, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup1);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask1_1);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl1 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_2, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_2, 0);
            ReshNyshText = findViewById(R.id.textViewCondToLevelup2);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask1_2);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl2 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}
            resh = mDataFiles.getInt(DATA_FILE_KOLRESH1_3, 0);
            nysh = mDataFiles.getInt(DATA_FILE_KOLNYSHN1_3, 0);
            ReshNyshText=findViewById(R.id.textViewCondToLevelup3);
            if (Theme_Light){
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple));
            }else {
                ReshNyshText.setTextColor(getResources().getColor(R.color.textsimple1));
            }
            s=getResources().getString(R.string.RNtask1_3);
            s = s + resh +"/"+nysh;
            ReshNyshText.setText(s);
            if (resh>=nysh){compl3 = true; ReshNyshText.setTextColor(getResources().getColor(R.color.correctansw));}

            if (compl1 && compl2 && compl3){
                AvaLevel = 2;
                String mes = getResources().getString(R.string.toastlvlup);
                Toast.makeText(getApplicationContext(), mes,
                        Toast.LENGTH_SHORT).show();
                ConditionTask.setText(R.string.condition);
                AnswerPers.setText("");
                TypeIzm.setText("");
                CorrAnswerCalc.setText(R.string.corranswer);
                if (Theme_Light){
                    AnswerPers.setBackgroundResource(R.color.standartansw);
                }else {
                    AnswerPers.setBackgroundResource(R.color.standartansw1);
                }
                AnswerPers.setFocusableInTouchMode(false);
                CorrAnswerCalc.setVisibility(View.INVISIBLE);
                Obnyl();

                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_LEVEL, AvaLevel);

                editor.putInt(DATA_FILE_KOLRESH1_1, 0);
                editor.putInt(DATA_FILE_KOLRESH1_2, 0);
                editor.putInt(DATA_FILE_KOLRESH1_3, 0);

                editor.putInt(DATA_FILE_KOLNYSHN1_1, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_2, 5);
                editor.putInt(DATA_FILE_KOLNYSHN1_3, 5);

                editor.apply();
            }
        }
    }

    public void Obnyl(){
        TextView TextTe;
        TextTe = findViewById(R.id.textViewHint);
        TextTe.setVisibility(View.INVISIBLE);
        TextTe = findViewById(R.id.textViewAnswerCalculdop1);
        TextTe.setVisibility(View.INVISIBLE);
        TextTe = findViewById(R.id.textViewAnswerCalculdop2);
        TextTe.setVisibility(View.INVISIBLE);
        TextTe = findViewById(R.id.textViewAnswerCalculdop3);
        TextTe.setVisibility(View.INVISIBLE);
        TextTe = findViewById(R.id.textViewTypeIzmdop1);
        TextTe.setVisibility(View.INVISIBLE);
        TextTe = findViewById(R.id.textViewTypeIzmdop2);
        TextTe.setVisibility(View.INVISIBLE);
        TextTe = findViewById(R.id.textViewTypeIzmdop3);
        TextTe.setVisibility(View.INVISIBLE);
        TextTe = findViewById(R.id.textViewEnterAnswerdop1);
        TextTe.setVisibility(View.INVISIBLE);
        TextTe.setFocusableInTouchMode(false);
        TextTe = findViewById(R.id.textViewEnterAnswerdop2);
        TextTe.setVisibility(View.INVISIBLE);
        TextTe.setFocusableInTouchMode(false);
        TextTe = findViewById(R.id.textViewEnterAnswerdop3);
        TextTe.setVisibility(View.INVISIBLE);
        TextTe.setFocusableInTouchMode(false);
    }

    //------------------------------------------------------------

    public void FirstLevel(){
        if (AvaLevel==1) {
            AnswerPers.setFocusableInTouchMode(true);
            CorrAnswerCalc.setVisibility(View.INVISIBLE);

            if (isNewSeans){
                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_WRONG_NOW, 0);
                editor.putInt(DATA_FILE_CORRECT_NOW, 0);
                wronganswnow=0;
                corranswnow=0;
                editor.apply();
                isNewSeans = false;
            }

                do
                    randtype = 1 + (int) (Math.random() * 3);
                while ((randtype == 1 && compl1) || (randtype == 2 && compl2) || (randtype == 3 && compl3));
                if (randtype == 1) {
                    ZadPoiskYglaRazvorota();
                }
                if (randtype == 2) {
                    ZadPoiskVremeniRazvorota();
                }
                if (randtype == 3) {
                    ZadPoiskLYR();
                }
        }else
        {
            if (AvaLevel>1) {
                String s = getResources().getString(R.string.toastbiglvl);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }else{
                String s = getResources().getString(R.string.toastNotAvail);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void SecendLevel(){
        if (AvaLevel==2) {
            AnswerPers.setFocusableInTouchMode(true);
            CorrAnswerCalc.setVisibility(View.INVISIBLE);

            if (isNewSeans){
                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_WRONG_NOW, 0);
                editor.putInt(DATA_FILE_CORRECT_NOW, 0);
                wronganswnow=0;
                corranswnow=0;
                editor.apply();
                isNewSeans = false;
            }

            do
                randtype = 1 + (int) (Math.random() * 3);
            while ((randtype == 1 && compl1) || (randtype == 2 && compl2) || (randtype == 3 && compl3));
            if (randtype == 1) {
                ZadPoiskPytevoiSkorosti();
            }
            if (randtype == 2) {
                ZadPoiskSkorostVetra();
            }
            if (randtype == 3) {
                ZadPoiskNapravleniaVetra();
            }
        }
        else{
            if (AvaLevel>2) {
                String s = getResources().getString(R.string.toastbiglvl);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }else{
                String s = getResources().getString(R.string.toastNotAvail);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void ThirdLevel(){
        if (AvaLevel==3) {
            AnswerPers.setFocusableInTouchMode(true);
            CorrAnswerCalc.setVisibility(View.INVISIBLE);

            if (isNewSeans){
                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_WRONG_NOW, 0);
                editor.putInt(DATA_FILE_CORRECT_NOW, 0);
                wronganswnow=0;
                corranswnow=0;
                editor.apply();
                isNewSeans = false;
            }

            do
                randtype = 1 + (int) (Math.random() * 3);
            while ((randtype == 1 && compl1) || (randtype == 2 && compl2) || (randtype == 3 && compl3));
            if (randtype == 1) {
                ZadPoiskKyrsi();
            }
            if (randtype == 2) {
                ZadPoiskFIPY();
            }
            if (randtype == 3) {
                ZadPoiskKyrsiPoVetry();
            }
        }
        else{
            if (AvaLevel>3) {
                String s = getResources().getString(R.string.toastbiglvl);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }else{
                String s = getResources().getString(R.string.toastNotAvail);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void FourtLevel(){
        if (AvaLevel==4) {
            AnswerPers.setFocusableInTouchMode(true);
            CorrAnswerCalc.setVisibility(View.INVISIBLE);

            if (isNewSeans){
                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_WRONG_NOW, 0);
                editor.putInt(DATA_FILE_CORRECT_NOW, 0);
                wronganswnow=0;
                corranswnow=0;
                editor.apply();
                isNewSeans = false;
            }

            do
                randtype = 1 + (int) (Math.random() * 3);
            while ((randtype == 1 && compl1) || (randtype == 2 && compl2) || (randtype == 3 && compl3));
            if (randtype == 1) {
                ZadPoiskZadRybesha();
            }
            if (randtype == 2) {
                ZadPoiskZadRybeshaOgr();
            }
            if (randtype == 3) {
                ZadPoiskZadRybeshaIzmen();
            }
        }
        else{
            if (AvaLevel>4) {
                String s = getResources().getString(R.string.toastbiglvl);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }else{
                String s = getResources().getString(R.string.toastNotAvail);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void FifthLevel(){
        if (AvaLevel==5) {
            AnswerPers.setFocusableInTouchMode(true);
            CorrAnswerCalc.setVisibility(View.INVISIBLE);

            if (isNewSeans){
                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_WRONG_NOW, 0);
                editor.putInt(DATA_FILE_CORRECT_NOW, 0);
                wronganswnow=0;
                corranswnow=0;
                editor.apply();
                isNewSeans = false;
            }

            do
                randtype = 1 + (int) (Math.random() * 3);
            while ((randtype == 1 && compl1) || (randtype == 2 && compl2) || (randtype == 3 && compl3));
            if (randtype == 1) {
                ZadPoiskIstenSkorost();
            }
            if (randtype == 2) {
                ZadPoiskPriborSkorost();
            }
            if (randtype == 3) {
                ZadPoiskIstenSkorostKYS();
            }
        }
        else{
            if (AvaLevel>5) {
                String s = getResources().getString(R.string.toastbiglvl);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }else{
                String s = getResources().getString(R.string.toastNotAvail);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void SixLevel(){
        if (AvaLevel==6) {
            AnswerPers.setFocusableInTouchMode(true);
            CorrAnswerCalc.setVisibility(View.INVISIBLE);

            if (isNewSeans){
                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putInt(DATA_FILE_WRONG_NOW, 0);
                editor.putInt(DATA_FILE_CORRECT_NOW, 0);
                wronganswnow=0;
                corranswnow=0;
                editor.apply();
                isNewSeans = false;
            }

            do
                randtype = 1 + (int) (Math.random() * 3);
            while ((randtype == 1 && compl1) || (randtype == 2 && compl2)|| (randtype == 3 && compl3));
            if (randtype == 1) {
                ZadPoiskIsprPutLBYmB();
            }
            if (randtype == 2) {
                ZadPoiskIsprPutLBYbB();
            }
            if (randtype == 3) {
                ZadPoiskIsprPutLBYbB2();
            }
        }
        else{
            if (AvaLevel>6) {
                String s = getResources().getString(R.string.toastbiglvl);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }else{
                String s = getResources().getString(R.string.toastNotAvail);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    //------------------------------------------------------------

    public void ZadPoiskYglaRazvorota(){
        Obnyl();
        typeoftaskglob=1;
        int V;
        byte x;
        diap = 0.4;

        V=100+(int)(Math.random()*800);
        x=(byte)(5+(int)(Math.random()*40));
        AnswerCalc = new RadRazv(V,x).calculate();
        AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_km);
        TypeIzm.setText(R.string.typeizm_km);
        ConditionTask.setText(getResources().getString(R.string.CondSkorRad)+" = "+V+getResources().getString(R.string.typeizm_kmch)+"\n"+getResources().getString(R.string.Condkren)+" = "+x+getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CondTask1));
    }
    public void ZadPoiskVremeniRazvorota(){
        Obnyl();
        typeoftaskglob=2;
        int V;
        byte x;
        int YR;
        double Rad;
        diap = 3;

        V=100+(int)(Math.random()*800);
        x=(byte)(5+(int)(Math.random()*40));
        YR=1+(int)(Math.random()*180);
        Rad = new RadRazv(V,x).calculate();
        AnswerCalc = new VremyaRazvor(Rad,YR,V).calculate();
        AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_s);
        TypeIzm.setText(R.string.typeizm_s);
        ConditionTask.setText(getResources().getString(R.string.CondSkorRad)+" = "+V+getResources().getString(R.string.typeizm_kmch)+"\n"+getResources().getString(R.string.Condkren)+" = "+x+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondYR) +" = "+YR+getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CondTask2));
    }
    public void ZadPoiskLYR(){
        Obnyl();
        typeoftaskglob=3;
        int V;
        byte x;
        int YR;
        double Rad;
        diap = 0.4;

        V=100+(int)(Math.random()*800);
        x=(byte)(5+(int)(Math.random()*40));
        YR=1+(int)(Math.random()*180);
        Rad = new RadRazv(V,x).calculate();
        AnswerCalc = new LYRRazvr(Rad,YR,V).calculate();
        AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_km);
        TypeIzm.setText(R.string.typeizm_km);
        ConditionTask.setText(getResources().getString(R.string.CondSkorRad)+" = "+V+getResources().getString(R.string.typeizm_kmch)+"\n"+getResources().getString(R.string.Condkren)+" = "+x+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondYR) +" = "+YR+getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CondTask3));
    }

    public void ZadPoiskPytevoiSkorosti(){
        if (AvaLevel>=2) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewTypeIzmdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewTypeIzmdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewTypeIzmdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasBen));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasYV));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasYS));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            typeoftaskglob=4;
            int ZMPY;
            int Bem;
            int V;
            int U;
            int dM;

            int YV;
            double YS;
            int Ben;

            V=100+(int)(Math.random()*800);
            ZMPY=(int)(Math.random()*360);
            Bem=(int)(Math.random()*360);
            U=(int)(Math.random()*200);
            dM=(-15)+(int)(Math.random()*31);
            diap = 10;

            Ben = new Ben_p(Bem,dM).calculate();
            YV = new YV_p(Ben, ZMPY).calculate();
            YS=new YS_p(U,V,YV).calculate();
            AnswerCalc = new W_p(V,U,YV,YS).calculate();
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_kmch);
            TypeIzm.setText(R.string.typeizm_kmch);
            ConditionTask.setText(getResources().getString(R.string.CondZMPY)+" = "+ZMPY+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondNapVetrMe)+" = "+Bem+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondSkorRad)+" = "+V+getResources().getString(R.string.typeizm_kmch)+"\n"+getResources().getString(R.string.CondSkorVetr)+" = "+U+getResources().getString(R.string.typeizm_kmch)+"\n"+getResources().getString(R.string.CondMagSkl)+" = "+dM+getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CondTask4));
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskSkorostVetra(){
        if (AvaLevel>=2) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewTypeIzmdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_kmch));
            TextTe = findViewById(R.id.textViewTypeIzmdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasUekv));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasYV));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            typeoftaskglob=5;
            int V;
            double YS;
            int W;

            V=100+(int)(Math.random()*800);
            YS=(-15)+(int)(Math.random()*31);
            W=100+(int)(Math.random()*900);
            diap = 8;

            AnswerCalc = new U_p(W,V,YS).calculate();
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_kmch);
            TypeIzm.setText(R.string.typeizm_kmch);
            ConditionTask.setText(getResources().getString(R.string.CondSkorRad)+" = "+V+getResources().getString(R.string.typeizm_kmch)+"\n"+getResources().getString(R.string.CondPytSkor)+" = "+W+getResources().getString(R.string.typeizm_kmch)+"\n" +getResources().getString(R.string.CondYS)+" = "+YS+getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CondTask5));
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskNapravleniaVetra(){
        if (AvaLevel>=2) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewTypeIzmdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_kmch));
            TextTe = findViewById(R.id.textViewTypeIzmdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewTypeIzmdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasUekv));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasYV));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasBen));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            typeoftaskglob=6;
            int V;
            int W;
            int YS;
            int MK;
            int dM;
            double YV;
            double U;
            double Ben;

            V=100+(int)(Math.random()*800);
            YS=(-15)+(int)(Math.random()*31);
            W=100+(int)(Math.random()*900);
            dM=(-15)+(int)(Math.random()*31);
            MK=(int)(Math.random()*360);
            diap = 8;
            AnswCalcDop1=W-V;
            U=Math.sqrt(V*V+W*W-2*V*W*Math.cos(Math.toRadians(YS)));
            YV=Math.toDegrees(Math.asin(V*Math.sin(Math.toRadians(YS))/U));
            AnswCalcDop2=YV;
            if (W>=V){
                Ben=MK+YS+YV+ dM;
            }
            else{
                Ben=MK+YS-YV+ dM;
                if (Ben>=0){
                    Ben=Ben-180;
                }
                else
                {
                    Ben=Ben+180;
                }
            }

            if (Ben<180) {
                AnswerCalc = Ben + 180;
            }
            else {
                AnswerCalc = Ben - 180;
            }
            AnswCalcDop3=Ben;
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_grad);
            TypeIzm.setText(R.string.typeizm_grad);
            ConditionTask.setText(getResources().getString(R.string.CondSkorRad)+" = "+V+getResources().getString(R.string.typeizm_kmch)+"\n"+getResources().getString(R.string.CondPytSkor)+" = "+W+getResources().getString(R.string.typeizm_kmch)+"\n" +getResources().getString(R.string.CondYS)+" = "+YS+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondMagKyr)+" = " + MK + getResources().getString(R.string.typeizm_grad)+"\n" +getResources().getString(R.string.CondMagSkl)+" = " + dM + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CondTask6));
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void ZadPoiskKyrsi(){
        if (AvaLevel>=3) {
            Obnyl();
            typeoftaskglob=7;
            int typeoftask=(int)(Math.random()*9);

            int KK=(int)(Math.random()*360);
            int MK = (int)((Math.random()*100) + KK-50);
            MK = (int)makeYgol0_360(MK);
            int IK = (int)((Math.random()*30) + MK-15);
            IK = (int)makeYgol0_360(IK);
            int OK = (int)((Math.random()*40) + IK-20);
            OK = (int)makeYgol0_360(OK);

            int dK=MK-KK;
            dK = (int)makeYgol180_180(dK);
            int dM=IK-MK;
            dM = (int)makeYgol180_180(dM);
            int dA=OK-IK;
            dA = (int)makeYgol180_180(dA);
            int d=dK+dM;
            d = (int)makeYgol180_180(d);
            int dMy=dM+dA;
            dMy = (int)makeYgol180_180(dMy);

            String s="";
            diap = 0;

            if (typeoftask==0){
                s = getResources().getString(R.string.CondKomKyr)+" = "+KK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondIstKyr)+" = "+IK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondKomSkl)+" = "+dK+getResources().getString(R.string.typeizm_grad)+"\n";
                s=s+getResources().getString(R.string.CondTask7_1);
                AnswerCalc = IK-KK-dK;
            }
            if (typeoftask==1){
                s = getResources().getString(R.string.CondKomKyr)+" = "+KK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondKomSkl)+" = "+dK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondMagSkl)+" = "+dM+getResources().getString(R.string.typeizm_grad)+"\n";
                s=s+getResources().getString(R.string.CondTask7_2i4);
                AnswerCalc = KK+dM+dK;
                if (AnswerCalc<0){
                    AnswerCalc=AnswerCalc+360;
                }
            }
            if (typeoftask==2){
                s = getResources().getString(R.string.CondMagKyr)+" = "+MK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondIstKyr)+" = "+IK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondMysSkl)+" = "+dMy+getResources().getString(R.string.typeizm_grad)+"\n";
                s=s+getResources().getString(R.string.CondTask7_3i6);
                AnswerCalc = MK+dMy;
                if (AnswerCalc<0){
                    AnswerCalc=AnswerCalc+360;
                }
            }
            if (typeoftask==3){
                s = getResources().getString(R.string.CondOrtKyr)+" = "+OK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondMagSkl)+" = "+dM+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondMysSkl)+" = "+dMy+getResources().getString(R.string.typeizm_grad)+"\n";
                s=s+getResources().getString(R.string.CondTask7_2i4);
                AnswerCalc = OK-dMy+dM;
                if (AnswerCalc<0){
                    AnswerCalc=AnswerCalc+360;
                }
            }
            if (typeoftask==4){
                s = getResources().getString(R.string.CondKomKyr)+" = "+KK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondMagSkl)+" = "+dM+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondVarSkl)+" = "+d+getResources().getString(R.string.typeizm_grad)+"\n";
                s=s+getResources().getString(R.string.CondTask7_5);
                AnswerCalc = KK+d-dM;
                if (AnswerCalc<0){
                    AnswerCalc=AnswerCalc+360;
                }
            }
            if (typeoftask==5){
                s = getResources().getString(R.string.CondKomKyr)+" = "+KK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondMysSkl)+" = "+dMy+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondMagSkl)+" = "+dM+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondVarSkl)+" = "+d+getResources().getString(R.string.typeizm_grad)+"\n";
                s=s+getResources().getString(R.string.CondTask7_3i6);
                AnswerCalc = KK+d-dM+dMy;
                if (AnswerCalc<0){
                    AnswerCalc=AnswerCalc+360;
                }
            }
            if (typeoftask==6){
                s = getResources().getString(R.string.CondMagKyr)+" = "+MK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondIstKyr)+" = "+IK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondVarSkl)+" = "+d+getResources().getString(R.string.typeizm_grad)+"\n";
                s=s+getResources().getString(R.string.CondTask7_7);
                AnswerCalc = IK-d;
            }
            if (typeoftask==7){
                s = getResources().getString(R.string.CondMagKyr)+" = "+MK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondIstKyr)+" = "+IK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondKomSkl)+" = "+dK+getResources().getString(R.string.typeizm_grad)+"\n";
                s=s+getResources().getString(R.string.CondTask7_8);
                AnswerCalc = IK-MK+dK;
            }
            if (typeoftask==8){
                s = getResources().getString(R.string.CondOrtKyr)+" = "+OK+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondVarSkl)+" = "+d+getResources().getString(R.string.typeizm_grad)+"\n";
                s = s+getResources().getString(R.string.CondAziSkl)+" = "+dA+getResources().getString(R.string.typeizm_grad)+"\n";
                s=s+getResources().getString(R.string.CondTask7_9);
                AnswerCalc = OK-dA-d;
                if (AnswerCalc<0){
                    AnswerCalc=AnswerCalc+360;
                }
            }

            if (AnswerCalc>=360){
                AnswerCalc=AnswerCalc-360;
            }
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_grad);
            TypeIzm.setText(R.string.typeizm_grad);
            ConditionTask.setText(s);
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskFIPY(){
        if (AvaLevel>=3) {
            Obnyl();
            typeoftaskglob=8;
            int typeoftask=(int)(Math.random()*2);
            int typeoftask2=(int)(Math.random()*3);

            int IK=(int)(Math.random()*360);
            int MK=(int)(Math.random()*360);
            int dM=(-15)+(int)(Math.random()*31);

            String s="";

            if (typeoftask==0){
                diap = 0;
                if (typeoftask2==0) {
                    int YS = (-15) + (int) (Math.random() * 31);
                    s = getResources().getString(R.string.CondIstKyr)+" = " + IK + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondMagSkl)+" = " + dM + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondYS)+" = " + YS + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondTask8_1i5i6);
                    AnswerCalc = IK - dM + YS;
                    if (AnswerCalc>=360){
                        AnswerCalc=AnswerCalc-360;
                    }
                    if (AnswerCalc<0){
                        AnswerCalc=AnswerCalc+360;
                    }
                }
                if (typeoftask2==1) {
                    int YS = (-15) + (int) (Math.random() * 31);
                    s = getResources().getString(R.string.CondIstKyr)+" = " + IK + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondMagSkl)+" = " + dM + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondYS)+" = " + YS + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondTask8_2i3i4);
                    AnswerCalc = IK+YS;
                    if (AnswerCalc>=360){
                        AnswerCalc=AnswerCalc-360;
                    }
                    if (AnswerCalc<0){
                        AnswerCalc=AnswerCalc+360;
                    }
                }
                if (typeoftask2==2) {
                    int YS = (-15) + (int) (Math.random() * 31);
                    s = getResources().getString(R.string.CondMagKyr)+" = " + MK + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondMagSkl)+" = " + dM + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondYS)+" = " + YS + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondTask8_2i3i4);
                    AnswerCalc = MK+dM+YS;
                    if (AnswerCalc>=360){
                        AnswerCalc=AnswerCalc-360;
                    }
                    if (AnswerCalc<0){
                        AnswerCalc=AnswerCalc+360;
                    }
                }
            }else{
                diap = 1;
                double YS;
                if (typeoftask2==0) {
                    int ZMPY = (int) (Math.random() * 360);
                    double S = 1+(int) (Math.random() * 30);
                    double LBY = (-5)+(int) (Math.random() * 11);
                    s = getResources().getString(R.string.CondZMPY)+" = " + ZMPY + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondMagSkl)+" = " + dM + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondMagKyr)+" = " + MK + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondLBY)+" = " + LBY + getResources().getString(R.string.typeizm_km)+"\n";
                    s = s + getResources().getString(R.string.CondSpr)+" = " + S + getResources().getString(R.string.typeizm_km)+"\n";
                    s = s + getResources().getString(R.string.CondTask8_2i3i4);
                    YS = ZMPY-MK+Math.toDegrees(Math.atan(LBY/S));
                    AnswerCalc = MK+dM+YS;
                    if (AnswerCalc>=360){
                        AnswerCalc=AnswerCalc-360;
                    }
                    if (AnswerCalc<0){
                        AnswerCalc=AnswerCalc+360;
                    }
                }
                if (typeoftask2==1) {
                    int ZIPY = (int) (Math.random() * 360);
                    double S = 1+(int) (Math.random() * 30);
                    double LBY = (-5)+(int) (Math.random() * 11);
                    s = getResources().getString(R.string.CondZIPY)+" = " + ZIPY + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondMagSkl)+" = " + dM + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondIstKyr)+" = " + IK + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondLBY)+" = " + LBY + getResources().getString(R.string.typeizm_km)+"\n";
                    s = s + getResources().getString(R.string.CondSpr)+" = " + S + getResources().getString(R.string.typeizm_km)+"\n";
                    s = s + getResources().getString(R.string.CondTask8_1i5i6);
                    YS = ZIPY-IK+Math.toDegrees(Math.atan(LBY/S));
                    AnswerCalc = IK-dM+YS;
                    if (AnswerCalc>=360){
                        AnswerCalc=AnswerCalc-360;
                    }
                    if (AnswerCalc<0){
                        AnswerCalc=AnswerCalc+360;
                    }
                }
                if (typeoftask2==2) {
                    int ZMPY = (int) (Math.random() * 360);
                    double S = 1+(int) (Math.random() * 30);
                    double LBY = (-5)+(int) (Math.random() * 11);
                    s = getResources().getString(R.string.CondZMPY)+" = " + ZMPY + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondMagSkl)+" = " + dM + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondMagKyr)+" = " + MK + getResources().getString(R.string.typeizm_grad)+"\n";
                    s = s + getResources().getString(R.string.CondLBY)+" = " + LBY + getResources().getString(R.string.typeizm_km)+"\n";
                    s = s + getResources().getString(R.string.CondSpr)+" = " + S + getResources().getString(R.string.typeizm_km)+"\n";
                    s = s + getResources().getString(R.string.CondTask8_1i5i6);
                    YS = ZMPY-MK+Math.toDegrees(Math.atan(LBY/S));
                    AnswerCalc = MK+YS;
                    if (AnswerCalc>=360){
                        AnswerCalc=AnswerCalc-360;
                    }
                    if (AnswerCalc<0){
                        AnswerCalc=AnswerCalc+360;
                    }
                }
            }

            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_grad);
            TypeIzm.setText(R.string.typeizm_grad);
            ConditionTask.setText(s);
        }else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskKyrsiPoVetry(){
        if (AvaLevel>=3) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewTypeIzmdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewTypeIzmdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasYV));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasYS));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            typeoftaskglob=9;
            diap=3;
            int YV;
            double YS;
            int Ben;
            String s="";
            int V=100+(int)(Math.random()*800);
            int ZIPY=(int)(Math.random()*360);
            int Bem=(int)(Math.random()*360);
            int U=(int)(Math.random()*200);
            int dM=(-15)+(int)(Math.random()*31);
            s = getResources().getString(R.string.CondZIPY)+" = " + ZIPY + getResources().getString(R.string.typeizm_grad)+"\n";
            s = s + getResources().getString(R.string.CondMagSkl)+" = " + dM + getResources().getString(R.string.typeizm_grad)+"\n";
            s = s + getResources().getString(R.string.CondNapVetrMe)+" = " + Bem + getResources().getString(R.string.typeizm_grad)+"\n";
            s = s + getResources().getString(R.string.CondSkorRad)+" = " + V + getResources().getString(R.string.typeizm_kmch)+"\n";
            s = s + getResources().getString(R.string.CondSkorVetr)+" = " + U + getResources().getString(R.string.typeizm_kmch)+"\n";
            s = s + getResources().getString(R.string.CondTask9);

            Ben = Bem-dM;
            if (Ben>=180){Ben=Ben-180;}else{Ben=Ben+180;}
            YV = Ben - ZIPY+dM;
            if (YV>180){YV=YV-360;}
            if (YV<=-180){YV=YV+360;}
            AnswCalcDop1=YV;
            double Ali = U*Math.sin(Math.toRadians(YV))/V;
            YS=Math.toDegrees(Math.asin(Ali));
            AnswCalcDop1=YS;
            AnswerCalc = ZIPY-dM-YS;
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_grad);
            TypeIzm.setText(R.string.typeizm_grad);
            ConditionTask.setText(s);
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void ZadPoiskZadRybesha(){
        if (AvaLevel>=4) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasHbar));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasdH));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprastpr));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            typeoftaskglob=10;
            String s;
            int Pmm;
            int Hesh;
            int Hkr;
            int Vv;
            int W;

            int dH;
            int Hbar;
            double tna;

            int PmmORPgpa=(int)(Math.random()*2);

            if (PmmORPgpa==0){
                Hesh =100*(10+(int)(Math.random()*110));
                s = getResources().getString(R.string.CondHesh)+" = "+Hesh+getResources().getString(R.string.typeizm_m)+"\n";
                Hkr =100*(1+(int)(Math.random()*7));
                s=s+getResources().getString(R.string.CondHkr)+" = "+Hkr+getResources().getString(R.string.typeizm_m)+"\n";
                Pmm=700+(int)(Math.random()*80);
                s = s+getResources().getString(R.string.CondPaer)+" = "+Pmm+getResources().getString(R.string.typeizm_mmrtst)+"\n";
                Vv=5+(int)(Math.random()*10);
                s = s+getResources().getString(R.string.CondVvert)+" = "+Vv+getResources().getString(R.string.typeizm_mc)+"\n";
                W=200+(int)(Math.random()*700);
                s = s+getResources().getString(R.string.CondPytSkor)+" = "+W+getResources().getString(R.string.typeizm_kmch)+"\n";

                Hbar=(760-Pmm)*11;
                AnswCalcDop1=Hbar;
                dH=Hesh-Hkr-Hbar;
                AnswCalcDop2=dH;
                tna=(double)dH/Vv;
                AnswCalcDop3=tna;
                AnswerCalc = W*tna/3600;
                diap = 0.4;
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_km);
                TypeIzm.setText(R.string.typeizm_km);
                ConditionTask.setText(s+getResources().getString(R.string.CondTask10_1));
            }else{
                TextTe = findViewById(R.id.textViewTypeIzmdop1);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_fyt));
                TextTe = findViewById(R.id.textViewTypeIzmdop2);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_fyt));
                TextTe = findViewById(R.id.textViewTypeIzmdop3);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_min));
                Hesh =1000*(3+(int)(Math.random()*29));
                s = getResources().getString(R.string.CondHesh)+" = "+Hesh+getResources().getString(R.string.typeizm_fyt)+"\n";
                Hkr =100*(1+(int)(Math.random()*7));
                s=s+getResources().getString(R.string.CondHkr)+" = "+Hkr+getResources().getString(R.string.typeizm_m)+"\n";
                Pmm=950+(int)(Math.random()*80);
                s = s+getResources().getString(R.string.CondPaer)+" = "+Pmm+getResources().getString(R.string.typeizm_gpa)+"\n";
                Vv=100*(10+(int)(Math.random()*19));
                s = s+getResources().getString(R.string.CondVvert)+" = "+Vv+getResources().getString(R.string.typeizm_fytmin)+"\n";
                W=200+(int)(Math.random()*100);
                s = s+getResources().getString(R.string.CondPytSkor)+" = "+W+getResources().getString(R.string.typeizm_yzl)+"\n";

                Hbar=(1013-Pmm)*27;
                AnswCalcDop1=Hbar;
                dH=Hesh-(int)(Hkr*3.28)-Hbar;
                AnswCalcDop2=dH;
                tna=(double)dH/Vv;
                AnswCalcDop3=tna;
                AnswerCalc = W*tna/60;
                diap = 0.8;
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_mmile);
                TypeIzm.setText(R.string.typeizm_mmile);
                ConditionTask.setText(s+getResources().getString(R.string.CondTask10_2));
            }
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskZadRybeshaOgr(){
        if (AvaLevel>=4) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasdH1));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasHbar));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasdH2));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            typeoftaskglob=11;
            String s;
            int Pmm;
            int Hesh;
            int Hkr;
            int Vv1;
            int W1;
            int Vv2;
            int W2;
            int H1;

            int dH1;
            int dH2;
            int Hbar;
            double tna1;
            double tna2;
            double S1;
            double S2;

            int PmmORPgpa=(int)(Math.random()*2);

            if (PmmORPgpa==0){
                TextTe = findViewById(R.id.textViewTypeIzmdop1);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_m));
                TextTe = findViewById(R.id.textViewTypeIzmdop2);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_m));
                TextTe = findViewById(R.id.textViewTypeIzmdop3);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_m));
                Hesh =100*(32+(int)(Math.random()*88));
                H1 =100*(29+(int)(Math.random()*3));
                s = getResources().getString(R.string.CondHesh)+" = "+Hesh+getResources().getString(R.string.typeizm_m)+"\n";
                s = s+getResources().getString(R.string.CondH1)+" = "+H1+getResources().getString(R.string.typeizm_m)+"\n";
                Hkr =100*(1+(int)(Math.random()*7));
                s=s+getResources().getString(R.string.CondHkr)+" = "+Hkr+getResources().getString(R.string.typeizm_m)+"\n";
                Pmm=700+(int)(Math.random()*80);
                s = s+getResources().getString(R.string.CondPaer)+" = "+Pmm+getResources().getString(R.string.typeizm_mmrtst)+"\n";
                Vv1=10+(int)(Math.random()*5);
                s = s+getResources().getString(R.string.CondVvert1)+" = "+Vv1+getResources().getString(R.string.typeizm_mc)+"\n";
                W1=500+(int)(Math.random()*400);
                s = s+getResources().getString(R.string.CondW1)+" = "+W1+getResources().getString(R.string.typeizm_kmch)+"\n";
                Vv2=5+(int)(Math.random()*5);
                s = s+getResources().getString(R.string.CondVvert2)+" = "+Vv2+getResources().getString(R.string.typeizm_mc)+"\n";
                W2=200+(int)(Math.random()*300);
                s = s+getResources().getString(R.string.CondW2)+" = "+W2+getResources().getString(R.string.typeizm_kmch)+"\n";

                Hbar=(760-Pmm)*11;
                AnswCalcDop2=Hbar;
                dH1=Hesh-H1;
                AnswCalcDop1=dH1;
                tna1=(double)dH1/Vv1;
                S1=W1*tna1/3600;
                dH2=H1-Hkr-Hbar;
                AnswCalcDop3=dH2;
                tna2=(double)dH2/Vv2;
                S2=W2*tna2/3600;
                AnswerCalc = S1+S2;
                diap = 0.4;
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_km);
                TypeIzm.setText(R.string.typeizm_km);
                ConditionTask.setText(s+getResources().getString(R.string.CondTask11_1));
            }else{
                TextTe = findViewById(R.id.textViewTypeIzmdop1);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_fyt));
                TextTe = findViewById(R.id.textViewTypeIzmdop2);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_fyt));
                TextTe = findViewById(R.id.textViewTypeIzmdop3);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_fyt));
                Hesh =1000*(12+(int)(Math.random()*20));
                H1 =1000*(9+(int)(Math.random()*3));
                s = getResources().getString(R.string.CondHesh)+" = "+Hesh+getResources().getString(R.string.typeizm_fyt)+"\n";
                s = s+getResources().getString(R.string.CondH1)+" = "+H1+getResources().getString(R.string.typeizm_fyt)+"\n";
                Hkr =100*(1+(int)(Math.random()*7));
                s=s+getResources().getString(R.string.CondHkr)+" = "+Hkr+getResources().getString(R.string.typeizm_m)+"\n";
                Pmm=950+(int)(Math.random()*80);
                s = s+getResources().getString(R.string.CondPaer)+" = "+Pmm+getResources().getString(R.string.typeizm_gpa)+"\n";
                Vv1=100*(20+(int)(Math.random()*9));
                s = s+getResources().getString(R.string.CondVvert1)+" = "+Vv1+getResources().getString(R.string.typeizm_fytmin)+"\n";
                W1=250+(int)(Math.random()*50);
                s = s+getResources().getString(R.string.CondW1)+" = "+W1+getResources().getString(R.string.typeizm_yzl)+"\n";
                Vv2=100*(10+(int)(Math.random()*10));
                s = s+getResources().getString(R.string.CondVvert2)+" = "+Vv2+getResources().getString(R.string.typeizm_fytmin)+"\n";
                W2=200+(int)(Math.random()*50);
                s = s+getResources().getString(R.string.CondW2)+" = "+W2+getResources().getString(R.string.typeizm_yzl)+"\n";

                Hbar=(1013-Pmm)*27;
                AnswCalcDop2=Hbar;
                dH1=Hesh-H1;
                AnswCalcDop1=dH1;
                tna1=(double)dH1/Vv1;
                S1=W1*tna1/60;
                dH2=H1-Hkr*3-Hbar;
                AnswCalcDop3=dH2;
                tna2=(double)dH2/Vv2;
                S2=W2*tna2/60;
                AnswerCalc = S1+S2;
                diap = 0.8;
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_mmile);
                TypeIzm.setText(R.string.typeizm_mmile);
                ConditionTask.setText(s+getResources().getString(R.string.CondTask11_2));
            }
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskZadRybeshaIzmen(){
        if (AvaLevel>=4) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasSnach));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasdHsht));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasSrasp));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            typeoftaskglob=12;
            String s;
            int Hzad;
            int Szad;
            int Hper;

            int SloshORProst=(int)(Math.random()*2);
            int PmmORPgpa=(int)(Math.random()*2);

            if (PmmORPgpa==0){
                Hzad = 100 * (10 + (int) (Math.random() * 30));
                Hper = 100 * (10 + (int) (Math.random() * 10));
                TextTe = findViewById(R.id.textViewTypeIzmdop1);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_km));
                TextTe = findViewById(R.id.textViewTypeIzmdop2);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_m));
                TextTe = findViewById(R.id.textViewTypeIzmdop3);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_km));

                if (SloshORProst==0) {
                    int Pmm;
                    int Hesh;
                    int Hkr;
                    int Vv;
                    int W;

                    int dH;
                    int Hbar;
                    double tna;
                    int dHz;
                    double S;
                    double Sras;
                    double tras;

                    Hesh = 100 * (40 + (int) (Math.random() * 80));
                    s = getResources().getString(R.string.CondHesh)+" = " + Hesh + getResources().getString(R.string.typeizm_m)+"\n";
                    Hkr = 100 * (1 + (int) (Math.random() * 7));
                    s = s + getResources().getString(R.string.CondHkr)+" = " + Hkr + getResources().getString(R.string.typeizm_m)+"\n";
                    Pmm = 700 + (int) (Math.random() * 80);
                    s = s + getResources().getString(R.string.CondPaer)+" = " + Pmm + getResources().getString(R.string.typeizm_mmrtst)+"\n";
                    Vv = 5 + (int) (Math.random() * 10);
                    s = s + getResources().getString(R.string.CondVvert)+" = " + Vv + getResources().getString(R.string.typeizm_mc)+"\n";
                    W = 200 + (int) (Math.random() * 700);
                    s = s + getResources().getString(R.string.CondPytSkor)+" = " + W + getResources().getString(R.string.typeizm_kmch)+"\n";

                    Hbar = (760 - Pmm) * 11;
                    dH = Hesh - Hkr - Hbar;
                    tna = (double)dH / Vv;
                    S = W * tna / 3600;
                    AnswCalcDop1=S;
                    if (Hzad>=Hper){
                        dHz=Hesh-Hzad;
                    }else{
                        dHz=Hesh-Hzad-Hbar;
                    }
                    AnswCalcDop2=dHz;
                    do
                        Szad = 1 + (int) (Math.random() * 100);
                    while (S-15<=Szad);
                    Sras=S-Szad;
                    AnswCalcDop3=Sras;
                    tras=3600*(Sras/W);
                    AnswerCalc = dHz/tras;
                    diap = 2;
                    s=s+getResources().getString(R.string.CondHzad)+" = " + Hzad + getResources().getString(R.string.typeizm_m)+"\n";
                    s=s+getResources().getString(R.string.CondHper)+" = " + Hper + getResources().getString(R.string.typeizm_m)+"\n";
                    s=s+getResources().getString(R.string.CondSzad)+" = " + Szad + getResources().getString(R.string.typeizm_km)+"\n";
                    AnswerText = (int)AnswerCalc + getResources().getString(R.string.typeizm_mc);
                    TypeIzm.setText(R.string.typeizm_mc);
                    ConditionTask.setText(s + getResources().getString(R.string.CondTask12_1i2));
                }else{
                    int Pmm;
                    int Hesh;
                    int Hkr;
                    int Vv1;
                    int W1;
                    int Vv2;
                    int W2;
                    int H1;

                    int dH1;
                    int dH2;
                    int Hbar;
                    double tna1;
                    double tna2;
                    double S1;
                    double S2;
                    int dHz=0;
                    double S;
                    double S2ras=0;
                    double t2ras;
                    double Sras;
                    double tras;

                    Hesh =100*(40+(int)(Math.random()*80));
                    H1 =100*(29+(int)(Math.random()*3));
                    s = getResources().getString(R.string.CondHesh)+" = "+Hesh+getResources().getString(R.string.typeizm_m)+"\n";
                    s = s+getResources().getString(R.string.CondH1)+" = "+H1+getResources().getString(R.string.typeizm_m)+"\n";
                    Hkr =100*(1+(int)(Math.random()*7));
                    s=s+getResources().getString(R.string.CondHkr)+" = "+Hkr+getResources().getString(R.string.typeizm_m)+"\n";
                    Pmm=700+(int)(Math.random()*80);
                    s = s+getResources().getString(R.string.CondPaer)+" = "+Pmm+getResources().getString(R.string.typeizm_mmrtst)+"\n";
                    Vv1=10+(int)(Math.random()*5);
                    s = s+getResources().getString(R.string.CondVvert1)+" = "+Vv1+getResources().getString(R.string.typeizm_mc)+"\n";
                    W1=500+(int)(Math.random()*400);
                    s = s+getResources().getString(R.string.CondW1)+" = "+W1+getResources().getString(R.string.typeizm_kmch)+"\n";
                    Vv2=5+(int)(Math.random()*5);
                    s = s+getResources().getString(R.string.CondVvert2)+" = "+Vv2+getResources().getString(R.string.typeizm_mc)+"\n";
                    W2=200+(int)(Math.random()*300);
                    s = s+getResources().getString(R.string.CondW2)+" = "+W2+getResources().getString(R.string.typeizm_kmch)+"\n";

                    Hbar=(760-Pmm)*11;
                    dH1=Hesh-H1;
                    tna1=(double)dH1/Vv1;
                    S1=W1*tna1/3600;
                    dH2=H1-Hkr-Hbar;
                    tna2=(double)dH2/Vv2;
                    S2=W2*tna2/3600;
                    S=S1+S2;
                    AnswCalcDop1=S;
                    if (Hzad<H1) {
                        if (Hzad >= Hper) {
                            dHz = H1 - Hzad;
                        } else {
                            dHz = H1 - Hzad - Hbar;
                        }
                        t2ras=(double)dHz/Vv2;
                        S2ras=W2*t2ras/3600;
                    }
                    AnswCalcDop2=dHz;
                    do
                        Szad = 1 + (int) (Math.random() * 100);
                    while (S-15-S2ras<=Szad);
                    Sras=S-Szad-S2ras;
                    AnswCalcDop3=Sras;
                    tras=3600*(Sras/W1);
                    AnswerCalc = dH1/tras;
                    diap = 2;
                    s=s+getResources().getString(R.string.CondHzad)+" = " + Hzad + getResources().getString(R.string.typeizm_m)+"\n";
                    s=s+getResources().getString(R.string.CondHper)+" = " + Hper + getResources().getString(R.string.typeizm_m)+"\n";
                    s=s+getResources().getString(R.string.CondSzad)+" = " + Szad + getResources().getString(R.string.typeizm_km)+"\n";
                    AnswerText = (int)AnswerCalc + getResources().getString(R.string.typeizm_mc);
                    TypeIzm.setText(R.string.typeizm_mc);
                    ConditionTask.setText(s + getResources().getString(R.string.CondTask12_1i2));
                }
            }else{
                Hzad = 1000 * (4 + (int) (Math.random() * 6));
                Hper = 1000 * (4 + (int) (Math.random() * 3));
                TextTe = findViewById(R.id.textViewTypeIzmdop1);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_mmile));
                TextTe = findViewById(R.id.textViewTypeIzmdop2);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_fyt));
                TextTe = findViewById(R.id.textViewTypeIzmdop3);
                TextTe.setVisibility(View.VISIBLE);
                TextTe.setText(getResources().getString(R.string.typeizm_mmile));

                if (SloshORProst==0) {
                    int Pmm;
                    int Hesh;
                    int Hkr;
                    int Vv;
                    int W;

                    int dH;
                    int Hbar;
                    double tna;
                    int dHz;
                    double S;
                    double Sras;
                    double tras;

                    Hesh = 1000 * (11 + (int) (Math.random() * 21));
                    s = getResources().getString(R.string.CondHesh)+" = " + Hesh + getResources().getString(R.string.typeizm_fyt)+"\n";
                    Hkr = 100 * (1 + (int) (Math.random() * 7));
                    s = s + getResources().getString(R.string.CondHkr)+" = " + Hkr + getResources().getString(R.string.typeizm_m)+"\n";
                    Pmm = 950 + (int) (Math.random() * 80);
                    s = s + getResources().getString(R.string.CondPaer)+" = " + Pmm + getResources().getString(R.string.typeizm_gpa)+"\n";
                    Vv = 100 * (10 + (int) (Math.random() * 19));
                    s = s + getResources().getString(R.string.CondVvert)+" = " + Vv + getResources().getString(R.string.typeizm_fytmin)+"\n";
                    W = 200 + (int) (Math.random() * 100);
                    s = s + getResources().getString(R.string.CondPytSkor)+" = " + W + getResources().getString(R.string.typeizm_yzl)+"\n";

                    Hbar = (1013 - Pmm) * 27;
                    dH = Hesh - Hkr*3 - Hbar;
                    tna = (double)dH / (double)Vv;
                    S = W * tna / 60;
                    AnswCalcDop1=S;
                    if (Hzad>=Hper){
                        dHz=Hesh-Hzad;
                    }else{
                        dHz=Hesh-Hzad-Hbar;
                    }
                    AnswCalcDop2=dHz;
                    do
                        Szad = 1 + (int) (Math.random() * 100);
                    while (S-15<=Szad);
                    Sras=S-Szad;
                    AnswCalcDop3=Sras;
                    tras=60*(Sras/W);
                    AnswerCalc = dHz/tras;
                    diap = 50;
                    s=s+getResources().getString(R.string.CondHzad)+" = " + Hzad + getResources().getString(R.string.typeizm_fyt)+"\n";
                    s=s+getResources().getString(R.string.CondHper)+" = " + Hper + getResources().getString(R.string.typeizm_fyt)+"\n";
                    s=s+getResources().getString(R.string.CondSzad)+" = " + Szad + getResources().getString(R.string.typeizm_mmile)+"\n";
                    AnswerText = (int)AnswerCalc + getResources().getString(R.string.typeizm_fytmin);
                    TypeIzm.setText(R.string.typeizm_fytmin);
                    ConditionTask.setText(s + getResources().getString(R.string.CondTask12_3i4));
                }else{
                    int Pmm;
                    int Hesh;
                    int Hkr;
                    int Vv1;
                    int W1;
                    int Vv2;
                    int W2;
                    int H1;

                    int dH1;
                    int dH2;
                    int Hbar;
                    double tna1;
                    double tna2;
                    double S1;
                    double S2;
                    int dHz=0;
                    double S;
                    double S2ras=0;
                    double t2ras;
                    double Sras;
                    double tras;

                    Hesh =1000*(11+(int)(Math.random()*21));
                    H1 =1000*(9+(int)(Math.random()*3));
                    s = getResources().getString(R.string.CondHesh)+" = "+Hesh+getResources().getString(R.string.typeizm_fyt)+"\n";
                    s = s+getResources().getString(R.string.CondH1)+" = "+H1+getResources().getString(R.string.typeizm_fyt)+"\n";
                    Hkr =100*(1+(int)(Math.random()*7));
                    s=s+getResources().getString(R.string.CondHkr)+" = "+Hkr+getResources().getString(R.string.typeizm_m)+"\n";
                    Pmm=950+(int)(Math.random()*80);
                    s = s+getResources().getString(R.string.CondPaer)+" = "+Pmm+getResources().getString(R.string.typeizm_gpa)+"\n";
                    Vv1=100*(20+(int)(Math.random()*9));
                    s = s+getResources().getString(R.string.CondVvert1)+" = "+Vv1+getResources().getString(R.string.typeizm_fytmin)+"\n";
                    W1=250+(int)(Math.random()*50);
                    s = s+getResources().getString(R.string.CondW1)+" = "+W1+getResources().getString(R.string.typeizm_yzl)+"\n";
                    Vv2=100*(10+(int)(Math.random()*10));
                    s = s+getResources().getString(R.string.CondVvert2)+" = "+Vv2+getResources().getString(R.string.typeizm_fytmin)+"\n";
                    W2=200+(int)(Math.random()*50);
                    s = s+getResources().getString(R.string.CondW2)+" = "+W2+getResources().getString(R.string.typeizm_yzl)+"\n";

                    Hbar=(1013-Pmm)*27;
                    dH1=Hesh-H1;
                    tna1=(double)dH1/(double) Vv1;
                    S1=W1*tna1/60;
                    dH2=H1-Hkr*3-Hbar;
                    tna2=(double)dH2/(double)Vv2;
                    S2=W2*tna2/60;
                    S=S1+S2;
                    AnswCalcDop1=S;
                    if (Hzad<H1) {
                        if (Hzad >= Hper) {
                            dHz = H1 - Hzad;
                        } else {
                            dHz = H1 - Hzad - Hbar;
                        }
                        t2ras=(double)dHz/Vv2;
                        S2ras=W2*t2ras/60;
                    }
                    AnswCalcDop2=dHz;
                    do
                        Szad = 1 + (int) (Math.random() * 100);
                    while (S-15-S2ras<=Szad);
                    Sras=S-Szad-S2ras;
                    AnswCalcDop3=Sras;
                    tras=60*(Sras/W1);
                    AnswerCalc = dH1/tras;
                    diap = 50;
                    s=s+getResources().getString(R.string.CondHzad)+" = " + Hzad +getResources().getString(R.string.typeizm_fyt)+"\n";
                    s=s+getResources().getString(R.string.CondHper)+" = " + Hper +getResources().getString(R.string.typeizm_fyt)+"\n";
                    s=s+getResources().getString(R.string.CondSzad)+" = " + Szad +getResources().getString(R.string.typeizm_mmile)+"\n";
                    AnswerText = (int)AnswerCalc + getResources().getString(R.string.typeizm_fytmin);
                    TypeIzm.setText(R.string.typeizm_fytmin);
                    ConditionTask.setText(s + getResources().getString(R.string.CondTask12_3i4));
                }
            }
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void ZadPoiskIstenSkorost(){
        if (AvaLevel>=5) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewTypeIzmdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_kmch));
            TextTe = findViewById(R.id.textViewTypeIzmdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_kmch));
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasdVsh));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasVprisp));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            typeoftaskglob=13;
            int H;
            int Vpr;
            int Vprys;
            double tn;
            double VAR;
            String s;
            int dV;
            int dVsh;

            H=100*(10+(int)(Math.random()*110));
            if (H<=3000){
                Vpr=100+(int)(Math.random()*300);
            }else{
                Vpr=400+(int)(Math.random()*400);
            }

            Vprys=Vpr;
            double tCA;
            if (H>=11000){
                tCA=-56.5;
            }else{
                tCA=(H*(-0.006496)+288.2)-273.15;
            }

            if ((Vpr<300)||(H<2000)){
                dVsh=0;
            }else {
                dVsh = CoafShatia(Vpr, H);
            }
            AnswCalcDop1=dVsh;

            int dORdida=(int)(Math.random()*2);
            if (dORdida==0){
                dV=(-15)+(int)(Math.random()*31);
                s = getResources().getString(R.string.CondPoprav)+" = "+dV+getResources().getString(R.string.typeizm_kmch)+"\n";
            }else{
                int dVa=(-15)+(int)(Math.random()*31);
                int dVi=(-15)+(int)(Math.random()*31);
                s = getResources().getString(R.string.CondPopravI)+" = "+dVi+getResources().getString(R.string.typeizm_kmch)+"\n"+getResources().getString(R.string.CondPopravA)+" = " + dVa + getResources().getString(R.string.typeizm_kmch)+"\n";
                dV=dVa+dVi;
            }
            Vpr=Vpr+dV+dVsh;
            int tprORtn=(int)(Math.random()*2);
            if (tprORtn==0){
                int tpr=(-35)+(int)(Math.random()*21);
                s = s+getResources().getString(R.string.CondTnaTerm)+" = "+tpr+getResources().getString(R.string.typeizm_grad)+"\n";
                tn=((273+tpr)/0.996)-0.384*Math.pow((Vpr/100),2)-273;
                VAR=Math.abs(tCA-tn);
                double Vip=Vpr*171233*Math.pow((288-VAR)-0.006496*H,0.5)/Math.pow(288-0.006496*H,2.628);
                tn=((273+tpr)/0.996)-0.384*Math.pow((Vip/100),2)-273;
            }else{
                tn=(-60)+(int)(Math.random()*41);
                s = s+getResources().getString(R.string.CondTnaEsh)+" = "+tn+getResources().getString(R.string.typeizm_grad)+"\n";
            }
            AnswCalcDop2=Vpr;
            diap = 10;

            VAR=Math.abs(tCA-tn);
            AnswerCalc = Vpr*171233*Math.pow((288-VAR)-0.006496*H,0.5)/Math.pow(288-0.006496*H,2.628);
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_kmch);
            TypeIzm.setText(R.string.typeizm_kmch);
            ConditionTask.setText(getResources().getString(R.string.CondHesh)+" = "+H+getResources().getString(R.string.typeizm_m)+"\n"+getResources().getString(R.string.CondPribSc)+" = "+Vprys+getResources().getString(R.string.typeizm_kmch)+"\n"+s+getResources().getString(R.string.CondTask13i15));
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskPriborSkorost(){
        if (AvaLevel>=5) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewTypeIzmdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_kmch));
            TextTe = findViewById(R.id.textViewTypeIzmdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_kmch));
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasVprisp));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasdVsh));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            typeoftaskglob=14;
            int H;
            int Vis;
            double Vpris;
            double tn;
            double VAR;
            String s;
            int dV;
            int dVsh;

            H=100*(10+(int)(Math.random()*110));
            if (H<=3000){
                Vis=100+(int)(Math.random()*300);
            }else{
                Vis=400+(int)(Math.random()*500);
            }
            double tCA;
            if (H>=11000){
                tCA=-56.5;
            }else{
                tCA=(H*(-0.006496)+288.2)-273.15;
            }

            int dORdida=(int)(Math.random()*2);
            if (dORdida==0){
                dV=(-15)+(int)(Math.random()*31);
                s = getResources().getString(R.string.CondPoprav)+" = "+dV+getResources().getString(R.string.typeizm_kmch)+"\n";
            }else{
                int dVa=(-15)+(int)(Math.random()*31);
                int dVi=(-15)+(int)(Math.random()*31);
                s = getResources().getString(R.string.CondPopravI)+" = "+dVi+getResources().getString(R.string.typeizm_kmch)+"\n"+getResources().getString(R.string.CondPopravA)+" = " + dVa + getResources().getString(R.string.typeizm_kmch)+"\n";
                dV=dVa+dVi;
            }
            int tprORtn=(int)(Math.random()*2);
            if (tprORtn==0){
                int tpr=(-35)+(int)(Math.random()*21);
                s = s+getResources().getString(R.string.CondTnaTerm)+" = "+tpr+getResources().getString(R.string.typeizm_grad)+"\n";
                tn=((273+tpr)/0.996)-0.384*Math.pow((Vis/100),2)-273;
            }else{
                tn=(-60)+(int)(Math.random()*41);
                s = s+getResources().getString(R.string.CondTnaEsh)+" = "+tn+getResources().getString(R.string.typeizm_grad)+"\n";
            }

            diap = 10;

            VAR=Math.abs(tCA-tn);
            Vpris = Vis*Math.pow(288-0.006496*H,2.628)/171233/Math.pow((288-VAR)-0.006496*H,0.5);
            AnswCalcDop1=Vpris;
            if ((Vpris<300)||(H<2000)){
                dVsh=0;
            }else {
                dVsh = CoafShatia((int)(Vpris), H);
            }
            AnswCalcDop2=dVsh;
            AnswerCalc=Vpris-dV-dVsh;
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_kmch);
            TypeIzm.setText(R.string.typeizm_kmch);
            ConditionTask.setText(getResources().getString(R.string.CondHesh)+" = "+H+getResources().getString(R.string.typeizm_m)+"\n"+getResources().getString(R.string.CondIstinSc)+" = "+Vis+getResources().getString(R.string.typeizm_kmch)+"\n"+s+getResources().getString(R.string.CondTask14));
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskIstenSkorostKYS(){
        if (AvaLevel>=5) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewTypeIzmdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_kmch));
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasVprispKYS));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            typeoftaskglob=15;
            int H;
            int Vpr;
            int Vprys;
            double tn;
            double VAR;
            String s;
            int dV;

            H=100*(10+(int)(Math.random()*110));
            if (H<=3000){
                Vpr=100+(int)(Math.random()*300);
            }else{
                Vpr=400+(int)(Math.random()*400);
            }

            Vprys=Vpr;
            double tCA;
            if (H>=11000){
                tCA=-56.5;
            }else{
                tCA=(H*(-0.006496)+288.2)-273.15;
            }

            int dORdida=(int)(Math.random()*2);
            if (dORdida==0){
                dV=(-15)+(int)(Math.random()*31);
                s = getResources().getString(R.string.CondPoprav)+" = "+dV+getResources().getString(R.string.typeizm_kmch)+"\n";
            }else{
                int dVa=(-15)+(int)(Math.random()*31);
                int dVi=(-15)+(int)(Math.random()*31);
                s = getResources().getString(R.string.CondPopravI)+" = "+dVi+getResources().getString(R.string.typeizm_kmch)+"\n"+getResources().getString(R.string.CondPopravA)+" = " + dVa + getResources().getString(R.string.typeizm_kmch)+"\n";
                dV=dVa+dVi;
            }

            Vpr=Vpr+dV;
            AnswCalcDop1=Vpr;
            int tprORtn=(int)(Math.random()*2);
            if (tprORtn==0){
                int tpr=(-35)+(int)(Math.random()*21);
                s = s+getResources().getString(R.string.CondTnaTerm)+" = "+tpr+getResources().getString(R.string.typeizm_grad)+"\n";
                tn=((273+tpr)/0.996)-0.384*Math.pow((Vpr/100),2)-273;
            }else{
                tn=(-60)+(int)(Math.random()*41);
                s = s+getResources().getString(R.string.CondTnaEsh)+" = "+tn+getResources().getString(R.string.typeizm_grad)+"\n";
            }

            diap = 10;

            VAR=Math.abs(tn-tCA);
            AnswerCalc = Vpr- (VAR*Vpr/500);
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_kmch);
            TypeIzm.setText(R.string.typeizm_kmch);
            ConditionTask.setText(getResources().getString(R.string.CondHesh)+" = "+H+getResources().getString(R.string.typeizm_m)+"\n"+getResources().getString(R.string.CondPribScKYS)+" = "+Vprys+getResources().getString(R.string.typeizm_kmch)+"\n"+s+getResources().getString(R.string.CondTask13i15));
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void ZadPoiskIsprPutLBYmB(){
        if (AvaLevel>=6) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewTypeIzmdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewTypeIzmdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewTypeIzmdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasLBY));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasMKvih));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasMKsl));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            double ZMPY;
            double MK;
            double KYR;
            double Sobsh;
            double Spr;
            double tpr;
            double W;
            double YVvih;
            diap=2;
            typeoftaskglob=16;
            double MPR;
            double MPS;
            double YCf;
            double DP;
            double Sost;
            double BY;
            double PK;
            double LBY;

            String s="";

            ZMPY=(int)(Math.random()*360);
            MK=(int)(Math.random()*360);
            KYR=(-90)+(int)(Math.random()*181);
            if (KYR<0){KYR=KYR+360;}
            Sobsh = 90+(int)(Math.random()*41);
            tpr= 5+(int)(Math.random()*6);
            W= 300+(int)(Math.random()*200);
            YVvih=20+(int)(Math.random()*70);

            Sost = Sobsh - (W*tpr/60);
            Spr=(W*tpr/60);

            MPR=MK+KYR;
            if (MPR>=360){
                MPR=MPR-360;
            }
            DP=ZMPY-MPR;
            DP=makeYgol180_180(DP);
            BY=DP*Sost/Spr;
            PK=BY+DP;
            YCf=ZMPY-MK+BY;
            YCf=makeYgol180_180(YCf);
            if (DP>0){
                if (YVvih<0){YVvih=YVvih*(-1);}
            }
            if (DP<0){
                if (YVvih>0){YVvih=YVvih*(-1);}
            }
            if (DP==0) {
                YVvih=0;
            }
            LBY=Sost*Math.tan(Math.toRadians(DP));
            AnswCalcDop1 =LBY;
            AnswCalcDop2 = ZMPY-YVvih;
            AnswCalcDop3 = ZMPY-YCf;

            AnswerCalc = MK-PK;
            AnswerCalc=makeYgol0_360(AnswerCalc);
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_grad);
            TypeIzm.setText(R.string.typeizm_grad);
            ConditionTask.setText(getResources().getString(R.string.CondZMPY)+" = "+ZMPY+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondMagKyr)+" = "+MK+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondKYR)+" = "+KYR+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondSobsh)+" = "+Sobsh+getResources().getString(R.string.typeizm_km)+"\n"+getResources().getString(R.string.Condtpr)+" = "+tpr+getResources().getString(R.string.typeizm_min)+"\n" + getResources().getString(R.string.CondPytSkor)+" = " + W + getResources().getString(R.string.typeizm_kmch)+ getResources().getString(R.string.CondTask18));
        }else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskIsprPutLBYbB() {
        if (AvaLevel >= 6) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewTypeIzmdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewTypeIzmdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewTypeIzmdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasLBY));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasMKvih));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasMKsl));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);

            diap=2;
            double ZMPY;
            double MK;
            double KYR;
            double Sobsh;
            double Spr;
            double tpr;
            double W;
            double YVvih;

            double MPR;
            double MPS;
            double YCf;
            double DP;
            double Sost;
            double BY;
            double PK;
            double LBY;

            String s="";

            ZMPY=(int)(Math.random()*360);
            MK=(int)(Math.random()*360);
            KYR=(91)+(int)(Math.random()*270);
            Sobsh = 90+(int)(Math.random()*41);
            tpr= 5+(int)(Math.random()*6);
            W= 300+(int)(Math.random()*200);
            YVvih=20+(int)(Math.random()*70);

            Sost = Sobsh - (W*tpr/60);
            Spr=(W*tpr/60);

            MPS=MK+KYR;
            if (MPS>=180){
                MPS=MPS-180;
            }else{
                MPS=MPS+180;
            }
            BY=MPS-ZMPY;
            BY=makeYgol180_180(BY);
            if (BY>0){
                if (YVvih<0){YVvih=YVvih*(-1);}
            }
            if (BY<0){
                if (YVvih>0){YVvih=YVvih*(-1);}
            }
            if (BY==0) {
                YVvih=0;
            }
            DP=BY*Spr/Sost;
            PK=BY+DP;
            YCf=ZMPY-MK+BY;
            YCf=makeYgol180_180(YCf);
            LBY=Sost*Math.tan(Math.toRadians(DP));
            AnswCalcDop1=LBY;
            AnswCalcDop2 = ZMPY-YVvih;
            AnswCalcDop3 = ZMPY-YCf;
            AnswerCalc = MK-PK;
            AnswerCalc=makeYgol0_360(AnswerCalc);
            AnswerText = (int) AnswerCalc + getResources().getString(R.string.typeizm_grad);
            TypeIzm.setText(R.string.typeizm_grad);
            ConditionTask.setText(getResources().getString(R.string.CondZMPY)+" = "+ZMPY+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondMagKyr)+" = "+MK+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondKYR)+" = "+KYR+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondSobsh)+" = "+Sobsh+getResources().getString(R.string.typeizm_km)+"\n"+getResources().getString(R.string.Condtpr)+" = "+tpr+getResources().getString(R.string.typeizm_min)+"\n" + getResources().getString(R.string.CondPytSkor)+" = " + W + getResources().getString(R.string.typeizm_kmch)+ getResources().getString(R.string.CondTask18));
        } else {
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskIsprPutLBYbB2() {
        if (AvaLevel >= 6) {
            Obnyl();
            TextView TextTe;
            TextTe = findViewById(R.id.textViewTypeIzmdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewTypeIzmdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewTypeIzmdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setText(getResources().getString(R.string.typeizm_grad));
            TextTe = findViewById(R.id.textViewEnterAnswerdop1);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasDP));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop2);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasLBY));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            TextTe = findViewById(R.id.textViewEnterAnswerdop3);
            TextTe.setVisibility(View.VISIBLE);
            TextTe.setHint(getResources().getString(R.string.answdoprasBY));
            TextTe.setText("");
            if (Theme_Light){
                TextTe.setBackgroundResource(R.color.standartansw);
            }else {
                TextTe.setBackgroundResource(R.color.standartansw1);
            }
            TextTe.setFocusableInTouchMode(true);
            double DP=0;
            double BY=0;
            double Sobsh;
            double Spr;
            double LBY;
            int tpr;
            int W;
            String s="";
            double Sost;

            int rand3 = 1+(int)(Math.random()*3);
            Sobsh = 90+(int)(Math.random()*41);
            tpr= 5+(int)(Math.random()*6);
            W= 300+(int)(Math.random()*200);

            Sost = Sobsh - (W*tpr/60);
            Spr=(W*tpr/60);
            switch (rand3){
                case 1:
                    LBY = (-10)+(int)(Math.random()*21);
                    s=getResources().getString(R.string.CondLBY)+" = " + LBY + getResources().getString(R.string.typeizm_km);
                    AnswCalcDop3=BY=Math.atan(Math.toRadians(LBY/Sost));
                    AnswCalcDop1=DP=Math.atan(Math.toRadians(LBY/Spr));
                    break;
                case 2:
                    BY = (-10)+(int)(Math.random()*21);
                    s=getResources().getString(R.string.CondBY)+" = " + BY + getResources().getString(R.string.typeizm_grad);
                    AnswCalcDop1=DP=BY*Spr/Sost;
                    AnswCalcDop2=LBY=Spr*Math.tan(Math.toRadians(BY));
                    break;
                case 3:
                    DP = (-10)+(int)(Math.random()*21);
                    s=getResources().getString(R.string.CondDP)+" = " + DP + getResources().getString(R.string.typeizm_grad);
                    AnswCalcDop3=BY=DP*Sost/Spr;
                    AnswCalcDop2=LBY=Sost*Math.tan(Math.toRadians(DP));
                    break;
            }

            AnswerCalc = BY+DP;
            if (AnswerCalc>=360){
                AnswerCalc=AnswerCalc-360;
            }
            if (AnswerCalc<=-360){
                AnswerCalc=AnswerCalc+360;
            }
            AnswerText = (int) AnswerCalc + getResources().getString(R.string.typeizm_grad);
            TypeIzm.setText(R.string.typeizm_grad);
            ConditionTask.setText(getResources().getString(R.string.CondSobsh)+" = " + Sobsh + getResources().getString(R.string.typeizm_km)+"\n"+getResources().getString(R.string.Condtpr)+" = " + tpr + getResources().getString(R.string.typeizm_min)+"\n" + getResources().getString(R.string.CondPytSkor)+" = " + W + getResources().getString(R.string.typeizm_kmch)+"\n" + s+ getResources().getString(R.string.CondTask18));
        } else {
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }

    //------------------------------------------------------------

    public void GenerateTask(View view){
        String[] typelevel = getResources().getStringArray(R.array.leveltypes);
        if (Theme_Light){
            AnswerPers.setBackgroundResource(R.color.standartansw);
        }else {
            AnswerPers.setBackgroundResource(R.color.standartansw1);
        }
        AnswerPers.setText("");
        CorrAnswerCalc.setText(R.string.corranswer);
        String spinText = spinType.getSelectedItem().toString();

        if (spinText.equals(typelevel[0])){FirstLevel();}
        if (spinText.equals(typelevel[1])){SecendLevel();}
        if (spinText.equals(typelevel[2])){ThirdLevel();}
        if (spinText.equals(typelevel[3])){FourtLevel();}
        if (spinText.equals(typelevel[4])){FifthLevel();}
        if (spinText.equals(typelevel[5])){SixLevel();}
    }
    public int CoafShatia(int V,int H){
        int Shat = 0;
        int Ig=0;
        int Jg=0;
        double A;

        for (int i=0;i<7;i++){
            if (H>=arrShat[i][0]){Ig=i;}
        }
        for (int i=0;i<7;i++){
            if (V>=arrShat[0][i]){Jg=i;}
        }
        if ((Ig==0)||(Jg==0)){
            Shat=0;
        }else{
            A=(((double)arrShat[Ig][Jg]*(arrShat[0][Jg+1]-V)*(arrShat[Ig+1][0]-H)/(arrShat[0][Jg+1]-arrShat[0][Jg])/(arrShat[Ig+1][0]-arrShat[Ig][0]))+((double)arrShat[Ig][Jg+1]*(V-arrShat[0][Jg])*(arrShat[Ig+1][0]-H)/(arrShat[0][Jg+1]-arrShat[0][Jg])/(arrShat[Ig+1][0]-arrShat[Ig][0]))+((double)arrShat[Ig+1][Jg]*(arrShat[0][Jg+1]-V)*(H-arrShat[Ig][0])/(arrShat[0][Jg+1]-arrShat[0][Jg])/(arrShat[Ig+1][0]-arrShat[Ig][0]))+((double)arrShat[Ig+1][Jg+1]*(V-arrShat[0][Jg])*(H-arrShat[Ig][0])/(arrShat[0][Jg+1]-arrShat[0][Jg])/(arrShat[Ig+1][0]-arrShat[Ig][0])));
            Shat=(int)A;
            Shat=(-1)*Shat;
        }
        return Shat;
    }
    private static boolean isNumeric(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public double makeYgol0_360(double x){
        if (x>=360){
            x=x-360;
        }
        if (x<=-360){
            x=x+360;
        }
        if (x<0){
            x=x+360;
        }
        return x;
    }
    public double makeYgol180_180(double x){
        if (x>180){
            x=x-360;
        }
        if (x<-180){
            x=x+360;
        }
        return x;
    }

}
