package com.martin.alllibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.martin.alllibrary.R;
import com.martin.alllibrary.base.mvc.BaseActivity;
import com.martin.alllibrary.extras.ExtraName;
import com.martin.alllibrary.util.storeUtils.FileStoreUtil.FileStoreUtil;

public class EditActivity extends BaseActivity {

    private EditText etContent;

    public static void saveStart(Context context,String res){
        FileStoreUtil.getInstance().saveStr(res,ExtraName.EXTRA_DATA,context);
        Intent intent = new Intent(context, EditActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, EditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
    }

    private void init() {
        etContent = ((EditText) findViewById(R.id.et_content));
        String data = FileStoreUtil.getInstance().getStr(ExtraName.EXTRA_DATA, getContext());
        etContent.setText(data);
    }
}
