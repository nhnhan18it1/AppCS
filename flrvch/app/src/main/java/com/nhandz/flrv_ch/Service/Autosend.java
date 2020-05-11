package com.nhandz.flrv_ch.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.nhandz.flrv_ch.ConfigAutoSendActivity;
import com.nhandz.flrv_ch.MainActivity;
import com.nhandz.flrv_ch.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Autosend extends Service {
    public static final String CHANNEL_1_ID="Channel 1";
    public static final String CHANNEL_2_ID="Channel 2";
    String Content;
    String h;
    String m;

    //http://nhavbnm.epizy.com/FLRV-CH/api/autosendmess?id=100006483932035&body=%20xxx11%20&fb_dtsg=AQFqflOAN09Q:AQFwzzyeUeqL&h=8&m=55&i=1
    public Autosend() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            Content= intent.getStringExtra("content");
            h=intent.getStringExtra("h");
            m=intent.getStringExtra("m");
            new letSend(Content,h,m).execute();
            Notification nBui = new
                    NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("Status send message")
                    .setContentText("on run")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setCategory(NotificationCompat.CATEGORY_PROMO)
                    .build();
//            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
//            notificationManagerCompat.notify(1, nBui);
            startForeground(133,nBui);
            return Service.START_STICKY;
        }catch (Exception e){
            e.printStackTrace();
            return Service.START_STICKY;
        }


        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel1.setDescription("This is channel 2");


            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
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
            try {
                if (s.equals(null)) {
                    new letSend(content, h, m).execute();
                } else {
                    try {
                        Log.e("rs_ausen", "onPostExecute: " + s);
                        JSONObject jsonObject = new JSONObject(s);
                        String status = jsonObject.getString("type");
                        if (!status.equals("success")) {
                            new letSend(content, h, m).execute();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
                new letSend(content, h, m).execute();
            }



        }}
}
