package com.zero.cdownload.config;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class ConnectConfig {
    private int connectTimeOut;
    private int readTimeOut;
    private int readBufferSize;

    public static ConnectConfig build(){
        return new ConnectConfig();
    }

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public ConnectConfig setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
        return this;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public ConnectConfig setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
        return this;
    }

    public int getReadBufferSize() {
        return readBufferSize;
    }

    public ConnectConfig setReadBufferSize(int readBufferSize) {
        this.readBufferSize = readBufferSize;
        return this;
    }
}
