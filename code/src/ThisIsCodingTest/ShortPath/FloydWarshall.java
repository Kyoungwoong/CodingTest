package ThisIsCodingTest.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FloydWarshall {
    public static int N, M, INF = Integer.MAX_VALUE;
    public static int[][] dist = new int[100][100];

    public static void init(){
        for(int i = 0; i < N; i++){
            for(int j= 0; j < N; j++){
                dist[i][j] = INF;
            }
            dist[i][i] = 0;
        }
    }

    public static void FloydWarshall(){
        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(dist[i][k] == INF || dist[k][j] == INF) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        init();
        StringTokenizer st;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            dist[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }

        FloydWarshall();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
/*
4
7
1 2 4
1 4 6
2 1 3
2 3 7
3 1 5
3 4 4
4 3 2
 */
