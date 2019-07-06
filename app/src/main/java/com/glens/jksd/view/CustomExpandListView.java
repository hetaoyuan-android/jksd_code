package com.glens.jksd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by daiMaGe on 2017/5/5.
 * 重写ExpandableListView以解决ScrollView嵌套ExpandableListView
 * 导致ExpandableListView显示不正常的问题
 */

public class CustomExpandListView extends ExpandableListView {

    public CustomExpandListView(Context context) {
        super(context);
    }

    public CustomExpandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomExpandListView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 1,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
