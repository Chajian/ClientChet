package com.yezi.chet.thread;


import java.util.concurrent.*;

/**
 * 使用单件模式保证线程池的方便调用
 * 策略模式增加其功能
 */
public class ChetThreadPool {

    public static ThreadPoolExecutor threadPool = null;
    public static ChetThreadPool chetThreadPool = null;
    public static int cpuNum = 4;//处理器数量
    public static int threadNum = 9;//并发线程数量
    public static int corePoolSize = 8;//核心池数量
    public static int maximumpoolSize = 9;
    long keepAliveTime = 60l;


    private  ChetThreadPool(){
        synchronized (ThreadPoolExecutor.class) {
            cpuNum = Runtime.getRuntime().availableProcessors();//获取处理器数量
            threadNum = cpuNum * 2 + 1;
            corePoolSize = cpuNum * 2;
            maximumpoolSize = corePoolSize + 1;
            threadPool = new ThreadPoolExecutor(corePoolSize, maximumpoolSize, keepAliveTime
                    , TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(300), Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());
        }
    }

    //加入线程池
    public void putRunnable(Runnable runnable){
        threadPool.execute(runnable);
    }

    public static ChetThreadPool getChetThreadPool() {
        if(chetThreadPool == null)
            chetThreadPool = new ChetThreadPool();
        return chetThreadPool;
    }
}
