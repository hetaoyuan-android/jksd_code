package com.glens.jksd.activity.activity_detect;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.glens.jksd.R;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.deteect.AddGrounResistanceBean;
import com.glens.jksd.bean.deteect.GroundUpImgBean;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.detection.AddDetectionPresenter;
import com.glens.jksd.network.view.detection.AddDetectionView;
import com.glens.jksd.pictureselector.GlideImageLoader;
import com.glens.jksd.pictureselector.ImagePickerAdapter;
import com.glens.jksd.pictureselector.SelectDialog;
import com.glens.jksd.upimg.HttpStaticApi;
import com.glens.jksd.upimg.LogUtil;
import com.glens.jksd.upimg.RetrofitCallBack;
import com.glens.jksd.upimg.RetrofitHttpUpLoad;
import com.glens.jksd.upimg.StringUtil;
import com.glens.jksd.upimg.ToastUtil;
import com.glens.jksd.utils.RepairConstantUtils;
import com.glens.jksd.utils.StaticUtils;
import com.glens.jksd.utils.ToastUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * 交跨测量  未处理activity
 */
public class DetectionPayMeasureNoDisposeActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener, RetrofitCallBack {

    public static final String TAG = DetectionPayMeasureNoDisposeActivity.class.getSimpleName();


    @Bind(R.id.tv_no_measure_line_name)
    TextView tvNoMeasureLineName;
    @Bind(R.id.tv_no_measure_tower_num)
    TextView tvNoMeasureTowerNum;
    @Bind(R.id.et_no_measure_env)
    EditText etNoMeasureEnv;
    @Bind(R.id.tv_no_measure_distance)
    EditText tvNoMeasureDistance;
    @Bind(R.id.et_describe_content)
    EditText etDescribeContent;
    @Bind(R.id.btn_no_measure_save)
    Button btnNoMeasureSave;
    // intent 传递过来的字段
    private String measureTitle;
    private String measureLineName;
    private String measureTowerNo;

    private Spinner mSpinner;
    private ArrayAdapter<String> adapter;
    private String[] ctype = new String[]{"道路", "铁路", "重要通道 ", "水系 "};
    private int mSelectDartPosition = 1;

    private String mLineId;
    private String mLineVol;
    private String mStartTowerId;
    private String mStartTowerNo;
    private String mEndTowerId;
    private String mEndTowerNo;
    private String mTaskCode;

    private AddDetectionPresenter mPresenter;
    private AddGrounResistanceBean mBean;

    // 图片上传
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter imagePickerAdapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 4;               //允许选择图片最大数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_pay_measure_no_dispose);
        ButterKnife.bind(this);
        initView();
//        initData();
        initImagePicker();
        initWidget();
        requestPermission();
    }

    private void initView() {
        getIntentExtra();
        setTitleText(measureTitle);
        getLlBasetitleBack().setOnClickListener(this);
        btnNoMeasureSave.setOnClickListener(this);
        tvNoMeasureLineName.setText(measureLineName);
        tvNoMeasureTowerNum.setText(measureTowerNo);

        mSpinner = findViewById(R.id.spinner_pay_measure_department);
        adapter = new ArrayAdapter<>(this, R.layout.item_spinner, ctype);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String string = (String) mSpinner.getSelectedItem();
                mSelectDartPosition = mSpinner.getSelectedItemPosition();
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
        measureTitle = extras.getString(StaticUtils.MEASURE_TITLE);
        measureLineName = extras.getString(StaticUtils.MEASURE_LINENAME);
        measureTowerNo = extras.getString(StaticUtils.MEASURE_TOWERNO);
        mTaskCode = extras.getString(StaticUtils.MEASURE_TASK_CODE);
        mLineId = extras.getString(StaticUtils.MEASURE_LINEID);
        mLineVol = extras.getString(StaticUtils.MEASURE_LINEVOL);
        mStartTowerId = extras.getString(StaticUtils.MEASURE_STARTTOWERID);
        mStartTowerNo = extras.getString(StaticUtils.MEASURE_STARTTOWERNO);
        mEndTowerId = extras.getString(StaticUtils.MEASURE_ENDTOWERID);
        mEndTowerNo = extras.getString(StaticUtils.MEASURE_ENDTOWERNO);
    }

    private boolean isEmpty() {
        String mEnvValue = etNoMeasureEnv.getText().toString().trim();
        String mDistance = tvNoMeasureDistance.getText().toString().trim();
        String mDesc = etDescribeContent.getText().toString().trim();
        if (mEnvValue.isEmpty()) {
            ToastUtils.showToastSafe(this, "请输入环境温度");
            return true;
        }
        if (mDistance.isEmpty()) {
            ToastUtils.showToastSafe(this, "请输入交跨距离");
            return true;
        }
        if (selImageList.size() == 0 || selImageList == null) {
            ToastUtils.showToastSafe(this, "请最少选择一张照片");
            return true;

        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_basetitle_back:
                finish();
                break;
            case R.id.btn_no_measure_save:
//                mPresenter.addPayMeasure();
                if (!isEmpty()) {
                    upLoadImage();
                }
            default:
                break;
        }
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_pay_measure);
        selImageList = new ArrayList<>();
        imagePickerAdapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        imagePickerAdapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(imagePickerAdapter);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(DetectionPayMeasureNoDisposeActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(DetectionPayMeasureNoDisposeActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) imagePickerAdapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    imagePickerAdapter.setImages(selImageList);
                }
            }
            Log.i("imagepath1", images.get(0).path);
            for (int i = 0; i < images.size(); i++) {
                pathImage.add(images.get(i).path);
            }

        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    imagePickerAdapter.setImages(selImageList);
                }
            }

        }
    }

    private boolean isPermissionRequested;

    /**
     * Android6.0之后需要动态申请权限
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {

            isPermissionRequested = true;

            ArrayList<String> permissionsList = new ArrayList<>();

            String[] permissions = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };

            for (String perm : permissions) {
                if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(perm)) {
                    permissionsList.add(perm);
                    // 进入到这里代表没有权限.
                }
            }

            if (!permissionsList.isEmpty()) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), 0);
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    protected ProgressDialog waitDialog;

    private void showWaitDialog() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (waitDialog == null || !waitDialog.isShowing()) {
                    waitDialog = new ProgressDialog(DetectionPayMeasureNoDisposeActivity.this);
                    waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    waitDialog.setCanceledOnTouchOutside(false);
                    ImageView view = new ImageView(DetectionPayMeasureNoDisposeActivity.this);
                    view.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    Animation loadAnimation = AnimationUtils.loadAnimation(
                            DetectionPayMeasureNoDisposeActivity.this, R.anim.rotate);
                    view.startAnimation(loadAnimation);
                    loadAnimation.start();
                    view.setImageResource(R.drawable.loading);
                    // waitDialog.setCancelable(false);
                    waitDialog.show();
                    waitDialog.setContentView(view);
                    LogUtil.i("waitDialong.......");
                }

            }
        });

    }

    public void dismissWaitDialog() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (waitDialog != null && waitDialog.isShowing()) {
                    waitDialog.dismiss();
                    waitDialog = null;
                }
            }
        });

    }

    @Override
    public void onResponse(Response response, int method) {
        dismissWaitDialog();
        switch (method) {
            case HttpStaticApi.HTTP_UPLOADIMAGE:
                GroundUpImgBean bean = (GroundUpImgBean) response.body();
                if (bean.isResult()) {
                    ToastUtil.makeShortText(this, bean.getMsg());
                } else {
                    ToastUtil.makeShortText(this, bean.getMsg());
                }
        }
    }

    @Override
    public void onFailure(Response response, int method) {
        dismissWaitDialog();
    }

    private ArrayList<String> pathImage = new ArrayList<>();

    private void upLoadImage() {
        showWaitDialog();
        RetrofitHttpUpLoad retrofitHttpUpLoad = RetrofitHttpUpLoad.getInstance();
        retrofitHttpUpLoad.clear();
        if (images != null) {

            for (int i = 0; i < images.size(); i++) {
                if (!StringUtil.isEmpty(pathImage.get(i))) {
                    retrofitHttpUpLoad = retrofitHttpUpLoad.addParameter("pic" + i, new File(pathImage.get(i)));
                    Log.i("pathImage", "" + pathImage.get(i));
                }
            }
        }
        Map<String, RequestBody> params = retrofitHttpUpLoad
                .addParameter("taskCode", mTaskCode)
                .addParameter("crossType", String.valueOf(mSelectDartPosition + 1))
                .addParameter("ambientTemperature", etNoMeasureEnv.getText().toString().trim())
                .addParameter("crossDis", tvNoMeasureDistance.getText().toString().trim())
                .addParameter("lineId", mLineId)
                .addParameter("lineVol", mLineVol)
                .addParameter("startTowerId", mStartTowerId)
                .addParameter("startTowerNo", mStartTowerNo)
                .addParameter("endTowerId", mEndTowerId)
                .addParameter("endTowerNo", mEndTowerNo)
                .addParameter("siteDesc", etDescribeContent.getText().toString().trim())
                .bulider();

        retrofitHttpUpLoad.addToEnqueue(retrofitHttpUpLoad.mHttpService.addMeasureRecordAndMeasure(params),
                this, HttpStaticApi.HTTP_UPLOADIMAGE);
    }
}

