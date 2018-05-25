package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhoulei8 on 2017/3/15.
 */
public class ThreadPoolExceptionDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            //异常被吃掉
//            executorService.submit(new MyTask(100,i));
            // 下面两种执行线程异常不被吃掉
            executorService.execute(new MyTask(100,i));
            //Future submit = executorService.submit(new MyTask(100, i));
//            try {
//                submit.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
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
    }

}
