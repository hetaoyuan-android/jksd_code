package com.glens.jksd.activity.activity_detect;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.glens.jksd.R;
import com.glens.jksd.activity.activity_repair.stand_add.RepairTowerAddActivity;
import com.glens.jksd.base.BaseActivity;
import com.glens.jksd.bean.deteect.AddGrounResistanceBean;
import com.glens.jksd.bean.deteect.GroundUpImgBean;
import com.glens.jksd.network.PresentView;
import com.glens.jksd.network.view.detection.AddDetectionPresenter;
import com.glens.jksd.network.view.detection.AddDetectionView;
import com.glens.jksd.network.view.detection.GroundResistancePresenter;
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
 * 接地电阻去处理/ 新增
 */
public class DetectionManagerGroundNoDisposeActivity extends BaseActivity implements View.OnClickListener, AddDetectionView, ImagePickerAdapter.OnRecyclerViewItemClickListener, RetrofitCallBack {

    public static final String TAG = RepairTowerAddActivity.class.getSimpleName();

    @Bind(R.id.tv_groung_no_line_name)
    TextView tvGroungNoLineName;
    @Bind(R.id.tv_no_ground_tower_num)
    TextView tvNoGroundTowerNum;
    @Bind(R.id.et_ground_no_a)
    EditText etGroundNoA;
    @Bind(R.id.et_ground_no_b)
    EditText etGroundNoB;
    @Bind(R.id.et_ground_no_c)
    EditText etGroundNoC;
    @Bind(R.id.et_ground_no_d)
    EditText etGroundNoD;
    @Bind(R.id.et_describe_content)
    EditText etDescribeContent;
    @Bind(R.id.btn_ground_no_save)
    Button btnGroundNoSave;

    private String groundTitle;
    private String groundLineName;
    private String groundTowerNum;
    private String mTaskCode;
    private String mLineId;
    private String mLineVol;
    private String mTowerId;

    private AddDetectionPresenter mPresenter;
    private AddGrounResistanceBean mBean;

    // 图片
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 4;               //允许选择图片最大数

    private ArrayList<String> imgPath = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_manager_ground_no_dispose);
        ButterKnife.bind(this);
        initView();
        initData();
        initImagePicker();
        initWidget();
        requestPermission();


    }

    private void initView() {
        getIntentExtra();
        setTitleText(groundTitle);
        tvGroungNoLineName.setText(groundLineName);
        tvNoGroundTowerNum.setText(groundTowerNum);
        getLlBasetitleBack().setOnClickListener(this);
        btnGroundNoSave.setOnClickListener(this);
    }


    private void initData() {
        mPresenter = new AddDetectionPresenter(this);
        mPresenter.onCreate();
        mPresenter.BindPresentView((PresentView) DetectionManagerGroundNoDisposeActivity.this);

    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        groundTitle = extras.getString(StaticUtils.GROUND_TITLE);
        groundLineName = extras.getString(StaticUtils.GROUND_LINENAME);
        groundTowerNum = extras.getString(StaticUtils.GROUND_TOWERNUM);
        mTaskCode = extras.getString(StaticUtils.GROUND_TASK_CODE);
        mLineId = extras.getString(StaticUtils.GROUND_TASK_LINE_ID);
        mLineVol = extras.getString(StaticUtils.GROUND_TASK_LINE_VOL);
        mTowerId = extras.getString(StaticUtils.GROUND_TASK__TOWER_ID);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_basetitle_back:
                finish();
                break;
            case R.id.btn_ground_no_save:
                Log.e("btn_ground_no_save", "btn_ground_no_save");
//                mPresenter.addGroundResistance();
                if (!isEmpty()) {
                    upLoadImage();
                }
            default:
                break;
        }
    }

    private boolean isEmpty() {
        String aValue = etGroundNoA.getText().toString().trim();
        String bValue = etGroundNoB.getText().toString().trim();
        String cValue = etGroundNoC.getText().toString().trim();
        String dValue = etGroundNoD.getText().toString().trim();
        String siteDesc = etDescribeContent.getText().toString().trim();

        if (aValue.isEmpty()) {
            ToastUtils.showToastSafe(this, "请输入电阻值A");
            return true;
        }
        if (bValue.isEmpty()) {
            ToastUtils.showToastSafe(this, "请输入电阻值B");
            return true;
        }
        if (cValue.isEmpty()) {
            ToastUtils.showToastSafe(this, "请输入电阻值C");
            return true;
        }
        if (dValue.isEmpty()) {
            ToastUtils.showToastSafe(this, "请输入电阻值D");
            return true;
        }
        if (siteDesc.isEmpty()) {
            ToastUtils.showToastSafe(this, "请输入现场描述");
            return true;
        }
        if (selImageList.size() == 0 || selImageList == null) {
            ToastUtils.showToastSafe(this, "请最少选择一张照片");
            return true;

        }
        return false;
    }

    @Override
    public void onSuccess(Object bean, String resultMsg, boolean result) {
        Log.e(TAG, "DetectionManagerGroundNoDisposeActivity获取数据" + resultMsg + "   result:" + result);
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
        map.put("erAValue", etGroundNoA.getText().toString().trim());
        map.put("erBValue", etGroundNoB.getText().toString().trim());
        map.put("erCValue", etGroundNoC.getText().toString().trim());
        map.put("erDValue", etGroundNoD.getText().toString().trim());
        map.put("lineId", mLineId);
        map.put("lineVol", mLineVol);
        map.put("towerId", mTowerId);
        map.put("towerNo", groundTowerNum);
        map.put("siteDesc", etDescribeContent.getText().toString().trim());
        map.put("picCodes", "");
        return map;
    }

    @Override
    public void onError(String result) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {            //当然权限多了，建议使用Switch，不必纠结于此
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "权限申请成功", Toast.LENGTH_SHORT).show();
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "权限申请失败，用户拒绝权限", Toast.LENGTH_SHORT).show();
            }
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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
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
                                Intent intent = new Intent(DetectionManagerGroundNoDisposeActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(DetectionManagerGroundNoDisposeActivity.this, ImageGridActivity.class);
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
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
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
                    adapter.setImages(selImageList);
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
                    adapter.setImages(selImageList);
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
                    waitDialog = new ProgressDialog(DetectionManagerGroundNoDisposeActivity.this);
                    waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    waitDialog.setCanceledOnTouchOutside(false);
                    ImageView view = new ImageView(DetectionManagerGroundNoDisposeActivity.this);
                    view.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    Animation loadAnimation = AnimationUtils.loadAnimation(
                            DetectionManagerGroundNoDisposeActivity.this, R.anim.rotate);
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

    //    private String[] pathImage = {"", ""};
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
//        if (!StringUtil.isEmpty(pathImage.get(0))) {
//            retrofitHttpUpLoad = retrofitHttpUpLoad.addParameter("pic1", new File(pathImage.get(0)));
//            Log.i("pathImage", "" + pathImage.get(0));
//        }
//        if (!StringUtil.isEmpty(pathImage.get(1))) {
//            retrofitHttpUpLoad = retrofitHttpUpLoad.addParameter("pic2", new File(pathImage.get(1)));
//            Log.i("pathImage", "" + pathImage.get(1));
//        }
        }
        Map<String, RequestBody> params = retrofitHttpUpLoad
                .addParameter("taskCode", mTaskCode)
                .addParameter("erAValue", etGroundNoA.getText().toString().trim())
                .addParameter("erBValue", etGroundNoB.getText().toString().trim())
                .addParameter("erCValue", etGroundNoC.getText().toString().trim())
                .addParameter("erDValue", etGroundNoD.getText().toString().trim())
                .addParameter("lineId", mLineId)
                .addParameter("lineVol", mLineVol)
                .addParameter("towerId", mTowerId)
                .addParameter("towerNo", groundTowerNum)
                .addParameter("siteDesc", etGroundNoD.getText().toString().trim())
                .addParameter("details", etDescribeContent.getText().toString().trim())
                .bulider();


        retrofitHttpUpLoad.addToEnqueue(retrofitHttpUpLoad.mHttpService.upLoadAgree(params),
                this, HttpStaticApi.HTTP_UPLOADIMAGE);
    }
}
