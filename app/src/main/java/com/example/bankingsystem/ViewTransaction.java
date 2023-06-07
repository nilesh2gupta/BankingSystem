package com.example.bankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewTransaction extends AppCompatActivity {

    TransactionAdapter adapter;
    Database db;
     ArrayList<TransactionDataModel> dataList=new ArrayList<>();
     ArrayList<ViewTransactionDetailModel> model=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);
        RecyclerView recyclerView;
        recyclerView=findViewById(R.id.recyclerView);
        db=new Database(ViewTransaction.this);
        db.openDatabase();

        @SuppressLint("SimpleDateFormat") String timeStamp = new android.icu.text.SimpleDateFormat("dd-MM-yyyy HH:mm a").format(Calendar.getInstance().getTime());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,true));
        try{
            model=db.getTransactionDetail();
            for(int i=0;i<model.size();i++){
                dataList.add(new TransactionDataModel(model.get(i).sender,model.get(i).receiver,model.get(i).credit,timeStamp));
            }
        }
        catch (Exception e){

        }


        recyclerView.setHasFixedSize(true);
        adapter=new TransactionAdapter(this,dataList);
        recyclerView.setAdapter(adapter);

    }
}