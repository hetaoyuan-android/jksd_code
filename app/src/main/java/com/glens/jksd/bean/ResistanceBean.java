package com.glens.jksd.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wkc on 2019/5/22.
 */
public class ResistanceBean implements Serializable {

    /**
     * total : 4
     * records : [{"endDate":"2019-06-20","executorId":"1","executorName":"zhangliang","lineName":"测试线001",
     * "lineVol":"35","startDate":"2019-06-01","taskCode":"d833300bb77d484cba703bee250b1b97","taskName":"red test",
     * "taskStatus":1,"taskType":2},{"endDate":"2019-05-27","executorId":"1","executorName":"zhangliang","lineName":"红黑线002,大树线001","lineVol":"35,35","startDate":"2019-05-22","taskCode":"43fa6c32498f40ac8e30d414217bb438","taskName":"接地电阻测试","taskStatus":2,"taskType":1},{"endDate":"2019-05-27","executorId":"1","executorName":"zhangliang","lineName":"大树线001,红黑线002","lineVol":"35,35","startDate":"2019-05-22","taskCode":"5d5ed7dec6824cb29a103b95fe4a5356","taskName":"红外测温测试","taskStatus":2,"taskType":2},{"endDate":"2019-05-27","executorId":"1","executorName":"王斌","lineVol":"35,35","startDate":"2019-05-22","taskCode":"bef15f25a34b468e87b5b285cf628a99","taskName":"交跨测量测试","taskStatus":2,"taskType":3}]
     * erDectionCnt : 1
     * cmDectionCnt : 1
     * irDetectionCnt : 2
     */

    private int total;
    private int erDectionCnt;
    private int cmDectionCnt;
    private int irDetectionCnt;
    private List<RecordBean> records;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    private int pages;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getErDectionCnt() {
        return erDectionCnt;
    }

    public void setErDectionCnt(int erDectionCnt) {
        this.erDectionCnt = erDectionCnt;
    }

    public int getCmDectionCnt() {
        return cmDectionCnt;
    }

    public void setCmDectionCnt(int cmDectionCnt) {
        this.cmDectionCnt = cmDectionCnt;
    }

    public int getIrDetectionCnt() {
        return irDetectionCnt;
    }

    public void setIrDetectionCnt(int irDetectionCnt) {
        this.irDetectionCnt = irDetectionCnt;
    }

    public List<RecordBean> getRecord() {
        return records;
    }

    public void setRecord(List<RecordBean> record) {
        this.records = record;
    }

    public static class RecordBean {
        /**
         * endDate : 2019-06-20
         * executorId : 1
         * executorName : zhangliang
         * lineName : 测试线001
         * lineVol : 35
         * startDate : 2019-06-01
         * taskCode : d833300bb77d484cba703bee250b1b97
         * taskName : red test
         * taskStatus : 1
         * taskType : 2
         */

        private String endDate;
        private String executorId;
        private String executorName;
        private String lineName;
        private String lineVol;
        private String startDate;
        private String taskCode;
        private String taskName;
        private int taskStatus;
        private int taskType;

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getExecutorId() {
            return executorId;
        }

        public void setExecutorId(String executorId) {
            this.executorId = executorId;
        }

        public String getExecutorName() {
            return executorName;
        }

        public void setExecutorName(String executorName) {
            this.executorName = executorName;
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

        public int getTaskType() {
            return taskType;
        }

        public void setTaskType(int taskType) {
            this.taskType = taskType;
        }

        @Override
        public String toString() {
            return "RecordBean{" +
                    "endDate='" + endDate + '\'' +
                    ", executorId='" + executorId + '\'' +
                    ", executorName='" + executorName + '\'' +
                    ", lineName='" + lineName + '\'' +
                    ", lineVol='" + lineVol + '\'' +
                    ", startDate='" + startDate + '\'' +
                    ", taskCode='" + taskCode + '\'' +
                    ", taskName='" + taskName + '\'' +
                    ", taskStatus=" + taskStatus +
                    ", taskType=" + taskType +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ResistanceBean{" +
                "total=" + total +
                ", erDectionCnt=" + erDectionCnt +
                ", cmDectionCnt=" + cmDectionCnt +
                ", irDetectionCnt=" + irDetectionCnt +
                ", records=" + records +
                '}';
    }
}
