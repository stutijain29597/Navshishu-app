package com.example.admin1.navshishu;

import android.icu.text.DateFormat;

/**
 * Created by admin1 on 26-09-2017.
 */
public class user {
    String Name;
    String sdate;
    String address;

    String pic;
    Boolean check;


    public user() {
    }

    public user(String name,String sdate, String add,String url) {
        this.Name = name;
        this.address = add;
        this.sdate = sdate;
        pic=url;
        this.check=false;

    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return address;
    }

    public String getsdate() {
        return sdate;
    }

    public Boolean getcheck(){return check;}
    public String getpic(){return pic;}


}

