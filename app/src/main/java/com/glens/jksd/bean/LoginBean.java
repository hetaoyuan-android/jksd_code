package com.glens.jksd.bean;

public class LoginBean {


    /**
     * deptCode : 1
     * deptType : 1
     * dutyCode : 1
     * dutyName : 维修工程师
     * loginName : zhangliang
     * organizationCode : 1
     * organizationName : 1
     * roleCode : 1
     * roleName : 外业人员
     * userCode : 1
     * userName : 张亮
     * wechatNo : 0808
     */

    private String deptCode;
    private String deptType;
    private String dutyCode;
    private String dutyName;
    private String loginName;
    private String organizationCode;
    private String organizationName;
    private String roleCode;
    private String roleName;
    private String userCode;
    private String userName;
    private String wechatNo;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "deptCode='" + deptCode + '\'' +
                ", deptType='" + deptType + '\'' +
                ", dutyCode='" + dutyCode + '\'' +
                ", dutyName='" + dutyName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", organizationCode='" + organizationCode + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", wechatNo='" + wechatNo + '\'' +
                '}';
    }
}
