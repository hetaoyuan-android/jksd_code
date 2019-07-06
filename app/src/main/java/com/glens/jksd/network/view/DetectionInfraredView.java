package com.glens.jksd.network.view;

import com.glens.jksd.bean.ResistanceBean;
import com.glens.jksd.network.PresentView;

/**
 * Created by wkc on 2019/5/24.
 */
public interface DetectionInfraredView extends PresentView {
    void showToast(String info);

    void changeToItemActivity();

    void onSuccess(ResistanceBean bean, String resultMsg);

    void setBottomViewText(int total,int undo,int done);
}
