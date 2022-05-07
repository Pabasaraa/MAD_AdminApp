package com.example.kuppiya_admin;

public class newsHelperClass {

        String topic, description;


    public newsHelperClass(String topic, String description) {
        this.topic = topic;
        this.description = description;
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
}
