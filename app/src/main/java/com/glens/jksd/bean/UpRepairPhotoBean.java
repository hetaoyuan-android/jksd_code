package com.glens.jksd.bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/13.
 */
public class UpRepairPhotoBean {

    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * creatorId : 1
         * modifierId : 1
         * picCode : 1d4519fbd7c14dc8b271386073adc2bb
         * picType : 1
         * picUrl : data/images/ohm/2019-06-13/2a1ea6a0-af09-4ee2-82c5-7b1e3363c4ed.jpg
         * reportDeptId : 1
         * reportorId : 1
         * taskCode : 1,测试
         */

        private String creatorId;
        private String modifierId;
        private String picCode;
        private int picType;
        private String picUrl;
        private String reportDeptId;
        private String reportorId;
        private String taskCode;

        public String getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(String creatorId) {
            this.creatorId = creatorId;
        }

        public String getModifierId() {
            return modifierId;
        }

        public void setModifierId(String modifierId) {
            this.modifierId = modifierId;
        }

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

        public String getReportDeptId() {
            return reportDeptId;
        }

        public void setReportDeptId(String reportDeptId) {
            this.reportDeptId = reportDeptId;
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

        private String audioCode;
        private String audioUrl;

        public String getAudioCode() {
            return audioCode;
        }

        public void setAudioCode(String audioCode) {
            this.audioCode = audioCode;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "creatorId='" + creatorId + '\'' +
                    ", modifierId='" + modifierId + '\'' +
                    ", picCode='" + picCode + '\'' +
                    ", picType=" + picType +
                    ", picUrl='" + picUrl + '\'' +
                    ", reportDeptId='" + reportDeptId + '\'' +
                    ", reportorId='" + reportorId + '\'' +
                    ", taskCode='" + taskCode + '\'' +
                    ", audioCode='" + audioCode + '\'' +
                    ", audioUrl='" + audioUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UpRepairPhotoBean{" +
                "records=" + records +
                '}';
    }
}
