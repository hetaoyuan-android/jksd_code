package com.glens.jksd.adapter.detection.infrared;

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
import com.glens.jksd.activity.activity_detect.DetectionManagerInfraredNoDisposeActivity;
import com.glens.jksd.bean.deteect.InfraraedListBean;
import com.glens.jksd.utils.StaticUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfraredMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater mLayoutInflater;
    private InfraraedListBean infraraedListBean;
    private Context mContext;


    private static final int NO_DISPOSE_INFRARED = 0;//未处理
    private static final int DONE_INFRARED = 1;//已处理

    public InfraredMainAdapter(InfraraedListBean infraraedListBean, Context mContext) {
        this.infraraedListBean = infraraedListBean;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(InfraraedListBean data) {
        this.infraraedListBean = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == NO_DISPOSE_INFRARED) {
            return new NoDisposeViewHolder(mLayoutInflater.inflate(R.layout.item_detection_no_dispose_infrared, viewGroup, false));

        } else if (i == DONE_INFRARED) {
            View view = mLayoutInflater.inflate(R.layout.item_detection_dispose_infrared, viewGroup, false);
            DoneViewHolder viewHolder = new DoneViewHolder(view);
            view.setOnClickListener((v) -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(view, infraraedListBean.getRecords().get((int) view.getTag()));
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
            if (infraraedListBean.getRecords().get(i) != null) {
                InfraraedListBean.RecordsBean bean = infraraedListBean.getRecords().get(i);

                String lineVol = bean.getLineVol();
                String title = Integer.parseInt(lineVol) / 1000 + "kV" + bean.getLineName() + bean.getTowerNo();
                ((NoDisposeViewHolder) viewHolder).tvGroundNoDisposeTitle.setText(title);
                ((NoDisposeViewHolder) viewHolder).tvGroundNoName.setText(bean.getDetectionTask().getExecutorName());
                ((NoDisposeViewHolder) viewHolder).tvGroundNoDepartment.setText(bean.getDetectionTask().getExeUnitName());
                ((NoDisposeViewHolder) viewHolder).btnGroundNoGoDispose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, DetectionManagerInfraredNoDisposeActivity.class);
                        intent.putExtra(StaticUtils.INFRARED_TASK_CODE, bean.getTaskCode());
                        intent.putExtra(StaticUtils.INFRARED_TITLE, title);
                        intent.putExtra(StaticUtils.INFRARED_LINENAME, bean.getLineName());
                        intent.putExtra(StaticUtils.INFRARED_TOWERNO, bean.getTowerNo());
                        intent.putExtra(StaticUtils.INFRARED_TASK_LINE_ID, bean.getLineId());
                        intent.putExtra(StaticUtils.INFRARED_TASK_LINE_VOL, lineVol);
                        intent.putExtra(StaticUtils.INFRARED_TASK__TOWER_ID, bean.getTowerId());
                        mContext.startActivity(intent);
                    }
                });
            }
        }

        // 已处理
        if (viewHolder instanceof DoneViewHolder) {
            if (infraraedListBean.getRecords().get(i) != null) {
                InfraraedListBean.RecordsBean bean = infraraedListBean.getRecords().get(i);
                ((DoneViewHolder) viewHolder).tvDisposeInfraredTitle.setText(Integer.parseInt(bean.getLineVol()) / 1000 + "kV" + bean.getLineName() + bean.getTowerNo());
                ((DoneViewHolder) viewHolder).tvDisposeInfraredName.setText(bean.getDetectionTask().getExecutorName());
                ((DoneViewHolder) viewHolder).tvDisposeInfraredDepart.setText(bean.getDetectionTask().getExeUnitName());
                ((DoneViewHolder) viewHolder).tvDisposeInfraredTime.setText(bean.getTemperatureRecord().getCreateTime());
                ((DoneViewHolder) viewHolder).tvDisposeInfraredEnv.setText(bean.getTemperatureRecord().getAmbientTemperature());
                ((DoneViewHolder) viewHolder).tvDisposeInfraredNor.setText(bean.getTemperatureRecord().getNormalTemperature());
                ((DoneViewHolder) viewHolder).tvDisposeInfraredHighTep.setText(bean.getTemperatureRecord().getMaxTemperature());
            }
            ((DoneViewHolder) viewHolder).rlInfraredShow.setTag(i);
        }

    }

    @Override
    public int getItemCount() {
        return infraraedListBean.getRecords().size();
    }

    //区分不同的item的方法
    @Override
    public int getItemViewType(int position) {
        if (0 == Integer.parseInt(infraraedListBean.getRecords().get(position).getIsInspected())) {
            return NO_DISPOSE_INFRARED;
        } else if (1 == Integer.parseInt(infraraedListBean.getRecords().get(position).getIsInspected())) {
            return DONE_INFRARED;
        } else {
            return 0;
        }
    }

    // 自定义点击事件
    public interface RecyclerViewItemClickListener {
        void onItemClick(View v, Object bean);
    }

    public InfraredMainAdapter.RecyclerViewItemClickListener itemClickListener;

    public void setOnItemClickListener(InfraredMainAdapter.RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }

    // 红外测温未处理
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

    // 红外测温已处理
    public static class DoneViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_dispose_test_title)
        TextView tvDisposeInfraredTitle;
        @Bind(R.id.tv_dispose_infrared_name)
        TextView tvDisposeInfraredName;
        @Bind(R.id.tv_dispose_infrared_depart)
        TextView tvDisposeInfraredDepart;
        @Bind(R.id.tv_dispose_infrared_time)
        TextView tvDisposeInfraredTime;
        @Bind(R.id.tv_dispose_infrared_env)
        TextView tvDisposeInfraredEnv;
        @Bind(R.id.tv_dispose_infrared_nor)
        TextView tvDisposeInfraredNor;
        @Bind(R.id.tv_dispose_infrared_high_tep)
        TextView tvDisposeInfraredHighTep;
        @Bind(R.id.rl_infrared_show)
        RelativeLayout rlInfraredShow;


        public DoneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
