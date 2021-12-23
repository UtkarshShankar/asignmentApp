package com.utkarsh.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {
    TextView date,time,total,addUnit;
    EditText product,price,unitPrice,quantity;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        save=findViewById(R.id.btnsave);

        date=findViewById(R.id.datetext);
        time=findViewById(R.id.timetext);
        total=findViewById(R.id.total_text);
        addUnit=findViewById(R.id.addUnit);

        product=findViewById(R.id.product_text);
        price=findViewById(R.id.price_text);
        unitPrice=findViewById(R.id.unit_price_text);
        quantity=findViewById(R.id.quantity_text);

        Date currentTime = Calendar.getInstance().getTime();
        
    }
}