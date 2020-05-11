package com.nhandz.flrv_ch.DT;

import java.io.Serializable;

public class comments extends account implements Serializable {
    private int IDBV;
    private int IDNBL;
    private String Content;
    private String cmt_created_at;
    private String cmt_updated_at;

    public comments(int IDBV, int IDNBL,String name,String Avt, String content, String Created_at, String updated_at) {
        super(name,Avt);
        this.IDBV = IDBV;
        this.IDNBL = IDNBL;
        Content = content;
        this.cmt_created_at = Created_at;
        this.cmt_updated_at = updated_at;
    }

    public int getIDBV() {
        return IDBV;
    }

    public void setIDBV(int IDBV) {
        this.IDBV = IDBV;
    }

    public int getIDNBL() {
        return IDNBL;
    }

    public void setIDNBL(int IDNBL) {
        this.IDNBL = IDNBL;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return cmt_updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.cmt_updated_at = updated_at;
    }
}
