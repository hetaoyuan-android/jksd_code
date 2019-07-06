package com.glens.jksd.adapter.RepairTaskAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.bean.RepairTowerListBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepairCheckAdapter extends RecyclerView.Adapter<RepairCheckAdapter.MyViewHolder> {

    private RepairCheckAdapter.onRecyclerViewItemClickListener mItemClickListener = null;
    private List<RepairTowerListBean.RecordsBean> mList;
    private Context mContext;

    public RepairCheckAdapter(List<RepairTowerListBean.RecordsBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

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

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.repairCheckTime.setText(mList.get(position).getCreateTime() == null ? " " : mList.get(position).getCreateTime());
        myViewHolder.repairTaskCheck.setImageDrawable(mContext.getResources().getDrawable(R.drawable.repair_daily_maintenance));
        myViewHolder.repairTaskCheckName.setText(getCheckName(mList.get(position)));
        myViewHolder.repairTaskCheckUsername.setText(mList.get(position).getCreateByName() == null ? "" : mList.get(position).getCreateByName());
        myViewHolder.repairTaskCheckUsername.setText(getTextName(mList.get(position)));
        myViewHolder.mRyRepairCheck.setTag(position);
    }

    private String getTextName(RepairTowerListBean.RecordsBean recordsBean) {
        if (recordsBean.getSurveyManName() != null) {
            return recordsBean.getSurveyManName();
        } else if (recordsBean.getCreateByName() != null) {
            return recordsBean.getCreateByName();
        }
        return "";
    }

    private String getCheckName(RepairTowerListBean.RecordsBean recordsBean) {
        if (recordsBean != null && recordsBean.getLineVol() != null && recordsBean.getLineName() != null && recordsBean.getTowerNo() != null) {
            return recordsBean.getLineVol() + "KV" + recordsBean.getLineName() + "线" + recordsBean.getTowerNo() + "杆";
        } else if (recordsBean != null && recordsBean.getLineVol() != null && recordsBean.getLineName() != null && recordsBean.getStartTowerNo() != null) {
            return recordsBean.getLineVol() + "KV" + recordsBean.getLineName() + "线" + recordsBean.getStartTowerNo() + "杆";
        } else {
            return "";
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(RepairCheckAdapter.onRecyclerViewItemClickListener listener) {
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
        @Bind(R.id.ry_repair_check)
        RelativeLayout mRyRepairCheck;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
