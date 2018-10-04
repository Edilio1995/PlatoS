package com.example.edilio.guitiming;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ArrayList<Float> timeStep = new ArrayList<>();
    private ListView resultList;
    private ArrayAdapter<String> adapter;
    private String log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        timeStep = (ArrayList<Float>) getIntent().getExtras().get("ListTiming");
        log = (String) getIntent().getExtras().get("Log");
        Log.d("LOG", log);
        resultList = (ListView) findViewById(R.id.resultList);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parseArray(timeStep));
        resultList.setAdapter(adapter);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.submit_dialog);
        dialog.setTitle("RESULT");
        dialog.show();
        Button yes = (Button) dialog.findViewById(R.id.btn_yes1);
        Button no = (Button) dialog.findViewById(R.id.btn_no1);
        final File file = new File(getFilesDir()+"/"+"log.txt");
        DataOutputStream os = null;
        try {
            os = new DataOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            os.writeChars(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SaveOnAltervista().execute(file);
                    dialog.dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

       /* Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"edilio.massaro@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "LOG");
        i.putExtra(Intent.EXTRA_TEXT   , log);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }*/
    }

    private void writeToFile(String data,Context context) {
        try {
            Log.d("SAlvat","SALVATO");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("log.txt", Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void goBack(View v){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public ArrayList<String> parseArray(ArrayList<Float> list){
       ArrayList<String> tmp = new ArrayList<String>();
        for(int i = 0; i < list.size(); i++){
            tmp.add("Instruction[" +i+ "] Time["+list.get(i) +"ms]");
        }
        return tmp;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void retry(View v){
        Intent intent = new Intent(getApplicationContext(),SimulatorActivity.class);
        startActivity(intent);
    }


}
