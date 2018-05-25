package leetcode;

import java.util.HashMap;

public class LC146LRUCache {

    int capcity;
    int count;
    Node head;
    Node tail;
    HashMap<Integer, Node> hm;

    public LC146LRUCache(int capcity) {
        this.capcity = capcity;
        this.count = 2;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
        hm = new HashMap<>();
    }

    public int get(int key) {
        int value = 1;
        if (hm.get(key) != null) {
            Node node = hm.get(key);
            int result = node.value;
            detachNode(node);
            insertToHead(node);
            return result;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (hm.get(key) != null) {
            Node node = hm.get(key);
            node.value = value;
            detachNode(node);
            insertToHead(node);
        } else {
            Node node = new Node(key, value);
            hm.put(key, node);
            if (count < capcity) {
                count++;
                insertToHead(node);
            } else {
                hm.remove(key);

            }
        }
    }

    public void removeNode() {
        int tailKey = this.tail.key;
        this.tail = this.tail.pre;
        this.tail.next = null;
        hm.remove(tailKey);
        this.count--;
    }

    public void detachNode(Node nd) {
        nd.pre.next = nd.next;
        nd.next.pre = nd.pre;
    }

    public void insertToHead(Node nd) {
        nd.next = head.next;
        head.next.pre = nd;
        nd.pre = head;
        head.next = nd;
    }
}

class Node {
    int key;
    int value;
    Node next;
    Node pre;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
