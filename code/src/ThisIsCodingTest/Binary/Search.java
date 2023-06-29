package ThisIsCodingTest.Binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Search {
    private static int N, K;
    private static String[] words;
    private static String[] queries;
    private static int[] result;

    public static int lyrics(String target) {
        int idx = target.indexOf("?");
        int len = target.length();
        int cnt = 0;
        // ? 가 뒤에 나오는 경우.
        if(idx != 0){
            String std = target.substring(0, idx);
            for (int i = 0; i < N; i++) {
                if (words[i].length() == len && words[i].substring(0, idx).equals(std)) {
                    cnt++;
                }
            }
        }
        // ? 가 앞에 나오는 경우.
        else{
            int temp = 0, lastIdx = 0;
            for (int i = 0; i < len; i++) {
                temp = target.indexOf("?", i);
                if (temp == -1) {
                    break;
                }else{
                    lastIdx = temp;
                }
            }

            if(lastIdx - idx + 1 == len){
                for (int i = 0; i < N; i++) {
                    if (words[i].length() == len) {
                        cnt++;
                    }
                }
            }else{
                String std = target.substring(lastIdx+1);
                for (int i = 0; i < N; i++) {
                    if (words[i].length() == len && words[i].substring(lastIdx+1).equals(std)) {
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            words[i] = st.nextToken();
        }

        queries = new String[K];
        result = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            queries[i] = st.nextToken();
        }

        for (int i = 0; i < K; i++) {
            result[i] = lyrics(queries[i]);
            System.out.print(result[i] + " ");
        }

    }
}
/*
6 5
frodo front frost frozen frame kakao
fro?? ????o fr??? fro??? pro?
 */
