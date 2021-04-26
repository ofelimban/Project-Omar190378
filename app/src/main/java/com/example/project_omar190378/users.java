package com.example.project_omar190378;

public class users {

    private String fname;
    private String lname;
    private String mail;
    private String num;

    public users() {
    }

    public users(String fname, String lname, String mail, String num) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.num = num;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMail() {
        return mail;
    }

    public String getNum() {
        return num;
    }




}
