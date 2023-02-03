package CodeTree.IntermediateMID.TreeSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Fast {
    private static int n, m;
    private static TreeSet<Integer> treeSet = new TreeSet<>();
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());
            treeSet.add(num);
        }

        for(int i = 0; i < m; i++){
            int num = Integer.parseInt(br.readLine());
            if(treeSet.ceiling(num) == null){
                sb.append("-1\n");
            }else{
                int add = treeSet.ceiling(num);
                sb.append(add + "\n");
            }

        }
        System.out.println(sb);
    }
}