package CodeTree.IntermediateMID.TreeSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Seat {
    private static int n, m;
    private static TreeSet<Integer> seat = new TreeSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= m; i++){
            seat.add(i);
        }

        int cnt = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int high = Integer.parseInt(st.nextToken());
            if(seat.floor(high) == null){
                break;
            }
            seat.remove(seat.floor(high));
            cnt++;
        }
        System.out.println(cnt);
    }
}