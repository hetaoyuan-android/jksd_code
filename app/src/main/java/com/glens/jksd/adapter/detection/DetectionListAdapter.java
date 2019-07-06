package com.glens.jksd.adapter.detection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.bean.ResistanceBean;
import com.glens.jksd.utils.RepairConstantUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 检测管理的主页面
 */
public class DetectionListAdapter extends RecyclerView.Adapter<DetectionListAdapter.MyViewHolder> {


    private ResistanceBean mResistanceBean;
    private Context context;

    public DetectionListAdapter(ResistanceBean mResistanceBean, Context context) {
        this.mResistanceBean = mResistanceBean;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detection_manager, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener((v) -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, mResistanceBean.getRecord().get((int) view.getTag()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        if (mResistanceBean.getRecord().get(position) != null) {
            ResistanceBean.RecordBean recordsBean = mResistanceBean.getRecord().get(position);
            switch (recordsBean.getTaskType()) {
                case RepairConstantUtils.DETECTION_GROUND_RESISTANCE:
                    myViewHolder.mImItemDetectImage.setImageResource(R.drawable.detection_ground);

                    break;
                case RepairConstantUtils.DETECTION_INFRARDE:
                    myViewHolder.mImItemDetectImage.setImageResource(R.drawable.infrared);

                    break;
                case RepairConstantUtils.DETECTION_PAY_ACROSS_MEASURE:
                    myViewHolder.mImItemDetectImage.setImageResource(R.drawable.detection_measure);

                    break;
                default:
                    Log.e("DetectionListAdapter", "非正常工单参数类型");
                    break;
            }
            myViewHolder.mTvItemDetectTitle.setText(recordsBean.getTaskName() == null ? "" : recordsBean.getTaskName());
            myViewHolder.mTvItemDetecName.setText(recordsBean.getExecutorName() == null ? "" : recordsBean.getExecutorName());
            myViewHolder.mTvItemDetectTime.setText(recordsBean.getStartDate() == null || recordsBean.getEndDate() == null ?
                    "" : String.valueOf(recordsBean.getStartDate() + " " + recordsBean.getEndDate()));

            switch (recordsBean.getTaskStatus()) {
                case RepairConstantUtils.DETECTION_CREATE:
                    myViewHolder.mTvUndo.setText("未开始");
                    myViewHolder.mTvUndo.setTextColor(context.getResources().getColor(R.color.red));
                    myViewHolder.mIvTaskUndo.setImageResource(R.drawable.repair_record_stop);
                    break;
                case RepairConstantUtils.DETECTION_DOING:
                    myViewHolder.mTvUndo.setText("已开始");
                    myViewHolder.mTvUndo.setTextColor(context.getResources().getColor(R.color.text_green));
                    myViewHolder.mIvTaskUndo.setImageResource(R.drawable.repair_record_start);
                    break;
                case RepairConstantUtils.DETECTION_DONE:
                    myViewHolder.mTvUndo.setText("已结束");
                    myViewHolder.mTvUndo.setTextColor(context.getResources().getColor(R.color.grey_hint));
                    myViewHolder.mIvTaskUndo.setImageResource(R.drawable.repair_over);
                    break;
                default:
                    Log.e("DetectionListAdapter", "非正常工单状态");
                    break;
            }
        }
        myViewHolder.mRyTaskProgress.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mResistanceBean.getRecord().size();
    }

    // 点击事件
    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }

    private RecyclerViewItemClickListener itemClickListener = null;

    public interface RecyclerViewItemClickListener {
        void onItemClick(View v, Object bean);
    }

    /**
     * 设置数据  刷新界面
     *
     * @param data 数据源
     */
    public void setData(ResistanceBean data) {
        mResistanceBean = data;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.im_item_detect_image)
        ImageView mImItemDetectImage;
        @Bind(R.id.tv_item_detect_title)
        TextView mTvItemDetectTitle;
        @Bind(R.id.tv_item_detec_name)
        TextView mTvItemDetecName;
        @Bind(R.id.tv_item_detect_time)
        TextView mTvItemDetectTime;
        @Bind(R.id.ll_content)
        LinearLayout mLlContent;
        @Bind(R.id.iv_task_undo)
        ImageView mIvTaskUndo;
        @Bind(R.id.tv_undo)
        TextView mTvUndo;
        @Bind(R.id.ry_task_unstart)
        RelativeLayout mRyTaskUnstart;
        @Bind(R.id.ry_task_progress)
        RelativeLayout mRyTaskProgress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
