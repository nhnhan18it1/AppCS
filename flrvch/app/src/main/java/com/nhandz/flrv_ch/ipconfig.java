package com.nhandz.flrv_ch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ipconfig extends AppCompatActivity {

    private EditText edtIp;
    private Button btnback;
    private Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipconfig);

        edtIp=findViewById(R.id.ipconfig_edt_ip);
        btnback=findViewById(R.id.ipconfig_btn_back);
        btnsubmit=findViewById(R.id.ipconfig_btn_submit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtIp.getText().equals("")){
                    MainActivity.server="http://"+edtIp.getText().toString()+":8888/FLRV-CH";
                    MainActivity.serverImg="http://"+edtIp.getText().toString()+":8888";
                    Toast.makeText(ipconfig.this, "Newserver:"+MainActivity.server, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ipconfig.this, "ip không được để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
