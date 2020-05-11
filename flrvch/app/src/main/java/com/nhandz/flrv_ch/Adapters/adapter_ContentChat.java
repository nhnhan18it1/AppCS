package com.nhandz.flrv_ch.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.beardedhen.androidbootstrap.*;
import com.nhandz.flrv_ch.MainActivity;
import com.nhandz.flrv_ch.R;
import com.nhandz.flrv_ch.DT.*;

import java.util.ArrayList;

public class adapter_ContentChat extends RecyclerView.Adapter<adapter_ContentChat.ViewHolder> {
    ArrayList<Chats> listChats;
    Context context;

    public adapter_ContentChat(ArrayList<Chats> listChats, Context context) {
        this.listChats = listChats;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.contentchat_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (listChats.get(position).getIDsend().equals(String.valueOf(MainActivity.OnAccount.getID()))){
            holder.bootstrapLabel.setRight(25);
            holder.bootstrapLabel.setLeft(0);
            holder.Container.setPadding(0,0,25,0);
        }
        else {
            holder.bootstrapLabel.setRight(0);
            holder.bootstrapLabel.setLeft(25);
            holder.Container.setPadding(25,0,0,0);

        }
    }



    @Override
    public int getItemCount() {
        return listChats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        BootstrapLabel bootstrapLabel;
        LinearLayout Container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bootstrapLabel=itemView.findViewById(R.id.adt_chat_lblCt);
            Container=itemView.findViewById(R.id.adt_chat_container);
        }
    }
}
