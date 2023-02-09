package SWExpert.Heap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int num, cnt;
    public Node(int num, int cnt){
        this.num = num;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node n){
        return this.cnt - n.cnt;
    }
}

public class MakeNum {
    private static int n, k, cnt;
    private static int[] arr = new int[10];
    private static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("SW_Testcase/Heap/make_num.txt"));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; ++tc){
            pq.clear();
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            k = Integer.parseInt(br.readLine());

            pq.add(new Node(k, 0));

            while(!pq.isEmpty()){
                Node node = pq.poll();
                System.out.print("\n"+node.num + " " + node.cnt);
                if(node.num == 0){
                    cnt = node.cnt;
                    break;
                }

                pq.add(new Node(0, node.cnt + node.num));
                for(int i = 0; i < n; i++){
                    pq.add(new Node(node.num/arr[i], node.cnt + node.num%arr[i]));
                }
            }
            System.out.println();

            sb.append("#" + tc + " " + cnt +"\n");
        }

        System.out.println(sb);
    }

}