package CodeTree.IntermediateMID.TreeSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SmallDiff {
    private static int n, m, ans = Integer.MAX_VALUE;
    private static int[] arr= new int[100000];
    private static TreeSet<Integer> treeSet = new TreeSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
            treeSet.add(num);
        }

        for(int i = 0; i < n; i++){
            int x = arr[i];

            if(treeSet.ceiling(m + x) != null){
                ans = Math.min(ans, treeSet.ceiling(m + x) - x);
            }

            if(treeSet.floor(x - m) != null){
                ans = Math.min(ans, x - treeSet.floor(x - m));
            }
        }

        if(ans == Integer.MAX_VALUE)
            ans = -1;

        System.out.print(ans);
    }
}