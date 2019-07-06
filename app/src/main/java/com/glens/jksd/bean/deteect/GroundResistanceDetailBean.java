package com.glens.jksd.bean.deteect;

import java.util.List;

public class GroundResistanceDetailBean {


    /**
     * erAValue : 5
     * erBValue : 6
     * erCValue : 5
     * erDValue : 6
     * isResistorAQualified : 1
     * isResistorBQualified : 1
     * isResistorCQualified : 1
     * isResistorDQualified : 1
     * lineVol : 35
     * lineName : mock
     * picList : [{"picCode":"d977ef54b00342eb8577d7d573755611","picUrl":"data/images/20190523/aff21a33-2374-467b-bdfa-dd5c85fb55a7.jpg"}]
     * recodeCode : aa5ca334a78146c88e5d2fbfdf0a910d
     * siteDesc : mock
     * taskCode : 43fa6c32498f40ac8e30d414217bb438
     * towerNo : 001
     * createTime : mock
     * detectionTask : {"exeUnitName":"mock","executorName":"mock"}
     */

    private String erAValue;
    private String erBValue;
    private String erCValue;
    private String erDValue;
    private int isResistorAQualified;
    private int isResistorBQualified;
    private int isResistorCQualified;
    private int isResistorDQualified;
    private String lineVol;
    private String lineName;
    private String recodeCode;
    private String siteDesc;
    private String taskCode;
    private String towerNo;
    private String createTime;
    private DetectionTaskBean detectionTask;
    private List<PicListBean> picList;

    public String getErAValue() {
        return erAValue;
    }

    public void setErAValue(String erAValue) {
        this.erAValue = erAValue;
    }

    public String getErBValue() {
        return erBValue;
    }

    public void setErBValue(String erBValue) {
        this.erBValue = erBValue;
    }

    public String getErCValue() {
        return erCValue;
    }

    public void setErCValue(String erCValue) {
        this.erCValue = erCValue;
    }

    public String getErDValue() {
        return erDValue;
    }

    public void setErDValue(String erDValue) {
        this.erDValue = erDValue;
    }

    public int getIsResistorAQualified() {
        return isResistorAQualified;
    }

    public void setIsResistorAQualified(int isResistorAQualified) {
        this.isResistorAQualified = isResistorAQualified;
    }

    public int getIsResistorBQualified() {
        return isResistorBQualified;
    }

    public void setIsResistorBQualified(int isResistorBQualified) {
        this.isResistorBQualified = isResistorBQualified;
    }

    public int getIsResistorCQualified() {
        return isResistorCQualified;
    }

    public void setIsResistorCQualified(int isResistorCQualified) {
        this.isResistorCQualified = isResistorCQualified;
    }

    public int getIsResistorDQualified() {
        return isResistorDQualified;
    }

    public void setIsResistorDQualified(int isResistorDQualified) {
        this.isResistorDQualified = isResistorDQualified;
    }

    public String getLineVol() {
        return lineVol;
    }

    public void setLineVol(String lineVol) {
        this.lineVol = lineVol;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getRecodeCode() {
        return recodeCode;
    }

    public void setRecodeCode(String recodeCode) {
        this.recodeCode = recodeCode;
    }

    public String getSiteDesc() {
        return siteDesc;
    }

    public void setSiteDesc(String siteDesc) {
        this.siteDesc = siteDesc;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public DetectionTaskBean getDetectionTask() {
        return detectionTask;
    }

    public void setDetectionTask(DetectionTaskBean detectionTask) {
        this.detectionTask = detectionTask;
    }

    public List<PicListBean> getPicList() {
        return picList;
    }

    public void setPicList(List<PicListBean> picList) {
        this.picList = picList;
    }

    public static class DetectionTaskBean {
        /**
         * exeUnitName : mock
         * executorName : mock
         */

        private String exeUnitName;
        private String executorName;

        public String getExeUnitName() {
            return exeUnitName;
        }

        public void setExeUnitName(String exeUnitName) {
            this.exeUnitName = exeUnitName;
        }

        public String getExecutorName() {
            return executorName;
        }

        public void setExecutorName(String executorName) {
            this.executorName = executorName;
        }
    }

    public static class PicListBean {
        /**
         * picCode : d977ef54b00342eb8577d7d573755611
         * picUrl : data/images/20190523/aff21a33-2374-467b-bdfa-dd5c85fb55a7.jpg
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
    }
}
