package com.glens.jksd.bean.repair_bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/20.
 */
public class RecordDetailBean {

    /**
     * audioList : [{"audioCode":"f972a7141e014dab972415561411bef1","audioUrl":"data/audio/ohm/2019-06-04/b5b9f110-afcd-4cf3-9144-befe28ce73e7.mp3"}]
     * createTime : 2019-06-04 13:51
     * picList : [{"picCode":"74e5b647bc70441995263d166b86b9b5","picUrl":"data/images/ohm/2019-06-04/82027551-d605-40d2-a412-1c783f527bdc.jpg"}]
     * recordCode : bf00a38d82c446e68023cf3a0a940543
     * soundName : 测试
     * soundRecordorId : 1
     * soundRecordorName : 张亮
     * soundType : 1
     * taskCode : 1
     */

    private String createTime;
    private String recordCode;
    private String soundName;
    private String soundRecordorId;
    private String soundRecordorName;
    private int soundType;
    private String taskCode;
    private List<AudioListBean> audioList;
    private List<PicListBean> picList;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public String getSoundRecordorId() {
        return soundRecordorId;
    }

    public void setSoundRecordorId(String soundRecordorId) {
        this.soundRecordorId = soundRecordorId;
    }

    public String getSoundRecordorName() {
        return soundRecordorName;
    }

    public void setSoundRecordorName(String soundRecordorName) {
        this.soundRecordorName = soundRecordorName;
    }

    public int getSoundType() {
        return soundType;
    }

    public void setSoundType(int soundType) {
        this.soundType = soundType;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
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
         * audioCode : f972a7141e014dab972415561411bef1
         * audioUrl : data/audio/ohm/2019-06-04/b5b9f110-afcd-4cf3-9144-befe28ce73e7.mp3
         */

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
            return "AudioListBean{" +
                    "audioCode='" + audioCode + '\'' +
                    ", audioUrl='" + audioUrl + '\'' +
                    '}';
        }
    }

    public static class PicListBean {
        /**
         * picCode : 74e5b647bc70441995263d166b86b9b5
         * picUrl : data/images/ohm/2019-06-04/82027551-d605-40d2-a412-1c783f527bdc.jpg
         */

        private String picCode;
        private String picUrl;

        public String getPicCode() {
            return picCode;
        }

        public void setPicCode(String picCode) {
            this.picCode = picCode;
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
                    ", picUrl='" + picUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RecordDetailBean{" +
                "createTime='" + createTime + '\'' +
                ", recordCode='" + recordCode + '\'' +
                ", soundName='" + soundName + '\'' +
                ", soundRecordorId='" + soundRecordorId + '\'' +
                ", soundRecordorName='" + soundRecordorName + '\'' +
                ", soundType=" + soundType +
                ", taskCode='" + taskCode + '\'' +
                ", audioList=" + audioList +
                ", picList=" + picList +
                '}';
    }
}
