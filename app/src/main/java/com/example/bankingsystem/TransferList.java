package com.example.bankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import java.util.ArrayList;
import java.util.Calendar;


public class TransferList extends AppCompatActivity {



    TransferListAdapter adapterBedBook;
    String name;
    String balance;

    public  ArrayList<CustomerListModel> arraylist=new ArrayList<>();
    public ArrayList<CustomerdataModel> model=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_list);
        RecyclerView recyclerViewbed;
        recyclerViewbed=findViewById(R.id.recyclerViewbed);
        Intent k=getIntent();
        name=k.getStringExtra("name");
        balance=k.getStringExtra("Sbalance");
        Log.d("balance2",balance);
        @SuppressLint("SimpleDateFormat") String timeStamp = new android.icu.text.SimpleDateFormat("dd-MM-yyyy HH:mm a").format(Calendar.getInstance().getTime());

        recyclerViewbed.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,true));
        try{

            Database db=new Database(getApplicationContext());
            db.openDatabase();
            model=db.fetchData();
            for(int i=0;i<model.size();i++){
                String name=model.get(i).name;
                String email=model.get(i).email;
                String amount=model.get(i).amount;
                int image=model.get(i).image;
                arraylist.add(new CustomerListModel(name,email,amount,timeStamp,image));
            }}
        catch(Exception ex){

        }

        recyclerViewbed.setHasFixedSize(true);
        adapterBedBook=new TransferListAdapter(this,arraylist,name,balance);
        recyclerViewbed.setAdapter(adapterBedBook);



    }

}



