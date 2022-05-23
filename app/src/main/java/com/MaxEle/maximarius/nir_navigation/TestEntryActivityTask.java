package com.MaxEle.maximarius.nir_navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class TestEntryActivityTask extends AppCompatActivity {

    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";
    boolean Theme_Light;
    SharedPreferences mDataFiles;

    Dialog Instract;

    Button Answ1;
    Button Answ2;
    Button Answ3;
    Button AnswOK;

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
        setContentView(R.layout.activity_test_entry_task);

        AdView mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        }else{
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        Instract = new Dialog(TestEntryActivityTask.this);
        Instract.setTitle(getResources().getString(R.string.instructions));
        Instract.setContentView(R.layout.dialog_view);

        LinearLayout viewmain = Instract.findViewById(R.id.viewMain);
        ScrollView scrolldialog = Instract.findViewById(R.id.scrolldia);
        if (Theme_Light){
            viewmain.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            scrolldialog.setBackgroundColor(getResources().getColor(R.color.backgroundview));
        }else {
            viewmain.setBackgroundColor(getResources().getColor(R.color.background1));
            scrolldialog.setBackgroundColor(getResources().getColor(R.color.background1));
        }


        Answ1=findViewById(R.id.buttonAnsw1);
        Answ2=findViewById(R.id.buttonAnsw2);
        Answ3=findViewById(R.id.buttonAnsw3);
        AnswOK=findViewById(R.id.buttonAnswOK);
        AnswOK.setVisibility(View.INVISIBLE);
        Answ1.setVisibility(View.INVISIBLE);
        Answ2.setVisibility(View.INVISIBLE);
        Answ3.setVisibility(View.INVISIBLE);


        if (Theme_Light){
            Answ1.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton), PorterDuff.Mode.MULTIPLY);
            Answ2.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton), PorterDuff.Mode.MULTIPLY);
            Answ3.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton), PorterDuff.Mode.MULTIPLY);
        }else {
            Answ1.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton1), PorterDuff.Mode.MULTIPLY);
            Answ2.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton1), PorterDuff.Mode.MULTIPLY);
            Answ3.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton1), PorterDuff.Mode.MULTIPLY);
        }

        TextView destext1 = Instract.findViewById(R.id.textViewDescr1);
        destext1.setText(R.string.descrtask20_1);
        TextView destext2 = Instract.findViewById(R.id.textViewDescr2);
        destext2.setText(R.string.descrtask20_2);
        TextView destext3 = Instract.findViewById(R.id.textViewDescr3);
        destext3.setText(R.string.descrtask20_3);
        TextView destext4 = Instract.findViewById(R.id.textViewDescr4);
        destext4.setText(R.string.descrtask20_4);
        ImageView desIm1 = Instract.findViewById(R.id.imageViewIm1);
        ImageView desIm2 = Instract.findViewById(R.id.imageViewIm2);
        ImageView desIm3 = Instract.findViewById(R.id.imageViewIm3);
        ImageView desIm4 = Instract.findViewById(R.id.imageViewIm4);
        desIm1.setImageResource(R.drawable.instr_krug1);
        desIm2.setImageResource(R.drawable.instr_krug2);
        desIm3.setImageResource(R.drawable.instr_krug3);
        desIm4.setImageResource(R.drawable.instr_krug4);

    }

    @Override
    public void onBackPressed() {
        TestEntryActivityTask.this.finish();
        Intent intent = new Intent(TestEntryActivityTask.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }
    public void onClickBack(View view){
        TestEntryActivityTask.this.finish();
        Intent intent = new Intent(TestEntryActivityTask.this, MainActivity.class);
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

    public void onClickInstr(View view) {
        Instract.show();
    }

    public void onClickBackDial(View view) {
        Instract.dismiss();
    }

    boolean ans1 = false;
    boolean ans2 = false;
    boolean ans3 = false;
    public void onClickTask(View view) {
        Button StartBut = findViewById(R.id.buttonTasksT);
        StartBut.setVisibility(View.INVISIBLE);
        AnswOK.setVisibility(View.VISIBLE);
        Answ1.setVisibility(View.VISIBLE);
        Answ2.setVisibility(View.VISIBLE);
        Answ3.setVisibility(View.VISIBLE);

        TextView ConditionTask = findViewById(R.id.textViewCondition);

        String s;
        int MKpos=(int)(Math.random()*360);
        int MKpol=(int)(Math.random()*360);
        float k = MKpol-MKpos;
        k=makeYgol0_360(k);
        k=(-1)*(k-360);
        int RorL=(int)(Math.random()*2);
        if (RorL==0){
            s = getResources().getString(R.string.leftfortask);
            if ((k<=110 && k>=0) || (k<=360 && k>=290)){
                ans1 = true;
                ans2 = false;
                ans3 = false;
            }
            if (k>=180 && k<=290){
                ans1 = false;
                ans2 = false;
                ans3 = true;
            }
            if (k>=110 && k<=180){
                ans1 = false;
                ans2 = true;
                ans3 = false;
            }
        }else{
            s=getResources().getString(R.string.rightfortask);
            if ((k<=70 && k>=0) || (k<=360 && k>=250)){
                ans1 = true;
                ans2 = false;
                ans3 = false;
            }
            if (k>=70 && k<=180){
                ans1 = false;
                ans2 = false;
                ans3 = true;
            }
            if (k>=180 && k<=250){
                ans1 = false;
                ans2 = true;
                ans3 = false;
            }
        }
        s=getResources().getString(R.string.condtfortask1)+MKpos+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.condtfortask2)+MKpol+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.condtfortask3)+s+"\n"+getResources().getString(R.string.condtfortask4);
        ConditionTask.setText(s);

    }

    public  void SetDisc(){
        Answ1.setEnabled(false);
        Answ2.setEnabled(false);
        Answ3.setEnabled(false);
        ans1 = false;
        ans2 = false;
        ans3 = false;
    }

    public void onClickAnsw1(View view) {
        AnswOK.setVisibility(View.VISIBLE);
        if (ans1){
            Answ1.getBackground().setColorFilter(getResources().getColor(R.color.correctansw), PorterDuff.Mode.MULTIPLY);
        }else{
            Answ1.getBackground().setColorFilter(getResources().getColor(R.color.wrongansw), PorterDuff.Mode.MULTIPLY);
            if (ans2){
                Answ2.getBackground().setColorFilter(getResources().getColor(R.color.correctansw), PorterDuff.Mode.MULTIPLY);
            }
            if (ans3){
                Answ3.getBackground().setColorFilter(getResources().getColor(R.color.correctansw), PorterDuff.Mode.MULTIPLY);
            }
        }
        SetDisc();
    }

    public void onClickAnsw2(View view) {
        AnswOK.setVisibility(View.VISIBLE);
        if (ans2){
            Answ2.getBackground().setColorFilter(getResources().getColor(R.color.correctansw), PorterDuff.Mode.MULTIPLY);
        }else{
            Answ2.getBackground().setColorFilter(getResources().getColor(R.color.wrongansw), PorterDuff.Mode.MULTIPLY);
            if (ans1){
                Answ1.getBackground().setColorFilter(getResources().getColor(R.color.correctansw), PorterDuff.Mode.MULTIPLY);
            }
            if (ans3){
                Answ3.getBackground().setColorFilter(getResources().getColor(R.color.correctansw), PorterDuff.Mode.MULTIPLY);
            }
        }
        SetDisc();
    }

    public void onClickAnsw3(View view) {
        AnswOK.setVisibility(View.VISIBLE);
        if (ans3){
            Answ3.getBackground().setColorFilter(getResources().getColor(R.color.correctansw), PorterDuff.Mode.MULTIPLY);
        }else{
            Answ3.getBackground().setColorFilter(getResources().getColor(R.color.wrongansw), PorterDuff.Mode.MULTIPLY);
            if (ans1){
                Answ1.getBackground().setColorFilter(getResources().getColor(R.color.correctansw), PorterDuff.Mode.MULTIPLY);
            }
            if (ans2){
                Answ2.getBackground().setColorFilter(getResources().getColor(R.color.correctansw), PorterDuff.Mode.MULTIPLY);
            }
        }
        SetDisc();
    }

    public void onClickAnswOK(View view) {

        if (Theme_Light){
            Answ1.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton), PorterDuff.Mode.MULTIPLY);
            Answ2.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton), PorterDuff.Mode.MULTIPLY);
            Answ3.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton), PorterDuff.Mode.MULTIPLY);
        }else {
            Answ1.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton1), PorterDuff.Mode.MULTIPLY);
            Answ2.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton1), PorterDuff.Mode.MULTIPLY);
            Answ3.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton1), PorterDuff.Mode.MULTIPLY);
        }

        AnswOK.setVisibility(View.INVISIBLE);
        Answ1.setEnabled(true);
        Answ2.setEnabled(true);
        Answ3.setEnabled(true);

        TextView ConditionTask = findViewById(R.id.textViewCondition);

        String s;
        int MKpos=(int)(Math.random()*360);
        int MKpol=(int)(Math.random()*360);
        float k = MKpol-MKpos;
        k=makeYgol0_360(k);
        k=(-1)*(k-360);
        int RorL=(int)(Math.random()*2);
        if (RorL==0){
            s = getResources().getString(R.string.leftfortask);
            if ((k<=110 && k>=0) || (k<=360 && k>=290)){
                ans1 = true;
                ans2 = false;
                ans3 = false;
            }
            if (k>=180 && k<=290){
                ans1 = false;
                ans2 = false;
                ans3 = true;
            }
            if (k>=110 && k<=180){
                ans1 = false;
                ans2 = true;
                ans3 = false;
            }
        }else{
            s=getResources().getString(R.string.rightfortask);
            if ((k<=70 && k>=0) || (k<=360 && k>=250)){
                ans1 = true;
                ans2 = false;
                ans3 = false;
            }
            if (k>=70 && k<=180){
                ans1 = false;
                ans2 = false;
                ans3 = true;
            }
            if (k>=180 && k<=250){
                ans1 = false;
                ans2 = true;
                ans3 = false;
            }
        }
        s=getResources().getString(R.string.condtfortask1)+MKpos+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.condtfortask2)+MKpol+getResources().getString(R.string.typeizm_grad)+"\n"+getResources().getString(R.string.condtfortask3)+s+"\n"+getResources().getString(R.string.condtfortask4);
        ConditionTask.setText(s);
    }
}
