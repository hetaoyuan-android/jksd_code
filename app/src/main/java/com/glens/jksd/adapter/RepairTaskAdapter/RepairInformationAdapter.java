package com.glens.jksd.adapter.RepairTaskAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.bean.repair_task_bean.RepairInformationBean;
import com.glens.jksd.utils.imagePick.view.MyGridView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepairInformationAdapter extends RecyclerView.Adapter<RepairInformationAdapter.MyViewHolder> {

    private onRecyclerViewItemClickListener mItemClickListener = null;
    private List<RepairInformationBean.RecordsBean> mList;
    private Context mContext;

    public RepairInformationAdapter(List<RepairInformationBean.RecordsBean> mList, Context mContext) {
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
    public void setData(List<RepairInformationBean.RecordsBean> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_information, viewGroup, false);
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
        myViewHolder.tvRepairInformationTime.setText(mList.get(position).getCreateTime() == null ? " " : mList.get(position).getCreateTime());
        myViewHolder.repairInformationName.setText(mList.get(position).getCreateByName() == null ? " " : mList.get(position).getCreateByName());
        myViewHolder.mGridView.setAdapter(new InformtionListAdapter(mContext,mList.get(position).getPicList()));
        myViewHolder.mGridView.setOnTouchListener((v, event) -> true);
        myViewHolder.rlRepairInformation.setTag(position);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_repair_information_time)
        TextView tvRepairInformationTime;
        @Bind(R.id.repair_information_name)
        TextView repairInformationName;
        @Bind(R.id.ry_repair_information)
        RelativeLayout ryRepairInformation;
        @Bind(R.id.grid_view)
        MyGridView mGridView;
        @Bind(R.id.rl_repair_information)
        LinearLayout rlRepairInformation;
        
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
