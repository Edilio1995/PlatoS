package com.example.edilio.guitiming;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Edilio on 04/12/2017.
 */

public class SaveOnAltervista extends AsyncTask<File,Void,Void> {
    @Override
    protected Void doInBackground(File... files) {
        FTPClient client = new FTPClient();
        File file = files[0];
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
        client.enterLocalPassiveMode(); // important!
        try {
            client.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean result = false;
        try {
            result = client.storeFile("/LOG/" + System.currentTimeMillis() +".txt", in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result) Log.v("upload result", "succeeded");
        try {
            client.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
