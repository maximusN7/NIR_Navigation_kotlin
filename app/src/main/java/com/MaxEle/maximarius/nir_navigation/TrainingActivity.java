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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.MaxEle.maximarius.nir_navigation.newpack.Ben_p;
import com.MaxEle.maximarius.nir_navigation.newpack.LYRRazvr;
import com.MaxEle.maximarius.nir_navigation.newpack.MyClass;
import com.MaxEle.maximarius.nir_navigation.newpack.RadRazv;
import com.MaxEle.maximarius.nir_navigation.newpack.U_p;
import com.MaxEle.maximarius.nir_navigation.newpack.VremyaRazvor;
import com.MaxEle.maximarius.nir_navigation.newpack.W_p;
import com.MaxEle.maximarius.nir_navigation.newpack.YS_p;
import com.MaxEle.maximarius.nir_navigation.newpack.YV_p;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.LinkedList;

public class TrainingActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_LEVEL = "level";
    public static final String DATA_FILE_EXC6 = "exclusive6lvl";
    public static final String DATA_FILE_MY_TASKS = "my_tasks";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";
    boolean Theme_Light;


    boolean Excl6;

    boolean MyTaskChoosen = false;
    ArrayList<String> listoftasks = new ArrayList<>();
    LinkedList<String> splitedTasks= new LinkedList<>();

    TextView ConditionTask;
    TextView CorrAnswerCalc;
    TextView TypeIzm;
    EditText AnswerPers;
    Spinner spinType;
    String spinText;
    Spinner spinMyType;

    double diap;
    String AnswerText;
    double AnswerCalc;

    int AvaLevel;
    SharedPreferences mDataFiles;

    int[][] arrShat = {{0,300,400,500,600,700,800,900},{2000,1,2,3,4,7,9,10},{4000,2,4,6,10,16,23,24},{6000,3,6,11,18,27,39,40},{8000,4,9,17,28,41,53,54},{10000,6,13,24,40,56,80,81},{12000,9,19,34,56,78,98,99},{14000,12,25,48,73,97,118,119}};

    boolean Unfliped = true;
    RelativeLayout NLlay;
    NestedScrollView NLCenter;
    ImageView NL1;
    ImageView NL2;
    ImageView NL3;
    ImageView Visisr;
    float dX = 0;
    float dY = 0;

    private AdView mAdView;

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

        setContentView(R.layout.activity_training);


        mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        }else{
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);

        AvaLevel = mDataFiles.getInt(DATA_FILE_LEVEL, 0);
        Excl6 = mDataFiles.getBoolean(DATA_FILE_EXC6, false);


        spinType = findViewById(R.id.taskslist);
        spinMyType = findViewById(R.id.mytaskslist);
        AnswerPers = findViewById(R.id.textViewEnterAnswer);
        AnswerPers.setOnEditorActionListener(this);
        AnswerPers.setFocusableInTouchMode(false);
        ConditionTask = findViewById(R.id.textViewCondition);
        CorrAnswerCalc = findViewById(R.id.textViewAnswerCalcul);
        TypeIzm = findViewById(R.id.textViewTypeIzm);
        CorrAnswerCalc.setVisibility(View.INVISIBLE);

        ScrollView viewansw = findViewById(R.id.viewcond);
        TextView textAnsw = findViewById(R.id.textViewAnswerCalcul);
        if (Theme_Light){
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            textAnsw.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            AnswerPers.setBackgroundResource(R.color.standartansw);
        }else {
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
            textAnsw.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
            AnswerPers.setBackgroundResource(R.color.standartansw1);
        }

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

        String AllMyTasks = mDataFiles.getString(DATA_FILE_MY_TASKS,"");
        if (!AllMyTasks.equals("")){
            String[] tasks = AllMyTasks.split("/");
            for (int i = 0; i < tasks.length; i++) {
                String[] task_harak = tasks[i].split(",");
                splitedTasks.add(task_harak[0]);
                splitedTasks.add(task_harak[1]);
                splitedTasks.add(task_harak[2]);
                listoftasks.add(task_harak[2]);
            }
            spinMyType.setVisibility(View.VISIBLE);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listoftasks);
            if (Theme_Light){
                adapter.setDropDownViewResource(R.layout.spinner_light);
            }else {
                adapter.setDropDownViewResource(R.layout.spinner_dark);
            }
            spinMyType.setAdapter(adapter);

            spinMyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    CorrAnswerCalc.setVisibility(View.INVISIBLE);
                    MyTaskChoosen=true;
                    String s;
                    if (AvaLevel<7){
                        s = getResources().getString(R.string.toastNotAvailMyTask);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinMyType.setSelection(0);
                    }
                    spinText = spinMyType.getSelectedItem().toString();
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }else{
            spinMyType.setVisibility(View.INVISIBLE);
        }

        spinMyType.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (spinMyType.getCount()==1){
                    ConditionTask.setText(R.string.condition);
                    AnswerPers.setText("");
                    TypeIzm.setText("");
                    CorrAnswerCalc.setText(R.string.corranswer);
                    if (Theme_Light){
                        AnswerPers.setBackgroundResource(R.color.standartansw);
                    }else {
                        AnswerPers.setBackgroundResource(R.color.standartansw1);
                    }
                    CorrAnswerCalc.setVisibility(View.INVISIBLE);
                    MyTaskChoosen=true;
                    String s;
                    if (AvaLevel<7){
                        s = getResources().getString(R.string.toastNotAvailMyTask);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinMyType.setSelection(0);
                    }
                    spinText = spinMyType.getSelectedItem().toString();
                }
                return false;
            }
        });

        final String[] typetask = getResources().getStringArray(R.array.taskstypes);
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
                CorrAnswerCalc.setVisibility(View.INVISIBLE);
                MyTaskChoosen=false;
                String s;
                spinText = spinType.getSelectedItem().toString();
                if (spinText.equals(typetask[3])){
                    if (AvaLevel<2){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[4])){
                    if (AvaLevel<2){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[5])){
                    if (AvaLevel<2){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }

                if (spinText.equals(typetask[6])){
                    if (AvaLevel<3){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[7])){
                    if (AvaLevel<3){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[8])){
                    if (AvaLevel<3){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }

                if (spinText.equals(typetask[9])){
                    if (AvaLevel<4){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[10])){
                    if (AvaLevel<4){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[11])){
                    if (AvaLevel<4){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }

                if (spinText.equals(typetask[12])){
                    if (AvaLevel<5){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[13])){
                    if (AvaLevel<5){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[14])){
                    if (AvaLevel<5){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }

                if (spinText.equals(typetask[15])){
                    if (AvaLevel<6 && !Excl6){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[16])){
                    if (AvaLevel<6 && !Excl6){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[17])){
                    if (AvaLevel<6 && !Excl6){
                        s = getResources().getString(R.string.toastNotAvail);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinType.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                    ConditionTask.setText(R.string.condition);
                    AnswerPers.setText("");
                    TypeIzm.setText("");
                    CorrAnswerCalc.setText(R.string.corranswer);
                    if (Theme_Light){
                        AnswerPers.setBackgroundResource(R.color.standartansw);
                    }else {
                        AnswerPers.setBackgroundResource(R.color.standartansw1);
                    }
                    CorrAnswerCalc.setVisibility(View.INVISIBLE);
                    MyTaskChoosen=false;
                    String s;
                    if (AvaLevel<7){
                        s = getResources().getString(R.string.toastNotAvailMyTask);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                    spinText = spinType.getSelectedItem().toString();
                return false;
            }
        });

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

    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            String stringAnsw = AnswerPers.getText().toString().replace(',','.');
            boolean isNumAns= isNumeric(stringAnsw);
            if (isNumAns) {

                CorrAnswerCalc.setVisibility(View.VISIBLE);
                double answerfrpers = Double.parseDouble(AnswerPers.getText().toString().replace(',','.'));

                if (answerfrpers <= AnswerCalc + diap && answerfrpers >= AnswerCalc - diap) {
                    AnswerPers.setBackgroundResource(R.color.correctansw);
                } else {
                    AnswerPers.setBackgroundResource(R.color.wrongansw);
                }
                stringAnsw=AnswerText+getResources().getString(R.string.corranswer);
                CorrAnswerCalc.setText(stringAnsw);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                AnswerPers.setFocusable(false);
            }
            return true;
        }
        return false;
    }

    //---------------------------------------------------------------------------------

    public void ZadPoiskYglaRazvorota(){
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

            U=Math.sqrt(V*V+W*W-2*V*W*Math.cos(Math.toRadians(YS)));
            YV=Math.toDegrees(Math.asin(V*Math.sin(Math.toRadians(YS))/U));
            if (W>=V){
                Ben=MK+YS+YV+dM;
            }
            else{
                Ben=MK+YS-YV+dM;
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
            diap=2;
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
            double Ali = U*Math.sin(Math.toRadians(YV))/V;
            YS=Math.toDegrees(Math.asin(Ali));
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
                dH=Hesh-Hkr-Hbar;
                tna=(double)dH/Vv;
                AnswerCalc = W*tna/3600;
                diap = 0.4;
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_km);
                TypeIzm.setText(R.string.typeizm_km);
                ConditionTask.setText(s+getResources().getString(R.string.CondTask10_1));
            }else{
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
                dH=Hesh-(int)(Hkr*3.28)-Hbar;
                tna=(double)dH/Vv;
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
                dH1=Hesh-H1;
                tna1=(double)dH1/Vv1;
                S1=W1*tna1/3600;
                dH2=H1-Hkr-Hbar;
                tna2=(double)dH2/Vv2;
                S2=W2*tna2/3600;
                AnswerCalc = S1+S2;
                diap = 0.4;
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_km);
                TypeIzm.setText(R.string.typeizm_km);
                ConditionTask.setText(s+getResources().getString(R.string.CondTask11_1));
            }else{
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
                dH1=Hesh-H1;
                tna1=(double)dH1/Vv1;
                S1=W1*tna1/60;
                dH2=H1-Hkr*3-Hbar;
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
            String s;
            int Hzad;
            int Szad;
            int Hper;

            int SloshORProst=(int)(Math.random()*2);
            int PmmORPgpa=(int)(Math.random()*2);

            if (PmmORPgpa==0){
                Hzad = 100 * (10 + (int) (Math.random() * 30));
                Hper = 100 * (10 + (int) (Math.random() * 10));

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
                    if (Hzad>=Hper){
                        dHz=Hesh-Hzad;
                    }else{
                        dHz=Hesh-Hzad-Hbar;
                    }
                    do
                        Szad = (1 + (int) (Math.random() * 100));
                    while (S-15<=Szad);
                    Sras=S-Szad;
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
                    int dHz;
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
                    if (Hzad<H1) {
                        if (Hzad >= Hper) {
                            dHz = H1 - Hzad;
                        } else {
                            dHz = H1 - Hzad - Hbar;
                        }
                        t2ras=(double)dHz/Vv2;
                        S2ras=W2*t2ras/3600;
                    }
                    do
                        Szad = 1 + (int) (Math.random() * 100);
                    while (S-15-S2ras<=Szad);
                    Sras=S-Szad-S2ras;
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
                    if (Hzad>=Hper){
                        dHz=Hesh-Hzad;
                    }else{
                        dHz=Hesh-Hzad-Hbar;
                    }
                    do
                        Szad = 1 + (int) (Math.random() * 100);
                    while (S-15<=Szad);
                    Sras=S-Szad;
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
                    int dHz;
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
                    if (Hzad<H1) {
                        if (Hzad >= Hper) {
                            dHz = H1 - Hzad;
                        } else {
                            dHz = H1 - Hzad - Hbar;
                        }
                        t2ras=(double)dHz/Vv2;
                        S2ras=W2*t2ras/60;
                    }
                    do
                        Szad = 1 + (int) (Math.random() * 100);
                    while (S-15-S2ras<=Szad);
                    Sras=S-Szad-S2ras;
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
            if ((Vpris<300)||(H<2000)){
                dVsh=0;
            }else {
                dVsh = CoafShatia((int)(Vpris), H);
            }
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
        if (AvaLevel>=6  || Excl6) {
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
            int rand3 = 1+(int)(Math.random()*3);
            switch (rand3){
                case 1:
                    s=getResources().getString(R.string.CondTask16i17_1);
                    AnswerCalc = MK-PK;
                    break;
                case 2:
                    s=getResources().getString(R.string.CondTask16i17_2);
                    AnswerCalc = ZMPY-YVvih;
                    break;
                case 3:
                    s=getResources().getString(R.string.CondTask16i17_3);
                    AnswerCalc = ZMPY-YCf;
                    break;
            }

            AnswerCalc=makeYgol0_360(AnswerCalc);
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_grad);
            TypeIzm.setText(R.string.typeizm_grad);
            ConditionTask.setText(getResources().getString(R.string.CondNaRS)+"\n"+getResources().getString(R.string.CondZMPY)+" = "+ZMPY+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondMagKyr)+" = "+MK+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondKYR)+" = "+KYR+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondSobsh)+" = "+Sobsh+getResources().getString(R.string.typeizm_km)+"\n"+getResources().getString(R.string.Condtpr)+" = "+tpr+getResources().getString(R.string.typeizm_min)+"\n" + getResources().getString(R.string.CondPytSkor)+" = " + W + getResources().getString(R.string.typeizm_kmch)+"\n" + getResources().getString(R.string.CondYVvih)+" = " + YVvih + getResources().getString(R.string.typeizm_grad)+s);
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskIsprPutLBYbB(){
        if (AvaLevel>=6  || Excl6) {
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
            if (BY>=0){
                if (YVvih<0){YVvih=YVvih*(-1);}
            }else{
                if (YVvih>0){YVvih=YVvih*(-1);}
            }
            DP=BY*Spr/Sost;
            PK=BY+DP;
            YCf=ZMPY-MK+BY;
            YCf=makeYgol180_180(YCf);

            int rand3 = 1+(int)(Math.random()*3);
            switch (rand3){
                case 1:
                    s=getResources().getString(R.string.CondTask16i17_1);
                    AnswerCalc = MK-PK;
                    break;
                case 2:
                    s=getResources().getString(R.string.CondTask16i17_2);
                    AnswerCalc = ZMPY-YVvih;
                    break;
                case 3:
                    s=getResources().getString(R.string.CondTask16i17_3);
                    AnswerCalc = ZMPY-YCf;
                    break;
            }

            AnswerCalc=makeYgol0_360(AnswerCalc);
            AnswerText = (int)AnswerCalc+getResources().getString(R.string.typeizm_grad);
            TypeIzm.setText(R.string.typeizm_grad);
            ConditionTask.setText(getResources().getString(R.string.CondOtRS)+"\n"+getResources().getString(R.string.CondZMPY)+" = "+ZMPY+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondMagKyr)+" = "+MK+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondKYR)+" = "+KYR+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.CondSobsh)+" = "+Sobsh+getResources().getString(R.string.typeizm_km)+"\n"+getResources().getString(R.string.Condtpr)+" = "+tpr+getResources().getString(R.string.typeizm_min)+"\n" + getResources().getString(R.string.CondPytSkor)+" = " + W + getResources().getString(R.string.typeizm_kmch)+"\n" + getResources().getString(R.string.CondYVvih)+" = " + YVvih + getResources().getString(R.string.typeizm_grad)+s);
        }
        else{
            String s = getResources().getString(R.string.toastNotAvail);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskIsprPutLBYbB2() {
        if (AvaLevel >= 6  || Excl6) {
            diap=2;
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
                    BY=Math.toDegrees(Math.atan(LBY/Spr));
                    DP=Math.toDegrees(Math.atan(LBY/Sost));
                    break;
                case 2:
                    BY = (-10)+(int)(Math.random()*21);
                    s=getResources().getString(R.string.CondBY)+" = " + BY + getResources().getString(R.string.typeizm_grad);
                    DP=BY*Spr/Sost;
                    LBY=Spr*Math.tan(Math.toRadians(BY));
                    break;
                case 3:
                    DP = (-10)+(int)(Math.random()*21);
                    s=getResources().getString(R.string.CondDP)+" = " + DP + getResources().getString(R.string.typeizm_grad);
                    BY=DP*Sost/Spr;
                    LBY=Sost*Math.tan(Math.toRadians(DP));
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

    //--------------------------------------------------------------------

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
            TrainingActivity.this.finish();
            Intent intent = new Intent(TrainingActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
        }
    }
    public void onClickBack(View view){
        TrainingActivity.this.finish();
        Intent intent = new Intent(TrainingActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }
/*
    public void onClickOrient(View view) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }*/

    boolean NLshowed = false;
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
        Toast.makeText(TrainingActivity.this, getResources().getString(R.string.toastNLFixed), Toast.LENGTH_SHORT).show();
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
        Toast.makeText(TrainingActivity.this, getResources().getString(R.string.toastNLUnspeci), Toast.LENGTH_SHORT).show();
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

    public void GenerateTask(View view){
        AnswerPers.setFocusableInTouchMode(true);
        String[] typetask = getResources().getStringArray(R.array.taskstypes);
        if (Theme_Light){
            AnswerPers.setBackgroundResource(R.color.standartansw);
        }else {
            AnswerPers.setBackgroundResource(R.color.standartansw1);
        }

        AnswerPers.setText("");
        CorrAnswerCalc.setText(R.string.corranswer);
        CorrAnswerCalc.setVisibility(View.INVISIBLE);
        if (MyTaskChoosen){
            int spinMyText = spinMyType.getSelectedItemPosition();
            String strnumsofacts = splitedTasks.get(spinMyText*3);
            String strdiap = splitedTasks.get(1+spinMyText*3);
            double diapfromstr = Double.parseDouble(strdiap);
            CreateMyTask(strnumsofacts, diapfromstr);
        }else{
        String spinText = spinType.getSelectedItem().toString();
        if (spinText.equals(typetask[0])){ZadPoiskYglaRazvorota();}
        if (spinText.equals(typetask[1])){ZadPoiskVremeniRazvorota();}
        if (spinText.equals(typetask[2])){ZadPoiskLYR();}

        if (spinText.equals(typetask[3])){ZadPoiskPytevoiSkorosti();}
        if (spinText.equals(typetask[4])){ZadPoiskSkorostVetra();}
        if (spinText.equals(typetask[5])){ZadPoiskNapravleniaVetra();}

        if (spinText.equals(typetask[6])){ZadPoiskKyrsi();}
        if (spinText.equals(typetask[7])){ZadPoiskFIPY();}
        if (spinText.equals(typetask[8])){ZadPoiskKyrsiPoVetry();}

        if (spinText.equals(typetask[9])){ZadPoiskZadRybesha();}
        if (spinText.equals(typetask[10])){ZadPoiskZadRybeshaOgr();}
        if (spinText.equals(typetask[11])){ZadPoiskZadRybeshaIzmen();}

        if (spinText.equals(typetask[12])){ZadPoiskIstenSkorost();}
        if (spinText.equals(typetask[13])){ZadPoiskPriborSkorost();}
        if (spinText.equals(typetask[14])){ZadPoiskIstenSkorostKYS();}

        if (spinText.equals(typetask[15])){ZadPoiskIsprPutLBYmB();}
        if (spinText.equals(typetask[16])){ZadPoiskIsprPutLBYbB();}
        if (spinText.equals(typetask[17])){ZadPoiskIsprPutLBYbB2();}
        }
    }

    String condit="";
    public void CreateMyTask(String NumsOfAct, double Diap){
        ObnylNaborAcc();

        diap=Diap;
        String[] numberAct = NumsOfAct.split("_");
        for (int i=0;i<numberAct.length;i++){
            switch (numberAct[i]){
                case "1":
                    promOtv.add(NaborAcc.RadRazv());
                    if (NaborAcc.nesush1){
                        condit = condit+getResources().getString(R.string.CondSkorRad)+" = "+NaborAcc.V+getResources().getString(R.string.typeizm_kmch)+"\n";
                    }
                    if (NaborAcc.nesush2){
                        condit = condit+getResources().getString(R.string.Condkren)+" = "+NaborAcc.x+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    break;
                case "2":
                    promOtv.add(NaborAcc.VremyaRazvor());
                    if (NaborAcc.nesush1){
                        condit = condit+getResources().getString(R.string.CondSkorRad)+" = "+NaborAcc.V+getResources().getString(R.string.typeizm_kmch)+"\n";
                    }
                    if (NaborAcc.nesush2){
                        condit = condit+getResources().getString(R.string.CondRad)+" = "+NaborAcc.Rad+getResources().getString(R.string.typeizm_km)+"\n";
                    }
                    if (NaborAcc.nesush3){
                        condit = condit+getResources().getString(R.string.CondYR)+"="+NaborAcc.YR+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    break;
                case "3":
                    promOtv.add(NaborAcc.LYRRazvr());
                    if (NaborAcc.nesush1){
                        condit = condit+getResources().getString(R.string.CondRad)+" = "+NaborAcc.Rad+getResources().getString(R.string.typeizm_km)+"\n";
                    }
                    if (NaborAcc.nesush2){
                        condit = condit+getResources().getString(R.string.CondYR)+" = "+NaborAcc.YR+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    break;
                case "4":
                    NaborAcc.Ben_p();
                    if (NaborAcc.nesush1){
                        condit = condit+getResources().getString(R.string.CondNapVetrMe)+" = "+NaborAcc.Bem+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    if (NaborAcc.nesush2){
                        condit = condit+getResources().getString(R.string.CondMagSkl)+" = "+NaborAcc.dM+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    break;
                case "5":
                    NaborAcc.Ben_p2();
                    if (NaborAcc.nesush1){
                        condit = condit+getResources().getString(R.string.CondSkorRad)+" = "+NaborAcc.V+getResources().getString(R.string.typeizm_kmch)+"\n";
                    }
                    if (NaborAcc.nesush2){
                        condit = condit+getResources().getString(R.string.CondPytSkor)+" = "+NaborAcc.W+getResources().getString(R.string.typeizm_kmch)+"\n";
                    }
                    if (NaborAcc.nesush3){
                        condit = condit+getResources().getString(R.string.CondNapVetrMe)+" = "+NaborAcc.Bem+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    if (NaborAcc.nesush4){
                        condit = condit+getResources().getString(R.string.CondMagSkl)+" = "+NaborAcc.dM+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    if (NaborAcc.nesush5){
                        condit = condit+getResources().getString(R.string.CondMagKyr)+" = "+NaborAcc.MK+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    if (NaborAcc.nesush6){
                        condit = condit+getResources().getString(R.string.CondYV)+" = "+NaborAcc.YV+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    if (NaborAcc.nesush7){
                        condit = condit+getResources().getString(R.string.CondYS)+" = "+NaborAcc.YS+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    break;
                case "6":
                    NaborAcc.Bem_p();
                    if (NaborAcc.nesush1){
                        condit = condit+getResources().getString(R.string.CondNapVetrNa)+" = "+NaborAcc.Ben+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    if (NaborAcc.nesush2){
                        condit = condit+getResources().getString(R.string.CondMagSkl)+" = "+NaborAcc.dM+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    break;
                case "7":
                    promOtv.add(NaborAcc.YV_p());
                    if (NaborAcc.nesush1){
                        condit = condit+getResources().getString(R.string.CondNapVetrNa)+" = "+NaborAcc.Ben+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    if (NaborAcc.nesush2){
                        condit = condit+getResources().getString(R.string.CondZMPY)+" = "+NaborAcc.ZMPY+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    break;
                case "8":
                    promOtv.add(NaborAcc.YS_p());
                    if (NaborAcc.nesush1){
                        condit = condit+getResources().getString(R.string.CondSkorRad)+" = "+NaborAcc.V+getResources().getString(R.string.typeizm_kmch)+"\n";
                    }
                    if (NaborAcc.nesush2){
                        condit = condit+getResources().getString(R.string.CondYV)+" = "+NaborAcc.YV+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    if (NaborAcc.nesush3){
                        condit = condit+getResources().getString(R.string.CondSkorVetr)+" = "+NaborAcc.U+getResources().getString(R.string.typeizm_kmch)+"\n";
                    }
                    break;
                case "9":
                    promOtv.add(NaborAcc.W_p());
                    if (NaborAcc.nesush1){
                        condit = condit+getResources().getString(R.string.CondSkorRad)+" = "+NaborAcc.V+getResources().getString(R.string.typeizm_kmch)+"\n";
                    }
                    if (NaborAcc.nesush2){
                        condit = condit+getResources().getString(R.string.CondYV)+" = "+NaborAcc.YV+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    if (NaborAcc.nesush3){
                        condit = condit+getResources().getString(R.string.CondYS)+" = "+NaborAcc.YS+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    break;
                case "10":
                    promOtv.add(NaborAcc.U_p());
                    if (NaborAcc.nesush1){
                        condit = condit+getResources().getString(R.string.CondSkorRad)+" = "+NaborAcc.V+getResources().getString(R.string.typeizm_kmch)+"\n";
                    }
                    if (NaborAcc.nesush2){
                        condit = condit+getResources().getString(R.string.CondPytSkor)+" = "+NaborAcc.W+getResources().getString(R.string.typeizm_kmch)+"\n";
                    }
                    if (NaborAcc.nesush3){
                        condit = condit+getResources().getString(R.string.CondYS)+" = "+NaborAcc.YS+getResources().getString(R.string.typeizm_grad)+"\n";
                    }
                    break;
            }
        }
        switch (numberAct[numberAct.length-1]){
            case "1":
                AnswerCalc = NaborAcc.RadRazv();
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_km);
                TypeIzm.setText(R.string.typeizm_km);
                condit=condit+getResources().getString(R.string.CondMyTask1);
                break;
            case "2":
                AnswerCalc = NaborAcc.VremyaRazvor();
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_s);
                TypeIzm.setText(R.string.typeizm_s);
                condit=condit+getResources().getString(R.string.CondMyTask2);
                break;
            case "3":
                AnswerCalc = NaborAcc.LYRRazvr();
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_km);
                TypeIzm.setText(R.string.typeizm_grad);
                condit=condit+getResources().getString(R.string.CondMyTask3);
                break;
            case "4":
                AnswerCalc = NaborAcc.Ben_p();
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_grad);
                TypeIzm.setText(R.string.typeizm_grad);
                condit=condit+getResources().getString(R.string.CondMyTask4);
                break;
            case "5":
                AnswerCalc = NaborAcc.Ben_p2();
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_grad);
                TypeIzm.setText(R.string.typeizm_grad);
                condit=condit+getResources().getString(R.string.CondMyTask5);
                break;
            case "6":
                AnswerCalc = NaborAcc.Bem_p();
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_grad);
                TypeIzm.setText(R.string.typeizm_grad);
                condit=condit+getResources().getString(R.string.CondMyTask6);
                break;
            case "7":
                AnswerCalc = NaborAcc.YV_p();
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_grad);
                TypeIzm.setText(R.string.typeizm_grad);
                condit=condit+getResources().getString(R.string.CondMyTask7);
                break;
            case "8":
                AnswerCalc = NaborAcc.YS_p();
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_grad);
                TypeIzm.setText(R.string.typeizm_grad);
                condit=condit+getResources().getString(R.string.CondMyTask8);
                break;
            case "9":
                AnswerCalc = NaborAcc.W_p();
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_kmch);
                TypeIzm.setText(R.string.typeizm_kmch);
                condit=condit+getResources().getString(R.string.CondMyTask9);
                break;
            case "10":
                AnswerCalc = NaborAcc.U_p();
                AnswerText = String.format("%.1f", +AnswerCalc)+getResources().getString(R.string.typeizm_kmch);
                TypeIzm.setText(R.string.typeizm_kmch);
                condit=condit+getResources().getString(R.string.CondMyTask10);
                break;
        }

        ConditionTask.setText(condit);
    }
    MyClass NaborAcc = new MyClass();
    LinkedList<Double> promOtv = new LinkedList<>();
    public void ObnylNaborAcc(){
        condit="";
        NaborAcc.V=null;
        NaborAcc.W=null;
        NaborAcc.YR=null;
        NaborAcc.YS=null;
        NaborAcc.YV=null;
        NaborAcc.U=null;
        NaborAcc.MK=null;
        NaborAcc.Bem=null;
        NaborAcc.Ben=null;
        NaborAcc.dM=null;
        NaborAcc.ZMPY=null;
        NaborAcc.x=null;
        NaborAcc.Rad=null;
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
    public double makeYgol180_180(double x){
        if (x>180){
            x=x-360;
        }
        if (x<-180){
            x=x+360;
        }
        return x;
    }
    public static double getRandomIntegerBetweenRange(double min, double max){
        return (int)(Math.random()*((max-min)+1))+min;
    }
}
