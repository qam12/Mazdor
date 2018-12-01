package com.example.maju.mazdor.Model;

/**
 * Created by qamber.haider on 11/29/2018.
 */

public class User_Details {

    public String user_FirstName;
    public String user_Telephone;
    public String user_Email;
    public String user_Password;
    public String id;

    public User_Details() {
    }

    public User_Details(String user_FirstName, String user_Telephone, String user_Email, String user_Password, String id) {
        this.user_FirstName = user_FirstName;
        this.user_Telephone = user_Telephone;
        this.user_Email = user_Email;
        this.user_Password = user_Password;
        this.id = id;
    }

    public String getUser_FirstName() {
        return user_FirstName;
    }

    public void setUser_FirstName(String user_FirstName) {
        this.user_FirstName = user_FirstName;
    }

    public String getUser_Telephone() {
        return user_Telephone;
    }

    public void setUser_Telephone(String user_Telephone) {
        this.user_Telephone = user_Telephone;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
