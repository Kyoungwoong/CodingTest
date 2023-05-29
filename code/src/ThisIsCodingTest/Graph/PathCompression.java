package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PathCompression {
    public static int V, E;
    public static int[] arr;

    public static void init(){
        for(int i = 1; i <= V; i++){
            arr[i] = i;
        }
    }

    public static int findParent(int n){
        if(arr[n] != n){
            if(n == 3){
                System.out.println("arr[n] = " + arr[n]);
            }
            arr[n] = findParent(arr[n]);
        }

        return arr[n];
    }

    public static void union(int a, int b){
        a = findParent(a);
        b = findParent(b);
//        System.out.println("a = " + a + " b = " + b);
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

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }


        System.out.println("루트 테이블 내용 출력: ");
        for(int i = 1; i <= V; i++){
            System.out.print(findParent(i) + " ");
        }
        System.out.println();
        System.out.println("부모 테이블 내용 출력: ");
        for(int i = 1; i <= V; i++){
            System.out.print(arr[i] + " ");
        }

    }
}
/*
6 4
1 4
2 3
2 4
5 6
 */