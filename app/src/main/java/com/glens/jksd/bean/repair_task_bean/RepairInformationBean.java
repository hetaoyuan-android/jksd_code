package com.glens.jksd.bean.repair_task_bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/20.
 */
public class RepairInformationBean {


    /**
     * total : 1
     * records : [{"createTime":"2019-06-06 13:27","datumCode":"4800bbe774b24897820e38e800779e90","picList":[{"picCode":"2909cec101684b348899ca0807d7d69c","picType":1,"picUrl":"data/images/ohm/2019-06-06/f5930e48-e3c9-4dbe-8b3b-ecdf97d33472.jpg"},{"picCode":"448547e4315645b1a87d55862033789b","picType":1,"picUrl":"data/images/ohm/2019-06-06/00b86a22-1403-4190-b619-659e0e9c2506.jpg"}],"reportorId":"1","taskCode":"1"}]
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
         * createTime : 2019-06-06 13:27
         * datumCode : 4800bbe774b24897820e38e800779e90
         * picList : [{"picCode":"2909cec101684b348899ca0807d7d69c","picType":1,"picUrl":"data/images/ohm/2019-06-06/f5930e48-e3c9-4dbe-8b3b-ecdf97d33472.jpg"},{"picCode":"448547e4315645b1a87d55862033789b","picType":1,"picUrl":"data/images/ohm/2019-06-06/00b86a22-1403-4190-b619-659e0e9c2506.jpg"}]
         * reportorId : 1
         * taskCode : 1
         */

        private String createTime;
        private String datumCode;
        private String reportorId;
        private String taskCode;
        private String createByName;
        private List<PicListBean> picList;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDatumCode() {
            return datumCode;
        }

        public void setDatumCode(String datumCode) {
            this.datumCode = datumCode;
        }

        public String getReportorId() {
            return reportorId;
        }

        public void setReportorId(String reportorId) {
            this.reportorId = reportorId;
        }

        public String getTaskCode() {
            return taskCode;
        }

        public void setTaskCode(String taskCode) {
            this.taskCode = taskCode;
        }

        public List<PicListBean> getPicList() {
            return picList;
        }

        public void setPicList(List<PicListBean> picList) {
            this.picList = picList;
        }

        public String getCreateByName() {
            return createByName;
        }

        public void setCreateByName(String createByName) {
            this.createByName = createByName;
        }

        public static class PicListBean {
            /**
             * picCode : 2909cec101684b348899ca0807d7d69c
             * picType : 1
             * picUrl : data/images/ohm/2019-06-06/f5930e48-e3c9-4dbe-8b3b-ecdf97d33472.jpg
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
            return "RecordsBean{" +
                    "createTime='" + createTime + '\'' +
                    ", datumCode='" + datumCode + '\'' +
                    ", reportorId='" + reportorId + '\'' +
                    ", taskCode='" + taskCode + '\'' +
                    ", createByName='" + createByName + '\'' +
                    ", picList=" + picList +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RepairInformationBean{" +
                "total=" + total +
                ", records=" + records +
                '}';
    }
}
