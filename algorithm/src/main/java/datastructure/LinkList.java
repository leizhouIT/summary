package datastructure;

/**
 * Created by zhoulei8 on 2017/5/18.
 * 单向链表
 */
public class LinkList {
    public static void main(String[] args) {

        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        // 打印反转前的链表
        Node h = head;
        while (null != h) {
            System.out.print(h.getNumItems() + " ");
            h = h.getNext();
        }
        // 调用反转方法
        // head = reverse1(head);
        head = DoublyLinkLisk.reverse(head);

        System.out.println("\n**************************");
        // 打印反转后的结果
        while (null != head) {
            System.out.print(head.getNumItems() + " ");
            head = head.getNext();
        }
    }
    private Link first;

    public LinkList() {
        first = null;
    }

    /**
     * 判断链表是否是空
     *
     * @return
     */
    public boolean isEmpty() {
        return (first == null);
    }

    /**
     * 插入到链表的头部
     *
     * @param i
     * @param d
     */
    public void insertFirst(int i, double d) {

        //构建数据节点
        Link newLink = new Link(i, d);
        //将节点的下一个指向第一个节点
        newLink.next = first;
        //将第一个节点指向创建的新节点
        first = newLink;
    }

    /**
     * 删除链表头的节点数据
     *
     * @return
     */
    public Link remonvFirst() {
        Link temp = first;//将第一个节点指向临时节点
        first = first.next;//第一个节点指向原来第一个节点的下一个节点
        return temp;//返回第一个节点（删除节点）
    }

    /**
     * 查找链表中的一个数据
     *
     * @param value
     * @return
     */
    public Link find(int value) {
        Link link = first;
        while (link.idata != value) {
            if (link.next == null)//已经达到链表的末尾
                return null;
            else
                link = link.next;//将指针移动到下个节点
        }
        return link;
    }

    /**
     * 删除一个节点
     *
     * @param key
     * @return
     */
    public Link remove(int key) {
        Link current = first;//当前节点指向头部
        Link previous = first;//记录当前节点的前一个节点，初始指向头部节点
        //循环找要删除的节点
        while (current.idata != key) {
            if (current.next == null) {//到了链表的尾部
                return null;
            } else {
                previous = current;//记录当前节点走过的节点
                current = current.next;//当前节点向下移动一个
            }
        }
        //判断要删除的节点是否是头部节点，如果是将头部节点的指向头节点的下个节点
        if (current == first) {
            first = first.next;
        } else {//如果不是头部节点，表明是当前节点，那么将当前节点的前一个节点的next指向当前节点的next，删除当前节点
            previous.next = current.next;
        }
        return current;
    }
}

class Link {
    public int idata;//数据
    public double ddata;//数据
    public Link next;//指向下个节点
    public Link previous;//指向前一个节点

    public Link(int i, double d) {
        this.idata = i;
        this.ddata = d;
    }
}

/**
 * 双端链表
 */
class FirstLastLink {

    private Link first;
    private Link last;

    public FirstLastLink() {
        first = null;
        last = null;
    }

    /**
     * 判断双端链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 向双端链表头部插入数据
     *
     * @param value
     */
    public void insertFirst(int value) {
        Link newLink = new Link(value, 0d);
        //如果链表中是空
        if (isEmpty()) {
            //最后指向新的节点
            last = newLink;
        } else {
            //不等于空，新加入的节点的next指向头节点
            newLink.next = first;
        }
        //将头指向新节点
        first = newLink;
    }

    /**
     * 向双端链表末尾插入数据
     *
     * @param value
     */
    public void insertLast(int value) {
        Link newLink = new Link(value, 0d);
        //如果链表中是空
        if (isEmpty()) {
            //头指向新的节点
            first = newLink;
        } else {
            //不等于空，链表的最后一个节点的next指向新创建的节点
            last.next = newLink;
        }
        //最后的节点指向新节点
        last = newLink;

    }

    public Link deleteFirst() {
        //将第一个头节点标记
        Link temp = first;
        //如果头节点的下个节点是空，表明只有头节点一个数据
        if (first.next == null) {
            //链表中清空了，最后一个节点是null
            last = null;
        }
        //如果头节点的下个节点不等于null，那么将头节点指向头节点的下个节点
        first = first.next;
        return temp;
    }
}

/**
 * 有序链表
 */
class OrderLink {

    private Link first;

    public OrderLink() {
        first = null;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }


    /**
     * 按照从小到大的顺序插入节点
     *
     * @param value
     */
    public void insert(int value) {
        Link newLink = new Link(value, 0d);
        Link previous = null;//记录当前节点的前一个节点
        Link current = first;//当前节点
        //循环查找如果当前节点不是空表示没有到达链表的末尾，
        // 并且插入的值大于当前循环到的节点值
        while (current != null && value > current.idata) {
            previous = current;//当前节点的前一个节点等于
            current = current.next;//当前节点指向当前节点的下个节点
        }
        //如果当前节点的前一节点为空
        if (previous == null) {
            //链表的第一个节点指向新节点
            first = newLink;
        } else {//如果当前节点的前一节点不为空
            //前一节点的下个节点指向新加入的节点
            previous.next = newLink;
        }
        //新加入的节点的下个节点指向当前节点
        newLink.next = current;
    }

    /**
     * 从有序链表的头部删除一个节点
     *
     * @return
     */
    public Link remove() {
        Link temp = first;
        first = first.next;
        return temp;
    }
}

/**
 * 双向链表
 */
class DoublyLinkLisk {

    private Link first;
    private Link last;

    public DoublyLinkLisk() {
        first = null;
        last = null;
    }

    /**
     * 判断双向链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 双向链表头部插入节点
     *
     * @param value
     */
    public void insertFirst(int value) {
        Link newLink = new Link(value, 0d);
        if (isEmpty()) {//如果链表是空
            last = newLink;//最后一个节点指向新节点
        } else {//如果不是空第一个节点的前一个节点指向新节点
            first.previous = newLink;
        }
        //新节点的下个节点指向第一个节点
        newLink.next = first;
        //第一个节点指向新节点
        first = newLink;
    }

    /**
     * 双向链表的尾部插入节点
     * @param value
     */
    public void insertLast(int value) {
        Link newLink = new Link(value, 0d);
        if (isEmpty()) {//如果链表是空，头部指向新节点
            first = newLink;
        } else {//如果不是空，最后一个节点的下个节点指向新节点，新节点的前一个节点指向之前的最后一个节点
            last.next = newLink;
            newLink.previous = last;
        }
        //最后节点指向新节点
        last = newLink;

    }


    /**
     * 删除头部节点
     * @return
     */
    public Link removeFirst() {
        Link temp = first;//记录头部节点
        if (first.next == null) {//如果头部节点的下个节点是空，表示链表已经是空
            last = null;
        } else {//如果头部节点的下个节点不是空，那么头节点的前一节点指向空
            first.next.previous = null;
        }
        //重新为头节点赋值
        first = first.next;
        return temp;
    }

    /**
     * 删除最后一个节点
     * @return
     */
    public Link removeLast() {
        Link temp = last;//记录要删除的节点
        if (first.next == null) {//如果头节点的下个节点是空，表明链表已经是空
            first = null;
        } else {//最后的节点的前一个节点的向下指向变为空，删除最后一个节点
            last.previous.next = null;
        }
        //重新给最后一个节点赋值
        last = last.previous;
        return temp;
    }

    /**
     * 在指定节点的后面插入节点
     * @param key
     * @param value
     * @return
     */
    public boolean insertAfter(int key, int value) {
        Link current = first;//记录头节点，从链表的头开始找
        while (current.idata != key) {//如果当前节点不等于指定的值
            current = current.next;//将当前节点向下移动
            if (current == null) {//到链表的末尾没有找到给定的节点
                return false;
            }
        }
        //找到指定的节点，构建数据，准备插入
        Link newLink = new Link(value, 0d);
        if (current == last) {//如果当前节点是最后一个节点，那么最后节点指向新节点，新节点的下个指向空
            newLink.next = null;
            last = newLink;
        } else {//如果当前节点不是最后节点
            //新节点要在当前节点之后，那么新节点的下个节点应该指向当前节点的下个节点
            //当前节点的下个节点的前一个指向新节点
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        //新节点的前一个节点指向当前节点
        newLink.previous = current;
        //当前节点的下个节点指向新节点
        current.next = newLink;
        return true;
    }


    /**
     * 删除指向节点
     * @param key
     * @return
     */
    public Link deleteKey(int key) {
        Link current = first;//记录头节点，从链表的头开始找
        while (current.idata != key) {//如果当前节点不等于指定的值
            current = current.next;
            if (current == null) {
                return null;
            }
        }
        //找到指定的要删除的节点
        //如果找的要删除的节点等于头节点
        if (current == first) {
            //将头节点重新赋值为当前节点的下个节点
            first = current.next;
        } else {
            //如果不是头节点，当前节点前一个节点的next指向当前节点的next，删除当前节点
            current.previous.next = current.next;
        }
        //如果找到的节点是最后一个节点
        if (current == last) {
            //重新为最后节点赋值，删除最后一个节点
            last = current.previous;
        } else {
            //如果不是最后一个节点，当前节点的下个节点的前一个指向当前节点的前一个节点，删除当前节点
            current.next.previous = current.previous;
        }
        return current;
    }


    public static void reverseLink(Link last){
        /*Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        // 打印反转前的链表
        Node h = head;
        while (null != h) {
            System.out.print(h.getNumItems() + " ");
            h = h.getNext();
        }
        // 调用反转方法
        // head = reverse1(head);
        head = reverse(head);

        System.out.println("\n**************************");
        // 打印反转后的结果
        while (null != head) {
            System.out.print(head.getNumItems() + " ");
            head = head.getNext();
        }*/

    }

    /**
     * 取头节点的下个节点为当前节点
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        if (head == null)
            return head;
        Node pre = head;// 上一结点
        Node cur = head.getNext();// 当前结点
        Node tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
        while (cur != null) {// 当前结点为null，说明位于尾结点
            tmp = cur.getNext();
            cur.setNext(pre);// 反转指针域的指向

            // 指针往下移动
            pre = cur;
            cur = tmp;
        }
        // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
        head.setNext(null);
        return pre;
    }
}
