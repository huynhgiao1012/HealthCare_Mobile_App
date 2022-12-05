package com.example.healthcareapp;

public class Message {
    private String sender, receiver, msg;
    private int pfp;

    public Message(String sender, String receiver, String msg, int pfp) {
        this.msg = msg;
        this.receiver = receiver;
        this.sender = sender;
        this.pfp = pfp;
    }

    public String getMsg() {
        return msg;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public int getPfp() {
        return pfp;
    }
}
