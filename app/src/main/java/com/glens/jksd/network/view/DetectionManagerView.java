package com.glens.jksd.network.view;

import com.glens.jksd.bean.ResistanceBean;
import com.glens.jksd.network.PresentView;

public interface DetectionManagerView extends PresentView {

    void showToast(String info);

    void changeToItemActivity();

    void onSuccess(ResistanceBean bean, String resultMsg);

    void setBottomViewText(int total,int infrared,int measure,int resistance );

    void setTimeTaskSearch(String time);

    void setTaskType(int type);

    int geTaskType();
}
