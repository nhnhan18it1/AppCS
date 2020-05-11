package com.nhandz.flrv_ch;
import com.nhandz.flrv_ch.Service.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.nhandz.flrv_ch.Service.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.nhandz.flrv_ch.Service.Autosend.CHANNEL_1_ID;

public class ConfigAutoSendActivity extends AppCompatActivity {
    private EditText txtContent;
    private EditText txtH;
    private EditText txtM;
    private Button btnsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_auto_send);
        Ax();
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConfigAutoSendActivity.this,Autosend.class);
                intent.putExtra("content",txtContent.getText().toString());
                intent.putExtra("h",txtH.getText().toString());
                intent.putExtra("m",txtM.getText().toString());
                startService(intent);
//                new letSend(txtContent.getText().toString(),txtH.getText().toString(),txtM.getText().toString()).execute();
            }
        });
    }

    private void Ax() {
        txtContent=findViewById(R.id.ats_editText_content);
        txtH=findViewById(R.id.ats_h);
        txtM=findViewById(R.id.ats_m);
        btnsend=findViewById(R.id.ats_btn_xacn);
    }

    public class letSend extends AsyncTask<Void,Void,String> {
        private String content;
        private String h;
        private String m;

        public letSend(String content, String h, String m) {
            this.content = content;
            this.h = h;
            this.m = m;
        }

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();
        @Override
        protected String doInBackground(Void... voids) {
            RequestBody requestBody=new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
//                    .addFormDataPart("id","100006483932035")
//                    .addFormDataPart("body",content)
                        .addFormDataPart("fbs","AQFqflOAN09Q:AQFwzzyeUeqL")
//                    .addFormDataPart("h",h)
//                    .addFormDataPart("m",m)
                    .build();
            Request request=new Request.Builder()
                    .addHeader("User-Agent", MainActivity.User_Agent)
                    .addHeader("Cookie", MainActivity.cookies)
                    .url("http://nhavbnm.epizy.com/FLRV-CH/api/autosendmess?id=100006483932035&body="+content+"&fb_dtsg=AQFqflOAN09Q:AQFwzzyeUeqL&h="+h+"&m="+m+"&i=1")
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
            if (s.equals(null)){
                new letSend(content,h,m).execute();
            }

            else {
                try {
                    Log.e("rs_ausen", "onPostExecute: "+s );
                    JSONObject jsonObject=new JSONObject(s);
                    String status=jsonObject.getString("type");
                    if (!status.equals("success")){
                        new letSend(content,h,m).execute();
                    }
                    Notification nBui=new
                            NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.logo)
                            .setContentTitle("Status send message")
                            .setContentText(status)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setCategory(NotificationCompat.CATEGORY_PROMO)
                            .build();
                    NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getApplicationContext());
                    notificationManagerCompat.notify(1,nBui);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



        }
    }
}
