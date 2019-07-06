package com.glens.jksd.adapter.detection.measure;

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
import com.glens.jksd.activity.activity_detect.DetectionPayMeasureNoDisposeActivity;
import com.glens.jksd.bean.deteect.PayMeasureListBean;
import com.glens.jksd.utils.StaticUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PayMeasureListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private LayoutInflater mLayoutInflater;
    private PayMeasureListBean mPayMeasureListBean;
    private Context mContext;

    private static final int NO_DISPOSE_PAY_MEASURE = 0;//未处理
    private static final int DONE_PAY_MEASURE = 1;//已处理


    public PayMeasureListAdapter(PayMeasureListBean payMeasureListBean, Context mContext) {
        this.mPayMeasureListBean = payMeasureListBean;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == NO_DISPOSE_PAY_MEASURE) {
            return new NoDisposeViewHolder(mLayoutInflater.inflate(R.layout.item_detection_no_dispose_pay_across_measure, viewGroup, false));
        } else if (i == DONE_PAY_MEASURE) {
            View view = mLayoutInflater.inflate(R.layout.item_detection_dispose_pay_across_measure, viewGroup, false);
            DoneViewHolder viewHolder = new DoneViewHolder(view);
            view.setOnClickListener((v) -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(view, mPayMeasureListBean.getRecords().get((int) view.getTag()));
                }
            });
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof NoDisposeViewHolder) {
            if (mPayMeasureListBean.getRecords() != null) {
                PayMeasureListBean.RecordsBean bean = mPayMeasureListBean.getRecords().get(i);
                String lineName = bean.getLineName();
                String towerNo = bean.getStartTowerNo() + "-" + bean.getEndTowerNo();
                String title = Integer.parseInt(bean.getLineVol()) / 1000 +"kV" + lineName + towerNo;
                ((NoDisposeViewHolder) viewHolder).tvNoMeasureTitle.setText(title);
                ((NoDisposeViewHolder) viewHolder).tvNoDisposeMeasureName.setText(bean.getDetectionTask().getExecutorName());
                ((NoDisposeViewHolder) viewHolder).tvNoDisposeMeasureDepart.setText(bean.getDetectionTask().getExeUnitName());
                ((NoDisposeViewHolder) viewHolder).btnNoDisposeMeasure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(StaticUtils.MEASURE_TITLE, title);
                        intent.putExtra(StaticUtils.MEASURE_LINENAME, lineName);
                        intent.putExtra(StaticUtils.MEASURE_TOWERNO, bean.getStartTowerNo() + "-" + bean.getEndTowerNo());
                        intent.putExtra(StaticUtils.MEASURE_TASK_CODE, bean.getTaskCode());
                        intent.putExtra(StaticUtils.MEASURE_LINEID, bean.getLineId());
                        intent.putExtra(StaticUtils.MEASURE_LINEVOL, bean.getLineVol());
                        intent.putExtra(StaticUtils.MEASURE_STARTTOWERID, bean.getStartTowerId());
                        intent.putExtra(StaticUtils.MEASURE_STARTTOWERNO, bean.getStartTowerNo());
                        intent.putExtra(StaticUtils.MEASURE_ENDTOWERID, bean.getEndTowerId());
                        intent.putExtra(StaticUtils.MEASURE_ENDTOWERNO, bean.getEndTowerNo());
                        intent.setClass(mContext, DetectionPayMeasureNoDisposeActivity.class);
                        mContext.startActivity(intent);
                    }
                });
            }
        }

        if (viewHolder instanceof DoneViewHolder) {
            if (mPayMeasureListBean.getRecords() != null) {
                PayMeasureListBean.RecordsBean bean = mPayMeasureListBean.getRecords().get(i);
                String title = Integer.parseInt(bean.getLineVol()) / 1000 +"kV" + bean.getLineName() + bean.getStartTowerNo() + "-" + bean.getEndTowerNo();
                ((DoneViewHolder) viewHolder).tvDisposeMeasureTitle.setText(title);
                ((DoneViewHolder) viewHolder).tvDisposeMeasureName.setText(bean.getDetectionTask().getExecutorName());
                ((DoneViewHolder) viewHolder).tvDisposeMeasureDepart.setText(bean.getDetectionTask().getExeUnitName());
                ((DoneViewHolder) viewHolder).tvDisposeMeasureTime.setText(bean.getMeasureRecord().getCreateTime());
                ((DoneViewHolder) viewHolder).tvDisposeMeasureEnv.setText(bean.getMeasureRecord().getAmbientTemperature());
                ((DoneViewHolder) viewHolder).tvDisposeMeasureDistance.setText(bean.getMeasureRecord().getCrossDis());
                switch (Integer.parseInt(bean.getMeasureRecord().getCrossType())) {
                    case 1:
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setText(R.string.step_road);
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setTextColor(mContext.getResources().getColor(R.color.step_high_speed));
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setBackground(mContext.getResources().getDrawable(R.drawable.step_high_speed));
                        break;
                    case 2:
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setText(R.string.step_railway);
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setTextColor(mContext.getResources().getColor(R.color.step_railway));
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setBackground(mContext.getResources().getDrawable(R.drawable.step_railway));
                        break;
                    case 3:
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setText(R.string.step_important_channel);
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setTextColor(mContext.getResources().getColor(R.color.step_important_channel));
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setBackground(mContext.getResources().getDrawable(R.drawable.step_important_channel));
                        break;
                    case 4:
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setText(R.string.step_river);
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setTextColor(mContext.getResources().getColor(R.color.step_river));
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureCrossType.setBackground(mContext.getResources().getDrawable(R.drawable.step_river));
                        break;
                    default:
                        break;
                }

                switch (Integer.parseInt(bean.getMeasureRecord().getIsQualified())) {
                    case 0:
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureIsQualified.setText(R.string.not_qualified);
                        break;
                    case 1:
                        ((DoneViewHolder) viewHolder).tvDisposeMeasureIsQualified.setText(R.string.qualified);
                }

            }
            ((DoneViewHolder) viewHolder).rlPayMeasureMain.setTag(i);

        }
    }

    @Override
    public int getItemCount() {
        return mPayMeasureListBean.getRecords().size();
    }

    public void setData(PayMeasureListBean data) {
        this.mPayMeasureListBean = data;
        notifyDataSetChanged();
    }

    //区分不同的item的方法
    @Override
    public int getItemViewType(int position) {
        if (0 == Integer.parseInt(mPayMeasureListBean.getRecords().get(position).getIsInspected())) {
            return NO_DISPOSE_PAY_MEASURE;
        } else if (1 == Integer.parseInt(mPayMeasureListBean.getRecords().get(position).getIsInspected())) {
            return DONE_PAY_MEASURE;
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


    // 未处理
    public static class NoDisposeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_no_measure_title)
        TextView tvNoMeasureTitle;
        @Bind(R.id.tv_no_dispose_measure_name)
        TextView tvNoDisposeMeasureName;
        @Bind(R.id.tv_no_dispose_measure_depart)
        TextView tvNoDisposeMeasureDepart;
        @Bind(R.id.btn_no_dispose_measure)
        Button btnNoDisposeMeasure;

        public NoDisposeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // 已处理
    public static class DoneViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_dispose_measure_title)
        TextView tvDisposeMeasureTitle;
        @Bind(R.id.tv_dispose_measure_cross_type)
        TextView tvDisposeMeasureCrossType;
        @Bind(R.id.tv_dispose_measure_name)
        TextView tvDisposeMeasureName;
        @Bind(R.id.tv_dispose_measure_depart)
        TextView tvDisposeMeasureDepart;
        @Bind(R.id.tv_dispose_measure_time)
        TextView tvDisposeMeasureTime;
        @Bind(R.id.tv_dispose_measure_env)
        TextView tvDisposeMeasureEnv;
        @Bind(R.id.tv_dispose_measure_distance)
        TextView tvDisposeMeasureDistance;
        @Bind(R.id.tv_dispose_measure_is_qualified)
        TextView tvDisposeMeasureIsQualified;
        @Bind(R.id.rl_pay_measure_main)
        RelativeLayout rlPayMeasureMain;

        public DoneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
