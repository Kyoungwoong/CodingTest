package CodeTree.IntermediateMID.HashSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

public class CTag {
    private static int n, m;
    private static String[] a = new String[500];
    private static String[] b = new String[500];
    // AAT와 같은 형태로 저장.
    private static HashSet<String> ATGC;

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            a[i] = br.readLine();
        }

        for(int i = 0; i < n; i++){
            b[i] = br.readLine();
        }

        // a[i]의 i, j, k 가져와서
        // b[i]의 i, j, k 가져와서 같은 게 있으면 패스 다르면 더해주기
        int ans = 0;
        for(int i = 0; i < m-2; i++){
            for(int j = i+1; j < m-1; j++){
                for(int k = j+1; k < m; k++){
                    ATGC = new HashSet<>();
                    // A에서 가져오기
                    for(int s = 0; s < n; s++){
                        String ADNA = ""+a[s].charAt(i) + a[s].charAt(j) + a[s].charAt(k);
                        ATGC.add(ADNA);
                    }
                    // B에 잇는 지 검사
                    boolean check = true;
                    for(int s = 0; s < n; s++){
                        String BDNA = ""+b[s].charAt(i) + b[s].charAt(j) + b[s].charAt(k);
                        if(ATGC.contains(BDNA)){
                            check = false;
                            break;
                        }
                    }
                    if(check){
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);

    }
}