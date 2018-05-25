package thread;

import java.util.concurrent.*;

/**
 * Created by zhoulei8 on 2017/3/15.
 */
public class TraceThreadPoolExecutor {

    public static void main(String[] args) {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0,Integer.MAX_VALUE,
                0L,TimeUnit.SECONDS,new SynchronousQueue<Runnable>()){
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if (t != null){
                    System.out.println("任务："+r.toString()+" 当前线程"+Thread.currentThread().getName());
                    t.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 10; i++){
            Future<?> submit = threadPoolExecutor.submit(new MyTask(100, 0));
            try {
                submit.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
//            threadPoolExecutor.execute(new MyTask(100,0));

        }
    }
    static class MyTask implements Runnable{

        private int a,b;
        public MyTask(int a,int b) {
            this.a=a;
            this.b=b;
        }

        @Override
        public void run() {

            double i = a / b;
            System.out.println("结果为："+i);
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
    /*public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(warp(command,clientTrace(),Thread.currentThread().getName()));
    }

    private Exception clientTrace(){
        return new Exception("Client stack trace");
    }

    private Runnable warp(final Runnable task,final Exception exception,String threadName) {

        return new Runnable()   {
//            @Override
            public void run() {
                try {
                    task.run();
                } catch (Exception e1) {
                    exception.printStackTrace();
                    throw e1;
                }
            }
        };
    }*/


}
