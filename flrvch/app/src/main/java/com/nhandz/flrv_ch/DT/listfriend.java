package com.nhandz.flrv_ch.DT;

import com.google.gson.Gson;
import com.nhandz.flrv_ch.MainActivity;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class listfriend implements Serializable {
    private String ID1;
    private String ID2;
    private String created_at;
    private String update_at;
    private boolean IsOnline=true;

    public listfriend(String ID1, String ID2, String created_at, String update_at) {
        this.ID1 = ID1;
        this.ID2 = ID2;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public boolean isOnline() {
        return IsOnline;
    }

    public void setOnline(boolean online) {
        IsOnline = online;
    }

    public String getID1() {
        return ID1;
    }

    public void setID1(String ID1) {
        this.ID1 = ID1;
    }

    public String getID2() {
        return ID2;
    }

    public void setID2(String ID2) {
        this.ID2 = ID2;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }




}
