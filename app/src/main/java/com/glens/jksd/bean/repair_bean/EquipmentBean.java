package com.glens.jksd.bean.repair_bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wkc on 2019/6/21.
 */
public class EquipmentBean implements Serializable {

    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable{
        /**
         * lineId : 2
         * lineName : 红黑线
         * lineVol : 35
         * tower : [{"towerId":"5","towerNo":"005"}]
         */

        private String lineId;
        private String lineName;
        private String lineVol;
        private List<TowerBean> tower;

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

        public List<TowerBean> getTower() {
            return tower;
        }

        public void setTower(List<TowerBean> tower) {
            this.tower = tower;
        }

        public static class TowerBean implements Serializable{
            /**
             * towerId : 5
             * towerNo : 005
             */

            private String towerId;
            private String towerNo;

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

            @Override
            public String toString() {
                return "TowerBean{" +
                        "towerId='" + towerId + '\'' +
                        ", towerNo='" + towerNo + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "lineId='" + lineId + '\'' +
                    ", lineName='" + lineName + '\'' +
                    ", lineVol='" + lineVol + '\'' +
                    ", tower=" + tower +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "EquipmentBean{" +
                "records=" + records +
                '}';
    }
}
