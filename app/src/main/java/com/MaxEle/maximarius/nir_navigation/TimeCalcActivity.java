package com.MaxEle.maximarius.nir_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TimeCalcActivity extends AppCompatActivity {

    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";

    SharedPreferences mDataFiles;

    boolean Theme_Light;

    TextView CurrentNumb;
    TextView AllNums;

    char[] TimeChars;
    String CurrentNumbStr;
    String AllNumsStr;

    boolean flagravno = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);
        if (mDataFiles.contains(DATA_FILE_THEME_LIGHT)) {
            Theme_Light = mDataFiles.getBoolean(DATA_FILE_THEME_LIGHT, true);
        } else {
            Theme_Light = true;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putBoolean(DATA_FILE_THEME_LIGHT, Theme_Light);
            editor.apply();
        }
        if (Theme_Light) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.AppThemeDark);
        }
        setContentView(R.layout.activity_time_calc);

        AdView mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        CurrentNumb = findViewById(R.id.CurrTime);
        AllNums = findViewById(R.id.AllTime);

        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности
        Button ButBack = findViewById(R.id.buttonBack);
        ButBack.setOnClickListener(view -> {
            TimeCalcActivity.this.finish();
            Intent intent = new Intent(TimeCalcActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back);
        });

        Button But0 = findViewById(R.id.button0);
        But0.setOnClickListener(view -> whenNumberPressed('0'));
        Button But1 = findViewById(R.id.button1);
        But1.setOnClickListener(view -> whenNumberPressed('1'));
        Button But2 = findViewById(R.id.button2);
        But2.setOnClickListener(view -> whenNumberPressed('2'));
        Button But3 = findViewById(R.id.button3);
        But3.setOnClickListener(view -> whenNumberPressed('3'));
        Button But4 = findViewById(R.id.button4);
        But4.setOnClickListener(view -> whenNumberPressed('4'));
        Button But5 = findViewById(R.id.button5);
        But5.setOnClickListener(view -> whenNumberPressed('5'));
        Button But6 = findViewById(R.id.button6);
        But6.setOnClickListener(view -> whenNumberPressed('6'));
        Button But7 = findViewById(R.id.button7);
        But7.setOnClickListener(view -> whenNumberPressed('7'));
        Button But8 = findViewById(R.id.button8);
        But8.setOnClickListener(view -> whenNumberPressed('8'));
        Button But9 = findViewById(R.id.button9);
        But9.setOnClickListener(view -> whenNumberPressed('9'));
        Button ButClear = findViewById(R.id.buttonClear);
        ButClear.setOnClickListener(view -> ClearAll());
        Button ButMinus = findViewById(R.id.buttonminus);
        ButMinus.setOnClickListener(view -> {
            AllNumsStr = AllNums.getText().toString();
            CurrentNumbStr = CurrentNumb.getText().toString();
            if (flagravno) {
                AllNums.setText("");
                AllNumsStr = "";
                flagravno = false;
                AllNumsStr = AllNumsStr + CurrentNumbStr + "-";
            } else {
                if (CurrentNumbStr.equals(getResources().getString(R.string.TimeCalc00_00))) {
                    if (!AllNumsStr.equals(""))
                        if (AllNumsStr.charAt(AllNumsStr.length() - 1) == '+' || AllNumsStr.charAt(AllNumsStr.length() - 1) == '-') {
                            AllNumsStr = AllNumsStr.substring(0, AllNumsStr.length() - 1);
                        }
                    AllNumsStr = AllNumsStr + "-";
                } else {
                    AllNumsStr = AllNumsStr + CurrentNumbStr + "-";
                }
            }
            AllNums.setText(AllNumsStr);
            CurrentNumb.setText(getResources().getString(R.string.TimeCalc00_00));
        });
        Button ButPlus = findViewById(R.id.buttonplus);
        ButPlus.setOnClickListener(view -> {
            AllNumsStr = AllNums.getText().toString();
            CurrentNumbStr = CurrentNumb.getText().toString();

            if (flagravno) {
                AllNums.setText("");
                AllNumsStr = "";
                flagravno = false;
                AllNumsStr = AllNumsStr + CurrentNumbStr + "+";
            } else {
                if (CurrentNumbStr.equals(getResources().getString(R.string.TimeCalc00_00))) {
                    if (!AllNumsStr.equals(""))
                        if (AllNumsStr.charAt(AllNumsStr.length() - 1) == '+' || AllNumsStr.charAt(AllNumsStr.length() - 1) == '-') {
                            AllNumsStr = AllNumsStr.substring(0, AllNumsStr.length() - 1);
                        }
                    AllNumsStr = AllNumsStr + "+";
                } else {
                    AllNumsStr = AllNumsStr + CurrentNumbStr + "+";
                }
            }
            AllNums.setText(AllNumsStr);
            CurrentNumb.setText(getResources().getString(R.string.TimeCalc00_00));
        });
        Button ButEquals = findViewById(R.id.buttonequ);
        ButEquals.setOnClickListener(view -> {
            if (!flagravno) {
                AllNumsStr = AllNums.getText().toString();
                CurrentNumbStr = CurrentNumb.getText().toString();

                AllNumsStr = AllNumsStr + CurrentNumbStr + "=";

                String input = AllNumsStr;
                String value = "";
                int valueSize = 0;
                List<Time> times = new ArrayList<>();

                int firstNumSize;

                if (!input.startsWith("+") && !input.startsWith("-")) {
                    if ((firstNumSize = input.split("[+\\-=]")[0].length()) > 5) {
                        times.add(new Time(input.substring(0, firstNumSize)));
                        input = input.substring(firstNumSize);
                    } else {
                        times.add(new Time(input.substring(0, 5)));
                        input = input.substring(5);
                    }
                } else {
                    if ((firstNumSize = input.split("[+\\-=]")[1].length()) > 5) {
                        firstNumSize++;
                        times.add(new Time(input.substring(0, firstNumSize)));
                        input = input.substring(firstNumSize);
                    }
                }

                for (char c : input.toCharArray()) {
                    value = value.concat(String.valueOf(c));
                    valueSize++;
                    if (valueSize == 6) {
                        times.add(new Time(value));
                        value = "";
                        valueSize = 0;
                    }
                }

                AllNumsStr = AllNumsStr.concat(calculateTime(times));
                AllNums.setText(AllNumsStr);
                flagravno = true;
            }
        });

        //-----------------------------------------------------------------------
    }

    @Override
    public void onBackPressed() {
        TimeCalcActivity.this.finish();
        Intent intent = new Intent(TimeCalcActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back);
    }

    public void ClearAll() {
        AllNums.setText("");
        CurrentNumb.setText(R.string.TimeCalc00_00);
        CurrentNumbStr = "";
        AllNumsStr = "";
        flagravno = false;
    }

    static class Time {
        char sign = '+';
        int timeInMins;

        public Time(String time) {
            if(time.charAt(0) == '-'){
                sign = '-';
                timeInMins = Integer.parseInt(time.split(":")[0].substring(1)) * 60 + Integer.parseInt(time.split(":")[1]);
            } else if (time.charAt(0) == '+') {
                timeInMins = Integer.parseInt(time.split(":")[0].substring(1)) * 60 + Integer.parseInt(time.split(":")[1]);
            } else {
                timeInMins = Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
            }
        }
    }

    public String calculateTime(List<Time> times) {
        int mins = 0;
        String time = "";
        String format = "%02d";
        for (Time t : times) {
            if (t.sign == '+') {
                mins += t.timeInMins;
            } else {
                mins -= t.timeInMins;
            }
        }
        if (mins < 0) {
            time = "-";
        }
        if ((String.valueOf(Math.abs(mins / 60))).length() > 1 ){
            format = "%d";
        }
        time = time.concat(String.format(Locale.getDefault(), format, Math.abs(mins / 60)) + ":" + String.format(Locale.getDefault(), "%02d", Math.abs(mins % 60)));
        CurrentNumb.setText(time);
        return time;
    }

    public void whenNumberPressed(char c) {
        if (flagravno) {
            ClearAll();
        }
        CurrentNumbStr = CurrentNumb.getText().toString();
        TimeChars = CurrentNumbStr.toCharArray();
        TimeChars[0] = TimeChars[1];
        TimeChars[1] = TimeChars[3];
        TimeChars[3] = TimeChars[4];
        TimeChars[4] = c;
        CurrentNumbStr = "";
        StringBuilder total = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            total.append(TimeChars[i]);
        }
        CurrentNumbStr = total.toString();
        CurrentNumb.setText(CurrentNumbStr);
    }
}
