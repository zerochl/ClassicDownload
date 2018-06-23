# ClassisDownload
找了一天RxDownload的bug，解决不了，懒得去看他的实现，自己写了一套基于RxJava的下载，支持线程池管理、断点续传、下载完成校验、下载过程中断、下载文件名MD5化（TaskEntity build时可选择是否使用）
# 使用说明

依赖集成
``` java
root gradle 添加：
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
依赖添加：
dependencies {
	        implementation 'com.github.zerochl:ClassicDownload:1.0.4'
	}
```

* 初始化
```language_key 
CDownloadConfig downloadConfig = CDownloadConfig.build()
    
        .setDiskCachePath("/sdcard/Download")
        
        .setConnectConfig(ConnectConfig.build().setConnectTimeOut(10000).setReadTimeOut(20000))
        
        .setIoThreadPoolConfig(ThreadPoolConfig.build().setCorePoolSize(4).setMaximumPoolSize(100).setKeepAliveTime(60));

CDownload.getInstance().init(downloadConfig);
```
初始化简单易懂，看方法字面意思即可。
* 使用
```language_key 
CDownload.getInstance().create("http://p5.qhimg.com/dr/72__/t01a362a049573708ae.png", new CDownloadListener() {
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
                        Log.e("HongLi", "onError");
                    }

                    @Override
                    public void onCancel() {
                        Log.e("HongLi", "onCancel");
                    }
                });
CDownload.getInstance().start("http://p5.qhimg.com/dr/72__/t01a362a049573708ae.png");
```
create提供多种重载函数，同时也提供可Entity参数传入，entity支持build模式，兼容了两种风格的使用了吧。

下载的URL作为下载任务的唯一key

# 作者联系方式：QQ：975804495
# 疯狂的程序员群：186305789，没准你能遇到绝影大神
