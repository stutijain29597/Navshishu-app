package com.example.admin1.navshishu;

/**
 * Created by admin1 on 26-02-2018.
 */
public class Message {
    private String message,user_name;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public Message() {
    }
    public Message(String message, String user_name) {
        this.message = message;
        this.user_name = user_name;
    }
}