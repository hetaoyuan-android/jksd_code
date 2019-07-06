package com.glens.jksd.activity.activity_repair.stand_add;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.PowerApplication;
import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.RecordingItem;
import com.glens.jksd.bean.UpRepairPhotoBean;
import com.glens.jksd.network.presenter.RepairGetMapPresenter;
import com.glens.jksd.network.view.RepairTowerAddView;
import com.glens.jksd.service.RecordingService;
import com.glens.jksd.utils.MediaRecordUtils;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ThreadUtils;
import com.glens.jksd.utils.ToastUtils;
import com.glens.jksd.utils.baseEvent.BaseEvent;
import com.glens.jksd.utils.imagePick.adapter.ImagePublishAdapter;
import com.glens.jksd.utils.imagePick.model.ImageItem;
import com.glens.jksd.utils.imagePick.util.ImagePickUtils;
import com.glens.jksd.utils.imagePick.view.MyGridView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.glens.jksd.utils.MediaRecordUtils.mRecordLayout;

public class RepairAddInformationActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, RepairTowerAddView {
    public static final String TAG = RepairAddInformationActivity.class.getSimpleName();
    @Bind(R.id.bt_repair_record)
    ImageView btRepairRecord;
    @Bind(R.id.repair_record_line)
    View mRepairRecordLine;
    @Bind(R.id.iv_repair_record_trumpet)
    ImageView ivRepairRecordTrumpet;
    @Bind(R.id.pb_repair_record)
    ProgressBar mPbRepairRecord;
    @Bind(R.id.cm_repair_record)
    Chronometer mCmRepairRecord;
    @Bind(R.id.iv_repair_record_delete)
    ImageView ivRepairRecordDelete;
    @Bind(R.id.ry_repair_record)
    RelativeLayout mRyRepairRecord;
    @Bind(R.id.iv_repair_record_trumpet_two)
    ImageView ivRepairRecordTrumpetTwo;
    @Bind(R.id.pb_repair_record_two)
    ProgressBar mPbRepairRecordTwo;
    @Bind(R.id.cm_repair_record_two)
    Chronometer mCmRepairRecordTwo;
    @Bind(R.id.iv_repair_record_delete_two)
    ImageView ivRepairRecordDeleteTwo;
    @Bind(R.id.ry_repair_record_two)
    RelativeLayout mRyRepairRecordTwo;
    @Bind(R.id.iv_repair_record_trumpet_three)
    ImageView ivRepairRecordTrumpetThree;
    @Bind(R.id.pb_repair_record_three)
    ProgressBar mPbRepairRecordThree;
    @Bind(R.id.cm_repair_record_three)
    Chronometer mCmRepairRecordThree;
    @Bind(R.id.iv_repair_record_delete_three)
    ImageView ivRepairRecordDeleteThree;
    @Bind(R.id.ry_repair_record_three)
    RelativeLayout mRyRepairRecordThree;
    @Bind(R.id.grid_view)
    MyGridView mGridView;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout repairTaskStandardRy;
    @Bind(R.id.repair_information_record)
    RadioButton repairInformationRecord;
    @Bind(R.id.repair_information_method)
    RadioButton repairInformationMethod;
    @Bind(R.id.repair_information_work)
    RadioButton repairInformationWork;
    @Bind(R.id.repair_information_others)
    RadioButton repairInformationOthers;
    @Bind(R.id.rg_repair_information)
    RadioGroup mRgRepairInformation;
    @Bind(R.id.ry_information)
    RelativeLayout ryInformation;


    private boolean mStartRecording = true;
    private MediaPlayer mMediaPlayer = null;
    private int recordTime = 0;//循环更新的次数
    public int mCurrentItem = 1;//当前播放的录音
    private RecordingItem recordingItemOne;
    private RecordingItem recordingItemTwo;
    private RecordingItem recordingItemThree;
    private Handler mHandler;
    private RepairGetMapPresenter mPresenter;
    private ImagePublishAdapter mImageListAdapter;
    private UpRepairPhotoBean photoBean;
    private String picCode;
    private String audioCode;
    private int picType = 1;


    private boolean isShowDelete = false;
    private ArrayList<ImageItem> mImageList;
    private String mTaskCode;
    private HashMap<String, String> mAudioPathMap;
    private static final int REQUEST_CHECK_PIC = 117;
    private UpRepairPhotoBean mAudioBean;
    private ArrayList<String> mAudioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_add_information);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void initView() {
        initData();
        initNetData();
        initImageData();
    }

    private void initData() {
        mAudioList = new ArrayList<>();
        mHandler = new Handler();
        mAudioPathMap = new HashMap<>();
        mRecordLayout = 1;
        mRgRepairInformation.check(R.id.repair_information_record);
        setTitleText(R.string.repair_information_add);
        getLlBasetitleBack().setOnClickListener(this);
        mRepairTaskStandardText.setText(R.string.repair_check_submit);
        ButterKnife.bind(this);

        mRgRepairInformation.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.repair_information_record:
                    picType = RepairConstantUtils.INFORMATION_RECORD;
                    break;
                case R.id.repair_information_method:
                    picType = RepairConstantUtils.INFORMATION_METHOD;
                    break;
                case R.id.repair_information_work:
                    picType = RepairConstantUtils.INFORMATION_WORK;
                    break;
                case R.id.repair_information_others:
                    picType = RepairConstantUtils.INFORMATION_OTHERS;
                    break;
                    default:
                        break;
            }
        });
    }

    private Runnable mRunnable = () -> {
        if (mMediaPlayer != null) {
            int mCurrentPosition = mMediaPlayer.getCurrentPosition();
            setProgressbar(mCurrentItem, mCurrentPosition);
            updateSeekBar();
            if (!mMediaPlayer.isPlaying()) {
                setConfigureStop();
            }
        }
    };

    private void setProgressbar(int mRecordLayout, int progress) {
        switch (mRecordLayout) {
            case 1:
                mPbRepairRecord.setProgress(progress);
                break;
            case 2:
                mPbRepairRecordTwo.setProgress(progress);
                break;
            case 3:
                mPbRepairRecordThree.setProgress(progress);
                break;
            default:
                break;
        }
    }

    private void updateSeekBar() {
        mHandler.postDelayed(mRunnable, 1000);
    }


    private Runnable mUpAudio = () -> {
        mPresenter.upAudioData(mCurrentItem, TAG, mTaskCode, this,RepairConstantUtils.INFORMATION_TYPE);
    };


    /**
     * 初始化绑定presenter
     */
    private void initNetData() {
        mTaskCode = PreferenceUtils.getString(this, "taskCode", "");
        mPresenter = new RepairGetMapPresenter(RepairAddInformationActivity.this);
        mPresenter.onCreate();
        mPresenter.BindPresentView(RepairAddInformationActivity.this);
    }

    private void initImageData() {
        mImageList = new ArrayList<>();
        mGridView.setHaveScrollbar(false);
        mGridView.setOnItemClickListener(this);
        mGridView.setOnItemLongClickListener((parent, view, position, id) -> {
            if (isShowDelete) {
                isShowDelete = false;
            } else {
                isShowDelete = true;
            }
            mImageListAdapter.setShowDelete(isShowDelete);
            return true;
        });
        mImageListAdapter = new ImagePublishAdapter(this, mImageList, 4);
        mGridView.setAdapter(mImageListAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.grid_view:
                BaseEvent event = new BaseEvent();
                event.setEventType(RepairConstantUtils.NET_POST_DETECTION_IMAGE);
                ImagePickUtils.promptSelectImgDialog(event, position, this, mImageList, REQUEST_CHECK_PIC, 4, R.id.ry_information);
                PowerApplication.setCurrentDataList(mImageList);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "requestCode=" + requestCode + " resultCode=" + resultCode);
        switch (requestCode) {
            case REQUEST_CHECK_PIC:
                if (mImageList.size() < 4 && resultCode == -1 && !TextUtils.isEmpty(ImagePickUtils.getImagePath())) {
                    ImageItem item = new ImageItem();
                    item.sourcePath = ImagePickUtils.getImagePath();
                    mImageList.add(item);
                    mImageListAdapter.setData(mImageList);


                    Log.e("路径", item.sourcePath);
                    mPresenter.upInformationPhoto(item.sourcePath,mTaskCode, picType, "测试");
                }
                break;
        }
    }

    @OnClick({R.id.iv_repair_record_trumpet, R.id.iv_repair_record_trumpet_two, R.id.iv_repair_record_trumpet_three,
            R.id.iv_repair_record_delete, R.id.iv_repair_record_delete_two, R.id.iv_repair_record_delete_three,
            R.id.bt_repair_record, R.id.repair_task_standard_ry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_repair_record_trumpet:
                mCurrentItem = RepairConstantUtils.REPAIR_RECORD_ITEM_ONE;
                isPlayRecord(mCurrentItem);
                break;
            case R.id.iv_repair_record_trumpet_two:
                mCurrentItem = RepairConstantUtils.REPAIR_RECORD_ITEM_TWO;
                isPlayRecord(mCurrentItem);
                break;
            case R.id.iv_repair_record_trumpet_three:
                mCurrentItem = RepairConstantUtils.REPAIR_RECORD_ITEM_THREE;
                isPlayRecord(mCurrentItem);
                break;
            case R.id.iv_repair_record_delete:
                MediaRecordUtils.isDeleteRecord(getApplicationContext(), recordingItemOne, RepairConstantUtils.REPAIR_RECORD_ITEM_ONE, mRyRepairRecord);
                mPresenter.checkDeleteSound(RepairConstantUtils.REPAIR_RECORD_ITEM_ONE, mAudioPathMap, TAG,RepairConstantUtils.INFORMATION_TYPE);
                break;
            case R.id.iv_repair_record_delete_two:
                MediaRecordUtils.isDeleteRecord(getApplicationContext(), recordingItemTwo, RepairConstantUtils.REPAIR_RECORD_ITEM_TWO, mRyRepairRecordTwo);
                mPresenter.checkDeleteSound(RepairConstantUtils.REPAIR_RECORD_ITEM_TWO, mAudioPathMap, TAG,RepairConstantUtils.INFORMATION_TYPE);
                break;
            case R.id.iv_repair_record_delete_three:
                MediaRecordUtils.isDeleteRecord(getApplicationContext(), recordingItemThree, RepairConstantUtils.REPAIR_RECORD_ITEM_THREE, mRyRepairRecordThree);
                mPresenter.checkDeleteSound(RepairConstantUtils.REPAIR_RECORD_ITEM_THREE, mAudioPathMap, TAG,RepairConstantUtils.INFORMATION_TYPE);
                break;
            case R.id.bt_repair_record:
                isStartRecord(mRecordLayout);
                break;
            case R.id.repair_task_standard_ry:
                if (mPresenter.checkInformationData(this, mTaskCode, picCode, audioCode)) {
                    mPresenter.addInformationRecord();
                }
                break;
        }
    }

    /**
     * 录音前鉴权
     *
     * @param layout 布局位置
     */
    private void isStartRecord(int layout) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this
                    , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1);
        } else {
            if (layout < 4) {
                onRecord(mStartRecording, layout);
                mStartRecording = !mStartRecording;
                Log.e(TAG, "点击录音后状态" + mStartRecording);
            } else {
                ToastUtils.showToastSafe(this, "只能录入三条录音");
            }
        }
    }

    /**
     * 开始录音
     *
     * @param start        是否录音状态
     * @param recordLayout 录音总条数
     */
    private void onRecord(boolean start, int recordLayout) {
        recordTime = 0;
        Intent intent = new Intent(this, RecordingService.class);
        intent.putExtra(RepairConstantUtils.REPAIR_RECORD_LAYOUT, recordLayout);

        if (start) {
            ToastUtils.showToastSafe(this, "开始录音...");
            File folder = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC) + "/SoundRecorder");
            if (!folder.exists()) {
                folder.mkdir();
            }
            startTimer();
            startService(intent);
        } else {
            ToastUtils.showToastSafe(this, "录音结束...");
            stopService(intent);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            ThreadUtils.mHandler.postDelayed(mUpAudio, 1000);
        }
    }

    private void startTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.e("持续时间", "en" + SystemClock.elapsedRealtime() + "录音次数" + mRecordLayout);
                recordTime++;
                if (recordTime == 5) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onRecord(false, mRecordLayout);
                            switch (mRecordLayout) {
                                case 1:
                                    mRepairRecordLine.setVisibility(View.VISIBLE);
                                    mRyRepairRecord.setVisibility(View.VISIBLE);
                                    break;
                                case 2:
                                    mRyRepairRecordTwo.setVisibility(View.VISIBLE);
                                    break;
                                case 3:
                                    mRyRepairRecordThree.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    Log.e(" startTimer", "mRecordLayout不合法");
                                    break;
                            }
                            Log.e("终止时间", "en" + SystemClock.elapsedRealtime());
                            mStartRecording = !mStartRecording;
                            timer.cancel();
                        }
                    });
                }
            }
        };
        timer.schedule(task, 1000, 1000);                //启动定时器

    }


    /**
     * 播放音乐
     */
    private void isPlayRecord(int layoutSize) {
        isKeepFile(layoutSize);
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        mMediaPlayer = new MediaPlayer();
        switch (layoutSize) {
            case 1:
                MediaRecordUtils.setMediaSource(mMediaPlayer, recordingItemOne, mPbRepairRecord, mCmRepairRecord);
                break;
            case 2:
                MediaRecordUtils.setMediaSource(mMediaPlayer, recordingItemTwo, mPbRepairRecordTwo, mCmRepairRecordTwo);
                break;
            case 3:
                MediaRecordUtils.setMediaSource(mMediaPlayer, recordingItemThree, mPbRepairRecordThree, mCmRepairRecordThree);
                break;
            default:
                Log.e(TAG, "isPlayRecord layoutSize不合法");
                break;
        }
        updateSeekBar();
    }

    private void isKeepFile(int layoutSize) {
        Log.e("isKeepFile", "layoutSize" + layoutSize);
        switch (layoutSize) {
            case RepairConstantUtils.REPAIR_RECORD_ITEM_ONE:
                recordingItemOne = new RecordingItem();
                MediaRecordUtils.setRecordItemPath(getApplicationContext(), recordingItemOne, RepairConstantUtils.REPAIR_RECORD_ONE);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_TWO:
                recordingItemTwo = new RecordingItem();
                MediaRecordUtils.setRecordItemPath(getApplicationContext(), recordingItemTwo, RepairConstantUtils.REPAIR_RECORD_TWO);
                break;
            case RepairConstantUtils.REPAIR_RECORD_ITEM_THREE:
                recordingItemThree = new RecordingItem();
                MediaRecordUtils.setRecordItemPath(getApplicationContext(), recordingItemThree, RepairConstantUtils.REPAIR_RECORD_THREE);
                break;
            default:
                Log.e("isKeepFile", "mRecordLayout 不合法");
                break;
        }
    }

    @Override
    public void onSuccess(Object bean, String resultMsg) {
        Log.e(TAG, "获取数据" + resultMsg);

        if (TextUtils.equals(resultMsg, RepairConstantUtils.UP_AUDIO)) {
            mAudioBean = (UpRepairPhotoBean) bean;
            mAudioList.add(mAudioBean.getRecords().get(0).getAudioCode());
            mPresenter.keepDataPosition(TAG, mAudioPathMap, mAudioList);
            if (audioCode == null) {
                audioCode = mAudioBean.getRecords().get(0).getAudioCode();
            } else {
                audioCode = audioCode + "," + mAudioBean.getRecords().get(0).getAudioCode();
            }

            Log.e("audioCode", audioCode);

        } else if (TextUtils.equals(resultMsg, RepairConstantUtils.UP_PIC)) {
            photoBean = (UpRepairPhotoBean) bean;
            if (photoBean != null) {
                if (!TextUtils.isEmpty(picCode)) {
                    picCode = picCode + "," + photoBean.getRecords().get(0).getPicCode();
                } else {
                    picCode = photoBean.getRecords().get(0).getPicCode();
                }
            }
            Log.e("picCode", picCode);
        } else {
            ToastUtils.showToastSafe(this, resultMsg);
            dismissSvpDilog(0);
            finish();
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
        map.put("picCodes", picCode);
        map.put("audioCodes", audioCode);

        Log.e("传输数据", map.toString());
        return map;
    }

    @Override
    public void onError(String result) {
        Log.e(TAG, "请求结果 " + result);
    }

    private void setConfigureStop() {
        switch (mCurrentItem) {
            case 1:
                stopPlaying(mPbRepairRecord, mCmRepairRecord);
                break;
            case 2:
                stopPlaying(mPbRepairRecordTwo, mCmRepairRecordTwo);
                break;
            case 3:
                stopPlaying(mPbRepairRecordThree, mCmRepairRecordThree);
                break;
            default:
                break;

        }
    }

    private void stopPlaying(ProgressBar progressBar, Chronometer chronometer) {
        mHandler.removeCallbacks(mRunnable);
        if (mMediaPlayer != null) {
            try {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
            } catch (IllegalStateException e) {
                mMediaPlayer = null;
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.stop();
            }

            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        MediaRecordUtils.cleanData(chronometer, progressBar);
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        super.onEventMainThread(event);
        switch (event.getEventType()) {
            case RepairConstantUtils.NET_POST_DETECTION_IMAGE:
                //更新图片
                mImageList.addAll(PowerApplication.getmDataList());
                mImageListAdapter.setData(mImageList);
                Log.e("路径", Objects.requireNonNull(PowerApplication.getmDataList().get(0)).sourcePath);
                mPresenter.upInformationPhoto(PowerApplication.getmDataList().get(0).sourcePath,mTaskCode, picType, "测试");
                break;

            case RepairConstantUtils.NET_OMCONSTRUCTION_PHASEMATERIAL_SUCCESS:
                finish();
                break;

            case RepairConstantUtils.NET_OMCOMPLETION_PHASEMATERIAL_SUCCESS:
                finish();
                break;
            case RepairConstantUtils.DELETE_IMAGE_SUCCESS:
                if (!TextUtils.isEmpty(picCode)) {
                    isShowDelete = false;
                    mImageListAdapter.setShowDelete(false);
                    Log.e(TAG, "picCode " + picCode);
                    mPresenter.deleteInformationPicture(picCode);
                }
                break;
            case RepairConstantUtils.DELETE_IMAGE:

                break;
            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMediaPlayer != null) {
            setConfigureStop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mMediaPlayer != null) {
            setConfigureStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mMediaPlayer != null) {
            setConfigureStop();
        }
    }
}
