package com.example.eventbusmqtt.eventbus.event;

public class EventMessage {

    String topic;
    String name;

    public EventMessage(String topic,String name){
        this.topic = topic;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String gettopic() {
        return topic;
    }

    public void settopic(String topic) {
        this.topic = topic;
    }

}
