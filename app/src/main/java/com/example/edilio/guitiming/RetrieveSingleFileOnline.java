package com.example.edilio.guitiming;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Edilio on 01/12/2017.
 */

public class RetrieveSingleFileOnline extends AsyncTask<MyParamTask,Void, String> {

    @Override
    protected String doInBackground(MyParamTask... voids) {
        MyParamTask task = voids[0];
        Log.d("PArametri", "Nome: " + task.getName() + "Lista " + task.getType());
        int choise = task.getType();
        FTPClient client = new FTPClient();
        try {
            client.connect("ftp.guitiming.altervista.org");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.login("guitiming", "kingigofku94");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.enterLocalPassiveMode();
        FTPFile[] files = new FTPFile[0];
        File x = new File(task.getPath()+"/"+task.getName());
        if (choise == 1) {
            OutputStream outputStream = null;
            boolean success = false;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(x));
                success = client.retrieveFile("/Sequence/" + task.getName(), outputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (choise == 0) {
            OutputStream outputStream = null;
            boolean success = false;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(x));
                success = client.retrieveFile("/AndroidInterface/" + task.getName(), outputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            }
            return x.getAbsolutePath();
        }
    }

