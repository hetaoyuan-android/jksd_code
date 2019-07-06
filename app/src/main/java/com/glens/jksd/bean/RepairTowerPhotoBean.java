package com.glens.jksd.bean;

/**
 * Created by wkc on 2019/6/11.
 */
public class RepairTowerPhotoBean {

    private String creatorId;
    private String modifierId;
    private String picCode;
    private String picType;
    private String picUrl;
    private String reportDeptId;
    private String reportorId;
    private String taskCode;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getReportDeptId() {
        return reportDeptId;
    }

    public void setReportDeptId(String reportDeptId) {
        this.reportDeptId = reportDeptId;
    }

    public String getReportorId() {
        return reportorId;
    }

    public void setReportorId(String reportorId) {
        this.reportorId = reportorId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    @Override
    public String toString() {
        return "RepairTowerPhotoBean{" +
                "creatorId='" + creatorId + '\'' +
                ", modifierId='" + modifierId + '\'' +
                ", picCode='" + picCode + '\'' +
                ", picType='" + picType + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", reportDeptId='" + reportDeptId + '\'' +
                ", reportorId='" + reportorId + '\'' +
                ", taskCode='" + taskCode + '\'' +
                '}';
    }
}
