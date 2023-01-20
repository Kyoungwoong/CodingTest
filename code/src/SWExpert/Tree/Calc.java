package SWExpert.Tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Calc {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    private static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("SW_Testcase/Tree/calc.txt"));

        while (T++ < 10) {
            N = Integer.parseInt(br.readLine());
            BinaryTree tree = new BinaryTree();

            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                String data = st.nextToken();
                tree.insert(idx, data);
                if (isOperator(data.charAt(0))) {
                    int leftChildIdx = Integer.parseInt(st.nextToken());
                    int rightChildIdx = Integer.parseInt(st.nextToken());
                    tree.insert(idx, leftChildIdx, rightChildIdx);
                }
            }

            double result = tree.calc(tree.root);
            ans.append("#").append(T).append(" ").append((int) result).append("\n");
        }
        System.out.println(ans);
    }

    private static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    private static class BinaryTree {

        Node root;

        public void insert(int idx, String data) {
            if (root == null) {
                root = new Node(idx, data);
                return;
            }

            Node node = find(root, idx);
            node.data = data;
        }

        public void insert(int parentIdx, int leftChildIdx, int rightChildIdx) {
            Node node = find(root, parentIdx);
            node.leftChild = new Node(leftChildIdx, "");
            node.rightChild = new Node(rightChildIdx, "");
        }

        private Node find(Node head, int idx) {
            if (head.idx == idx) return head;
            Node result = null;
            if (head.leftChild != null) {
                result = find(head.leftChild, idx);
                if (result != null) return result;
            }
            if (head.rightChild != null) {
                result = find(head.rightChild, idx);
                if (result != null) return result;
            }
            return result;
        }

        public double calc(Node head) {
            char operator = head.data.charAt(0);
            double left = (head.leftChild.leftChild == null ? Double.parseDouble(head.leftChild.data) : calc(head.leftChild));
            double right = (head.rightChild.rightChild == null ? Double.parseDouble(head.rightChild.data) : calc(head.rightChild));
            double result;
            if (operator == '+') {
                result = left + right;
            } else if (operator == '-') {
                result = left - right;
            } else if (operator == '*') {
                result = left * right;
            } else if (operator == '/') {
                result = left / right;
            } else {
                result = 0;
            }
            return result;
        }

        private static class Node {
            int idx;
            String data;
            Node leftChild, rightChild;

            public Node(int idx, String data) {
                this.idx = idx;
                this.data = data;
                leftChild = null;
                rightChild = null;
            }
        }
    }
}