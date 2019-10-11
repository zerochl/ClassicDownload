package com.zero.cdownload.entity;

import com.zero.cdownload.listener.CDownloadListener;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class CDownloadTaskEntity {
    private String url;
    private CDownloadListener downloadListener;
    private boolean hasCancel = false;
    private int threadPoolType;
    private String singleThreadPoolKey;
    private boolean needMD5Name = false;
    private boolean supportRedirects = false;

    public CDownloadTaskEntity() {
    }

    public CDownloadTaskEntity(String url, CDownloadListener downloadListener) {
        this.url = url;
        this.downloadListener = downloadListener;
    }

    public CDownloadTaskEntity(String url, CDownloadListener downloadListener, int threadPoolType) {
        this.url = url;
        this.downloadListener = downloadListener;
        this.threadPoolType = threadPoolType;
    }

    public CDownloadTaskEntity(String url, CDownloadListener downloadListener, int threadPoolType, String singleThreadPoolKey) {
        this.url = url;
        this.downloadListener = downloadListener;
        this.threadPoolType = threadPoolType;
        this.singleThreadPoolKey = singleThreadPoolKey;
    }

    public static CDownloadTaskEntity build() {
        return new CDownloadTaskEntity();
    }

    public String getUrl() {
        return url;
    }

    public CDownloadTaskEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public CDownloadListener getDownloadListener() {
        return downloadListener;
    }

    public CDownloadTaskEntity setDownloadListener(CDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
        return this;
    }

    public boolean isHasCancel() {
        return hasCancel;
    }

    public CDownloadTaskEntity setHasCancel(boolean hasCancel) {
        this.hasCancel = hasCancel;
        return this;
    }

    public int getThreadPoolType() {
        return threadPoolType;
    }

    public CDownloadTaskEntity setThreadPoolType(int threadPoolType) {
        this.threadPoolType = threadPoolType;
        return this;
    }

    public String getSingleThreadPoolKey() {
        return singleThreadPoolKey;
    }

    public CDownloadTaskEntity setSingleThreadPoolKey(String singleThreadPoolKey) {
        this.singleThreadPoolKey = singleThreadPoolKey;
        return this;
    }

    public boolean isNeedMD5Name() {
        return needMD5Name;
    }

    public CDownloadTaskEntity setNeedMD5Name(boolean needMD5Name) {
        this.needMD5Name = needMD5Name;
        return this;
    }

    public boolean isSupportRedirects() {
        return supportRedirects;
    }

    /**
     * 支持重定向
     *
     * @param supportRedirects 支持重定向
     * @return CDownloadTaskEntity
     */
    public CDownloadTaskEntity setSupportRedirects(boolean supportRedirects) {
        this.supportRedirects = supportRedirects;
        return this;
    }
}
