package com.zero.cdownload.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class PathUtil {
    public static String getLocalFilePath(String netUrl, String cacheFolder) {
        if (TextUtils.isEmpty(netUrl) || TextUtils.isEmpty(cacheFolder)) {
            Log.e("HongLi", "in getLocalFilePath netUrl or cacheFolder is empty.");
            return "";
        }
        return FileUtil.getFolderByPath(cacheFolder).getAbsolutePath() + "/" + MD5Util.getMD5String(getUTF8(netUrl));
    }

    /**
     * url必须为/格式不能为\
     *
     * @param url
     * @return
     */
    public static String getUTF8(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        String fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
        String filePath = url.substring(0, url.lastIndexOf("/") + 1);
        try {
            fileName = URLEncoder.encode(new String(fileName.toString().getBytes("UTF-8")), "UTF-8");
            // 如果是中文空格会自动转成+
            fileName = fileName.replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return filePath + fileName;
    }
}
