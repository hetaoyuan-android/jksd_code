package com.glens.jksd.bean.repair_bean;

import java.util.List;

/**
 * Created by wkc on 2019/6/18.
 */
public class DefectDetailBean {

    /**
     * defect : {"dydj":"35","dzorxlmc":"测试线","qxnr":"缺陷内容111111","towerNo":"001"}
     * defectCode : 1
     * lineId : 1
     * lineName : 测试线
     * lineVol : 35
     * picList : []
     * recordCode : d7afce2b7fe24d44a8f46b5ec021527a
     * taskCode : 1
     * textName : 内容描述
     * towerId : 1
     * towerNo : 001
     */

    private DefectBean defect;
    private String defectCode;
    private String lineId;
    private String lineName;
    private String lineVol;
    private String recordCode;
    private String taskCode;
    private String textName;
    private String towerId;
    private String towerNo;
    private List<PicListBean> picList;

    public DefectBean getDefect() {
        return defect;
    }

    public void setDefect(DefectBean defect) {
        this.defect = defect;
    }

    public String getDefectCode() {
        return defectCode;
    }

    public void setDefectCode(String defectCode) {
        this.defectCode = defectCode;
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

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
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

    public List<PicListBean> getPicList() {
        return picList;
    }

    public void setPicList(List<PicListBean> picList) {
        this.picList = picList;
    }
    public static class PicListBean {
        /**
         * picCode : df1144ec8bcb431a937353e325fd9908
         * picUrl : data/images/ohm/2019-05-28/a1248eb1-599c-4006-a0ad-7cbfebaab33f.jpg
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

    public static class DefectBean {
        /**
         * dydj : 35
         * dzorxlmc : 测试线
         * qxnr : 缺陷内容111111
         * towerNo : 001
         */

        private String dydj;
        private String dzorxlmc;
        private String qxnr;
        private String towerNo;

        public String getDydj() {
            return dydj;
        }

        public void setDydj(String dydj) {
            this.dydj = dydj;
        }

        public String getDzorxlmc() {
            return dzorxlmc;
        }

        public void setDzorxlmc(String dzorxlmc) {
            this.dzorxlmc = dzorxlmc;
        }

        public String getQxnr() {
            return qxnr;
        }

        public void setQxnr(String qxnr) {
            this.qxnr = qxnr;
        }

        public String getTowerNo() {
            return towerNo;
        }

        public void setTowerNo(String towerNo) {
            this.towerNo = towerNo;
        }

        @Override
        public String toString() {
            return "DefectBean{" +
                    "dydj='" + dydj + '\'' +
                    ", dzorxlmc='" + dzorxlmc + '\'' +
                    ", qxnr='" + qxnr + '\'' +
                    ", towerNo='" + towerNo + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DefectDetailBean{" +
                "defect=" + defect +
                ", defectCode='" + defectCode + '\'' +
                ", lineId='" + lineId + '\'' +
                ", lineName='" + lineName + '\'' +
                ", lineVol='" + lineVol + '\'' +
                ", recordCode='" + recordCode + '\'' +
                ", taskCode='" + taskCode + '\'' +
                ", textName='" + textName + '\'' +
                ", towerId='" + towerId + '\'' +
                ", towerNo='" + towerNo + '\'' +
                ", picList=" + picList +
                '}';
    }
}
