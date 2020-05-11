package com.nhandz.flrv_ch.Adapters;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.beardedhen.androidbootstrap.*;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nhandz.flrv_ch.ChatActivity;
import com.nhandz.flrv_ch.MainActivity;
import com.nhandz.flrv_ch.R;
import com.nhandz.flrv_ch.DT.*;
import com.nhandz.flrv_ch.Service.*;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class adapter_listfriend extends RecyclerView.Adapter<adapter_listfriend.ViewHolder>  {

    private Context context;
    private ArrayList<listfriend> listfriends;

    public adapter_listfriend(Context context, ArrayList<listfriend> listfriends) {
        this.context = context;
        this.listfriends = listfriends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.listfriend_item,parent,false);
        return new  ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (Integer.parseInt(listfriends.get(position).getID1()) == MainActivity.OnAccount.getID())
        {
            new loadinforFR(listfriends.get(position).getID2(),holder.avt,holder.txtname).execute();
        }
        else{
            new loadinforFR(listfriends.get(position).getID1(),holder.avt,holder.txtname).execute();
        }
        holder.txtfirtmess.setText("hello!");
        holder.btnOnline.setShowOutline(listfriends.get(position).isOnline());
        holder.itView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatActivity.class);

                intent.putExtra("dataUserForChat",listfriends.get(position));
                context.startActivity(intent);
        }
        });
    }

    public void updateOnline(ArrayList<NodeFriend> nodeFriends){
        if (nodeFriends.size()==0){
            for (int i=0;i<listfriends.size();i++){
                listfriends.get(i).setOnline(true);
                this.notifyItemChanged(i);
            }
        }
        else {
            for (int j=0;j<nodeFriends.size();j++){
                for (int i=0;i<listfriends.size();i++){
                    if (nodeFriends.get(j).getIDND().equals(listfriends.get(i).getID1())||
                            nodeFriends.get(j).getIDND().equals(listfriends.get(i).getID2())){
                        listfriends.get(i).setOnline(false);
                        this.notifyItemChanged(i);
                        break;
                    }
                }
            }
        }


    }

    @Override
    public int getItemCount() {
        return listfriends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        BootstrapCircleThumbnail avt;
        TextView txtname;
        TextView txtfirtmess;
        BootstrapButton btnmenu;
        BootstrapButton btnOnline;
        View itView1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avt=itemView.findViewById(R.id.listfriend_item_avt);
            txtname=itemView.findViewById(R.id.listfriend_item_name);
            txtfirtmess=itemView.findViewById(R.id.listfriend_item_fistmess);
            btnmenu=itemView.findViewById(R.id.listfriend_item_menumess);
            btnOnline=itemView.findViewById(R.id.adt_listfriend_item_btnonline);
            itView1=itemView;
        }
    }
    public class loadinforFR extends AsyncTask<Void,Void,String>{

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();
        private String ID;
        private BootstrapCircleThumbnail avt;
        private TextView txtname;
        //private TextView txtmess;

        public loadinforFR(String ID, BootstrapCircleThumbnail avt, TextView txtname) {
            this.ID = ID;
            this.avt = avt;
            this.txtname = txtname;
            //this.txtmess = txtmess;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String json="";


            RequestBody requestBody=new MultipartBody.Builder()
                    .addFormDataPart("ID",ID)
                    .setType(MultipartBody.FORM)
                    .build();
            Request request=new Request.Builder()
                    .addHeader("User-Agent", MainActivity.User_Agent)
                    .addHeader("Cookie", MainActivity.cookies)
                    .url(MainActivity.server+"/api/loadinforuser")
                    .post(requestBody)
                    .build();
            try {
                Response response=okHttpClient.newCall(request).execute();
                json=response.body().string();
                Log.e("ìnoracc", "doInBackground: "+json );
                return json;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson=new Gson();
            account[] accounts=gson.fromJson(s,account[].class);
            Glide.with(context.getApplicationContext())
                    .load(MainActivity.serverImg+""+accounts[0].getAvt())
                    .error(R.drawable.logo)
                    .into(avt);
            txtname.setText(accounts[0].getName());
        }
    }
}
