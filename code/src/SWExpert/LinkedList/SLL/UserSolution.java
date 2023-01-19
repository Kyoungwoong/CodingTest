package SWExpert.LinkedList.SLL;

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
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

        newNode.next = head.next;
        head.next = newNode;
    }

    public void addNode2Tail(int data) {
        Node newNode = getNode(data);
        Node cur = head;
        while(cur.next != null){
            cur = cur.next;
        }

        newNode.next = cur.next;
        cur.next = newNode;
    }

    public void addNode2Num(int data, int num) {
        Node newNode = getNode(data);
        Node cur = head;
        int idx = 0;
        while(idx != num-1){
            cur = cur.next;
            idx++;
        }
        newNode.next = cur.next;
        cur.next = newNode;
    }

    public void removeNode(int data) {
        Node cur = head;
        while(cur.next != null && cur.next.data != data){
            cur = cur.next;
        }
        if(cur.next == null){
            return;
        }else{
            cur.next = cur.next.next;
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
}