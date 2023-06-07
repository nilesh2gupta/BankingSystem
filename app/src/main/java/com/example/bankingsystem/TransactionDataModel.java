package com.example.bankingsystem;

public class TransactionDataModel {
    String sendername,receivername,credit,time;

    public TransactionDataModel(String sendername, String receivername, String credit, String time) {
        this.sendername = sendername;
        this.receivername = receivername;
        this.credit = credit;
        this.time = time;
    }

    public TransactionDataModel() {
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
