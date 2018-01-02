package com.martin.alllibrary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.martin.alllibrary.R;
import com.martin.alllibrary.activity.adapter.SelectCityAdapter;
import com.martin.alllibrary.activity.adapter.SelectCountyAdapter;
import com.martin.alllibrary.activity.adapter.SelectProvinceAdapter;
import com.martin.alllibrary.activity.model.AreaModel;
import com.martin.alllibrary.base.mvc.BaseActivity;
import com.martin.alllibrary.extras.ExtraName;

import java.util.List;

public class SelectAreaActivity extends BaseActivity {

    private ImageView imgTopBack;
    private TextView txtTopTitle;
    private ListView lvProvince;
    private ListView lvCity;
    private ListView lvCounty;
    private TextView txtConfirm;
    private AreaModel model;
    private SelectProvinceAdapter adapterProvince;
    private SelectCityAdapter adapterCity;
    private SelectCountyAdapter adapterCounty;
    private String strProvince = "";
    private String strCity = "";
    private String strCounty = "";

    public static void startForResult(Activity activity, int code) {
        Intent intent = new Intent(activity, SelectAreaActivity.class);
        activity.startActivityForResult(intent, code);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);

        init();
    }

    private void init() {
        initView();
        initData();

        txtTopTitle.setText("选择地区");
        adapterProvince = new SelectProvinceAdapter(null, getContext());
        lvProvince.setAdapter(adapterProvince);
        adapterCity = new SelectCityAdapter(null, getContext());
        lvCity.setAdapter(adapterCity);
        adapterCounty = new SelectCountyAdapter(null, getContext());
        lvCounty.setAdapter(adapterCounty);

        initAdapter();
        setListener();
    }

    /**
     * 根据数据设置 适配器
     */
    private void initAdapter() {
        if (model != null) {
            adapterProvince.refreshRes(model.getProvince());
            AreaModel.ProvinceBean checkItem = adapterProvince.getCheckItem();
            setCityList(checkItem);
        }
    }

    /**
     * 根据选中的省，刷新市
     */
    private void setCityList(AreaModel.ProvinceBean checkItem) {
        if (checkItem != null && checkItem.getCity() != null && checkItem.getCity() != null) {

            List<AreaModel.ProvinceBean.CityBean> list = checkItem.getCity();
            if (list.size() > 0) {
                adapterCity.refreshRes(list);
                adapterCity.setCheck(0);
                AreaModel.ProvinceBean.CityBean cityBean = adapterCity.getCheckItem();
                setCountyList(cityBean);
            }
        }
    }

    /**
     * 根据选择的市，刷新县
     */
    private void setCountyList(AreaModel.ProvinceBean.CityBean cityBean) {
        if (cityBean != null && cityBean.getDistrict() != null && cityBean.getDistrict().size() > 0) {
            List<AreaModel.ProvinceBean.CityBean.DistrictBean> list = cityBean.getDistrict();
            // 判断 县区 列表第一条是否有 “不限” 这个条目，没有就添加
            if (list != null && list.size() > 0) {
                if (!list.get(0).getName().equals("不限")) {
                    AreaModel.ProvinceBean.CityBean.DistrictBean bean = new AreaModel.ProvinceBean.CityBean.DistrictBean();
                    bean.setName("不限");
                    bean.setZipcode("00000000");
                    list.add(0, bean);
                }
            }
            adapterCounty.refreshRes(list);
            adapterCounty.setCheck(0);
        }
    }


    /**
     * 设置监听
     */
    private void setListener() {
        imgTopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult();
            }
        });

        lvProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapterProvince.setCheck(position);
                setCityList(adapterProvince.getItem(position));
            }
        });

        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapterCity.setCheck(position);
                setCountyList(adapterCity.getCheckItem());
            }
        });

        lvCounty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapterCounty.setCheck(position);
            }
        });

    }

    /**
     * 设置返回值
     */
    private void setResult() {

        AreaModel.ProvinceBean provinceBean = adapterProvince.getCheckItem();
        if (provinceBean != null) {
            strProvince = provinceBean.getName();
        }
        AreaModel.ProvinceBean.CityBean cityBean = adapterCity.getCheckItem();
        if (cityBean != null) {
            strCity = cityBean.getName();
        }
        AreaModel.ProvinceBean.CityBean.DistrictBean districtBean = adapterCounty.getCheckItem();
        if (districtBean != null) {
            strCounty = districtBean.getName();
        }

        Intent intent = new Intent();
        intent.putExtra(ExtraName.EXTRA_NAME_PROVINCE, strProvince);
        intent.putExtra(ExtraName.EXTRA_NAME_CITY, strCity);
        intent.putExtra(ExtraName.EXTRA_NAME_COUNTY, strCounty.equals("不限") ? "" : strCounty);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 绑定View
     */
    private void initView() {
        imgTopBack = findView(R.id.img_top_back);
        txtTopTitle = findView(R.id.txt_top_title);
        lvProvince = findView(R.id.lv_province);
        lvCity = findView(R.id.lv_city);
        lvCounty = findView(R.id.lv_county);
        txtConfirm = findView(R.id.txt_confirm);
    }


    /**
     * 初始化数据
     */
    private void initData() {
        String areaResource = getResources().getString(R.string.country_resource);
        model = new Gson().fromJson(areaResource.replaceAll(" ", ""), AreaModel.class);
    }

}
