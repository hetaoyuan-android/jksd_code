package com.glens.jksd.bean;

/**
 * Created by wkc on 2019/6/10.
 */
public class RepairBillBean {

    /**
     * constructionUnit : 121212
     * createByName : 张亮
     * createTime : 2019-05-27 14:05
     * dispatchTeam : 1233
     * endDate : 2019-05-31
     * isPowerCut : 0  //0 false,1 true
     * overhaulType : 1
     * powerOutage : 123
     * startDate : 2019-05-27
     * taskCode : 1
     * taskName : ceshi
     * taskStatus : 1
     * workLeader : 12
     */

    private String constructionUnit;
    private String createByName;
    private String createTime;
    private String dispatchTeam;
    private String endDate;
    private int isPowerCut;
    private int overhaulType;
    private String powerOutage;
    private String startDate;
    private String taskCode;
    private String taskName;
    private int taskStatus;
    private String workLeader;

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDispatchTeam() {
        return dispatchTeam;
    }

    public void setDispatchTeam(String dispatchTeam) {
        this.dispatchTeam = dispatchTeam;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getIsPowerCut() {
        return isPowerCut;
    }

    public void setIsPowerCut(int isPowerCut) {
        this.isPowerCut = isPowerCut;
    }

    public int getOverhaulType() {
        return overhaulType;
    }

    public void setOverhaulType(int overhaulType) {
        this.overhaulType = overhaulType;
    }

    public String getPowerOutage() {
        return powerOutage;
    }

    public void setPowerOutage(String powerOutage) {
        this.powerOutage = powerOutage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getWorkLeader() {
        return workLeader;
    }

    public void setWorkLeader(String workLeader) {
        this.workLeader = workLeader;
    }

    @Override
    public String toString() {
        return "RepairBillBean{" +
                "constructionUnit='" + constructionUnit + '\'' +
                ", createByName='" + createByName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", dispatchTeam='" + dispatchTeam + '\'' +
                ", endDate='" + endDate + '\'' +
                ", isPowerCut=" + isPowerCut +
                ", overhaulType=" + overhaulType +
                ", powerOutage='" + powerOutage + '\'' +
                ", startDate='" + startDate + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskStatus=" + taskStatus +
                ", workLeader='" + workLeader + '\'' +
                '}';
    }
}
