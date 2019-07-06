package com.glens.jksd.bean;

/**
 * Created by wkc on 2019/6/14.
 */
public class RepairAddTowerCheckBean {
    private String checkRecordCode;

    public String getCheckRecordCode() {
        return checkRecordCode;
    }

    public void setCheckRecordCode(String checkRecordCode) {
        this.checkRecordCode = checkRecordCode;
    }

    @Override
    public String toString() {
        return "RepairAddTowerCheckBean{" +
                "checkRecordCode='" + checkRecordCode + '\'' +
                '}';
    }
}
