package com.zero.cdownload.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zero.cdownload.CDownload;
import com.zero.cdownload.listener.CDownloadListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
//        requestPermission(MOUNT_UNMOUNT_FILESYSTEMS);
//        requestPermission(INTERNET);

        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                RxDownload.INSTANCE.delete("http://p5.qhimg.com/dr/72__/t01a362a049573708ae.png",true).subscribe();
//                RxDownload.INSTANCE.clear("http://p5.qhimg.com/dr/72__/t01a362a049573708ae.png").subscribe();
//                RxDownload.INSTANCE.create("http://p5.qhimg.com/dr/72__/t01a362a049573708ae.png").subscribe(new Consumer<Status>() {
//                    @Override
//                    public void accept(Status status) throws Exception {
//                        Log.e("HongLi", "RxDownload status:" + status.toString());
//                        if (status instanceof Downloading) {
////                                onProgress(status.getDownloadSize(), status.getTotalSize());
//                        } else if (status instanceof Succeed) {
////                                onComplete();
//                        } else if (status instanceof Failed) {
////                                onError(((Failed) status).getThrowable());
//                        }
//                    }
//                });
//                RxDownload.INSTANCE.start("http://p5.qhimg.com/dr/72__/t01a362a049573708ae.png").subscribe();

                CDownload.getInstance().create("https://oss02.bihu.com/art/AC7356E7C418549577FB57889F02A31C.txt", new CDownloadListener() {
                    @Override
                    public void onPreStart() {
                        Log.e("HongLi", "onPreStart");
                    }

                    @Override
                    public void onProgress(long maxSIze, long currentSize) {
                        Log.e("HongLi", "in onProgress maxSIze:" + maxSIze + ";currentSize:" + currentSize);
                    }

                    @Override
                    public void onComplete(String localFilePath) {
                        Log.e("HongLi", "onComplete localFilePath:" + localFilePath);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.e("HongLi", "onError errorMessage:" + errorMessage);
                    }

                    @Override
                    public void onCancel() {
                        Log.e("HongLi", "onCancel");
                    }
                });
                CDownload.getInstance().start("https://oss02.bihu.com/art/AC7356E7C418549577FB57889F02A31C.txt");
            }
        }, 0);

    }
}
