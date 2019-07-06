package com.glens.jksd.adapter.RepairTaskAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.bean.RepairTowerListBean;
import com.glens.jksd.utils.RepairConstantUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepairStandardTaskAdapter extends RecyclerView.Adapter<RepairStandardTaskAdapter.MyViewHolder> {


    private onRecyclerViewItemClickListener mItemClickListener = null;
    private List<RepairTowerListBean.RecordsBean> mList;
    private Context mContext;

    public RepairStandardTaskAdapter(List<RepairTowerListBean.RecordsBean> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_repair_check, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener((v) -> {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, mList.get((int) view.getTag()));
            }
        });
        return holder;
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClick(View v, Object bean);
    }

    /**
     * 设置数据  刷新界面
     *
     * @param data 数据源
     */
    public void setData(List<RepairTowerListBean.RecordsBean> data) {
        this.mList = data;
        notifyDataSetChanged();
        Log.e("数据源改变", data.toString());
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder myViewHolder, int position) {
        myViewHolder.repairCheckTime.setText(mList.get(position).getCreateTime() == null ? " " : mList.get(position).getCreateTime());
        myViewHolder.repairTaskCheck.setImageDrawable(mContext.getResources().getDrawable(R.drawable.repair_daily_maintenance));
        myViewHolder.repairTaskCheckName.setText(getCheckName(mList.get(position)));
        myViewHolder.repairTaskCheckUsername.setText(getUserName(mList.get(position)));
        setRepairTaskState(mList.get(position),myViewHolder);
        myViewHolder.mRyRepairCheck.setTag(position);
    }

    private void setRepairTaskState(RepairTowerListBean.RecordsBean recordsBean, MyViewHolder myViewHolder) {
        if (recordsBean.getIsInspected() != null) {
            myViewHolder.repairTaskState.setVisibility(View.VISIBLE);
            getTaskType(recordsBean.getIsInspected(),myViewHolder.repairTaskState);
        }
    }

    private String getUserName(RepairTowerListBean.RecordsBean recordsBean) {
        if (!TextUtils.isEmpty(recordsBean.getSprayManName())) {
            return recordsBean.getSprayManName();
        }else if (!TextUtils.isEmpty(recordsBean.getTestManName())) {
            return recordsBean.getTestManName();
        } else if (!TextUtils.isEmpty(recordsBean.getCreateByName())) {
            return recordsBean.getCreateByName();
        }
        return "";
    }

    private String getCheckName(RepairTowerListBean.RecordsBean recordsBean) {
        if (recordsBean != null && recordsBean.getLineVol() != null && recordsBean.getLineName() != null && recordsBean.getTowerNo() != null) {
            return recordsBean.getLineVol() + "KV" + recordsBean.getLineName()  + recordsBean.getTowerNo() + "杆";
        } else if (recordsBean != null && recordsBean.getLineVol() != null && recordsBean.getLineName() != null && recordsBean.getStartTowerNo() != null) {
            return recordsBean.getLineVol() + "KV" + recordsBean.getLineName()  + recordsBean.getStartTowerNo() + "杆";
        } else {
            return "";
        }
    }

    private void getTaskType(String soundType, TextView repairDrawableText) {
        String type;
        switch (soundType) {
            case RepairConstantUtils.TASK_REPAIR_UNDO:
                type = RepairConstantUtils.TASK_REPAIR_UNDO_TEXT;
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_red));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_red_stroke));
                break;
            case RepairConstantUtils.TASK_REPAIR_DOWN:
                type = RepairConstantUtils.TASK_REPAIR_DOWN_TEXT;
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_blue));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_blue_stroke));
                break;
            default:
                type = "";

        }
        repairDrawableText.setText(type);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.repair_task_check)
        ImageView repairTaskCheck;
        @Bind(R.id.repair_task_check_name)
        TextView repairTaskCheckName;
        @Bind(R.id.repair_check_time)
        TextView repairCheckTime;
        @Bind(R.id.repair_task_check_username)
        TextView repairTaskCheckUsername;
        @Bind(R.id.repair_task_state)
        TextView repairTaskState;
        @Bind(R.id.ry_repair_check)
        RelativeLayout mRyRepairCheck;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
