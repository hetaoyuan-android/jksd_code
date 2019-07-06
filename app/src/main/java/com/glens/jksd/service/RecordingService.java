package com.glens.jksd.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.glens.jksd.R;
import com.glens.jksd.utils.MediaRecordUtils;
import com.glens.jksd.utils.PreferenceUtils;
import com.glens.jksd.utils.RepairConstantUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.TimerTask;

/**
 * 录音的 Service
 */

public class RecordingService extends Service {

    private static final String LOG_TAG = "RecordingService";

    private String mFileName = null;
    private String mFilePath = null;

    private MediaRecorder mRecorder = null;

    private long mStartingTimeMillis = 0;
    private long mElapsedMillis = 0;
    private TimerTask mIncrementTimerTask = null;
    private int layoutSize;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        layoutSize = intent.getIntExtra(RepairConstantUtils.REPAIR_RECORD_LAYOUT, 0);
        startRecording();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mRecorder != null) {
            stopRecording(layoutSize);
        }
        super.onDestroy();
    }

    public void startRecording() {
        setFileNameAndPath();

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置音频源
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//设置录制文件输出格式
        mRecorder.setOutputFile(mFilePath);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);//设置录制音频的编码器
        mRecorder.setAudioChannels(1);
        mRecorder.setAudioSamplingRate(44100);//设置采样频率
        mRecorder.setAudioEncodingBitRate(192000);//设置录制音频编码比特率

        try {
            mRecorder.prepare();
            mRecorder.start();
            mStartingTimeMillis = System.currentTimeMillis();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void setFileNameAndPath() {
        int count = 0;
        File f;

        do {
            count++;
            mFileName = getString(R.string.repair_record_scene)
                    + "_" + (System.currentTimeMillis()) + ".mp4";
//            mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFilePath = Objects.requireNonNull(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC)).getAbsolutePath();
            mFilePath += "/SoundRecorder/" + mFileName;
            f = new File(mFilePath);
        } while (f.exists() && !f.isDirectory());
    }

    public void stopRecording(int layoutSize) {
        mRecorder.stop();
        mElapsedMillis = (System.currentTimeMillis() - mStartingTimeMillis);
        mRecorder.release();

        switch (layoutSize) {
            case 1:
                keepRecord(RepairConstantUtils.REPAIR_RECORD_ONE,mFilePath,mElapsedMillis);
                break;
            case 2:
                keepRecord(RepairConstantUtils.REPAIR_RECORD_TWO,mFilePath,mElapsedMillis);
                break;
            case 3:
                keepRecord(RepairConstantUtils.REPAIR_RECORD_THREE,mFilePath,mElapsedMillis);
                break;
            default:
                Log.e("stopRecording","非正常layoutSize");
                break;

        }
        if (mIncrementTimerTask != null) {
            mIncrementTimerTask.cancel();
            mIncrementTimerTask = null;
        }

        mRecorder = null;
    }

    private void keepRecord(String key, String mFilePath, long mElapsedMillis) {
        PreferenceUtils.putString(getApplicationContext(),key,mFilePath);
        MediaRecordUtils.mRecordLayout++;
        Log.e(key + "停止录音路径", mFilePath + " 时长" + mElapsedMillis);
    }

}
