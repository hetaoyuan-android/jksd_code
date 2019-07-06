package com.glens.jksd.fragment.RepairTaskFragments;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_repair.RepairStandardTaskActivity;
import com.glens.jksd.base.BaseFragment;
import com.glens.jksd.bean.RepairBillBean;
import com.glens.jksd.network.presenter.RepairBillPresenter;
import com.glens.jksd.network.view.RepairBillView;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 任务台账
 */
public class RepairTaskBillFragment extends BaseFragment implements RepairBillView {
    public static final String TAG = RepairTaskBillFragment.class.getSimpleName();
    @Bind(R.id.repair_task_name_text)
    TextView mRepairTaskNameText;
    @Bind(R.id.repair_power_cut_text)
    TextView mRepairPowerCutText;
    @Bind(R.id.repair_power_area_text)
    TextView mRepairPowerAreaText;
    @Bind(R.id.repair_plan_start_time_text)
    TextView mRepairPlanStartTimeText;
    @Bind(R.id.repair_plan_end_time_text)
    TextView mRepairPlanEndTimeText;
    @Bind(R.id.repair_type_text)
    TextView mRepairTypeText;
    @Bind(R.id.repair_task_create_text)
    TextView mRepairTaskCreateText;
    @Bind(R.id.repair_task_create_time_text)
    TextView mRepairTaskCreateTimeText;
    @Bind(R.id.repair_task_class_tet)
    TextView mRepairTaskClassTet;
    @Bind(R.id.repair_construction_organization_text)
    TextView mRepairConstructionOrganizationText;
    @Bind(R.id.repair_task_person_text)
    TextView mRepairTaskPersonText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;
    @Bind(R.id.repair_task_standard_text)
    TextView repairTaskStandardText;

    private RepairBillPresenter mPresenter;
    private String taskCode;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_repair_task_bill, null);
        ButterKnife.bind(this, view);
        setButtonDrawable(repairTaskStandardText);
        initNetData();
        return view;
    }

    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        taskCode = PreferenceUtils.getString(getActivity(), "taskCode", "");
        Log.e(TAG, "taskCode " + taskCode);
        mPresenter = new RepairBillPresenter(getActivity());
        mPresenter.onCreate();
        mPresenter.BindPresentView(this);
        if (taskCode != null) {
            mPresenter.getRepairListInfo(getActivity(), taskCode, this);
        }
    }


    private void setButtonDrawable(TextView textView) {
        Drawable drawable = getResources().getDrawable(R.drawable.repair_mend_icon);
        drawable.setBounds(0, 0, 30, 30);//第一0是距左边距离，第二0是距上边距离，30、35分别是长宽
        textView.setCompoundDrawables(drawable, null, null, null);//只放左边
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.repair_task_standard_ry)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), RepairStandardTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSuccess(RepairBillBean bean, String resultMsg) {
        mRepairTaskNameText.setText(bean.getTaskName() == null ? "" : bean.getTaskName());
        mRepairPowerCutText.setText(bean.getIsPowerCut() == 1 ? "是" : "否");
        mRepairPowerAreaText.setText(bean.getPowerOutage() == null ? "" : bean.getPowerOutage());
        mRepairPlanStartTimeText.setText(bean.getStartDate() == null ? "" : bean.getStartDate());
        mRepairPlanEndTimeText.setText(bean.getEndDate() == null ? "" : bean.getEndDate());
        mRepairTypeText.setText(getRepairText(bean.getOverhaulType()));
        mRepairTaskCreateText.setText(bean.getCreateByName() == null ? "" : bean.getCreateByName());
        mRepairTaskCreateTimeText.setText(bean.getCreateTime() == null ? "" : bean.getCreateTime());
        mRepairTaskClassTet.setText(bean.getDispatchTeam() == null ? "" : bean.getDispatchTeam());
        mRepairConstructionOrganizationText.setText(bean.getConstructionUnit() == null ? "" : bean.getConstructionUnit());
        mRepairTaskPersonText.setText(bean.getWorkLeader() == null ? "" : bean.getWorkLeader());

        Log.e(TAG,"请求结果" + resultMsg);
    }

    private String getRepairText(int overhaulType) {
        String repairType ;
        switch (overhaulType) {
            case RepairConstantUtils.DETECTION_GROUND_RESISTANCE:
                repairType = RepairConstantUtils.REPAIR_TECHNICAL;
                break;
            case RepairConstantUtils.DETECTION_INFRARDE:
                repairType = RepairConstantUtils.REPAIR_FIXED;
                break;
            case RepairConstantUtils.DETECTION_PAY_ACROSS_MEASURE:
                repairType = RepairConstantUtils.REPAIR_DAILY;
                break;
            default:
                repairType = "";
                break;

        }
        return repairType;
    }

    @Override
    public void onError(String result) {

    }
}
