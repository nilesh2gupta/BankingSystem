package com.example.bankingsystem;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Calendar;

public class TransferListAdapter extends RecyclerView.Adapter<TransferListAdapter.ViewHolder> {
    public ArrayList<CustomerdataModel> model=new ArrayList<>();
    private final  Context context;
    Database db;
    MediaPlayer mp;
    TransferList transferList=new TransferList();
    EditText amount;
    TextView username;
    int y=0;
    private String money;
    private final String name;
    private final String senderAmount;

    private final ArrayList<CustomerListModel> arraylist;
    @SuppressLint("SimpleDateFormat") String timeStamp = new android.icu.text.SimpleDateFormat("dd-MM-yyyy HH:mm a").format(Calendar.getInstance().getTime());

    private  int lastposition=-1;

    public TransferListAdapter(Context context, ArrayList<CustomerListModel> arraylist,String name,String senderAmount){
        this.context=context;
        this.arraylist=arraylist;
        this.name=name;
        this.senderAmount=senderAmount;


    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.customer_list_model,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try{
            holder.hospname.setText(arraylist.get(position).username);
            holder.totalbed.setText(arraylist.get(position).useremail);
            holder.availbed.setText(arraylist.get(position).balance);
            holder.status.setText(arraylist.get(position).time);
            holder.image.setImageResource(arraylist.get(position).image);
            holder.linearBed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("Username",transferList.name);
                    Dialog Dpay=new Dialog(context);
                    Dpay.setContentView(R.layout.payment_model);
                    Button pay=Dpay.findViewById(R.id.pay);
                           username=Dpay.findViewById(R.id.user_name);
                           username.setText(arraylist.get(position).username);
                        pay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                AlertDialog.Builder confirm=new AlertDialog.Builder(context);
                                confirm.setTitle("Confirmation");
                                confirm.setIcon(R.drawable.launcher_icon_bank);
                                confirm.setMessage("Are you sure to make payment?");
                                confirm.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mp=new MediaPlayer();
                                        mp.create(context, R.raw.transactionsong);
                                        mp.setLooping(false);
                                        mp.start();
                                        amount=Dpay.findViewById(R.id.amount);
                                        String balance=arraylist.get(position).balance;
                                        int x=Integer.parseInt(balance);
                                        money=amount.getText().toString();
                                        y=Integer.parseInt(money);
                                        int net_balance=x+y;
                                        String Net=Integer.toString(net_balance);
                                        int senderamt=Integer.parseInt(senderAmount);
                                        int senderNetBalance=senderamt-y;
                                        String totalsenderamt=Integer.toString(senderNetBalance);
                                        Log.d("Transaction Detail",totalsenderamt+" "+Net);
                                        int senderid=getIndex(name);
                                        int receiverid=getIndex(arraylist.get(position).username);
                                        Log.d("senderid","Receiverid= "+receiverid+"   Senderid= "+senderid);
                                        updateData(totalsenderamt,senderid);
                                        newEntry(name,arraylist.get(position).username,money);
                                        updateData(Net,receiverid);
                                        arraylist.set(position,new CustomerListModel(arraylist.get(position).username,arraylist.get(position).useremail,Net,timeStamp,arraylist.get(position).image));
                                        notifyItemChanged(position);
                                        Dialog dialog1=new Dialog(context);
                                        dialog1.setContentView(R.layout.custom_dialog_box);
                                        dialog1.show();
                                    }
                                });
                                confirm.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                confirm.show();
                            }
                        });
                    Dpay.show();
                    }


            });

            setAnimation(holder.itemView,position);
        }
        catch (Exception e){

        }


    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView hospname;
        TextView totalbed;
        TextView availbed;
        TextView status;
        ImageView image;
        LinearLayout linearBed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospname=itemView.findViewById(R.id.hospitalname);
            totalbed=itemView.findViewById(R.id.totalbed);
            availbed=itemView.findViewById(R.id.availablebed);
            status=itemView.findViewById(R.id.status);
            image=itemView.findViewById(R.id.pic);
            linearBed=itemView.findViewById(R.id.recyclerbed);


        }
    }
    private void setAnimation(View view,int position){
        if(position>lastposition){
            Animation slideIn= AnimationUtils.loadAnimation(context,R.anim.anim);
            view.startAnimation(slideIn);
            lastposition=position;
        }

    }

  public void updateData(String netbalance,int id){
      db=new Database(context);
      CustomerdataModel v=new CustomerdataModel();
      db.updateCustomerData(v,netbalance,id);

  }
  public int getIndex(String name){
        int id=0;
        db=new Database(context);
      db.openDatabase();
      model=db.fetchData();
      for(int i=0;i<model.size();i++){
         if(model.get(i).name.equals(name)){
             id=model.get(i).id;
         }
      }
      return id;
  }

  public void newEntry(String sender,String receiver,String credit){
        db=new Database(context);
        db.addTransaction(sender,receiver,credit);
  }


}
