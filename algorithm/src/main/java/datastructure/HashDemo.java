package datastructure;

/**
 * Created by zhoulei8 on 2017/6/17.
 */


public class HashDemo {


}

/**
 * 链表地址法
 */
class LinkedHash{
    private SortedList[] hashArray;
    private int arraySize;
    public LinkedHash(int size){
        arraySize=size;
        hashArray = new SortedList[arraySize];
        for (int j=0;j<arraySize;j++){//将存放数据的数组填满，但是里面的数据初始化为空的SortedList，SortedList真正存放数据
            hashArray[j]=new SortedList();
        }
    }

    /**
     * 计算hashcode
     * @param key
     * @return
     */
    public int hashFunc(int key){
        return key%arraySize;
    }

    public void insert(Link theLink){
        int key = theLink.getKey();
        int hashVal = hashFunc(key);
        hashArray[hashVal].insert(theLink);
    }

    public void delete(int key){
        int hashVal = hashFunc(key);
        hashArray[hashVal].delete(key);
    }

    public Link find(int key){
        int hashVal = hashFunc(key);
        Link theLink = hashArray[hashVal].find(key);
        return theLink;
    }




    /**
     * 存放数据
     */
    class Link{
        private int iData;
        public Link next;
        public Link(int item){
            iData = item;
        }

        public int getKey(){
            return iData;
        }

        public void displayLink(){
            System.out.println(iData+"");
        }
    }

    /**
     * 链表中数据操作类
     */
    class SortedList{
        private Link first;
        public void SortedList(){
            first = null;//将链表的头指向空，一个空链表
        }

        /**
         * 插入数据，按照从小到大的顺序
         * @param theLink
         */
        public void insert(Link theLink){
            int key = theLink.getKey();
            Link previous = null;
            Link current = first;//当前节点指向头
            //如果插入的节点比当前节点的值大，其实就是沿着链表查找
            while (current != null && key>current.getKey()){
                previous = current;//将前一个节点指向当前节点
                current=current.next;//当前节点的下个节点指针指向当前节点的
            }
            if (previous == null){//如果当前节点的前一个节点是null，刚插入的时候，当前节点已经指向头，那么该链表是空
                first=theLink;//插入的节点指向头节点
            }else {//如果当前节点的前一个节点不是null,那么当前节点的前一个节点的下个指向插入的节点
                previous.next=theLink;
            }
            //插入节点的下个节点变为当前节点
            theLink.next=current;
        }

        /**
         *
         * @param key
         */
        public void delete(int key){
            Link previous = null;
            Link current = first;//从链表的头开始查找
            //沿着链表向下查找
            while (current!=null&& key!=current.getKey()){
                previous = current;
                current=current.next;
            }
            if (previous==null){//如果当前节点的前一个节点是null，刚插入的时候，当前节点已经指向头，那么该链表是空
                first=first.next;//删除头节点
            }else {
                //将当前查找到的节点删除，当前节点的前一个节点的后节点指针指向要删除的节点的下个节点，插入是按照从小到大的顺序
                previous.next=current.next;
            }
        }

        /**
         * 查找一个节点
         * @param key
         * @return
         */
        public Link find(int key){
            Link current = first;//从头开始找
            while (current!=null&&current.getKey()<=key){
                if (current.getKey()==key)//如果当前节点是直接返回
                    return current;
                current =current.next;//如果当前节点的只小于查找的节点，继续向下查找
            }
            return null;
        }
    }

}

/**
 * 开放地址法
 * hash结构存储数据，冲突解决线性查找,以及二次hash
 */
class LinearHash{
    private DataItem[] hashArray;//存放数据
    private int arraySize;//数据的大小
    private DataItem nonItem;//删除节点

    public LinearHash(int size){
        arraySize=size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);
    }

    public void displayTable(){
        System.out.println("hashDemo:");
        for (int j=0;j<arraySize;j++){
            if (hashArray[j]!=null)
                System.out.println(hashArray[j].getKey()+"");
            else
                System.out.println("*****");
        }
        System.out.println("");
    }

    /**
     * 计算hashcode的方法
     * @param key
     * @return
     */
    public int hashFunc(long key){
        return (int) (key%arraySize);
    }


    /**
     * 该方法:stepSize = constant-(key%constant)
     * 对hashcode相同的值在hash，5：表示小于数组容量的一个质数，
     *
     * @param key
     * @return
     */
    public int hashFunc2(long key){
        return (int) (5-key%5);
    }

    /**
     * 插入数据，如果位置已经有数据，继续向下查找空位置
     * @param item
     */
    public void insert(DataItem item){
        long key = item.getKey();
        int hashVal = hashFunc(key);
        int stepSzie = hashFunc2(key);//获得再hash的步长
        //查找对应数组的位置是否是null，并且如果这个位置的值是-1表明是删除的数据
        while (hashArray[hashVal]!=null&&
                hashArray[hashVal].getKey()!=-1){
            ++hashVal;//线性查找
//            hashVal+=stepSzie;//二次探测的再hash
            hashVal%=arraySize;

        }
        //找到适合的位置
        hashArray[hashVal]=item;
    }

    /**
     * 删除一个数据
     * @param key
     * @return
     */
    public DataItem delete(int key){
        int hashVal = hashFunc(key);
        int stepSzie = hashFunc2(key);//获得再hash的步长
        //如果匹配的数据不是null
        while (hashArray[hashVal]!=null){
            //判断数据是否和要删除的相等
            if (hashArray[hashVal].getKey()==key){
                DataItem temp = hashArray[hashVal];//记录要删除的数据
                hashArray[hashVal]=nonItem;//将删除的数据位置替换为一个特殊的元素
                return temp;//返回删除的数据
            }
            ++hashVal;//当条件不满足的时候继续向下，按照线性查找
            //hashVal+=stepSzie;//二次探测的再hash
            hashVal%=arraySize;//重新计算hash值，这里有两个作用，如果达到尾部，那么将hash还原到头部
        }
        return null;
    }

    /**
     * 查找一个元素
     * @param key
     * @return
     */
    public DataItem find(int key){
        int hashVal = hashFunc(key);
        int stepSzie = hashFunc2(key);//获得再hash的步长
        while (hashArray[hashVal]!=null){
            if (hashArray[hashVal].getKey()==key){
                return hashArray[hashVal];
            }
            ++hashVal;//当条件不满足的时候继续向下，按照线性查找
            //hashVal+=stepSzie;//二次探测的再hash
            hashVal%=arraySize;
        }
        return null;
    }
}

