package com.zero.cdownload.constants;

/**
 * Created by zero on 2018/4/26.
 *
 * @author zero
 */

public class ExecutorConstant {
    /**
     * corePoolSize 池中所保存的线程数，包括空闲线程。
     */
    public static final int CORE_POOL_SIZE = 4;
    /**
     * maximumPoolSize - 池中允许的最大线程数(采用LinkedBlockingQueue时没有作用)。
     */
    public static final int MAXIMUM_POOL_SIZE = 100;
    /**
     * keepAliveTime -当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间，线程池维护线程所允许的空闲时间
     * MILLISECONDS
     */
    public static final int KEEP_ALIVE_TIME = 60;

    public static final String SINGLE_THREAD_POOL_TYPE_DEFAULE = "DEFAULT_CLASSIC_DOWNLOAD_SINGLE_THREAD_POOL_TYPE";
}
