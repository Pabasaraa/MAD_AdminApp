package com.example.kuppiya_admin;

import java.io.Serializable;

public class newsHelperClass implements Serializable {

       private String key;
       private  String topic, description;


    public newsHelperClass(String topic, String description,String key) {
        this.topic = topic;
        this.description = description;
        this.key = key;
    }

    public newsHelperClass() { }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }
}
