package com.example.edilio.guitiming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class SignIn extends AppCompatActivity {

    private EditText name;
    private EditText surname;
    private EditText birthday;
    private EditText occupation;
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private RadioButton r4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        name = (EditText) findViewById(R.id.nameText);
        surname = (EditText) findViewById(R.id.surnameText);
        birthday = (EditText) findViewById(R.id.birthText);
        occupation = (EditText) findViewById(R.id.occupationText);
        r1 = (RadioButton) findViewById(R.id.affermative);
        r2 = (RadioButton) findViewById(R.id.negative);
        r3 = (RadioButton) findViewById(R.id.affermativeVib);
        r4 = (RadioButton) findViewById(R.id.negativeVib);
    }

    public void foward(View v){
        Boolean question = false;
        Boolean vibration = false;
        if(r1.isChecked()) question = true;
        if(r2.isChecked()) question = false;
        if(r3.isChecked()) vibration = true;
        if(r4.isChecked()) vibration = false;
        UserData user = new UserData(name.getText()+"",surname.getText()+"",birthday.getText()+"",occupation.getText()+"",question,vibration);
        Log.d("User",user.toString());
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("UserData",user.toString()+"");
        editor.putString("Vibration",user.getVibration()+"");
        editor.apply();
        Intent intent = new Intent (this, InstructionActivity.class);
        startActivity(intent);
    }
}
