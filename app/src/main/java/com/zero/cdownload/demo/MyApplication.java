package com.zero.cdownload.demo;

import android.app.Application;

import com.zero.cdownload.CDownload;
import com.zero.cdownload.config.CDownloadConfig;
import com.zero.cdownload.config.ConnectConfig;
import com.zero.cdownload.config.ThreadPoolConfig;

/**
 * @author caizhixing
 * @date 3/15/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CDownloadConfig downloadConfig = CDownloadConfig.build()
                .setDiskCachePath("/sdcard/mimikko/download")
                .setConnectConfig(ConnectConfig.build().setConnectTimeOut(10000).setReadTimeOut(20000))
                .setIoThreadPoolConfig(ThreadPoolConfig.build().setCorePoolSize(4).setMaximumPoolSize(100).setKeepAliveTime(60));

        CDownload.getInstance().init(downloadConfig);
    }
}

