package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CheckCycle {
    public static int V, E;
    public static int[] arr;

    public static void init(){
        for(int i = 1; i <= V; i++){
            arr[i] = i;
        }
    }

    public static int findParent(int n){
        if(arr[n] != n){
            arr[n] = findParent(arr[n]);
        }

        return arr[n];
    }

    public static void union(int a, int b){
        a = findParent(a);
        b = findParent(b);
        if(a < b){
            arr[b] = a;
        }else{
            arr[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        arr = new int[V+1];
        init();

        boolean cycle = false;

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(findParent(a) == findParent(b)){
                cycle = true;
                break;
            }else{
                union(a, b);
            }
        }

        if (cycle) {
            System.out.println("사이클이 발생했습니다.");
        }
        else {
            System.out.println("사이클이 발생하지 않았습니다.");
        }

    }
}
/*
3 3
1 2
1 3
2 3
 */