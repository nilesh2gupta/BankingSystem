package com.example.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME="BankDatabase";
    private static  final String TABLE_CUSTOMER="CustomersData";
    private static  final String KEY_ID="id";
    private static  final String KEY_NAME="name";
    private static  final String KEY_EMAIL="email";
    private static  final String KEY_AMOUNT="amount";
    private static  final String KEY_IMAGE="image";
    private static  final String ID="id";
    private static  final String TABLE_TRANSACTION="TransactionData";
    private static  final String SENDER="sender";
    private static  final String RECEIVER="receiver";
    private static  final String CREDIT="credit";



    private static final int VERSION=1;
    public Database(Context context) {
        super(context, DATABASE_NAME, null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CUSTOMER + "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME  + " TEXT ," + KEY_EMAIL + " TEXT," + KEY_AMOUNT +" TEXT ,"+ KEY_IMAGE +" INTEGER " + ")");
        db.execSQL("CREATE TABLE " + TABLE_TRANSACTION + "(" + ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + SENDER  + " TEXT," + RECEIVER + " TEXT," + CREDIT +" TEXT " + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CUSTOMER );
           db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TRANSACTION );

           onCreate(db);
    }
    public void addCustomer(String name,String email,String amount,int image){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_EMAIL,email);
        values.put(KEY_AMOUNT,amount);
        values.put(KEY_IMAGE,image);
        db.insert(TABLE_CUSTOMER,null,values);

    }
    public void addTransaction(String sender,String receiver,String credit){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SENDER,sender);
        values.put(RECEIVER,receiver);
        values.put(CREDIT,credit);
        db.insert(TABLE_TRANSACTION,null,values);

    }
    public ArrayList<CustomerdataModel> fetchData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM "+TABLE_CUSTOMER,null);
        ArrayList<CustomerdataModel> arrayList=new ArrayList<>();
        while(cursor.moveToNext()){
            CustomerdataModel model=new CustomerdataModel();
            model.id=cursor.getInt(0);
            model.name=cursor.getString(1);
            model.email=cursor.getString(2);
            model.amount=cursor.getString(3);
            model.image=cursor.getInt(4);
            arrayList.add(model);

        }
        return arrayList;
    }
    public ArrayList<ViewTransactionDetailModel> getTransactionDetail(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM "+TABLE_TRANSACTION,null);
        ArrayList<ViewTransactionDetailModel> arrayList=new ArrayList<>();
        while(cursor.moveToNext()){
           ViewTransactionDetailModel model=new ViewTransactionDetailModel();
            model.key_id=cursor.getInt(0);
            model.sender=cursor.getString(1);
            model.receiver=cursor.getString(2);
            model.credit=cursor.getString(3);
            arrayList.add(model);

        }
        return arrayList;
    }
    public void updateCustomerData(CustomerdataModel model,String amount,int id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_AMOUNT,amount);
        model.id=id;
        sqLiteDatabase.update(TABLE_CUSTOMER,values,KEY_ID +"="+model.id,null);
    }

    public void openDatabase(){
        SQLiteDatabase db=this.getReadableDatabase();
    }
}
