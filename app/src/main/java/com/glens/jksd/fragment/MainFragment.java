package com.glens.jksd.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_detect.DetectionManagerActivity;
import com.glens.jksd.activity.activity_repair.RepairTaskActivity;
import com.glens.jksd.activity.activity_task_manage.TaskManageActivity;
import com.glens.jksd.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainFragment extends BaseFragment {

    private static final String TAG = MainFragment.class.getSimpleName();//"MainFragment"
    private GridView mGrid_view;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;


    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_common_frame, null);
        mGrid_view = (GridView) view.findViewById(R.id.grid_view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initData() {
        String[] from = {"img", "text"};
        int[] to = {R.id.img, R.id.text};
        //图标
        int icno[] = {R.drawable.jiance, R.drawable.jianxiu, R.drawable.gongcheng,
                R.drawable.zhouqi, R.drawable.teshu, R.drawable.zhishou,
                R.drawable.quexian, R.drawable.yinhuan};
        //图标下的文字
        String name[] = {"检测管理", "检修任务", "工程管理", "周期巡视", "特殊巡视", "值守巡视", "缺陷管理", "隐患管理"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icno.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
        adapter = new SimpleAdapter(getActivity(), dataList, R.layout.item_main_gridview, from, to);
        mGrid_view.setAdapter(adapter);
        mGrid_view.setBackground(mContext.getResources().getDrawable(R.drawable.main_bg_gridview));
        mGrid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent detectIntent = new Intent(getActivity(), DetectionManagerActivity.class);
                        startActivity(detectIntent);
                        break;
                    case 1:
                        Intent repairIntent = new Intent(getActivity(), RepairTaskActivity.class);
                        startActivity(repairIntent);
                        break;
                    case 2:
                        Intent taskIntent = new Intent(getActivity(), TaskManageActivity.class);
                        startActivity(taskIntent);
                        break;


                }
            }
        });
    }

}
