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
import com.glens.jksd.utils.RepairConstantUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepairRecordAdapter extends RecyclerView.Adapter<RepairRecordAdapter.MyViewHolder> {

    private onRecyclerViewItemClickListener mItemClickListener = null;
    private List<RepairTowerListBean.RecordsBean> mList;
    private Context mContext;

    public RepairRecordAdapter(List<RepairTowerListBean.RecordsBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_repair_ground, parent, false);
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
        myViewHolder.repairDrawableText.setText(getTaskType(mList.get(position).getSoundType(),myViewHolder.repairDrawableText));
//        setDrawableBackground(myViewHolder.repairDrawableText, mList.get(position).getDepartment());
        myViewHolder.repairTaskCheck.setImageDrawable(mContext.getResources().getDrawable(R.drawable.repair_daily_maintenance));
        myViewHolder.repairTaskCheckName.setText(mList.get(position).getSoundName() == null ? " " : mList.get(position).getSoundName());
        myViewHolder.repairTaskCheckUsername.setText(mList.get(position).getSoundRecordorName() == null ? "" : mList.get(position).getSoundRecordorName());
        myViewHolder.mRyRepairCheck.setTag(position);
    }

    private String getTaskType(int soundType,TextView repairDrawableText) {
        String type;
        switch (soundType){
            case RepairConstantUtils.START_WORK:
                type= "开工";
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_red));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_red_stroke));
                break;
            case RepairConstantUtils.DOWN_WORK:
                type = "收工";
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_blue));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_blue_stroke));
                break;
                default:
                    type= "";
        }
        return type;
    }




    private void setDrawableBackground(TextView repairDrawableText, String department) {
        department = department == null ? "空参" : department;
        switch (department) {
            case "研发":
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_blue));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_blue_stroke));
                break;
            case "行政":
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_red));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_red_stroke));
                break;
            case "后勤":
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_orange));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_orange_stroke));
                break;
            default:
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_blue));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_blue_stroke));
                break;
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
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
        @Bind(R.id.repair_drawable_text)
        TextView repairDrawableText;
        @Bind(R.id.ry_item_repair_ground)
        RelativeLayout mRyRepairCheck;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
