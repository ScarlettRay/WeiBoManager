package xyz.iamray.flow.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author winray
 * @since v1.0.1
 * 爬虫的外部线程池
 */
public class SpiderPool {

    public static ExecutorService executorService = new ThreadPoolExecutor(
            3,
            8,
            5000, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(100),new ThreadPoolExecutor.AbortPolicy());
}
