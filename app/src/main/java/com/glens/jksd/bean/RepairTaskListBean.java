package com.glens.jksd.bean;

import java.io.Serializable;

public class RepairTaskListBean implements Serializable {
    private String userName;
    private String department;
    private String taskName;
    private String taskType;
    private String taskTime;

    public RepairTaskListBean(String userName, String department, String taskName, String taskType, String taskTime) {
        this.userName = userName;
        this.department = department;
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskTime = taskTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    @Override
    public String toString() {
        return "RepairTaskListBean{" +
                "userName='" + userName + '\'' +
                ", department='" + department + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskType='" + taskType + '\'' +
                ", taskTime='" + taskTime + '\'' +
                '}';
    }
}
