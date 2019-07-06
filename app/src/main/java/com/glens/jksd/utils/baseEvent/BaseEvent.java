package com.glens.jksd.utils.baseEvent;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by wkc on 16/8/20.
 */
public class BaseEvent implements Serializable {
    private String mMsg;
    private Bundle bundle;
    private int position;
    private int eventType;//可能类型有很多种，数据也不一样
    private Object data;//数据对象

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public BaseEvent(String msg) {
        mMsg = msg;
    }


    public BaseEvent(int type) {
        eventType = type;
    }

    public BaseEvent() {

    }

    public String getMsg() {
        return mMsg;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "mMsg='" + mMsg + '\'' +
                ", bundle=" + bundle +
                ", position=" + position +
                ", eventType=" + eventType +
                ", data=" + data +
                '}';
    }
}
