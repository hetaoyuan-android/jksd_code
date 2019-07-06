package com.glens.jksd.bean.repair_bean;

import java.util.List;

/**
 * Created by wkc on 2019/7/1.
 */
public class InformationDetailBean {

    /**
     * audioList : [{"audioCode":"54f940d16bad4eeb8caac7ee74774277","audioType":1,"audioUrl":"data/audio/ohm/2019-07-01/8ad60aef-b8f5-413c-86c2-b9507cc4bbb8.mp4"}]
     * createTime : 2019-07-01 16:17
     * creatorId : 1
     * datumCode : 6224776d9a15490d99a7ee0f72a88050
     * modifierId : 1
     * picList : [{"picCode":"b82b5cab6efe4000bc6c56449669131f","picType":1,"picUrl":"data/images/ohm/2019-07-01/c0f2fb06-5870-47b4-89c5-2412ca0cc55a.jpg"}]
     * reportDeptId : 1
     * taskCode : d92a2500d8fa4267985a3fbfb7438698
     * updateTime : 2019-07-01 16:17
     */

    private String createTime;
    private String creatorId;
    private String datumCode;
    private String modifierId;
    private String reportDeptId;
    private String taskCode;
    private String updateTime;
    private List<AudioListBean> audioList;
    private List<PicListBean> picList;

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

    public String getDatumCode() {
        return datumCode;
    }

    public void setDatumCode(String datumCode) {
        this.datumCode = datumCode;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getReportDeptId() {
        return reportDeptId;
    }

    public void setReportDeptId(String reportDeptId) {
        this.reportDeptId = reportDeptId;
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

    public List<AudioListBean> getAudioList() {
        return audioList;
    }

    public void setAudioList(List<AudioListBean> audioList) {
        this.audioList = audioList;
    }

    public List<PicListBean> getPicList() {
        return picList;
    }

    public void setPicList(List<PicListBean> picList) {
        this.picList = picList;
    }

    public static class AudioListBean {
        /**
         * audioCode : 54f940d16bad4eeb8caac7ee74774277
         * audioType : 1
         * audioUrl : data/audio/ohm/2019-07-01/8ad60aef-b8f5-413c-86c2-b9507cc4bbb8.mp4
         */

        private String audioCode;
        private int audioType;
        private String audioUrl;

        public String getAudioCode() {
            return audioCode;
        }

        public void setAudioCode(String audioCode) {
            this.audioCode = audioCode;
        }

        public int getAudioType() {
            return audioType;
        }

        public void setAudioType(int audioType) {
            this.audioType = audioType;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        @Override
        public String toString() {
            return "AudioListBean{" +
                    "audioCode='" + audioCode + '\'' +
                    ", audioType=" + audioType +
                    ", audioUrl='" + audioUrl + '\'' +
                    '}';
        }
    }

    public static class PicListBean {
        /**
         * picCode : b82b5cab6efe4000bc6c56449669131f
         * picType : 1
         * picUrl : data/images/ohm/2019-07-01/c0f2fb06-5870-47b4-89c5-2412ca0cc55a.jpg
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
        return "InformationDetailBean{" +
                "createTime='" + createTime + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", datumCode='" + datumCode + '\'' +
                ", modifierId='" + modifierId + '\'' +
                ", reportDeptId='" + reportDeptId + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", audioList=" + audioList +
                ", picList=" + picList +
                '}';
    }
}
