package com.MaxEle.maximarius.nir_navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.widget.NestedScrollView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class NLActivity extends AppCompatActivity {

    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";
    public static final String DATA_FILE_SMALL = "small";
    public static final String DATA_FILE_ADS_DISABLE = "ads_disable";
    public static final String DATA_FILE_LANGUAGE = "language";
    public static final String DATA_FILE_NLINSTRACTIONS = "nl_marked";
    boolean Theme_Light;
    boolean smallsize;
    SharedPreferences mDataFiles;

    boolean Unfliped = true;
    NestedScrollView NLlay;
    NestedScrollView NLCenter;
    ImageView NL1;
    ImageView NL2;
    ImageView NL3;
    ImageView Visisr;
    boolean isBig = true;

    boolean isRussian;
    Dialog Instract;
    SwitchCompat switchNLins;
    boolean isNLMarked;

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

        setContentView(R.layout.activity_nl);

        AdView mAdView = findViewById(R.id.banner_ad);
        boolean AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false);
        if (AdsDis) {
            mAdView.setVisibility(View.INVISIBLE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        Instract = new Dialog(NLActivity.this);
        Instract.setTitle(getResources().getString(R.string.instructions));
        Instract.setContentView(R.layout.dialog_view_nl);


        LinearLayout viewmain = Instract.findViewById(R.id.viewMain);
        ScrollView scrolldialog = Instract.findViewById(R.id.scrolldia);
        switchNLins = Instract.findViewById(R.id.switchInstNL);
        if (Theme_Light) {
            viewmain.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            scrolldialog.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            switchNLins.setTextColor(getResources().getColor(R.color.textsimple));
        } else {
            viewmain.setBackgroundColor(getResources().getColor(R.color.background1));
            scrolldialog.setBackgroundColor(getResources().getColor(R.color.background1));
            switchNLins.setTextColor(getResources().getColor(R.color.textsimple1));
        }


        isRussian = mDataFiles.getBoolean(DATA_FILE_LANGUAGE, true);


        if (mDataFiles.contains(DATA_FILE_SMALL)) {
            smallsize = mDataFiles.getBoolean(DATA_FILE_SMALL, false);
        } else {
            smallsize = false;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putBoolean(DATA_FILE_SMALL, smallsize);
            editor.apply();
        }
        if (mDataFiles.contains(DATA_FILE_NLINSTRACTIONS)) {
            isNLMarked = mDataFiles.getBoolean(DATA_FILE_NLINSTRACTIONS, false);
        } else {
            isNLMarked = false;
            SharedPreferences.Editor editor = mDataFiles.edit();
            editor.putBoolean(DATA_FILE_NLINSTRACTIONS, isNLMarked);
            editor.apply();
        }
        //поставить переключатель маркировки НЛ в нужное положение
        switchNLins.setChecked(isNLMarked);
        if (isNLMarked) {
            if (isRussian)
                switchNLins.setText(R.string.switchInstNLyes);
            else
                switchNLins.setText(R.string.switchInstNLyeseng);
            isNLMarked = true;
        } else {
            if (isRussian)
                switchNLins.setText(R.string.switchInstNLno);
            else
                switchNLins.setText(R.string.switchInstNLnoeng);
            isNLMarked = false;
        }


        NLlay = findViewById(R.id.NLlayout);
        NL1 = findViewById(R.id.imageViewNL1);
        NL2 = findViewById(R.id.imageViewNL2);
        NL3 = findViewById(R.id.imageViewNL3);

        if (smallsize) {
            NL1.setImageResource(R.drawable.nl1_1_d);
            NL2.setImageResource(R.drawable.nl1_2_d);
            NL3.setImageResource(R.drawable.nl1_3_d);
        } else {
            if (isNLMarked) {
                NL1.setImageResource(R.drawable.nl1_1ins);
                NL2.setImageResource(R.drawable.nl1_2ins);
                NL3.setImageResource(R.drawable.nl1_3ins);
            } else {
                NL1.setImageResource(R.drawable.nl1_1);
                NL2.setImageResource(R.drawable.nl1_2);
                NL3.setImageResource(R.drawable.nl1_3);
            }
        }


        NLCenter = findViewById(R.id.scrollCenter);
        NLCenter.post(() -> {
            int cent = NL2.getBottom() / 3;
            NLCenter.smoothScrollTo(0, cent);
        });
        Visisr = findViewById(R.id.imageViewVisir);

        //Обработка перемещения Визирки происходит в CustomImageViewForVisir

        if (switchNLins != null) {
            switchNLins.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (isRussian)
                        switchNLins.setText(R.string.switchInstNLyes);
                    else
                        switchNLins.setText(R.string.switchInstNLyeseng);
                    isNLMarked = true;
                } else {
                    if (isRussian)
                        switchNLins.setText(R.string.switchInstNLno);
                    else
                        switchNLins.setText(R.string.switchInstNLnoeng);
                    isNLMarked = false;
                }
                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putBoolean(DATA_FILE_NLINSTRACTIONS, isNLMarked);
                editor.apply();
                MarkUnmarkNL(isNLMarked);
            });
        }

        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности
        Button ButClose = findViewById(R.id.buttonNLclose);
        ButClose.setOnClickListener(view -> {
            NLActivity.this.finish();
            Intent intent = new Intent(NLActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back);
        });
        Button ButVisir = findViewById(R.id.buttonVIS);
        ButVisir.setOnClickListener(view -> {
            Visisr = findViewById(R.id.imageViewVisir);
            Visisr.setY(0);
        });
        Button ButFlip = findViewById(R.id.buttonNLflip);
        ButFlip.setOnClickListener(view -> {
            Unfliped = !Unfliped;
            if (Unfliped) {
                if (smallsize) {
                    NL1.setImageResource(R.drawable.nl1_1_d);
                    NL2.setImageResource(R.drawable.nl1_2_d);
                    NL3.setImageResource(R.drawable.nl1_3_d);
                } else {
                    if (isNLMarked) {
                        NL1.setImageResource(R.drawable.nl1_1ins);
                        NL2.setImageResource(R.drawable.nl1_2ins);
                        NL3.setImageResource(R.drawable.nl1_3ins);
                    } else {
                        NL1.setImageResource(R.drawable.nl1_1);
                        NL2.setImageResource(R.drawable.nl1_2);
                        NL3.setImageResource(R.drawable.nl1_3);
                    }
                }
            } else {
                if (smallsize) {
                    NL1.setImageResource(R.drawable.nl2_1_d);
                    NL2.setImageResource(R.drawable.nl2_2_d);
                    NL3.setImageResource(R.drawable.nl2_3_d);
                } else {
                    if (isNLMarked) {
                        NL1.setImageResource(R.drawable.nl2_1ins);
                        NL2.setImageResource(R.drawable.nl2_2ins);
                        NL3.setImageResource(R.drawable.nl2_3ins);
                    } else {
                        NL1.setImageResource(R.drawable.nl2_1);
                        NL2.setImageResource(R.drawable.nl2_2);
                        NL3.setImageResource(R.drawable.nl2_3);
                    }
                }
            }
        });
        Button ButOrient = findViewById(R.id.buttonOrient);
        ButOrient.setOnClickListener(view -> {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                Instract.setContentView(R.layout.dialog_view_nl);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Instract.setContentView(R.layout.dialog_view);
                }
            }
        });
        Button ButBackDial = Instract.findViewById(R.id.buttonBack);
        Button ButInstr = findViewById(R.id.buttonInstructon);
        ButInstr.setOnClickListener(view -> {
            TextView destext;
            ImageView desIm;
            desIm = Instract.findViewById(R.id.imageViewIm2);
            desIm.setImageResource(R.drawable.nl_instr2);
            desIm = Instract.findViewById(R.id.imageViewIm3);
            desIm.setImageResource(R.drawable.nl_instr3);
            desIm = Instract.findViewById(R.id.imageViewIm4);
            desIm.setImageResource(R.drawable.nl_instr4);
            desIm = Instract.findViewById(R.id.imageViewIm5);
            desIm.setImageResource(R.drawable.nl_instr5);
            desIm = Instract.findViewById(R.id.imageViewIm6);
            desIm.setImageResource(R.drawable.nl_instr6);
            desIm = Instract.findViewById(R.id.imageViewIm7);
            desIm.setImageResource(R.drawable.nl_instr7);
            desIm = Instract.findViewById(R.id.imageViewIm8);
            desIm.setImageResource(R.drawable.nl_instr8);

            if (isRussian) {
                ButBackDial.setText(R.string.back);
                destext = Instract.findViewById(R.id.textViewSwitch);
                destext.setText(R.string.TextswitchInstNLno);
                destext = Instract.findViewById(R.id.textViewDescr1);
                destext.setText(R.string.NL_dialog1);
                destext = Instract.findViewById(R.id.textViewDescr2);
                destext.setText(R.string.NL_dialog2);
                destext = Instract.findViewById(R.id.textViewDescr3);
                destext.setText(R.string.NL_dialog3);
                destext = Instract.findViewById(R.id.textViewDescr4);
                destext.setText(R.string.NL_dialog4);
                destext = Instract.findViewById(R.id.textViewDescr5);
                destext.setText(R.string.NL_dialog5);
                destext = Instract.findViewById(R.id.textViewDescr6);
                destext.setText(R.string.NL_dialog6);
                destext = Instract.findViewById(R.id.textViewDescr7);
                destext.setText(R.string.NL_dialog7);
                destext = Instract.findViewById(R.id.textViewDescr8);
                destext.setText(R.string.NL_dialog8);
                destext = Instract.findViewById(R.id.textViewDescr9);
                destext.setText(R.string.NL_dialog9);
                destext = Instract.findViewById(R.id.textViewDescr10);
                destext.setText(R.string.NL_dialog10);
                desIm = Instract.findViewById(R.id.imageViewIm1);
                desIm.setImageResource(R.drawable.nl_instr1);
                desIm = Instract.findViewById(R.id.imageViewIm9);
                desIm.setImageResource(R.drawable.nl_instr9);
                desIm = Instract.findViewById(R.id.imageViewIm10);
                desIm.setImageResource(R.drawable.nl_instr10);
            } else {
                ButBackDial.setText(R.string.backeng);
                destext = Instract.findViewById(R.id.textViewSwitch);
                destext.setText(R.string.TextswitchInstNLnoeng);
                destext = Instract.findViewById(R.id.textViewDescr1);
                destext.setText(R.string.NL_dialog1eng);
                destext = Instract.findViewById(R.id.textViewDescr2);
                destext.setText(R.string.NL_dialog2eng);
                destext = Instract.findViewById(R.id.textViewDescr3);
                destext.setText(R.string.NL_dialog3eng);
                destext = Instract.findViewById(R.id.textViewDescr4);
                destext.setText(R.string.NL_dialog4eng);
                destext = Instract.findViewById(R.id.textViewDescr5);
                destext.setText(R.string.NL_dialog5eng);
                destext = Instract.findViewById(R.id.textViewDescr6);
                destext.setText(R.string.NL_dialog6eng);
                destext = Instract.findViewById(R.id.textViewDescr7);
                destext.setText(R.string.NL_dialog7eng);
                destext = Instract.findViewById(R.id.textViewDescr8);
                destext.setText(R.string.NL_dialog8eng);
                destext = Instract.findViewById(R.id.textViewDescr9);
                destext.setText(R.string.NL_dialog9eng);
                destext = Instract.findViewById(R.id.textViewDescr10);
                destext.setText(R.string.NL_dialog10eng);
                desIm = Instract.findViewById(R.id.imageViewIm1);
                desIm.setImageResource(R.drawable.nl_instr1eng);
                desIm = Instract.findViewById(R.id.imageViewIm9);
                desIm.setImageResource(R.drawable.nl_instr9eng);
                desIm = Instract.findViewById(R.id.imageViewIm10);
                desIm.setImageResource(R.drawable.nl_instr10eng);
            }

            Instract.show();
        });

        ButBackDial.setOnClickListener(view -> Instract.dismiss());
        //-----------------------------------------------------------------------

        if (isRussian) {
            ButOrient.setText(R.string.Orientation);
            ButClose.setText(R.string.back);
            ButFlip.setText(R.string.NLflip);
            ButVisir.setText(R.string.Visir);
        } else {
            ButOrient.setText(R.string.Orientationeng);
            ButClose.setText(R.string.backeng);
            ButFlip.setText(R.string.NLflipeng);
            ButVisir.setText(R.string.Visireng);
        }



      /*  Button Button1=findViewById(R.id.buttonNLclose);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);
        Button1=findViewById(R.id.buttonNLflip);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);
        Button1=findViewById(R.id.buttonVIS);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);*/
    }

    public void MarkUnmarkNL(boolean isNLMarked) {
        if (isNLMarked) {
            if (Unfliped) {
                NL1.setImageResource(R.drawable.nl1_1ins);
                NL2.setImageResource(R.drawable.nl1_2ins);
                NL3.setImageResource(R.drawable.nl1_3ins);
            } else {
                NL1.setImageResource(R.drawable.nl2_1ins);
                NL2.setImageResource(R.drawable.nl2_2ins);
                NL3.setImageResource(R.drawable.nl2_3ins);
            }
        } else {
            if (Unfliped) {
                NL1.setImageResource(R.drawable.nl1_1);
                NL2.setImageResource(R.drawable.nl1_2);
                NL3.setImageResource(R.drawable.nl1_3);
            } else {
                NL1.setImageResource(R.drawable.nl2_1);
                NL2.setImageResource(R.drawable.nl2_2);
                NL3.setImageResource(R.drawable.nl2_3);
            }
        }
    }

    @Override
    public void onBackPressed() {
        NLActivity.this.finish();
        Intent intent = new Intent(NLActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back);
    }
}
