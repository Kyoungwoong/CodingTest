package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Team {
    private static int N, M;
    private static int[] team;

    public static int findParent(int n){
        if(team[n] != n){
            team[n] = findParent(team[n]);
        }
        return team[n];
    }

    public static void union(int a, int b){
        a = findParent(a);
        b = findParent(b);

        if(a < b){
            team[b] = a;
        }else{
            team[a] = b;
        }
    }

    public static void init(){
        for(int i = 0; i<= N; i++){
            team[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
//        prev();

        oct16();
    }

    private static void oct16() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        team = new int[N + 1];
        oct16_init();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (cmd == 0) {
                oct16_union(a, b);
            } else {
                if (oct16_FindParent(a) == oct16_FindParent(b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static void oct16_union(int a, int b) {
        a = oct16_FindParent(a);
        b = oct16_FindParent(b);

        if (a < b) {
            team[b] = a;
        } else {
            team[a] = b;
        }

    }

    private static int oct16_FindParent(int n) {
        if (team[n] != n) {
            team[n] = oct16_FindParent(team[n]);
        }
        return team[n];
    }

    private static void oct16_init() {
        for (int i = 0; i <= N; i++) {
            team[i] = i;
        }
    }

    private static void prev() throws IOException {
        // 팀 합치기 0 a b -> a와 b를 한팀으로
        // 같은 팀 여부 1 a b -> No, YES로 출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        team = new int[N+1];
        init();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switch (cmd){
                case 0:
                    // 팁 합치기
                    union(a, b);
                    break;
                case 1:
                    if(findParent(a) == findParent(b)){
                        sb.append("YES\n");
                    }else{
                        sb.append("NO\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
    }
}
/*
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1
 */
