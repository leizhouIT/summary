package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoulei8 on 2017/3/14.
 */
public class ThreadPoolDemo implements InitializingBean,DisposableBean {

    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolDemo.class);

    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    private final Object lock = new Object();
    private int corePoolSize = 1;

    private int maxPoolSize = Integer.MAX_VALUE;

    private int keepAliveSeconds = 60;

    private boolean allowCoreThreadTimeOut = false;

    private int queueCapacity = Integer.MAX_VALUE;

    private ThreadPoolExecutor threadPoolExecutor;

    private String threadName;

    /**
     * 设置核心线程大小
     * @param corePoolSize
     */
    public void setCorePoolSize(int corePoolSize) {
        synchronized (this.lock) {
            this.corePoolSize = corePoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setCorePoolSize(corePoolSize);
            }
        }
    }

    /**
     * 返回核心线程大小
     * @return
     */
    public int getCorePoolSize() {
        synchronized (this.lock) {
            return this.corePoolSize;
        }
    }

    /**
     * 设置最大线程
     * @param maxPoolSize
     */
    public void setMaxPoolSize(int maxPoolSize) {
        synchronized (this.lock) {
            this.maxPoolSize = maxPoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setMaximumPoolSize(maxPoolSize);
            }
        }
    }

    /**
     * 返回最大线程
     */
    public int getMaxPoolSize() {
        synchronized (this.lock) {
            return this.maxPoolSize;
        }
    }

    /**
     * 设置线程的活动时间
     */
    public void setKeepAliveSeconds(int keepAliveSeconds) {
        synchronized (this.lock) {
            this.keepAliveSeconds = keepAliveSeconds;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setKeepAliveTime(keepAliveSeconds, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * 获取线程的活动时间
     */
    public int getKeepAliveSeconds() {
        synchronized (this.lock) {
            return this.keepAliveSeconds;
        }
    }

    /**
     * 当前线程处理完任务，直到下次任务到来，并且时间是在等待活跃时间之后，是否销毁线程
     * @param allowCoreThreadTimeOut
     */
    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    /**
     * 设置任务队列的大小
     * @param queueCapacity
     */
    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }


    protected ExecutorService initializeExecutor(
            ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {

        BlockingQueue<Runnable> queue = createQueue(this.queueCapacity);
        ThreadPoolExecutor executor  = new ThreadPoolExecutor(
                this.corePoolSize, this.maxPoolSize, this.keepAliveSeconds, TimeUnit.SECONDS,
                queue, threadFactory, new MyRejectedExecutionHandler()){
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if (t != null){
                    System.out.println("任务："+r.toString()+" 当前线程"+Thread.currentThread().getName());
                    t.printStackTrace();
                }
            }
        };
        if (this.allowCoreThreadTimeOut) {
            executor.allowCoreThreadTimeOut(true);
        }

        this.threadPoolExecutor = executor;
        return executor;
    }

    public String threadName(){
        synchronized (this.lock) {
            return threadName;
        }
    }
    public void setThreadName(String name){
        synchronized (this.lock) {
            this.threadName = name;
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initializeExecutor(null,null);
    }

    class MyRejectedExecutionHandler extends RejectedExecutionException implements RejectedExecutionHandler{

        public MyRejectedExecutionHandler(){}
        public MyRejectedExecutionHandler(String msg) {
            super(msg);
        }

        public MyRejectedExecutionHandler(String msg, Throwable cause) {
            super(msg, cause);
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            int size = executor.getQueue().size();
            if (queueCapacity<size){

                logger.error("任务已满,任务:{}"+r.toString()+"当前线程："+executor+" 配置队列长度: "+queueCapacity +" 实际放入任务: "+size);
            }

        }
    }

    class MyThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r,threadName+poolNumber.getAndIncrement());
            return thread;
        }
    }

    /**
     * 创建给定容量的线程池大小
     */
    protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
        if (queueCapacity > 0) {
            return new LinkedBlockingQueue<Runnable>(queueCapacity);
        }
        else {
            return new SynchronousQueue<Runnable>();
        }
    }

    /**
     */
    public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
        return this.threadPoolExecutor;
    }

    /**
     * @see ThreadPoolExecutor#getPoolSize()
     */
    public int getPoolSize() {
        return getThreadPoolExecutor().getPoolSize();
    }

    /**
     * @see ThreadPoolExecutor#getActiveCount()
     */
    public int getActiveCount() {
        return getThreadPoolExecutor().getActiveCount();
    }


    public void execute(Runnable task) {
        Executor executor = getThreadPoolExecutor();
        try {
            executor.execute(task);
        }
        catch (RejectedExecutionException ex) {
            logger.error("当前线程:{}",Thread.currentThread().getName()+"异常任务："+task+" 异常:"+ex);
            throw new MyRejectedExecutionHandler("线程运行异常",ex);
        }
    }

    public void execute(Runnable task, long startTimeout) {
        execute(task);
    }

    public Future<?> submit(Runnable task) {
        ExecutorService executor = getThreadPoolExecutor();
        try {
            return executor.submit(task);
        }
        catch (RejectedExecutionException ex) {

            logger.error("当前线程:{}",Thread.currentThread().getName()+"异常任务："+task+" 异常:"+ex);
            throw new MyRejectedExecutionHandler("线程运行异常",ex);
        }
    }

    public <T> Future<T> submit(Callable<T> task) {
        ExecutorService executor = getThreadPoolExecutor();
        try {
            return executor.submit(task);
        }
        catch (RejectedExecutionException ex) {
            logger.error("当前线程:{}",Thread.currentThread().getName()+"异常任务："+task+" 异常:"+ex);
            throw new MyRejectedExecutionHandler("线程运行异常",ex);
        }
    }



    public boolean prefersShortLivedTasks() {
        return true;
    }
    public void destroy() {
        shutdown();
    }

    /**
     * Perform a shutdown on the ThreadPoolExecutor.
     * @see ExecutorService#shutdown()
     * @see ExecutorService#shutdownNow()
     */
    public void shutdown() {
        getThreadPoolExecutor().shutdown();
    }
}
