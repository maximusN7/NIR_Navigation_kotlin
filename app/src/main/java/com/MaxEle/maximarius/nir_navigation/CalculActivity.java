package com.MaxEle.maximarius.nir_navigation;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.LinkedList;


public class CalculActivity extends AppCompatActivity {

    Spinner spinType;
    String spinText;
    String spinText1;
    String spinText2;

    String AnswerText;
    double AnswerCalc;

    Spinner spinHard;
    Spinner spinValue;
    TextView Answ;
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
    EditText desIm1;
    EditText desIm2;
    EditText desIm3;
    EditText desIm4;
    EditText desIm5;
    EditText desIm6;
    EditText desIm7;
    EditText desIm8;
    EditText desIm9;
    EditText desIm10;
    EditText desIm11;
    TextView desttext1;
    TextView desttext2;
    TextView desttext3;
    TextView desttext4;
    TextView desttext5;
    TextView desttext6;
    TextView desttext7;
    TextView desttext8;
    TextView desttext9;
    TextView desttext10;
    TextView desttext11;

    Boolean ValueRUS = true;
    Boolean isHard = false;
    int WhatRybZad;

    int[][] arrShat = {{0, 300, 400, 500, 600, 700, 800, 900}, {2000, 1, 2, 3, 4, 7, 9, 10}, {4000, 2, 4, 6, 10, 16, 23, 24}, {6000, 3, 6, 11, 18, 27, 39, 40}, {8000, 4, 9, 17, 28, 41, 53, 54}, {10000, 6, 13, 24, 40, 56, 80, 81}, {12000, 9, 19, 34, 56, 78, 98, 99}, {14000, 12, 25, 48, 73, 97, 118, 119}};

    SharedPreferences mDataFiles;
    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_PREMIUM = "premakk";
    public static final String DATA_FILE_LEVEL = "level";
    public static final String DATA_FILE_FIRSTENTER_PREM = "firstentprem";
    public static final String DATA_FILE_MY_TASKS = "my_tasks";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";
    boolean PremAkk;
    boolean isFirstEnter;
    int AvaLevel;
    boolean Theme_Light;

    boolean MyTaskChoosen = false;
    ArrayList<String> listoftasks = new ArrayList<>();
    LinkedList<String> splitedTasks= new LinkedList<>();
    Spinner spinMyType;

    static final String TAG = "CalculActivity";

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

        setContentView(R.layout.activity_calcul);

        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);
        PremAkk = mDataFiles.getBoolean(DATA_FILE_PREMIUM, false);

        AdView mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        }else{
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        ScrollView viewansw = findViewById(R.id.viewansw);
        if (Theme_Light){
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            viewansw = findViewById(R.id.viewcond);
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview));
        }else {
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
            viewansw = findViewById(R.id.viewcond);
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
        }

        AvaLevel = mDataFiles.getInt(DATA_FILE_LEVEL, 0);

        spinHard = findViewById(R.id.spinner);
        spinValue = findViewById(R.id.spinner2);
        spinType = findViewById(R.id.taskslist);

        if (mDataFiles.contains(DATA_FILE_FIRSTENTER_PREM)) {
            isFirstEnter = mDataFiles.getBoolean(DATA_FILE_FIRSTENTER_PREM, true);
        } else {
            isFirstEnter = true;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putBoolean(DATA_FILE_FIRSTENTER_PREM, isFirstEnter);
            editor.apply();
        }

        spinMyType = findViewById(R.id.mytaskslist);

       /* String AllMyTasks = mDataFiles.getString(DATA_FILE_MY_TASKS,"");
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
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinMyType.setAdapter(adapter);

            spinMyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent,
                                           View itemSelected, int selectedItemPosition, long selectedId) {

                    MyTaskChoosen=true;
                    String s;
                    MakeINVISIBLEALL();
                    if (AvaLevel<7){
                        s = getResources().getString(R.string.toastNotAvailMyTask);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinMyType.setSelection(0);
                    }
                    spinText = spinType.getSelectedItem().toString();
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        }else{
            spinMyType.setVisibility(View.INVISIBLE);
        }
*/
        final String[] typetask = getResources().getStringArray(R.array.taskstypescalc);
        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String s;
                spinText = spinType.getSelectedItem().toString();
                if (spinText.equals(typetask[0])) {
                    DescPoiskYglaRazvorota();
                }
                if (spinText.equals(typetask[1])) {
                    DescPoiskVremeniRazvorota();
                }
                if (spinText.equals(typetask[2])) {
                    DescPoiskLYR();
                }

                if (spinText.equals(typetask[3])) {
                    if (PremAkk) {
                        DescPoiskPytevoiSkorosti();
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[4])) {
                    if (PremAkk) {
                        DescPoiskSkorostVetra();
                        WhatRybZad = 5;
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[5])) {
                    if (PremAkk) {
                        DescPoiskNapravleniaVetra();
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }

                if (spinText.equals(typetask[6])) {
                    if (PremAkk) {
                        DescPoiskKyrsi();
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[7])) {
                    if (PremAkk) {
                        DescPoiskFIPY();
                        WhatRybZad = 4;
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[8])) {
                    if (PremAkk) {
                        DescPoiskKyrsiPoVetry();
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }

                if (spinText.equals(typetask[9])) {
                    if (PremAkk) {
                        DescPoiskZadRybesha();
                        WhatRybZad = 1;
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[10])) {

                    if (PremAkk) {
                        DescPoiskZadRybeshaOgr();
                        WhatRybZad = 2;
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[11])) {
                    if (PremAkk) {
                        DescPoiskZadRybeshaIzmen();
                        WhatRybZad = 3;
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }

                if (spinText.equals(typetask[12])) {
                    if (PremAkk) {
                        DescPoiskIstenSkorost();
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[13])) {
                    if (PremAkk) {
                        DescPoiskPriborSkorost();
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[14])) {
                    if (PremAkk) {
                        DescPoiskIstenSkorostKYS();
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }

                if (spinText.equals(typetask[15])) {
                    if (PremAkk) {
                        DescPoiskIsprPutLBYmB();
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[16])) {
                    if (PremAkk) {
                        DescPoiskIsprPutLBYbB();
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
                if (spinText.equals(typetask[17])) {
                    if (PremAkk) {
                        DescPoiskIsprPutLBYbB2();
                    } else {
                        s = getResources().getString(R.string.toastNotAvailPrem);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                        spinType.setSelection(0);
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final String[] typevalue = getResources().getStringArray(R.array.valuestypes);
        spinValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                spinText1 = spinValue.getSelectedItem().toString();
                if (spinText1.equals(typevalue[0])) {
                    ValueRUS = true;
                    if (WhatRybZad == 1) {
                        DescPoiskZadRybesha();
                    }
                    if (WhatRybZad == 2) {
                        DescPoiskZadRybeshaOgr();
                    }
                    if (WhatRybZad == 3) {
                        DescPoiskZadRybeshaIzmen();
                    }
                }
                if (spinText1.equals(typevalue[1])) {
                    ValueRUS = false;
                    if (WhatRybZad == 1) {
                        DescPoiskZadRybesha();
                    }
                    if (WhatRybZad == 2) {
                        DescPoiskZadRybeshaOgr();
                    }
                    if (WhatRybZad == 3) {
                        DescPoiskZadRybeshaIzmen();
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final String[] typehard = getResources().getStringArray(R.array.hardtypes);
        spinHard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                spinText2 = spinHard.getSelectedItem().toString();
                if (spinText2.equals(typehard[0])) {
                    isHard = false;
                    if (WhatRybZad == 3) {
                        DescPoiskZadRybeshaIzmen();
                    }
                    if (WhatRybZad == 4) {
                        DescPoiskFIPY();
                    }
                    if (WhatRybZad == 5) {
                        DescPoiskSkorostVetra();
                    }
                }
                if (spinText2.equals(typehard[1])) {
                    isHard = true;
                    if (WhatRybZad == 3) {
                        DescPoiskZadRybeshaIzmen();
                    }
                    if (WhatRybZad == 4) {
                        DescPoiskFIPY();
                    }
                    if (WhatRybZad == 5) {
                        DescPoiskSkorostVetra();
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        CalculActivity.this.finish();
        Intent intent = new Intent(CalculActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }
    public void onClickBack(View view) {
        CalculActivity.this.finish();
        Intent intent = new Intent(CalculActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }

    public void MakeINVISIBLEALL() {
        spinHard.setVisibility(View.INVISIBLE);
        spinValue.setVisibility(View.INVISIBLE);
        Answ = findViewById(R.id.textViewCondition);
        Answ.setText(R.string.calculation);
        destext1 = findViewById(R.id.textViewWhat1);
        destext2 = findViewById(R.id.textViewWhat2);
        destext3 = findViewById(R.id.textViewWhat3);
        destext4 = findViewById(R.id.textViewWhat4);
        destext5 = findViewById(R.id.textViewWhat5);
        destext6 = findViewById(R.id.textViewWhat6);
        destext7 = findViewById(R.id.textViewWhat7);
        destext8 = findViewById(R.id.textViewWhat8);
        destext9 = findViewById(R.id.textViewWhat9);
        destext10 = findViewById(R.id.textViewWhat10);
        destext11 = findViewById(R.id.textViewWhat11);
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
        desttext1 = findViewById(R.id.textViewEdIzm1);
        desttext2 = findViewById(R.id.textViewEdIzm2);
        desttext3 = findViewById(R.id.textViewEdIzm3);
        desttext4 = findViewById(R.id.textViewEdIzm4);
        desttext5 = findViewById(R.id.textViewEdIzm5);
        desttext6 = findViewById(R.id.textViewEdIzm6);
        desttext7 = findViewById(R.id.textViewEdIzm7);
        desttext8 = findViewById(R.id.textViewEdIzm8);
        desttext9 = findViewById(R.id.textViewEdIzm9);
        desttext10 = findViewById(R.id.textViewEdIzm10);
        desttext11 = findViewById(R.id.textViewEdIzm11);
        desttext1.setVisibility(View.INVISIBLE);
        desttext2.setVisibility(View.INVISIBLE);
        desttext3.setVisibility(View.INVISIBLE);
        desttext4.setVisibility(View.INVISIBLE);
        desttext5.setVisibility(View.INVISIBLE);
        desttext6.setVisibility(View.INVISIBLE);
        desttext7.setVisibility(View.INVISIBLE);
        desttext8.setVisibility(View.INVISIBLE);
        desttext9.setVisibility(View.INVISIBLE);
        desttext10.setVisibility(View.INVISIBLE);
        desttext11.setVisibility(View.INVISIBLE);
        desIm1 = findViewById(R.id.textViewEnterParam1);
        desIm2 = findViewById(R.id.textViewEnterParam2);
        desIm3 = findViewById(R.id.textViewEnterParam3);
        desIm4 = findViewById(R.id.textViewEnterParam4);
        desIm5 = findViewById(R.id.textViewEnterParam5);
        desIm6 = findViewById(R.id.textViewEnterParam6);
        desIm7 = findViewById(R.id.textViewEnterParam7);
        desIm8 = findViewById(R.id.textViewEnterParam8);
        desIm9 = findViewById(R.id.textViewEnterParam9);
        desIm10 = findViewById(R.id.textViewEnterParam10);
        desIm11 = findViewById(R.id.textViewEnterParam11);
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
        desIm1.setText("");
        desIm2.setText("");
        desIm3.setText("");
        desIm4.setText("");
        desIm5.setText("");
        desIm6.setText("");
        desIm7.setText("");
        desIm8.setText("");
        desIm9.setText("");
        desIm10.setText("");
        desIm11.setText("");
    }
    public void CalculateTask(View view) {
        EditText editText = findViewById(R.id.textViewEnterParam1);
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        String[] typetask = getResources().getStringArray(R.array.taskstypescalc);
        TextView Answ = findViewById(R.id.textViewCondition);
        Answ.setText(R.string.calculation);
        spinText = spinType.getSelectedItem().toString();
        if (spinText.equals(typetask[0])) {
            ZadPoiskYglaRazvorota();
        }
        if (spinText.equals(typetask[1])) {
            ZadPoiskVremeniRazvorota();
        }
        if (spinText.equals(typetask[2])) {
            ZadPoiskLYR();
        }

        if (spinText.equals(typetask[3])) {
            ZadPoiskPytevoiSkorosti();
        }
        if (spinText.equals(typetask[4])) {
            ZadPoiskSkorostVetra();
        }
        if (spinText.equals(typetask[5])) {
            ZadPoiskNapravleniaVetra();
        }

        if (spinText.equals(typetask[6])) {
            ZadPoiskKyrsi();
        }
        if (spinText.equals(typetask[7])) {
            ZadPoiskFIPY();
        }
        if (spinText.equals(typetask[8])) {
            ZadPoiskKyrsiPoVetry();
        }

        if (spinText.equals(typetask[9])) {
            ZadPoiskZadRybesha();
        }
        if (spinText.equals(typetask[10])) {
            ZadPoiskZadRybeshaOgr();
        }
        if (spinText.equals(typetask[11])) {
            ZadPoiskZadRybeshaIzmen();
        }

        if (spinText.equals(typetask[12])) {
            ZadPoiskIstenSkorost();
        }
        if (spinText.equals(typetask[13])) {
            ZadPoiskPriborSkorost();
        }
        if (spinText.equals(typetask[14])) {
            ZadPoiskIstenSkorostKYS();
        }

        if (spinText.equals(typetask[15])) {
            ZadPoiskIsprPutLBYmB();
        }
        if (spinText.equals(typetask[16])) {
            ZadPoiskIsprPutLBYbB();
        }
        if (spinText.equals(typetask[17])) {
            ZadPoiskIsprPutLBYbB2();
        }
    }

    //---------------------------------------------------------------------
    public void DescPoiskYglaRazvorota() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalSkorRad);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_kmch);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.Calkren);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_grad);
        desIm2.setVisibility(View.VISIBLE);
    }
    private void DescPoiskVremeniRazvorota() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalSkorRad);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_kmch);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.Calkren);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_grad);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalYR);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_grad);
        desIm3.setVisibility(View.VISIBLE);
    }
    private void DescPoiskLYR() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalSkorRad);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_kmch);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.Calkren);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_grad);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalYR);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_grad);
        desIm3.setVisibility(View.VISIBLE);
    }

    public void DescPoiskPytevoiSkorosti() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalZMPY);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_grad);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalNapVetrMe);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_grad);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalSkorRad);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_kmch);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalSkorVetr);
        desttext4.setVisibility(View.VISIBLE);
        desttext4.setText(R.string.typeizm_kmch);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalMagSkl);
        desttext5.setVisibility(View.VISIBLE);
        desttext5.setText(R.string.typeizm_grad);
        desIm5.setVisibility(View.VISIBLE);
        destext6.setVisibility(View.VISIBLE);
        destext6.setText(R.string.CalSobsh);
        desttext6.setVisibility(View.VISIBLE);
        desttext6.setText(R.string.typeizm_km);
        desIm6.setVisibility(View.VISIBLE);
    }
    public void DescPoiskSkorostVetra() {
        MakeINVISIBLEALL();
        spinHard.setVisibility(View.VISIBLE);
        if (!isHard) {
            destext1.setVisibility(View.VISIBLE);
            destext1.setText(R.string.CalSkorRad);
            desttext1.setVisibility(View.VISIBLE);
            desttext1.setText(R.string.typeizm_kmch);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            destext2.setText(R.string.CalPytSkor);
            desttext2.setVisibility(View.VISIBLE);
            desttext2.setText(R.string.typeizm_kmch);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            destext3.setText(R.string.CalYS);
            desttext3.setVisibility(View.VISIBLE);
            desttext3.setText(R.string.typeizm_grad);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            destext4.setText(R.string.CalMagKyr);
            desttext4.setVisibility(View.VISIBLE);
            desttext4.setText(R.string.typeizm_grad);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            destext5.setText(R.string.CalMagSkl);
            desttext5.setVisibility(View.VISIBLE);
            desttext5.setText(R.string.typeizm_grad);
            desIm5.setVisibility(View.VISIBLE);
        } else {
            destext1.setVisibility(View.VISIBLE);
            destext1.setText(R.string.CalSkorRad);
            desttext1.setVisibility(View.VISIBLE);
            desttext1.setText(R.string.typeizm_kmch);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            destext2.setText(R.string.CalSpr);
            desttext2.setVisibility(View.VISIBLE);
            desttext2.setText(R.string.typeizm_km);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            destext3.setText(R.string.CalZMPY);
            desttext3.setVisibility(View.VISIBLE);
            desttext3.setText(R.string.typeizm_grad);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            destext4.setText(R.string.CalMagKyr);
            desttext4.setVisibility(View.VISIBLE);
            desttext4.setText(R.string.typeizm_grad);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            destext5.setText(R.string.CalLBY);
            desttext5.setVisibility(View.VISIBLE);
            desttext5.setText(R.string.typeizm_km);
            desIm5.setVisibility(View.VISIBLE);
            destext6.setVisibility(View.VISIBLE);
            destext6.setText(R.string.CalMagSkl);
            desttext6.setVisibility(View.VISIBLE);
            desttext6.setText(R.string.typeizm_grad);
            desIm6.setVisibility(View.VISIBLE);
            destext7.setVisibility(View.VISIBLE);
            destext7.setText(R.string.Caltpr);
            desttext7.setVisibility(View.VISIBLE);
            desttext7.setText(R.string.typeizm_min);
            desIm7.setVisibility(View.VISIBLE);
        }
    }
    public void DescPoiskNapravleniaVetra() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalZMPY);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_grad);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalSkorRad);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_kmch);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalNapVetrMe);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_grad);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalSkorVetr);
        desttext4.setVisibility(View.VISIBLE);
        desttext4.setText(R.string.typeizm_kmch);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalMagSkl);
        desttext5.setVisibility(View.VISIBLE);
        desttext5.setText(R.string.typeizm_grad);
        desIm5.setVisibility(View.VISIBLE);
    }

    public void DescPoiskKyrsi() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalMagKyr);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_grad);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalIstKyr);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_grad);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalKomKyr);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_grad);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalOrtKyr);
        desttext4.setVisibility(View.VISIBLE);
        desttext4.setText(R.string.typeizm_grad);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalMagSkl);
        desttext5.setVisibility(View.VISIBLE);
        desttext5.setText(R.string.typeizm_grad);
        desIm5.setVisibility(View.VISIBLE);
        destext6.setVisibility(View.VISIBLE);
        destext6.setText(R.string.CalKomSkl);
        desttext6.setVisibility(View.VISIBLE);
        desttext6.setText(R.string.typeizm_grad);
        desIm6.setVisibility(View.VISIBLE);
        destext7.setVisibility(View.VISIBLE);
        destext7.setText(R.string.CalAziSkl);
        desttext7.setVisibility(View.VISIBLE);
        desttext7.setText(R.string.typeizm_grad);
        desIm7.setVisibility(View.VISIBLE);
        destext8.setVisibility(View.VISIBLE);
        destext8.setText(R.string.CalMysSkl);
        desttext8.setVisibility(View.VISIBLE);
        desttext8.setText(R.string.typeizm_grad);
        desIm8.setVisibility(View.VISIBLE);
        destext9.setVisibility(View.VISIBLE);
        destext9.setText(R.string.CalVarSkl);
        desttext9.setVisibility(View.VISIBLE);
        desttext9.setText(R.string.typeizm_grad);
        desIm9.setVisibility(View.VISIBLE);
    }
    public void DescPoiskFIPY() {
        MakeINVISIBLEALL();
        spinHard.setVisibility(View.VISIBLE);
        if (!isHard) {
            destext1.setVisibility(View.VISIBLE);
            destext1.setText(R.string.CalMagKyr);
            desttext1.setVisibility(View.VISIBLE);
            desttext1.setText(R.string.typeizm_grad);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            destext2.setText(R.string.CalIstKyr);
            desttext2.setVisibility(View.VISIBLE);
            desttext2.setText(R.string.typeizm_grad);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            destext3.setText(R.string.CalMagSkl);
            desttext3.setVisibility(View.VISIBLE);
            desttext3.setText(R.string.typeizm_grad);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            destext4.setText(R.string.CalYS);
            desttext4.setVisibility(View.VISIBLE);
            desttext4.setText(R.string.typeizm_grad);
            desIm4.setVisibility(View.VISIBLE);
        } else {
            destext1.setVisibility(View.VISIBLE);
            destext1.setText(R.string.CalZMPY);
            desttext1.setVisibility(View.VISIBLE);
            desttext1.setText(R.string.typeizm_grad);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            destext2.setText(R.string.CalZIPY);
            desttext2.setVisibility(View.VISIBLE);
            desttext2.setText(R.string.typeizm_grad);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            destext3.setText(R.string.CalMagSkl);
            desttext3.setVisibility(View.VISIBLE);
            desttext3.setText(R.string.typeizm_grad);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            destext4.setText(R.string.CalMagKyr);
            desttext4.setVisibility(View.VISIBLE);
            desttext4.setText(R.string.typeizm_grad);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            destext5.setText(R.string.CalIstKyr);
            desttext5.setVisibility(View.VISIBLE);
            desttext5.setText(R.string.typeizm_grad);
            desIm5.setVisibility(View.VISIBLE);
            destext6.setVisibility(View.VISIBLE);
            destext6.setText(R.string.CalLBY);
            desttext6.setVisibility(View.VISIBLE);
            desttext6.setText(R.string.typeizm_km);
            desIm6.setVisibility(View.VISIBLE);
            destext7.setVisibility(View.VISIBLE);
            destext7.setText(R.string.CalSpr);
            desttext7.setVisibility(View.VISIBLE);
            desttext7.setText(R.string.typeizm_km);
            desIm7.setVisibility(View.VISIBLE);
        }
    }
    public void DescPoiskKyrsiPoVetry() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalZIPY);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_grad);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalMagSkl);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_grad);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalNapVetrMe);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_grad);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalSkorRad);
        desttext4.setVisibility(View.VISIBLE);
        desttext4.setText(R.string.typeizm_kmch);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalSkorVetr);
        desttext5.setVisibility(View.VISIBLE);
        desttext5.setText(R.string.typeizm_kmch);
        desIm5.setVisibility(View.VISIBLE);
    }

    public void DescPoiskZadRybesha() {
        MakeINVISIBLEALL();
        spinValue.setVisibility(View.VISIBLE);
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalHesh);
        desttext1.setVisibility(View.VISIBLE);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalHkr);
        desttext2.setVisibility(View.VISIBLE);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalPaer);
        desttext3.setVisibility(View.VISIBLE);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalVvert);
        desttext4.setVisibility(View.VISIBLE);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalPytSkor);
        desttext5.setVisibility(View.VISIBLE);
        desIm5.setVisibility(View.VISIBLE);

        if (ValueRUS) {
            desttext1.setText(R.string.typeizm_m);
            desttext2.setText(R.string.typeizm_m);
            desttext3.setText(R.string.typeizm_mmrtst);
            desttext4.setText(R.string.typeizm_mc);
            desttext5.setText(R.string.typeizm_kmch);
        } else {
            desttext1.setText(R.string.typeizm_fyt);
            desttext2.setText(R.string.typeizm_m);
            desttext3.setText(R.string.typeizm_gpa);
            desttext4.setText(R.string.typeizm_fytmin);
            desttext5.setText(R.string.typeizm_yzl);
        }
    }
    public void DescPoiskZadRybeshaOgr() {
        MakeINVISIBLEALL();
        spinValue.setVisibility(View.VISIBLE);
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalHesh);
        desttext1.setVisibility(View.VISIBLE);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalH1);
        desttext2.setVisibility(View.VISIBLE);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalHkr);
        desttext3.setVisibility(View.VISIBLE);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalPaer);
        desttext4.setVisibility(View.VISIBLE);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalVvert1);
        desttext5.setVisibility(View.VISIBLE);
        desIm5.setVisibility(View.VISIBLE);
        destext6.setVisibility(View.VISIBLE);
        destext6.setText(R.string.CalW1);
        desttext6.setVisibility(View.VISIBLE);
        desIm6.setVisibility(View.VISIBLE);
        destext7.setVisibility(View.VISIBLE);
        destext7.setText(R.string.CalVvert2);
        desttext7.setVisibility(View.VISIBLE);
        desIm7.setVisibility(View.VISIBLE);
        destext8.setVisibility(View.VISIBLE);
        destext8.setText(R.string.CalW2);
        desttext8.setVisibility(View.VISIBLE);
        desIm8.setVisibility(View.VISIBLE);

        if (ValueRUS) {
            desttext1.setText(R.string.typeizm_m);
            desttext2.setText(R.string.typeizm_m);
            desttext3.setText(R.string.typeizm_m);
            desttext4.setText(R.string.typeizm_mmrtst);
            desttext5.setText(R.string.typeizm_mc);
            desttext6.setText(R.string.typeizm_kmch);
            desttext7.setText(R.string.typeizm_mc);
            desttext8.setText(R.string.typeizm_kmch);
        } else {
            desttext1.setText(R.string.typeizm_fyt);
            desttext2.setText(R.string.typeizm_fyt);
            desttext3.setText(R.string.typeizm_m);
            desttext4.setText(R.string.typeizm_gpa);
            desttext5.setText(R.string.typeizm_fytmin);
            desttext6.setText(R.string.typeizm_yzl);
            desttext7.setText(R.string.typeizm_fytmin);
            desttext8.setText(R.string.typeizm_yzl);
        }
    }
    public void DescPoiskZadRybeshaIzmen() {
        MakeINVISIBLEALL();
        spinValue.setVisibility(View.VISIBLE);
        spinHard.setVisibility(View.VISIBLE);
        if (!isHard) {
            destext1.setVisibility(View.VISIBLE);
            destext1.setText(R.string.CalHesh);
            desttext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            destext2.setText(R.string.CalHkr);
            desttext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            destext3.setText(R.string.CalPaer);
            desttext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            destext4.setText(R.string.CalVvert);
            desttext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            destext5.setText(R.string.CalPytSkor);
            desttext5.setVisibility(View.VISIBLE);
            desIm5.setVisibility(View.VISIBLE);
            destext6.setVisibility(View.VISIBLE);
            destext6.setText(R.string.CalHzad);
            desttext6.setVisibility(View.VISIBLE);
            desIm6.setVisibility(View.VISIBLE);
            destext7.setVisibility(View.VISIBLE);
            destext7.setText(R.string.CalHper);
            desttext7.setVisibility(View.VISIBLE);
            desIm7.setVisibility(View.VISIBLE);
            destext8.setVisibility(View.VISIBLE);
            destext8.setText(R.string.CalSzad);
            desttext8.setVisibility(View.VISIBLE);
            desIm8.setVisibility(View.VISIBLE);

            if (ValueRUS) {
                desttext1.setText(R.string.typeizm_m);
                desttext2.setText(R.string.typeizm_m);
                desttext3.setText(R.string.typeizm_mmrtst);
                desttext4.setText(R.string.typeizm_mc);
                desttext5.setText(R.string.typeizm_kmch);
                desttext6.setText(R.string.typeizm_m);
                desttext7.setText(R.string.typeizm_m);
                desttext8.setText(R.string.typeizm_km);
            } else {
                desttext1.setText(R.string.typeizm_fyt);
                desttext2.setText(R.string.typeizm_m);
                desttext3.setText(R.string.typeizm_gpa);
                desttext4.setText(R.string.typeizm_fytmin);
                desttext5.setText(R.string.typeizm_yzl);
                desttext6.setText(R.string.typeizm_fyt);
                desttext7.setText(R.string.typeizm_fyt);
                desttext8.setText(R.string.typeizm_mmile);
            }
        } else {
            destext1.setVisibility(View.VISIBLE);
            destext1.setText(R.string.CalHesh);
            desttext1.setVisibility(View.VISIBLE);
            desIm1.setVisibility(View.VISIBLE);
            destext2.setVisibility(View.VISIBLE);
            destext2.setText(R.string.CalH1);
            desttext2.setVisibility(View.VISIBLE);
            desIm2.setVisibility(View.VISIBLE);
            destext3.setVisibility(View.VISIBLE);
            destext3.setText(R.string.CalHkr);
            desttext3.setVisibility(View.VISIBLE);
            desIm3.setVisibility(View.VISIBLE);
            destext4.setVisibility(View.VISIBLE);
            destext4.setText(R.string.CalPaer);
            desttext4.setVisibility(View.VISIBLE);
            desIm4.setVisibility(View.VISIBLE);
            destext5.setVisibility(View.VISIBLE);
            destext5.setText(R.string.CalVvert1);
            desttext5.setVisibility(View.VISIBLE);
            desIm5.setVisibility(View.VISIBLE);
            destext6.setVisibility(View.VISIBLE);
            destext6.setText(R.string.CalW1);
            desttext6.setVisibility(View.VISIBLE);
            desIm6.setVisibility(View.VISIBLE);
            destext7.setVisibility(View.VISIBLE);
            destext7.setText(R.string.CalVvert2);
            desttext7.setVisibility(View.VISIBLE);
            desIm7.setVisibility(View.VISIBLE);
            destext8.setVisibility(View.VISIBLE);
            destext8.setText(R.string.CalW2);
            desttext8.setVisibility(View.VISIBLE);
            desIm8.setVisibility(View.VISIBLE);
            destext9.setVisibility(View.VISIBLE);
            destext9.setText(R.string.CalHzad);
            desttext9.setVisibility(View.VISIBLE);
            desIm9.setVisibility(View.VISIBLE);
            destext10.setVisibility(View.VISIBLE);
            destext10.setText(R.string.CalHper);
            desttext10.setVisibility(View.VISIBLE);
            desIm10.setVisibility(View.VISIBLE);
            destext11.setVisibility(View.VISIBLE);
            destext11.setText(R.string.CalSzad);
            desttext11.setVisibility(View.VISIBLE);
            desIm11.setVisibility(View.VISIBLE);

            if (ValueRUS) {
                desttext1.setText(R.string.typeizm_m);
                desttext2.setText(R.string.typeizm_m);
                desttext3.setText(R.string.typeizm_m);
                desttext4.setText(R.string.typeizm_mmrtst);
                desttext5.setText(R.string.typeizm_mc);
                desttext6.setText(R.string.typeizm_kmch);
                desttext7.setText(R.string.typeizm_mc);
                desttext8.setText(R.string.typeizm_kmch);
                desttext9.setText(R.string.typeizm_m);
                desttext10.setText(R.string.typeizm_m);
                desttext11.setText(R.string.typeizm_km);
            } else {
                desttext1.setText(R.string.typeizm_fyt);
                desttext2.setText(R.string.typeizm_fyt);
                desttext3.setText(R.string.typeizm_m);
                desttext4.setText(R.string.typeizm_gpa);
                desttext5.setText(R.string.typeizm_fytmin);
                desttext6.setText(R.string.typeizm_yzl);
                desttext7.setText(R.string.typeizm_fytmin);
                desttext8.setText(R.string.typeizm_yzl);
                desttext9.setText(R.string.typeizm_fyt);
                desttext10.setText(R.string.typeizm_fyt);
                desttext11.setText(R.string.typeizm_mmile);
            }
        }
    }

    public void DescPoiskIstenSkorost() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalVis);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_m);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalPribSc);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_kmch);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalPoprav);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_kmch);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalTnaEsh);
        desttext4.setVisibility(View.VISIBLE);
        desttext4.setText(R.string.typeizm_grad);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalTnaTerm);
        desttext5.setVisibility(View.VISIBLE);
        desttext5.setText(R.string.typeizm_grad);
        desIm5.setVisibility(View.VISIBLE);
    }
    public void DescPoiskPriborSkorost() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalVis);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_m);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalIstinSc);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_kmch);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalPoprav);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_kmch);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalTnaEsh);
        desttext4.setVisibility(View.VISIBLE);
        desttext4.setText(R.string.typeizm_grad);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalTnaTerm);
        desttext5.setVisibility(View.VISIBLE);
        desttext5.setText(R.string.typeizm_grad);
        desIm5.setVisibility(View.VISIBLE);
    }
    public void DescPoiskIstenSkorostKYS() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalVis);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_m);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalPribScKYS);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_kmch);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalPoprav);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_kmch);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalTnaEsh);
        desttext4.setVisibility(View.VISIBLE);
        desttext4.setText(R.string.typeizm_grad);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalTnaTerm);
        desttext5.setVisibility(View.VISIBLE);
        desttext5.setText(R.string.typeizm_grad);
        desIm5.setVisibility(View.VISIBLE);
    }

    public void DescPoiskIsprPutLBYmB() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalZMPY);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_grad);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalMagKyr);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_grad);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalKYR);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_grad);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalSobsh);
        desttext4.setVisibility(View.VISIBLE);
        desttext4.setText(R.string.typeizm_km);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalSpr);
        desttext5.setVisibility(View.VISIBLE);
        desttext5.setText(R.string.typeizm_km);
        desIm5.setVisibility(View.VISIBLE);
        destext6.setVisibility(View.VISIBLE);
        destext6.setText(R.string.Caltpr);
        desttext6.setVisibility(View.VISIBLE);
        desttext6.setText(R.string.typeizm_min);
        desIm6.setVisibility(View.VISIBLE);
        destext7.setVisibility(View.VISIBLE);
        destext7.setText(R.string.CalW);
        desttext7.setVisibility(View.VISIBLE);
        desttext7.setText(R.string.typeizm_kmch);
        desIm7.setVisibility(View.VISIBLE);
        destext8.setVisibility(View.VISIBLE);
        destext8.setText(R.string.CalYVvih);
        desttext8.setVisibility(View.VISIBLE);
        desttext8.setText(R.string.typeizm_grad);
        desIm8.setVisibility(View.VISIBLE);
    }
    public void DescPoiskIsprPutLBYbB() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalZMPY);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_grad);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalMagKyr);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_grad);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.CalKYR);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_grad);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalSobsh);
        desttext4.setVisibility(View.VISIBLE);
        desttext4.setText(R.string.typeizm_km);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalSpr);
        desttext5.setVisibility(View.VISIBLE);
        desttext5.setText(R.string.typeizm_km);
        desIm5.setVisibility(View.VISIBLE);
        destext6.setVisibility(View.VISIBLE);
        destext6.setText(R.string.Caltpr);
        desttext6.setVisibility(View.VISIBLE);
        desttext6.setText(R.string.typeizm_min);
        desIm6.setVisibility(View.VISIBLE);
        destext7.setVisibility(View.VISIBLE);
        destext7.setText(R.string.CalW);
        desttext7.setVisibility(View.VISIBLE);
        desttext7.setText(R.string.typeizm_kmch);
        desIm7.setVisibility(View.VISIBLE);
        destext8.setVisibility(View.VISIBLE);
        destext8.setText(R.string.CalYVvih);
        desttext8.setVisibility(View.VISIBLE);
        desttext8.setText(R.string.typeizm_grad);
        desIm8.setVisibility(View.VISIBLE);
    }
    public void DescPoiskIsprPutLBYbB2() {
        MakeINVISIBLEALL();
        destext1.setVisibility(View.VISIBLE);
        destext1.setText(R.string.CalSobsh);
        desttext1.setVisibility(View.VISIBLE);
        desttext1.setText(R.string.typeizm_km);
        desIm1.setVisibility(View.VISIBLE);
        destext2.setVisibility(View.VISIBLE);
        destext2.setText(R.string.CalSpr);
        desttext2.setVisibility(View.VISIBLE);
        desttext2.setText(R.string.typeizm_km);
        desIm2.setVisibility(View.VISIBLE);
        destext3.setVisibility(View.VISIBLE);
        destext3.setText(R.string.Caltpr);
        desttext3.setVisibility(View.VISIBLE);
        desttext3.setText(R.string.typeizm_min);
        desIm3.setVisibility(View.VISIBLE);
        destext4.setVisibility(View.VISIBLE);
        destext4.setText(R.string.CalW);
        desttext4.setVisibility(View.VISIBLE);
        desttext4.setText(R.string.typeizm_kmch);
        desIm4.setVisibility(View.VISIBLE);
        destext5.setVisibility(View.VISIBLE);
        destext5.setText(R.string.CalLBY);
        desttext5.setVisibility(View.VISIBLE);
        desttext5.setText(R.string.typeizm_km);
        desIm5.setVisibility(View.VISIBLE);
        destext6.setVisibility(View.VISIBLE);
        destext6.setText(R.string.CalBY);
        desttext6.setVisibility(View.VISIBLE);
        desttext6.setText(R.string.typeizm_grad);
        desIm6.setVisibility(View.VISIBLE);
        destext7.setVisibility(View.VISIBLE);
        destext7.setText(R.string.CalDP);
        desttext7.setVisibility(View.VISIBLE);
        desttext7.setText(R.string.typeizm_grad);
        desIm7.setVisibility(View.VISIBLE);
    }
    //---------------------------------------------------------------------------------------------

    public void ZadPoiskYglaRazvorota() {
        double V = 0;
        double x = 0;
        double g = 9.80665;
        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            V = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            x = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        if (allright) {
            AnswerCalc = V * V * 10 * 10 / 36 / 36 / g / Math.tan(Math.toRadians(x)) / 1000;
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_km);
            s = s + getResources().getString(R.string.CalcAns1) + AnswerText;
            Answ.setText(s);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskVremeniRazvorota() {
        double V = 0;
        double x = 0;
        double YR = 0;
        double Rad;
        double g = 9.80665;
        double Pi = 3.14159;
        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            V = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            x = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            YR = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }


        if (allright) {
            Rad = V * V * 10 * 10 / 36 / 36 / g / Math.tan(Math.toRadians(x)) / 1000;
            AnswerCalc = Pi * Rad * 100 * YR * 36 / 180 / V;
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_s);
            s = s + getResources().getString(R.string.CalcAns2_1) + Rad + getResources().getString(R.string.typeizm_km)+getResources().getString(R.string.CalcAns2_2) + AnswerText;
            Answ.setText(s);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskLYR() {
        double V = 0;
        double x = 0;
        double YR = 0;
        double Rad;
        double g = 9.80665;
        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            V = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            x = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            YR = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        if (allright) {
            Rad = V * V * 10 * 10 / 36 / 36 / g / Math.tan(Math.toRadians(x)) / 1000;
            AnswerCalc = Rad * Math.tan(Math.toRadians(YR / 2));
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_km);
            s = s + getResources().getString(R.string.CalcAns2_1) + Rad + getResources().getString(R.string.typeizm_km)+getResources().getString(R.string.CalcAns3_2) + AnswerText;
            Answ.setText(s);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void ZadPoiskPytevoiSkorosti() {
        double ZMPY = 0;
        double Bem = 0;
        double V = 0;
        double U = 0;
        double dM = 0;
        double S = 0;

        double YV;
        double YS;
        double Ben;
        double tp;
        double MKr;
        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            ZMPY = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Bem = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            V = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            U = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm5.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            dM = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm6.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            S = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        if (allright) {
            Ben = Bem - dM;
            if (Ben >= 180) {
                Ben = Ben - 180;
            } else {
                Ben = Ben + 180;
            }
            YV = Ben - ZMPY;
            if (YV > 180) {
                YV = YV - 360;
            }
            if (YV <= -180) {
                YV = YV + 360;
            }
            double Ali = U * Math.sin(Math.toRadians(YV)) / V;
            YS = Math.toDegrees(Math.asin(Ali));
            if (YV==0){
                AnswerCalc= V+U;
            }else {
                if (YV == 180) {
                    AnswerCalc= V-U;
                } else {
                    AnswerCalc = V * Math.sin(Math.toRadians(YV + YS)) / Math.sin(Math.toRadians(YV));
                }
            }

            tp = S * 60 / AnswerCalc;
            MKr = ZMPY - YS;
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_kmch);
            s = s + getResources().getString(R.string.CalcAns4_1)+ Ben + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CalcAns4_2) + YV + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CalcAns4_3) + String.format("%.2f", +YS) + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CalcAns4_4) + AnswerText + getResources().getString(R.string.CalcAns4_5) + String.format("%.2f", +tp) + getResources().getString(R.string.typeizm_min)+getResources().getString(R.string.CalcAns4_6) + String.format("%.2f", +MKr) + getResources().getString(R.string.typeizm_grad);
            Answ.setText(s);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskSkorostVetra() {
        double V = 0;
        double LBY = 0;
        double Spr = 0;
        double MK = 0;
        double dM = 0;
        double tpr = 0;
        double ZMPY = 0;

        double YV;
        double U;
        double Ben;
        double BY;

        double Uekv;
        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;


        if (isHard) {
            double YSr;
            double YSf;
            double W;

            stringParam = desIm1.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns) {
                V = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm2.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Spr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm3.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                ZMPY = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm4.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                MK = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm5.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                LBY = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm6.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                dM = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm7.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                tpr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }

            if (allright) {
                W = Spr * 60 / tpr;
                Uekv = W - V;
                YSr = ZMPY - MK;
                BY = Math.toDegrees(Math.atan(LBY / Spr));
                YSf = YSr + BY;
                U = Math.sqrt(V * V + W * W - 2 * V * W * Math.cos(Math.toRadians(YSf)));
                YV = Math.toDegrees(Math.asin(V * Math.sin(Math.toRadians(YSf)) / U));
                int PoprMaxa = 0;
                if (YV > 30 || YV < -30) {
                    PoprMaxa++;
                }
                if (YV > 40 || YV < -40) {
                    PoprMaxa++;
                }
                if (YV > 50 || YV < -50) {
                    PoprMaxa++;
                }
                if (YV > 60 || YV < -60) {
                    PoprMaxa++;
                }
                if (YV > 70 || YV < -70) {
                    PoprMaxa++;
                }
                if (YV > 80 || YV < -80) {
                    PoprMaxa++;
                }
                YV = YV - PoprMaxa;

                String SupText;
                if (W >= V) {
                    Ben = MK + YSf + YV;
                    SupText = getResources().getString(R.string.CalcAns5_1);
                    Ben = makeYgol0_360(Ben);
                } else {
                    Ben = MK + YSf - YV;
                    if (Ben >= 180) {
                        Ben = Ben - 180;
                    } else {
                        Ben = Ben + 180;
                    }
                    SupText = getResources().getString(R.string.CalcAns5_2);
                }

                if (Ben + dM < 180) {
                    AnswerCalc = Ben + dM + 180;
                } else {
                    AnswerCalc = Ben + dM - 180;
                }
                AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                s = s + getResources().getString(R.string.CalcAns5_3) + String.format("%.2f", +BY) + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CalcAns5_4) + YSr +getResources().getString(R.string.typeizm_grad) +getResources().getString(R.string.CalcAns5_5) + String.format("%.2f", +YSf) +getResources().getString(R.string.typeizm_grad)+ getResources().getString(R.string.CalcAns5_6) + String.format("%.2f", +W) + getResources().getString(R.string.typeizm_kmch)+getResources().getString(R.string.CalcAns5_7) + String.format("%.2f", +Uekv) + getResources().getString(R.string.typeizm_kmch)+getResources().getString(R.string.CalcAns5_8) + String.format("%.2f", +YV) + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CalcAns5_9) + String.format("%.2f", +U) + getResources().getString(R.string.typeizm_kmch)+getResources().getString(R.string.CalcAns5_10) + SupText + String.format("%.2f", +Ben) + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CalcAns5_11) + AnswerText;
                Answ.setText(s);
            } else {
                s = getResources().getString(R.string.toastWrongtypeing);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            double W = 0;
            double YS = 0;

            stringParam = desIm1.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns) {
                V = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm2.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                W = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm3.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                YS = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm4.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                MK = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm5.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                dM = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }

            if (allright) {
                Uekv = W - V;
                U = Math.sqrt(V * V + W * W - 2 * V * W * Math.cos(Math.toRadians(YS)));
                YV = Math.toDegrees(Math.asin(V * Math.sin(Math.toRadians(YS)) / U));
                int PoprMaxa = 0;
                if (YV > 30 || YV < -30) {
                    PoprMaxa++;
                }
                if (YV > 40 || YV < -40) {
                    PoprMaxa++;
                }
                if (YV > 50 || YV < -50) {
                    PoprMaxa++;
                }
                if (YV > 60 || YV < -60) {
                    PoprMaxa++;
                }
                if (YV > 70 || YV < -70) {
                    PoprMaxa++;
                }
                if (YV > 80 || YV < -80) {
                    PoprMaxa++;
                }
                YV = YV - PoprMaxa;

                String SupText;
                if (W >= V) {
                    Ben = MK + YS + YV;
                    SupText = getResources().getString(R.string.CalcAns5_1);;
                    Ben = makeYgol0_360(Ben);
                } else {
                    Ben = MK + YS - YV;
                    if (Ben >= 180) {
                        Ben = Ben - 180;
                    } else {
                        Ben = Ben + 180;
                    }
                    SupText = getResources().getString(R.string.CalcAns5_2);
                }

                if (Ben + dM < 180) {
                    AnswerCalc = Ben + dM + 180;
                } else {
                    AnswerCalc = Ben + dM - 180;
                }
                AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                s = s + getResources().getString(R.string.CalcAns5_3s) + Uekv + getResources().getString(R.string.typeizm_kmch)+getResources().getString(R.string.CalcAns5_4s) + String.format("%.2f", +YV) + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CalcAns5_5s) + String.format("%.2f", +U) + getResources().getString(R.string.typeizm_kmch)+getResources().getString(R.string.CalcAns5_6s) + SupText + String.format("%.2f", +Ben) + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CalcAns5_11) + AnswerText;
                Answ.setText(s);
            } else {
                s = getResources().getString(R.string.toastWrongtypeing);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void ZadPoiskNapravleniaVetra() {
        double ZMPY = 0;
        double Bem = 0;
        double V = 0;
        double U = 0;
        double dM = 0;

        double YV;
        double YS;
        double Ben;
        double tp;
        double MKr;
        double Uekv;
        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            ZMPY = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            V = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Bem = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            U = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm5.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            dM = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        if (allright) {
            Ben = Bem - dM;
            if (Ben >= 180) {
                Ben = Ben - 180;
            } else {
                Ben = Ben + 180;
            }
            YV = Ben - ZMPY;
            if (YV > 180) {
                YV = YV - 360;
            }
            if (YV <= -180) {
                YV = YV + 360;
            }
            double Ali = U * Math.sin(Math.toRadians(YV)) / V;
            YS = Math.toDegrees(Math.asin(Ali));
            AnswerCalc = V * Math.sin(Math.toRadians(YV + YS)) / Math.sin(Math.toRadians(YV));
            Uekv = AnswerCalc - V;
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_kmch);
            s = s + getResources().getString(R.string.CalcAns6_1) + Ben + getResources().getString(R.string.typeizm_grad)+ getResources().getString(R.string.CalcAns6_2) + YV + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CalcAns6_3) + String.format("%.2f", +YS) + getResources().getString(R.string.typeizm_grad)+getResources().getString(R.string.CalcAns6_4) + AnswerText + getResources().getString(R.string.CalcAns6_5) + String.format("%.2f", +Uekv) + getResources().getString(R.string.typeizm_kmch);
            Answ.setText(s);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void ZadPoiskKyrsi() {
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        double KK = 0;
        double MK = 0;
        double IK = 0;
        double OK = 0;

        double dK = 0;
        double dM = 0;
        double dA = 0;
        double d = 0;
        double dMy = 0;

        String stringParam;
        boolean isNumAns;
        boolean fl1 = false;
        boolean fl2 = false;
        boolean fl3 = false;
        boolean fl4 = false;
        boolean fl5 = false;
        boolean fl6 = false;
        boolean fl7 = false;
        boolean fl8 = false;
        boolean fl9 = false;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            MK = Double.parseDouble(stringParam);
            fl1 = true;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            IK = Double.parseDouble(stringParam);
            fl2 = true;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            KK = Double.parseDouble(stringParam);
            fl3 = true;
        }
        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            OK = Double.parseDouble(stringParam);
            fl4 = true;
        }
        stringParam = desIm5.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            dM = Double.parseDouble(stringParam);
            fl5 = true;
        }
        stringParam = desIm6.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            dK = Double.parseDouble(stringParam);
            fl6 = true;
        }
        stringParam = desIm7.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            dA = Double.parseDouble(stringParam);
            fl7 = true;
        }
        stringParam = desIm8.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            dMy = Double.parseDouble(stringParam);
            fl8 = true;
        }
        stringParam = desIm9.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            d = Double.parseDouble(stringParam);
            fl9 = true;
        }

        if (fl1 && fl2) {
            AnswerCalc = IK - MK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n"+getResources().getString(R.string.CondMagSkl)+" = " + AnswerText;
        }
        if (fl1 && fl3) {
            AnswerCalc = MK - KK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n"+getResources().getString(R.string.CondKomSkl)+" = " + AnswerText;
        }
        if (fl1 && fl4) {
            AnswerCalc = OK - MK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n"+getResources().getString(R.string.CondMysSkl)+" = " + AnswerText;
        }
        if (fl2 && fl3) {
            AnswerCalc = IK - KK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n"+getResources().getString(R.string.CondVarSkl)+" = " + AnswerText;
        }
        if (fl2 && fl4) {
            AnswerCalc = OK - IK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondAziSkl)+" = " + AnswerText;
        }

        if (fl1 && fl5) {
            AnswerCalc = MK + dM;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondIstKyr)+" = " + AnswerText;
        }
        if (fl1 && fl6) {
            AnswerCalc = MK - dK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondKomKyr)+" = " + AnswerText;
        }
        if (fl1 && fl8) {
            AnswerCalc = MK + dMy;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondOrtKyr)+" = " + AnswerText;
        }
        if (fl2 && fl5) {
            AnswerCalc = IK - dM;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondMagKyr)+" = " + AnswerText;
        }
        if (fl2 && fl7) {
            AnswerCalc = IK + dA;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondOrtKyr)+" = " + AnswerText;
        }
        if (fl2 && fl9) {
            AnswerCalc = IK - d;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondKomKyr)+" = " + AnswerText;
        }
        if (fl3 && fl6) {
            AnswerCalc = KK + dK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondMagKyr)+" = " + AnswerText;
        }
        if (fl3 && fl9) {
            AnswerCalc = KK + d;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondIstKyr)+" = " + AnswerText;
        }
        if (fl4 && fl7) {
            AnswerCalc = OK - dA;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondIstKyr)+" = " + AnswerText;
        }
        if (fl4 && fl8) {
            AnswerCalc = OK - dMy;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondMagKyr)+" = " + AnswerText;
        }
        if (fl1 && fl7 && fl8) {
            AnswerCalc = MK + dMy - dA;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondIstKyr)+" = " + AnswerText;
        }
        if (fl1 && fl5 && fl7) {
            AnswerCalc = MK + dM + dA;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondOrtKyr)+" = " + AnswerText;
        }
        if (fl1 && fl5 && fl9) {
            AnswerCalc = MK + dM - d;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondKomKyr)+" = " + AnswerText;
        }
        if (fl1 && fl6 && fl9) {
            AnswerCalc = MK - dK + d;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondIstKyr)+" = " + AnswerText;
        }
        if (fl2 && fl6 && fl9) {
            AnswerCalc = IK - d + dK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondMagKyr)+" = " + AnswerText;
        }
        if (fl2 && fl7 && fl8) {
            AnswerCalc = IK + dA - dMy;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondMagKyr)+" = " + AnswerText;
        }
        if (fl2 && fl5 && fl6) {
            AnswerCalc = IK - dM - dK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondKomKyr)+" = " + AnswerText;
        }
        if (fl2 && fl5 && fl8) {
            AnswerCalc = IK - dM + dMy;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondOrtKyr)+" = " + AnswerText;
        }
        if (fl3 && fl5 && fl9) {
            AnswerCalc = KK + d - dM;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondMagKyr)+" = " + AnswerText;
        }
        if (fl3 && fl7 && fl9) {
            AnswerCalc = KK + d + dA;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondOrtKyr)+" = " + AnswerText;
        }
        if (fl3 && fl5 && fl6) {
            AnswerCalc = KK + dK + dM;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondIstKyr)+" = " + AnswerText;
        }
        if (fl3 && fl6 && fl8) {
            AnswerCalc = KK + dK + dMy;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondOrtKyr)+" = " + AnswerText;
        }
        if (fl4 && fl6 && fl7) {
            AnswerCalc = OK - dA - dM;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondMagKyr)+" = " + AnswerText;
        }
        if (fl4 && fl9 && fl7) {
            AnswerCalc = OK - d - dK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondKomKyr)+" = " + AnswerText;
        }
        if (fl4 && fl6 && fl8) {
            AnswerCalc = OK - dA - dMy;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondKomKyr)+" = " + AnswerText;
        }
        if (fl4 && fl5 && fl8) {
            AnswerCalc = OK - dMy + dM;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondIstKyr)+" = " + AnswerText;
        }

        if (fl1 && fl6 && fl9 && fl7) {
            AnswerCalc = MK - dK + d + dA;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondOrtKyr)+" = " + AnswerText;
        }
        if (fl2 && fl6 && fl8 && fl7) {
            AnswerCalc = IK + dA - dMy - dK;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondKomKyr)+" = " + AnswerText;
        }
        if (fl3 && fl5 && fl6 && fl7) {
            AnswerCalc = KK + dK + dM + dA;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondOrtKyr)+" = " + AnswerText;
        }
        if (fl3 && fl7 && fl8 && fl9) {
            AnswerCalc = KK + d - dMy + dA;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondMagKyr)+" = " + AnswerText;
        }
        if (fl4 && fl5 && fl6 && fl7) {
            AnswerCalc = OK - dK - dM - dA;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondKomKyr)+" = " + AnswerText;
        }
        if (fl4 && fl6 && fl8 && fl9) {
            AnswerCalc = OK - dK - dMy + d;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc < 0) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            s = s + "\n" +getResources().getString(R.string.CondIstKyr)+" = " + AnswerText;
        }

        Answ.setText(s);
        if (s.equals(getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation))) {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskFIPY() {
        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;

        double MK = 0;
        double IK = 400;
        double dM = 0;
        double YS = 0;

        if (!isHard) {
            stringParam = desIm1.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns) {
                MK = Double.parseDouble(stringParam);
                allright = true;
            } else {
                stringParam = desIm2.getText().toString();
                isNumAns = isNumeric(stringParam);
                if (isNumAns) {
                    IK = Double.parseDouble(stringParam);
                    allright = true;
                } else {
                    allright = false;
                }
            }
            stringParam = desIm3.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                dM = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm4.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                YS = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }

            if (allright) {
                if (IK == 400) {
                    AnswerCalc = MK + dM + YS;
                    if (AnswerCalc >= 360) {
                        AnswerCalc = AnswerCalc - 360;
                    }
                    if (AnswerCalc < 0) {
                        AnswerCalc = AnswerCalc + 360;
                    }
                    AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                    s = s + "\n"+getResources().getString(R.string.CondFIPY)+" = " + AnswerText;
                    AnswerCalc = MK + YS;
                    if (AnswerCalc >= 360) {
                        AnswerCalc = AnswerCalc - 360;
                    }
                    if (AnswerCalc < 0) {
                        AnswerCalc = AnswerCalc + 360;
                    }
                    AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                    s = s + "\n"+getResources().getString(R.string.CondFMPY)+" = " + AnswerText;
                } else {
                    AnswerCalc = IK - dM + YS;
                    if (AnswerCalc >= 360) {
                        AnswerCalc = AnswerCalc - 360;
                    }
                    if (AnswerCalc < 0) {
                        AnswerCalc = AnswerCalc + 360;
                    }
                    AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                    s = s + "\n"+getResources().getString(R.string.CondFMPY)+" = " + AnswerText;
                    AnswerCalc = IK + YS;
                    if (AnswerCalc >= 360) {
                        AnswerCalc = AnswerCalc - 360;
                    }
                    if (AnswerCalc < 0) {
                        AnswerCalc = AnswerCalc + 360;
                    }
                    AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                    s = s + "\n"+getResources().getString(R.string.CondFIPY)+" = " + AnswerText;
                }
                Answ.setText(s);
            } else {
                s = getResources().getString(R.string.toastWrongtypeing);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            double ZIPY = 400;
            double ZMPY = 0;
            double LBY = 0;
            double Spr = 0;

            stringParam = desIm1.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns) {
                ZMPY = Double.parseDouble(stringParam);
                allright = true;
            } else {
                stringParam = desIm2.getText().toString();
                isNumAns = isNumeric(stringParam);
                if (isNumAns) {
                    ZIPY = Double.parseDouble(stringParam);
                    allright = true;
                } else {
                    allright = false;
                }
            }
            stringParam = desIm3.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                dM = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm4.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                MK = Double.parseDouble(stringParam);
                allright = true;
            } else {
                stringParam = desIm5.getText().toString();
                isNumAns = isNumeric(stringParam);
                if (isNumAns && allright) {
                    IK = Double.parseDouble(stringParam);
                    allright = true;
                } else {
                    allright = false;
                }
            }
            stringParam = desIm6.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                LBY = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm7.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Spr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }

            if (allright) {
                double YSr;
                if (IK == 400) {
                    if (ZIPY == 400) {
                        YSr = ZMPY - MK;
                        s = s + "\n"+getResources().getString(R.string.CalcAns8_1) + YSr + getResources().getString(R.string.typeizm_grad)+"\n";
                        YS = YSr + Math.toDegrees(Math.atan(LBY / Spr));
                        s = s + "\n"+getResources().getString(R.string.CalcAns8_2) + YS + getResources().getString(R.string.typeizm_grad)+"\n";
                        AnswerCalc = MK + YS;
                        if (AnswerCalc >= 360) {
                            AnswerCalc = AnswerCalc - 360;
                        }
                        if (AnswerCalc < 0) {
                            AnswerCalc = AnswerCalc + 360;
                        }
                        AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                        s = s + "\n"+getResources().getString(R.string.CondFMPY)+" = " + AnswerText;
                        AnswerCalc = MK + dM + YS;
                        if (AnswerCalc >= 360) {
                            AnswerCalc = AnswerCalc - 360;
                        }
                        if (AnswerCalc < 0) {
                            AnswerCalc = AnswerCalc + 360;
                        }
                    } else {
                        YSr = ZIPY - dM - MK;
                        s = s + "\n"+getResources().getString(R.string.CalcAns8_1) + YSr + getResources().getString(R.string.typeizm_grad)+"\n";
                        YS = YSr + Math.toDegrees(Math.atan(LBY / Spr));
                        s = s + "\n"+getResources().getString(R.string.CalcAns8_2) + YS + getResources().getString(R.string.typeizm_grad)+"\n";
                        AnswerCalc = MK + YS;
                        if (AnswerCalc >= 360) {
                            AnswerCalc = AnswerCalc - 360;
                        }
                        if (AnswerCalc < 0) {
                            AnswerCalc = AnswerCalc + 360;
                        }
                        AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                        s = s + "\n"+getResources().getString(R.string.CondFMPY)+" = " + AnswerText;
                        AnswerCalc = MK + dM + YS;
                        if (AnswerCalc >= 360) {
                            AnswerCalc = AnswerCalc - 360;
                        }
                        if (AnswerCalc < 0) {
                            AnswerCalc = AnswerCalc + 360;
                        }
                    }
                } else {
                    if (ZIPY == 400) {
                        YSr = ZMPY + dM - IK;
                        s = s + "\n"+getResources().getString(R.string.CalcAns8_1) + YSr + getResources().getString(R.string.typeizm_grad)+"\n";
                        YS = YSr + Math.toDegrees(Math.atan(LBY / Spr));
                        s = s + "\n"+getResources().getString(R.string.CalcAns8_2) + YS + getResources().getString(R.string.typeizm_grad)+"\n";
                        AnswerCalc = IK - dM + YS;
                        if (AnswerCalc >= 360) {
                            AnswerCalc = AnswerCalc - 360;
                        }
                        if (AnswerCalc < 0) {
                            AnswerCalc = AnswerCalc + 360;
                        }
                        AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                        s = s + "\n"+getResources().getString(R.string.CondFMPY)+" = " + AnswerText;
                        AnswerCalc = IK + YS;
                        if (AnswerCalc >= 360) {
                            AnswerCalc = AnswerCalc - 360;
                        }
                        if (AnswerCalc < 0) {
                            AnswerCalc = AnswerCalc + 360;
                        }
                    } else {
                        YSr = ZIPY - IK;
                        s = s + "\n"+getResources().getString(R.string.CalcAns8_1) + YSr + getResources().getString(R.string.typeizm_grad)+"\n";
                        YS = YSr + Math.toDegrees(Math.atan(LBY / Spr));
                        s = s + "\n"+getResources().getString(R.string.CalcAns8_2) + YS + getResources().getString(R.string.typeizm_grad)+"\n";
                        AnswerCalc = IK - dM + YS;
                        if (AnswerCalc >= 360) {
                            AnswerCalc = AnswerCalc - 360;
                        }
                        if (AnswerCalc < 0) {
                            AnswerCalc = AnswerCalc + 360;
                        }
                        AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                        s = s + "\n"+getResources().getString(R.string.CondFMPY)+" = " + AnswerText;
                        AnswerCalc = IK + YS;
                        if (AnswerCalc >= 360) {
                            AnswerCalc = AnswerCalc - 360;
                        }
                        if (AnswerCalc < 0) {
                            AnswerCalc = AnswerCalc + 360;
                        }
                    }
                }
                AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
                s = s + "\n"+getResources().getString(R.string.CondFIPY)+" = " + AnswerText;
                Answ.setText(s);
            } else {
                s = getResources().getString(R.string.toastWrongtypeing);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void ZadPoiskKyrsiPoVetry() {
        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;

        double ZIPY = 0;
        double ZMPY = 0;
        double dM = 0;
        double Bem = 0;
        double V = 0;
        double U = 0;
        double YV;
        double YS;
        double Ben;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            ZIPY = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            dM = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Bem = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            V = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm5.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            U = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        if (allright) {
            Ben = Bem - dM;
            if (Ben >= 180) {
                Ben = Ben - 180;
            } else {
                Ben = Ben + 180;
            }
            s = s + "\n"+getResources().getString(R.string.CalcAns9_1) + (int) Ben + getResources().getString(R.string.typeizm_grad)+"\n";
            ZMPY = ZIPY - dM;
            s = s + "\n"+getResources().getString(R.string.CalcAns9_2) + (int) ZMPY + getResources().getString(R.string.typeizm_grad)+"\n";
            YV = Ben - ZMPY;
            if (YV > 180) {
                YV = YV - 360;
            }
            if (YV <= -180) {
                YV = YV + 360;
            }
            s = s + getResources().getString(R.string.CalcAns9_3) + (int) YV + getResources().getString(R.string.typeizm_grad)+"\n";
            double Ali = U * Math.sin(Math.toRadians(YV)) / V;
            YS = Math.toDegrees(Math.asin(Ali));
            s = s + getResources().getString(R.string.CalcAns9_4) + (int) YS + getResources().getString(R.string.typeizm_grad)+"\n";
            AnswerCalc = ZIPY - dM - YS;
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            Answ.setText(s + getResources().getString(R.string.CalcAns9_5) + AnswerText);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void ZadPoiskZadRybesha() {
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        double Pmm = 0;
        double Hesh = 0;
        double Hkr = 0;
        double Vv = 0;
        double W = 0;

        double dH;
        double Hbar;
        double tna;
        String stringParam;
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            Hesh = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Hkr = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Pmm = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Vv = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm5.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            W = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        if (allright) {
            if (ValueRUS) {
                Hbar = (760 - Pmm) * 11;
                s = s + getResources().getString(R.string.CalcAns10_1) + (int) Hbar + getResources().getString(R.string.typeizm_m)+"\n";
                dH = Hesh - Hkr - Hbar;
                s = s + getResources().getString(R.string.CalcAns10_2) + (int) dH + getResources().getString(R.string.typeizm_m)+"\n";
                tna = dH / Vv;
                s = s + getResources().getString(R.string.CalcAns10_3) + (int) tna + getResources().getString(R.string.typeizm_s)+"\n";
                AnswerCalc = W * tna / 3600;
                AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_km);
                Answ.setText(s + getResources().getString(R.string.CalcAns10_4) + AnswerText);
            } else {
                Hbar = (1013 - Pmm) * 27;
                s = s + getResources().getString(R.string.CalcAns10_1) + (int) Hbar + getResources().getString(R.string.typeizm_fyt)+"\n";
                dH = Hesh - (int) (Hkr * 3.28) - Hbar;
                s = s + getResources().getString(R.string.CalcAns10_2) + (int) dH + getResources().getString(R.string.typeizm_fyt)+"\n";
                tna = dH / Vv;
                s = s + getResources().getString(R.string.CalcAns10_3) + (int) tna + getResources().getString(R.string.typeizm_min)+"\n";
                AnswerCalc = W * tna / 60;
                AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_mmile);
                Answ.setText(s + getResources().getString(R.string.CalcAns10_4) + AnswerText);
            }
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskZadRybeshaOgr() {
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        double Pmm = 0;
        double Hesh = 0;
        double Hkr = 0;
        double H1 = 0;
        double Vv1 = 0;
        double W1 = 0;
        double Vv2 = 0;
        double W2 = 0;

        double dH1;
        double dH2;
        double Hbar;
        double tna1;
        double tna2;
        double S1;
        double S2;
        String stringParam;
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            Hesh = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            H1 = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Hkr = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Pmm = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm5.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Vv1 = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm6.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            W1 = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm7.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Vv2 = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm8.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            W2 = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        if (allright) {
            if (ValueRUS) {
                Hbar = (760 - Pmm) * 11;
                s = s + getResources().getString(R.string.CalcAns11_1) + (int) Hbar + getResources().getString(R.string.typeizm_m)+"\n";
                dH1 = Hesh - H1;
                s = s + getResources().getString(R.string.CalcAns11_2) + (int) dH1 + getResources().getString(R.string.typeizm_m)+"\n";
                tna1 = dH1 / Vv1;
                s = s + getResources().getString(R.string.CalcAns11_3) + (int) tna1 + getResources().getString(R.string.typeizm_s)+"\n";
                S1 = W1 * tna1 / 3600;
                s = s + getResources().getString(R.string.CalcAns11_4) + String.format("%.2f", S1) + getResources().getString(R.string.typeizm_km)+"\n";
                dH2 = H1 - Hkr - Hbar;
                s = s + getResources().getString(R.string.CalcAns11_5) + (int) dH2 + getResources().getString(R.string.typeizm_m)+"\n";
                tna2 = dH2 / Vv2;
                s = s + getResources().getString(R.string.CalcAns11_6) + (int) tna2 + getResources().getString(R.string.typeizm_s)+"\n";
                S2 = W2 * tna2 / 3600;
                s = s + getResources().getString(R.string.CalcAns11_7) + String.format("%.2f", S2) + getResources().getString(R.string.typeizm_km)+"\n";
                AnswerCalc = S1 + S2;
                AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_km);
                Answ.setText(s + getResources().getString(R.string.CalcAns11_8) + AnswerText);
            } else {
                Hbar = (1013 - Pmm) * 27;
                s = s + getResources().getString(R.string.CalcAns11_1) + (int) Hbar + getResources().getString(R.string.typeizm_fyt)+"\n";
                dH1 = Hesh - H1;
                s = s + getResources().getString(R.string.CalcAns11_2) + (int) dH1 + getResources().getString(R.string.typeizm_fyt)+"\n";
                tna1 = dH1 / Vv1;
                s = s + getResources().getString(R.string.CalcAns11_3) + (int) tna1 + getResources().getString(R.string.typeizm_min)+"\n";
                S1 = W1 * tna1 / 60;
                s = s + getResources().getString(R.string.CalcAns11_4) + String.format("%.2f", S1) + getResources().getString(R.string.typeizm_mmile)+"\n";
                dH2 = H1 - Hkr * 3 - Hbar;
                s = s + getResources().getString(R.string.CalcAns11_5) + (int) dH2 + getResources().getString(R.string.typeizm_fyt)+"\n";
                tna2 = dH2 / Vv2;
                s = s + getResources().getString(R.string.CalcAns11_6) + (int) tna2 + getResources().getString(R.string.typeizm_min)+"\n";
                S2 = W2 * tna2 / 60;
                s = s + getResources().getString(R.string.CalcAns11_7) + String.format("%.2f", S2) + getResources().getString(R.string.typeizm_mmile)+"\n";
                AnswerCalc = S1 + S2;
                AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_mmile);
                Answ.setText(s + getResources().getString(R.string.CalcAns11_8) + AnswerText);
            }
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskZadRybeshaIzmen() {
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        double Pmm = 0;
        double Hesh = 0;
        double Hkr = 0;
        double Hbar;
        double Hzad = 0;
        double Hper = 0;
        double Szad = 0;
        double dHz;
        double Sras;
        double tras;

        String stringParam;
        boolean isNumAns;
        boolean allright;

        if (!isHard) {
            double Vv = 0;
            double W = 0;
            double dH;
            double tna;
            double S;
            stringParam = desIm1.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns) {
                Hesh = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm2.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Hkr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm3.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Pmm = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm4.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Vv = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm5.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                W = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm6.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Hzad = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm7.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Hper = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm8.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Szad = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }

            if (allright) {
                if (ValueRUS) {
                    Hbar = (760 - Pmm) * 11;
                    s = s + getResources().getString(R.string.CalcAns12_1) + (int) Hbar + getResources().getString(R.string.typeizm_m)+"\n";
                    dH = Hesh - Hkr - Hbar;
                    s = s + getResources().getString(R.string.CalcAns12_2) + (int) dH + getResources().getString(R.string.typeizm_m)+"\n";
                    tna = dH / Vv;
                    s = s + getResources().getString(R.string.CalcAns12_3) + (int) tna + getResources().getString(R.string.typeizm_s)+"\n";
                    S = W * tna / 3600;
                    s = s + getResources().getString(R.string.CalcAns12_4) + String.format("%.2f", S) + getResources().getString(R.string.typeizm_km)+"\n";
                    if (Hzad >= Hper) {
                        dHz = Hesh - Hzad;
                    } else {
                        dHz = Hesh - Hzad - Hbar;
                    }
                    s = s + getResources().getString(R.string.CalcAns12_5) + dHz + getResources().getString(R.string.typeizm_m)+"\n";
                    Sras = S - Szad;
                    s = s + getResources().getString(R.string.CalcAns12_6) + String.format("%.2f", Sras) + getResources().getString(R.string.typeizm_km)+"\n";
                    tras = 3600 * (Sras / W);
                    s = s + getResources().getString(R.string.CalcAns12_7) + (int) tras + getResources().getString(R.string.typeizm_s)+"\n";
                    AnswerCalc = dHz / tras;
                    AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_mc);
                    Answ.setText(s + getResources().getString(R.string.CalcAns12_8) + AnswerText);
                } else {
                    Hbar = (1013 - Pmm) * 27;
                    s = s + getResources().getString(R.string.CalcAns12_1) + (int) Hbar + getResources().getString(R.string.typeizm_fyt)+"\n";
                    dH = Hesh - Hkr * 3 - Hbar;
                    s = s + getResources().getString(R.string.CalcAns12_2) + (int) dH + getResources().getString(R.string.typeizm_fyt)+"\n";
                    tna = dH / Vv;
                    s = s + getResources().getString(R.string.CalcAns12_3) + (int) tna + getResources().getString(R.string.typeizm_min)+"\n";
                    S = W * tna / 60;
                    s = s + getResources().getString(R.string.CalcAns12_4) + String.format("%.2f", S) + getResources().getString(R.string.typeizm_mmile)+"\n";
                    if (Hzad >= Hper) {
                        dHz = Hesh - Hzad;
                    } else {
                        dHz = Hesh - Hzad - Hbar;
                    }
                    s = s + getResources().getString(R.string.CalcAns12_5) + (int) dHz + getResources().getString(R.string.typeizm_fyt)+"\n";
                    Sras = S - Szad;
                    s = s + getResources().getString(R.string.CalcAns12_6) + String.format("%.2f", Sras) + getResources().getString(R.string.typeizm_mmile)+"\n";
                    tras = 60 * (Sras / W);
                    s = s + getResources().getString(R.string.CalcAns12_7) + (int) tras + getResources().getString(R.string.typeizm_min)+"\n";
                    AnswerCalc = dHz / tras;
                    AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_fytmin);
                    Answ.setText(s + getResources().getString(R.string.CalcAns12_8) + AnswerText);
                }
            } else {
                s = getResources().getString(R.string.toastWrongtypeing);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            double dH1;
            double dH2;
            double tna1;
            double tna2;
            double S1;
            double S2;
            double H1 = 0;
            double Vv1 = 0;
            double W1 = 0;
            double Vv2 = 0;
            double W2 = 0;
            double S;
            double S2ras;
            double t2ras;

            stringParam = desIm1.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns) {
                Hesh = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm2.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                H1 = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm3.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Hkr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm4.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Pmm = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm5.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Vv1 = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm6.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                W1 = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm7.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Vv2 = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm8.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                W2 = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm9.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Hzad = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm10.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Hper = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm11.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                Szad = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }

            if (allright) {
                if (ValueRUS) {
                    Hbar = (760 - Pmm) * 11;
                    s = s + getResources().getString(R.string.CalcAns12_1) + (int) Hbar + getResources().getString(R.string.typeizm_m)+"\n";
                    dH1 = Hesh - H1;
                    s = s + getResources().getString(R.string.CalcAns12_2_1) + (int) dH1 + getResources().getString(R.string.typeizm_m)+"\n";
                    tna1 = dH1 / Vv1;
                    s = s + getResources().getString(R.string.CalcAns12_3_1) + (int) tna1 + getResources().getString(R.string.typeizm_s)+"\n";
                    S1 = W1 * tna1 / 3600;
                    s = s + getResources().getString(R.string.CalcAns12_4_1) + String.format("%.2f", S1) + getResources().getString(R.string.typeizm_km)+"\n";
                    dH2 = H1 - Hkr - Hbar;
                    s = s + getResources().getString(R.string.CalcAns12_5_1) + (int) dH2 + getResources().getString(R.string.typeizm_m)+"\n";
                    tna2 = dH2 / Vv2;
                    s = s + getResources().getString(R.string.CalcAns12_6_1) + (int) tna2 + getResources().getString(R.string.typeizm_s)+"\n";
                    S2 = W2 * tna2 / 3600;
                    s = s + getResources().getString(R.string.CalcAns12_7_1) + String.format("%.2f", S2) + getResources().getString(R.string.typeizm_km)+"\n";
                    S = S1 + S2;
                    s = s + getResources().getString(R.string.CalcAns12_8_1) + String.format("%.2f", S) + getResources().getString(R.string.typeizm_km)+"\n";
                    if (Hzad < H1) {
                        if (Hzad >= Hper) {
                            dHz = H1 - Hzad;
                        } else {
                            dHz = H1 - Hzad - Hbar;
                        }
                        s = s + getResources().getString(R.string.CalcAns12_9_1) + (int) dHz + getResources().getString(R.string.typeizm_m)+"\n";
                        t2ras = dHz / Vv2;
                        s = s + getResources().getString(R.string.CalcAns12_10_1) + (int) t2ras + getResources().getString(R.string.typeizm_s)+"\n";
                        S2ras = W2 * t2ras / 3600;
                        s = s + getResources().getString(R.string.CalcAns12_11_1) + String.format("%.2f", S2ras) + getResources().getString(R.string.typeizm_km)+"\n";
                        Sras = S - Szad - S2ras;
                        s = s + getResources().getString(R.string.CalcAns12_12_1) + String.format("%.2f", Sras) + getResources().getString(R.string.typeizm_km)+"\n";
                        tras = 3600 * (Sras / W1);
                        s = s + getResources().getString(R.string.CalcAns12_13_1) + (int) tras + getResources().getString(R.string.typeizm_s)+"\n";
                    } else {
                        Sras = S - Szad;
                        s = s + getResources().getString(R.string.CalcAns12_9_2) + String.format("%.2f", Sras) + getResources().getString(R.string.typeizm_km)+"\n";
                        tras = 3600 * (Sras / W1);
                        s = s + getResources().getString(R.string.CalcAns12_10_2) + (int) tras + getResources().getString(R.string.typeizm_s)+"\n";
                    }
                    AnswerCalc = dH1 / tras;
                    AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_mc);
                    Answ.setText(s + getResources().getString(R.string.CalcAns12_8) + AnswerText);
                } else {
                    Hbar = (1013 - Pmm) * 27;
                    s = s + getResources().getString(R.string.CalcAns12_1) + (int) Hbar + getResources().getString(R.string.typeizm_fyt)+"\n";
                    dH1 = Hesh - H1;
                    s = s + getResources().getString(R.string.CalcAns12_2_1) + (int) dH1 + getResources().getString(R.string.typeizm_fyt)+"\n";
                    tna1 = dH1 / Vv1;
                    s = s + getResources().getString(R.string.CalcAns12_3_1) + (int) tna1 + getResources().getString(R.string.typeizm_min)+"\n";
                    S1 = W1 * tna1 / 60;
                    s = s + getResources().getString(R.string.CalcAns12_4_1) + String.format("%.2f", S1) + getResources().getString(R.string.typeizm_mmile)+"\n";
                    dH2 = H1 - Hkr * 3 - Hbar;
                    s = s + getResources().getString(R.string.CalcAns12_5_1) + (int) dH2 + getResources().getString(R.string.typeizm_fyt)+"\n";
                    tna2 = dH2 / Vv2;
                    s = s + getResources().getString(R.string.CalcAns12_6_1) + (int) tna2 + getResources().getString(R.string.typeizm_min)+"\n";
                    S2 = W2 * tna2 / 60;
                    s = s + getResources().getString(R.string.CalcAns12_7_1) + String.format("%.2f", S2) + getResources().getString(R.string.typeizm_mmile)+"\n";
                    S = S1 + S2;
                    s = s + getResources().getString(R.string.CalcAns12_8_1) + String.format("%.2f", S) + getResources().getString(R.string.typeizm_mmile)+"\n";
                    if (Hzad < H1) {
                        if (Hzad >= Hper) {
                            dHz = H1 - Hzad;
                        } else {
                            dHz = H1 - Hzad - Hbar;
                        }
                        s = s + getResources().getString(R.string.CalcAns12_9_1) + (int) dHz + getResources().getString(R.string.typeizm_fyt)+"\n";
                        t2ras = dHz / Vv2;
                        s = s + getResources().getString(R.string.CalcAns12_10_1) + (int) t2ras + getResources().getString(R.string.typeizm_min)+"\n";
                        S2ras = W2 * t2ras / 60;
                        s = s + getResources().getString(R.string.CalcAns12_11_1) + String.format("%.2f", S2ras) + getResources().getString(R.string.typeizm_mmile)+"\n";
                        Sras = S - Szad - S2ras;
                        s = s + getResources().getString(R.string.CalcAns12_12_1) + String.format("%.2f", Sras) + getResources().getString(R.string.typeizm_mmile)+"\n";
                        tras = 60 * (Sras / W1);
                        s = s + getResources().getString(R.string.CalcAns12_13_1) + (int) tras + getResources().getString(R.string.typeizm_min)+"\n";
                    } else {
                        Sras = S - Szad;
                        s = s + getResources().getString(R.string.CalcAns12_9_2) + String.format("%.2f", Sras) + getResources().getString(R.string.typeizm_mmile)+"\n";
                        tras = 60 * (Sras / W1);
                        s = s + getResources().getString(R.string.CalcAns12_10_2) + (int) tras + getResources().getString(R.string.typeizm_min)+"\n";
                    }
                    AnswerCalc = dH1 / tras;
                    AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_fytmin);
                    Answ.setText(s + getResources().getString(R.string.CalcAns12_8) + AnswerText);
                }
            } else {
                s = getResources().getString(R.string.toastWrongtypeing);
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ZadPoiskIstenSkorost() {
        double H = 0;
        double Vpr = 0;
        double Vprys = 0;
        double tn = 0;
        double tpr = 0;
        double VAR = 0;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        double dV = 0;
        double dVsh = 0;
        String stringParam;
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            H = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Vprys = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            dV = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            tn = Double.parseDouble(stringParam);
            allright = true;
        } else {
            stringParam = desIm5.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                tpr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
        }

        if (allright) {
            Vpr = Vprys;
            double tCA;
            if (H >= 11000) {
                tCA = -56.5;
            } else {
                tCA = (H * (-0.006496) + 288.2) - 273.15;
            }

            if ((Vpr < 300) || (H < 2000)) {
                dVsh = 0;
            } else {
                dVsh = CoafShatia((int) Vpr, (int) H);
            }
            s = s + getResources().getString(R.string.CalcAns13_1) + (int) dVsh +getResources().getString(R.string.typeizm_kmch)+"\n";
            Vpr = Vpr + dV + dVsh;
            s = s + getResources().getString(R.string.CalcAns13_2) + Vpr + getResources().getString(R.string.typeizm_kmch)+"\n";
            if (tn == 0) {
                tn = ((273 + tpr) / 0.996) - 0.384 * Math.pow((Vpr / 100), 2) - 273;
                VAR = Math.abs(tCA - tn);
                double Vip = Vpr * 171233 * Math.pow((288 - VAR) - 0.006496 * H, 0.5) / Math.pow(288 - 0.006496 * H, 2.628);
                s = s + getResources().getString(R.string.CalcAns13_3) + Vip + getResources().getString(R.string.typeizm_kmch)+"\n";
                tn = ((273 + tpr) / 0.996) - 0.384 * Math.pow((Vip / 100), 2) - 273;
                s = s + getResources().getString(R.string.CalcAns13_4) + tn + getResources().getString(R.string.typeizm_grad)+"\n";
            }

            VAR = Math.abs(tCA - tn);
            AnswerCalc = Vpr * 171233 * Math.pow((288 - VAR) - 0.006496 * H, 0.5) / Math.pow(288 - 0.006496 * H, 2.628);
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_kmch);
            Answ.setText(s + getResources().getString(R.string.CalcAns13_5) + AnswerText);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskPriborSkorost() {
        double H = 0;
        double Vis = 0;
        double Vpris = 0;
        double tn = 0;
        double tpr = 0;
        double VAR = 0;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        double dV = 0;
        double dVsh = 0;
        String stringParam;
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            H = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Vis = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            dV = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            tn = Double.parseDouble(stringParam);
            allright = true;
        } else {
            stringParam = desIm5.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                tpr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
        }

        if (allright) {
            double tCA;
            if (H >= 11000) {
                tCA = -56.5;
            } else {
                tCA = (H * (-0.006496) + 288.2) - 273.15;
            }

            if (tn == 0) {
                tn = ((273 + tpr) / 0.996) - 0.384 * Math.pow((Vis / 100), 2) - 273;
                s = s + getResources().getString(R.string.CalcAns14_1) + tn + getResources().getString(R.string.typeizm_grad)+"\n";
            }

            VAR = Math.abs(tCA - tn);
            Vpris = Vis * Math.pow(288 - 0.006496 * H, 2.628) / 171233 / Math.pow((288 - VAR) - 0.006496 * H, 0.5);
            if (s.equals(getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation))) {
                s = s + getResources().getString(R.string.CalcAns14_2) + Vpris + getResources().getString(R.string.typeizm_kmch)+"\n";
            } else {
                s = s + getResources().getString(R.string.CalcAns14_3) + Vpris + getResources().getString(R.string.typeizm_kmch)+"\n";
            }
            if ((Vpris < 300) || (H < 2000)) {
                dVsh = 0;
            } else {
                dVsh = CoafShatia((int) (Vpris), (int) H);
            }
            s = s + getResources().getString(R.string.CalcAns14_4) + (int) dVsh +getResources().getString(R.string.typeizm_kmch)+"\n";
            AnswerCalc = Vpris - dV - dVsh;
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_kmch);
            Answ.setText(s + getResources().getString(R.string.CalcAns14_5) + AnswerText);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskIstenSkorostKYS() {
        double H = 0;
        double Vpr = 0;
        double Vprys = 0;
        double tn = 0;
        double tpr = 0;
        double VAR = 0;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        double dV = 0;
        String stringParam;
        boolean isNumAns;
        boolean allright;


        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            H = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Vprys = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            dV = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            tn = Double.parseDouble(stringParam);
            allright = true;
        } else {
            stringParam = desIm5.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                tpr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
        }

        if (allright) {
            Vpr = Vprys;
            double tCA;
            if (H >= 11000) {
                tCA = -56.5;
            } else {
                tCA = (H * (-0.006496) + 288.2) - 273.15;
            }

            Vpr = Vpr + dV;
            s = s + getResources().getString(R.string.CalcAns15_1) + Vpr + getResources().getString(R.string.typeizm_kmch)+"\n";

            if (tn == 0) {
                tn = ((273 + tpr) / 0.996) - 0.384 * Math.pow((Vpr / 100), 2) - 273;
                s = s + getResources().getString(R.string.CalcAns15_2) + tn + getResources().getString(R.string.typeizm_grad)+"\n";
            }

            VAR = Math.abs(tn - tCA);
            AnswerCalc = Vpr - (VAR * Vpr / 500);
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_kmch);
            Answ.setText(s + getResources().getString(R.string.CalcAns15_3) + AnswerText);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void ZadPoiskIsprPutLBYmB() {
        double ZMPY = 0;
        double MK = 0;
        double KYR = 0;
        double Sobsh = 0;
        double Spr = 0;
        double tpr = 0;
        double W = 0;
        double YVvih = 0;

        double MPR;
        double MPS;
        double YCf;
        double DP;
        double Sost;
        double LBY;
        double BY;
        double PK;
        double KYRppm;
        double MKvih;
        double KYRvih;
        double MKsl;
        double KYRsl;

        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            ZMPY = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            MK = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            KYR = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Sobsh = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm5.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Spr = Double.parseDouble(stringParam);
            allright = true;
        } else {
            stringParam = desIm6.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                tpr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm7.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                W = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
        }
        stringParam = desIm8.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            YVvih = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        if (allright) {
            if ((KYR <= 90) || (KYR >= 270)) {
                MPR = MK + KYR;
                if (MPR >= 360) {
                    MPR = MPR - 360;
                }
                s = s + getResources().getString(R.string.CalcAns16_1) + (int) MPR + getResources().getString(R.string.typeizm_grad)+"\n";
                DP = ZMPY - MPR;
                DP = makeYgol180_180(DP);
                s = s + getResources().getString(R.string.CalcAns16_2) + String.format("%.1f", +DP) + getResources().getString(R.string.typeizm_grad)+"\n";
                if (Spr == 0) {
                    Sost = Sobsh - (W * tpr / 60);
                    Spr = (W * tpr / 60);
                    s = s + getResources().getString(R.string.CalcAns16_3) + (int) Spr + getResources().getString(R.string.typeizm_km)+"\n";
                } else {
                    Sost = Sobsh - Spr;
                }
                if (DP > 0) {
                    if (YVvih < 0) {
                        YVvih = YVvih * (-1);
                    }
                }
                if (DP < 0) {
                    if (YVvih > 0) {
                        YVvih = YVvih * (-1);
                    }
                }
                if (DP == 0) {
                    YVvih = 0;
                }
                s = s + getResources().getString(R.string.CalcAns16_4) + (int) Sost + getResources().getString(R.string.typeizm_km)+"\n";
                LBY = Sost * Math.tan(Math.toRadians(DP));
                s = s + getResources().getString(R.string.CalcAns16_5) + String.format("%.2f", +LBY) + getResources().getString(R.string.typeizm_km)+"\n";
                BY = DP * Sost / Spr;
                s = s + getResources().getString(R.string.CalcAns16_6) + String.format("%.1f", +BY) + getResources().getString(R.string.typeizm_grad)+"\n";
                PK = BY + DP;
                s = s + getResources().getString(R.string.CalcAns16_7) + String.format("%.1f", +PK) + getResources().getString(R.string.typeizm_grad)+"\n";
                YCf = ZMPY - MK + BY;
                YCf = makeYgol180_180(YCf);
                s = s + getResources().getString(R.string.CalcAns16_8) + String.format("%.1f", +YCf) + getResources().getString(R.string.typeizm_grad)+"\n";
                KYRppm = 360 + YCf;
                KYRppm = makeYgol0_360(KYRppm);
                s = s + getResources().getString(R.string.CalcAns16_9) + String.format("%.1f", +KYRppm) + getResources().getString(R.string.typeizm_grad)+"\n";
                MKvih = ZMPY - YVvih;
                MKvih = makeYgol0_360(MKvih);
                s = s + getResources().getString(R.string.CalcAns16_10) + String.format("%.1f", +MKvih) + getResources().getString(R.string.typeizm_grad)+"\n";
                KYRvih = 360 + YVvih;
                KYRvih = makeYgol0_360(KYRvih);
                s = s + getResources().getString(R.string.CalcAns16_11) + String.format("%.1f", +KYRvih) + getResources().getString(R.string.typeizm_grad)+"\n";
                MKsl = MK - BY;
                MKsl = makeYgol0_360(MKsl);
                s = s + getResources().getString(R.string.CalcAns16_12) + String.format("%.1f", +MKsl) + getResources().getString(R.string.typeizm_grad)+"\n";
                KYRsl = 360 + YCf;
                KYRsl = makeYgol0_360(KYRsl);
                s = s + getResources().getString(R.string.CalcAns16_13) + String.format("%.1f", +KYRsl) + getResources().getString(R.string.typeizm_grad)+"\n";
                AnswerCalc = MK - PK;
                AnswerCalc = makeYgol0_360(AnswerCalc);
            } else {
                MPS = MK + KYR;
                if (MPS >= 180) {
                    MPS = MPS - 180;
                } else {
                    MPS = MPS + 180;
                }
                s = s + getResources().getString(R.string.CalcAns16_1_1) + (int) MPS + getResources().getString(R.string.typeizm_grad)+"\n";
                BY = MPS - ZMPY;
                BY = makeYgol180_180(BY);
                s = s + getResources().getString(R.string.CalcAns16_2_1) + String.format("%.1f", +BY) + getResources().getString(R.string.typeizm_grad)+"\n";
                if (Spr == 0) {
                    Sost = Sobsh - (W * tpr / 60);
                    Spr = (W * tpr / 60);
                    s = s + getResources().getString(R.string.CalcAns16_3) + (int) Spr + getResources().getString(R.string.typeizm_km)+"\n";
                } else {
                    Sost = Sobsh - Spr;
                }
                if (BY > 0) {
                    if (YVvih < 0) {
                        YVvih = YVvih * (-1);
                    }
                }
                if (BY < 0) {
                    if (YVvih > 0) {
                        YVvih = YVvih * (-1);
                    }
                }
                if (BY == 0) {
                    YVvih = 0;
                }
                s = s + getResources().getString(R.string.CalcAns16_4) + (int) Sost + getResources().getString(R.string.typeizm_km)+"\n";
                LBY = Spr * Math.tan(Math.toRadians(BY));
                s = s + getResources().getString(R.string.CalcAns16_5) + String.format("%.2f", +LBY) + getResources().getString(R.string.typeizm_km)+"\n";
                DP = BY * Spr / Sost;
                s = s + getResources().getString(R.string.CalcAns16_6_1) + String.format("%.1f", +DP) + getResources().getString(R.string.typeizm_grad)+"\n";
                PK = BY + DP;
                s = s + getResources().getString(R.string.CalcAns16_7) + String.format("%.1f", +PK) + getResources().getString(R.string.typeizm_grad)+"\n";
                YCf = ZMPY - MK + BY;
                YCf = makeYgol180_180(YCf);
                s = s + getResources().getString(R.string.CalcAns16_8) + String.format("%.1f", +YCf) + getResources().getString(R.string.typeizm_grad)+"\n";
                MKvih = ZMPY - YVvih;
                MKvih = makeYgol0_360(MKvih);
                s = s + getResources().getString(R.string.CalcAns16_9_1) + String.format("%.1f", +MKvih) + getResources().getString(R.string.typeizm_grad)+"\n";
                KYRvih = 180 + YVvih;
                KYRvih = makeYgol0_360(KYRvih);
                s = s + getResources().getString(R.string.CalcAns16_10_1) + String.format("%.1f", +KYRvih) + getResources().getString(R.string.typeizm_grad)+"\n";
                MKsl = MK - BY;
                MKsl = makeYgol0_360(MKsl);
                s = s + getResources().getString(R.string.CalcAns16_11_1) + String.format("%.1f", +MKsl) + getResources().getString(R.string.typeizm_grad)+"\n";
                KYRsl = 180 + YCf;
                KYRsl = makeYgol0_360(KYRsl);
                s = s + getResources().getString(R.string.CalcAns16_12_1) + String.format("%.1f", +KYRsl) + getResources().getString(R.string.typeizm_grad)+"\n";
                AnswerCalc = MK - PK;
                AnswerCalc = makeYgol0_360(AnswerCalc);
            }
            AnswerText = String.format("%.1f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            Answ.setText(s + getResources().getString(R.string.CalcAns16_14) + AnswerText);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskIsprPutLBYbB() {
        double ZMPY = 0;
        double MK = 0;
        double KYR = 0;
        double Sobsh = 0;
        double Spr = 0;
        double tpr = 0;
        double W = 0;
        double YVvih = 0;

        double MPR;
        double MPS;
        double YCf;
        double DP;
        double Sost;
        double LBY;
        double BY;
        double PK;
        double KYRppm;
        double MKvih;
        double KYRvih;
        double MKsl;
        double KYRsl;

        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            ZMPY = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            MK = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm3.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            KYR = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm4.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Sobsh = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm5.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Spr = Double.parseDouble(stringParam);
            allright = true;
        } else {
            stringParam = desIm6.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                tpr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm7.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                W = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
        }
        stringParam = desIm8.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            YVvih = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }

        if (allright) {
            if ((KYR <= 90) || (KYR >= 270)) {
                MPR = MK + KYR;
                if (MPR >= 360) {
                    MPR = MPR - 360;
                }
                s = s + getResources().getString(R.string.CalcAns16_1) + (int) MPR + getResources().getString(R.string.typeizm_grad)+"\n";
                DP = ZMPY - MPR;
                DP = makeYgol180_180(DP);
                s = s + getResources().getString(R.string.CalcAns16_2) + String.format("%.1f", +DP) + getResources().getString(R.string.typeizm_grad)+"\n";
                if (Spr == 0) {
                    Sost = Sobsh - (W * tpr / 60);
                    Spr = (W * tpr / 60);
                    s = s + getResources().getString(R.string.CalcAns16_3) + (int) Spr + getResources().getString(R.string.typeizm_km)+"\n";
                } else {
                    Sost = Sobsh - Spr;
                }
                if (DP > 0) {
                    if (YVvih < 0) {
                        YVvih = YVvih * (-1);
                    }
                }
                if (DP < 0) {
                    if (YVvih > 0) {
                        YVvih = YVvih * (-1);
                    }
                }
                if (DP == 0) {
                    YVvih = 0;
                }
                s = s + getResources().getString(R.string.CalcAns16_4) + (int) Sost + getResources().getString(R.string.typeizm_km)+"\n";
                LBY = Sost * Math.tan(Math.toRadians(DP));
                s = s + getResources().getString(R.string.CalcAns16_5) + String.format("%.2f", +LBY) + getResources().getString(R.string.typeizm_km)+"\n";
                BY = DP * Sost / Spr;
                s = s + getResources().getString(R.string.CalcAns16_6) + String.format("%.1f", +BY) + getResources().getString(R.string.typeizm_grad)+"\n";
                PK = BY + DP;
                s = s + getResources().getString(R.string.CalcAns16_7) + String.format("%.1f", +PK) + getResources().getString(R.string.typeizm_grad)+"\n";
                YCf = ZMPY - MK + BY;
                YCf = makeYgol180_180(YCf);
                s = s + getResources().getString(R.string.CalcAns16_8) + String.format("%.1f", +YCf) + getResources().getString(R.string.typeizm_grad)+"\n";
                KYRppm = 360 + YCf;
                KYRppm = makeYgol0_360(KYRppm);
                s = s + getResources().getString(R.string.CalcAns16_9) + String.format("%.1f", +KYRppm) + getResources().getString(R.string.typeizm_grad)+"\n";
                MKvih = ZMPY - YVvih;
                MKvih = makeYgol0_360(MKvih);
                s = s + getResources().getString(R.string.CalcAns16_10) + String.format("%.1f", +MKvih) + getResources().getString(R.string.typeizm_grad)+"\n";
                KYRvih = 360 + YVvih;
                KYRvih = makeYgol0_360(KYRvih);
                s = s + getResources().getString(R.string.CalcAns16_11) + String.format("%.1f", +KYRvih) + getResources().getString(R.string.typeizm_grad)+"\n";
                MKsl = MK - BY;
                MKsl = makeYgol0_360(MKsl);
                s = s + getResources().getString(R.string.CalcAns16_12) + String.format("%.1f", +MKsl) + getResources().getString(R.string.typeizm_grad)+"\n";
                KYRsl = 360 + YCf;
                KYRsl = makeYgol0_360(KYRsl);
                s = s + getResources().getString(R.string.CalcAns16_13) + String.format("%.1f", +KYRsl) + getResources().getString(R.string.typeizm_grad)+"\n";
                AnswerCalc = MK - PK;
                AnswerCalc = makeYgol0_360(AnswerCalc);
            } else {
                MPS = MK + KYR;
                if (MPS >= 180) {
                    MPS = MPS - 180;
                } else {
                    MPS = MPS + 180;
                }
                s = s + getResources().getString(R.string.CalcAns16_1_1) + (int) MPS + getResources().getString(R.string.typeizm_grad)+"\n";
                BY = MPS - ZMPY;
                BY = makeYgol180_180(BY);
                s = s + getResources().getString(R.string.CalcAns16_2_1) + String.format("%.1f", +BY) + getResources().getString(R.string.typeizm_grad)+"\n";
                if (Spr == 0) {
                    Sost = Sobsh - (W * tpr / 60);
                    Spr = (W * tpr / 60);
                    s = s + getResources().getString(R.string.CalcAns16_3) + (int) Spr + getResources().getString(R.string.typeizm_km)+"\n";
                } else {
                    Sost = Sobsh - Spr;
                }
                if (BY > 0) {
                    if (YVvih < 0) {
                        YVvih = YVvih * (-1);
                    }
                }
                if (BY < 0) {
                    if (YVvih > 0) {
                        YVvih = YVvih * (-1);
                    }
                }
                if (BY == 0) {
                    YVvih = 0;
                }
                s = s + getResources().getString(R.string.CalcAns16_4) + (int) Sost + getResources().getString(R.string.typeizm_km)+"\n";
                LBY = Spr * Math.tan(Math.toRadians(BY));
                s = s + getResources().getString(R.string.CalcAns16_5) + String.format("%.2f", +LBY) + getResources().getString(R.string.typeizm_km)+"\n";
                DP = BY * Spr / Sost;
                s = s + getResources().getString(R.string.CalcAns16_6_1) + String.format("%.1f", +DP) + getResources().getString(R.string.typeizm_grad)+"\n";
                PK = BY + DP;
                s = s + getResources().getString(R.string.CalcAns16_7) + String.format("%.1f", +PK) + getResources().getString(R.string.typeizm_grad)+"\n";
                YCf = ZMPY - MK + BY;
                YCf = makeYgol180_180(YCf);
                s = s + getResources().getString(R.string.CalcAns16_8) + String.format("%.1f", +YCf) + getResources().getString(R.string.typeizm_grad)+"\n";
                MKvih = ZMPY - YVvih;
                MKvih = makeYgol0_360(MKvih);
                s = s + getResources().getString(R.string.CalcAns16_9_1) + String.format("%.1f", +MKvih) + getResources().getString(R.string.typeizm_grad)+"\n";
                KYRvih = 180 + YVvih;
                KYRvih = makeYgol0_360(KYRvih);
                s = s + getResources().getString(R.string.CalcAns16_10_1) + String.format("%.1f", +KYRvih) + getResources().getString(R.string.typeizm_grad)+"\n";
                MKsl = MK - BY;
                MKsl = makeYgol0_360(MKsl);
                s = s + getResources().getString(R.string.CalcAns16_11_1) + String.format("%.1f", +MKsl) + getResources().getString(R.string.typeizm_grad)+"\n";
                KYRsl = 180 + YCf;
                KYRsl = makeYgol0_360(KYRsl);
                s = s + getResources().getString(R.string.CalcAns16_12_1) + String.format("%.1f", +KYRsl) + getResources().getString(R.string.typeizm_grad)+"\n";
                AnswerCalc = MK - PK;
                AnswerCalc = makeYgol0_360(AnswerCalc);
            }
            AnswerText = String.format("%.1f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            Answ.setText(s + getResources().getString(R.string.CalcAns16_14) + AnswerText);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void ZadPoiskIsprPutLBYbB2() {
        double DP = 400;
        double BY = 400;
        double Sobsh = 0;
        double Spr = 0;
        double LBY = 400;
        double tpr = 0;
        double W = 0;

        double Sost;


        String stringParam;
        String s = getResources().getString(R.string.calculation2) + "\n" + getResources().getString(R.string.calculation);
        boolean isNumAns;
        boolean allright;

        stringParam = desIm1.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns) {
            Sobsh = Double.parseDouble(stringParam);
            allright = true;
        } else {
            allright = false;
        }
        stringParam = desIm2.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            Spr = Double.parseDouble(stringParam);
            allright = true;
        } else {
            stringParam = desIm3.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                tpr = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
            stringParam = desIm4.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                W = Double.parseDouble(stringParam);
                allright = true;
            } else {
                allright = false;
            }
        }
        stringParam = desIm5.getText().toString();
        isNumAns = isNumeric(stringParam);
        if (isNumAns && allright) {
            LBY = Double.parseDouble(stringParam);
            allright = true;
        } else {
            stringParam = desIm6.getText().toString();
            isNumAns = isNumeric(stringParam);
            if (isNumAns && allright) {
                BY = Double.parseDouble(stringParam);
                allright = true;
            } else {
                stringParam = desIm7.getText().toString();
                isNumAns = isNumeric(stringParam);
                if (isNumAns && allright) {
                    DP = Double.parseDouble(stringParam);
                    allright = true;
                } else {
                    allright = false;
                }
            }
        }

        if (allright) {
            if (Spr == 0) {
                Sost = Sobsh - (W * tpr / 60);
                Spr = (W * tpr / 60);
                s = s + getResources().getString(R.string.CalcAns16_3) + (int) Spr + getResources().getString(R.string.typeizm_km)+"\n";
            } else {
                Sost = Sobsh - Spr;
            }
            s = s + getResources().getString(R.string.CalcAns18_1) + (int) Sost + getResources().getString(R.string.typeizm_km)+"\n";
            if (BY == 400 && DP == 400) {
                BY = Math.toDegrees(Math.atan(LBY / Spr));
                DP = Math.toDegrees(Math.atan(LBY / Sost));
                s = s + getResources().getString(R.string.CalcAns18_2) + String.format("%.1f", +DP) + getResources().getString(R.string.typeizm_grad)+"\n";
                s = s + getResources().getString(R.string.CalcAns18_3) + String.format("%.1f", +BY) + getResources().getString(R.string.typeizm_grad)+"\n";
            }
            if (LBY == 400 && DP == 400) {
                DP = BY * Spr / Sost;
                LBY = Spr * Math.tan(Math.toRadians(BY));
                s = s + getResources().getString(R.string.CalcAns18_2) + String.format("%.1f", +DP) + getResources().getString(R.string.typeizm_grad)+"\n";
                s = s + getResources().getString(R.string.CalcAns18_4) + String.format("%.1f", +LBY) + getResources().getString(R.string.typeizm_km)+"\n";
            }
            if (LBY == 400 && BY == 400) {
                BY = DP * Sost / Spr;
                LBY = Sost * Math.tan(Math.toRadians(DP));
                s = s + getResources().getString(R.string.CalcAns18_5) + String.format("%.1f", +BY) + getResources().getString(R.string.typeizm_grad)+"\n";
                s = s + getResources().getString(R.string.CalcAns18_4) + String.format("%.1f", +LBY) + getResources().getString(R.string.typeizm_km)+"\n";
            }

            AnswerCalc = BY + DP;
            if (AnswerCalc >= 360) {
                AnswerCalc = AnswerCalc - 360;
            }
            if (AnswerCalc <= -360) {
                AnswerCalc = AnswerCalc + 360;
            }
            AnswerText = String.format("%.2f", +AnswerCalc) + getResources().getString(R.string.typeizm_grad);
            Answ.setText(s + getResources().getString(R.string.CalcAns18_6) + AnswerText);
        } else {
            s = getResources().getString(R.string.toastWrongtypeing);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }
    }

    //---------------------------------------------------------------------------------------------
    public int CoafShatia(int V, int H) {
        int Shat = 0;
        int Ig = 0;
        int Jg = 0;
        double A;

        for (int i = 0; i < 7; i++) {
            if (H >= arrShat[i][0]) {
                Ig = i;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (V >= arrShat[0][i]) {
                Jg = i;
            }
        }
        if ((Ig == 0) || (Jg == 0)) {
            Shat = 0;
        } else {
            A = (((double) arrShat[Ig][Jg] * (arrShat[0][Jg + 1] - V) * (arrShat[Ig + 1][0] - H) / (arrShat[0][Jg + 1] - arrShat[0][Jg]) / (arrShat[Ig + 1][0] - arrShat[Ig][0])) + ((double) arrShat[Ig][Jg + 1] * (V - arrShat[0][Jg]) * (arrShat[Ig + 1][0] - H) / (arrShat[0][Jg + 1] - arrShat[0][Jg]) / (arrShat[Ig + 1][0] - arrShat[Ig][0])) + ((double) arrShat[Ig + 1][Jg] * (arrShat[0][Jg + 1] - V) * (H - arrShat[Ig][0]) / (arrShat[0][Jg + 1] - arrShat[0][Jg]) / (arrShat[Ig + 1][0] - arrShat[Ig][0])) + ((double) arrShat[Ig + 1][Jg + 1] * (V - arrShat[0][Jg]) * (H - arrShat[Ig][0]) / (arrShat[0][Jg + 1] - arrShat[0][Jg]) / (arrShat[Ig + 1][0] - arrShat[Ig][0])));
            Shat = (int) A;
            Shat = (-1) * Shat;
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
    public double makeYgol0_360(double x) {
        if (x >= 360) {
            x = x - 360;
        }
        if (x <= -360) {
            x = x + 360;
        }
        if (x < 0) {
            x = x + 360;
        }
        return x;
    }
    public double makeYgol180_180(double x) {
        if (x > 180) {
            x = x - 360;
        }
        if (x < -180) {
            x = x + 360;
        }
        return x;
    }
    //---------------------------------------------------------------------------------------------
}