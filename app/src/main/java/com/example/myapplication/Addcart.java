package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Addcart extends AppCompatActivity {
    Context ctx;
    String Pizzaname1;
    String PizzaDescription1;
    Double Allprice;
    String q1;
    private static String  name1;
    private static String des1;
    private static  String price1;

    Button buyPizza,delete;

    TextView  pizzaname, pizzaprice, pizzadescription,result,qty1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcart);

        pizzaname=findViewById(R.id.txt1);
        pizzadescription=findViewById(R.id.txt2);
        pizzaprice=findViewById(R.id.txt3);
        result=findViewById(R.id.txt4);
        qty1=findViewById(R.id.txt5);

       buyPizza=findViewById(R.id.btnbuy);
       delete=findViewById(R.id.btndelete);


        Bundle bundle= getIntent().getExtras();

        Intent intent = getIntent();
        final Double pizzaprice1 = intent.getDoubleExtra("PRICE", 0.00);
        final String pizzaname1 = intent.getStringExtra("NAME");
        final String pizzadescription1 = intent.getStringExtra("DETAILS");
        final String quantity1=intent.getStringExtra("QUANTI");


        PizzaDescription1=pizzadescription1;
        Pizzaname1=pizzaname1;
        Allprice=pizzaprice1;
        q1=quantity1;


        pizzaname.setText(pizzaname1);
        pizzadescription.setText(pizzadescription1);
        pizzaprice.setText(String.valueOf(pizzaprice1));
        qty1.setText(quantity1);

       buyPizza.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              String name=pizzaname.getText().toString();
              String description=pizzadescription.getText().toString();
              String quan=qty1.getText().toString();
              String price=pizzaprice.getText().toString();

              Toast.makeText(Addcart.this,quan+" "+price,Toast.LENGTH_LONG).show();
                //String name=String.valueOf(pizzaname.getText());
               // String price=String.valueOf(pizzaprice.getText());
                //String quan=String.valueOf(qty1.getText());
                name1=name;
                price1=price;
                des1=quan;

                //String URL="http://192.168.1.129:8080/demo/addorder?name="+"CheeseLovers"+"&quantity="+"2"+"&price="+"4000";
               String URL="http://192.168.1.129:8080/demo/addorder?name="+name+"&quantity="+quan+"&price="+price;

               RequestQueue queue = (RequestQueue) Volley.newRequestQueue(Addcart.this);
               StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new HTTPResponseListner(), new HTTPErrorListner());
               queue.add(stringRequest);
               addTrasaction();


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


    public void  addTrasaction(){
        Intent transaction=new Intent(this,PayMethod.class);
        startActivity(transaction);
    }

}
