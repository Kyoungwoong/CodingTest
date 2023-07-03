package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Enter {
    private static int G, P;
    private static HashSet<Integer> enter = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        for (int i = 1; i <= G; i++) {
            enter.add(i);
        }

        int cnt = 0;
        for (int i = 0; i < P; i++) {
            int p = Integer.parseInt(br.readLine());
            boolean check = false;
            for (int j = p; j >= 1; j--) {
                if (enter.contains(j)) {
                    enter.remove(j);
                    cnt++;
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println(cnt);
                break;
            }
        }
    }
}
/*
4
3
4
1
1
--- 2
4
6
2
2
3
3
4
4
--- 3
 */
