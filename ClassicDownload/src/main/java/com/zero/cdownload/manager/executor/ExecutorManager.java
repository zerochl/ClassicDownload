package com.zero.cdownload.manager.executor;

import com.zero.cdownload.config.ThreadPoolConfig;
import com.zero.cdownload.constants.TypeConstant;

import java.util.HashMap;
import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class ExecutorManager {
    private static HashMap<String,Executor> singleExecutorMap = new HashMap<>();
    private static Executor normalExecutor;

    /**
     * 初始化线程池
     * @param threadPoolConfig 如果传入参数为null，会创建default线程池
     */
    public static synchronized void init(ThreadPoolConfig threadPoolConfig){
        if (threadPoolConfig == null) {
            threadPoolConfig = ThreadPoolConfig.getDefaultThreadPoolConfig();
        }
        normalExecutor = ExecutorFactory.newFixedThreadPool(threadPoolConfig);
        //因为是静态变量，防止应用非正常退出之后异常
        clear();
    }

    public static synchronized Scheduler getRxJavaExecutor(int type, String singleThreadPoolKey){
        return Schedulers.from(getExecutor(type, singleThreadPoolKey));
    }

    public static synchronized Executor getExecutor(int type, String singleThreadPoolKey){
        Executor executor;
        switch (type) {
            case TypeConstant.THREAD_POOL_TYPE_IO:
                executor = getNormalExecutor();
                break;
            case TypeConstant.THREAD_POOL_TYPE_SINGLE:
                executor = getSingleExecutorByKey(singleThreadPoolKey);
                break;
            default:
                executor = getNormalExecutor();
                break;
        }
        return executor;
    }

    /**
     * key作为单线程吃的唯一值，不同的key可以产生不同的单线程池
     * @param key
     * @return
     */
    public static synchronized Executor getSingleExecutorByKey(String key){
        Executor singleExecutor = singleExecutorMap.get(key);
        if(null == singleExecutor){
            singleExecutor = ExecutorFactory.newSingleThreadExecutor();
            singleExecutorMap.put(key,singleExecutor);
        }
        return singleExecutor;
    }

    /**
     * 线程池配置，未使用key进行区分，因为意义不大
     * @return
     */
    public static synchronized Executor getNormalExecutor(){
        return normalExecutor;
    }

    public static void clear(){
        singleExecutorMap.clear();
    }

}
