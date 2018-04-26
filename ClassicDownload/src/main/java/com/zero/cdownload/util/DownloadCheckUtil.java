package com.zero.cdownload.util;

import android.text.TextUtils;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class DownloadCheckUtil {
    /**
     * 校验文件下载是否成功
     * @param netUrl
     * @param localFileUrl
     * @return
     */
    public static boolean checkFileDownloadOk(String netUrl,String localFileUrl){
        if (TextUtils.isEmpty(netUrl) || TextUtils.isEmpty(localFileUrl) || !FileUtil.isExist(localFileUrl)) {
            return false;
        }
        if (FileUtil.getNetFileLength(netUrl) == FileUtil.getLocalFileLength(localFileUrl)) {
            return true;
        }
        return false;
    }
}
