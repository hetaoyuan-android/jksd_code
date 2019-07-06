package com.glens.jksd.bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/10.
 */
public class RepairTowerListBean {


    /**
     * asc : true
     * current : 1
     * limit : 2147483647
     * offset : 0
     * openSort : true
     * optimizeCountSql : true
     * pages : 2
     * records : [{"checkRecordCode":"1","createByName":"张亮","createTime":"2019-06-01 09:36","lineId":"1","lineName":"大树线","lineVol":"35","phase":"12","taskCode":"1","towerId":"1","towerNo":"001"},{"checkRecordCode":"bec32ea512e545c4b47685a982532a72","createByName":"王斌","createTime":"2019-05-28 17:20","lineId":"1","lineName":"冲冲冲线","lineVol":"35","phase":"1","taskCode":"1","towerId":"1","towerNo":"001"},{"checkRecordCode":"ad7841b0766348c49d307e23194297cf","createByName":"王斌","createTime":"2019-05-28 17:02","lineId":"1","lineName":"哈哈线","lineVol":"35","phase":"1","taskCode":"1","towerId":"1","towerNo":"001"},{"checkRecordCode":"e43c70ae733b471d9febdbbca38283fc","createByName":"王斌","createTime":"2019-05-28 16:53","lineId":"1","lineName":"吃吃11线","lineVol":"35","phase":"1","taskCode":"1","towerId":"1","towerNo":"001"},{"checkRecordCode":"7512d5b2ce7a4755aa15ae556a4056c1","createByName":"王斌","createTime":"2019-05-28 16:52","lineId":"1","lineName":"吃吃11线","lineVol":"35","phase":"1","taskCode":"1","towerId":"1","towerNo":"001"},
     * {"checkRecordCode":"1d06c68ade594bebb4661a031000ff81","createByName":"王斌","createTime":"2019-05-28 16:52","lineId":"1","lineName":"吃吃线","lineVol":"35","phase":"1","taskCode":"1","towerId":"1","towerNo":"001"},{"checkRecordCode":"a442dadf27b7405682713a9a3e833aa5","createByName":"王斌","createTime":"2019-05-28 16:47","lineId":"1","lineName":"测试线222","lineVol":"35","phase":"1","taskCode":"1","towerId":"1","towerNo":"001"},{"checkRecordCode":"4d4d60893dc5452994363b42b45c8f59","createByName":"王斌","createTime":"2019-05-28 16:47","lineId":"1","lineName":"测试线222","lineVol":"35","phase":"1","taskCode":"1","towerId":"1","towerNo":"001"},{"checkRecordCode":"05167d3f59d546209168fe9f3088a396","createByName":"王斌","createTime":"2019-05-28 16:46","lineId":"1","lineName":"测试线222","lineVol":"35","phase":"1","taskCode":"1","towerId":"1","towerNo":"001"},{"checkRecordCode":"7219d226c1584301b2e9bbcd9837161e","createByName":"王斌","createTime":"2019-05-28 16:37","lineId":"1","lineName":"测试线","lineVol":"35","phase":"1","taskCode":"1","towerId":"1","towerNo":"001"}]
     * searchCount : true
     * size : 10
     * total : 20
     */

    private boolean asc;
    private int current;
    private int limit;
    private int offset;
    private boolean openSort;
    private boolean optimizeCountSql;
    private int pages;
    private boolean searchCount;
    private int size;
    private int total;
    private List<RecordsBean> records;

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOpenSort() {
        return openSort;
    }

    public void setOpenSort(boolean openSort) {
        this.openSort = openSort;
    }

    public boolean isOptimizeCountSql() {
        return optimizeCountSql;
    }

    public void setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

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
//        {                //类型：Object  必有字段  备注：消缺列表
//            "lineVol":"35",                //类型：String  必有字段  备注：电压等级
//                "lineId":"1",                //类型：String  必有字段  备注：线路id
//                "lineName":"测试线",                //类型：String  必有字段  备注：线路名称
//                "towerId":"1",                //类型：String  必有字段  备注：杆塔id
//                "towerNo":"001",                //类型：String  必有字段  备注：杆塔编号
//                "defectCode":"2",                //类型：String  必有字段  备注：缺陷编号
//                "recordCode":"3",                //类型：String  必有字段  备注：消缺记录编号
//                "taskCode":"1",                //类型：String  必有字段  备注：任务编码
//                "createTime":"2019-05-29 11:08",                //类型：String  必有字段  备注：创建时间
//                "createByName":"张亮"                //类型：String  必有字段  备注：创建人
//        }
        /**
         * 登杆检查
         * checkRecordCode : 1
         * createByName : 张亮
         * createTime : 2019-06-01 09:36
         * lineId : 1
         * lineName : 大树线
         * lineVol : 35
         * phase : 12
         * taskCode : 1
         * towerId : 1
         * towerNo : 001
         */

        private String checkRecordCode;
        private String createByName;
        private String createTime;
        private String lineId;
        private String lineName;
        private String lineVol;
        private String phase;
        private String taskCode;
        private String towerId;
        private String towerNo;

        public String getCheckRecordCode() {
            return checkRecordCode;
        }

        public void setCheckRecordCode(String checkRecordCode) {
            this.checkRecordCode = checkRecordCode;
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

        public String getLineId() {
            return lineId;
        }

        public void setLineId(String lineId) {
            this.lineId = lineId;
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

        public String getPhase() {
            return phase;
        }

        public void setPhase(String phase) {
            this.phase = phase;
        }

        public String getTaskCode() {
            return taskCode;
        }

        public void setTaskCode(String taskCode) {
            this.taskCode = taskCode;
        }

        public String getTowerId() {
            return towerId;
        }

        public void setTowerId(String towerId) {
            this.towerId = towerId;
        }

        public String getTowerNo() {
            return towerNo;
        }

        public void setTowerNo(String towerNo) {
            this.towerNo = towerNo;
        }

        private String defectCode;
        private String recordCode;

        public String getDefectCode() {
            return defectCode;
        }

        public void setDefectCode(String defectCode) {
            this.defectCode = defectCode;
        }

        public String getRecordCode() {
            return recordCode;
        }

        public void setRecordCode(String recordCode) {
            this.recordCode = recordCode;
        }
        private String sprayManName;//喷涂人

        public String getSprayManName() {
            return sprayManName;
        }

        public void setSprayManName(String sprayManName) {
            this.sprayManName = sprayManName;
        }

        private String sweeper;//清扫人

        public String getSweeper() {
            return sweeper;
        }

        public void setSweeper(String sweeper) {
            this.sweeper = sweeper;
        }

        /**
         *   "startTowerId":"1",                //类型：String  必有字段  备注：无
         *                 "startTowerNo":"001",                //类型：String  必有字段  备注：无
         *                 "endTowerId":"2",                //类型：String  必有字段  备注：无
         *                 "endTowerNo":"002",
         * @return
         */
        private String startTowerId;
        private String startTowerNo;
        private String endTowerId;
        private String endTowerNo;

        public String getStartTowerId() {
            return startTowerId;
        }

        public void setStartTowerId(String startTowerId) {
            this.startTowerId = startTowerId;
        }

        public String getStartTowerNo() {
            return startTowerNo;
        }

        public void setStartTowerNo(String startTowerNo) {
            this.startTowerNo = startTowerNo;
        }

        public String getEndTowerId() {
            return endTowerId;
        }

        public void setEndTowerId(String endTowerId) {
            this.endTowerId = endTowerId;
        }

        public String getEndTowerNo() {
            return endTowerNo;
        }

        public void setEndTowerNo(String endTowerNo) {
            this.endTowerNo = endTowerNo;
        }

        private String soundRecordorName;
        private int soundType;

        public String getSoundName() {
            return soundName;
        }

        public void setSoundName(String soundName) {
            this.soundName = soundName;
        }

        private String soundName;
        private String isInspected;

        public String getIsInspected() {
            return isInspected;
        }

        public void setIsInspected(String isInspected) {
            this.isInspected = isInspected;
        }

        public int getSoundType() {
            return soundType;
        }

        public void setSoundType(int soundType) {
            this.soundType = soundType;
        }

        public String getSoundRecordorName() {
            return soundRecordorName;
        }

        public void setSoundRecordorName(String soundRecordorName) {
            this.soundRecordorName = soundRecordorName;
        }

        private String surveyManName;
        private String surveyUnitName;

        public String getSurveyManName() {
            return surveyManName;
        }

        public void setSurveyManName(String surveyManName) {
            this.surveyManName = surveyManName;
        }

        public String getSurveyUnitName() {
            return surveyUnitName;
        }

        public void setSurveyUnitName(String surveyUnitName) {
            this.surveyUnitName = surveyUnitName;
        }

        private String testManName;
        private String testConclusion;

        public String getTestManName() {
            return testManName;
        }

        public void setTestManName(String testManName) {
            this.testManName = testManName;
        }

        public String getTestConclusion() {
            return testConclusion;
        }

        public void setTestConclusion(String testConclusion) {
            this.testConclusion = testConclusion;
        }

        private String datumCode;//检修资料编号

        public String getDatumCode() {
            return datumCode;
        }

        public void setDatumCode(String datumCode) {
            this.datumCode = datumCode;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "checkRecordCode='" + checkRecordCode + '\'' +
                    ", createByName='" + createByName + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", lineId='" + lineId + '\'' +
                    ", lineName='" + lineName + '\'' +
                    ", lineVol='" + lineVol + '\'' +
                    ", phase='" + phase + '\'' +
                    ", taskCode='" + taskCode + '\'' +
                    ", towerId='" + towerId + '\'' +
                    ", towerNo='" + towerNo + '\'' +
                    ", defectCode='" + defectCode + '\'' +
                    ", recordCode='" + recordCode + '\'' +
                    ", sprayManName='" + sprayManName + '\'' +
                    ", sweeper='" + sweeper + '\'' +
                    ", startTowerId='" + startTowerId + '\'' +
                    ", startTowerNo='" + startTowerNo + '\'' +
                    ", endTowerId='" + endTowerId + '\'' +
                    ", endTowerNo='" + endTowerNo + '\'' +
                    ", soundRecordorName='" + soundRecordorName + '\'' +
                    ", soundType=" + soundType +
                    ", soundName='" + soundName + '\'' +
                    ", isInspected='" + isInspected + '\'' +
                    ", surveyManName='" + surveyManName + '\'' +
                    ", surveyUnitName='" + surveyUnitName + '\'' +
                    ", testManName='" + testManName + '\'' +
                    ", testConclusion='" + testConclusion + '\'' +
                    ", datumCode='" + datumCode + '\'' +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "RepairTowerListBean{" +
                "asc=" + asc +
                ", current=" + current +
                ", limit=" + limit +
                ", offset=" + offset +
                ", openSort=" + openSort +
                ", optimizeCountSql=" + optimizeCountSql +
                ", pages=" + pages +
                ", searchCount=" + searchCount +
                ", size=" + size +
                ", total=" + total +
                ", records=" + records +
                '}';
    }
}
