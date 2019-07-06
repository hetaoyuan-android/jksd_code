package com.glens.jksd.bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/3.
 */
public class RepairListBean {


    /**
     * total : 2
     * maintainCnt : 0
     * techTransCnt : 2
     * overhaulCnt : 0
     * records : [{"cityUnitId":"1","cityUnitName":"ccc","constructionUnit":"cccc","constructionUnitId":"1","createByName":"张亮","dispatchTeam":"ccc","dispatchTeamId":"1","endDate":"2019-06-19","lineName":"测试线001,测试线001,红外线002,测试线004,星星线003","lineVol":"35,35,35,35,35","overhaulType":1,"startDate":"2019-06-03","taskCode":"2","taskName":"123","taskStatus":1},{"cityUnitId":"1","cityUnitName":"122","constructionUnit":"121212","constructionUnitId":"1","createByName":"张亮","dispatchTeam":"1233","dispatchTeamId":"1","endDate":"2019-05-31","lineName":"测试线001,红外线002,测试线004,星星线003,测试线001","lineVol":"35,35,35,35,35","overhaulType":1,"startDate":"2019-05-27","taskCode":"1","taskName":"ceshi","taskStatus":1}]
     */

    private int total;
    private int maintainCnt;
    private int techTransCnt;
    private int overhaulCnt;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMaintainCnt() {
        return maintainCnt;
    }

    public void setMaintainCnt(int maintainCnt) {
        this.maintainCnt = maintainCnt;
    }

    public int getTechTransCnt() {
        return techTransCnt;
    }

    public void setTechTransCnt(int techTransCnt) {
        this.techTransCnt = techTransCnt;
    }

    public int getOverhaulCnt() {
        return overhaulCnt;
    }

    public void setOverhaulCnt(int overhaulCnt) {
        this.overhaulCnt = overhaulCnt;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * cityUnitId : 1
         * cityUnitName : ccc
         * constructionUnit : cccc
         * constructionUnitId : 1
         * createByName : 张亮
         * dispatchTeam : ccc
         * dispatchTeamId : 1
         * endDate : 2019-06-19
         * lineName : 测试线001,测试线001,红外线002,测试线004,星星线003
         * lineVol : 35,35,35,35,35
         * overhaulType : 1
         * startDate : 2019-06-03
         * taskCode : 2
         * taskName : 123
         * taskStatus : 1
         */

        private String cityUnitId;
        private String cityUnitName;
        private String constructionUnit;
        private String constructionUnitId;
        private String createByName;
        private String dispatchTeam;
        private String dispatchTeamId;
        private String endDate;
        private String lineName;
        private String lineVol;
        private int overhaulType;
        private String startDate;
        private String taskCode;
        private String taskName;
        private int taskStatus;

        public String getCityUnitId() {
            return cityUnitId;
        }

        public void setCityUnitId(String cityUnitId) {
            this.cityUnitId = cityUnitId;
        }

        public String getCityUnitName() {
            return cityUnitName;
        }

        public void setCityUnitName(String cityUnitName) {
            this.cityUnitName = cityUnitName;
        }

        public String getConstructionUnit() {
            return constructionUnit;
        }

        public void setConstructionUnit(String constructionUnit) {
            this.constructionUnit = constructionUnit;
        }

        public String getConstructionUnitId() {
            return constructionUnitId;
        }

        public void setConstructionUnitId(String constructionUnitId) {
            this.constructionUnitId = constructionUnitId;
        }

        public String getCreateByName() {
            return createByName;
        }

        public void setCreateByName(String createByName) {
            this.createByName = createByName;
        }

        public String getDispatchTeam() {
            return dispatchTeam;
        }

        public void setDispatchTeam(String dispatchTeam) {
            this.dispatchTeam = dispatchTeam;
        }

        public String getDispatchTeamId() {
            return dispatchTeamId;
        }

        public void setDispatchTeamId(String dispatchTeamId) {
            this.dispatchTeamId = dispatchTeamId;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
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

        public int getOverhaulType() {
            return overhaulType;
        }

        public void setOverhaulType(int overhaulType) {
            this.overhaulType = overhaulType;
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

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "cityUnitId='" + cityUnitId + '\'' +
                    ", cityUnitName='" + cityUnitName + '\'' +
                    ", constructionUnit='" + constructionUnit + '\'' +
                    ", constructionUnitId='" + constructionUnitId + '\'' +
                    ", createByName='" + createByName + '\'' +
                    ", dispatchTeam='" + dispatchTeam + '\'' +
                    ", dispatchTeamId='" + dispatchTeamId + '\'' +
                    ", endDate='" + endDate + '\'' +
                    ", lineName='" + lineName + '\'' +
                    ", lineVol='" + lineVol + '\'' +
                    ", overhaulType=" + overhaulType +
                    ", startDate='" + startDate + '\'' +
                    ", taskCode='" + taskCode + '\'' +
                    ", taskName='" + taskName + '\'' +
                    ", taskStatus=" + taskStatus +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RepairListBean{" +
                "total=" + total +
                ", maintainCnt=" + maintainCnt +
                ", techTransCnt=" + techTransCnt +
                ", overhaulCnt=" + overhaulCnt +
                ", records=" + records +
                '}';
    }
}
