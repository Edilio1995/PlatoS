package com.example.edilio.guitiming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AltervistaList extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altervista_list);
        int choise = (int) getIntent().getExtras().get("Choise");
        ArrayList<String> list = new ArrayList<>();
        try {
            list = new RetriveFromAltervista().execute(choise).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        lv = (ListView) findViewById(R.id.itemList);
        if(choise==0) {
            lv.setAdapter(new CustomAdapter(this, list,0, this));
        }
        if(choise==1) {
            lv.setAdapter(new CustomAdapter(this, list,1, this));
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
