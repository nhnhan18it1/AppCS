package com.nhandz.flrv_ch.DT;

import java.io.Serializable;

public class account implements Serializable {
    protected int ID;
    protected String Username;
    protected String password;
    protected String name;
    protected String SDT;
    protected String Email;
    protected String Dob;
    protected String Avtpage;
    protected String Avt;
    protected String sex;
    protected String created_at;
    protected String updated_at;
    protected String remember_token;

    public account(int ID, String username, String password, String name, String SDT, String email, String dob, String avtpage, String avt,
                   String sex, String created_at, String updated_at, String remember_token) {
        this.ID = ID;
        this.Username = username;
        this.password = password;
        this.name = name;
        this.SDT = SDT;
        this.Email = email;
        this.Dob = dob;
        this.Avtpage = avtpage;
        this.Avt = avt;
        this.sex = sex;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.remember_token = remember_token;
    }

    public account(String name, String avt) {

        this.name = name;
        this.Avt = avt;
    }

    public account(int ID, String name, String avt) {
        this.ID = ID;
        this.name = name;
        Avt = avt;
    }

    public account(){
        int ID=0;
        String Username=null;
        String password=null;
        String name=null;
        String SDT=null;
        String Email=null;
        String Dob=null;
        String Avtpage=null;
        String Avt=null;
        String sex=null;
        String created_at=null;
        String updated_at=null;
        String remember_token=null;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getAvtpage() {
        return Avtpage;
    }

    public void setAvtpage(String avtpage) {
        Avtpage = avtpage;
    }

    public String getAvt() {
        return Avt;
    }

    public void setAvt(String avt) {
        Avt = avt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }
}
