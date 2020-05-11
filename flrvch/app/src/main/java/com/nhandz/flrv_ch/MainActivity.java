package com.nhandz.flrv_ch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nhandz.flrv_ch.DT.NodeFriend;
import com.nhandz.flrv_ch.DT.TextviewFont;
import com.nhandz.flrv_ch.DT.account;
import com.nhandz.flrv_ch.Service.Autosend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    public static String server= "http://192.168.61.31:8888/FLRV-CH";
    public static String serverImg="http://192.168.61.31:8888";
    public static String cookies="__test=afd4ff1e1b7f9ade68ae2dc16420678d; expires=Thu, 31-Dec-38 23:55:55 GMT; path=/";
    public static String User_Agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240 ";
    public static String XSRF_TOKEN=""; //"eyJpdiI6InNYRzRBSW9acWRJSW1sd0hDbUQzaWc9PSIsInZhbHVlIjoiRzlUVW5vQ293ZGJOQ2ZIVjdBRVlLZDRHbFBmaXk1MzcwVDdhaDJ2aEpBb09cL3NyRUR1VVMzKytKdkYxRVdHc2UiLCJtYWMiOiI4ZmExOWQ4ZWUwNWEwZjUyNDg4ZGU5MDEzZDQzYWY2NWYxN2Y4NGQ5NmY4ZWI5YWVjMTVhZGI2Nzk0ODM0YWEwIn0%3D";
    public static account OnAccount=null;
    public static String Nodeserver="https://flrv-ch.herokuapp.com/";//"http://192.168.1.2:3000";
    public static Socket mSocket;

    Button btnnext;
    TextviewFont textviewFont;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TIÊU ĐỀ ACTIVITY"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
        actionBar.hide(); //Ẩn ActionBar nếu muốn

        try {
            mSocket= IO.socket(Nodeserver);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.connect();

//        MainActivity.mSocket.on("server-send-listus", new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                ArrayList<NodeFriend> nodeFriends=new ArrayList<>();
//                JSONArray jsonArray=(JSONArray)args[0];
//                try {
//                    for (int i=0;i<jsonArray.length();i++){
//                        JSONObject jsonObject=(JSONObject) jsonArray.get(i);
//                        nodeFriends.add(new NodeFriend(
//                                jsonObject.getString("IDND"),
//                                jsonObject.getString("Name"),
//                                jsonObject.getString("Avt"),
//                                jsonObject.getString("IDN")
//                        ));
//
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                Log.e("l-lít", "call: "+nodeFriends.size() );
//
//            }
//        });

        btnnext=findViewById(R.id.btnNext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        textviewFont=findViewById(R.id.main_ipconfig);
        textviewFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ConfigAutoSendActivity.class);
                startActivity(intent);

            }
        });
        if (OnAccount!=null){
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }

    }
}
