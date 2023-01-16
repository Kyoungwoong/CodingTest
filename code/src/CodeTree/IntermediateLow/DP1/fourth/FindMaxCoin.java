package CodeTree.IntermediateLow.DP1.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FindMaxCoin {
    private static int n, m;
    private static int[] arr = new int[10001];
    private static int[] coin = new int[100];

    public static void findMaxCoin(){
        for(int j = 0; j < n; j++){
            arr[coin[j]]++;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 0; j < n; j++){
                if(i >= coin[j]){
                    if(arr[i-coin[j]] == 0) continue;
                    arr[i] = Math.max(arr[i], arr[i - coin[j]] + 1);
                }

            }
            // System.out.print(arr[i] + " ");
        }
        // System.out.println();
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            coin[i] = Integer.parseInt(st.nextToken());
        }

        findMaxCoin();

        if(arr[m] == 0){
            arr[m] = -1;
        }
        System.out.println(arr[m]);

    }
}
}
