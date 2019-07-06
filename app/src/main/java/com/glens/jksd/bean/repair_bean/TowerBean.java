package com.glens.jksd.bean.repair_bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wkc on 2019/6/24.
 */
public class TowerBean implements Serializable {

    private List<RecordsBean> records;

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable{
        /**
         * towerNo : 003
         * towerId : 3
         */

        private String towerNo;
        private String towerId;

        public String getTowerNo() {
            return towerNo;
        }

        public void setTowerNo(String towerNo) {
            this.towerNo = towerNo;
        }

        public String getTowerId() {
            return towerId;
        }

        public void setTowerId(String towerId) {
            this.towerId = towerId;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "towerNo='" + towerNo + '\'' +
                    ", towerId='" + towerId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TowerBean{" +
                "records=" + records +
                '}';
    }
}
