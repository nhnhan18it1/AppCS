package com.nhandz.flrv_ch.Adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.google.gson.Gson;
import com.nhandz.flrv_ch.DT.*;
import com.nhandz.flrv_ch.HomeActivity;
import com.nhandz.flrv_ch.MainActivity;
import com.nhandz.flrv_ch.R;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.beardedhen.androidbootstrap.*;

public class adapter_home_news extends RecyclerView.Adapter<adapter_home_news.ViewHolder> {

    ArrayList<news> data_news;
    Context context;
    DrawerLayout drawerLayout;
    SendIDBV sendIDBV;

    public adapter_home_news(ArrayList<news> data_news, Context context, DrawerLayout drawerLayout) {
        this.data_news = data_news;
        this.context = context;
        this.drawerLayout = drawerLayout;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.home_item_news,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        int maxs= holder.ctntt.getWidth();
        holder.txtname.setText(String.valueOf(data_news.get(position).getName()));
        GlideUrl url1=new GlideUrl(MainActivity.serverImg +""+data_news.get(position).getImg(),
                new LazyHeaders
                        .Builder()
                        .addHeader("User-Agent",MainActivity.User_Agent)
                        .addHeader("Cookie",MainActivity.cookies)
                        .addHeader("XSRF-TOKEN","eyJpdiI6InNYRzRBSW9acWRJSW1sd0hDbUQzaWc9PSIsInZhbHVlIjoiRzlUVW5vQ293ZGJOQ2ZIVjdBRVlLZDRHbFBmaXk1MzcwVDdhaDJ2aEpBb09cL3NyRUR1VVMzKytKdkYxRVdHc2UiLCJtYWMiOiI4ZmExOWQ4ZWUwNWEwZjUyNDg4ZGU5MDEzZDQzYWY2NWYxN2Y4NGQ5NmY4ZWI5YWVjMTVhZGI2Nzk0ODM0YWEwIn0%3D")
                        .build()
                );
        Glide
                .with(context.getApplicationContext())
                .load(url1)
                .timeout(30000)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.drawable.logo)
                .into(holder.Imgcontent);
        holder.txtContent.setText(data_news.get(position).getContent());

        GlideUrl url2=new GlideUrl(MainActivity.serverImg +""+data_news.get(position).getAvt(),
                new LazyHeaders.Builder()
                        .addHeader("User-Agent",MainActivity.User_Agent)
                        .addHeader("Cookie",MainActivity.cookies)
                        .build()
        );
        Glide
                .with(context.getApplicationContext())
                .load(url2)
                .timeout(30000)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.drawable.logo)
                .into(holder.Imgavt);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, data_news.get(position).getID(), Toast.LENGTH_SHORT).show();
                Log.e("homeadt", "onClick: "+data_news.get(position).getID());
            }
        });
        holder.txtTime.setText("1 gio trước");


        holder.ctnshare.setWeightSum(maxs/3);

        holder.ctncmt.setWeightSum(maxs/3);

        holder.txtclike.setText(String.valueOf(data_news.get(position).getCLike())+" người đã thích");
        //Log.e("time", "onBindViewHolder: "+data_news.get(position).getCreated_at() );
        new apicmt(holder.listcmt,holder.txtccmt).execute(String.valueOf(data_news.get(position).getIDBV()));


        holder.ctnlike.setWeightSum(maxs/3);
        holder.iconlike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("letclick", "onClick: like" );

                new postlike(String.valueOf(data_news.get(position).getIDBV()),String.valueOf(MainActivity.OnAccount.getID()),holder.iconlike,holder.txtclike).execute();
                //holder.iconlike.setTextColor(com.beardedhen.androidbootstrap.R.color.bootstrap_brand_primary);
                //holder.txtlike.setTextColor(com.beardedhen.androidbootstrap.R.color.bootstrap_brand_primary);

            }
        });
        holder.iconcmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.CENTER);
                sendIDBV=new HomeActivity();
                sendIDBV.GetID(String.valueOf(data_news.get(position).getIDBV()));
            }
        });

    }

    public void loadlikecount(final TextView txtcl, final int IDBV){
        OkHttpClient okHttpClient= new OkHttpClient.Builder()
                .build();


    }

    public class loadimgG extends AsyncTask<Void,Void,GlideUrl>{

        private Context context;
        private ImageView imageView;
        private String url;

        public loadimgG(Context context, ImageView imageView, String url) {
            this.context = context;
            this.imageView = imageView;
            this.url = url;
        }

        @Override
        protected GlideUrl doInBackground(Void... voids) {
            GlideUrl url2=new GlideUrl(url,
                    new LazyHeaders.Builder()
                            .addHeader("User-Agent",MainActivity.User_Agent)
                            .addHeader("Cookie",MainActivity.cookies)
                            .build()
            );
            return url2;
        }

        @Override
        protected void onPostExecute(GlideUrl glideUrl) {
            super.onPostExecute(glideUrl);
            Glide
                    .with(context)
                    .load(glideUrl)
                    .timeout(30000)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .error(R.drawable.logo)
                    .override(450,300)
                    .into(imageView);

        }
    }

    public class postlike extends AsyncTask<Void,Void,String>{
        private String IDBV;
        private String IDNL;
        private BootstrapButton txticon;
        private TextView txtclike;

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();

        public postlike(String IDBV, String IDNL, BootstrapButton txticon,TextView cl) {
            this.IDBV = IDBV;
            this.IDNL = IDNL;
            this.txticon=txticon;
            this.txtclike=cl;
            //this.txtTl = txtTl;
        }



        @Override
        protected String doInBackground(Void... voids) {
            RequestBody requestBody=new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("IDBV",String.valueOf(IDBV))
                    .addFormDataPart("IDND",String.valueOf(IDNL))
                    .build();
            Request request=new Request.Builder()
                    .addHeader("User-Agent",MainActivity.User_Agent)
                    .addHeader("Cookie", MainActivity.cookies)
                    .url(MainActivity.server+"/api/postlike")
                    .post(requestBody)
                    .build();
            try {
                Response response=okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            boolean check=Boolean.parseBoolean(s);
            s=s.trim();
            Log.e("postlike", "onPostExecute: "+s );
            if (s.equals("true")){
                txticon.setShowOutline(false);
            }
            else {
                txticon.setShowOutline(true);
//                txticon.setTextColor(R.color.bootstrap_gray);
//                //txtTl.setTextColor(R.color.bootstrap_gray);
//                txticon.setBackgroundColor(R.color.bootstrap_brand_success);
            }
            new loadclike(IDBV,txtclike).execute();

        }
    }

    public class loadclike extends AsyncTask<Void,Void,String>{

        private String IDBV;
        private TextView txtclike;
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();

        public loadclike(String IDBV, TextView txtclike) {
            this.IDBV = IDBV;
            this.txtclike = txtclike;
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestBody requestBody=new MultipartBody.Builder()
                    .addFormDataPart("IDBV",String.valueOf(IDBV))
                    .build();
            Request request=new Request.Builder()
                    .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240 ")
                    .addHeader("Cookie", MainActivity.cookies)
                    .url(MainActivity.server+"/api/loadclike")
                    .post(requestBody)
                    .build();
            try {
                Response response=okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "0";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("réponlike", "onPostExecute: "+s );
            txtclike.setText(s+" người đã thích");
        }
    }

    public class sendcmt extends AsyncTask<Void,Void,String>{

        private String IDBV;
        private String IDNBL;
        private String Content;

        public sendcmt(String IDBV, String IDNBL, String content) {
            this.IDBV = IDBV;
            this.IDNBL = IDNBL;
            Content = content;
        }

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();


        @Override
        protected String doInBackground(Void... voids) {
            RequestBody requestBody=new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("Content",Content )
                    .addFormDataPart("IDNBL",IDNBL)
                    .addFormDataPart("IDBV",IDBV)
                    .build();
            Request request
                    =new Request.Builder()
                    .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240 ")
                    .addHeader("Cookie", MainActivity.cookies)
                    .url(MainActivity.server+"/api/putcomment")
                    .post(requestBody)
                    .build();
            try {
                Response response=okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("putcmt", "onPostExecute: "+s );
        }
    }

    public void getcmt(int IDBV){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();
        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("IDBV",String.valueOf(IDBV))
                .build();
        Request request=new Request.Builder()
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240 ")
                .addHeader("Cookie", MainActivity.cookies)
                .url(MainActivity.server+"/api/getcmt")
                .post(requestBody)
                .build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            Gson gson=new Gson();
            comments[] comments=gson.fromJson(response.body().string(), com.nhandz.flrv_ch.DT.comments[].class);
            Log.e("adt_cmt", "getcmt: "+comments.length );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class apicmt extends AsyncTask<String,Void,String>{

        private ArrayList<comments> lcmt=new ArrayList<>();
        private RecyclerView rcy;
        private TextView txtcCmt;

        public apicmt(RecyclerView rcy,TextView txtc) {
            this.rcy = rcy;
            this.txtcCmt=txtc;
        }

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();
        @Override
        protected String doInBackground(String... strings) {
            RequestBody requestBody=new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("IDBV",strings[0])
                    .build();
            Request request=new Request.Builder()
                    .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240 ")
                    .addHeader("Cookie", MainActivity.cookies)
                    .url(MainActivity.server+"/api/getcmt")
                    .post(requestBody)
                    .build();
            try {
                Response response=okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson=new Gson();
            comments[] comments=gson.fromJson(s, comments[].class);
            txtcCmt.setText(String.valueOf(comments.length)+" bình luận");
            for (int i=0;i<comments.length;i++){
                lcmt.add(comments[i]);
            }
            //adapter_comment adapter_comment=new adapter_comment(lcmt,context.getApplicationContext());
            //rcy.setAdapter(adapter_comment);
            Log.e("adt_cmt", "getcmt: "+comments.length );
        }
    }

    @Override
    public int getItemCount() {
        return data_news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtname;
        de.hdodenhof.circleimageview.CircleImageView Imgavt;
        ImageView Imgcontent;
        TextView txtContent;
        ConstraintLayout linearLayout;
        RecyclerView listcmt;
        TextView txtTime;
        TextView txtclike;
        TextView txtccmt;
        BootstrapEditText txtinputcmt;
        BootstrapButton btnsend;
        LinearLayout ctnlike;
        BootstrapButton iconlike;
        LinearLayout ctntt;
        BootstrapButton iconcmt;
        BootstrapButton iconshare;
        LinearLayout ctncmt;
        LinearLayout ctnshare;
        DrawerLayout drawerLayout;
        //TextView txtlike;
        //de.hdodenhof.circleimageview.CircleImageView cimgT;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.home_adt_header);
            Imgavt=itemView.findViewById(R.id.cmt_avt);
            Imgcontent=itemView.findViewById(R.id.home_adt_imgcontent);
            txtContent=itemView.findViewById(R.id.home_adt_txtcontent);
            linearLayout=itemView.findViewById(R.id.home_adt_head);
            //cimgT=itemView.findViewById(R.id.home_adt_imgt);

            txtTime=itemView.findViewById(R.id.home_adt_time);
            txtclike=itemView.findViewById(R.id.cmt_clike);
            txtccmt=itemView.findViewById(R.id.cmt_ccmt);

            ctnlike=itemView.findViewById(R.id.home_adt_ctnlike);
            iconlike=itemView.findViewById(R.id.home_adt_iconlike);
            ctntt=itemView.findViewById(R.id.adt_news_ctntt);
            iconcmt=itemView.findViewById(R.id.home_adt_iconcmt);
            iconshare=itemView.findViewById(R.id.home_adt_iconshare);
            ctncmt=itemView.findViewById(R.id.home_adt_ctncmt);
            ctnshare=itemView.findViewById(R.id.home_adt_ctnshare);

        }
    }
}
