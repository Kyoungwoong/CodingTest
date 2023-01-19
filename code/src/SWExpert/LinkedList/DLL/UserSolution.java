package SWExpert.LinkedList.DLL;

class Node {
    public int data;
    public Node prev;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class UserSolution {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        head = new Node(0);
    }

    public void addNode2Head(int data) {
        Node newNode = getNode(data);

        if(head.next == null){
            head.next = newNode;
            newNode.prev = head;
            newNode.next = null;
        }else{
            newNode.prev = head;
            newNode.next = head.next;

            head.next.prev = newNode;
            head.next = newNode;
        }

    }

    public void addNode2Tail(int data) {
        Node newNode = getNode(data);
        Node cur = head;
        while(cur.next != null){
            cur = cur.next;
        }

        newNode.next = null;
        newNode.prev = cur;
        cur.next = newNode;
    }

    public void addNode2Num(int data, int num) {
        Node newNode = getNode(data);
        Node cur = head;
        int idx = 0;
        while(cur.next != null && idx != num-1){
            cur = cur.next;
            idx++;
        }

        if(cur.next == null){
            newNode.next = null;
            newNode.prev = cur;
            cur.next = newNode;
        }else{
            newNode.next = cur.next;
            newNode.prev = cur;

            cur.next.prev = newNode;
            cur.next = newNode;
        }

    }

    public int findNode(int data) {
        int idx = 0;
        Node cur = head;
        while(cur.next.data != data){
            cur = cur.next;
            idx++;
        }
        return idx+1;
    }

    public void removeNode(int data) {
        Node cur = head;
        while(cur.next != null && cur.next.data != data){
            cur = cur.next;
        }
        if(cur.next == null){
            return;
        }else{
            if(cur.next.next == null){
                cur.next = null;
            }else{
                cur.next.next.prev = cur;
                cur.next = cur.next.next;
            }

        }
    }

    public int getList(int[] output) {
        int idx = 0;
        Node cur = head;
        while(cur.next != null){
            output[idx] = cur.next.data;
            cur = cur.next;
            idx++;
        }
        return idx;
    }

    public int getReversedList(int[] output) {
        int idx = 0;
        Node cur = head;
        while(cur.next != null){
            cur = cur.next;
        }
        while(cur != head){
            output[idx] = cur.data;
            idx++;
            cur = cur.prev;
        }
        return idx;
    }
}