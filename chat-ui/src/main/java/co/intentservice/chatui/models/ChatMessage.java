package co.intentservice.chatui.models;

import android.text.format.DateFormat;

import java.util.concurrent.TimeUnit;

public class ChatMessage {
    private String message;
    private String userName;
    private long timestamp;
    private Type type;

    public ChatMessage(String message, long timestamp, Type type){
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    public ChatMessage(String message, String userName, long timestamp, Type type){
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
        this.userName = userName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public String getFormattedTime(){
        return DateFormat.format("yyyy-MM-dd", timestamp).toString();
    }

    public enum Type {
        SENT, RECEIVED
    }
}
