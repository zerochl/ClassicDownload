package com.zero.cdownload.util;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class FileUtil {
    public static String getName(String path) {
        return new File(path).getName();
    }

    /**
     * 如果文件夹不存在会自动创建
     *
     * @param folderPath
     * @return
     */
    public static File getFolderByPath(String folderPath) {
        if (TextUtils.isEmpty(folderPath)) {
            return null;
        }
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    public static boolean isExist(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return file.exists();
    }

    public static boolean deleteFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return deleteFile(file);
    }

    public static boolean deleteFile(File file) {
        if (null == file) {
            return false;
        }
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取本地文件的length
     * @param localFilePath
     * @return
     */
    public static long getLocalFileLength(String localFilePath) {
        if (TextUtils.isEmpty(localFilePath) || !isExist(localFilePath)) {
            return 0;
        }
        return new File(localFilePath).length();
    }

    /**
     * 去服务端请求文件大小
     *
     * @param url
     * @return
     */
    public static int getNetFileLength(String url) {
        if (TextUtils.isEmpty(url)) {
            return 0;
        }
        try {
            return getNetFileLength(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 去服务端请求文件大小
     *
     * @param url
     * @return
     */
    public static int getNetFileLength(URL url) {
        if (null == url) {
            return 0;
        }
        int size = 0;
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            size = con.getContentLength();
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            if (null != con) {
                con.disconnect();
            }
        }
        return size;
    }

    /**
     * 给文件重新命名
     * @param fromFilePath 原始文件名
     * @param toFilePath rename之后的文件名
     * @author ChenHongLi
     */
    public static void rename(String fromFilePath,String toFilePath){
        if(TextUtils.isEmpty(fromFilePath) || TextUtils.isEmpty(toFilePath)){
            return;
        }
        File fromFile = new File(fromFilePath);
        File toFile = new File(toFilePath);
        if(fromFile.exists()){
            if(toFile.exists()){
                toFile.delete();
                toFile = new File(toFilePath);
            }
            fromFile.renameTo(toFile);
        }
    }
    /**
     * 文件移动
     * @param localFilePath
     * @param folderPath
     * @return
     */
    public static boolean moveTo(String localFilePath,String folderPath){
        if(TextUtils.isEmpty(localFilePath) || TextUtils.isEmpty(folderPath) || !isExist(localFilePath)){
            return false;
        }
        File folderPathFile = new File(folderPath);
        return moveAs(localFilePath, folderPathFile.getAbsolutePath() + File.separator + new Date().toString() + ".png", folderPath);
    }
    /**
     * 文件移动
     * @param localFilePath
     * @param distanceFilePath
     * @param folderPath
     * @return
     */
    public static boolean moveAs(String localFilePath,String distanceFilePath,String folderPath){
        if(TextUtils.isEmpty(localFilePath) || TextUtils.isEmpty(distanceFilePath) || !isExist(localFilePath) || TextUtils.isEmpty(folderPath)){
            return false;
        }
        File localFile = new File(localFilePath);
        File folderPathFile = new File(folderPath);
        File distanceFile = new File(distanceFilePath);
        if (!folderPathFile.exists())
            folderPathFile.mkdirs();
        return localFile.renameTo(distanceFile);
    }
    /**
     * 文件复制
     * @param localFilePath
     * @param folderPath
     * @return
     */
    public static boolean copyTo(String localFilePath,String folderPath){
        if(TextUtils.isEmpty(localFilePath) || TextUtils.isEmpty(folderPath) || !isExist(localFilePath)){
            return false;
        }
        File folderPathFile = new File(folderPath);
        return copyAs(localFilePath, folderPathFile.getAbsolutePath() + File.separator + new Date().toString() + ".png", folderPath);
    }
    /**
     * 文件复制
     * @param localFilePath
     * @param distanceFilePath
     * @param folderPath
     * @return
     */
    public static boolean copyAs(String localFilePath,String distanceFilePath,String folderPath){
        if(TextUtils.isEmpty(localFilePath) || TextUtils.isEmpty(folderPath) || TextUtils.isEmpty(distanceFilePath) || !isExist(localFilePath)){
            return false;
        }
        File localFile = new File(localFilePath);
        File folderPathFile = new File(folderPath);
        File distanceFile = new File(distanceFilePath);
        if (!folderPathFile.exists())
            folderPathFile.mkdirs();
        int bytesum = 0;
        int byteread = 0;
        InputStream inStream;
        try {
            inStream = new FileInputStream(localFile);
            //读入原文件
            FileOutputStream fs = new FileOutputStream(distanceFile);
            byte[] buffer = new byte[1444];
            while ( (byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread; //字节数 文件大小
//	            System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
}
