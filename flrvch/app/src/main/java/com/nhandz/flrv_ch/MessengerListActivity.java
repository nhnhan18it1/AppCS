package com.nhandz.flrv_ch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.beardedhen.androidbootstrap.*;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.google.gson.Gson;
import com.nhandz.flrv_ch.Adapters.adapter_home_news;
import com.nhandz.flrv_ch.Adapters.adapter_listfriend;
import com.nhandz.flrv_ch.DT.*;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.socket.emitter.Emitter;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessengerListActivity extends AppCompatActivity {

    private RecyclerView RvListfriend;
    private BootstrapCircleThumbnail BctAvt;
    private BootstrapEditText BedtSearch;
    private BootstrapButton BbtnSearch;
    private adapter_listfriend adt;
    private ArrayList<listfriend> listfriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_list);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TIÊU ĐỀ ACTIVITY"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
        actionBar.hide(); //Ẩn ActionBar nếu muốn

        initView();
        initListFriend();

        new loadListFriend().execute();
        GlideUrl url2=new GlideUrl(MainActivity.serverImg+""+LoginActivity.Avt,
                new LazyHeaders.Builder()
                        .addHeader("Cookie",MainActivity.cookies)
                        .build()
        );

        Glide.with(getApplicationContext())
                .load(url2)
                .timeout(30000)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.logo)
                .into(BctAvt);
        MainActivity.mSocket.on("server-send-listus", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                ArrayList<NodeFriend> nodeFriends=new ArrayList<>();
                JSONArray jsonArray=(JSONArray)args[0];
                try {
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=(JSONObject) jsonArray.get(i);
                    nodeFriends.add(new NodeFriend(
                            jsonObject.getString("IDND"),
                            jsonObject.getString("Name"),
                            jsonObject.getString("Avt"),
                            jsonObject.getString("IDN")
                            ));


                }
                adt.updateOnline(nodeFriends);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
                Log.e("l-lít-2", "call: "+nodeFriends.size() );

            }
        });
    }

    private void initView() {
        BctAvt=findViewById(R.id.lisf_AvtOnAcc);
    }

    private void initListFriend() {
        RvListfriend=findViewById(R.id.list_friend);
        listfriends=new ArrayList<>();
        RvListfriend.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MessengerListActivity.this,LinearLayoutManager.VERTICAL,false);
        RvListfriend.setLayoutManager(linearLayoutManager);
        adt=new adapter_listfriend(getApplicationContext(),listfriends);
        RvListfriend.setAdapter(adt);
        //listfriends.add(new listfriend("1","2","ád","ád"));

    }
    public class loadListFriend extends AsyncTask<Void,Void,String>{

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();

        @Override
        protected String doInBackground(Void... voids) {
            RequestBody requestBody=new MultipartBody.Builder()
                    .addFormDataPart("ID",String.valueOf(MainActivity.OnAccount.getID()))
                    .setType(MultipartBody.FORM)
                    .build();
            Log.d("ìnorid", "doInBackground: "+MainActivity.OnAccount.getID());
            Request request=new Request.Builder()
                    .addHeader("User-Agent",MainActivity.User_Agent)
                    .addHeader("Cookie",MainActivity.cookies)
                    .url(MainActivity.server+"/api/loadlistfr")
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
            Gson gson=new Gson();
            listfriend[] listfriendrs=gson.fromJson(s,listfriend[].class);
            if (listfriendrs.length!=0){
                for (int i=0;i<listfriendrs.length;i++){
                    listfriends.add(listfriendrs[i]);
                }
            }
            adt.notifyDataSetChanged();
            new GetListFriendFromNode().execute();
            Log.e("lisf", "onPostExecute: "+s );

        }
    }
    public class GetListFriendFromNode extends  AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            MainActivity.mSocket.emit("mobile-require-list");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adt.notifyDataSetChanged();
        }
    }

}
