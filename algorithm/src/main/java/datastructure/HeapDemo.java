package datastructure;

/**
 * Created by zhoulei8 on 2017/6/18.
 * 堆的数据结构，不同于java的内存堆
 */
public class HeapDemo {

    public static void main(String[] args) {
        System.out.println(1/2);
//        Heap node = new Heap(5);
//        node.insert(1);
//        node.insert(2);
//        node.insert(3);
//        node.insert(4);
//        node.insert(5);
//        System.out.println(node);

    }

    /**
     * 存放数据
     */
    static class Node{
        private int iData;
        public Node(int key){
            iData=key;
        }
        public int getKey(){
            return iData;
        }
        public void setKey(int id){
            iData=id;
        }
    }

    static class Heap{

        private Node[] heapArray;//存放数据的数组
        private int maxSize;//数据最大的指针
        private int currentSize;//当前指针
        public Heap(int mx){
            maxSize=mx;
            currentSize=0;
            heapArray=new Node[maxSize];
        }

        /**
         * 判断堆中是否有数据，当前指针总是指向最后一位
         * @return
         */
        public boolean isEmpty(){
            return currentSize==0;
        }

        /**
         * 插入数据,新数据放置在大于他的子节点，小于父节点的位置
         * @param key
         * @return
         */
        public boolean insert(int key){
            if (currentSize==maxSize)
                return false;
            Node newNode = new Node(key);
            heapArray[currentSize]=newNode;//新加入的节点总是放在最后一位
            trickleUp(currentSize);//调整节点位置
            currentSize++;
            return true;
        }

        /**
         * 向上移动节点
         * @param index
         */
        public void trickleUp(int index){
            int parent = (index-1)/2;//获取父节点的下标
            Node bottom = heapArray[index];//将要移动的节点
            //如果向上移动的节点比他的父节点大，交换位置
            while (index>0 && heapArray[parent].getKey()<bottom.getKey()){
                heapArray[index]=heapArray[parent];//交换位置
                index=parent;//将父节点下标指向新加入的节点
                parent=(parent-1)/2;//继续向下获取父节点的下标
            }
            //如果index<=0表明数组是空，那新加入的就是一个节点，如果父节点大于新加入的节点，那么新加入的节点就在此处
            heapArray[index]=bottom;
        }

        /**
         * 删除节点，总是从根节点删除，然后将最后一个节点赋值到根节点，在向下移动找到
         * 合适的位置
         * @return
         */
        public Node remove(){
            Node root = heapArray[0];//记录根节点(要删除的节点)
            heapArray[0]=heapArray[--currentSize];//将最后一个节点移动到根节点处
            trickleDown(0);//向下移动节点
            return root;
        }

        /**
         * 向下移动节点
         * @param index
         */
        public void trickleDown(int index){
            int largerChild;//记录更大节点的下标
            Node top = heapArray[index];//记录移动的节点
            //如果移动的节点比数组最大的一半要小
            while (index<currentSize/2){
                int leftChild = 2*index+1;//获取左节点
                int rightChild = leftChild+1;//获取右节点
                //如果右节点小于最大节点的下标，说明没有到达最后，
                //并且左节点的值小于右节点的值
                if (rightChild<currentSize &&
                        heapArray[leftChild].getKey()<
                        heapArray[rightChild].getKey()){
                    largerChild = rightChild;//那么将右节点(数据大的节点)的下标指向定义好的节点下标
                }else{
                    largerChild = leftChild;
                }


                //如果将要移动的节点大于等于最大的节点，直接跳出
                if (top.getKey()>=heapArray[largerChild].getKey()){
                    break;
                }
                //最大节点指向要移动的节点
                heapArray[index]=heapArray[largerChild];
                index=largerChild;//交换下标
            }
            heapArray[index]=top;
        }

        /**
         * 改变节点位置
         * @param index
         * @param newValue
         * @return
         */
        public boolean change(int index,int newValue){
            //如果改变的位置小于0或者大于最大的数据位置的值，说明不合法
            if (index<0 || index>=currentSize){
                return false;
            }
            //获取要改变的位置的值
            int oldValue=heapArray[index].getKey();
            //设置新的值
            heapArray[index].setKey(newValue);
            //开始移动选择合适位置
            if (oldValue<newValue){
                trickleUp(index);
            }else {
                trickleDown(index);
            }
            return true;
        }


    }
}
