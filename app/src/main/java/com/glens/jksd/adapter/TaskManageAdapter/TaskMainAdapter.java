package com.glens.jksd.adapter.TaskManageAdapter;

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
import com.glens.jksd.bean.RepairTaskListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TaskMainAdapter extends RecyclerView.Adapter<TaskMainAdapter.MyViewHolder> {

    private onRecyclerViewItemClickListener itemClickListener = null;
    private List<RepairTaskListBean> mList;
    private Context context;

    public TaskMainAdapter(List<RepairTaskListBean> list, Context context) {
        this.mList = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task_content, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener((v) -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, mList.get((int) view.getTag()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.tvTaskItemTitle.setText(mList.get(position).getTaskName() == null ? "" : mList.get(position).getTaskName());
        myViewHolder.itemRepairTaskName.setText(mList.get(position).getDepartment() == null ? "" : mList.get(position).getDepartment());
        myViewHolder.itemRepairTaskDepartment.setText(mList.get(position).getTaskTime() == null ? "" : mList.get(position).getTaskTime());
        myViewHolder.ryTaskManage.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
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
    public void setData(ArrayList<RepairTaskListBean> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_repair_task_image)
        ImageView itemRepairTaskImage;
        @Bind(R.id.item_repair_task_name)
        TextView itemRepairTaskName;
        @Bind(R.id.item_repair_task_department)
        TextView itemRepairTaskDepartment;
        @Bind(R.id.tv_task_item_title)
        TextView tvTaskItemTitle;
        @Bind(R.id.ry_task_manage)
        RelativeLayout ryTaskManage;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
