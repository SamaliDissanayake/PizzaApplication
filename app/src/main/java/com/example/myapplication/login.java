package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.annotation.Inherited;

public class login extends AppCompatActivity {

    private Button btn1;
    private EditText usName;
    private EditText passwod;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn1 = (Button) findViewById(R.id.btnlog);
        usName = (EditText) findViewById(R.id.edUser);
        passwod = (EditText) findViewById(R.id.edPassword);
        btn2 = (Button) findViewById(R.id.btnReg);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usName.getText().toString().equals("admin") && passwod.getText().toString().equals("admin123")) {
                    openActivity2();
                } else {
                    usName.setError("plese enter valid  user name or password");
                    usName.setText("");
                    passwod.setError("plese enter valid  user name or password");
                    passwod.setText("");
                }
            }
        });
    }


    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
