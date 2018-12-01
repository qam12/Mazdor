package com.example.maju.mazdor.Model;

/**
 * Created by qamber.haider on 11/28/2018.
 */

public class UserList {
    public String prFirstName;
    public String prEmail;
    public String prTelephone;
    public String prid;

    public UserList() {
    }

    public UserList(String prFirstName, String prEmail, String prTelephone, String prid) {
        this.prFirstName = prFirstName;
        this.prEmail = prEmail;
        this.prTelephone = prTelephone;
        this.prid = prid;
    }

    public String getPrFirstName() {
        return prFirstName;
    }

    public void setPrFirstName(String prFirstName) {
        this.prFirstName = prFirstName;
    }

    public String getPrEmail() {
        return prEmail;
    }

    public void setPrEmail(String prEmail) {
        this.prEmail = prEmail;
    }

    public String getPrTelephone() {
        return prTelephone;
    }

    public void setPrTelephone(String prTelephone) {
        this.prTelephone = prTelephone;
    }

    public String getPrid() {
        return prid;
    }

    public void setPrid(String prid) {
        this.prid = prid;
    }

}
