package com.example.bankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button transfer_money;
TextView name,email;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transfer_money=findViewById(R.id.transfer_money);
        name=findViewById(R.id.name);
        email=findViewById(R.id.textView10);
        imageView=findViewById(R.id.imageView6);
        Intent i=getIntent();
        String name1=i.getStringExtra("name");
        String email1=i.getStringExtra("email");
        String balance2=i.getStringExtra("balance");
        Log.d("balance2",balance2);
        int image1=Integer.parseInt(i.getStringExtra("image"));

        name.setText(name1);
        email.setText(email1);
        imageView.setImageResource(image1);
        transfer_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ipass=new Intent(getApplicationContext(),TransferList.class);
                ipass.putExtra("name",name1);
                ipass.putExtra("Sbalance",balance2);
                startActivity(ipass);
            }
        });
    }
}