package CodeTree.IntermediateMID.TreeSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class TopK {
    private static int n, k;
    private static TreeSet<Integer> treeSet = new TreeSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            treeSet.add(Integer.parseInt(st.nextToken()));
        }

        int num = treeSet.last();
        sb.append(num + " ");
        for(int i = 0; i < k-1; i++){
            num = treeSet.lower(num);
            sb.append(num + " ");
        }
        System.out.println(sb);
    }
}