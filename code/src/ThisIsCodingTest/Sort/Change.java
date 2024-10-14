package ThisIsCodingTest.Sort;

import javax.naming.PartialResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Change {
    public static int N, K;
    public static Integer[] A, B;

    public static void swap(){
        int temp = A[0];
        A[0] = B[N-1];
        B[N-1] = temp;

        Arrays.sort(A);
        Arrays.sort(B);
    }

    public static int sum(){
        int ans = 0;
        for(int i = 0; i < N; i++){
            ans += A[i];
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
//        prev();

        oct14();
    }

    private static void oct14() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new Integer[N];
        B = new Integer[N];
        for(int j = 0; j < 2; j++){
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                if(j == 0){
                    A[i] = Integer.parseInt(st.nextToken());
                }else{
                    B[i] = Integer.parseInt(st.nextToken());
                }
            }
        }

        for (int i = 0; i < K; i++) {
            Arrays.sort(A);
            Arrays.sort(B);
            if (A[0] < B[N - 1]) {
                int temp = A[0];
                A[0] = B[N-1];
                B[N - 1] = temp;
            }
        }

        System.out.println(sum());
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new Integer[N];
        B = new Integer[N];

        for(int j = 0; j < 2; j++){
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                if(j == 0){
                    A[i] = Integer.parseInt(st.nextToken());
                }else{
                    B[i] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // 오름차순 정렬
        Arrays.sort(A);
        Arrays.sort(B);

        for(int i = 0; i < K; i++){
            swap();
        }

        System.out.println(sum());
    }
}
/*
5 3
1 2 5 4 3
5 5 6 6 5
 */
