package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import javax.sql.StatementEvent;

public class ActivityDetails1 extends AppCompatActivity {

    CardView small;

    TextView pizzaname, pizzaprice, pizzadescription, allprice1, add;
    TextView result;

    ImageView pizzaimage;

    EditText quentity;

    Button Buy, cansal, set1, set2;
    Button set;

    String Pizzaname1;
    String PizzaDescription1;
    String Imageurl;
    Double Allprice;

    int i = 0;

    int qty = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);

        pizzaname = findViewById(R.id.name);

        pizzadescription = findViewById(R.id.description);

        pizzaprice = findViewById(R.id.price);

        allprice1 = findViewById(R.id.allprice);
        add = findViewById(R.id.txtaaa);
        result = findViewById(R.id.txtshow);


        pizzaimage = findViewById(R.id.image);


        Buy = findViewById(R.id.addToCart);
        set1 = findViewById(R.id.btnset1);
        set2 = findViewById(R.id.btnset2);

        cansal = findViewById(R.id.btnCansal);


        Intent intent = getIntent();
        final String imgurl = intent.getStringExtra("IMG");
        final Double pizzaprice1 = intent.getDoubleExtra("PRICE", 0.00);
        final String pizzaname1 = intent.getStringExtra("NAME");
        final String pizzadescription1 = intent.getStringExtra("DETAILS");


        Imageurl = imgurl;
        PizzaDescription1 = pizzadescription1;
        Pizzaname1 = pizzaname1;



        Glide.with(ActivityDetails1.this).load(imgurl).into(pizzaimage);
        pizzaname.setText(pizzaname1);
        pizzadescription.setText(pizzadescription1);
        pizzaprice.setText("Rs." + pizzaprice1);
        allprice1.setText("Rs. " + pizzaprice1);

        Allprice = pizzaprice1;

        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityDetails1.this, "Your order is placed Successfully", Toast.LENGTH_SHORT).show();

                String name = pizzaname.getText().toString();
                String description = pizzadescription.getText().toString();
                String quantity = add.getText().toString();
                String price = allprice1.getText().toString();


                String URL = "http://192.168.1.129:8080/demo/addorder?name=" + name + "&description=" + description + "&quantity=" + qty + "&price=" + price + "";

                RequestQueue queue = (RequestQueue) Volley.newRequestQueue(ActivityDetails1.this);

                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new HTTPResponseListner(), new HTTPErrorListner());
                queue.add(stringRequest);
                addtoCart();


            }
        });

        cansal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityDetails1.this, "Thank you for", Toast.LENGTH_SHORT).show();
                cansacl();
            }
        });


        set2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i < 5) {
                    i++;
                    add.setText(i + "");

                    qty = i;
                    allprice1.setText("" + qty * Allprice);




                } else {
                    Toast.makeText(ActivityDetails1.this, "Maximum is 5!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i > 0) {
                    i--;
                    add.setText(i + "");

                    qty = i;
                    allprice1.setText("" + qty * Allprice);
                } else {
                    Toast.makeText(ActivityDetails1.this, "Minimum is 0!", Toast.LENGTH_SHORT).show();
                }
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

    public void addtoCart() {
        Intent details = new Intent(this, Addcart.class);

        details.putExtra("NAME", Pizzaname1);
        details.putExtra("DETAILS", PizzaDescription1);
        details.putExtra("PRICE", Double.parseDouble(allprice1.getText().toString()));
        details.putExtra("QUANTI",add.getText());

        startActivity(details);
    }

    public void cansacl() {
        Intent cansall = new Intent(this, login.class);
        startActivity(cansall);
    }

}



