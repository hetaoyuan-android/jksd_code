package com.glens.jksd.base;

import java.io.Serializable;

public class AutoSingleResponse<E> implements Serializable {
    private int httpCode;
    private boolean result;
    private String msg;
    private E data;           // 单个对象
    private String ticket;     // 校验token

    public AutoSingleResponse(int httpCode, String msg) {
        this.httpCode = httpCode;
        this.msg = msg;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "AutoSingleResponse{" +
                "httpCode=" + httpCode +
                ", result=" + result +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
