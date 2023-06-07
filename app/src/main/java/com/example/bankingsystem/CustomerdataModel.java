package com.example.bankingsystem;

public class CustomerdataModel {
    int id,image;
    String name,email,amount;

    public CustomerdataModel(int id, String name, String email, String amount,int image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.amount = amount;
        this.image=image;
    }

    public CustomerdataModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
