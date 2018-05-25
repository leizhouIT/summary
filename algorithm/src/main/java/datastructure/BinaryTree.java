package datastructure;

/**
 * Created by zhoulei8 on 2017/5/25.
 * 二叉树结构
 */
public class BinaryTree {

    private Node root;//根节点

    /**
     * 数据节点
     */
    private class Node{
        private int iData;//真正数据
        private Node leftChild;//左节点
        private Node rightChild;//右节点
    }

    /**
     * 根据key查询节点数据，如果没有返回空
     * @param key
     * @return
     */
    public Node find(int key){
        Node current = root;
        while (key != current.iData){
            //如果查找的关键字小于当前节点的值，那么向二叉树的左边查找
            if (key<current.iData){
                current =current.leftChild;
            }else {
                //如果查找的值大于当前节点的值，那么向二叉树的右边查找
                current = current.rightChild;
            }
        }
        return current;
    }

    /**
     * 插入一个元素
     * @param i
     */
    public void insert(int i){
        //构建新节点
        Node newNode = new Node();
        newNode.iData = i;

        if (root == null){//如果根节点是null，直接赋值给根节点
            root =newNode;
            return;
        }else {
            Node current = root;//从根节点开始查找，记录根节点
            Node parent;//记录跟踪当前节点的上一节点
            while (true){
                parent = current;//记录查找的当前元素，防止节点向左或者右移动的时候变成null，没办法记录上一个父节点
                if (i<current.iData){//插入的数据比当前节点小，那么当前节点向左移动
                    current = current.leftChild;//当前节点向左移动
                    if (current == null){//移动后的节点是空
                        parent.leftChild = newNode;//空节点的上一节点的左节点等于要插入数据
                        return;
                    }
                }else {//同节点向左移动一样原理，只是节点向右移动
                    current = current.rightChild;
                    if (current == null){
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }

    }

    /**
     * 遍历整棵树
     */
    public void displayNode() {
        Node current = root;
        if (root ==null){
            return;
        }
        while (current.leftChild != null){
            current = current.leftChild;
            System.out.println(current.iData);
        }
        while (current.rightChild != null){
            current =current.rightChild;
            System.out.println(current.iData);
        }
    }

    /**
     * 中序遍历根据一个节点向下遍历所有节点
     * @param localRoot
     */
    public void displayNode(Node localRoot){

        if (localRoot !=null){

            displayNode(localRoot.leftChild);
            displayNode(localRoot.rightChild);
        }
    }

    /**
     * 查找最小节点
     * @return
     */
    public Node min(){
        Node current;
        Node last = null;
        current = root;//从根节点开始
        while (current != null){
            last = current;//记录节点信息
            current = current.leftChild;//沿着最左边查找
        }
        return last;
    }

    /**
     * 删除一个元素
     * @param key
     * @return
     */
    public boolean delete(int key){
        Node current = root;//从根节点开始查找
        Node parent = root;//记录查找后的父节点
        boolean isLeftChild = true;//是否是左节点标识
        //循环查找要删除的节点
        while (current.iData != key){
            parent = current;
            //如果查找的节点小于当前节点，那么向左查找
            if (key < current.iData){
                isLeftChild = true;//标识左节点
                current = current.leftChild;//指向左节点
            }else {
                isLeftChild = false;//不是左节点
                current = current.rightChild;//指向右节点
            }
            if (current == null){//找到叶子节点还没有找到，直接返回
                return false;
            }
        }
        //找到要删除的节点，已经是最后节点或者是只有一个节点的根节点
        if (current.leftChild == null && current.rightChild == null){
            if (current == root){//如果是根节点
                root = null;
            }else if (isLeftChild){//如果是左节点
                parent.leftChild = null;//当前节点的父节点的左节点指向null，删除当前节点
            }else {//表明当前节点的父节点没有左节点，只有一个右节点，直接指向null
                parent.rightChild=null;
            }
        }else if (current.rightChild==null){//如果当前节点的右节点是null
            if (current==root){//当前节点是根节点
                root = current.leftChild;//当前节点的指向当前节点的左节点
            }else if (isLeftChild){//如果是左节点
                parent.leftChild=current.leftChild;//当前节点的父节点的左节点指向当前节点的左节点，删除当前节点
            }else {//当前节点的父节点的右节点，指向当前节点的左节点，删除当前节点
                parent.rightChild=current.leftChild;
            }
        }else if (current.leftChild==null){//如果左节点是null
            if (current==root){//首先判断当前节点是否是根节点
                root = current.rightChild;//删除根节点，根节点重新赋值为当前节点的右节点
            }else if (isLeftChild){//如果当前节点是左节点
                parent.leftChild=current.rightChild;//当前节点的父节点的左节点指向当前节点的右节点
            }else {//当前节点的父节点的右节点指向当前节点的右节点
                parent.rightChild=current.rightChild;
            }
        }else {
            //查找后继节点
            Node successor = getSuccessor(current);
            if (current == root){
                root = successor;
            }else if (isLeftChild){
                parent.leftChild=successor;
            }else {
                parent.rightChild = successor;
                successor.leftChild=current.leftChild;
            }
        }
        return true;
    }


    /**
     * 查找后继节点，左边最后一个节点
     * @param delNode
     * @return
     */
    private Node getSuccessor(Node delNode){
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null){
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild){
            successorParent.leftChild = successor.rightChild;
            successor.rightChild=delNode.rightChild;
        }
        return successor;
    }
}


