package com.MaxEle.maximarius.nir_navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedList;

public class ConstructActivity extends AppCompatActivity {

    public static final String DATA_FILE = "datafile";
    public static final String DATA_FILE_MY_TASKS = "my_tasks";
    public static final String DATA_FILE_LEVEL = "level";
    public static final String DATA_FILE_THEME_LIGHT = "theme_light";

    TextView arr1;
    TextView arr2;
    TextView arr3;
    TextView arr4;
    TextView arr5;
    TextView arr6;
    TextView arr7;
    TextView arr8;

    ToggleButton ButtonAct1;
    ToggleButton ButtonAct2;
    ToggleButton ButtonAct3;
    ToggleButton ButtonAct4;
    ToggleButton ButtonAct5;
    ToggleButton ButtonAct6;
    ToggleButton ButtonAct7;
    ToggleButton ButtonAct8;
    ToggleButton ButtonAct9;
    ToggleButton ButtonAct10;
    byte kolvo = 0;
    byte mest1 = 0;
    byte mest2 = 0;
    byte mest3 = 0;
    byte mest4 = 0;
    byte mest5 = 0;
    byte mest6 = 0;
    byte mest7 = 0;
    byte mest8 = 0;

    Dialog Instract;

    Spinner spinMyType;
    ArrayList<String> listoftasks = new ArrayList<>();
    LinkedList<String> splitedTasks= new LinkedList<>();

    boolean SaveorDelFlag=true;
    String[] actionnames;
    boolean[] Active = new boolean[10];

    boolean Theme_Light;
    int AvaLevel;
    SharedPreferences mDataFiles;

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

        setContentView(R.layout.activity_construct);

        mDataFiles = getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);
        AvaLevel = mDataFiles.getInt(DATA_FILE_LEVEL, 0);

        actionnames = getResources().getStringArray(R.array.action_number);

        Instract = new Dialog(ConstructActivity.this);
        Instract.setTitle(getResources().getString(R.string.instructions));
        Instract.setContentView(R.layout.dialog_view);

        ScrollView viewansw = findViewById(R.id.viewCond);
        LinearLayout viewmain = Instract.findViewById(R.id.viewMain);
        ScrollView scrolldialog = Instract.findViewById(R.id.scrolldia);
        if (Theme_Light){
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            viewansw = findViewById(R.id.scrollButtons);
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            viewmain.setBackgroundColor(getResources().getColor(R.color.backgroundview));
            scrolldialog.setBackgroundColor(getResources().getColor(R.color.backgroundview));
        }else {
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
            viewansw = findViewById(R.id.scrollButtons);
            viewansw.setBackgroundColor(getResources().getColor(R.color.backgroundview1));
            viewmain.setBackgroundColor(getResources().getColor(R.color.background1));
            scrolldialog.setBackgroundColor(getResources().getColor(R.color.background1));
        }

        TextView destext1 = Instract.findViewById(R.id.textViewDescr1);
        destext1.setText(R.string.descrconstr_1);
        TextView destext2 = Instract.findViewById(R.id.textViewDescr2);
       // destext2.setText(R.string.descrconstr_2);
        TextView destext3 = Instract.findViewById(R.id.textViewDescr3);
        destext3.setText("");
        TextView destext4 = Instract.findViewById(R.id.textViewDescr4);
        destext4.setText("");
        ImageView desIm1 = Instract.findViewById(R.id.imageViewIm1);
        ImageView desIm2 = Instract.findViewById(R.id.imageViewIm2);
        ImageView desIm3 = Instract.findViewById(R.id.imageViewIm3);
        ImageView desIm4 = Instract.findViewById(R.id.imageViewIm4);
        desIm1.setImageDrawable(null);
        desIm2.setImageDrawable(null);
        desIm3.setImageDrawable(null);
        desIm4.setImageDrawable(null);

        spinMyType = findViewById(R.id.mytaskslist);
        String AllMyTasks = mDataFiles.getString(DATA_FILE_MY_TASKS,"");
        if (!AllMyTasks.equals("")){
            listoftasks.add(getResources().getString(R.string.CreateTaskList));
            String[] tasks = AllMyTasks.split("/");
            for(String s:tasks){
                String[] task_harak = s.split(",");
                splitedTasks.add(task_harak[0]);
                splitedTasks.add(task_harak[1]);
                splitedTasks.add(task_harak[2]);
                listoftasks.add(task_harak[2]);
            }
            spinMyType.setVisibility(View.VISIBLE);



            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listoftasks);
            if (Theme_Light){
                adapter.setDropDownViewResource(R.layout.spinner_light);
            }else {
                adapter.setDropDownViewResource(R.layout.spinner_dark);
            }
            spinMyType.setAdapter(adapter);

        }else{
            listoftasks.add(getResources().getString(R.string.CreateTaskList));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listoftasks);
            if (Theme_Light){
                adapter.setDropDownViewResource(R.layout.spinner_light);
            }else {
                adapter.setDropDownViewResource(R.layout.spinner_dark);
            }
            spinMyType.setAdapter(adapter);
        }

        final Button SaveOrDelete = findViewById(R.id.buttonCreate);
        final ScrollView ButtonsScroll = findViewById(R.id.scrollButtons);
        spinMyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                ClearScr();
                EditText NameTask = findViewById(R.id.textViewName);
                EditText DiapTask = findViewById(R.id.textViewDiap);
                String spinText = spinMyType.getSelectedItem().toString();
                kolvo=0;
                if (spinText.equals(getResources().getString(R.string.CreateTaskList))){
                    ClearScr();
                    ButtonsScroll.setVisibility(View.VISIBLE);
                    SaveOrDelete.setText(R.string.Save);
                    NameTask.setEnabled(true);
                    DiapTask.setEnabled(true);
                    SaveorDelFlag = true;
                }else{
                    SaveorDelFlag = false;
                    ButtonsScroll.setVisibility(View.INVISIBLE);
                    SaveOrDelete.setText(R.string.Delete);
                    int nom = spinMyType.getSelectedItemPosition()-1;
                    String strnumsofacts = splitedTasks.get(nom*3);
                    String[] numberAct = strnumsofacts.split("_");
                    NameTask.setText(splitedTasks.get(2+nom*3));
                    DiapTask.setText(splitedTasks.get(1+nom*3));
                    NameTask.setEnabled(false);
                    DiapTask.setEnabled(false);
                    for (int i=0;i<numberAct.length;i++){
                        int nomAct=Integer.parseInt(numberAct[i]);
                        addInnerIfAction((byte)(i+1),(byte)nomAct);
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        for (int i=0;i<10; i++) Active[i]=false;

        arr1 = findViewById(R.id.textViewArrow1);
        arr1.setVisibility(View.INVISIBLE);
        arr2 = findViewById(R.id.textViewArrow2);
        arr2.setVisibility(View.INVISIBLE);
        arr3 = findViewById(R.id.textViewArrow3);
        arr3.setVisibility(View.INVISIBLE);
        arr4 = findViewById(R.id.textViewArrow4);
        arr4.setVisibility(View.INVISIBLE);
        arr5 = findViewById(R.id.textViewArrow5);
        arr5.setVisibility(View.INVISIBLE);
        arr6 = findViewById(R.id.textViewArrow6);
        arr6.setVisibility(View.INVISIBLE);
        arr7 = findViewById(R.id.textViewArrow7);
        arr7.setVisibility(View.INVISIBLE);
        arr8 = findViewById(R.id.textViewArrow8);
        arr8.setVisibility(View.INVISIBLE);


        ButtonAct1 = findViewById(R.id.buttonAct1);
        ButtonAct1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MakeButtGrey(ButtonAct1);
                    if ((kolvo <= 7) && (!Active[0])) {
                        addInnerIfAction((byte)(kolvo+1), (byte)1);
                        Active[0] = true;
                        kolvo++;
                    }else{ButtonAct1.setChecked(false);}
                } else {
                    deleteInnerIfAction((byte)1);
                    if (Active[0]) {
                        ButtonAct1.setChecked(true);
                    }else{
                        kolvo--;
                        MakeButtNormal(ButtonAct1);
                    }
                }
            }
        });
        ButtonAct2 = findViewById(R.id.buttonAct2);
        ButtonAct2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MakeButtGrey(ButtonAct2);
                    if ((kolvo <= 7) && (!Active[1])) {
                        addInnerIfAction((byte)(kolvo+1), (byte)2);
                        Active[1] = true;
                        kolvo++;
                    }else{ButtonAct2.setChecked(false);}
                } else {
                    deleteInnerIfAction((byte)2);
                    if (Active[1]) {
                        ButtonAct2.setChecked(true);
                    }else{
                        kolvo--;
                        MakeButtNormal(ButtonAct2);
                    }
                }
            }
        });
        ButtonAct3 = findViewById(R.id.buttonAct3);
        ButtonAct3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MakeButtGrey(ButtonAct3);
                    if ((kolvo <= 7) && (!Active[2])) {
                        addInnerIfAction((byte)(kolvo+1), (byte)3);
                        Active[2] = true;
                        kolvo++;
                    }else{ButtonAct3.setChecked(false);}
                } else {
                    deleteInnerIfAction((byte)3);
                    if (Active[2]) {
                        ButtonAct3.setChecked(true);
                    }else{
                        kolvo--;
                        MakeButtNormal(ButtonAct3);
                    }
                }
            }
        });
        ButtonAct4 = findViewById(R.id.buttonAct4);
        ButtonAct4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MakeButtGrey(ButtonAct4);
                    if ((kolvo <= 7) && (!Active[3])) {
                        addInnerIfAction((byte)(kolvo+1), (byte)4);
                        Active[3] = true;
                        kolvo++;
                    }else{ButtonAct4.setChecked(false);}
                } else {
                    deleteInnerIfAction((byte)4);
                    if (Active[3]) {
                        ButtonAct4.setChecked(true);
                    }else{
                        kolvo--;
                        MakeButtNormal(ButtonAct4);
                    }
                }
            }
        });
        ButtonAct5 = findViewById(R.id.buttonAct5);
        ButtonAct5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MakeButtGrey(ButtonAct5);
                    if ((kolvo <= 7) && (!Active[4])) {
                        addInnerIfAction((byte)(kolvo+1), (byte)5);
                        Active[4] = true;
                        kolvo++;
                    }else{ButtonAct5.setChecked(false);}
                } else {
                    deleteInnerIfAction((byte)5);
                    if (Active[4]) {
                        ButtonAct5.setChecked(true);
                    }else{
                        kolvo--;
                        MakeButtNormal(ButtonAct5);
                    }
                }
            }
        });
        ButtonAct6 = findViewById(R.id.buttonAct6);
        ButtonAct6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MakeButtGrey(ButtonAct6);
                    if ((kolvo <= 7) && (!Active[5])) {
                        addInnerIfAction((byte)(kolvo+1), (byte)6);
                        Active[5] = true;
                        kolvo++;
                    }else{ButtonAct6.setChecked(false);}
                } else {
                    deleteInnerIfAction((byte)6);
                    if (Active[5]) {
                        ButtonAct6.setChecked(true);
                    }else{
                        kolvo--;
                        MakeButtNormal(ButtonAct6);
                    }
                }
            }
        });
        ButtonAct7 = findViewById(R.id.buttonAct7);
        ButtonAct7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MakeButtGrey(ButtonAct7);
                    if ((kolvo <= 7) && (!Active[6])) {
                        addInnerIfAction((byte)(kolvo+1), (byte)7);
                        Active[6] = true;
                        kolvo++;
                    }else{ButtonAct7.setChecked(false);}
                } else {
                    deleteInnerIfAction((byte)7);
                    if (Active[6]) {
                        ButtonAct7.setChecked(true);
                    }else{
                        kolvo--;
                        MakeButtNormal(ButtonAct7);
                    }
                }
            }
        });
        ButtonAct8 = findViewById(R.id.buttonAct8);
        ButtonAct8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MakeButtGrey(ButtonAct8);
                    if ((kolvo <= 7) && (!Active[7])) {
                        addInnerIfAction((byte)(kolvo+1), (byte)8);
                        Active[7] = true;
                        kolvo++;
                    }else{ButtonAct8.setChecked(false);}
                } else {
                    deleteInnerIfAction((byte)8);
                    if (Active[7]) {
                        ButtonAct8.setChecked(true);
                    }else{
                        kolvo--;
                        MakeButtNormal(ButtonAct8);
                    }
                }
            }
        });
        ButtonAct9 = findViewById(R.id.buttonAct9);
        ButtonAct9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MakeButtGrey(ButtonAct9);
                    if ((kolvo <= 7) && (!Active[8])) {
                        addInnerIfAction((byte)(kolvo+1), (byte)9);
                        Active[8] = true;
                        kolvo++;
                    }else{ButtonAct9.setChecked(false);}
                } else {
                    deleteInnerIfAction((byte)9);
                    if (Active[8]) {
                        ButtonAct9.setChecked(true);
                    }else{
                        kolvo--;
                        MakeButtNormal(ButtonAct9);
                    }
                }
            }
        });
        ButtonAct10 = findViewById(R.id.buttonAct10);
        ButtonAct10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MakeButtGrey(ButtonAct10);
                    if ((kolvo <= 7) && (!Active[9])) {
                        addInnerIfAction((byte)(kolvo+1), (byte)10);
                        Active[9] = true;
                        kolvo++;
                    }else{ButtonAct10.setChecked(false);}
                } else {
                    deleteInnerIfAction((byte)10);
                    if (Active[9]) {
                        ButtonAct10.setChecked(true);
                    }else{
                        kolvo--;
                        MakeButtNormal(ButtonAct10);
                    }
                }
            }
        });

    }

    public void MakeButtGrey(Button but){
        if (Theme_Light){
            but.getBackground().setColorFilter(getResources().getColor(R.color.edithint1), PorterDuff.Mode.MULTIPLY);
        }else {
            but.getBackground().setColorFilter(getResources().getColor(R.color.edithint1), PorterDuff.Mode.MULTIPLY);
        }
    }
    public void MakeButtNormal(Button but){
        if (Theme_Light){
            but.getBackground().setColorFilter(getResources().getColor(R.color.backgroundbutton), PorterDuff.Mode.MULTIPLY);
        }else {
            but.getBackground().setColorFilter(getResources().getColor(R.color.background1), PorterDuff.Mode.MULTIPLY);
        }
    }
    @Override
    public void onBackPressed() {
        ConstructActivity.this.finish();
        Intent intent = new Intent(ConstructActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }
    public void onClickBack(View view) {
        ConstructActivity.this.finish();
        Intent intent = new Intent(ConstructActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.act_out_back,R.anim.act_in_back);
    }

    public void addInnerIfAction(byte place, byte numAct){
        TextView name;
        TextView arrow;
        switch (place){
            case 1:
                name=findViewById(R.id.textViewChosen1);
                name.setText(actionnames[numAct-1]);
                arrow=findViewById(R.id.textViewArrow1);
                arrow.setVisibility(View.VISIBLE);
                mest1=numAct;
                break;
            case 2:
                name=findViewById(R.id.textViewChosen2);
                name.setText(actionnames[numAct-1]);
                arrow=findViewById(R.id.textViewArrow2);
                arrow.setVisibility(View.VISIBLE);
                mest2=numAct;
                break;
            case 3:
                name=findViewById(R.id.textViewChosen3);
                name.setText(actionnames[numAct-1]);
                arrow=findViewById(R.id.textViewArrow3);
                arrow.setVisibility(View.VISIBLE);
                mest3=numAct;
                break;
            case 4:
                name=findViewById(R.id.textViewChosen4);
                name.setText(actionnames[numAct-1]);
                arrow=findViewById(R.id.textViewArrow4);
                arrow.setVisibility(View.VISIBLE);
                mest4=numAct;
                break;
            case 5:
                name=findViewById(R.id.textViewChosen5);
                name.setText(actionnames[numAct-1]);
                arrow=findViewById(R.id.textViewArrow5);
                arrow.setVisibility(View.VISIBLE);
                mest5=numAct;
                break;
            case 6:
                name=findViewById(R.id.textViewChosen6);
                name.setText(actionnames[numAct-1]);
                arrow=findViewById(R.id.textViewArrow6);
                arrow.setVisibility(View.VISIBLE);
                mest6=numAct;
                break;
            case 7:
                name=findViewById(R.id.textViewChosen7);
                name.setText(actionnames[numAct-1]);
                arrow=findViewById(R.id.textViewArrow7);
                arrow.setVisibility(View.VISIBLE);
                mest7=numAct;
                break;
            case 8:
                name=findViewById(R.id.textViewChosen8);
                name.setText(actionnames[numAct-1]);
                arrow=findViewById(R.id.textViewArrow8);
                arrow.setVisibility(View.VISIBLE);
                mest8=numAct;
                break;
        }
    }

    public void deleteInnerIfAction(byte numAct){
        TextView name;
        TextView arrow;
        switch (kolvo){
            case 1:
                if (mest1==numAct){
                    name=findViewById(R.id.textViewChosen1);
                    name.setText("");
                    arrow=findViewById(R.id.textViewArrow1);
                    arrow.setVisibility(View.INVISIBLE);
                    Active[numAct-1]=false;
                }
                break;
            case 2:
                if (mest2==numAct){
                    name=findViewById(R.id.textViewChosen2);
                    name.setText("");
                    arrow=findViewById(R.id.textViewArrow2);
                    arrow.setVisibility(View.INVISIBLE);
                    Active[numAct-1]=false;
                }
                break;
            case 3:
                if (mest3==numAct){
                    name=findViewById(R.id.textViewChosen3);
                    name.setText("");
                    arrow=findViewById(R.id.textViewArrow3);
                    arrow.setVisibility(View.INVISIBLE);
                    Active[numAct-1]=false;
                }
                break;
            case 4:
                if (mest4==numAct){
                    name=findViewById(R.id.textViewChosen4);
                    name.setText("");
                    arrow=findViewById(R.id.textViewArrow4);
                    arrow.setVisibility(View.INVISIBLE);
                    Active[numAct-1]=false;
                }
                break;
            case 5:
                if (mest5==numAct){
                    name=findViewById(R.id.textViewChosen5);
                    name.setText("");
                    arrow=findViewById(R.id.textViewArrow5);
                    arrow.setVisibility(View.INVISIBLE);
                    Active[numAct-1]=false;
                }
                break;
            case 6:
                if (mest6==numAct){
                    name=findViewById(R.id.textViewChosen6);
                    name.setText("");
                    arrow=findViewById(R.id.textViewArrow6);
                    arrow.setVisibility(View.INVISIBLE);
                    Active[numAct-1]=false;
                }
                break;
            case 7:
                if (mest7==numAct){
                    name=findViewById(R.id.textViewChosen7);
                    name.setText("");
                    arrow=findViewById(R.id.textViewArrow7);
                    arrow.setVisibility(View.INVISIBLE);
                    Active[numAct-1]=false;
                }
                break;
            case 8:
                if (mest8==numAct){
                    name=findViewById(R.id.textViewChosen8);
                    name.setText("");
                    arrow=findViewById(R.id.textViewArrow8);
                    arrow.setVisibility(View.INVISIBLE);
                    Active[numAct-1]=false;
                }
                break;
        }
    }

    public void onClickCreate(View view) {
        if (AvaLevel<7){
            String s = getResources().getString(R.string.toastNotAvailMyTask);
            Toast.makeText(getApplicationContext(), s,
                    Toast.LENGTH_SHORT).show();
        }else {
            String s;
            EditText NameTask = findViewById(R.id.textViewName);
            EditText DiapTask = findViewById(R.id.textViewDiap);
            if (SaveorDelFlag) {
                String stringDiap = DiapTask.getText().toString().replace(',', '.');
                String stringName = NameTask.getText().toString();
                boolean isNumAns = isNumeric(stringDiap);
                if (kolvo != 0) {
                    if (isNumAns) {
                        if (stringName.equals("")) {
                            s = getResources().getString(R.string.toastnoname);
                            Toast.makeText(getApplicationContext(), s,
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            String ComplString = "";
                            SharedPreferences.Editor editor = mDataFiles.edit();
                            switch (kolvo) {
                                case 1:
                                    ComplString = "" + mest1;
                                    break;
                                case 2:
                                    ComplString = mest1 + "_" + mest2;
                                    break;
                                case 3:
                                    ComplString = mest1 + "_" + mest2 + "_" + mest3;
                                    break;
                                case 4:
                                    ComplString = mest1 + "_" + mest2 + "_" + mest3 + "_" + mest4;
                                    break;
                                case 5:
                                    ComplString = mest1 + "_" + mest2 + "_" + mest3 + "_" + mest4 + "_" + mest5;
                                    break;
                                case 6:
                                    ComplString = mest1 + "_" + mest2 + "_" + mest3 + "_" + mest4 + "_" + mest5 + "_" + mest6;
                                    break;
                                case 7:
                                    ComplString = mest1 + "_" + mest2 + "_" + mest3 + "_" + mest4 + "_" + mest5 + "_" + mest6 + "_" + mest7;
                                    break;
                                case 8:
                                    ComplString = mest1 + "_" + mest2 + "_" + mest3 + "_" + mest4 + "_" + mest5 + "_" + mest6 + "_" + mest7 + "_" + mest8;
                                    break;
                            }
                            splitedTasks.add(ComplString);
                            ComplString = ComplString + "," + stringDiap;
                            ComplString = ComplString + "," + stringName + "/";
                            ComplString = mDataFiles.getString(DATA_FILE_MY_TASKS, "") + ComplString;
                            editor.putString(DATA_FILE_MY_TASKS, ComplString);
                            editor.apply();
                            splitedTasks.add(stringDiap);
                            splitedTasks.add(stringName);

                            listoftasks.add(stringName);
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listoftasks);
                            if (Theme_Light){
                adapter.setDropDownViewResource(R.layout.spinner_light);
            }else {
                adapter.setDropDownViewResource(R.layout.spinner_dark);
            }
                            spinMyType.setAdapter(adapter);

                            s = getResources().getString(R.string.toastyestask1) + "'" + stringName + "'" + getResources().getString(R.string.toastyestask2);
                            Toast.makeText(getApplicationContext(), s,
                                    Toast.LENGTH_SHORT).show();
                            ClearScr();
                        }
                    } else {
                        s = getResources().getString(R.string.toastnodiap);
                        Toast.makeText(getApplicationContext(), s,
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    s = getResources().getString(R.string.toastnotask);
                    Toast.makeText(getApplicationContext(), s,
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                NameTask.setEnabled(true);
                DiapTask.setEnabled(true);
                int nom = spinMyType.getSelectedItemPosition() - 1;
                spinMyType.setSelection(0);


                s = getResources().getString(R.string.DeleteTaskToast1) + listoftasks.get(nom + 1) + getResources().getString(R.string.DeleteTaskToast2);
                listoftasks.remove(nom + 1);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listoftasks);
                if (Theme_Light){
                adapter.setDropDownViewResource(R.layout.spinner_light);
            }else {
                adapter.setDropDownViewResource(R.layout.spinner_dark);
            }
                spinMyType.setAdapter(adapter);

                String AllMyTasks = mDataFiles.getString(DATA_FILE_MY_TASKS, "");
                String[] tasks = AllMyTasks.split("/");
                for (String s1 : tasks) {
                    String[] task_harak = s1.split(",");
                    splitedTasks.add(task_harak[0]);
                    splitedTasks.add(task_harak[1]);
                    splitedTasks.add(task_harak[2]);
                }
                splitedTasks.remove(3 * (nom));
                splitedTasks.remove(3 * (nom));
                splitedTasks.remove(3 * (nom));
                String CompStr = "";
                for (int i = 1; i < tasks.length; i++) {
                    CompStr = CompStr + splitedTasks.get(3 * (i - 1)) + "," + splitedTasks.get(1 + 3 * (i - 1)) + "," + splitedTasks.get(2 + 3 * (i - 1)) + "/";
                }
                SharedPreferences.Editor editor = mDataFiles.edit();
                editor.putString(DATA_FILE_MY_TASKS, CompStr);
                editor.apply();
                Toast.makeText(getApplicationContext(), s,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ClearScr(){
        EditText NameTask = findViewById(R.id.textViewName);
        EditText DiapTask = findViewById(R.id.textViewDiap);
        NameTask.setText("");
        DiapTask.setText("");
        TextView Choosen1 = findViewById(R.id.textViewChosen1);
        TextView Choosen2 = findViewById(R.id.textViewChosen2);
        TextView Choosen3 = findViewById(R.id.textViewChosen3);
        TextView Choosen4 = findViewById(R.id.textViewChosen4);
        TextView Choosen5 = findViewById(R.id.textViewChosen5);
        TextView Choosen6 = findViewById(R.id.textViewChosen6);
        TextView Choosen7 = findViewById(R.id.textViewChosen7);
        TextView Choosen8 = findViewById(R.id.textViewChosen8);
        Choosen1.setText("");
        Choosen2.setText("");
        Choosen3.setText("");
        Choosen4.setText("");
        Choosen5.setText("");
        Choosen6.setText("");
        Choosen7.setText("");
        Choosen8.setText("");

        arr1 = findViewById(R.id.textViewArrow1);
        arr1.setVisibility(View.INVISIBLE);
        arr2 = findViewById(R.id.textViewArrow2);
        arr2.setVisibility(View.INVISIBLE);
        arr3 = findViewById(R.id.textViewArrow3);
        arr3.setVisibility(View.INVISIBLE);
        arr4 = findViewById(R.id.textViewArrow4);
        arr4.setVisibility(View.INVISIBLE);
        arr5 = findViewById(R.id.textViewArrow5);
        arr5.setVisibility(View.INVISIBLE);
        arr6 = findViewById(R.id.textViewArrow6);
        arr6.setVisibility(View.INVISIBLE);
        arr7 = findViewById(R.id.textViewArrow7);
        arr7.setVisibility(View.INVISIBLE);
        arr8 = findViewById(R.id.textViewArrow8);
        arr8.setVisibility(View.INVISIBLE);
        kolvo = 0;
        mest1 = 0;
        mest2 = 0;
        mest3 = 0;
        mest4 = 0;
        mest5 = 0;
        mest6 = 0;
        mest7 = 0;
        mest8 = 0;
        for (int i=0;i<10; i++) Active[i]=false;
        ButtonAct1.setChecked(false);
        ButtonAct2.setChecked(false);
        ButtonAct3.setChecked(false);
        ButtonAct4.setChecked(false);
        ButtonAct5.setChecked(false);
        ButtonAct6.setChecked(false);
        ButtonAct7.setChecked(false);
        ButtonAct8.setChecked(false);
        ButtonAct9.setChecked(false);
        ButtonAct10.setChecked(false);
    }

    public void onClickInstr(View view) {Instract.show(); }
    public void onClickBackDial(View view) {
        Instract.dismiss();
    }

    private static boolean isNumeric(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
