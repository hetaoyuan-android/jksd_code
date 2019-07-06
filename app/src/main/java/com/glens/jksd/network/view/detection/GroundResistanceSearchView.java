package com.glens.jksd.network.view.detection;

import com.glens.jksd.network.PresentView;

public interface GroundResistanceSearchView extends PresentView {

    void onSuccess(Object bean, String resultMsg);

    void showDialog(String message);

    void hideDialog();

    void setGroundBottomText(int total, int handleCnt, int untreatedCnt);

    void setTaskType(int type);

    int geTaskType();

    void setTaskCode(String taskCode);

    String getTaskCode();

}
