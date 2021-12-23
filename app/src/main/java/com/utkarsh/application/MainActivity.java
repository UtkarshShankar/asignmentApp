package com.utkarsh.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button purchase,sell;
    public List<Model>modalList;
    public Adapter adapter;
    TextView totalPurchase,totalSell;
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);

    private static String JSON_URL="https://82b4-2405-201-3017-1073-a1a4-d584-6ee2-f5aa.ngrok.io/api/getTransactionList";
    private static String JSON_URL_Total="https://82b4-2405-201-3017-1073-a1a4-d584-6ee2-f5aa.ngrok.io/api/GetTotalPurchaseAndSell";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.RegisterRecycler);
        totalPurchase=findViewById(R.id.Val_purchase);
        totalSell=findViewById(R.id.Val_sell);
        modalList=new ArrayList<Model>();
        GetListTxn();
        GetTotal();
        //recyclerView.setLayoutManager(linearLayoutManager);
        //adapter=new Adapter(modalList ,getApplicationContext());
        //recyclerView.setAdapter(adapter);
        purchase=findViewById(R.id.btnPurchase);
        sell=findViewById(R.id.btnsell);



        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    private void GetTotal() {
        RequestQueue queue=Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest=new JsonObjectRequest(Request.Method.GET, JSON_URL_Total, null, response -> {
            try {
                //JSONObject object = new JSONObject("response");
                JSONArray array = response.getJSONArray("response");

                    //Log.d("tag", "Branch Id -: " + array.getInt(i));
                    totalSell.setText(Integer.toString(array.getInt(0)));
                    totalPurchase.setText(Integer.toString(array.getInt(1)));



            } catch (JSONException e) {
                e.printStackTrace();

        }
    },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("tag","on Error Response "+error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void GetListTxn() {
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, JSON_URL, null, response -> {

            try { JSONArray res =response.getJSONArray("response");
                Toast.makeText(getApplicationContext(), "found", Toast.LENGTH_LONG).show();
            for (int i = 0; i < res.length(); i++) {
                JSONObject jsonObject=res.getJSONObject(i);
                    Model model = new Model();
                    model.setDate(jsonObject.getString("date"));
                    model.setProduct(jsonObject.getString("name"));
                    model.setTime(jsonObject.getString("time"));
                    model.setQuantity(jsonObject.getString("quantity")+" "+jsonObject.getString("unit"));
                    model.setPurchase_amt(jsonObject.getString("totalPrice"));
                    modalList.add(model);
            }
                recyclerView.setLayoutManager(linearLayoutManager);
                adapter=new Adapter(modalList ,getApplicationContext());
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("tag","on Error Response "+error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);


    }
}