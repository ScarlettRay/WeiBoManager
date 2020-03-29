package xyz.iamray.flow;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author winray
 * @since v1.0.1
 * 流程管理员
 * 一个流程你可以直接执行，也可以交由FlowManager来执行
 * FlowManager提供定时执行，循环执行，运行结果保存功能
 */
public class FlowManager {

    private static ExecutorService pool = new ThreadPoolExecutor(2,
            8,
            5000, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(50),new ThreadPoolExecutor.AbortPolicy());


}
