package com.glens.jksd.bean.repair_task_bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/20.
 */
public class RepairGroundBean {

    /**
     * total : 1
     * records : [{"checkTime":"2019-06-05 17:55","checker":"王斌","createTime":"2019-06-05 17:46","demolitionPerson":"王斌","demolitionTime":"2019-06-05 17:56","fixPerson":"王斌","fixTime":"2019-06-05 17:55","groudWireName":"20190605174601","groudWireType":5,"recordCode":"17769947a7274099bc5b2b8c9c21ea2f","returnTime":"2019-06-05 17:57","returner":"王斌","takedTime":"2019-06-05 17:46","takenPerson":"王斌","taskCode":"1"}]
     */

    private int total;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * checkTime : 2019-06-05 17:55
         * checker : 王斌
         * createTime : 2019-06-05 17:46
         * demolitionPerson : 王斌
         * demolitionTime : 2019-06-05 17:56
         * fixPerson : 王斌
         * fixTime : 2019-06-05 17:55
         * groudWireName : 20190605174601
         * groudWireType : 5
         * recordCode : 17769947a7274099bc5b2b8c9c21ea2f
         * returnTime : 2019-06-05 17:57
         * returner : 王斌
         * takedTime : 2019-06-05 17:46
         * takenPerson : 王斌
         * taskCode : 1
         */

        private String checkTime;
        private String checker;
        private String createTime;
        private String demolitionPerson;
        private String demolitionTime;
        private String fixPerson;
        private String fixTime;
        private String groudWireName;
        private int groudWireType;
        private String recordCode;
        private String returnTime;
        private String returner;
        private String takedTime;
        private String takenPerson;
        private String taskCode;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDemolitionPerson() {
            return demolitionPerson;
        }

        public void setDemolitionPerson(String demolitionPerson) {
            this.demolitionPerson = demolitionPerson;
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

        public String getTaskCode() {
            return taskCode;
        }

        public void setTaskCode(String taskCode) {
            this.taskCode = taskCode;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "checkTime='" + checkTime + '\'' +
                    ", checker='" + checker + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", demolitionPerson='" + demolitionPerson + '\'' +
                    ", demolitionTime='" + demolitionTime + '\'' +
                    ", fixPerson='" + fixPerson + '\'' +
                    ", fixTime='" + fixTime + '\'' +
                    ", groudWireName='" + groudWireName + '\'' +
                    ", groudWireType=" + groudWireType +
                    ", recordCode='" + recordCode + '\'' +
                    ", returnTime='" + returnTime + '\'' +
                    ", returner='" + returner + '\'' +
                    ", takedTime='" + takedTime + '\'' +
                    ", takenPerson='" + takenPerson + '\'' +
                    ", taskCode='" + taskCode + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RepairGroundBean{" +
                "total=" + total +
                ", records=" + records +
                '}';
    }
}
