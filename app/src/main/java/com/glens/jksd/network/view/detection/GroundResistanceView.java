package com.glens.jksd.network.view.detection;

import com.glens.jksd.network.PresentView;

public interface GroundResistanceView extends PresentView {

    void onSuccess(Object bean, String resultMsg);

    void showDialog(String message);

    void hideDialog();

    void setGroundBottomText(int total, int handleCnt, int untreatedCnt);

}
