package com.glens.jksd.network.view;

import com.glens.jksd.bean.RepairListBean;
import com.glens.jksd.network.PresentView;

/**
 * Created by wkc on 2019/6/3.
 */
public interface RepairListView extends PresentView {

    void changeToItemActivity();

    void onSuccess(RepairListBean bean, String resultMsg);

    void setBottomViewText(int total,int overhaulCnt,int techTransCnt,int maintainCnt );

    void setTimeTaskSearch(String startTime,String endTime);

    void setTaskType(int type);

    int geTaskType();

}
