package com.MaxEle.maximarius.nir_navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;

public class TestEntryActivity extends AppCompatActivity {
    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
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
    ImageView kursim;
    EditText KursEdit;
    EditText PosadEdit;

    Button Lbut;
    Button Rbut;
    boolean La = false;
    boolean Ra = true;

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

        setContentView(R.layout.activity_test_entry);

        AdView mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        }else{
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        image = findViewById(R.id.imageViewrot);
        kursim = findViewById(R.id.imageViewkur);

        Instract = new Dialog(TestEntryActivity.this);
        Instract.setTitle(getResources().getString(R.string.instructions));
        Instract.setContentView(R.layout.dialog_view);

        ImageView plane = findViewById(R.id.imagePlane);
        LinearLayout viewmain = Instract.findViewById(R.id.viewMain);
        ScrollView scrolldialog = Instract.findViewById(R.id.scrolldia);
        if (Theme_Light){
            plane.setImageResource(R.drawable.plane_for_rot);
            kursim.setImageResource(R.drawable.kurs);
            viewmain.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            scrolldialog.setBackgroundColor(getResources().getColor(R.color.backgroundview));
        }else {
            plane.setImageResource(R.drawable.plane_for_rot_dark);
            kursim.setImageResource(R.drawable.kurs_for_rot_dark);
            viewmain.setBackgroundColor(getResources().getColor(R.color.background1));
            scrolldialog.setBackgroundColor(getResources().getColor(R.color.background1));
        }


        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);
        dXcent =(float)(metricsB.widthPixels)/2;
        dYcent =(float)(4*(metricsB.heightPixels)/11);

        Lbut = findViewById(R.id.buttonL);
        Rbut = findViewById(R.id.buttonR);
        Rbut.setEnabled(false);
        if (Theme_Light){
            Rbut.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbuttonpressed), PorterDuff.Mode.MULTIPLY);
        }else {
            Rbut.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbuttonpressed1), PorterDuff.Mode.MULTIPLY);
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

        KursEdit = findViewById(R.id.textViewkurs);
        KursEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String kursstr = KursEdit.getText().toString();
                boolean isNumAns= isNumeric(kursstr);
                if (isNumAns) {

                    int kursnumb = Integer.parseInt(KursEdit.getText().toString());
                    kursnumb = makeYgol0_360d(kursnumb);
                    KursEdit.setText(String.valueOf(kursnumb));
                    kursnumb = (-1)*(kursnumb -360);
                    grad=image.getRotation();
                    grad2=kursim.getRotation();
                    image.setRotation(grad+kursnumb-grad2);
                    kursim.setRotation(kursnumb);
                    float k = grad+kursnumb-grad2;
                    if (Ra){
                        if ((k<=70 && k>=0) || (k<=360 && k>=250)){
                            image.setImageResource(R.drawable.round_r_str);
                        }
                        if (k>70 && k<=180){
                            image.setImageResource(R.drawable.round_r_par);
                        }
                        if (k>180 && k<250){
                            image.setImageResource(R.drawable.round_r_tea);
                        }}
                    if (La){
                        if ((k<=110 && k>=0) || (k<=360 && k>=290)){
                            image.setImageResource(R.drawable.round_l_str);
                        }
                        if (k>=180 && k<290){
                            image.setImageResource(R.drawable.round_l_par);
                        }
                        if (k>110 && k<180){
                            image.setImageResource(R.drawable.round_l_tea);
                        }
                    }

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
            return false;
        });

        PosadEdit = findViewById(R.id.textViewposad);
        PosadEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isNumeric(PosadEdit.getText().toString())) {
                    int posnumb = Integer.parseInt(PosadEdit.getText().toString());
                    posnumb = makeYgol0_360d(posnumb);
                    PosadEdit.setText(String.valueOf(makeYgol0_360d(posnumb)));
                    grad=image.getRotation();
                    grad2=kursim.getRotation();
                    image.setRotation(posnumb+grad2);
                    float k = posnumb+grad2;
                    if (Ra){
                        if ((k<=70 && k>=0) || (k<=360 && k>=250)){
                            image.setImageResource(R.drawable.round_r_str);
                        }
                        if (k>70 && k<=180){
                            image.setImageResource(R.drawable.round_r_par);
                        }
                        if (k>180 && k<250){
                            image.setImageResource(R.drawable.round_r_tea);
                        }}
                    if (La){
                        if ((k<=110 && k>=0) || (k<=360 && k>=290)){
                            image.setImageResource(R.drawable.round_l_str);
                        }
                        if (k>=180 && k<290){
                            image.setImageResource(R.drawable.round_l_par);
                        }
                        if (k>110 && k<180){
                            image.setImageResource(R.drawable.round_l_tea);
                        }
                    }
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
            return false;
        });

        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        grad=image.getRotation();
                        image.setRotation(grad);
                        dX = event.getRawX();
                        dY = event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        newGrad=-(float)(Math.toDegrees(Math.atan2(dXcent-event.getRawX(),dYcent-event.getRawY()))-Math.toDegrees(Math.atan2(dXcent-dX,dYcent-dY)));
                        image.setRotation(grad+newGrad);
                        float k=grad+newGrad;
                        k=makeYgol0_360(k);
                        int l = (int)k;
                        float k1=kursim.getRotation();
                        k1=makeYgol0_360(k-k1);
                        PosadEdit.setText((int)(k1)+"");
                        if (Ra){
                            if ((k<=70 && k>=0) || (k<=360 && k>=250)){
                                image.setImageResource(R.drawable.round_r_str);
                            }
                            if (k>70 && k<=180){
                                image.setImageResource(R.drawable.round_r_par);
                            }
                            if (k>180 && k<250){
                                image.setImageResource(R.drawable.round_r_tea);
                            }}
                        if (La){
                            if ((k<=110 && k>=0) || (k<=360 && k>=290)){
                                image.setImageResource(R.drawable.round_l_str);
                            }
                            if (k>=180 && k<290){
                                image.setImageResource(R.drawable.round_l_par);
                            }
                            if (k>110 && k<180){
                                image.setImageResource(R.drawable.round_l_tea);
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        kursim.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        grad2=kursim.getRotation();
                        kursim.setRotation(grad2);
                        grad=image.getRotation();
                        image.setRotation(grad);
                        dX2 = event.getRawX();
                        dY2 = event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        newGrad2=-(float)(Math.toDegrees(Math.atan2(dXcent-event.getRawX(),dYcent-event.getRawY()))-Math.toDegrees(Math.atan2(dXcent-dX2,dYcent-dY2)));
                        kursim.setRotation(grad2+newGrad2);
                        newGrad=-(float)(Math.toDegrees(Math.atan2(dXcent-event.getRawX(),dYcent-event.getRawY()))-Math.toDegrees(Math.atan2(dXcent-dX,dYcent-dY)));
                        image.setRotation(grad+newGrad2);
                        float k=grad+newGrad2;
                        k=makeYgol0_360(k);
                        float k1=grad2+newGrad2;
                        k1=makeYgol0_360(k1);
                        int l = (int)k1;
                        l=(-1)*(l-360);
                        float krn = makeYgol0_360(k+l);
                        KursEdit.setText(l+"");
                       // l = (int)k;
                       // l=(-1)*(l-360);
                        PosadEdit.setText((int)krn+"");
                        if (Ra){
                        if ((k<=70 && k>=0) || (k<=360 && k>=250)){
                            image.setImageResource(R.drawable.round_r_str);
                        }
                        if (k>70 && k<=180){
                            image.setImageResource(R.drawable.round_r_par);
                        }
                        if (k>180 && k<250){
                            image.setImageResource(R.drawable.round_r_tea);
                        }}
                        if (La){
                            if ((k<=110 && k>=0) || (k<=360 && k>=290)){
                                image.setImageResource(R.drawable.round_l_str);
                            }
                            if (k>=180 && k<290){
                                image.setImageResource(R.drawable.round_l_par);
                            }
                            if (k>110 && k<180){
                                image.setImageResource(R.drawable.round_l_tea);
                            }
                        }
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
    public void onBackPressed() {
        TestEntryActivity.this.finish();
        Intent intent = new Intent(TestEntryActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }
    public void onClickBack(View view){
        TestEntryActivity.this.finish();
        Intent intent = new Intent(TestEntryActivity.this, MainActivity.class);
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
    private static boolean isNumeric(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public void onClickInstr(View view) {
        Instract.show();
    }

    public void onClickBackDial(View view) {
        Instract.dismiss();
    }

    public void onClickL(View view) {
        Lbut.setEnabled(false);
        if (Theme_Light){
            Lbut.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbuttonpressed), PorterDuff.Mode.MULTIPLY);
        }else {
            Lbut.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbuttonpressed1), PorterDuff.Mode.MULTIPLY);
        }
        Rbut.setEnabled(true);
        if (Theme_Light){
            Rbut.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton), PorterDuff.Mode.MULTIPLY);
        }else {
            Rbut.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton1), PorterDuff.Mode.MULTIPLY);
        }
        La=true;
        Ra=false;
        float k = image.getRotation();
        k=makeYgol0_360(k);
        if ((k<=110 && k>=0) || (k<=360 && k>=290)){
            image.setImageResource(R.drawable.round_l_str);
        }
        if (k>=180 && k<290){
            image.setImageResource(R.drawable.round_l_par);
        }
        if (k>110 && k<180){
            image.setImageResource(R.drawable.round_l_tea);
        }
    }

    public void onClickR(View view) {
        Rbut.setEnabled(false);
        if (Theme_Light){
            Rbut.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbuttonpressed), PorterDuff.Mode.MULTIPLY);
        }else {
            Rbut.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbuttonpressed1), PorterDuff.Mode.MULTIPLY);
        }
        Lbut.setEnabled(true);
        if (Theme_Light){
            Lbut.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton), PorterDuff.Mode.MULTIPLY);
        }else {
            Lbut.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton1), PorterDuff.Mode.MULTIPLY);
        }
        Ra=true;
        La=false;
        float k = image.getRotation();
        k=makeYgol0_360(k);
        if ((k<=70 && k>=0) || (k<=360 && k>=250)){
            image.setImageResource(R.drawable.round_r_str);
        }
        if (k>70 && k<=180){
            image.setImageResource(R.drawable.round_r_par);
        }
        if (k>180 && k<250){
            image.setImageResource(R.drawable.round_r_tea);
        }
    }

}
