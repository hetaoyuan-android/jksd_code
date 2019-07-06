package com.glens.jksd.adapter.detection.ground;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_detect.DetectionManagerGroundNoDisposeActivity;
import com.glens.jksd.bean.deteect.GroundResistanceListBean;
import com.glens.jksd.utils.StaticUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroundResistanceMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater mLayoutInflater;
    private GroundResistanceListBean groundResistanceListBean;
    private Context mContext;


    private static final int NO_DISPOSE_GROUND_RESISTANCE = 0;//未处理
    private static final int DONE_GROUND_RESISTANCE = 1;//已处理


    public GroundResistanceMainAdapter(GroundResistanceListBean groundResistanceListBean, Context context) {
        this.groundResistanceListBean = groundResistanceListBean;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(GroundResistanceListBean data) {
        this.groundResistanceListBean = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == NO_DISPOSE_GROUND_RESISTANCE) {
            return new NoDisposeViewHolder(mLayoutInflater.inflate(R.layout.item_detection_no_dispose_infrared, viewGroup, false));
        } else if (i == DONE_GROUND_RESISTANCE) {
            View view = mLayoutInflater.inflate(R.layout.item_detection_dispose_ground_resistance, viewGroup, false);
            DoneViewHolder viewHolder = new DoneViewHolder(view);
//            return new DoneViewHolder(mLayoutInflater.inflate(R.layout.item_detection_dispose_ground_resistance, viewGroup, false));
            view.setOnClickListener((v) -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(view, groundResistanceListBean.getRecords().get((int) view.getTag()));
                }
            });
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        // 未处理
        if (viewHolder instanceof NoDisposeViewHolder) {
            if (groundResistanceListBean.getRecords().get(i) != null) {
                GroundResistanceListBean.RecordsBean recordsBean = groundResistanceListBean.getRecords().get(i);
                String title = Integer.parseInt(recordsBean.getLineVol()) / 1000 + "kV" + recordsBean.getLineName() + recordsBean.getTowerNo();
                ((NoDisposeViewHolder) viewHolder).tvGroundNoDisposeTitle.setText(title);
                ((NoDisposeViewHolder) viewHolder).tvGroundNoName.setText(recordsBean.getDetectionTask().getExecutorName());
                ((NoDisposeViewHolder) viewHolder).tvGroundNoDepartment.setText(recordsBean.getDetectionTask().getExeUnitName());
                ((NoDisposeViewHolder) viewHolder).btnGroundNoGoDispose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        //传递接地电阻任务的线路名称
                        intent.putExtra(StaticUtils.GROUND_TITLE, title);
                        intent.putExtra(StaticUtils.GROUND_LINENAME, recordsBean.getLineName());
                        intent.putExtra(StaticUtils.GROUND_TOWERNUM, recordsBean.getTowerNo());
                        intent.putExtra(StaticUtils.GROUND_TASK_CODE, recordsBean.getTaskCode());
                        intent.putExtra(StaticUtils.GROUND_TASK_LINE_ID,recordsBean.getLineId());
                        intent.putExtra(StaticUtils.GROUND_TASK_LINE_VOL, recordsBean.getLineVol());
                        intent.putExtra(StaticUtils.GROUND_TASK__TOWER_ID, recordsBean.getTowerId());
                        intent.setClass(mContext, DetectionManagerGroundNoDisposeActivity.class);
                        mContext.startActivity(intent);
                    }
                });
            }
        }

        if (viewHolder instanceof DoneViewHolder) {
            if (groundResistanceListBean.getRecords().get(i) != null) {
                GroundResistanceListBean.RecordsBean recordsBean = groundResistanceListBean.getRecords().get(i);
                String title = Integer.parseInt(recordsBean.getLineVol()) / 1000 + "kV" + recordsBean.getLineName() + recordsBean.getTowerNo();
                ((DoneViewHolder) viewHolder).tvGroundDisposeTitle.setText(title);
                ((DoneViewHolder) viewHolder).tvGroundDisposeName.setText(recordsBean.getDetectionTask().getExecutorName());
                ((DoneViewHolder) viewHolder).tvGroundDisposeDepart.setText(recordsBean.getDetectionTask().getExeUnitName());
                ((DoneViewHolder) viewHolder).tvGroundDisposeTime.setText(recordsBean.getDetectionTask().getStartDate());
                ((DoneViewHolder) viewHolder).tvGroundDisposeA.setText(recordsBean.getResistance().getErAValue());
                ((DoneViewHolder) viewHolder).tvGroundDisposeB.setText(recordsBean.getResistance().getErBValue());
                ((DoneViewHolder) viewHolder).tvGroundDisposeC.setText(recordsBean.getResistance().getErCValue());
                ((DoneViewHolder) viewHolder).tvGroundDisposeD.setText(recordsBean.getResistance().getErDValue());
            }
            ((DoneViewHolder) viewHolder).rlDisposeItemGround.setTag(i);
        }


    }


    @Override
    public int getItemCount() {
        return groundResistanceListBean.getRecords().size();
    }

    //区分不同的item的方法
    @Override
    public int getItemViewType(int position) {
        if (0 == Integer.parseInt(groundResistanceListBean.getRecords().get(position).getIsInspected())) {
            return NO_DISPOSE_GROUND_RESISTANCE;
        } else if (1 == Integer.parseInt(groundResistanceListBean.getRecords().get(position).getIsInspected())) {
            return DONE_GROUND_RESISTANCE;
        } else {
            return 0;
        }
    }

    // 自定义点击事件
    public interface RecyclerViewItemClickListener {
        void onItemClick(View v, Object bean);
    }

    public RecyclerViewItemClickListener itemClickListener;

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }


    //已处理的ViewHolder
    public static class DoneViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_ground_dispose_title)
        TextView tvGroundDisposeTitle;
        @Bind(R.id.tv_ground_dispose_name)
        TextView tvGroundDisposeName;
        @Bind(R.id.tv_ground_dispose_depart)
        TextView tvGroundDisposeDepart;
        @Bind(R.id.tv_ground_dispose_time)
        TextView tvGroundDisposeTime;
        @Bind(R.id.tv_ground_dispose_a)
        TextView tvGroundDisposeA;
        @Bind(R.id.tv_ground_dispose_b)
        TextView tvGroundDisposeB;
        @Bind(R.id.tv_ground_dispose_c)
        TextView tvGroundDisposeC;
        @Bind(R.id.tv_ground_dispose_d)
        TextView tvGroundDisposeD;
        @Bind(R.id.rl_dispose_item_ground)
        RelativeLayout rlDisposeItemGround;

        public DoneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //未处理的ViewHolder
    public static class NoDisposeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_ground_no_dispose_title)
        TextView tvGroundNoDisposeTitle;
        @Bind(R.id.tv_ground_no_name)
        TextView tvGroundNoName;
        @Bind(R.id.tv_ground_no_department)
        TextView tvGroundNoDepartment;
        @Bind(R.id.btn_ground_no_go_dispose)
        Button btnGroundNoGoDispose;

        public NoDisposeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
