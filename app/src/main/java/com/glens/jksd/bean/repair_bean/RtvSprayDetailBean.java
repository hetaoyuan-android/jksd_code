package com.glens.jksd.bean.repair_bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/19.
 */
public class RtvSprayDetailBean {

    /**
     * lineName : 大树线
     * lineVol : 35
     * loc : 1
     * phase : 1
     * picList : []
     * recordCode : 1
     * sprayManName : 111
     * sprayUnitName : 1111
     * taskCode : 1
     * textName : 11111
     * towerNo : 001
     */

    private String lineName;
    private String lineVol;
    private String loc;
    private String phase;
    private String recordCode;
    private String sprayManName;
    private String sprayUnitName;
    private String taskCode;
    private String textName;
    private String towerNo;
    private List<PicListBean> picList;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineVol() {
        return lineVol;
    }

    public void setLineVol(String lineVol) {
        this.lineVol = lineVol;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getSprayManName() {
        return sprayManName;
    }

    public void setSprayManName(String sprayManName) {
        this.sprayManName = sprayManName;
    }

    public String getSprayUnitName() {
        return sprayUnitName;
    }

    public void setSprayUnitName(String sprayUnitName) {
        this.sprayUnitName = sprayUnitName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public String getTowerNo() {
        return towerNo;
    }

    public void setTowerNo(String towerNo) {
        this.towerNo = towerNo;
    }

    public List<PicListBean> getPicList() {
        return picList;
    }

    public void setPicList(List<PicListBean> picList) {
        this.picList = picList;
    }
    public static class PicListBean {
        /**
         * picCode : df1144ec8bcb431a937353e325fd9908
         * picUrl : data/images/ohm/2019-05-28/a1248eb1-599c-4006-a0ad-7cbfebaab33f.jpg
         */

        private String picCode;
        private String picUrl;

        public String getPicCode() {
            return picCode;
        }

        public void setPicCode(String picCode) {
            this.picCode = picCode;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        @Override
        public String toString() {
            return "PicListBean{" +
                    "picCode='" + picCode + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RtvSprayDetailBean{" +
                "lineName='" + lineName + '\'' +
                ", lineVol='" + lineVol + '\'' +
                ", loc='" + loc + '\'' +
                ", phase='" + phase + '\'' +
                ", recordCode='" + recordCode + '\'' +
                ", sprayManName='" + sprayManName + '\'' +
                ", sprayUnitName='" + sprayUnitName + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", textName='" + textName + '\'' +
                ", towerNo='" + towerNo + '\'' +
                ", picList=" + picList +
                '}';
    }
}
