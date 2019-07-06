package com.glens.jksd.activity.activity_task_manage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.RecordingItem;
import com.glens.jksd.service.RecordingService;
import com.glens.jksd.utils.MediaRecordUtils;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.ToastUtils;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.glens.jksd.utils.MediaRecordUtils.mRecordLayout;

/**
 * 新增工作日志
 * 视频不超过15s
 */
public class TaskLogAddActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = TaskLogAddActivity.class.getSimpleName();
    @Bind(R.id.bt_line_select)
    EditText mBtLineSelect;
    @Bind(R.id.tv_tower_select)
    EditText mTvTowerSelect;
    @Bind(R.id.tv_repair_content_title)
    TextView mTvRepairContentTitle;
    @Bind(R.id.et_repair_content)
    EditText mEtRepairContent;
    @Bind(R.id.tv_record_title)
    TextView mTvRecordTitle;
    @Bind(R.id.bt_repair_record)
    ImageView mBtRepairRecord;
    @Bind(R.id.repair_record_line)
    View mRepairRecordLine;
    @Bind(R.id.iv_repair_record_trumpet)
    ImageView mIvRepairRecordTrumpet;
    @Bind(R.id.pb_repair_record)
    ProgressBar mPbRepairRecord;
    @Bind(R.id.cm_repair_record)
    Chronometer mCmRepairRecord;
    @Bind(R.id.iv_repair_record_delete)
    ImageView mIvRepairRecordDelete;
    @Bind(R.id.ry_repair_record)
    RelativeLayout mRyRepairRecord;
    @Bind(R.id.iv_repair_record_trumpet_two)
    ImageView mIvRepairRecordTrumpetTwo;
    @Bind(R.id.pb_repair_record_two)
    ProgressBar mPbRepairRecordTwo;
    @Bind(R.id.cm_repair_record_two)
    Chronometer mCmRepairRecordTwo;
    @Bind(R.id.iv_repair_record_delete_two)
    ImageView mIvRepairRecordDeleteTwo;
    @Bind(R.id.ry_repair_record_two)
    RelativeLayout mRyRepairRecordTwo;
    @Bind(R.id.iv_repair_record_trumpet_three)
    ImageView mIvRepairRecordTrumpetThree;
    @Bind(R.id.pb_repair_record_three)
    ProgressBar mPbRepairRecordThree;
    @Bind(R.id.cm_repair_record_three)
    Chronometer mCmRepairRecordThree;
    @Bind(R.id.iv_repair_record_delete_three)
    ImageView mIvRepairRecordDeleteThree;
    @Bind(R.id.ry_repair_record_three)
    RelativeLayout mRyRepairRecordThree;
    @Bind(R.id.ly_repair_record)
    LinearLayout mLyRepairRecord;
    @Bind(R.id.tv_repair_fix_pic)
    TextView mTvRepairFixPic;
    @Bind(R.id.repair_task_standard_text)
    TextView mRepairTaskStandardText;
    @Bind(R.id.repair_task_standard_ry)
    RelativeLayout mRepairTaskStandardRy;

    private boolean mStartRecording = true;
    private MediaPlayer mMediaPlayer = null;
    private int recordTime = 0;//循环更新的次数
    public int mCurrentItem = 1;//当前播放的录音
    private RecordingItem recordingItemOne;
    private RecordingItem recordingItemTwo;
    private RecordingItem recordingItemThree;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_log_add);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void initView() {
        mHandler = new Handler();
        setTitleText(R.string.task_manager);
        getLlBasetitleBack().setOnClickListener(this);
        mRepairTaskStandardText.setText("提交");
        mBtLineSelect.setHint("请输入日志标题");
        mTvTowerSelect.setHint("请输入地点");
        ButterKnife.bind(this);
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
                ToastUtils.showToastSafe(this,"只能录入三条录音");
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    onRecord(mStartRecording, mRecordLayout);
                }
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

    private void setConfigureStop() {
        switch (mCurrentItem) {
            case 1:
                MediaRecordUtils.stopPlaying(mHandler, mRunnable, mPbRepairRecord, mCmRepairRecord, mMediaPlayer);
                break;
            case 2:
                MediaRecordUtils.stopPlaying(mHandler, mRunnable, mPbRepairRecordTwo, mCmRepairRecordTwo, mMediaPlayer);
                break;
            case 3:
                MediaRecordUtils.stopPlaying(mHandler, mRunnable, mPbRepairRecordThree, mCmRepairRecordThree, mMediaPlayer);
                break;
            default:
                break;

        }
    }


    @OnClick({R.id.iv_repair_record_trumpet, R.id.iv_repair_record_delete, R.id.iv_repair_record_trumpet_two,
            R.id.iv_repair_record_delete_two, R.id.iv_repair_record_trumpet_three, R.id.iv_repair_record_delete_three,
            R.id.repair_task_standard_ry, R.id.bt_repair_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_repair_record_trumpet:
                mCurrentItem = RepairConstantUtils.REPAIR_RECORD_ITEM_ONE;
                isPlayRecord(mCurrentItem);
                break;
            case R.id.iv_repair_record_delete:
                MediaRecordUtils.isDeleteRecord(getApplicationContext(), recordingItemOne, RepairConstantUtils.REPAIR_RECORD_ITEM_ONE, mRyRepairRecord);
                break;
            case R.id.iv_repair_record_trumpet_two:
                mCurrentItem = RepairConstantUtils.REPAIR_RECORD_ITEM_TWO;
                isPlayRecord(mCurrentItem);
                break;
            case R.id.iv_repair_record_delete_two:
                MediaRecordUtils.isDeleteRecord(getApplicationContext(), recordingItemTwo, RepairConstantUtils.REPAIR_RECORD_ITEM_TWO, mRyRepairRecordTwo);
                break;
            case R.id.iv_repair_record_trumpet_three:
                mCurrentItem = RepairConstantUtils.REPAIR_RECORD_ITEM_THREE;
                isPlayRecord(mCurrentItem);
                break;
            case R.id.iv_repair_record_delete_three:
                MediaRecordUtils.isDeleteRecord(getApplicationContext(), recordingItemThree, RepairConstantUtils.REPAIR_RECORD_ITEM_THREE, mRyRepairRecordThree);
                break;
            case R.id.repair_task_standard_ry:
                break;
            case R.id.bt_repair_record:
                isStartRecord(mRecordLayout);
                break;
        }
    }


}
