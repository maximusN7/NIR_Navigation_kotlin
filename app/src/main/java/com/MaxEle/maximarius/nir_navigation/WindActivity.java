package com.MaxEle.maximarius.nir_navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;

public class WindActivity extends AppCompatActivity {

    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_WIND_TYPE_IZM = "type_izm_wind";
    public static final String DATA_FILE_WIND_SAVED_RUNW = "saved_runway";
    public static final String DATA_FILE_WIND_LIM_XW = "limit_xwind";
    public static final String DATA_FILE_WIND_LIM_TW = "limit_tailwind";
    public static final String DATA_FILE_WIND_LIM_HW = "limit_headwind";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";

    boolean Theme_Light;
    SharedPreferences mDataFiles;

    float grad = 10;
    float dX = 0;
    float dY = 0;
    float dXcent = 0;
    float dYcent = 0;
    float newGrad;

    float grad2 = 10;
    float dX2 = 0;
    float dY2 = 0;
    float newGrad2;
    ImageView image;
    ImageView imagernw;
    ImageView kursim;
    EditText KursEdit;
    EditText PosadEdit;
    TextView ObratPosadEdit;
    EditText WIndSpeedEdit;


    int WindRunwDirect;

    String spinText;
    Spinner SpinMeasure;

    Dialog Instract;
    int flagTypeIzm;

    SeekBar SeekWindSpeed;
    EditText destext1;
    EditText destext2;
    EditText destext3;
    TextView desizm1;
    TextView desizm2;
    TextView desizm3;

    int LimXw;
    int LimHw;
    int LimTw;

    TextView TextStrHeadTailAcc;
    TextView TextStrHeadTailObr;
    TextView TextStrXwindObr;
    TextView TextStrXwindAcc;
    TextView NumbXwindAcc;
    TextView NumbXwindObr;
    TextView NumbHeadTailObr;
    TextView NumbHeadTailAcc;

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

        setContentView(R.layout.activity_wind);

        LoadGraphs();
        LoadGraphs1();

        AdView mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        }else{
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        if (mDataFiles.contains(DATA_FILE_WIND_SAVED_RUNW)){
            WindRunwDirect = mDataFiles.getInt(DATA_FILE_WIND_SAVED_RUNW, 0);
        }
        else
        {
            WindRunwDirect=0;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_WIND_SAVED_RUNW, WindRunwDirect);
            editor.apply();
        }
        if (mDataFiles.contains(DATA_FILE_WIND_TYPE_IZM)){
            flagTypeIzm = mDataFiles.getInt(DATA_FILE_WIND_TYPE_IZM, 1);
        }else{
            flagTypeIzm=1;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_WIND_TYPE_IZM, 0);
            editor.apply();
        }

        image = findViewById(R.id.imageViewrot);
        imagernw = findViewById(R.id.imageViewrot_clearrnw);
        kursim = findViewById(R.id.imageViewkur);
        SpinMeasure = findViewById(R.id.measurlist);

        ImageView kurs_cr = findViewById(R.id.imageViewKurCircle);
        if (Theme_Light){
            kurs_cr.setImageResource(R.drawable.kurs_for_rot_wind);
        }else {
            kurs_cr.setImageResource(R.drawable.kurs_for_rot_dark_wind);
        }

        SeekWindSpeed=findViewById(R.id.seekBar);
        SeekWindSpeed.setOnSeekBarChangeListener(new
                SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                WIndSpeedEdit.setText(String.valueOf(progress));
                if (Integer.parseInt(WIndSpeedEdit.getText().toString())>100){
                    WIndSpeedEdit.setText(R.string.HandrForLim);
                }
                grad2=kursim.getRotation();
                grad=imagernw.getRotation();
                XWind(Integer.parseInt(WIndSpeedEdit.getText().toString()),(int)grad2,(int)grad);
                HTWind(Integer.parseInt(WIndSpeedEdit.getText().toString()),(int)grad2,(int)grad);
                CheckLimits();
            }
            @Override
                    public void onStartTrackingTouch(SeekBar seekBar){
            }
            @Override
                    public void onStopTrackingTouch(SeekBar seekBar){
            }
                });

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);
        dXcent =(float)(metricsB.widthPixels)/2;
        dYcent =(float)(3*(metricsB.heightPixels)/11);

        TextStrHeadTailAcc=findViewById(R.id.TextWindTailHeadAcc);
        TextStrHeadTailObr=findViewById(R.id.TextWindTailHeadObr);
        TextStrXwindObr=findViewById(R.id.TextWindXAcc_LR);
        TextStrXwindAcc=findViewById(R.id.TextWindXObr_LR);
        NumbXwindAcc=findViewById(R.id.TextWindXAcc_num);
        NumbXwindObr=findViewById(R.id.TextWindXObr_num);
        NumbHeadTailObr=findViewById(R.id.TextWindTailHeadAcc_num);
        NumbHeadTailAcc=findViewById(R.id.TextWindTailHeadObr_num);


        if (mDataFiles.contains(DATA_FILE_WIND_LIM_XW)){
            LimXw = mDataFiles.getInt(DATA_FILE_WIND_LIM_XW, 5);
            LimHw = mDataFiles.getInt(DATA_FILE_WIND_LIM_HW, 12);
            LimTw = mDataFiles.getInt(DATA_FILE_WIND_LIM_TW, 12);
        }else{
            LimXw=5;
            LimHw=12;
            LimTw=12;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_WIND_LIM_XW, LimXw);
            editor.putInt(DATA_FILE_WIND_LIM_HW, LimHw);
            editor.putInt(DATA_FILE_WIND_LIM_TW, LimTw);
            editor.apply();
        }


        final String[] typemeasure = getResources().getStringArray(R.array.wind_speed_measure);
        SpinMeasure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                spinText = SpinMeasure.getSelectedItem().toString();
                if (!isNumeric(WIndSpeedEdit.getText().toString())) {WIndSpeedEdit.setText(R.string.ZeroForStart);}
                if (spinText.equals(typemeasure[0])){
                    if (flagTypeIzm==1){
                        MakeTranslation(1.94);
                    }
                    if (flagTypeIzm==2){
                        MakeTranslation(0.54);
                    }
                    UpdateTypeIzm(getResources().getString(R.string.typeizm_yzl),0);
                }
                if (spinText.equals(typemeasure[1])){
                    if (flagTypeIzm==0){
                        MakeTranslation(0.515);
                    }
                    if (flagTypeIzm==2){
                        MakeTranslation(0.277);
                    }
                    UpdateTypeIzm(getResources().getString(R.string.typeizm_mc),1);
                }
                if (spinText.equals(typemeasure[2])){
                    if (flagTypeIzm==0){
                        MakeTranslation(1.85);
                    }
                    if (flagTypeIzm==1){
                        MakeTranslation(3.6);
                    }
                    UpdateTypeIzm(getResources().getString(R.string.typeizm_kmch),2);
                }
                CheckLimits();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        SpinMeasure.setSelection(flagTypeIzm);

        WIndSpeedEdit = findViewById(R.id.textViewWindSpeed);
        WIndSpeedEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isNumeric(WIndSpeedEdit.getText().toString())) {
                    if (!isNumeric(KursEdit.getText().toString())) {KursEdit.setText(R.string.ZeroForStart);}
                    int windspeednumb = Integer.parseInt(WIndSpeedEdit.getText().toString());
                    if (windspeednumb>100){
                        windspeednumb = 100;
                    }
                    int kursnumb = Integer.parseInt(KursEdit.getText().toString());
                    kursnumb = makeYgol0_360d(kursnumb);
                    KursEdit.setText(String.valueOf(kursnumb));
                    kursim.setRotation(kursnumb);
                    SeekWindSpeed.setProgress(windspeednumb);
                    WIndSpeedEdit.setText(String.valueOf(windspeednumb));
                    grad2=kursim.getRotation();
                    grad=imagernw.getRotation();
                    XWind(windspeednumb,(int)grad2,(int)grad);
                    HTWind(windspeednumb,(int)grad2,(int)grad);
                    CheckLimits();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }else{
                    WIndSpeedEdit.setText(R.string.ZeroForStart);
                }
                return true;
            }
            return false;
        });



        KursEdit = findViewById(R.id.textViewkurs);
        KursEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isNumeric(KursEdit.getText().toString())) {
                    int windspeednumb = Integer.parseInt(WIndSpeedEdit.getText().toString());
                    String windstr = WIndSpeedEdit.getText().toString();
                    boolean isNumAns2= isNumeric(windstr);
                    if (!isNumAns2) {WIndSpeedEdit.setText(R.string.ZeroForStart);}
                    int kursnumb = Integer.parseInt(KursEdit.getText().toString());
                    kursnumb = makeYgol0_360d(kursnumb);
                    KursEdit.setText(String.valueOf(kursnumb));
                    kursim.setRotation(kursnumb);
                    grad2=kursim.getRotation();
                    grad=imagernw.getRotation();
                    XWind(windspeednumb,(int)grad2,(int)grad);
                    HTWind(windspeednumb,(int)grad2,(int)grad);
                    CheckLimits();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }else{
                    KursEdit.setText(R.string.ZeroForStart);
                }
                return true;
            }
            return false;
        });

        PosadEdit = findViewById(R.id.textViewposad);
        PosadEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String posstr = PosadEdit.getText().toString();
                if (isNumeric(posstr)) {
                    String windstr = WIndSpeedEdit.getText().toString();
                    boolean isNumAns2= isNumeric(windstr);
                    if (!isNumAns2) {WIndSpeedEdit.setText(getResources().getString(R.string.ZeroForStart));}
                    int windspeednumb = Integer.parseInt(WIndSpeedEdit.getText().toString());
                    int posnumb = Integer.parseInt(PosadEdit.getText().toString());
                    posnumb = makeYgol0_360d(posnumb);
                    PosadEdit.setText(String.valueOf(posnumb));
                    int posnumobr=posnumb-180;
                    posnumobr = makeYgol0_360d(posnumobr);
                    ObratPosadEdit.setText(String.valueOf(posnumobr));
                    grad=imagernw.getRotation();
                    grad2=kursim.getRotation();
                    image.setRotation(posnumb);
                    imagernw.setRotation(posnumb);
                    XWind(windspeednumb,(int)grad2,(int)grad);
                    HTWind(windspeednumb,(int)grad2,(int)grad);
                    CheckLimits();
                    WindRunwDirect=posnumb;
                    SharedPreferences.Editor editor = mDataFiles.edit();
                    editor.putInt(DATA_FILE_WIND_SAVED_RUNW, WindRunwDirect);
                    editor.apply();

                    float k = posnumb;
                    MakeRNWcoll(k);

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
            return false;
        });

        ObratPosadEdit = findViewById(R.id.textViewobrat);

        image.setOnTouchListener((view, event) -> {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    grad=imagernw.getRotation();
                    image.setRotation(grad);
                    imagernw.setRotation(grad);
                    dX = event.getRawX();
                    dY = event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    newGrad=-(float)(Math.toDegrees(Math.atan2(dXcent-event.getRawX(),dYcent-event.getRawY()))-Math.toDegrees(Math.atan2(dXcent-dX,dYcent-dY)));
                    image.setRotation(grad+newGrad);
                    imagernw.setRotation(grad+newGrad);
                    float k=grad+newGrad;
                    k=makeYgol0_360(k);
                    int windspeednumb = Integer.parseInt(WIndSpeedEdit.getText().toString());
                    float gradi=imagernw.getRotation();
                    float gradi2=kursim.getRotation();
                    XWind(windspeednumb,(int)gradi2,(int)gradi);
                    HTWind(windspeednumb,(int)gradi2,(int)gradi);
                    CheckLimits();
                    WindRunwDirect=(int)k;
                    SharedPreferences.Editor editor = mDataFiles.edit();
                    editor.putInt(DATA_FILE_WIND_SAVED_RUNW, WindRunwDirect);
                    editor.apply();

                    PosadEdit.setText((int)(k)+"");
                    float obrk=k-180;
                    obrk=makeYgol0_360(obrk);
                    ObratPosadEdit.setText((int)(obrk)+"");
                    MakeRNWcoll(k);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    return false;
            }
            return true;
        });
        

        kursim.setOnTouchListener((view, event) -> {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    grad2=kursim.getRotation();
                    kursim.setRotation(grad2);
                    dX2 = event.getRawX();
                    dY2 = event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    newGrad2=-(float)(Math.toDegrees(Math.atan2(dXcent-event.getRawX(),dYcent-event.getRawY()))-Math.toDegrees(Math.atan2(dXcent-dX2,dYcent-dY2)));
                    kursim.setRotation(grad2+newGrad2);
                    float k=grad+newGrad2;
                    k=makeYgol0_360(k);
                    float k1=grad2+newGrad2;
                    k1=makeYgol0_360(k1);
                    int l = (int)k1;
                    KursEdit.setText(l+"");
                    int windspeednumb = Integer.parseInt(WIndSpeedEdit.getText().toString());
                    float gradi=imagernw.getRotation();
                    float gradi2=kursim.getRotation();
                    XWind(windspeednumb,(int)gradi2,(int)gradi);
                    HTWind(windspeednumb,(int)gradi2,(int)gradi);
                    CheckLimits();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    return false;
            }
            return true;
        });

        PosadEdit.setText(String.valueOf(WindRunwDirect));
        int posnumobr=WindRunwDirect-180;
        posnumobr = makeYgol0_360d(posnumobr);
        ObratPosadEdit.setText(String.valueOf(posnumobr));
        image.setRotation(WindRunwDirect);
        imagernw.setRotation(WindRunwDirect);
        float k = WindRunwDirect;
        FirsRNWShow(k);

        Instract = new Dialog(WindActivity.this);
        Instract.setTitle(getResources().getString(R.string.WindSettings));
        Instract.setContentView(R.layout.dialog_instr);
        destext1 = Instract.findViewById(R.id.textDescr1);
        destext2 = Instract.findViewById(R.id.textDescr2);
        destext3 = Instract.findViewById(R.id.textDescr3);
        destext1.setText(String.valueOf(LimXw));
        destext2.setText(String.valueOf(LimHw));
        destext3.setText(String.valueOf(LimTw));

        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности
        Button ButBack = findViewById(R.id.buttonBack);
        ButBack.setOnClickListener(view -> {
            WindActivity.this.finish();
            Intent intent = new Intent(WindActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
        });
        Button ButSettings = findViewById(R.id.buttonSettings);
        ButSettings.setOnClickListener(view -> {
            LinearLayout viewmain = Instract.findViewById(R.id.viewMain);
            ScrollView scrolldialog = Instract.findViewById(R.id.scrolldia);
            if (Theme_Light){
                viewmain.setBackgroundColor(getResources().getColor(R.color.backgroundview));
                scrolldialog.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            }else {
                viewmain.setBackgroundColor(getResources().getColor(R.color.background1));
                scrolldialog.setBackgroundColor(getResources().getColor(R.color.background1));
            }
            desizm1 = Instract.findViewById(R.id.textDescrIzm1);
            desizm2 = Instract.findViewById(R.id.textDescrIzm2);
            desizm3 = Instract.findViewById(R.id.textDescrIzm3);
            if (flagTypeIzm==0){
                desizm1.setText(R.string.typeizm_yzl);
                desizm2.setText(R.string.typeizm_yzl);
                desizm3.setText(R.string.typeizm_yzl);
            }
            if (flagTypeIzm==1){
                desizm1.setText(R.string.typeizm_mc);
                desizm2.setText(R.string.typeizm_mc);
                desizm3.setText(R.string.typeizm_mc);
            }
            if (flagTypeIzm==2){
                desizm1.setText(R.string.typeizm_kmch);
                desizm2.setText(R.string.typeizm_kmch);
                desizm3.setText(R.string.typeizm_kmch);
            }

            Instract.show();
        });
        //-------------------------------------------------------------------------
    }

    @Override
    public void onBackPressed() {
        WindActivity.this.finish();
        Intent intent = new Intent(WindActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }

    public float makeYgol0_360(float x){
        do {
            if (x>=360){
                x=x-360;
            }} while(x>=360);
        do {
            if (x<0){
                x=x+360;
            }} while(x<0);
        return x;
    }
    public int makeYgol0_360d(int x){
        do {
            if (x>=360){
                x=x-360;
            }} while(x>=360);
        do {
            if (x<0){
                x=x+360;
            }} while(x<0);
        return x;
    }
    public int makePoloshit(int x){
            if (x<0){
                x=x*(-1);
            }
        return x;
    }
    private static boolean isNumeric(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void onClickBackDial(View view) {
        int posnumb;
        if (!isNumeric(destext1.getText().toString())) {destext1.setText(R.string.ZeroForStart);}
        posnumb = Integer.parseInt(destext1.getText().toString());
        if (posnumb>100){ posnumb=100;destext1.setText(R.string.HandrForLim);}
        SharedPreferences.Editor editor = mDataFiles.edit();
        editor.putInt(DATA_FILE_WIND_LIM_XW, posnumb);

        if (!isNumeric(destext2.getText().toString())) {destext2.setText(R.string.ZeroForStart);}
        posnumb = Integer.parseInt(destext2.getText().toString());
        if (posnumb>100){ posnumb=100;destext2.setText(R.string.HandrForLim);}
        editor.putInt(DATA_FILE_WIND_LIM_HW, posnumb);

        if (!isNumeric(destext3.getText().toString())) {destext3.setText(R.string.ZeroForStart);}
        posnumb = Integer.parseInt(destext3.getText().toString());
        if (posnumb>100){ posnumb=100;destext3.setText(R.string.HandrForLim);}
        editor.putInt(DATA_FILE_WIND_LIM_TW, posnumb);
        editor.apply();

        CheckLimits();
        Instract.dismiss();
    }

    public void XWind(int wind, int naprWind, int naprPol){
        int Xwin;
        Xwin=(int)Math.round(wind*Math.sin(Math.toRadians(naprWind-naprPol)));
        if (Xwin>0){
            TextStrXwindAcc.setText(R.string.WindLeft);
            TextStrXwindObr.setText(R.string.WindRight);
        }
        if (Xwin<0){
            TextStrXwindAcc.setText(R.string.WindRight);
            TextStrXwindObr.setText(R.string.WindLeft);
        }
        if (Xwin==0){
            TextStrXwindAcc.setText(R.string.WindXwind);
            TextStrXwindObr.setText(R.string.WindXwind);
        }
        Xwin=makePoloshit(Xwin);
        NumbXwindAcc.setText(String.valueOf(Xwin));
        NumbXwindObr.setText(String.valueOf(Xwin));
    }
    public void HTWind(int wind, int naprWind, int naprPol){
        int HTwin;
        HTwin=(int)Math.round(wind*Math.cos(Math.toRadians(naprWind-naprPol)));
        if (HTwin>=0){
            TextStrHeadTailAcc.setText(R.string.WindHeadwind);
            TextStrHeadTailObr.setText(R.string.WindTailwind);
        }else{
            TextStrHeadTailAcc.setText(R.string.WindTailwind);
            TextStrHeadTailObr.setText(R.string.WindHeadwind);
        }
        HTwin=makePoloshit(HTwin);
        NumbHeadTailAcc.setText(String.valueOf(HTwin));
        NumbHeadTailObr.setText(String.valueOf(HTwin));
    }

    public void CheckLimits(){
        int WindHTAcc = Integer.parseInt(NumbHeadTailAcc.getText().toString());
        int WindHTObr = Integer.parseInt(NumbHeadTailObr.getText().toString());
        int WindXAcc = Integer.parseInt(NumbXwindAcc.getText().toString());
        LimXw = mDataFiles.getInt(DATA_FILE_WIND_LIM_XW, 5);
        LimHw = mDataFiles.getInt(DATA_FILE_WIND_LIM_HW, 12);
        LimTw = mDataFiles.getInt(DATA_FILE_WIND_LIM_TW, 12);
        String textWindHTAcc = TextStrHeadTailAcc.getText().toString();
        String textWindHTObr = TextStrHeadTailObr.getText().toString();
        if (((WindHTAcc<=LimHw)&&(textWindHTAcc.equals(getResources().getString(R.string.WindHeadwind))))||((WindHTAcc<=LimTw)&&(textWindHTAcc.equals(getResources().getString(R.string.WindTailwind))))){
            if (Theme_Light){
                NumbHeadTailAcc.setTextColor(getResources().getColor(R.color.textsimple));
            }else{
                NumbHeadTailAcc.setTextColor(getResources().getColor(R.color.textsimple1));
            }
        }else{
            NumbHeadTailAcc.setTextColor(getResources().getColor(R.color.wrongansw));
        }
        if (((WindHTObr<=LimHw)&&(textWindHTObr.equals(getResources().getString(R.string.WindHeadwind))))||((WindHTObr<=LimTw)&&(textWindHTObr.equals(getResources().getString(R.string.WindTailwind))))){
            if (Theme_Light){
                NumbHeadTailObr.setTextColor(getResources().getColor(R.color.textsimple));
            }else{
                NumbHeadTailObr.setTextColor(getResources().getColor(R.color.textsimple1));
            }
        }else{
            NumbHeadTailObr.setTextColor(getResources().getColor(R.color.wrongansw));
        }
        if (WindXAcc<=LimXw){
            if (Theme_Light){
                NumbXwindAcc.setTextColor(getResources().getColor(R.color.textsimple));
                NumbXwindObr.setTextColor(getResources().getColor(R.color.textsimple));
            }else{
                NumbXwindAcc.setTextColor(getResources().getColor(R.color.textsimple1));
                NumbXwindObr.setTextColor(getResources().getColor(R.color.textsimple1));
            }
        }else{
            NumbXwindAcc.setTextColor(getResources().getColor(R.color.wrongansw));
            NumbXwindObr.setTextColor(getResources().getColor(R.color.wrongansw));
        }
    }

    Bitmap[] bitmaps = new Bitmap[18];

    public void LoadGraphs(){
        final Runnable runnableLoad = () -> {
            for (int i=1; i<9; i++){
                bitmaps[i-1]=BitmapFactory.decodeResource(getResources(),
                        getResources().getIdentifier("drawable/rnw"+i,null, getPackageName()));
            }
        };
        Thread threadload = new Thread(runnableLoad);
        threadload.start();
    }
    public void LoadGraphs1(){
        final Runnable runnableLoad1 = () -> {
            for (int i=9; i<19; i++){
                bitmaps[i-1]=BitmapFactory.decodeResource(getResources(),
                        getResources().getIdentifier("drawable/rnw"+i,null, getPackageName()));
            }
        };
        Thread threadload1 = new Thread(runnableLoad1);
        threadload1.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Bundle bundle = msg.getData();
            int id = bundle.getInt("Key");
            image.setImageBitmap(bitmaps[id]);
        }
    };
    float kk;
    public void FirsRNWShow(float k){
        kk=k;
        final Runnable runnable = () -> {
            Message msg = handler.obtainMessage();
            Bundle bundle = new Bundle();
            for (int i=0; i<35; i++){
                if (kk>=5+10*i && kk<=14+10*i){
                    if (i<18){
                        bundle.putInt("Key", i);
                    }else{
                        image.setRotation(imagernw.getRotation()+180);
                        bundle.putInt("Key", i-18);
                    }
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }
            if ((kk>=355 && kk<=360)||(kk>=0 && kk<=4)){
                image.setRotation(imagernw.getRotation()+180);
                bundle.putInt("Key", 17);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
    public void MakeRNWcoll(float k){
        if ((k>=355 && k<=360)||(k>=0 && k<5)){
            image.setRotation(imagernw.getRotation()+180);
            image.setImageBitmap(bitmaps[17]);
        }
        else
        {
            for (int i=0; i<35; i++){
                if (k>=5+10*i && k<15+10*i){
                    if (i>=18){
                        image.setRotation(imagernw.getRotation()+180);
                        image.setImageBitmap(bitmaps[i-18]);
                    }else{
                        image.setImageBitmap(bitmaps[i]);
                    }
                }
            }
        }
    }

    public void MakeTranslation(double koef){
        int number = (int)Math.round(Integer.parseInt(WIndSpeedEdit.getText().toString())*koef);
        WIndSpeedEdit.setText(String.valueOf(number));
        SeekWindSpeed.setProgress(number);
        number = (int)Math.round(Integer.parseInt(NumbXwindAcc.getText().toString())*koef);
        NumbXwindAcc.setText(String.valueOf(number));
        number = (int)Math.round(Integer.parseInt(NumbXwindObr.getText().toString())*koef);
        NumbXwindObr.setText(String.valueOf(number));
        number = (int)Math.round(Integer.parseInt(NumbHeadTailAcc.getText().toString())*koef);
        NumbHeadTailAcc.setText(String.valueOf(number));
        NumbHeadTailObr.setText(String.valueOf(number));

        number = (int)Math.round(Integer.parseInt(destext1.getText().toString())*koef);
        destext1.setText(String.valueOf(number));
        number = (int)Math.round(Integer.parseInt(destext2.getText().toString())*koef);
        destext2.setText(String.valueOf(number));
        number = (int)Math.round(Integer.parseInt(destext3.getText().toString())*koef);
        destext3.setText(String.valueOf(number));

        int posnumb;
        if (!isNumeric(destext1.getText().toString())) {destext1.setText(R.string.ZeroForStart);}
        posnumb = Integer.parseInt(destext1.getText().toString());
        if (posnumb>100){ posnumb=100;destext1.setText(R.string.HandrForLim);}
        SharedPreferences.Editor editor = mDataFiles.edit();
        editor.putInt(DATA_FILE_WIND_LIM_XW, posnumb);

        if (!isNumeric(destext2.getText().toString())) {destext2.setText(R.string.ZeroForStart);}
        posnumb = Integer.parseInt(destext2.getText().toString());
        if (posnumb>100){ posnumb=100;destext2.setText(R.string.HandrForLim);}
        editor.putInt(DATA_FILE_WIND_LIM_HW, posnumb);

        if (!isNumeric(destext3.getText().toString())) {destext3.setText(R.string.ZeroForStart);}
        posnumb = Integer.parseInt(destext3.getText().toString());
        if (posnumb>100){ posnumb=100;destext3.setText(R.string.HandrForLim);}
        editor.putInt(DATA_FILE_WIND_LIM_TW, posnumb);
        editor.apply();
    }

    public void UpdateTypeIzm(String str, int flag){
        TextView measures = findViewById(R.id.TextWindTailHeadAcc_mea);
        measures.setText(str);
        measures = findViewById(R.id.TextWindTailHeadObr_mea);
        measures.setText(str);
        measures = findViewById(R.id.TextWindXAcc_mea);
        measures.setText(str);
        measures = findViewById(R.id.TextWindXObr_mea);
        measures.setText(str);
        flagTypeIzm=flag;
        SharedPreferences.Editor editor = mDataFiles.edit();
        editor.putInt(DATA_FILE_WIND_TYPE_IZM, flagTypeIzm);
        editor.apply();
    }
}
