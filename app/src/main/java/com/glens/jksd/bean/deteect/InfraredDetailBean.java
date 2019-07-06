package com.glens.jksd.bean.deteect;

import java.util.List;

public class InfraredDetailBean {


    /**
     * lineName : 大树线
     * lineVol : 35
     * towerNo : 001
     * normalTemperature : 26.6
     * ambientTemperature : 25.6
     * maxTemperature : 28.5
     * picList : [{"picCode":"1","picType":1,"picUrl":"data/images/1/736cfbfd-9bad-4684-acc9-5909729a65a7.jpg","recodeCode":"1"}]
     * recodeCode : 1
     * siteDesc : 现场描述
     * parts : 1
     * taskCode : mock
     * createTime : mock
     * detectionTask : {"exeUnitName":"mock","executorName":"mock"}
     */

    private String lineName;
    private String lineVol;
    private String towerNo;
    private String normalTemperature;
    private String ambientTemperature;
    private String maxTemperature;
    private String recodeCode;
    private String siteDesc;
    private int parts;
    private String taskCode;
    private String createTime;
    private DetectionTaskBean detectionTask;
    private List<PicListBean> picList;

    @Override
    public String toString() {
        return "InfraredDetailBean{" +
                "lineName='" + lineName + '\'' +
                ", lineVol='" + lineVol + '\'' +
                ", towerNo='" + towerNo + '\'' +
                ", normalTemperature='" + normalTemperature + '\'' +
                ", ambientTemperature='" + ambientTemperature + '\'' +
                ", maxTemperature='" + maxTemperature + '\'' +
                ", recodeCode='" + recodeCode + '\'' +
                ", siteDesc='" + siteDesc + '\'' +
                ", parts=" + parts +
                ", taskCode='" + taskCode + '\'' +
                ", createTime='" + createTime + '\'' +
                ", detectionTask=" + detectionTask +
                ", picList=" + picList +
                '}';
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

    public String getTowerNo() {
        return towerNo;
    }

    public void setTowerNo(String towerNo) {
        this.towerNo = towerNo;
    }

    public String getNormalTemperature() {
        return normalTemperature;
    }

    public void setNormalTemperature(String normalTemperature) {
        this.normalTemperature = normalTemperature;
    }

    public String getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(String ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
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

    public int getParts() {
        return parts;
    }

    public void setParts(int parts) {
        this.parts = parts;
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
         * picCode : 1
         * picType : 1
         * picUrl : data/images/1/736cfbfd-9bad-4684-acc9-5909729a65a7.jpg
         * recodeCode : 1
         */

        private String picCode;
        private int picType;
        private String picUrl;
        private String recodeCode;

        public String getPicCode() {
            return picCode;
        }

        public void setPicCode(String picCode) {
            this.picCode = picCode;
        }

        public int getPicType() {
            return picType;
        }

        public void setPicType(int picType) {
            this.picType = picType;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getRecodeCode() {
            return recodeCode;
        }

        public void setRecodeCode(String recodeCode) {
            this.recodeCode = recodeCode;
        }
    }
}
