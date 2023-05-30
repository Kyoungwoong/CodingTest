package ThisIsCodingTest.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Adventurer {
    private static int N;
    private static Integer[] scary;
    public static void main(String[] args) throws IOException {
        // 한 마을에 모험가가 N
        // N명의 모험가에게 공포도 측정.
        // 공포도가 X인 모험가는 반드시 X 명 이상으로 구성한 모험가 그룹에 참여해야함.
        // 최대 몇개의 모험가 그룹?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        scary = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            scary[i] = Integer.parseInt(st.nextToken());
        }

//        Arrays.sort(scary, Collections.reverseOrder());
//
//        int ans = 0;
//        for(int i = 0; i < N; i++){
//            int team = 0;
//            if(scary[i] <= N-i){
//                team++;
//            }
//
//            for(int j = i + scary[i]; j < N;){
//                if(scary[j] <= N - j){
//                    team++;
//                }
//                j += scary[j];
//            }
//            ans = Math.max(ans, team);
//        }

        Arrays.sort(scary);
        int ans = 0;
        int count = 0;
        for(int i = 0; i < N; i++){
            count += 1; // 현재 그룹에 해당 모험가를 포함시키기
            if (count >= scary[i]) { // 현재 그룹에 포함된 모험가의 수가 현재의 공포도 이상이라면, 그룹 결성
                ans += 1; // 총 그룹의 수 증가시키기
                count = 0; // 현재 그룹에 포함된 모험가의 수 초기화
            }
        }


        System.out.println("ans = " + ans);
    }
}
/*
5
2 3 1 2 2
 */
