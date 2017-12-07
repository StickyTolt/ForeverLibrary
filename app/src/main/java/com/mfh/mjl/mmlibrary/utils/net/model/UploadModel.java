package com.mfh.mjl.mmlibrary.utils.net.model;

/**
 * 作者：Martin on 2017/12/6 21:37
 * 邮箱：martin0207mfh@163.com
 */
public class UploadModel {

    /**
     * ossUrl : https://static.zhiyundaohe.com/b15e6b9e44d41b00c05a235bd4a258a3.png
     * src : /api/images/2bf2bafbd4774db89768cfdd66086812.png
     * servletPath : /api/images/2bf2bafbd4774db89768cfdd66086812.png
     * id : 2bf2bafbd4774db89768cfdd66086812
     */

    private String ossUrl;
    private String src;
    private String servletPath;
    private String id;

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
