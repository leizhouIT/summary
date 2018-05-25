package thread;

/**
 * Created by weijiang
 * Date: 2018/1/5
 * DESC:
 */
public class ThreadJoinTest {

    public static void main(String[] args) {
        ThreadJoinTest test = new ThreadJoinTest();
        test.doExecute();
    }

    void doExecute() {
        System.out.println(Thread.currentThread().getName()+">>>>>>>>>>start>>>>>");
        CustomerThread1 t1 = new CustomerThread1();
        CustomerThread thread = new CustomerThread(t1);

        try {
            t1.start();
            Thread.sleep(2000);
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName()+">>>>>>>>>>end>>>>>");
    }


    class CustomerThread1 extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"...start....");
            for(int x=0; x<5; x++) {
                System.out.println(Thread.currentThread().getName()+" loop at "+x+" times");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"...end....");
        }
    }

    class CustomerThread extends Thread {
        private CustomerThread1 t1;

        public CustomerThread(CustomerThread1 t1) {
            this.t1 = t1;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"%%%start%%");
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"%%%end%%");
        }


        public CustomerThread1 getT1() {
            return t1;
        }

        public void setT1(CustomerThread1 t1) {
            this.t1 = t1;
        }
    }
}
