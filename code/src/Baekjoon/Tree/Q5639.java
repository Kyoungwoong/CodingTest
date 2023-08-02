package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class BST {
    private class Node {
        private int data;      // Node에서 저장하는 data
        private Node parent;    // 현재 Node의 부모를 가리킴
        private Node left;      // 현재 Node의 왼쪽 자식을 가리킴
        private Node right;     // 현재 Node의 오른쪽 자식을 가리킴

        public Node(int d) {
            this.data = d;
            parent = left = right = null;
        }

        public int getData() {
            return this.data;
        }

        public Node getParent() {
            return this.parent;
        }

        public Node getLeft() {
            return this.left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setParent(Node n) {
            this.parent = n;
        }

        public void setLeft(Node n) {
            this.left = n;
        }

        public void setRight(Node n) {
            this.right = n;
        }
    }

    private Node root;      // BST의 root
    private int size;       // BST의 size
    private StringBuilder sb = new StringBuilder();

    public BST() {
        this.root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public Node getRoot() {
        return this.root;
    }

    public void add(int data) {
        Node newNode = new Node(data);
        if(size == 0) {
            root = newNode;
        }
        else {
            Node std = root;

            while(std.getLeft() != null || std.getRight() != null) {
                if(std.getData() > newNode.getData()) {
                    if(std.getLeft() == null) {
                        break;
                    }
                    std = std.getLeft();
                }
                else {
                    if(std.getRight() == null) {
                        break;
                    }
                    std = std.getRight();
                }
            }
            if(std.getData() > newNode.getData()) {
                std.setLeft(newNode);
            }
            else {
                std.setRight(newNode);
            }
            newNode.setParent(std);
        }
        size++;
    }

    public void postorder(Node n) {
        if(n != null) {
            postorder(n.getLeft());
            postorder(n.getRight());
            sb.append(n.getData()+ "\n");
        }
    }

    public void print() {
        System.out.println(sb);
    }
}


public class Q5639 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BST bst = new BST();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            int result = Integer.parseInt(line);
            bst.add(result);
        }
        bst.postorder(bst.getRoot());
        bst.print();
    }
}
