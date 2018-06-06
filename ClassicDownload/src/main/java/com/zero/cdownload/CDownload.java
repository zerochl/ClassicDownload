package com.zero.cdownload;

import android.util.Log;

import com.zero.cdownload.config.CDownloadConfig;
import com.zero.cdownload.constants.ExecutorConstant;
import com.zero.cdownload.constants.TypeConstant;
import com.zero.cdownload.entity.CDownloadTaskEntity;
import com.zero.cdownload.listener.CDownloadListener;
import com.zero.cdownload.manager.download.FileManager;
import com.zero.cdownload.manager.executor.ExecutorManager;

import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class CDownload {
    private static CDownload cDownload = new CDownload();
    private CDownloadConfig downloadConfig;
    private ConcurrentHashMap<String, CDownloadTaskEntity> downloadTaskList = new ConcurrentHashMap<>();

    private CDownload() {
    }

    public static CDownload getInstance() {
        return cDownload;
    }

    public CDownload init(CDownloadConfig downloadConfig) {
        this.downloadConfig = downloadConfig;
        ExecutorManager.init(downloadConfig.getIoThreadPoolConfig());
        FileManager.init(downloadConfig);
        return this;
    }

    public void create(String url, CDownloadListener downloadListener) {
        if (downloadTaskList.containsKey(url)) {
            Log.e("HongLi", "had been in task list.");
            return;
        }
        create(url, TypeConstant.THREAD_POOL_TYPE_IO, ExecutorConstant.SINGLE_THREAD_POOL_TYPE_DEFAULE, downloadListener);
    }

    public void create(String url, int threadPoolType, CDownloadListener downloadListener) {
        if (downloadTaskList.containsKey(url)) {
            Log.e("HongLi", "had been in task list.");
            return;
        }
        create(url, threadPoolType, ExecutorConstant.SINGLE_THREAD_POOL_TYPE_DEFAULE, downloadListener);
    }

    public void create(String url, int threadPoolType, String singleThreadPoolKey, CDownloadListener downloadListener) {
        if (downloadTaskList.containsKey(url)) {
            Log.e("HongLi", "had been in task list.");
            return;
        }
        CDownloadTaskEntity newTask = new CDownloadTaskEntity(url, downloadListener, threadPoolType, singleThreadPoolKey);
        downloadTaskList.put(url, newTask);
    }

    public void create(CDownloadTaskEntity downloadTaskEntity) {
        if (downloadTaskEntity == null) {
            Log.e("HongLi", "downloadTaskEntity is null.");
            return;
        }
        create(downloadTaskEntity.getUrl(), downloadTaskEntity.getThreadPoolType(), downloadTaskEntity.getSingleThreadPoolKey(), downloadTaskEntity.getDownloadListener());
    }

    public void start(String url) {
        final CDownloadTaskEntity task = downloadTaskList.get(url);
        if (task == null) {
            Log.e("HongLi", "in start there is not task in task list");
            return;
        }
        Observable
                .create(new ObservableOnSubscribe<CDownloadTaskEntity>() {
                    @Override
                    public void subscribe(ObservableEmitter<CDownloadTaskEntity> observableEmitter) throws Exception {
                        observableEmitter.onNext(task);
                    }
                })
                .subscribeOn(ExecutorManager.getRxJavaExecutor(task.getThreadPoolType(), task.getSingleThreadPoolKey()))
                .subscribe(new Consumer<CDownloadTaskEntity>() {
                    @Override
                    public void accept(CDownloadTaskEntity downloadTaskEntity) throws Exception {
                        FileManager.startDownload(downloadTaskEntity);
                        removeTask(downloadTaskEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        task.getDownloadListener().onError(throwable.getMessage());
                        removeTask(task);
                    }
                });
    }

    public void stop(String url){
        CDownloadTaskEntity task = downloadTaskList.get(url);
        if (task == null) {
            Log.e("HongLi", "in stop there is not task in task list");
            return;
        }
        removeTask(task);
    }

    private void removeTask(CDownloadTaskEntity task){
        if (task == null) {
            Log.e("HongLi", "in removeTask task is null");
            return;
        }
        task.setHasCancel(true);
        downloadTaskList.remove(task.getUrl());
    }
}
