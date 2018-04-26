package com.zero.cdownload.demo;

import android.app.Application;

import com.zero.cdownload.CDownload;
import com.zero.cdownload.config.CDownloadConfig;

import zlc.season.rxdownload3.core.DownloadConfig;
import zlc.season.rxdownload3.extension.ApkInstallExtension;
import zlc.season.rxdownload3.extension.ApkOpenExtension;

/**
 * @author caizhixing
 * @date 3/15/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DownloadConfig.Builder builder = DownloadConfig.Builder.Companion.create(this)
                .enableDb(false)
//                .setDbActor(new CustomSqliteActor(this))
                .enableService(false)
                .enableNotification(false)
                .addExtension(ApkInstallExtension.class)
                .addExtension(ApkOpenExtension.class);

        DownloadConfig.INSTANCE.init(builder);

        CDownloadConfig downloadConfig = CDownloadConfig.build();
        downloadConfig.setDiskCachePath("/sdcard/Download");

        CDownload.getInstance().init(downloadConfig);
    }
}

