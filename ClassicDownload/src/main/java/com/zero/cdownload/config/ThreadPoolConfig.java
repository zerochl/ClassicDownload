package com.zero.cdownload.config;

import com.zero.cdownload.constants.ExecutorConstant;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class ThreadPoolConfig {
    /**
     * corePoolSize 池中所保存的线程数，包括空闲线程。
     */
    private int corePoolSize = ExecutorConstant.CORE_POOL_SIZE;

    /**
     * maximumPoolSize - 池中允许的最大线程数(采用LinkedBlockingQueue时没有作用)。
     */
    private int maximumPoolSize = ExecutorConstant.MAXIMUM_POOL_SIZE;

    /**
     * keepAliveTime -当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间，线程池维护线程所允许的空闲时间
     */
    private int keepAliveTime = ExecutorConstant.KEEP_ALIVE_TIME;
    /**
     * "线程池"的阻塞队列容量
     */
    private int capacity = ExecutorConstant.CAPATITY;

    private ThreadPoolConfig() {
    }

    public static ThreadPoolConfig build(){
        return new ThreadPoolConfig();
    }

    public static ThreadPoolConfig getDefaultThreadPoolConfig(){
        return ThreadPoolConfig.build()
                .setCorePoolSize(ExecutorConstant.CORE_POOL_SIZE)
                .setMaximumPoolSize(ExecutorConstant.MAXIMUM_POOL_SIZE)
                .setKeepAliveTime(ExecutorConstant.KEEP_ALIVE_TIME);
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public ThreadPoolConfig setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public ThreadPoolConfig setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public ThreadPoolConfig setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public ThreadPoolConfig setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }
}
