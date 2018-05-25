package datastructure;

/**
 * Created by zhoulei8 on 2017/6/1.
 */
public class Tree234 {


    private Node root = new Node();//根节点

    /**
     * 返回找到元素的下标
     * @param key
     * @return
     */
    public int find(long key){
        Node curNode = root;//当前节点为根节点
        int childNumber;
        while (true){
            if ((childNumber=curNode.findItem(key)) != -1){
                return childNumber;
            }
        }
    }

    /**
     * 插入一个节点
     * @param value
     */
    public void insert(long value){
        Node curNode =root;//当前节点执行根节点
        DataItem tempItem = new DataItem(value);
        while (true){
            if (curNode.isFull()){//如果节点满了，那么开始分裂节点
                split(curNode);//分裂节点
                curNode = curNode.getParent();
                curNode = getNextChild(curNode,value);

            }else if (curNode.isLeaf()){//如果当前节点是叶子节点，退出循环准备插入，因为叶节点总是有位置的
                break;
            }else {//否则获取下个节点
                curNode = getNextChild(curNode,value);
            }
        }
        //直接插入数据
        curNode.insertItem(tempItem);
    }

    /**
     * 分裂节点,无论分裂的节点是根还是普通的节点，数据项为A、B、C，分裂过程如下：
     *
     * ·创建一个新的空节点，它是要分裂节点的兄弟，在要分裂节点的右边；
     ·数据项C移到新节点中；
     ·数据项B移动到要分裂节点的父节点中；
     ·数据项A保留在原来的位置；
     ·最右边的两个子节点从要分裂节点处断开，连接到新节点上。
     * @param thisNode
     */
    public void split(Node thisNode){
        DataItem itemB,itemC;//将要分裂的节点
        Node parent,child2,child3;
        int itemIndex;
        //先从节点中删除要分裂的数据
        itemC = thisNode.removeItem();
        itemB = thisNode.removeItem();
        //断开与子节点的链接引用
        child2=thisNode.disconnectChild(2);
        child3=thisNode.disconnectChild(3);
        //创建分裂的新的节点(右节点)
        Node newRight = new Node();
        if (thisNode == root){//如果要分裂的节点是根节点
            root = new Node();//创建新的根节点
            parent = root;//将新节点标记为原来的根节点的父节点
            root.connectChild(0,thisNode);//连接子节点
        }else {//如果不是根节点，获得父节点
            parent = thisNode.getParent();
        }
        //将分裂节点中的第二个值插入到父节点
        itemIndex = parent.insertItem(itemB);
        int n = parent.getNumItems();//得到父节点的元素个数
        for (int j=n-1;j>itemIndex;j--){//从最后位置向插入的位置循环
            Node temp = parent.disconnectChild(j);//新插入到数据之后的断开与子节点的链接，记录断开的节点
            parent.connectChild(j+1,temp);//连接到下个子节点，其实就是向后重新和子节点连接
        }
        //连接新分裂出来的节点
        parent.connectChild(itemIndex+1,newRight);
        newRight.insertItem(itemC);//将分裂的最大节点出入到新分裂的节点上
        //将之前断开的节点连接到新的分裂节点上
        newRight.connectChild(0,child2);
        newRight.connectChild(1,child3);
    }

    /**
     * 根据传入节点，和值得到传入的子节点
     * @param theNode
     * @param theValue
     * @return
     */
    public Node getNextChild(Node theNode,long theValue){
        int j;
        int numItems = theNode.getNumItems();//得到节点的元素数目
        for (j=0;j<numItems;j++){
            if (theValue<theNode.getItem(j).data){//如果传入的值，小于传入节点的值，因为存储是的数据从小到大的顺序
                return theNode.getChild(j);
            }
        }
        return theNode.getChild(j);
    }

    public void displayTree(){
        recDisplayTree(root,0,0);
    }

    private void recDisplayTree(Node thisNode,int level,int childNumber){
        System.out.println("level="+level+" child="+childNumber+" ");
        thisNode.displayNode();
        int numItems = thisNode.getNumItems();
        for (int j=0;j<numItems+1;j++){
            Node nextNode = thisNode.getChild(j);
            if (nextNode != null)
                recDisplayTree(nextNode,level+1,j);
            else
                return;
        }
    }



}

class DataItem{
    public long data;
    public DataItem(long data){
        this.data=data;
    }

    public void displayItem(){
        System.out.println("/"+data);
    }

    public long getKey(){
        return data;
    }

}

class Node{
    private static final int ORDER = 4;
    private int numItems;
    private Node parent;//父节点
    private Node[] childArray = new Node[ORDER];//子节点的引用
    private DataItem[] itemArray = new DataItem[ORDER-1];//节点的数据
    private Node next;// 指针域

    public Node(){

    }

    public Node(int numItems) {
        this.numItems = numItems;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * 连接子节点
     * @param childNum 子节点的位置
     * @param child
     */
    public void connectChild(int childNum,Node child){
        childArray[childNum]=child;//将子节点信息记录下来
        if (child != null)//子节点不是null
            child.parent=this;//他的父节点就是调用的节点
    }

    /**
     * 输出节点信息
     */
    public void displayNode(){
        for (int j=0;j<numItems;j++)
            itemArray[j].displayItem();
        System.out.println("/");
    }

    /**
     * 去掉节点的连接
     * @param childNum
     * @return
     */
    public Node disconnectChild(int childNum){
        Node tempNode = childArray[childNum];//定位断开连接的节点，并记录
        childArray[childNum]=null;
        return tempNode;
    }

    /**
     * 获得子节点
     * @param childNum
     * @return
     */
    public Node getChild(int childNum){
        return childArray[childNum];
    }

    /**
     * 获得父节点
     * @return
     */
    public Node getParent(){
        return parent;
    }

    /**
     * 是否是叶子节点,如果保存子节点的引用的第一个值是空那么表明是叶子节点，因为节点存储的是
     * 节点的引用，并且是有顺序的，如果第一个值是空，那就是没有下面节点的引用
     * @return
     */
    public boolean isLeaf(){
        return childArray[0]==null?true:false;
    }

    /**
     * 获得节点中的数据个数
     * @return
     */
    public int getNumItems(){
        return numItems;
    }

    /**
     * 根据节点的索引获得数据
     * @param index
     * @return
     */
    public DataItem getItem(int index){
        return itemArray[index];
    }

    /**
     * 判断叶子节点是否已满，叶子节点存储的数据是3个，
     * @return
     */
    public boolean isFull(){
        return numItems==ORDER-1?true:false;
    }

    /**
     * 根据key查找元素，返回元素的下标
     * @param key
     * @return
     */
    public int findItem(long key){
        for (int j=0;j<ORDER-1;j++){
            if (itemArray[j]==null){
                break;
            }else if (itemArray[j].data==key){
                return j;
            }
        }
        return -1;
    }

    /**
     * 插入一个节点数据
     * @param newItem
     * @return
     */
    public int insertItem(DataItem newItem){
        numItems++;
        long newKey = newItem.data;
        for (int j=ORDER-2;j>=0;j--){//循环节点数据，按照数组的下标，从最大到最小
            if (itemArray[j]==null){//如果该数组位置是空，继续向前查找
                continue;
            }else {
                long itsKey = itemArray[j].data;
                if (newKey<itsKey){//如果新加入的值小于当前的值
                    itemArray[j+1]=itemArray[j];//向后移动当前的值
                }else {
                    itemArray[j+1]=newItem;//当前值的下一位放置新的值
                    return j+1;
                }
            }
        }
        //当节点中一个数据也没有的时候，或者要插入的值最小的时候
        itemArray[0]=newItem;
        return 0;
    }

    /**
     *删除一个节点中的数据，获取节点中最大的删除
     * @return
     */
    public DataItem removeItem(){
        DataItem temp = itemArray[numItems-1];
        itemArray[numItems-1]=null;
        numItems--;
        return temp;
    }



}
