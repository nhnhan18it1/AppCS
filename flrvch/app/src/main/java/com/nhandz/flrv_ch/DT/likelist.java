package com.nhandz.flrv_ch.DT;

import java.io.Serializable;

public class likelist extends account implements Serializable {
    protected int IDBV;
    protected int IDNL;
    protected String likelist_created_at;
    protected String likelist_updated_at;

    public likelist(int IDBV, int IDNL,String Name,String avt,String created_at, String updated_at) {
        super(Name,avt);
        this.IDBV = IDBV;
        this.IDNL = IDNL;
        this.likelist_created_at = created_at;
        this.likelist_updated_at = updated_at;
    }

    public int getIDBV() {
        return IDBV;
    }

    public void setIDBV(int IDBV) {
        this.IDBV = IDBV;
    }

    public int getIDNL() {
        return IDNL;
    }

    public void setIDNL(int IDNL) {
        this.IDNL = IDNL;
    }

    public String getLikelist_created_at() {
        return likelist_created_at;
    }

    public void setLikelist_created_at(String likelist_created_at) {
        this.likelist_created_at = likelist_created_at;
    }

    public String getLikelist_updated_at() {
        return likelist_updated_at;
    }

    public void setLikelist_updated_at(String likelist_updated_at) {
        this.likelist_updated_at = likelist_updated_at;
    }
}
