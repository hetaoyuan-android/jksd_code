package com.glens.jksd.utils;

/**
 * Created by wkc on 2019/6/27.
 */
public class RepairDataUtils {
    public static String getPhase(String type) {
        String phase;
        switch (type) {
            case RepairConstantUtils.POWER_ZERO:
                phase = "A";
                break;
            case RepairConstantUtils.POWER_ONE:
                phase = "B";
                break;
            case RepairConstantUtils.POWER_TWO:
                phase = "C";
                break;
            default:
                phase = "";
                break;
        }
        return phase;
    }

}
