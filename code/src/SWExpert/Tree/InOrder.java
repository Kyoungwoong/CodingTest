package SWExpert.Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.FileInputStream;


public class InOrder {
    private static int TC = 10, N;
    private static String[] tree;

    public static void inOrder(int idx){
        if(idx > N){
            return;
        }
        inOrder(2 * idx);
        System.out.print(tree[idx]);
        inOrder(2 * idx+1);
    }
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("SW_Testcase/Tree/in_order.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int t = 1; t <= TC; t++){
            N = Integer.parseInt(br.readLine());
            tree = new String[N+1];

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                String data = st.nextToken();
                tree[idx] = data;
            }
            System.out.print("#" + t + " ");
            inOrder(1);
            System.out.println();
        }

    }
}
