package com.glens.jksd.network.view.detection;

import com.glens.jksd.network.PresentView;

import java.util.HashMap;

public interface AddDetectionView extends PresentView {
    void onSuccess(Object bean, String resultMsg, boolean result);

    void showDialog(String message);

    void hideDialog();

    HashMap<String, Object> getDataMap();
}
