package CodeTree.IntermediateMID.GridCompression;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Arrays;

public class CountingStar3 {
    private static int n, q;
    private static int[] arr = new int[100000];
    private static HashMap<Integer, Integer> s = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, 0, n);

        int cnt = 0;
        for(int i = 0; i < n; i++){
            s.put(arr[i], cnt);
            cnt++;
        }

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int ans = s.get(b) - s.get(a) + 1;
            sb.append(ans+"\n");
        }
        System.out.println(sb);

    }
}