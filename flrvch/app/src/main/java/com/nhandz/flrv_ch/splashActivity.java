package com.nhandz.flrv_ch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.*;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nhandz.flrv_ch.DT.GlideSetup;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class splashActivity extends AppCompatActivity {

    private BootstrapProgressBar progressBar;
    private TextView txtloading;
    private ImageView imgc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TIÊU ĐỀ ACTIVITY"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
        actionBar.hide(); //Ẩn ActionBar nếu muốn
        progressBar=findViewById(R.id.splash_progress);
        txtloading=findViewById(R.id.txtloading);
        imgc=findViewById(R.id.imgcheck);

        Headers headers=new Headers() {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params=new HashMap<String, String>();
                params.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240 ");
                params.put("Cookie", "__test=ae77fc34838aeae52345bf5e7f534f83; expires=Thu, 31-Dec-38 23:55:55 GMT; path=/");
                return params;
            }
        };

        GlideUrl url=new GlideUrl("http://nhavbnm.epizy.com/FLRV-CH/local/public/assets/img/newi/abc0.jpeg",
                new LazyHeaders
                        .Builder()
                        .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240 ")
                        .addHeader("Cookie", "__test=d677cf2235aee45bb06560724468ab8e; expires=Thu, 31-Dec-38 23:55:55 GMT; path=/")
                        //.addHeader("XSRF-TOKEN",MainActivity.XSRF_TOKEN)
                        .build()
        );


        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);



        Glide.with(getApplicationContext())
                //.setDefaultRequestOptions(new RequestOptions().timeout(30000))
                .load(url)
                .disallowHardwareConfig()
                .timeout(30000)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //.apply(options)
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("glide err", "onLoadFailed: "+e.toString()+"-"+model.toString() );
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.e("glide err", "onLoadFailed: "+dataSource.toString());
                        return false;
                    }
                })
                .error(R.drawable.logo)
                .override(600,200)
                .into(imgc);
       // progressBar.setMaxProgress(100);
        new loader().execute();
    }

    public class loader extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i=0;i<=10;i++){
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]*10);
            if (values[0]!=null){
                if (values[0]%2==0){
                    txtloading.setVisibility(View.VISIBLE);
                }
                else {
                    txtloading.setVisibility(View.GONE);
                }
            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent=new Intent(splashActivity.this,MainActivity.class);
            //startActivity(intent);
        }
    }
}
