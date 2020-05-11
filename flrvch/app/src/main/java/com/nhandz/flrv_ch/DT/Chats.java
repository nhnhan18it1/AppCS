package com.nhandz.flrv_ch.DT;

import java.io.Serializable;

public class Chats implements Serializable {
    private String ID;
    private String IDsend;
    private String IDreceive;
    private String content;
    private String status;
    private String created_at;
    private String updated_at;

    public Chats(String ID, String IDsend, String IDreceive, String content, String status, String created_at, String updated_at) {
        this.ID = ID;
        this.IDsend = IDsend;
        this.IDreceive = IDreceive;
        this.content = content;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDsend() {
        return IDsend;
    }

    public void setIDsend(String IDsend) {
        this.IDsend = IDsend;
    }

    public String getIDreceive() {
        return IDreceive;
    }

    public void setIDreceive(String IDreceive) {
        this.IDreceive = IDreceive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
