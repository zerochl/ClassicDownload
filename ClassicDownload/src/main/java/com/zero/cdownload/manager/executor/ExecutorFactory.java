package com.zero.cdownload.manager.executor;

import com.zero.cdownload.config.ThreadPoolConfig;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class ExecutorFactory {
    // 构造方法私有化
    private ExecutorFactory() {
    }

    /**
     * 按需创建线程池
     * @param threadPoolConfig
     * @return
     */
    public static Executor newFixedThreadPool(ThreadPoolConfig threadPoolConfig) {
        return new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaximumPoolSize(), threadPoolConfig.getKeepAliveTime(), TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 创建单线程池
     * @return
     */
    public static Executor newSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }

}
