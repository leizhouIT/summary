package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zhoulei8 on 2017/3/16.
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private static final int Threshold = 100;
    private long start;
    private long end;


    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean cancompute = (end-start)>Threshold;
        if (cancompute){
            for (long i= start ;i <= end ; i++){
                sum+=i;
            }
        }else {
            long step = (start+end)/100;
            List<ForkJoinDemo> list = new ArrayList<ForkJoinDemo>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                 long lastOne = pos+step;
                 ForkJoinDemo forkJoinDemo = new ForkJoinDemo(pos,lastOne);
                 pos+=step+1;

                list.add(forkJoinDemo);
                forkJoinDemo.fork();
            }
            for (ForkJoinDemo forkJoinDemo : list){
                sum+=forkJoinDemo.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(0,200000L);
        ForkJoinTask<Long> sum = forkJoinPool.submit(forkJoinDemo);
        System.out.println(sum.get());
    }
}
