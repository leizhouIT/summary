package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.concurrent.ExecutorConfigurationSupport;
import org.springframework.util.Assert;

import java.util.concurrent.*;

/**
 * Created by zhoulei8 on 2017/3/15.
 */
public class MyThreadPoolTaskExecutor extends ExecutorConfigurationSupport implements SchedulingTaskExecutor {


    private final static Logger LOGGER = LoggerFactory.getLogger(MyThreadPoolTaskExecutor.class);

    private final Object poolSizeMonitor = new Object();

    /**
     * 核心线程数
     * */
    private int corePoolSize = 1;

    /**
     * 最大线程数
     */
    private int maxPoolSize = Integer.MAX_VALUE;

    /**
     * 空闲线程活跃时间
     */
    private int keepAliveSeconds = 60;

    /**
     * 当前线程处理完任务，直到下次任务到来，并且时间是在等待活跃时间之后，是否销毁线程(包括核心线程)
     */
    private boolean allowCoreThreadTimeOut = false;

    /**
     * 队列的最大长度
     */
    private int queueCapacity = Integer.MAX_VALUE;

    /**
     * 执行任务的线程池
     */
    private ThreadPoolExecutor threadPoolExecutor;


    public void setCorePoolSize(int corePoolSize) {
        synchronized (this.poolSizeMonitor) {
            this.corePoolSize = corePoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setCorePoolSize(corePoolSize);
            }
        }
    }

    public int getCorePoolSize() {
        synchronized (this.poolSizeMonitor) {
            return this.corePoolSize;
        }
    }

    public void setMaxPoolSize(int maxPoolSize) {
        synchronized (this.poolSizeMonitor) {
            this.maxPoolSize = maxPoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setMaximumPoolSize(maxPoolSize);
            }
        }
    }

    public int getMaxPoolSize() {
        synchronized (this.poolSizeMonitor) {
            return this.maxPoolSize;
        }
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        synchronized (this.poolSizeMonitor) {
            this.keepAliveSeconds = keepAliveSeconds;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setKeepAliveTime(keepAliveSeconds, TimeUnit.SECONDS);
            }
        }
    }



    public int getKeepAliveSeconds() {
        synchronized (this.poolSizeMonitor) {
            return this.keepAliveSeconds;
        }
    }

    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }


    /**
     * 初始化线程池
     * @param threadFactory
     * @param rejectedExecutionHandler
     * @return
     */
    //protected
    public ExecutorService initializeExecutor(
            ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {

        BlockingQueue<Runnable> queue = createQueue(this.queueCapacity);
        ThreadPoolExecutor executor  = new ThreadPoolExecutor(
                this.corePoolSize, this.maxPoolSize, this.keepAliveSeconds, TimeUnit.SECONDS,
                queue, threadFactory, rejectedExecutionHandler){
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if (t != null){
                    t.printStackTrace();
                    LOGGER.error("当前线程:"+Thread.currentThread().getName()+" 执行任务错误"+" 任务内容: "+r.toString());
                }
            }
        };
        if (this.allowCoreThreadTimeOut) {
            executor.allowCoreThreadTimeOut(true);
        }

        this.threadPoolExecutor = executor;
        return executor;
    }
    protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
        if (queueCapacity > 0) {
            return new LinkedBlockingQueue<Runnable>(queueCapacity);
        }
        else {
            return new SynchronousQueue<Runnable>();
        }
    }

    public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
        Assert.state(this.threadPoolExecutor != null, "ThreadPoolTaskExecutor not initialized");
        return this.threadPoolExecutor;
    }

    public int getPoolSize() {
        return getThreadPoolExecutor().getPoolSize();
    }

    public int getActiveCount() {
        return getThreadPoolExecutor().getActiveCount();
    }


    public void execute(Runnable task) {
        getThreadPoolExecutor().execute(task);
    }

    public void execute(Runnable task, long startTimeout) {
        execute(task);
    }

    public Future<?> submit(Runnable task) {
            return getThreadPoolExecutor().submit(task);
    }

    public <T> Future<T> submit(Callable<T> task) {
            return getThreadPoolExecutor().submit(task);
    }

    public boolean prefersShortLivedTasks() {
        return true;
    }


}
