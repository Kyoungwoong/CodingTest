package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * N개의 악보. i번째 악보는 Bi초
 * 0초부터 1번 악보 시작
 * B1-1초에 1번 끝. B1부터 B1+B2-1초까지 2번 악보
 * T1부터 TQ까지 Q개의 시간에 대해 Ti초때 노래하는 악보를 i번째에 출력
 */
public class Q1392 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, Q;
    // key: 노래 시작 시간, value: i번째 노래
    static TreeMap<Integer, Integer> songStartTimeMap = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int startTime = 0;
        for (int i = 0; i < N; i++) {
            songStartTimeMap.put(startTime, i + 1);
            startTime += Integer.parseInt(br.readLine());
        }

        for (int q = 0; q < Q; q++) {
            int time = Integer.parseInt(br.readLine());
            int number = songStartTimeMap.floorKey(time);
            sb.append(songStartTimeMap.get(number)).append("\n");
        }

        System.out.println(sb.toString());
    }
}
