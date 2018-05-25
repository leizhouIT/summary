package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoulei8 on 2017/3/22.
 */
public class ParallelCalculation {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ParallelCalculation parallelCalculation = new ParallelCalculation();
        int psearch = SearchTask.Psearch(5);
        System.out.println("下标："+psearch);

    }

    static int [] arr={0,1,1,2,5,6,7,9,8,6,8,7,5,6};//需要查找的原始数组
    static ExecutorService taskExecutor = null;
//    static
    static final int THREAD_NUM = 2;
    //存放查找到的元素的下标
    static AtomicInteger integer = new AtomicInteger(-1);
    public ParallelCalculation(){
        taskExecutor = Executors.newFixedThreadPool(8);
//        taskExecutor = new MyThreadPoolTaskExecutor();
//        taskExecutor.setMaxPoolSize(4);
//        taskExecutor.setKeepAliveSeconds(60);
//        taskExecutor.setAllowCoreThreadTimeOut(false);
//        taskExecutor.setQueueCapacity(5);
//        taskExecutor.initializeExecutor(Executors.defaultThreadFactory(),new MyRejectedExecutionHandler());
    }


    /**
     * 查找要找的value的值的下标
     * @param startPos 开始搜索的数组起始下标，
     * @param endPos   搜索结束的数组下标
     * @param searValue  所要找的value值
     * @return  找到元素的下标，如果没有找到返回-1
     */
    public static int search(int startPos,int endPos,int searValue){
        int i = 0;
        for (i=startPos ; i< endPos ;i++){
            // 如果有其他线程找到，就直接返回
            if (integer.get()>=0){
                return integer.get();
            }
            //如果没有，那么就开始遍历查找
            if (arr[i]==searValue){
                // 原子操作，判断设置找到下标，是否成功，如果失败表明其他线程已经设置过了
                if (!integer.compareAndSet(-1,i)){
                    return integer.get();
                }
                return i;
            }
        }
        return -1;
    }

    public static class SearchTask implements Callable<Integer>{

        int startPos,endPos,searchValue;

        public SearchTask(int startPos,int endPos,int searchValue){
            this.startPos = startPos;
            this.endPos = endPos;
            this.searchValue = searchValue;
        }
        @Override
        public Integer call() throws Exception {
            int i = search(startPos, endPos, searchValue);
            return i;
        }

        public static int Psearch(int searchValue) throws ExecutionException, InterruptedException {
            int subArrSize = arr.length/THREAD_NUM+1;//根据线程数分割数组
            List<Future<Integer>> futures = new ArrayList<>();
            /**
             * 如果原数组是9，线程是2，那么分割的数组就是
             * 第一个线程查找[0-5]数组,第二个线程查找[5-8]
             *
             * int i = 0; i < arr.length; i+=subArrSize
             * 首先从0开始，然后将i值赋值为线程1的数组最后下标，依次类推
             *
             */
            for (int i = 0; i < arr.length; i+=subArrSize) {
                int end = i+subArrSize;
                if (end>=arr.length){
                    end = arr.length;
                }
                //将封装了返回值的future
                futures.add(taskExecutor.submit(new SearchTask(i,end,searchValue)));
            }
            for (Future<Integer> fu: futures) {
                if (fu.get()>=0){
                    return fu.get();
                }
            }
            return -1;
        }
    }


}
