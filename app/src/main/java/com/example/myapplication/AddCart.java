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

public class AddCart extends AppCompatActivity {

    private EditText pizzcardNumber;
    private EditText pizzexperid;
    private Button pay;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);

        pizzcardNumber = (EditText) findViewById(R.id.edCard);
        pizzexperid = (EditText) findViewById(R.id.edExp);
        pay = (Button) findViewById(R.id.btnpay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardNumber = pizzcardNumber.getText().toString();
                String experid = pizzexperid.getText().toString();
                String URL = "http://192.168.1.129:8080/demo/adduser  cardNumber=" + cardNumber + "&experid=" + experid;

                RequestQueue queue = (RequestQueue) Volley.newRequestQueue(AddCart.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new AddCart.HTTPResponseListner(), new AddCart.HTTPErrorListner());
                queue.add(stringRequest);
            }
        });


    }


    class HTTPResponseListner implements Response.Listener<String> {

        @Override
        public void onResponse(String response) {
            result.setText("Response" + response);
        }
    }

    class HTTPErrorListner implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            result.setText(error.getMessage());
        }
    }
}
