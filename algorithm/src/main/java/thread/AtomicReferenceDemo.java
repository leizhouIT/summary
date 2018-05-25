package thread;

import demo.MyRunable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhoulei8 on 2017/3/20.
 */
public class AtomicReferenceDemo {

    private final static Logger log = LoggerFactory.getLogger(AtomicReferenceDemo.class);

    public static void main(String[] args) {
        try{
//            int i = 1/0;
            MyRunable str = null;
            System.out.println(str.getClass());
        }catch (Exception e){
            log.error("错误信息id:{},name:{},age:{},yic:{}",123,"aaa",18,e);
        }

    }
    /**
     * 需求：如果数值小于20，增加一次10，只增加一次。
     * 现象:次程序保证线程安全，起初数值为19，增加一次变为29，但是
     * 其中两次连续扣减，导致程序判断失败，又回到19，原因没有记录程序中间状态
     */
    /*static AtomicReference<Integer> money = new AtomicReference<>();
    public static void main(String[] args) {

        money.set(19);
        for (int i=0;i<3;i++){
            new Thread(){
                public void run(){
                        while (true){
                            Integer m = money.get();
                            if (m<20){
                                if (money.compareAndSet(m,m+10)){
                                    System.out.println("增加成功,增加后:"+money.get());
                                    break;
                                }
                            }else {
                                break;
                            }
                        }
                }
            }.start();
        }


        new Thread(){
            @Override
            public void run() {
                for (int i = 0 ;i<100;i++){

                    while (true){
                        Integer m = money.get();
                        if (m>10){
                            if (money.compareAndSet(m,m-10)){
                                System.out.println("扣减成功,扣减后:"+money.get());
                                break;
                            }
                        }else {
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();*/

    /**
     * 对上述需求进行改进
     */

    //设置初始值和中间状态的版本
    /*static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19,0);

    public static void main(String[] args) {

        for (int i=0;i<3;i++){
            //获取版本
            final int stamp = money.getStamp();
            new Thread(){
                public void run(){
                    while (true){
                        Integer m = money.getReference();
                        if (m<20){
                            if (money.compareAndSet(m,m+10,stamp,stamp+1)){
                                System.out.println("增加成功,增加后:"+money.getReference());
                                break;
                            }
                        }else {
                            break;
                        }
                    }
                }
            }.start();
        }


        new Thread(){
            @Override
            public void run() {
                for (int i = 0 ;i<100;i++){

                    while (true){
                        Integer m = money.getReference();
                        int stamp = money.getStamp();
                        if (m>10){
                            if (money.compareAndSet(m,m-10,stamp,stamp+1)){
                                System.out.println("扣减成功,扣减后:"+money.getReference());
                                break;
                            }
                        }else {
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();}*/


}
