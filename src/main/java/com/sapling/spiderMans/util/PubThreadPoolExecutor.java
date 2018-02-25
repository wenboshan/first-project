package com.sapling.spiderMans.util;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PubThreadPoolExecutor {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 400, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(30));


    private static PubThreadPoolExecutor instance = new PubThreadPoolExecutor();

    private PubThreadPoolExecutor() {
    }

    public static PubThreadPoolExecutor getInstance() {
        return instance;
    }

    public ThreadPoolExecutor getPoolExecutorInstance() {
        return executor;
    }

    public CompletionService getCompletionService() {
        return new ExecutorCompletionService(executor);
    }


}
