package com.example.admin1.navshishu;

/**
 * Created by admin1 on 07-10-2017.
 */
public class feed {
    String Name;

    String feedback;
    public feed()
    {}
    public feed(String name,String feedb)
    {
        Name=name;

        feedback=feedb;

    }
    public String getName() {
        return Name;
    }
    public String getfeed(){ return feedback;}

}
