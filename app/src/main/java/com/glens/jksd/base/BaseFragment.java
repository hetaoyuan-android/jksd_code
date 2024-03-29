package com.glens.jksd.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {
    /**
     * 上下文
     */
    protected Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 强制子类重写，实现子类特有的ui
     * @return view
     */
    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    /**
     * 当孩子需要初始化数据，或者联网请求绑定数据，展示数据的 等等可以重写该方法
     */
    protected void initData() {

    }

    public void showSvpDilog(Context context,String title, boolean isOnOtherClickDismiss, String showOther, DialogInterface.OnDismissListener listener) {
        if ((getActivity()) != null) {
            ((BaseActivity) getActivity()).showSvpDilog(context,title, isOnOtherClickDismiss,showOther,listener);
        }
    }

    /**
     * 消除dialog
     *
     * @param time 持续多长时间消失
     */
    public void dismissSvpDialog(int time) {
        if (((BaseActivity) getActivity()) != null) {
            ((BaseActivity) getActivity()).dismissSvpDilog(time);
        }
    }

}
