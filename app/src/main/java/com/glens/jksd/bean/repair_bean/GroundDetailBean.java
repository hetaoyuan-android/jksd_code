package com.glens.jksd.bean.repair_bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/20.
 */
public class GroundDetailBean {


    /**
     * checkTime : 2019-06-05 17:55
     * checker : 王斌
     * checkerId : 12
     * createTime : 2019-06-05 17:46
     * creatorId : 12
     * demolitionPerson : 王斌
     * demolitionPersonId : 12
     * demolitionTime : 2019-06-05 17:56
     * fixPerson : 王斌
     * fixPersonId : 12
     * fixTime : 2019-06-05 17:55
     * groudWireName : 20190605174601
     * groudWireType : 5
     * modifierId : 12
     * picList : [{"picCode":"044fd3f8386f4e4ebc04e042e7e87ab9","picType":1,"picUrl":"data/images/ohm/2019-06-05/ef373242-98cb-472f-9874-5547da7eab89.jpg"},
     *  {"picCode":"8891c15b78864e04a9870edcb3882175","picType":2,"picUrl":"data/images/ohm/2019-06-05/9695dcba-b510-4467-b6a3-3d3dbed66873.jpg"},
     *  {"picCode":"a62b878e05a54cc19e9f18af341b64b8","picType":3,"picUrl":"data/images/ohm/2019-06-05/921a2a49-e849-4e42-a0cb-4053d798330f.jpg"},{
     *  "picCode":"cfb567ee31c54efeb6870dead3c92f89","picType":4,"picUrl":"data/images/ohm/2019-06-05/80f3a01f-ecd9-45ca-82aa-ab431047e7ff.jpg"},
     *  {"picCode":"de9e469dc41748d0bd167e06ae8290a8","picType":5,"picUrl":"data/images/ohm/2019-06-05/f1af4385-396a-4de2-93ea-ba2eff837aa5.jpg"}]
     * recordCode : 17769947a7274099bc5b2b8c9c21ea2f
     * returnTime : 2019-06-05 17:57
     * returner : 王斌
     * returnerId : 12
     * takedTime : 2019-06-05 17:46
     * takenPerson : 王斌
     * takerPersonId : 12
     * taskCode : 1
     * updateTime : 2019-06-05 17:57
     */

    private String checkTime;
    private String checker;
    private String checkerId;
    private String createTime;
    private String creatorId;
    private String demolitionPerson;
    private String demolitionPersonId;
    private String demolitionTime;
    private String fixPerson;
    private String fixPersonId;
    private String fixTime;
    private String groudWireName;
    private int groudWireType;
    private String modifierId;
    private String recordCode;
    private String returnTime;
    private String returner;
    private String returnerId;
    private String takedTime;
    private String takenPerson;
    private String takerPersonId;
    private String taskCode;
    private String updateTime;
    private List<PicListBean> picList;

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDemolitionPerson() {
        return demolitionPerson;
    }

    public void setDemolitionPerson(String demolitionPerson) {
        this.demolitionPerson = demolitionPerson;
    }

    public String getDemolitionPersonId() {
        return demolitionPersonId;
    }

    public void setDemolitionPersonId(String demolitionPersonId) {
        this.demolitionPersonId = demolitionPersonId;
    }

    public String getDemolitionTime() {
        return demolitionTime;
    }

    public void setDemolitionTime(String demolitionTime) {
        this.demolitionTime = demolitionTime;
    }

    public String getFixPerson() {
        return fixPerson;
    }

    public void setFixPerson(String fixPerson) {
        this.fixPerson = fixPerson;
    }

    public String getFixPersonId() {
        return fixPersonId;
    }

    public void setFixPersonId(String fixPersonId) {
        this.fixPersonId = fixPersonId;
    }

    public String getFixTime() {
        return fixTime;
    }

    public void setFixTime(String fixTime) {
        this.fixTime = fixTime;
    }

    public String getGroudWireName() {
        return groudWireName;
    }

    public void setGroudWireName(String groudWireName) {
        this.groudWireName = groudWireName;
    }

    public int getGroudWireType() {
        return groudWireType;
    }

    public void setGroudWireType(int groudWireType) {
        this.groudWireType = groudWireType;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturner() {
        return returner;
    }

    public void setReturner(String returner) {
        this.returner = returner;
    }

    public String getReturnerId() {
        return returnerId;
    }

    public void setReturnerId(String returnerId) {
        this.returnerId = returnerId;
    }

    public String getTakedTime() {
        return takedTime;
    }

    public void setTakedTime(String takedTime) {
        this.takedTime = takedTime;
    }

    public String getTakenPerson() {
        return takenPerson;
    }

    public void setTakenPerson(String takenPerson) {
        this.takenPerson = takenPerson;
    }

    public String getTakerPersonId() {
        return takerPersonId;
    }

    public void setTakerPersonId(String takerPersonId) {
        this.takerPersonId = takerPersonId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<PicListBean> getPicList() {
        return picList;
    }

    public void setPicList(List<PicListBean> picList) {
        this.picList = picList;
    }

    public static class PicListBean {
        /**
         * picCode : 044fd3f8386f4e4ebc04e042e7e87ab9
         * picType : 1
         * picUrl : data/images/ohm/2019-06-05/ef373242-98cb-472f-9874-5547da7eab89.jpg
         */

        private String picCode;
        private int picType;
        private String picUrl;

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

        @Override
        public String toString() {
            return "PicListBean{" +
                    "picCode='" + picCode + '\'' +
                    ", picType=" + picType +
                    ", picUrl='" + picUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GroundDetailBean{" +
                "checkTime='" + checkTime + '\'' +
                ", checker='" + checker + '\'' +
                ", checkerId='" + checkerId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", demolitionPerson='" + demolitionPerson + '\'' +
                ", demolitionPersonId='" + demolitionPersonId + '\'' +
                ", demolitionTime='" + demolitionTime + '\'' +
                ", fixPerson='" + fixPerson + '\'' +
                ", fixPersonId='" + fixPersonId + '\'' +
                ", fixTime='" + fixTime + '\'' +
                ", groudWireName='" + groudWireName + '\'' +
                ", groudWireType=" + groudWireType +
                ", modifierId='" + modifierId + '\'' +
                ", recordCode='" + recordCode + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", returner='" + returner + '\'' +
                ", returnerId='" + returnerId + '\'' +
                ", takedTime='" + takedTime + '\'' +
                ", takenPerson='" + takenPerson + '\'' +
                ", takerPersonId='" + takerPersonId + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", picList=" + picList +
                '}';
    }
}
