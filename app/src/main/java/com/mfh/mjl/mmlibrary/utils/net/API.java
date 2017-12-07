package com.mfh.mjl.mmlibrary.utils.net;

import com.martin.alllibrary.netUtil.basic.BasicResponse;
import com.mfh.mjl.mmlibrary.utils.net.model.LoginModel;
import com.mfh.mjl.mmlibrary.utils.net.model.UploadModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface API {
    /**
     * 网络请求超时时间毫秒
     */
    int DEFAULT_TIMEOUT = 20000;

    String API_SERVER_URL = "http://118.31.180.5/";
//    String API_SERVER_URL = "http://192.168.1.119:8080/dh-biz-bnky-web/";


    @FormUrlEncoded
    @POST("api/login.json")
    Observable<BasicResponse<LoginModel>> login(@Field("loginName") String name,
                                                @Field("password") String psd,
                                                @Field("deviceId") String deviceId,
                                                @Field("registrationId") String registerId);

    @POST("api/currentUser")
    Observable<BasicResponse> currentUser();


    @Multipart
    @POST("api/upload/android")
    Observable<BasicResponse<List<UploadModel>>> uploadFile(@Part("pic\"; filename=\"image1.png") RequestBody file);

    @Multipart
    @POST("api/upload/android")
    Observable<BasicResponse<List<UploadModel>>> uploadFile(@Part MultipartBody.Part file);

    @Multipart
    @POST("api/upload/android")
    Observable<BasicResponse<List<UploadModel>>> uploadFiles(@Part("pic\"; filename=\"image1.png") RequestBody imgs1,
                                                             @Part("pic\"; filename=\"image2.png") RequestBody imgs2,
                                                             @Part("pic\"; filename=\"image3.png") RequestBody imgs3);


}
