package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q2263 {
    private static int N, idx = 0;
    private static int[] inOrder;
    private static int[] postOrder;
    private static int[] preOrder;
    private static HashMap<Integer, Integer> inMap = new HashMap<>();
    private static HashSet<Integer> visited = new HashSet<>();

    public static void constructPreOrder(int is, int ie, int ps, int pe) {

        if (is <= ie && ps <= pe) {
            int root = postOrder[pe];

            preOrder[idx++] = root;
            int rootIdx = inMap.get(root);
//            visited.remove(root);
//
//            if (visited.isEmpty()) {
//                return;
//            }


            constructPreOrder(is, rootIdx - 1, ps, ps + rootIdx - is - 1);
            constructPreOrder(rootIdx + 1, ie, ps + rootIdx - is, pe - 1);
        }
    }

//    public static void leftSubTree(int s, int e) {
////        System.out.println("left Tree" + " idx: " + idx);
//        if (e < 0) {
//            return;
//        }
//        int root = postOrder[e];
//        constructPreOrder(s, e);
//    }
//
//    public static void rightSubTree(int s, int e) {
////        System.out.println("right Tree" + " idx: " + idx);
//        if (e < 0) {
//            return;
//        }
//        int root = postOrder[e];
//        constructPreOrder(root, e);
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        inOrder = new int[N];
        postOrder = new int[N];
        preOrder = new int[N];

        for (int i = 1; i <= N; i++) {
            visited.add(i);
        }

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    inOrder[j] = num;
                    inMap.put(num, j);
                } else {
                    postOrder[j] = num;
                }
            }
        }

        constructPreOrder(0, N-1, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(preOrder[i] + " ");
        }
        System.out.println(sb);

    }
}
/*
6
1 3 2 5 4 6
3 1 5 6 4 2

7
4 2 5 1 6 3 7
4 5 2 6 7 3 1
>>> 1 2 4 5 3 6 7
 */
