package com.zero.cdownload.config;

import com.zero.cdownload.constants.ConfigConstant;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class CDownloadConfig {

    private String diskCachePath;
    //临时文件存放文件夹
    private String tempFolderName;
    //临时文件后缀
    private String tempFileSuffix;
    //io线程池config
    private ThreadPoolConfig ioThreadPoolConfig;

    private ConnectConfig connectConfig;
    // 是否需要检查下载文件的大小
    private boolean needCheckFileLength = ConfigConstant.NEED_CHECK_DOWNLOAD_FILE_LENGTH;

    private CDownloadConfig(){
    }

    public static CDownloadConfig build(){
        return new CDownloadConfig();
    }

    public String getDiskCachePath() {
        return diskCachePath;
    }

    public CDownloadConfig setDiskCachePath(String diskCachePath) {
        this.diskCachePath = diskCachePath;
        return this;
    }

    public String getTempFolderName() {
        return tempFolderName;
    }

    public CDownloadConfig setTempFolderName(String tempFolderName) {
        this.tempFolderName = tempFolderName;
        return this;
    }

    public String getTempFileSuffix() {
        return tempFileSuffix;
    }

    public CDownloadConfig setTempFileSuffix(String tempFileSuffix) {
        this.tempFileSuffix = tempFileSuffix;
        return this;
    }

    public ThreadPoolConfig getIoThreadPoolConfig() {
        return ioThreadPoolConfig;
    }

    public CDownloadConfig setIoThreadPoolConfig(ThreadPoolConfig ioThreadPoolConfig) {
        this.ioThreadPoolConfig = ioThreadPoolConfig;
        return this;
    }

    public ConnectConfig getConnectConfig() {
        return connectConfig;
    }

    public CDownloadConfig setConnectConfig(ConnectConfig connectConfig) {
        this.connectConfig = connectConfig;
        return this;
    }

    public boolean isNeedCheckFileLength() {
        return needCheckFileLength;
    }

    public CDownloadConfig setNeedCheckFileLength(boolean needCheckFileLength) {
        this.needCheckFileLength = needCheckFileLength;
        return this;
    }
}
