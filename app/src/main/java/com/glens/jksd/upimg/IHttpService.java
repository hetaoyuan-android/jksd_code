package com.glens.jksd.upimg;

import com.glens.jksd.bean.deteect.GroundUpImgBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 *
 * retrofit接口对象，
 */

public interface IHttpService {

    /**
     *  接地电阻图文一起上传
     * @param params
     * @return
     */
    @Multipart
    @POST("detection/addResistance")
    Call<GroundUpImgBean> upLoadAgree(@PartMap Map<String, RequestBody> params);

    /**
     *  交跨测量图文一起上传
     */
    @Multipart
    @POST("detection/addMeasureRecord")
    Call<GroundUpImgBean> addMeasureRecordAndMeasure(@PartMap Map<String, RequestBody> params);

}
