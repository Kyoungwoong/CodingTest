package ThisIsCodingTest.Binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Seq {
    public static int N;
    public static String Target;
    public static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new String[N];
        Target = st.nextToken();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = st.nextToken();
        }

        for(int i = 0; i < N; i++){
            if(arr[i].equals(Target)){
                System.out.println((1+i));
                break;
            }
        }
    }
}
/*
5 dongbin
haun elkaj dkjfd dongbin youngwoong
 */
