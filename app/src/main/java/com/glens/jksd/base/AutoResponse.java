package com.glens.jksd.base;


import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 16/5/16.
 */
public class AutoResponse<E, T> implements Serializable {

    private int statusCode;
    private boolean success;
    private String resultMsg;
    private E data;           // 单个对象
    private List<T> objList;       // 数组对象
    private String ticket;     // 校验token

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public List<T> getObjList() {
        return objList;
    }

    public void setObjList(List<T> objList) {
        this.objList = objList;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "AutoResponse{" +
                "statusCode=" + statusCode +
                ", success=" + success +
                ", resultMsg='" + resultMsg + '\'' +
                ", data=" + data +
                ", objList=" + objList +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
