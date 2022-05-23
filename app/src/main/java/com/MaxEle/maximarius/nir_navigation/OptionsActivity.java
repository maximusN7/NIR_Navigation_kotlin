package com.MaxEle.maximarius.nir_navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {

    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_CORRECT = "correct";
    public static final String DATA_FILE_WRONG = "wrong";
    public static final String DATA_FILE_LEVEL = "level";

    public static final String DATA_FILE_KOLRESH1_1 = "kr1_1";
    public static final String DATA_FILE_KOLRESH1_2 = "kr1_2";
    public static final String DATA_FILE_KOLRESH1_3 = "kr1_3";

    public static final String DATA_FILE_KOLNYSHN1_1 = "kn1_1";
    public static final String DATA_FILE_KOLNYSHN1_2 = "kn1_2";
    public static final String DATA_FILE_KOLNYSHN1_3 = "kn1_3";

    int AvaLevel;
    int corransw;
    int wrongansw;
    SharedPreferences mDataFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);

        ProverkaDataFiles();
    }

    public void ProverkaDataFiles(){
        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);

        if (mDataFiles.contains(DATA_FILE_LEVEL)){
            AvaLevel = mDataFiles.getInt(DATA_FILE_LEVEL, 0);
        }
        else
        {
            AvaLevel=1;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_LEVEL, AvaLevel);
            editor.apply();
        }

        if (mDataFiles.contains(DATA_FILE_CORRECT)){
            corransw = mDataFiles.getInt(DATA_FILE_CORRECT, 0);
        }
        else
        {
            corransw=0;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_CORRECT, corransw);
            editor.apply();
        }

        if (mDataFiles.contains(DATA_FILE_WRONG)){
            wrongansw = mDataFiles.getInt(DATA_FILE_WRONG, 0);
        }
        else
        {
            wrongansw=0;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_WRONG, wrongansw);
            editor.apply();
        }

        int f;
        if (!mDataFiles.contains(DATA_FILE_KOLNYSHN1_1)){
            f=5;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_KOLNYSHN1_1, f);
            editor.apply();
        }
        if (!mDataFiles.contains(DATA_FILE_KOLNYSHN1_2)){
            f=5;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_KOLNYSHN1_2, f);
            editor.apply();
        }
        if (!mDataFiles.contains(DATA_FILE_KOLNYSHN1_3)){
            f=5;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_KOLNYSHN1_3, f);
            editor.apply();
        }
        if (!mDataFiles.contains(DATA_FILE_KOLRESH1_1)){
            f=0;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_KOLRESH1_1, f);
            editor.apply();
        }
        if (!mDataFiles.contains(DATA_FILE_KOLRESH1_2)){
            f=0;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_KOLRESH1_2, f);
            editor.apply();
        }
        if (!mDataFiles.contains(DATA_FILE_KOLRESH1_3)){
            f=0;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putInt(DATA_FILE_KOLRESH1_3, f);
            editor.apply();
        }
    }

    @Override
    public void onBackPressed() {
        OptionsActivity.this.finish();
        Intent intent = new Intent(OptionsActivity.this, StatActivity.class);
        startActivity(intent);
    }
    public void onClickBack(View view){
        OptionsActivity.this.finish();
        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickOk(View view){
        TextView text = findViewById(R.id.textViewEnterlvl);
        int a = Integer.parseInt(text.getText().toString());
        SharedPreferences.Editor editor = mDataFiles.edit();
        editor.putInt(DATA_FILE_LEVEL, a);
        editor.apply();
    }

    public void onClickOk2(View view){
        TextView text = findViewById(R.id.textViewEnterResh1);
        int a = Integer.parseInt(text.getText().toString());
        SharedPreferences.Editor editor = mDataFiles.edit();
        editor.putInt(DATA_FILE_KOLRESH1_1, a);
        text = findViewById(R.id.textViewEnterResh2);
        a = Integer.parseInt(text.getText().toString());
        editor.putInt(DATA_FILE_KOLRESH1_2, a);
        text = findViewById(R.id.textViewEnterResh3);
        a = Integer.parseInt(text.getText().toString());
        editor.putInt(DATA_FILE_KOLRESH1_3, a);
        editor.apply();
    }

    public void onClickOk3(View view){
        TextView text =  findViewById(R.id.textViewEnterNyshResh1);
        int a = Integer.parseInt(text.getText().toString());
        SharedPreferences.Editor editor = mDataFiles.edit();
        editor.putInt(DATA_FILE_KOLNYSHN1_1, a);
        text = findViewById(R.id.textViewEnterNyshResh2);
        a = Integer.parseInt(text.getText().toString());
        editor.putInt(DATA_FILE_KOLNYSHN1_2, a);
        text = findViewById(R.id.textViewEnterNyshResh3);
        a = Integer.parseInt(text.getText().toString());
        editor.putInt(DATA_FILE_KOLNYSHN1_3, a);
        editor.apply();
    }

    public void onClickOk4(View view){
        TextView text =  findViewById(R.id.textViewEnterCurr);
        int a = Integer.parseInt(text.getText().toString());
        SharedPreferences.Editor editor = mDataFiles.edit();
        editor.putInt(DATA_FILE_CORRECT, a);
        editor.apply();
    }

    public void onClickOk5(View view){
        TextView text =  findViewById(R.id.textViewEnterWrong);
        int a = Integer.parseInt(text.getText().toString());
        SharedPreferences.Editor editor = mDataFiles.edit();
        editor.putInt(DATA_FILE_WRONG, a);
        editor.apply();
    }
}
