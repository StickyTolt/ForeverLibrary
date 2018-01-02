package com.mfh.mjl.mmlibrary.uis.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.martin.alllibrary.base.mvc.BaseActivity;
import com.martin.alllibrary.extras.ExtraCode;
import com.martin.alllibrary.util.imgUtil.photoutils.PhotoUtil;
import com.martin.alllibrary.util.mediaUtil.MediaUtil;
import com.martin.alllibrary.util.mediaUtil.recordUtil.AudioRecodeUtils;
import com.martin.alllibrary.view.ActionSheet;
import com.mfh.mjl.mmlibrary.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 代码别删，如果你没搞完，我继续在这个Activity里面进行开发
 */
public class MediaActivity extends BaseActivity {

    @BindView(R.id.img_top_back)
    ImageView imgTopBack;
    @BindView(R.id.txt_top_title)
    TextView txtTopTitle;
    @BindView(R.id.txt_top_right)
    TextView txtTopRight;
    @BindView(R.id.btn_media)
    Button btnMedia;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.btn_upload_first)
    Button btnUploadFirst;
    @BindView(R.id.btn_upload_all)
    Button btnUploadAll;
    private String pathImg;
    private AudioRecodeUtils recodeUtils;

    //=====================================================================

    public static void start(Context context) {
        Intent intent = new Intent(context, MediaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        txtTopTitle.setText("MediaDemo");


    }


    @OnClick({R.id.img_top_back, R.id.btn_media, R.id.btn_upload_first, R.id.btn_upload_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_top_back:
                finish();
                break;
            case R.id.btn_media:
                showMediaAction();
                break;
            case R.id.btn_upload_first:
                break;
            case R.id.btn_upload_all:
                break;
        }
    }

    /**
     * 展示 Media Action
     */
    private void showMediaAction() {
        ArrayList<String> items = new ArrayList<>();
        items.add("选择图片");
        items.add("拍摄图片");
        items.add("选择视频");
        items.add("拍摄视频");
        items.add("选择录音");
        items.add("开启录音");
        ActionSheet.showSheetDialog(getContext(), "cancel", items, true, new ActionSheet.MenuItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        PhotoUtil.pickPhoto(getActivity(), ExtraCode.EXTRA_PICK_PHOTO);
                        break;
                    case 1:
                        pathImg = PhotoUtil.takePhotoStr(getActivity(), ExtraCode.EXTRA_TAKE_PHOTO);
                        break;
                    case 2:
                        MediaUtil.pickVideo(getActivity(), ExtraCode.EXTRA_PICK_VIDEO);
                        break;
                    case 3:
                        MediaUtil.takeVideo(getActivity(), ExtraCode.EXTRA_TAKE_VIDEO);
                        break;
                    case 4:
                        showToast("暂时没做");
                        break;
                    case 5:
                        initRecode();
                        break;
                    default:
                        showToast("未知点击");
                        break;
                }
            }
        });
    }

    /**
     * 初始化 录音
     */
    private void initRecode() {
        recodeUtils = new AudioRecodeUtils();
        recodeUtils.setOnAudioStatusUpdateListener(new AudioRecodeUtils.OnAudioStatusUpdateListener() {
            @Override
            public void onUpdate(double db, long time) {

            }

            @Override
            public void onStop(String filePath) {

            }
        });


    }
}
