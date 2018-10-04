package com.example.edilio.guitiming;

import android.os.AsyncTask;
import android.renderscript.Sampler;
import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Edilio on 01/12/2017.
 */

public class RetriveFromAltervista extends AsyncTask<Integer,Void,ArrayList<String>> {

    private ArrayList<String> list;


    @Override
    protected ArrayList<String> doInBackground(Integer... voids) {
        int choise = voids[0];
        list = new ArrayList<>();
        FTPClient client = new FTPClient();
        try {
            client.connect("ftp.guitiming.altervista.org");
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.enterLocalPassiveMode();
        try {
            client.login("guitiming", "kingigofku94");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FTPFile[] files = new FTPFile[0];
        if(choise==1) {
            try {
                files = client.listFiles("/Sequence/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(choise==0){
            try {
                files = client.listFiles("/AndroidInterface/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (FTPFile file : files) {
            System.out.println(file.getName());
            Log.d("FileSequence", file.getName());
            list.add(file.getName());
            // FileOutputStream fos = new FileOutputStream("Ftp Files/" + file.getName());
            // client.retrieveFile(file.getName(), fos);
        }
        return list;
    }
}
