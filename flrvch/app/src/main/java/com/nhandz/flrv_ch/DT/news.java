package com.nhandz.flrv_ch.DT;

import java.io.Serializable;

public class news extends account implements Serializable {
    protected int IDBV;
    protected int IDND;
    protected String Content;
    protected String Img;
    protected String type_content;
    protected int CLike;
    protected String News_created_at;
    protected String News_updated_at;
    //protected String name;


//    public news(int IDND,
//                String username,
//                String password,
//                String name,
//                String SDT,
//                String email,
//                String dob,
//                String avtpage,
//                String avt,
//                String sex,
//                String created_at,
//                String updated_at,
//                String remember_token,
//                int IDBV,
//                String content,
//                String img,
//                String type_content,
//                int CLike,
//                String news_created_at,
//                String news_updated_at) {
//        super(IDND, username, password, name, SDT, email, dob, avtpage, avt, sex, created_at, updated_at, remember_token);
//        this.IDBV = IDBV;
//        this.IDND = IDND;
//        this.Content = content;
//        this.Img = img;
//        this.type_content = type_content;
//        this.CLike = CLike;
//        this.News_created_at = news_created_at;
//        this.News_updated_at = news_updated_at;
//    }

    public news(int IDND,
                int IDBV,
                String content,
                String img,
                String type_content,
                int CLike,
                String news_created_at,
                String news_updated_at){
        this.IDBV = IDBV;
        this.IDND = IDND;
        this.Content = content;
        this.Img = img;
        this.type_content = type_content;
        this.CLike = CLike;
        this.created_at = news_created_at;
        this.updated_at = news_updated_at;

    }

    public news(String name, String avt, int IDBV, int IDND, String content, String img, String type_content, int CLike, String news_created_at, String news_updated_at) {
        super(name, avt);
        this.IDBV = IDBV;
        this.IDND = IDND;
        Content = content;
        Img = img;
        this.type_content = type_content;
        this.CLike = CLike;
        News_created_at = news_created_at;
        News_updated_at = news_updated_at;
    }

    public news(){
        int IDBV=0;
        int IDND=0;
        String Content=null;
        String Img=null;
        String type_content=null;
        int CLike=0;
        String created_at=null;
        String updated_at=null;
        String name=null;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIDBV() {
        return IDBV;
    }

    public void setIDBV(int IDBV) {
        this.IDBV = IDBV;
    }

    public int getIDND() {
        return IDND;
    }

    public void setIDND(int IDND) {
        this.IDND = IDND;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getType_content() {
        return type_content;
    }

    public void setType_content(String type_content) {
        this.type_content = type_content;
    }

    public int getCLike() {
        return CLike;
    }

    public void setCLike(int CLike) {
        this.CLike = CLike;
    }

    public String getCreated_at() {
        return News_created_at;
    }

    public void setCreated_at(String created_at) {
        this.News_created_at = created_at;
    }

    public String getUpdated_at() {
        return News_updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.News_updated_at = updated_at;
    }
}
