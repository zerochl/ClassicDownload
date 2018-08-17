package com.zero.cdownload.listener;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public interface CDownloadListener {
    void onPreStart();
    void onProgress(long maxSize, long currentSize);
    void onComplete(String localFilePath);
    void onError(String errorMessage);
    void onCancel();
}
