package com.zero.cdownload.manager.executor;

import com.zero.cdownload.config.ThreadPoolConfig;

import java.util.concurrent.ArrayBlockingQueue;
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
     * 创建可丢弃之前任务的线程池
     * 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     * @param threadPoolConfig
     * @return
     */
    public static Executor newDiscardOldThreadPool(ThreadPoolConfig threadPoolConfig) {
        return new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaximumPoolSize(), threadPoolConfig.getKeepAliveTime(), TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(threadPoolConfig.getCapacity()), new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    /**
     * 创建单线程池
     * @return
     */
    public static Executor newSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }

}
