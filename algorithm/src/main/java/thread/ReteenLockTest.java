package thread;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by weijiang
 * Date: 2018/1/4
 * DESC:
 */
public class ReteenLockTest {

    int count = 0;
    private ReentrantLock lock;
    private ArrayList<Thread> threadArrayList = new ArrayList<>();

    public static void main(String[] args) {

            ReteenLockTest test = new ReteenLockTest();
            test.doBusiness();


    }

    synchronized void doBusiness() {

        for(int x=0; x<10; x++) {
            RunTask rt = new RunTask("runTask_"+x);
            Thread t = new Thread(rt);
            t.setName("myThread_"+x);
            threadArrayList.add(t);
        }

        for(Thread t : threadArrayList) {
            t.start();
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        for (Thread thread : threadArrayList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("the result count is :"+count);
    }




    class RunTask implements Runnable{
        private String runName;

        public RunTask(String runName) {
            this.runName = runName;
        }

        @Override
        public void run() {
                System.out.println("CurrentThread is :"+ Thread.currentThread().getName());
                count++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("WakeUpThread is :"+ Thread.currentThread().getName()+"当前值 count= "+getCount());
            }

        }

//        public String getRunName() {
//            return runName;
//        }
//
//        public void setRunName(String runName) {
//            this.runName = runName;
//        }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
