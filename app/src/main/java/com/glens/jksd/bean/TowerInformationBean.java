package com.glens.jksd.bean;

import java.io.Serializable;

public class TowerInformationBean implements Serializable {
    private String towerName;

    public String getTowerName() {
        return towerName;
    }

    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }

    @Override
    public String toString() {
        return "TowerInformationBean{" +
                "towerName='" + towerName + '\'' +
                '}';
    }
}
