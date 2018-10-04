package com.example.edilio.guitiming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class InstructionActivity extends AppCompatActivity {

    private String pathXmlLayout;
    private String pathXmlSequence;
    private ArrayList<AndroidWidget> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        pathXmlLayout = prefs.getString("Layout", "No name defined");
        pathXmlSequence = prefs.getString("Sequence", "No name defined");
        setContentView(R.layout.activity_instruction);
        File fileSequence = new File(pathXmlSequence);
        Log.d("FIle", fileSequence.getName());
        XMLParser x = new XMLParser();
        try {
            list = x.parse(fileSequence);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextView textIns = (TextView) findViewById(R.id.insText);
        String ins = x.getResult();
        textIns.setText(ins);
    }

    public void startSimulation(View v){
        Intent intent = new Intent (this, SimulatorActivity.class);
        intent.putExtra("xmlLayout", pathXmlLayout);
        intent.putExtra("xmlSequence", pathXmlSequence);
        startActivity(intent);
    }
}
