package com.example.edilio.guitiming;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Vibrator;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class SimulatorActivity extends AppCompatActivity {
    private ArrayList<AndroidWidget> list;
    private FileInputStream fileLayout;
    private int seqI;
    private List<IdIndex> idList;
    private Long startTime;
    private ArrayList<Long> timeStep = new ArrayList<>();
    private String log;
    private Boolean vibration;
    private Vibrator vib;
    private XMLParser x;
    private Long insStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        list = new ArrayList<AndroidWidget>();
        seqI = 0;
        startTime = System.currentTimeMillis();
        insStartTime = System.currentTimeMillis();
        Log.d("TIMEINI",startTime+"");
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String xmlSequence = prefs.getString("Sequence", "No name defined");
        String  xmlLayout = prefs.getString("Layout", "No name defined");
        File fileSequence = new File(xmlSequence);
        fileLayout = null;
        try {
            fileLayout = new FileInputStream(xmlLayout);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            x = new XMLParser();
        try {
            list = x.parse(fileSequence);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinearLayout main = (LinearLayout) findViewById(R.id.main);
        View view = DynamicLayoutInflator.inflate(this, fileLayout, main);
        DynamicLayoutInflator.setDelegate(view, this);
        idList = DynamicLayoutInflator.getList();
        Log.d("DimensioneInCla", idList.size()+"");
        String userdata = prefs.getString("UserData", "No name defined");
        String tmp = prefs.getString("Vibration", "No name defined");
        if(tmp.equals("true")){
            this.vibration = true;
        }
        else {
            this.vibration = false;
        }
        String lStr = xmlLayout.substring(xmlLayout.lastIndexOf("/"));
        String lStr2 = xmlSequence.substring(xmlSequence.lastIndexOf("/"));
        PackageInfo pInfo = null;
        try {
            pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;
        log = "GuiTiming Ver: " + version + "\n";
        log = log + "Android Device: [" + android.os.Build.MODEL+"] \n";
        log = log + "Simulation on interface: " + lStr +"\n";
        log = log + "Sequence instruction: " + lStr2 +"\n";
        log = log + "[Date: " + Calendar.getInstance().getTime() + "] \n" ;
        log = log + userdata + "\n";
        populateListView();
        populateSpinnerView();
    }

    public void populateListView(){
        for(int i = 0; i < idList.size(); i++){
            if(idList.get(i).getType().equals("ListView")) {
                ListView tmpList = (ListView) findViewById(idList.get(i).getId());
                for (int j = 0; j < list.size(); j++) {
                    if(list.get(j).getId().equals(idList.get(i).getIdString())) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ((ListViewWid)list.get(j)).getList());
                        tmpList.setAdapter(adapter);
                        list.remove(j);
                    }
                }
            }
        }
    }

    public void populateSpinnerView(){
        for(int i = 0; i < idList.size(); i++){
            if(idList.get(i).getType().equals("Spinner")) {
                Spinner tmpSpinner = (Spinner) findViewById(idList.get(i).getId());
                for (int j = 0; j < list.size(); j++) {
                    if(list.get(j).getId().equals(idList.get(i).getIdString())) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ((SpinnerWidget)list.get(j)).getList());
                        tmpSpinner.setAdapter(adapter);
                        list.remove(j);
                    }
                }
            }
        }
    }

    public String findId(int id){
        for(int i=0; i < idList.size() ; i++){
            if(idList.get(i).getId()==id){
                return idList.get(i).getIdString();
            }
        }
        return null;
    }

    public Long getTime(Long timePass){
        return timePass - startTime;
    }

    public  void listenerEvent(View v) {
        Log.d("CIAO",v.getClass().getSimpleName());
        String typeComponent  = v.getClass().getSimpleName();
        final String id;
        log = log + "Component: " + v.getClass().getSimpleName() + " ID: " + findId(v.getId()) + " Time: " + (System.currentTimeMillis() - startTime) + "\n";
        switch (typeComponent) {
            case "Button": {
                Button tmp = (Button) findViewById(v.getId());
                id = findId(tmp.getId());
                Log.d("Tipologia",tmp.getId()+"");
                if (list.get(seqI).getId().equals(id)) {
                    timeStep.add(System.currentTimeMillis()-startTime);
                    if(list.get(seqI).getTime()*1000>(System.currentTimeMillis()-startTime)) {
                        log = log + "Result Ok, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    else {
                        log = log + "Result FAILED, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    startTime = System.currentTimeMillis();
                    seqI++;
                } else {
                    if (vibration) vib.vibrate(200);
                }
                if (list.size() == seqI) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("ListTiming",timeStep);
                    intent.putExtra("Log",log);
                    startActivity(intent);
                }
                break;
            }
            case "RadioButton": {
                RadioButton tmp = (RadioButton) findViewById(v.getId());
                id = findId(tmp.getId());
                if (list.get(seqI).getId().equals(id)) {
                    timeStep.add(System.currentTimeMillis()-startTime);
                    if(list.get(seqI).getTime()*1000>(System.currentTimeMillis()-startTime)) {
                        log = log + "Result Ok, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    else {
                        log = log + "Result FAILED, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    startTime = System.currentTimeMillis();
                    seqI++;
                } else {
                    if (vibration) vib.vibrate(200);
                }
                if (list.size() == seqI) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("ListTiming",timeStep);
                    intent.putExtra("Log",log);
                    startActivity(intent);
                }
                break;
            }
            case "CheckBox" : {
                CheckBox tmp = (CheckBox) findViewById(v.getId());
                id = findId(tmp.getId());
                if (list.get(seqI).getId().equals(id)) {
                    timeStep.add(System.currentTimeMillis()-startTime);
                    if(list.get(seqI).getTime()*1000>(System.currentTimeMillis()-startTime)) {
                        log = log + "Result Ok, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    else {
                        log = log + "Result FAILED, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    startTime = System.currentTimeMillis();
                    seqI++;
                } else {
                    if (vibration) vib.vibrate(200);
                }
                if (list.size() == seqI) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("ListTiming",timeStep);
                    intent.putExtra("Log",log);
                    startActivity(intent);
                }
                break;
            }
            case "EditText": {
                final EditText tmp = (EditText) findViewById(v.getId());
                id = findId(tmp.getId());
                EditTextWid tmpEdit = ((EditTextWid) list.get(seqI));
                String x = tmpEdit.getText();
                tmp.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (list.get(seqI).getId().equals(id)) {
                            EditTextWid tmpEdit = ((EditTextWid) list.get(seqI));
                            String x = tmpEdit.getText();
                            Log.d("Contenuto",tmpEdit.getText()+" ciao");
                            if(x.equals(tmp.getText()+"")){
                                Log.d("OK","Superato");
                                timeStep.add(System.currentTimeMillis()-startTime);
                                if(list.get(seqI).getTime()*1000>(System.currentTimeMillis()-startTime)) {
                                    log = log + "Result Ok, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                                }
                                else {
                                    log = log + "Result FAILED, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                                }
                                startTime = System.currentTimeMillis();
                                seqI++;
                            }
                            if(tmpEdit.getText().equals("")){
                                Log.d("OK","Superato");
                                timeStep.add(System.currentTimeMillis()-startTime);
                                if(list.get(seqI).getTime()*1000>(System.currentTimeMillis()-startTime)) {
                                    log = log + "Result Ok, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                                }
                                else {
                                    log = log + "Result FAILED, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                                }
                                startTime = System.currentTimeMillis();
                                seqI++;
                            }
                        }
                        if (list.size() == seqI) {
                            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                            intent.putExtra("ListTiming",timeStep);
                            intent.putExtra("Log",log);
                            startActivity(intent);
                        }
                    }
                });
               break;
            }
            case "TimePicker":{
                TimePicker tmp = (TimePicker) findViewById(v.getId());
                id = findId(tmp.getId());
                Log.d("Tipologia",tmp.getId()+"");
                if (list.get(seqI).getId().equals(id)) {
                    timeStep.add(System.currentTimeMillis()-startTime);
                    if(list.get(seqI).getTime()*1000>(System.currentTimeMillis()-startTime)) {
                        log = log + "Result Ok, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    else {
                        log = log + "Result FAILED, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    startTime = System.currentTimeMillis();
                    seqI++;
                } else {
                }
                if (list.size() == seqI) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("ListTiming",timeStep);
                    intent.putExtra("Log",log);
                    startActivity(intent);
                }
                break;
            }
            case "DatePicker":{
                DatePicker tmp = (DatePicker) findViewById(v.getId());
                id = findId(tmp.getId());
                Log.d("Tipologia",tmp.getId()+"");
                if (list.get(seqI).getId().equals(id)) {
                    timeStep.add(System.currentTimeMillis()-startTime);
                    if(list.get(seqI).getTime()*1000>(System.currentTimeMillis()-startTime)) {
                        log = log + "Result Ok, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    else {
                        log = log + "Result FAILED, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    startTime = System.currentTimeMillis();
                    seqI++;
                } else {
                }
                if (list.size() == seqI) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("ListTiming",timeStep);
                    intent.putExtra("Log",log);
                    startActivity(intent);
                }
                break;
            }
            case "Spinner":{
                Spinner tmp = (Spinner) findViewById(v.getId());
                id = findId(tmp.getId());
                Log.d("Tipologia",tmp.getId()+"");
                if (list.get(seqI).getId().equals(id)) {
                    timeStep.add(System.currentTimeMillis()-startTime);
                    if(list.get(seqI).getTime()*1000>(System.currentTimeMillis()-startTime)) {
                        log = log + "Result Ok, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    else {
                        log = log + "Result FAILED, Time:" + (System.currentTimeMillis() - startTime) + "\n";
                    }
                    startTime = System.currentTimeMillis();
                    seqI++;
                } else {
                }
                if (list.size() == seqI) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("ListTiming",timeStep);
                    intent.putExtra("Log",log);
                    startActivity(intent);
                }
                break;
            }
        }

    }

    private Boolean doubleBackToExitPressedOnce = false;



    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("HELP");
        dialog.show();
        Button yes = (Button) dialog.findViewById(R.id.btn_yes);
        Button no = (Button) dialog.findViewById(R.id.btn_no);
        Button abort = (Button) dialog.findViewById(R.id.abort);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log = log + "The user has reviewed the instructions [Time: " + (System.currentTimeMillis() - insStartTime) + "]" +"\n";
                final Dialog dialogIns = new Dialog(v.getContext());
                dialogIns.setContentView(R.layout.dialog_istruction);
                dialogIns.setTitle("HELP");
                dialogIns.show();
                TextView yes = (TextView) dialogIns.findViewById(R.id.insDialogText);
                yes.setText(x.getResult());
                Button back = (Button) dialogIns.findViewById(R.id.backSimul);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogIns.dismiss();
                        dialog.dismiss();
                    }
                });
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        abort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("ListTiming",timeStep);
                log = log + "TEST FAILED";
                intent.putExtra("Log",log);
                startActivity(intent);
                return;
            }
        });
    }

}
