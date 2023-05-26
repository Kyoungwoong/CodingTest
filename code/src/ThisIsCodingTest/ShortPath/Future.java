package ThisIsCodingTest.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 주의 사항
// 연결된 회사끼리는 양방향 가능..
public class Future {
    public static int N, M, X, K, INF = (int)1e9;
    public static int[][] dist = new int[100][100];

    public static void main(String[] args) throws IOException {
        // A는 1번 회사에 위치
        // X번 회사에 방문해 물건을 판해
        // K번 회사에 소개팅 1 ->..K..-> X
        // 도달할 수 없으면 -1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                dist[i][j] = INF;
            }
            dist[i][i]=0;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            dist[a][b] = 1;
            dist[b][a] = 1;
        }
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken())-1;
        K = Integer.parseInt(st.nextToken())-1;

        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(dist[i][j]+"\t");
            }
            System.out.println();
        }

        int result = dist[0][K] + dist[K][X];
        if(result >= INF){
            System.out.println("-1 = " + -1);
        }else{
            System.out.println("result = " + result);
        }
    }
}
/*
5 7
1 2
1 3
1 4
2 4
3 4
3 5
4 5
4 5

4 2
1 3
2 4
3 4
 */
