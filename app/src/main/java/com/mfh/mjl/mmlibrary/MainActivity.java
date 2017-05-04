package com.mfh.mjl.mmlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mfh.mjl.mmlibrary.total.TotalShowActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openNewEarth(View view) {
        TotalShowActivity.start(this);
    }
}
