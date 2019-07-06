package com.glens.jksd.bean;

import java.io.Serializable;

/**
 * Created by wkc on 2019/5/23.
 */
public class DetectionCountBean implements Serializable {
//    {                //类型：Object  必有字段  备注：无
//        "total":49,                //类型：Number  必有字段  备注：总记录
//            "irDetectionCnt":8,                //类型：Number  必有字段  备注：红外测温数量
//            "cmDectionCnt":10,                //类型：Number  必有字段  备注：交跨测量数量
//            "erDectionCnt":31                //类型：Number  必有字段  备注：接地电阻数量
//    }
    private int total;
    private int irDetectionCnt;
    private int cmDectionCnt;
    private int erDectionCnt;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIrDetectionCnt() {
        return irDetectionCnt;
    }

    public void setIrDetectionCnt(int irDetectionCnt) {
        this.irDetectionCnt = irDetectionCnt;
    }

    public int getCmDectionCnt() {
        return cmDectionCnt;
    }

    public void setCmDectionCnt(int cmDectionCnt) {
        this.cmDectionCnt = cmDectionCnt;
    }

    public int getErDectionCnt() {
        return erDectionCnt;
    }

    public void setErDectionCnt(int erDectionCnt) {
        this.erDectionCnt = erDectionCnt;
    }

    @Override
    public String toString() {
        return "DetectionCountBean{" +
                "total=" + total +
                ", irDetectionCnt=" + irDetectionCnt +
                ", cmDectionCnt=" + cmDectionCnt +
                ", erDectionCnt=" + erDectionCnt +
                '}';
    }
}
