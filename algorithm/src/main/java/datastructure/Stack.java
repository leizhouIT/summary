package datastructure;

/**
 * Created by zhoulei8 on 2017/5/18.
 */
public class Stack {

    private int maxSize;
    private long[] values;
    private int top;//记录栈中最大的元素位置

    public Stack(int max) {
        maxSize = max;
        values = new long[maxSize];
        top = -1;
    }

    /**
     * 栈中放入一个元素
     * @param value
     */
    public void push(long value){
        values[top++]=value;
    }

    /**
     * 栈中弹出一个元素
     * @return
     */
    public long pop(){

        return values[top--];
    }

    /**
     * 查询栈顶元素
     * @return
     */
    public long peek(){
        return values[top];
    }

    /**
     * 判断栈中是否有元素
     * @return
     */
    public boolean isEmpty(){
        return (top==-1);
    }
}
