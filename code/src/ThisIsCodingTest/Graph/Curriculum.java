package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Curriculum {
    private static int N;
    private static int[] arr, time;
    private static boolean[] visited;
    private static Queue<Integer> q = new LinkedList<>();
    private static ArrayList<ArrayList<Integer>> cur = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        time = new int[N + 1];
        visited = new boolean[N + 1];

        for(int i = 0; i <= N; i++){
            cur.add(new ArrayList<Integer>());
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean first = false;

            while (true) {
                int a = Integer.parseInt(st.nextToken());
                if (a == -1) break;
                if (!first) {
                    time[i] = a;
                    first = true;
                } else {
                    arr[i]++;
                    cur.get(a).add(i);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (arr[i] == 0) {
                q.add(i);
                visited[i] = true;
            }
        }
        
        int[] ans = new int[N+1];
        for(int i = 1; i <= N; i++){
            ans[i] = time[i];
        }
        int result = 0;
        while(!q.isEmpty()){
            int now = q.poll();
//            System.out.println("now = " + now);
//            for(int i = 1; i <= N; i++){
//                System.out.print(ans[i]+" ");
//            }
//            System.out.println();
            for(int i = 0; i < cur.get(now).size(); i++){
                arr[cur.get(now).get(i)]--;
                ans[cur.get(now).get(i)] = Math.max(ans[cur.get(now).get(i)], ans[now] + time[cur.get(now).get(i)]);

                if(arr[cur.get(now).get(i)] == 0){
                    q.add(cur.get(now).get(i));
                }
            }
//            for(int i = 1; i <= N; i++){
//                if(arr[i] == 0 && !visited[i]){
//                    q.add(i);
//                    visited[i] = true;
//                }
//            }
//            result += time[now];
//            ans[now] = result;
        }
        
        for(int i = 1; i<= N; i++){
            System.out.println("ans[i] = " + ans[i]);
        }
    }
}
/*
5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1
 */
