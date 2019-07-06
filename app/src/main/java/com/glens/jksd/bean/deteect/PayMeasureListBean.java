package com.glens.jksd.bean.deteect;

import java.util.List;

public class PayMeasureListBean {

    /**
     * records : [{"detectionTask":{"exeUnitName":"王斌","executorName":"王斌","startDate":"2019-05-22","endDate":"2019-05-27"},"startTowerId":"1","startTowerNo":"001","endTowerId":"2","endTowerNo":"002","isInspected":"1","lineId":"1","lineName":"大树线","lineVol":"35","recodeCode":"666c4dda430140aabc731ca9b4c821cb","taskCode":"bef15f25a34b468e87b5b285cf628a99","measureRecord":{"createTime":"2019-05-23 11:43","recodeCode":"33fe1545ab6143e2ac8dc2fef835000b","crossDis":"550","crossType":"1","isQualified":"1","ambientTemperature":"25"}}]
     * total : 4
     * handleCnt : 2
     * untreatedCnt : 2
     */

    private int total;
    private int handleCnt;
    private int untreatedCnt;
    private List<RecordsBean> records;
    private int pages;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHandleCnt() {
        return handleCnt;
    }

    public void setHandleCnt(int handleCnt) {
        this.handleCnt = handleCnt;
    }

    public int getUntreatedCnt() {
        return untreatedCnt;
    }

    public void setUntreatedCnt(int untreatedCnt) {
        this.untreatedCnt = untreatedCnt;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "PayMeasureListBean{" +
                "total=" + total +
                ", handleCnt=" + handleCnt +
                ", untreatedCnt=" + untreatedCnt +
                ", records=" + records +
                '}';
    }

    public static class RecordsBean {
        /**
         * detectionTask : {"exeUnitName":"王斌","executorName":"王斌","startDate":"2019-05-22","endDate":"2019-05-27"}
         * startTowerId : 1
         * startTowerNo : 001
         * endTowerId : 2
         * endTowerNo : 002
         * isInspected : 1
         * lineId : 1
         * lineName : 大树线
         * lineVol : 35
         * recodeCode : 666c4dda430140aabc731ca9b4c821cb
         * taskCode : bef15f25a34b468e87b5b285cf628a99
         * measureRecord : {"createTime":"2019-05-23 11:43","recodeCode":"33fe1545ab6143e2ac8dc2fef835000b","crossDis":"550","crossType":"1","isQualified":"1","ambientTemperature":"25"}
         */

        private DetectionTaskBean detectionTask;
        private String startTowerId;
        private String startTowerNo;
        private String endTowerId;
        private String endTowerNo;
        private String isInspected;
        private String lineId;
        private String lineName;
        private String lineVol;
        private String recodeCode;
        private String taskCode;
        private MeasureRecordBean measureRecord;

        public DetectionTaskBean getDetectionTask() {
            return detectionTask;
        }

        public void setDetectionTask(DetectionTaskBean detectionTask) {
            this.detectionTask = detectionTask;
        }

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

        public String getIsInspected() {
            return isInspected;
        }

        public void setIsInspected(String isInspected) {
            this.isInspected = isInspected;
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

        public String getRecodeCode() {
            return recodeCode;
        }

        public void setRecodeCode(String recodeCode) {
            this.recodeCode = recodeCode;
        }

        public String getTaskCode() {
            return taskCode;
        }

        public void setTaskCode(String taskCode) {
            this.taskCode = taskCode;
        }

        public MeasureRecordBean getMeasureRecord() {
            return measureRecord;
        }

        public void setMeasureRecord(MeasureRecordBean measureRecord) {
            this.measureRecord = measureRecord;
        }

        public static class DetectionTaskBean {
            /**
             * exeUnitName : 王斌
             * executorName : 王斌
             * startDate : 2019-05-22
             * endDate : 2019-05-27
             */

            private String exeUnitName;
            private String executorName;
            private String startDate;
            private String endDate;

            public String getExeUnitName() {
                return exeUnitName;
            }

            public void setExeUnitName(String exeUnitName) {
                this.exeUnitName = exeUnitName;
            }

            public String getExecutorName() {
                return executorName;
            }

            public void setExecutorName(String executorName) {
                this.executorName = executorName;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "detectionTask=" + detectionTask +
                    ", startTowerId='" + startTowerId + '\'' +
                    ", startTowerNo='" + startTowerNo + '\'' +
                    ", endTowerId='" + endTowerId + '\'' +
                    ", endTowerNo='" + endTowerNo + '\'' +
                    ", isInspected='" + isInspected + '\'' +
                    ", lineId='" + lineId + '\'' +
                    ", lineName='" + lineName + '\'' +
                    ", lineVol='" + lineVol + '\'' +
                    ", recodeCode='" + recodeCode + '\'' +
                    ", taskCode='" + taskCode + '\'' +
                    ", measureRecord=" + measureRecord +
                    '}';
        }

        public static class MeasureRecordBean {
            /**
             * createTime : 2019-05-23 11:43
             * recodeCode : 33fe1545ab6143e2ac8dc2fef835000b
             * crossDis : 550
             * crossType : 1
             * isQualified : 1
             * ambientTemperature : 25
             */

            private String createTime;
            private String recodeCode;
            private String crossDis;
            private String crossType;
            private String isQualified;
            private String ambientTemperature;

            @Override
            public String toString() {
                return "MeasureRecordBean{" +
                        "createTime='" + createTime + '\'' +
                        ", recodeCode='" + recodeCode + '\'' +
                        ", crossDis='" + crossDis + '\'' +
                        ", crossType='" + crossType + '\'' +
                        ", isQualified='" + isQualified + '\'' +
                        ", ambientTemperature='" + ambientTemperature + '\'' +
                        '}';
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getRecodeCode() {
                return recodeCode;
            }

            public void setRecodeCode(String recodeCode) {
                this.recodeCode = recodeCode;
            }

            public String getCrossDis() {
                return crossDis;
            }

            public void setCrossDis(String crossDis) {
                this.crossDis = crossDis;
            }

            public String getCrossType() {
                return crossType;
            }

            public void setCrossType(String crossType) {
                this.crossType = crossType;
            }

            public String getIsQualified() {
                return isQualified;
            }

            public void setIsQualified(String isQualified) {
                this.isQualified = isQualified;
            }

            public String getAmbientTemperature() {
                return ambientTemperature;
            }

            public void setAmbientTemperature(String ambientTemperature) {
                this.ambientTemperature = ambientTemperature;
            }
        }
    }
}
