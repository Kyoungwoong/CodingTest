package CodeTree.IntermediateMID.GridCompression;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.HashMap;

public class CountingStar {
    private static int n, q;
    private static TreeSet<Integer> nums = new TreeSet<>();
    private static HashMap<Integer, Integer> mapper = new HashMap<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            nums.add(Integer.parseInt(st.nextToken()));
        }

        int cnt = 1;
        for(Integer num: nums){
            mapper.put(num, cnt);
            cnt++;
        }

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(!nums.contains(a)){
                if(nums.higher(a) == null){
                    sb.append(0 + "\n");
                    continue;
                }
                a = nums.higher(a);
            }
            if(!nums.contains(b)){
                b = nums.lower(b);
            }
            int ans = mapper.get(b) - mapper.get(a) + 1;
            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }
}