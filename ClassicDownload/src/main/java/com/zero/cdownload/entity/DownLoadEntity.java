package com.zero.cdownload.entity;

public class DownLoadEntity {
    private String localFilePath;
    private String tempLocalFilePath;
    private boolean successful;

    public DownLoadEntity(String localFilePath, String tempLocalFilePath, boolean downloadResult) {
        this.localFilePath = localFilePath;
        this.tempLocalFilePath = tempLocalFilePath;
        this.successful = downloadResult;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public String getTempLocalFilePath() {
        return tempLocalFilePath;
    }

    public void setTempLocalFilePath(String tempLocalFilePath) {
        this.tempLocalFilePath = tempLocalFilePath;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
