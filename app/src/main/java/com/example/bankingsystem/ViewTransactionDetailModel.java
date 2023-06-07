package com.example.bankingsystem;

public class ViewTransactionDetailModel {
    int key_id;
    String sender,receiver,credit;

    public ViewTransactionDetailModel(int key_id, String sender, String receiver, String credit) {
        this.key_id = key_id;
        this.sender = sender;
        this.receiver = receiver;
        this.credit = credit;
    }

    public ViewTransactionDetailModel() {
    }

    public int getKey_id() {
        return key_id;
    }

    public void setKey_id(int key_id) {
        this.key_id = key_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
