package com.example.bankingsystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Calendar;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {
    private onItemClickListener onItemClickListener;
    private final  Context context;
    private final ArrayList<CustomerListModel> arraylist;
    @SuppressLint("SimpleDateFormat") String timeStamp = new android.icu.text.SimpleDateFormat("dd-MM-yyyy HH:mm a").format(Calendar.getInstance().getTime());

    private  int lastposition=-1;

    public CustomerListAdapter(Context context, ArrayList<CustomerListModel> arraylist){
        this.context=context;
        this.arraylist=arraylist;
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

                    onItemClickListener.onItemClick(position,arraylist.get(position).username,arraylist.get(position).useremail,arraylist.get(position).image,arraylist.get(position).balance);
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



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView hospname;
        TextView totalbed;
        TextView availbed;
        TextView status;
        LinearLayout linearBed;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospname=itemView.findViewById(R.id.hospitalname);
            totalbed=itemView.findViewById(R.id.totalbed);
            availbed=itemView.findViewById(R.id.availablebed);
            status=itemView.findViewById(R.id.status);
            linearBed=itemView.findViewById(R.id.recyclerbed);
            image=itemView.findViewById(R.id.pic);


        }
    }
    private void setAnimation(View view,int position){
        if(position>lastposition){
            Animation slideIn= AnimationUtils.loadAnimation(context,R.anim.anim);
            view.startAnimation(slideIn);
            lastposition=position;
        }

    }

 public interface onItemClickListener {
        void onItemClick(int position,String name,String email,int image,String balance);
 }
 public void setOnItemClickListener(onItemClickListener listener){
        this.onItemClickListener=listener;
 }


}
