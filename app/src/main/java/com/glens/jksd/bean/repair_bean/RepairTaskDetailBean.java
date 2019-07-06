package com.glens.jksd.bean.repair_bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/19.
 */
public class RepairTaskDetailBean {

    /**
     * createTime : 2019-06-03 15:06
     * lineName : 测试线
     * lineVol : 35
     * picList : []
     * recordCode : 1
     * recordTime : 2019-06-03 15:06
     * surveyManName : 2222
     * surveyUnitName : 1111
     * taskCode : 1
     * towerNo : 001
     * workContent : 1111
     */

    private String createTime;
    private String lineName;
    private String lineVol;
    private String recordCode;
    private String recordTime;
    private String surveyManName;
    private String surveyUnitName;
    private String taskCode;
    private String towerNo;
    private String workContent;
    private List<PicListBean> picList;

    public String getCreateTime() {
        return createTime;
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

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getSurveyManName() {
        return surveyManName;
    }

    public void setSurveyManName(String surveyManName) {
        this.surveyManName = surveyManName;
    }

    public String getSurveyUnitName() {
        return surveyUnitName;
    }

    public void setSurveyUnitName(String surveyUnitName) {
        this.surveyUnitName = surveyUnitName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTowerNo() {
        return towerNo;
    }

    public void setTowerNo(String towerNo) {
        this.towerNo = towerNo;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
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
        return "RepairTaskDetailBean{" +
                "createTime='" + createTime + '\'' +
                ", lineName='" + lineName + '\'' +
                ", lineVol='" + lineVol + '\'' +
                ", recordCode='" + recordCode + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", surveyManName='" + surveyManName + '\'' +
                ", surveyUnitName='" + surveyUnitName + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", towerNo='" + towerNo + '\'' +
                ", workContent='" + workContent + '\'' +
                ", picList=" + picList +
                '}';
    }
}
