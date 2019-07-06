package com.glens.jksd.bean.deteect;

import java.util.List;

public class PayMeasureDetailBean {

    /**
     * ambientTemperature : 25
     * crossDis : 2500
     * crossType : 1
     * isQualified : 1
     * lineVol : 35
     * lineName : mock
     * recodeCode : 33fe1545ab6143e2ac8dc2fef835000b
     * siteDesc : 描述
     * startTowerNo : 001
     * endTowerNo : 002
     * taskCode : bef15f25a34b468e87b5b285cf628a99
     * picList : [{"picCode":"d977ef54b00342eb8577d7d573755611","picUrl":"data/images/20190523/aff21a33-2374-467b-bdfa-dd5c85fb55a7.jpg"}]
     * createTime : mock
     * detectionTask : {"exeUnitName":"mock","executorName":"mock"}
     */

    private String ambientTemperature;
    private String crossDis;
    private int crossType;
    private int isQualified;
    private String lineVol;
    private String lineName;
    private String recodeCode;
    private String siteDesc;
    private String startTowerNo;
    private String endTowerNo;
    private String taskCode;
    private String createTime;
    private DetectionTaskBean detectionTask;
    private List<PicListBean> picList;

    public String getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(String ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public String getCrossDis() {
        return crossDis;
    }

    public void setCrossDis(String crossDis) {
        this.crossDis = crossDis;
    }

    public int getCrossType() {
        return crossType;
    }

    public void setCrossType(int crossType) {
        this.crossType = crossType;
    }

    public int getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(int isQualified) {
        this.isQualified = isQualified;
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

    public String getStartTowerNo() {
        return startTowerNo;
    }

    public void setStartTowerNo(String startTowerNo) {
        this.startTowerNo = startTowerNo;
    }

    public String getEndTowerNo() {
        return endTowerNo;
    }

    public void setEndTowerNo(String endTowerNo) {
        this.endTowerNo = endTowerNo;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
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
