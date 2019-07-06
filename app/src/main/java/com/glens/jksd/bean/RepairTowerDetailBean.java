package com.glens.jksd.bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/11.
 */
public class RepairTowerDetailBean {

    /**
     * checkRecordCode : bec32ea512e545c4b47685a982532a72
     * createTime : 2019-05-28 17:20
     * lineName : 冲冲冲线
     * lineVol : 35
     * phase : 1
     * picList : [{"picCode":"df1144ec8bcb431a937353e325fd9908","picUrl":"data/images/ohm/2019-05-28/a1248eb1-599c-4006-a0ad-7cbfebaab33f.jpg"}]
     * taskCode : 1
     * textName : 内容描述
     * towerNo : 001
     * createByName
     */

    private String checkRecordCode;
    private String createTime;
    private String lineName;
    private String lineVol;
    private String phase;
    private String taskCode;
    private String textName;
    private String towerNo;

    private String createByName;
    private List<PicListBean> picList;

    public String getCheckRecordCode() {
        return checkRecordCode;
    }

    public void setCheckRecordCode(String checkRecordCode) {
        this.checkRecordCode = checkRecordCode;
    }

    public String getCreateTime() {
        return createTime;
    }


    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
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
        return "RepairTowerDetailBean{" +
                "checkRecordCode='" + checkRecordCode + '\'' +
                ", createTime='" + createTime + '\'' +
                ", lineName='" + lineName + '\'' +
                ", lineVol='" + lineVol + '\'' +
                ", phase='" + phase + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", textName='" + textName + '\'' +
                ", towerNo='" + towerNo + '\'' +
                ", createByName='" + createByName + '\'' +
                ", picList=" + picList +
                '}';
    }
}
