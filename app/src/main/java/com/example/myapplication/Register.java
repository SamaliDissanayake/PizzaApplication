package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Register extends AppCompatActivity {

    private EditText fullName;
    private EditText userName;
    private EditText pssword;
    private Button submit;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        fullName = (EditText) findViewById(R.id.edName);
        userName = (EditText) findViewById(R.id.edUseName);
        pssword = (EditText) findViewById(R.id.edPassword);

        result = (TextView) findViewById(R.id.txtResult);

        submit = (Button) findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fullName.getText().toString();
                String usName = userName.getText().toString();
                String password = pssword.getText().toString();

                String URL = "http://192.168.1.129:8080/demo/adduser?name="+name +"&usName="+usName+"&password="+password;

                RequestQueue queue = (RequestQueue) Volley.newRequestQueue(Register.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new HTTPResponseListner(), new HTTPErrorListner());
                queue.add(stringRequest);

                fullName.setText("");
                userName.setText("");
                pssword.setText("");


            }
        });


    }


    class HTTPResponseListner implements Response.Listener<String> {

        @Override
        public void onResponse(String response) {
            result.setText("Success" + response);
        }
    }

    class HTTPErrorListner implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            result.setText(error.getMessage());
        }
    }


}

