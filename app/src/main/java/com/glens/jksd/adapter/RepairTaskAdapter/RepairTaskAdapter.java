package com.glens.jksd.adapter.RepairTaskAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.bean.RepairListBean;
import com.glens.jksd.utils.RepairConstantUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepairTaskAdapter extends RecyclerView.Adapter<RepairTaskAdapter.MyViewHolder> {
    private onRecyclerViewItemClickListener itemClickListener = null;
    private RepairListBean mRepairListBean;
    private Context context;

    public RepairTaskAdapter(RepairListBean list, Context context) {
        this.mRepairListBean = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_repair_task, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener((v) -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, mRepairListBean.getRecords().get((int) view.getTag()));
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        if (mRepairListBean.getRecords().get(position) != null) {
            RepairListBean.RecordsBean recordsBean = mRepairListBean.getRecords().get(position);
            switch (recordsBean.getOverhaulType()) {//1 技改，2大修，3 日常
                case RepairConstantUtils.DETECTION_GROUND_RESISTANCE:
                    myViewHolder.mItemRepairTaskImage.setImageResource(R.drawable.technical_renovation);
                    break;
                case RepairConstantUtils.DETECTION_INFRARDE:
                    myViewHolder.mItemRepairTaskImage.setImageResource(R.drawable.repair_overhaul);
                    break;
                case RepairConstantUtils.DETECTION_PAY_ACROSS_MEASURE:
                    myViewHolder.mItemRepairTaskImage.setImageResource(R.drawable.repair_daily_maintenance);
                    break;
                default:
                    Log.e("RepairTaskAdapter", "非正常工单参数类型");
                    break;
            }
            myViewHolder.mTvRepairTitle.setText(recordsBean.getTaskName() == null ? "" : recordsBean.getTaskName());
            myViewHolder.mItemRepairUser.setText(recordsBean.getCreateByName() == null ? "" : recordsBean.getCreateByName());
            myViewHolder.mItemRepairTaskDepartment.setText(recordsBean.getDispatchTeam() == null ? "" : recordsBean.getDispatchTeam());
            myViewHolder.mItemRepairTaskTime.setText(recordsBean.getStartDate() == null || recordsBean.getEndDate() == null ?
                    "" : String.valueOf(recordsBean.getStartDate() + "—" + recordsBean.getEndDate()));


            switch (recordsBean.getTaskStatus()) {
                case RepairConstantUtils.REPAIR_CREATE:
                    myViewHolder.mTvRepairTaskState.setText("创建");
                    myViewHolder.mTvRepairTaskState.setBackgroundColor(context.getResources().getColor(R.color.repairCreateBg));
                    myViewHolder.mTvRepairTaskState.setTextColor(context.getResources().getColor(R.color.repairCreateText));
                    break;
                case RepairConstantUtils.REPAIR_DOING:
                    myViewHolder.mTvRepairTaskState.setText("执行中");
                    myViewHolder.mTvRepairTaskState.setBackgroundColor(context.getResources().getColor(R.color.repairSendBg));
                    myViewHolder.mTvRepairTaskState.setTextColor(context.getResources().getColor(R.color.repairSendText));
                    break;
                case RepairConstantUtils.REPAIR_DONE:
                    myViewHolder.mTvRepairTaskState.setText("已完成");
                    myViewHolder.mTvRepairTaskState.setBackgroundColor(context.getResources().getColor(R.color.repairStartBg));
                    myViewHolder.mTvRepairTaskState.setTextColor(context.getResources().getColor(R.color.repairStartText));
                    break;
//                case RepairConstantUtils.REPAIR_DOWN:
//                    myViewHolder.mTvRepairTaskState.setText("收工");
//                    myViewHolder.mTvRepairTaskState.setBackgroundColor(context.getResources().getColor(R.color.repairDownBg));
//                    myViewHolder.mTvRepairTaskState.setTextColor(context.getResources().getColor(R.color.repairDownText));
//
//                    break;
//                case RepairConstantUtils.REPAIR_END:
//                    myViewHolder.mTvRepairTaskState.setText("结束");
//                    myViewHolder.mTvRepairTaskState.setBackgroundColor(context.getResources().getColor(R.color.view_bg));
//                    myViewHolder.mTvRepairTaskState.setTextColor(context.getResources().getColor(R.color.repairEndText));
//
//                    break;
                default:
                    Log.e("RepairTaskAdapter", "非正常工单状态");
                    break;
            }
        }
        myViewHolder.mRepairTaskItem.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mRepairListBean.getRecords().size();
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClick(View v, Object bean);
    }

    /**
     * 设置数据  刷新界面
     *
     * @param data 数据源
     */
    public void setData(RepairListBean data) {
        this.mRepairListBean = data;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_repair_task_image)
        ImageView mItemRepairTaskImage;
        @Bind(R.id.tv_repair_title)
        TextView mTvRepairTitle;
        @Bind(R.id.item_repair_task_name)
        TextView mItemRepairUser;
        @Bind(R.id.item_repair_task_department)
        TextView mItemRepairTaskDepartment;
        @Bind(R.id.item_repair_task_time)
        TextView mItemRepairTaskTime;
        @Bind(R.id.tv_repair_task_state)
        TextView mTvRepairTaskState;
//        @Bind(R.id.iv_task_undo)
//        ImageView mIvTaskUndo;
        @Bind(R.id.repair_task_item)
        RelativeLayout mRepairTaskItem;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
