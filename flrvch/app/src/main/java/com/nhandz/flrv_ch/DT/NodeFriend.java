package com.nhandz.flrv_ch.DT;

import java.io.Serializable;

public class NodeFriend implements Serializable {
    private String IDND;
    private String Name;
    private String Avt;
    private String IDN;

    public NodeFriend(String IDND, String name, String avt, String IDN) {
        this.IDND = IDND;
        Name = name;
        Avt = avt;
        this.IDN = IDN;
    }

    public String getIDND() {
        return IDND;
    }

    public void setIDND(String IDND) {
        this.IDND = IDND;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAvt() {
        return Avt;
    }

    public void setAvt(String avt) {
        Avt = avt;
    }

    public String getIDN() {
        return IDN;
    }

    public void setIDN(String IDN) {
        this.IDN = IDN;
    }
}
