package com.example.bankingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
   private final Context context;
   private  int lastposition=-1;
   private ArrayList<TransactionDataModel> dataList;


    public TransactionAdapter(Context context, ArrayList<TransactionDataModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.transaction_model_design,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder ;

    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
           holder.sendername.setText(dataList.get(position).sendername);
           holder.receivername.setText(dataList.get(position).receivername);
           holder.credit.setText(dataList.get(position).credit);
           holder.time.setText(dataList.get(position).time);
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class  ViewHolder extends RecyclerView.ViewHolder{
        TextView sendername,receivername,credit,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sendername=itemView.findViewById(R.id.sender_name);
            receivername=itemView.findViewById(R.id.receiver_name);
            credit=itemView.findViewById(R.id.amount);
            time=itemView.findViewById(R.id.time);

        }
    }
    private void setAnimation(View view,int position){
        if(position>lastposition){
            Animation slideIn= AnimationUtils.loadAnimation(context,R.anim.anim);
            view.startAnimation(slideIn);
            lastposition=position;
        }

    }
}
