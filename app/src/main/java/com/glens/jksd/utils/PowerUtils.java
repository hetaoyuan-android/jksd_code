package com.glens.jksd.utils;

import com.glens.jksd.utils.baseEvent.BaseEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wkc on 2019/6/14.
 */
public class PowerUtils {
    public static final String mBasePhoto = "http://172.16.2.58:8080/olis/eap/";
    private static final String mXiaoPhoto = "http://172.16.2.56:8080/olis/eap/";

    public static void postEvent(int type){
        BaseEvent event = new BaseEvent();
        event.setEventType(type);
        EventBus.getDefault().post(event);
    }

    public static String getmBasePhoto(){
      return mBasePhoto;
    }

    public static String getXiaoPhoto(){
        return mXiaoPhoto;
    }
}
