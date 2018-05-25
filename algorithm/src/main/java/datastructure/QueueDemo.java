package datastructure;

/**
 * Created by zhoulei8 on 2017/5/18.
 */
public class QueueDemo {

    private int maxSize;//队列最大
    private int front;//头部指针
    private int rear;//尾部指针
    private int nIteams;//元素个数
    private long[] values;//数据存储

    public QueueDemo(int s) {
        this.maxSize = s;
        values = new long[maxSize];
        this.front = 0;
        this.rear = -1;
        this.nIteams = 0;
    }

    /**
     * 队列中放入元素
     * @param value
     */
    public void insert(long value){

        //判断数组角标是否达到最大存储位置，如果是，最大角标回到第一个元素之前
        if (rear == maxSize-1){
            rear=-1;
        }
        values[++rear]=value;
        nIteams++;
    }

    /**
     * 取出队列中的一个元素
     * @return
     */
    public long remove(){
        long temp = values[front++];//数组中的第一个元素
        //如果头部指针和数组大小相同，已经超出数据边界
        if (front==maxSize){
            front=0;
        }
        nIteams--;
        return temp;
    }

    /**
     * 查看队列的头部一个元素
     * @return
     */
    public long peekFront(){
        return values[front];
    }

    /**
     * 判断队列是否是空
     * @return
     */
    public boolean isEmpty(){
        return (nIteams==0);
    }


    /**
     * 判断队列是否已满
     * @return
     */
    public boolean isFull(){
        return nIteams==maxSize;
    }


    /**
     * 队列的大小
     * @return
     */
    public int size(){
        return nIteams;
    }
}

/**
 * 优先级队列
 */
class Priority{
    private int maxSize;
    private long[] values;
    private int nIteams;

    public Priority(int s){
        maxSize = s;
        values = new long[maxSize];
        nIteams=0;
    }

    /**
     * 插入数据到队列中，从大到小的顺序
     *
     * @param value
     */
    public void insert(long value){
        int j;
        //队列中没有数据
        if (nIteams == 0){
            values[nIteams++]=value;
        }else {
            for (j=nIteams-1;j>=0;j--){
                //插入的大于当前数据，将原位置的值向前移动一位
                if (value>values[j]){
                    values[j+1]=values[j];
                }else {
                    break;
                }
            }
            //将新的值插入到头部
            values[j+1]=value;
            nIteams++;
        }
    }

    /**
     * 从队列的最后一位取数据
     * @return
     */
    public long remove(){
        return values[--nIteams];
    }

    /**
     * 查询队列中最后一位的值（最小值）
     * @return
     */
    public long peekMin(){
        return values[nIteams-1];
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return (nIteams==0);
    }

    /**
     * 判断队列是否满了
     * @return
     */
    public boolean isFull(){
        return nIteams==maxSize;
    }

}
