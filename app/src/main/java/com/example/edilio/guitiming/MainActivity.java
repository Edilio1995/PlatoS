package com.example.edilio.guitiming;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private FileInputStream file;
    private String pathXmlLayout;
    private String pathXmlSequence;
    private Button importA;
    private Button importB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        importA = (Button) findViewById(R.id.gui);
        importB = (Button) findViewById(R.id.sequence);

    }

    private static final int LAY_SELECT_CODE = 0;
    private static final int XML_SELECT_CODE = 1;

    public void performLayoutSearch(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    LAY_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void performOnlineLayoutSearch(View v){
        Intent intent = new Intent(v.getContext(),AltervistaList.class);
        intent.putExtra("Choise",0);
        startActivityForResult(intent, 0);
    }

    public void performOnlineSequenceSearch(View v){
        Intent intent = new Intent(v.getContext(),AltervistaList.class);
        intent.putExtra("Choise",1);
        startActivityForResult(intent, 1);
    }

    public void performXmlSearch(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    XML_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("RESULT", data.getStringExtra("result"));
        switch (requestCode) {
            case 0:{
                MyParamTask param = new MyParamTask(0,  data.getStringExtra("result"), getFilesDir()+"");
                try {
                    pathXmlLayout = new RetrieveSingleFileOnline().execute(param).get();
                    findViewById(R.id.gui).setBackgroundResource(R.drawable.buttonper);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 1: {
                MyParamTask param = new MyParamTask(1,  data.getStringExtra("result"), getFilesDir()+"");
                try {
                    pathXmlSequence = new RetrieveSingleFileOnline().execute(param).get();
                    findViewById(R.id.sequence).setBackgroundResource(R.drawable.buttonper);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public void start(View v) throws XmlPullParserException, IOException {
        Log.d("Pathname", pathXmlLayout);
        Log.d("Pathname", pathXmlSequence);
        BufferedReader br = new BufferedReader(new FileReader(pathXmlSequence));
        String line = null;
        while ((line = br.readLine()) != null) {
            Log.d("ciao",line);
        }
        Intent intent = new Intent (this, SignIn.class);
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("Layout", pathXmlLayout);
        editor.putString("Sequence", pathXmlSequence);
        editor.apply();
        startActivity(intent);
    }
}



