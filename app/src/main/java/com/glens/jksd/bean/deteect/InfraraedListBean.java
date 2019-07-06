package com.glens.jksd.bean.deteect;

import java.util.List;

/**
 *  红外测温列表Javabean
 */
public class InfraraedListBean {


    /**
     * records : [{"detectionTask":{"exeUnitName":"电缆室","executorName":"张亮","startDate":"2019-05-15","endDate":"2019-05-16"},"isInspected":"1","lineId":"1","lineName":"大树线","lineVol":"35","recodeCode":"1","taskCode":"1","towerId":"1","towerNo":"001","temperatureRecord":{"recodeCode":"mock","maxTemperature":"28.5","normalTemperature":"26.6","ambientTemperature":"25.6","createTime":"mock"}}]
     * total : 1
     * handleCnt : 1
     * untreatedCnt : 0
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

    public static class RecordsBean {
        /**
         * detectionTask : {"exeUnitName":"电缆室","executorName":"张亮","startDate":"2019-05-15","endDate":"2019-05-16"}
         * isInspected : 1
         * lineId : 1
         * lineName : 大树线
         * lineVol : 35
         * recodeCode : 1
         * taskCode : 1
         * towerId : 1
         * towerNo : 001
         * temperatureRecord : {"recodeCode":"mock","maxTemperature":"28.5","normalTemperature":"26.6","ambientTemperature":"25.6","createTime":"mock"}
         */

        private DetectionTaskBean detectionTask;
        private String isInspected;
        private String lineId;
        private String lineName;
        private String lineVol;
        private String recodeCode;
        private String taskCode;
        private String towerId;
        private String towerNo;
        private TemperatureRecordBean temperatureRecord;

        public DetectionTaskBean getDetectionTask() {
            return detectionTask;
        }

        public void setDetectionTask(DetectionTaskBean detectionTask) {
            this.detectionTask = detectionTask;
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

        public TemperatureRecordBean getTemperatureRecord() {
            return temperatureRecord;
        }

        public void setTemperatureRecord(TemperatureRecordBean temperatureRecord) {
            this.temperatureRecord = temperatureRecord;
        }

        public static class DetectionTaskBean {
            /**
             * exeUnitName : 电缆室
             * executorName : 张亮
             * startDate : 2019-05-15
             * endDate : 2019-05-16
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

        public static class TemperatureRecordBean {
            /**
             * recodeCode : mock
             * maxTemperature : 28.5
             * normalTemperature : 26.6
             * ambientTemperature : 25.6
             * createTime : mock
             */

            private String recodeCode;
            private String maxTemperature;
            private String normalTemperature;
            private String ambientTemperature;
            private String createTime;

            public String getRecodeCode() {
                return recodeCode;
            }

            public void setRecodeCode(String recodeCode) {
                this.recodeCode = recodeCode;
            }

            public String getMaxTemperature() {
                return maxTemperature;
            }

            public void setMaxTemperature(String maxTemperature) {
                this.maxTemperature = maxTemperature;
            }

            public String getNormalTemperature() {
                return normalTemperature;
            }

            public void setNormalTemperature(String normalTemperature) {
                this.normalTemperature = normalTemperature;
            }

            public String getAmbientTemperature() {
                return ambientTemperature;
            }

            public void setAmbientTemperature(String ambientTemperature) {
                this.ambientTemperature = ambientTemperature;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
