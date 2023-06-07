package com.example.bankingsystem;



import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerListModel extends AppCompatActivity {
    int image;
    String username;

    String useremail;

    String balance;

    String time;



    public CustomerListModel(String hospitalname, String totalbed, String availablebed, String status,int image) {
        this.username = hospitalname;
        this.useremail = totalbed;
        this.balance = availablebed;
        this.time = status;
        this.image=image;
    }




    public CustomerListModel() {
    }

    public String getHospitalname() {
        return username;
    }

    public void setHospitalname(String hospitalname) {
        this.username = hospitalname;
    }

    public String getTotalbed() {
        return useremail;
    }

    public void setTotalbed(String totalbed) {
        this.useremail = totalbed;
    }

    public String getAvailablebed() {
        return balance;
    }

    public void setAvailablebed(String availablebed) {
        this.balance = availablebed;
    }

    public String getStatus() {
        return time;
    }

    public void setStatus(String status) {
        this.time = status;
    }
}
