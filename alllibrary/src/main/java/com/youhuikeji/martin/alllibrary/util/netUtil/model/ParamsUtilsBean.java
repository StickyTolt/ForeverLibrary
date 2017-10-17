package com.youhuikeji.martin.alllibrary.util.netUtil.model;

/**
 * 由于封装了接口请求和JSON解析，这里是提交的数据
 */
public class ParamsUtilsBean {

    private String key ;
    private String value ;

    public ParamsUtilsBean(String key, String value){
        this.key = key ;
        this.value = value ;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
