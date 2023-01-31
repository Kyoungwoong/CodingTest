package CodeTree.IntermediateMID.HashSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

class Pos{
    int a, b;
    public Pos(int a, int b){
        this.a = a;
        this.b = b;
    }
}

public class ChangeSeat {
    private static int n, k;
    // i가 앉았던 자리
    private static HashSet<Integer>[] seat;
    private static Pos[] change;
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        seat = new HashSet[n+1];
        change = new Pos[k];
        int[] curPos = new int[n+1];

        for(int i = 1; i <= n; i++){
            seat[i] = new HashSet<Integer>();
            seat[i].add(i);
            curPos[i] = i;
        }

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Pos newPos = new Pos(a,b);

            change[i] = newPos;
        }

        for(int i = 0; i < 3*k; i++){
            Pos cur = change[i%k];
            // cur.a, b가 바뀔 위치
            // curPos가 그 위치에 앉아있는 사람. => a, b
            int a = curPos[cur.a];;
            int b = curPos[cur.b];

            int temp = a;
            curPos[cur.a] = b;
            curPos[cur.b] = temp;

            // cur.a위치에 b를 넣어주고 cur.b위치에 a를 넣어줌.
            seat[a].add(cur.b);
            seat[b].add(cur.a);
        }

        for(int i = 1; i <= n; i++){
            sb.append(seat[i].size() + "\n");
        }

        System.out.println(sb);
    }
}
