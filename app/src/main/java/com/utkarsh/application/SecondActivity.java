package com.utkarsh.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {
    TextView date,time,total_text,addUnit,toolbar_text;
    EditText product,price,quantity;
    Spinner unitPrice;
    String unit;
    ImageView imageView;
    Button save;
    private static String JSON_POST_URL="https://e4b3-2405-201-3017-1073-a1a4-d584-6ee2-f5aa.ngrok.io/api/createTransaction";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toolbar_text=findViewById(R.id.toolbartext);
        Intent intent =getIntent();
        Bundle b=intent.getExtras();
        if(b!=null){
            String title= (String) b.get("Type");
            toolbar_text.setText(title);
        }

        save=findViewById(R.id.btnsave);

        date=findViewById(R.id.datetext);
        time=findViewById(R.id.timetext);
        total_text=findViewById(R.id.total_text);
        addUnit=findViewById(R.id.addUnit);


        product=findViewById(R.id.product_text);
        price=findViewById(R.id.price_text);
        unitPrice=findViewById(R.id.unit_price_text);
        quantity=findViewById(R.id.quantity_text);

        imageView=findViewById(R.id.backArrow);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.units,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        unitPrice.setAdapter(adapter);
        unitPrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unit=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }) ;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getText().toString().equals("")||price.getText().toString().equals("")||quantity.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Please fill out the feilds first", Toast.LENGTH_SHORT).show();
                else
                    PostData();
            }
        });


    }

    private void PostData() {
        RequestQueue queue= Volley.newRequestQueue(SecondActivity.this);
        StringRequest request=new StringRequest(Request.Method.POST, JSON_POST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(SecondActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SecondActivity.this, "Failed to get Respose "+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("date", date.getText().toString());
                params.put("time", time.getText().toString());
                params.put("name", product.getText().toString());
                params.put("pricePerItem", price.getText().toString());
                params.put("typeOfTransaction",toolbar_text.getText().toString());
                params.put("unit",unit);

                return params;
            }
        };
        queue.add(request);

    }
}