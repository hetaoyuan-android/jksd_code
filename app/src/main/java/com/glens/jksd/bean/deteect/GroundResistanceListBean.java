package com.glens.jksd.bean.deteect;

import java.util.List;

/**
 *  接地电阻列表
 */
public class GroundResistanceListBean {


    //    {
//        "data": - {                //类型：Object  必有字段  备注：无
//            "total":1,                //类型：Number  必有字段  备注：无
//            "handleCnt":1,                //类型：Number  必有字段  备注：无
//            "untreatedCnt":0,                //类型：Number  必有字段  备注：无
//            "records": - [                //类型：Array  必有字段  备注：无
//        - {                //类型：Object  必有字段  备注：无
//                "detectionTask": - {                //类型：Object  必有字段  备注：无
//            "endDate":"2019-06-20",                //类型：String  必有字段  备注：无
//            "exeUnitName":"xxxx",                //类型：String  必有字段  备注：无
//            "executorName":"zhangliang",                //类型：String  必有字段  备注：无
//            "startDate":"2019-05-29"                //类型：String  必有字段  备注：无
//                },
//        "isInspected":"0",                //类型：String  必有字段  备注：无
//            "lineId":"1",                //类型：String  必有字段  备注：无
//            "lineName":"信息线",                //类型：String  必有字段  备注：无
//            "lineVol":"35",                //类型：String  必有字段  备注：无
//            "recodeCode":"a6bdc3eac37d4f349a00bc43fa63ea14",                //类型：String  必有字段  备注：无
//            "resistance": - {                //类型：Object  必有字段  备注：无
//            "createTime":"2019-05-29 14:41",                //类型：String  必有字段  备注：无
//            "erAValue":"3",                //类型：String  必有字段  备注：无
//            "erBValue":"4",                //类型：String  必有字段  备注：无
//            "erCValue":"5",                //类型：String  必有字段  备注：无
//            "erDValue":"3",                //类型：String  必有字段  备注：无
//            "isResistorAQualified":1,                //类型：Number  必有字段  备注：无
//            "isResistorBQualified":1,                //类型：Number  必有字段  备注：无
//            "isResistorCQualified":1,                //类型：Number  必有字段  备注：无
//            "isResistorDQualified":1,                //类型：Number  必有字段  备注：无
//            "recodeCode":"f85168c8c9da497eb5c9a55fff87682a"                //类型：String  必有字段  备注：无
//                },
//        "taskCode":"d5aa2ae5f25046f1b20043f6b6adc9a9",                //类型：String  必有字段  备注：无
//            "towerId":"1",                //类型：String  必有字段  备注：无
//            "towerNo":"001"                //类型：String  必有字段  备注：无
//            }
//        ]
//    },
//        "httpCode":200,                //类型：Number  必有字段  备注：无
//            "msg":"操作成功！",                //类型：String  必有字段  备注：无
//            "result":true                //类型：Boolean  必有字段  备注：无
//    }

    /**
     * total : 1
     * handleCnt : 1
     * untreatedCnt : 0
     * records : [{"detectionTask":{"endDate":"2019-06-20","exeUnitName":"xxxx","executorName":"zhangliang","startDate":"2019-05-29"},"isInspected":"0","lineId":"1","lineName":"信息线","lineVol":"35","recodeCode":"a6bdc3eac37d4f349a00bc43fa63ea14","resistance":{"createTime":"2019-05-29 14:41","erAValue":"3","erBValue":"4","erCValue":"5","erDValue":"3","isResistorAQualified":1,"isResistorBQualified":1,"isResistorCQualified":1,"isResistorDQualified":1,"recodeCode":"f85168c8c9da497eb5c9a55fff87682a"},"taskCode":"d5aa2ae5f25046f1b20043f6b6adc9a9","towerId":"1","towerNo":"001"}]
     */

    private int total;
    private int handleCnt;
    private int untreatedCnt;
    private List<RecordsBean> records;
    private String pages;

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
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
         * detectionTask : {"endDate":"2019-06-20","exeUnitName":"xxxx","executorName":"zhangliang","startDate":"2019-05-29"}
         * isInspected : 0
         * lineId : 1
         * lineName : 信息线
         * lineVol : 35
         * recodeCode : a6bdc3eac37d4f349a00bc43fa63ea14
         * resistance : {"createTime":"2019-05-29 14:41","erAValue":"3","erBValue":"4","erCValue":"5","erDValue":"3","isResistorAQualified":1,"isResistorBQualified":1,"isResistorCQualified":1,"isResistorDQualified":1,"recodeCode":"f85168c8c9da497eb5c9a55fff87682a"}
         * taskCode : d5aa2ae5f25046f1b20043f6b6adc9a9
         * towerId : 1
         * towerNo : 001
         */

        private DetectionTaskBean detectionTask;
        private String isInspected;
        private String lineId;
        private String lineName;
        private String lineVol;
        private String recodeCode;
        private ResistanceBean resistance;
        private String taskCode;
        private String towerId;
        private String towerNo;

        public DetectionTaskBean getDetectionTask() {
            return detectionTask;
        }

        public void setDetectionTask(DetectionTaskBean detectionTask) {
            this.detectionTask = detectionTask;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "detectionTask=" + detectionTask +
                    ", isInspected='" + isInspected + '\'' +
                    ", lineId='" + lineId + '\'' +
                    ", lineName='" + lineName + '\'' +
                    ", lineVol='" + lineVol + '\'' +
                    ", recodeCode='" + recodeCode + '\'' +
                    ", resistance=" + resistance +
                    ", taskCode='" + taskCode + '\'' +
                    ", towerId='" + towerId + '\'' +
                    ", towerNo='" + towerNo + '\'' +
                    '}';
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

        public ResistanceBean getResistance() {
            return resistance;
        }

        public void setResistance(ResistanceBean resistance) {
            this.resistance = resistance;
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



        public static class DetectionTaskBean {
            /**
             * endDate : 2019-06-20
             * exeUnitName : xxxx
             * executorName : zhangliang
             * startDate : 2019-05-29
             */

            private String endDate;
            private String exeUnitName;
            private String executorName;
            private String startDate;

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

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

            @Override
            public String toString() {
                return "DetectionTaskBean{" +
                        "endDate='" + endDate + '\'' +
                        ", exeUnitName='" + exeUnitName + '\'' +
                        ", executorName='" + executorName + '\'' +
                        ", startDate='" + startDate + '\'' +
                        '}';
            }
        }

        public static class ResistanceBean {
            /**
             * createTime : 2019-05-29 14:41
             * erAValue : 3
             * erBValue : 4
             * erCValue : 5
             * erDValue : 3
             * isResistorAQualified : 1
             * isResistorBQualified : 1
             * isResistorCQualified : 1
             * isResistorDQualified : 1
             * recodeCode : f85168c8c9da497eb5c9a55fff87682a
             */

            private String createTime;
            private String erAValue;
            private String erBValue;
            private String erCValue;
            private String erDValue;
            private int isResistorAQualified;
            private int isResistorBQualified;
            private int isResistorCQualified;
            private int isResistorDQualified;
            private String recodeCode;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getErAValue() {
                return erAValue;
            }

            public void setErAValue(String erAValue) {
                this.erAValue = erAValue;
            }

            public String getErBValue() {
                return erBValue;
            }

            public void setErBValue(String erBValue) {
                this.erBValue = erBValue;
            }

            public String getErCValue() {
                return erCValue;
            }

            public void setErCValue(String erCValue) {
                this.erCValue = erCValue;
            }

            public String getErDValue() {
                return erDValue;
            }

            public void setErDValue(String erDValue) {
                this.erDValue = erDValue;
            }

            public int getIsResistorAQualified() {
                return isResistorAQualified;
            }

            public void setIsResistorAQualified(int isResistorAQualified) {
                this.isResistorAQualified = isResistorAQualified;
            }

            public int getIsResistorBQualified() {
                return isResistorBQualified;
            }

            public void setIsResistorBQualified(int isResistorBQualified) {
                this.isResistorBQualified = isResistorBQualified;
            }

            public int getIsResistorCQualified() {
                return isResistorCQualified;
            }

            public void setIsResistorCQualified(int isResistorCQualified) {
                this.isResistorCQualified = isResistorCQualified;
            }

            public int getIsResistorDQualified() {
                return isResistorDQualified;
            }

            public void setIsResistorDQualified(int isResistorDQualified) {
                this.isResistorDQualified = isResistorDQualified;
            }

            public String getRecodeCode() {
                return recodeCode;
            }

            public void setRecodeCode(String recodeCode) {
                this.recodeCode = recodeCode;
            }

            @Override
            public String toString() {
                return "ResistanceBean{" +
                        "createTime='" + createTime + '\'' +
                        ", erAValue='" + erAValue + '\'' +
                        ", erBValue='" + erBValue + '\'' +
                        ", erCValue='" + erCValue + '\'' +
                        ", erDValue='" + erDValue + '\'' +
                        ", isResistorAQualified=" + isResistorAQualified +
                        ", isResistorBQualified=" + isResistorBQualified +
                        ", isResistorCQualified=" + isResistorCQualified +
                        ", isResistorDQualified=" + isResistorDQualified +
                        ", recodeCode='" + recodeCode + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "GroundResistanceListBean{" +
                "total=" + total +
                ", handleCnt=" + handleCnt +
                ", untreatedCnt=" + untreatedCnt +
                ", records=" + records +
                '}';
    }
}
