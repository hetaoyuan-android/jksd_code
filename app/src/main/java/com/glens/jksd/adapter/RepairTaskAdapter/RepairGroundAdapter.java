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
import com.glens.jksd.bean.repair_task_bean.RepairGroundBean;
import com.glens.jksd.utils.RepairConstantUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepairGroundAdapter extends RecyclerView.Adapter<RepairGroundAdapter.MyViewHolder> {
    private onRecyclerViewItemClickListener mItemClickListener = null;
    private List<RepairGroundBean.RecordsBean> mList;
    private Context mContext;

    public RepairGroundAdapter(List<RepairGroundBean.RecordsBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
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
    public void setData(List<RepairGroundBean.RecordsBean> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    @NonNull
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
        myViewHolder.repairTaskCheck.setImageDrawable(mContext.getResources().getDrawable(R.drawable.repair_daily_maintenance));
        myViewHolder.repairTaskCheckName.setText(mList.get(position).getGroudWireName() == null ? " " : mList.get(position).getGroudWireName());
        myViewHolder.repairTaskCheckUsername.setText(mList.get(position).getTakenPerson() == null ? "" : mList.get(position).getTakenPerson());
//        myViewHolder.repairDrawableText.setText(mList.get(position).getGroudWireType() == null ? "" : mList.get(position).getDepartment());
        setDrawableBackground(myViewHolder.repairDrawableText,mList.get(position).getGroudWireType());
        myViewHolder.mRyRepairCheck.setTag(position);
    }

    private void setDrawableBackground(TextView repairDrawableText, int type) {
        switch (type) {
            case RepairConstantUtils.REPAIR_ONE:
                repairDrawableText.setText(RepairConstantUtils.TASK_GROUND_ACCEPT);
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_blue));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_blue_stroke));
                break;
            case RepairConstantUtils.REPAIR_TWO:
                repairDrawableText.setText(RepairConstantUtils.TASK_GROUND_CHECK);
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_red));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_red_stroke));
                break;
            case RepairConstantUtils.REPAIR_THREE:
                repairDrawableText.setText(RepairConstantUtils.TASK_GROUND_HUNG);
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_orange));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_orange_stroke));
                break;
            case RepairConstantUtils.REPAIR_FOUR:
                repairDrawableText.setText(RepairConstantUtils.TASK_GROUND_BROKEN);
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.tv_drawable_orange));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_orange_stroke));
                break;
            case RepairConstantUtils.REPAIR_FIVE:
                repairDrawableText.setText(RepairConstantUtils.TASK_GROUND_RETURN);
                repairDrawableText.setTextColor(mContext.getResources().getColor(R.color.text_green));
                repairDrawableText.setBackground(mContext.getResources().getDrawable(R.drawable.tv_green_stroke));
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

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.repair_task_check)
        ImageView repairTaskCheck;
        @Bind(R.id.repair_task_check_name)
        TextView repairTaskCheckName;
        @Bind(R.id.repair_drawable_text)
        TextView repairDrawableText;
        @Bind(R.id.repair_check_time)
        TextView repairCheckTime;
        @Bind(R.id.repair_task_check_username)
        TextView repairTaskCheckUsername;
        @Bind(R.id.ry_item_repair_ground)
        RelativeLayout mRyRepairCheck;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
