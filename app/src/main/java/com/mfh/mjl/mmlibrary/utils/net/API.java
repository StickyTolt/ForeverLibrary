package com.mfh.mjl.mmlibrary.utils.net;

import com.martin.alllibrary.netUtil.basic.BasicResponse;
import com.mfh.mjl.mmlibrary.utils.net.model.MeiZi;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;


public interface API {
    /**
     * 网络请求超时时间毫秒
     */
    int DEFAULT_TIMEOUT = 20000;

    String HOST = "http://gank.io/";
    String API_SERVER_URL = HOST + "api/data/";


    @GET("福利/10/1")
    Observable<BasicResponse<List<MeiZi>>> getMezi();

    @Headers("Cache-Control: public, max-age=100")//设置缓存 缓存时间为100s
    @GET("everySay/selectAll.do")
    Observable<BasicResponse<List<MeiZi>>> lookBack(@Query("page") int page, @Query("rows") int number);


    @POST("upload/uploadFile.do")
    Observable<BasicResponse> uploadFiles(@Part("filename") String description,
                                          @Part("pic\"; filename=\"image1.png") RequestBody imgs1,
                                          @Part("pic\"; filename=\"image2.png") RequestBody imgs2);

    @POST("upload/uploadFile.do")
    Observable<BasicResponse> uploadFiles(@Part("filename") String description, @PartMap() Map<String, RequestBody> maps);

}
