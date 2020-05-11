package com.nhandz.flrv_ch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nhandz.flrv_ch.DT.*;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.beardedhen.androidbootstrap.*;
import com.nhandz.flrv_ch.Adapters.*;

public class ChatActivity extends AppCompatActivity {
    public account AccOnChat;
    private TextView txtName;
    private BootstrapCircleThumbnail imgAvt;
    private BootstrapButton btnBack;
    private BootstrapButton btnCall;
    private BootstrapButton btnVideoCall;
    private BootstrapButton btnInfor;
    private BootstrapEditText txtMessContent;
    private BootstrapButton btnSend;
    private RecyclerView listContentMess;
    private adapter_ContentChat adt;
    private ArrayList<Chats> listChats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TIÊU ĐỀ ACTIVITY"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
        actionBar.hide(); //Ẩn ActionBar nếu muốn
        Intent intent=getIntent();
        listfriend lf = (listfriend) intent.getSerializableExtra("dataUserForChat");
        AX();
        new GetInforForChat(lf).execute();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initChat();
    }

    public void AX(){
        txtName=findViewById(R.id.chatAc_Name);
        imgAvt=findViewById(R.id.chatAc_Avt);
        btnBack=findViewById(R.id.chatAc_btnBack);
        btnCall=findViewById(R.id.chatAc_btncall);
        btnVideoCall=findViewById(R.id.chatAc_btnVideocall);
        btnInfor=findViewById(R.id.chatAc_btnInfor);
        txtMessContent=findViewById(R.id.chatAc_inputMess);
        btnSend=findViewById(R.id.chatAc_btnSendMess);
        listContentMess=findViewById(R.id.chatAc_listMess);
    }

    public void initChat(){
        listContentMess.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ChatActivity.this,
                LinearLayoutManager.VERTICAL,
                false);
        listContentMess.setLayoutManager(linearLayoutManager);
        listChats=new ArrayList<>();
        listChats.add(new Chats("1","1","2","asdasd","asdas","saad","asdsa"));
        listChats.add(new Chats("12","2","1","asdasd","asdas","saad","asdsa"));
        adt=new adapter_ContentChat(listChats,getApplicationContext());
        listContentMess.setAdapter(adt);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }

    public class GetInforForChat extends AsyncTask<Void,Void,String>{
        private listfriend lf;
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();

        public GetInforForChat(listfriend lf) {
            this.lf = lf;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String ID="";
            if (lf.getID1().equals(String.valueOf(MainActivity.OnAccount.getID()))){
                ID=lf.getID2();
            }
            else {
                ID=lf.getID1();
            }
            RequestBody requestBody=new MultipartBody.Builder()
                    .addFormDataPart("ID",ID)
                    .setType(MultipartBody.FORM)
                    .build();
            Request request=new Request.Builder()
                    .addHeader("User-Agent", MainActivity.User_Agent)
                    .addHeader("Cookie", MainActivity.cookies)
                    .url(MainActivity.server+"/api/loadinforuser1")
                    .post(requestBody)
                    .build();
            try {
                Response response=okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.equals(null)){
                Log.e("loadifUs", "onPostExecute: "+s );
                Gson gson=new Gson();
                account[] acc=gson.fromJson(s,account[].class);
                AccOnChat=acc[0];
                txtName.setText(acc[0].getName());
                Glide.with(getApplicationContext())
                        .load(MainActivity.serverImg+""+acc[0].getAvt())
                        .error(R.drawable.icons_search)
                        .into(imgAvt);
            }
        }
    }
}
