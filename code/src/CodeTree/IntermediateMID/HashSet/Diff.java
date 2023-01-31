package CodeTree.IntermediateMID.HashSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Diff {
    private static int n, cnt = 0;
    private static HashSet<Integer> diff = new HashSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int key = Integer.parseInt(st.nextToken());
            if(!diff.contains(key)){
                cnt++;
                diff.add(key);
            }
        }
        System.out.println(cnt);
    }
}
