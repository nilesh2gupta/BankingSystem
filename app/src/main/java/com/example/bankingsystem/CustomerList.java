package com.example.bankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;


import java.util.ArrayList;
import java.util.Calendar;


public class CustomerList extends AppCompatActivity implements CustomerListAdapter.onItemClickListener {



    CustomerListAdapter adapterBedBook;
    public  ArrayList<CustomerListModel> arraylist=new ArrayList<>();
    ArrayList<CustomerdataModel> model;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        RecyclerView recyclerViewbed;
        recyclerViewbed=findViewById(R.id.recyclerViewbed);

        @SuppressLint("SimpleDateFormat") String timeStamp = new android.icu.text.SimpleDateFormat("dd-MM-yyyy HH:mm a").format(Calendar.getInstance().getTime());

        recyclerViewbed.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,true));

        model=new ArrayList<>();

        try{

            Database db=new Database(getApplicationContext());
            db.openDatabase();
            model=db.fetchData();
            for(int i=0;i<model.size();i++){
                int key_id=model.get(i).id;
                String name=model.get(i).name;
                String email=model.get(i).email;
                String amount=model.get(i).amount;
                int image=model.get(i).image;
                Log.d("Image no",name + image+ key_id);
                arraylist.add(new CustomerListModel(name,email,amount,timeStamp,image));
            }
            /*db.addCustomer("Nilesh ","nilegupta@gmail.com","25000",R.drawable.per1);
            db.addCustomer("Aleena","Aleena@gmail.com","55000",R.drawable.profile);
            db.addCustomer("Prakash","prakash@gmail.com","56000",R.drawable.per2);
            db.addCustomer("Shoial","shoail@gmail.com","32000",R.drawable.per3);
            db.addCustomer("Aditya","aditya@gmail.com","65000",R.drawable.per8);
            db.addCustomer("Sameer","sameer@gmail.com","12000",R.drawable.per5);
            db.addCustomer("Alok","alok@gmail.com","87000",R.drawable.per6);
            db.addCustomer("Vinsela","vinsela@gmail.com","30000",R.drawable.per7);
            db.addCustomer("Rosena","rosena@gmail.com","95000",R.drawable.per4);
            db.addCustomer("Bill Gates","billgate@gmail.com","25000",R.drawable.per9);*/



        }
        catch(Exception e){
            Log.e("Error Message",Log.getStackTraceString(e));
        }

        recyclerViewbed.setHasFixedSize(true);
        adapterBedBook=new CustomerListAdapter(this,arraylist);
        adapterBedBook.setOnItemClickListener(this);
        recyclerViewbed.setAdapter(adapterBedBook);



    }


    @Override
    public void onItemClick(int position,String name,String email,int image,String balance) {
         Intent intent=new Intent(this,MainActivity.class);
         intent.putExtra("name",name);
         intent.putExtra("email",email);
         intent.putExtra("image",Integer.toString(image));
         intent.putExtra("balance",balance);
         startActivity(intent);
    }
}