package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PayMethod extends AppCompatActivity {
   Button cash,card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_method);

        cash=findViewById(R.id.btncash);
        card=findViewById(R.id.btncard);
      card.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              cardpay();

          }
      });

    }
    public void cardpay(){
        Intent card=new Intent(this,CardPayment.class);
        startActivity(card);
    }

}
