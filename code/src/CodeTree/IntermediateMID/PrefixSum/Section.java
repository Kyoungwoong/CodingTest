package CodeTree.IntermediateMID.PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Section {
    static class Info{
        int a, b, c;
        public Info(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    private static int n, m, k;
    private static char[][] alphabet = new char[1001][1001];
    private static Info[][] info = new Info[1001][1001];

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= n; i++){
            String cmd = br.readLine();
            for(int j = 1; j <= m; j++){
                alphabet[i][j] = cmd.charAt(j-1);
            }
        }

        for(int i = 0; i <= n; i++){
            for(int j = 0; j<= m; j++){
                info[i][j] = new Info(0,0,0);

                if(i >= 1 && j >= 1){
                    if(alphabet[i][j] == 'a'){
                        info[i][j].a = info[i-1][j].a + info[i][j-1].a - info[i-1][j-1].a + 1;
                        info[i][j].b = info[i-1][j].b + info[i][j-1].b - info[i-1][j-1].b;
                        info[i][j].c = info[i-1][j].c + info[i][j-1].c - info[i-1][j-1].c;
                    }else if(alphabet[i][j] == 'b'){
                        info[i][j].a = info[i-1][j].a + info[i][j-1].a - info[i-1][j-1].a;
                        info[i][j].b = info[i-1][j].b + info[i][j-1].b - info[i-1][j-1].b + 1;
                        info[i][j].c = info[i-1][j].c + info[i][j-1].c - info[i-1][j-1].c;
                    }else{
                        info[i][j].a = info[i-1][j].a + info[i][j-1].a - info[i-1][j-1].a;
                        info[i][j].b = info[i-1][j].b + info[i][j-1].b - info[i-1][j-1].b;
                        info[i][j].c = info[i-1][j].c + info[i][j-1].c - info[i-1][j-1].c + 1;
                    }

                }

            }

        }

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append((info[x2][y2].a - info[x1-1][y2].a - info[x2][y1-1].a + info[x1-1][y1-1].a) + " " +
                    (info[x2][y2].b - info[x1-1][y2].b - info[x2][y1-1].b + info[x1-1][y1-1].b) + " " +
                    (info[x2][y2].c - info[x1-1][y2].c - info[x2][y1-1].c + info[x1-1][y1-1].c) + "\n");
        }
        System.out.println(sb);
    }
}
