package com.glens.jksd.activity.activity_detect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.deteect.AddGrounResistanceBean;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.detection.AddDetectionPresenter;
import com.glens.jksd.network.view.detection.AddDetectionView;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.StaticUtils;
import com.glens.jksd.utils.ToastUtils;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 检测管理 红外测温任务 未处理
 */
public class DetectionManagerInfraredNoDisposeActivity extends BaseActivity implements View.OnClickListener, AddDetectionView {

    public static final String TAG = DetectionManagerInfraredNoDisposeActivity.class.getSimpleName();

    @Bind(R.id.tv_infrared_line_name)
    TextView tvInfraredLineName;
    @Bind(R.id.tv_infrared_tower_num)
    TextView tvInfraredTowerNum;
    @Bind(R.id.et_infrared_no_env_temper)
    EditText etInfraredNoEnvTemper;
    @Bind(R.id.et_infrared_no_normal_temp)
    EditText etInfraredNoNormalTemp;
    @Bind(R.id.et_infrared_no_highest_temp)
    EditText etInfraredNoHighestTemp;
    @Bind(R.id.et_describe_content)
    EditText etDescribeContent;
    @Bind(R.id.btn_infrared_no_save)
    Button btnInfraredNoSave;

    private AddDetectionPresenter mPresenter;
    private AddGrounResistanceBean mBean;

    private Spinner mSpinner;
    private ArrayAdapter<String> adapter;
    private String[] ctype = new String[]{"引流板", "导线接续管", "地线接续管 ", "地线放电间隙 ", "悬垂线夹", "管母接头", "其它 "};

    private String mTaskCode;
    private String lineTitle;
    private String lineName;
    private String towerNo;
    private String mLineId;
    private String mLineVol;
    private String mTowerId;
    private int mSelectDartPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_manager_no_dispose);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        getIntentExtra();
        Log.e("mLineVol", "mLineVol" + mLineVol);
        setTitleText(lineTitle);
        tvInfraredLineName.setText(lineName);
        tvInfraredTowerNum.setText(towerNo);
        getLlBasetitleBack().setOnClickListener(this);
        btnInfraredNoSave.setOnClickListener(this);
        mSpinner = findViewById(R.id.spinner_department);
        adapter = new ArrayAdapter<>(this, R.layout.item_spinner, ctype);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String string = (String) mSpinner.getSelectedItem();
                mSelectDartPosition = mSpinner.getSelectedItemPosition();
//                if (selectPosition == 0) {
//                    ToastUtils.showToastSafe(getApplication(), "请重新选择部件名称");
//                    return;
//                }
//                ToastUtils.showToastSafe(getApplication(), "选择了：" + string + "selectPosition" + mSelectDartPosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initData() {
        mPresenter = new AddDetectionPresenter(this);
        mPresenter.onCreate();
        mPresenter.BindPresentView((PresentView) this);
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        lineTitle = extras.getString(StaticUtils.INFRARED_TITLE);
        lineName = extras.getString(StaticUtils.INFRARED_LINENAME);
        towerNo = extras.getString(StaticUtils.INFRARED_TOWERNO);
        mTaskCode = extras.getString(StaticUtils.INFRARED_TASK_CODE);
        mLineId = extras.getString(StaticUtils.INFRARED_TASK_LINE_ID);
        mLineVol = extras.getString(StaticUtils.INFRARED_TASK_LINE_VOL);
        mTowerId = extras.getString(StaticUtils.INFRARED_TASK__TOWER_ID);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_basetitle_back:
                finish();
                break;
            case R.id.btn_infrared_no_save:
                Log.e("btn_infrared_no_save", "btn_infrared_no_save");
                mPresenter.addInfrared();
                default:
                    break;
        }
    }

    @Override
    public void onSuccess(Object bean, String resultMsg, boolean result) {
        Log.e(TAG, "DetectionManagerInfraredNoDisposeActivity" + resultMsg + "   result:" + result);
        Log.e(TAG, "mSelectDartPosition" + mSelectDartPosition);
        mBean = (AddGrounResistanceBean) bean;
        if (mBean.getRecodeCode() != null && result) {
            ToastUtils.showToastSafe(this, "添加成功");
            String code = mBean.getRecodeCode();
            if (code != null) {
//                PreferenceUtils.putString(this, "picCode", code);
                Log.e(TAG, "存储code" + code);
            }

        }
    }

    @Override
    public void showDialog(String message) {
        showSvpDilog(this, message, false, null, null);
    }

    @Override
    public void hideDialog() {
        dismissSvpDilog(RepairConstantUtils.DIALOG_TIME);
    }

    @Override
    public HashMap<String, Object> getDataMap() {
        Log.e(TAG, "taskCode " + mTaskCode);
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskCode", mTaskCode);
        map.put("ambientTemperature", etInfraredNoEnvTemper.getText().toString().trim());
        map.put("normalTemperature", etInfraredNoNormalTemp.getText().toString().trim());
        map.put("maxTemperature", etInfraredNoHighestTemp.getText().toString().trim());
        map.put("parts", mSelectDartPosition + 1);
        map.put("isQualified", 1);
        map.put("lineId", mLineId);
        map.put("lineVol", mLineVol);
        map.put("towerId", mTowerId);
        map.put("towerNo", tvInfraredTowerNum.getText().toString().trim());
        map.put("siteDesc", etDescribeContent.getText().toString().trim());
        map.put("picCodes", "");
        return map;
    }

    @Override
    public void onError(String result) {

    }
}
