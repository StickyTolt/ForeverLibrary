package com.mfh.mjl.mmlibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.martin.alllibrary.activity.PhotoSelectActivity;
import com.martin.alllibrary.base.BaseActivity;
import com.martin.alllibrary.extras.ExtraCode;
import com.martin.alllibrary.extras.ExtraName;
import com.martin.alllibrary.netUtil.basic.BasicResponse;
import com.martin.alllibrary.netUtil.basic.DefaultObserver;
import com.martin.alllibrary.util.imgUtil.photoutils.PhotoUtil;
import com.martin.alllibrary.util.misc.interfacies.OnItemClickListener;
import com.martin.alllibrary.util.showUtil.LogUtils;
import com.mfh.mjl.mmlibrary.R;
import com.mfh.mjl.mmlibrary.adapter.PathListAdapter;
import com.mfh.mjl.mmlibrary.utils.net.NetApply;
import com.mfh.mjl.mmlibrary.utils.net.model.LoginModel;
import com.mfh.mjl.mmlibrary.utils.net.model.UploadModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class NetApplyActivity extends BaseActivity {

    private static final String TAG = NetApplyActivity.class.getSimpleName();

    @BindView(R.id.img_top_back)
    ImageView imgTopBack;
    @BindView(R.id.txt_top_title)
    TextView txtTopTitle;
    @BindView(R.id.txt_top_right)
    TextView txtTopRight;
    @BindView(R.id.btn_apply_net)
    Button btnApplyNet;
    @BindView(R.id.btn_upload_first)
    Button btnUploadFirst;
    @BindView(R.id.btn_upload_all)
    Button btnUploadAll;
    @BindView(R.id.btn_select_picture)
    Button btnSelectPicture;
    @BindView(R.id.rl_content)
    RecyclerView rlContent;

    //=================================================================
    private PathListAdapter adapter;
    private Map<String, RequestBody> bodyMap = new HashMap<>();
    private List<MultipartBody.Part> partList = new ArrayList<>();

    public static void start(Context context) {
        Intent intent = new Intent(context, NetApplyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_apply);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        txtTopTitle.setText("RxJava+Retrofit Demo");

        LinearLayoutManager layout = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rlContent.setLayoutManager(layout);

        adapter = new PathListAdapter(null, getContext());
        rlContent.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    @OnClick({R.id.img_top_back, R.id.btn_apply_net, R.id.btn_upload_first, R.id.btn_upload_all
            , R.id.btn_select_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_top_back:
                finish();
                break;
            case R.id.btn_apply_net:
                login();
                break;
            case R.id.btn_upload_first:
                uploadFile();
                break;
            case R.id.btn_upload_all:
                uploadAll();
                break;
            case R.id.btn_select_picture:
                PhotoUtil.pickPhoto(getActivity(), ExtraCode.EXTRA_PICK_PHOTO);
                break;
        }
    }

    /**
     * 上传所有文件
     */
    private void uploadAll() {
        List<String> data = adapter.getData();

        bodyMap.clear();
        partList.clear();

        LogUtils.e("data.size() = " + data.size());

        Observable.fromIterable(data)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s != null;
                    }
                })
                .map(new Function<String, RequestBody>() {
                    @Override
                    public RequestBody apply(String s) throws Exception {
                        File file = new File(s);
                        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        return body;
                    }
                })
                .subscribe(new Observer<RequestBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestBody requestBody) {
                        bodyMap.put(String.valueOf(bodyMap.size()), requestBody);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (bodyMap.size() < 2) {
                            showToast("文件数量太少了");
                            return;
                        }
                        LogUtils.e("bodyMap.size = " + bodyMap.size());
                        NetApply.getAPI().uploadFiles(bodyMap.get("0"), bodyMap.get("1"),bodyMap.get("2"))
                                .subscribeOn(Schedulers.io())
                                .subscribe(new DefaultObserver<BasicResponse<List<UploadModel>>>(getActivity()) {
                                    @Override
                                    public void onSuccess(BasicResponse<List<UploadModel>> response) {

                                    }
                                });
                    }
                });


        Observable.fromIterable(bodyMap.values())
                .filter(new Predicate<RequestBody>() {
                    @Override
                    public boolean test(RequestBody requestBody) throws Exception {
                        return requestBody != null;
                    }
                })
                .map(new Function<RequestBody, MultipartBody.Part>() {
                    @Override
                    public MultipartBody.Part apply(RequestBody requestBody) throws Exception {
                        return MultipartBody.Part.create(requestBody);
                    }
                })
                .subscribe(new Observer<MultipartBody.Part>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MultipartBody.Part part) {
                        partList.add(part);
                        Log.e(TAG, "uploadAll: 这里 数据长度 " + partList.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void login() {
        NetApply.getAPI()
                .login("xuke352", "zydh123", "123456", "meiyouregisterid")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponse<LoginModel>>(getActivity()) {
                    @Override
                    public void onSuccess(BasicResponse<LoginModel> response) {
                        String name = response.getData().getLoginName();
                        LogUtils.e(name);
                    }
                });
    }

    private void uploadFile() {
        if (adapter.getItemCount() > 0) {
            String path = adapter.getItem(0);
            File file = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part
                    .createFormData("uploadFile", file.getName(), requestFile);
            NetApply.getAPI()
                    .uploadFile(requestFile)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultObserver<BasicResponse<List<UploadModel>>>(getActivity()) {
                        @Override
                        public void onSuccess(BasicResponse<List<UploadModel>> response) {
                            List<UploadModel> data = response.getData();
                            LogUtils.e("上传的数量" + data.size() + "\n 地址是 ： " + data.get(0).getOssUrl());
                        }
                    });
        } else {
            showToast("请先选择图片");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ExtraCode.EXTRA_PICK_PHOTO:
                    String pathImg = data.getStringExtra(ExtraName.EXTRA_DATA);
                    if (pathImg != null && !"".equals(pathImg)) {
                        adapter.addRes(pathImg);
                    }
                    break;
            }
        }
    }
}
