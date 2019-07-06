package com.glens.jksd.base;

import android.support.annotation.CheckResult;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.glens.jksd.R;
import com.glens.jksd.view.CustomerLayoutManager;
import com.glens.jksd.view.ISwipeRefreshView;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public abstract  class BaseSwipeRefreshActivity extends BaseActivity implements ISwipeRefreshView {
    private static final String TAG = BaseSwipeRefreshActivity.class.getSimpleName();
    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.rv_refresh_layout)
    protected RecyclerView rvRefreshLayout;
    protected boolean mHasMoreData = true;
    protected CustomerLayoutManager layoutManager;
    protected void initSwipeLayout(){
        Log.d(TAG, "initSwipeLayout: ");
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: prepareRefresh = "+prepareRefresh());
                if (prepareRefresh()) {
                    onRefreshStarted();
                } else {
                    //产生一个加载数据的假象
                    hideRefresh();
                }
            }
        });
    }

    /**
     * check data status
     * @return return true indicate it should load data really else indicate don't refresh
     */
    protected boolean prepareRefresh(){
        return true;
    }

    /**
     * the method of get data
     */
    protected abstract void onRefreshStarted();

    @Override
    public void hideRefresh() {
        Log.d(TAG, "hideRefresh: ");
        // 防止刷新消失太快，让子弹飞一会儿. do not use lambda!!
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(swipeRefreshLayout != null){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        },1000);
    }

    @Override
    public void showRefresh() {
        Log.d(TAG, "showRefresh: ");
        swipeRefreshLayout.setRefreshing(true);
    }

    /**
     * check refresh item_orderMonitor is refreshing
     * @return if the refresh item_orderMonitor is refreshing return true else return false
     */
    @CheckResult
    protected boolean isRefreshing(){
        Log.d(TAG, "isRefreshing: ");
        return swipeRefreshLayout.isRefreshing();
    }

    @Override
    public void getDataFinish() {
        Log.d(TAG, "getDataFinish: ");
        hideRefresh();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        rvRefreshLayout.setAdapter(null);
    }

    /**
     * the flag of has more data or not
     */
    protected void hasNoMoreData() {
        mHasMoreData = false;
        Snackbar.make(rvRefreshLayout, R.string.load_no_more_data, Snackbar.LENGTH_LONG)
                .setAction(R.string.load_top, v -> (rvRefreshLayout.getLayoutManager()).smoothScrollToPosition(rvRefreshLayout,null,0))
                .show();
    }

    protected void initRefreshLayout(){
        layoutManager = new CustomerLayoutManager(rvRefreshLayout.getContext());
        this.rvRefreshLayout.setLayoutManager(layoutManager);
    }
}
