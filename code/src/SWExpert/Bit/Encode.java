package SWExpert.Bit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://swexpertacademy.com/main/learn/course/lectureProblemViewer.do
public class Encode {
    private static int testCase, N, M;

    //    private static final Map<String, Integer> CODE_MAP = Map.ofEntries(
//            Map.entry("0001101", 0),
//            Map.entry("0011001", 1),
//            Map.entry("0010011", 2),
//            Map.entry("0111101", 3),
//            Map.entry("0100011", 4),
//            Map.entry("0110001", 5),
//            Map.entry("0101111", 6),
//            Map.entry("0111011", 7),
//            Map.entry("0110111", 8),
//            Map.entry("0001011", 9)
//    );
    private static final Map<String, Integer> CODE_MAP = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        String[] codes = {
                "0001101", // 0
                "0011001", // 1
                "0010011", // 2
                "0111101", // 3
                "0100011", // 4
                "0110001", // 5
                "0101111", // 6
                "0111011", // 7
                "0110111", // 8
                "0001011"  // 9
        };

        for (int i = 0; i < 10; i++) {
            CODE_MAP.put(codes[i], i);
        }

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int[] encode = new int[8];
            int idx = 7, sum = 0;
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                if (line.indexOf("1") == -1 || sum != 0) {
                    continue;
                }
                int firstIdx = -1, lastIdx = -1;
                for (int j = 0; j < M; j++) {
                    if (line.charAt(j) == '1') {
                        firstIdx = j;
                        break;
                    }
                }
                for (int j = M - 1; j >= 0; j--) {
                    if (line.charAt(j) == '1') {
                        lastIdx = j;
                        break;
                    }
                }

                while (idx >= 0) {
                    String idxNum = line.substring(lastIdx - 6, lastIdx+1);
//                    System.out.println(idxNum + ": " + CODE_MAP.get(idxNum));
                    encode[idx] = CODE_MAP.get(idxNum);
                    lastIdx -= 7;
                    idx--;
                }

                int odd = 0, even = 0;
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        odd += encode[j];
                    } else {
                        even += encode[j];
                    }
                }

                sb.append("#").append(t).append(" ");
                sum = odd * 3 + even;
                if (sum % 10 == 0) {
                    sb.append(odd+even);
                } else {
                    sb.append(0);
                }

                if (t != testCase) {
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}

/*
2
16 80
00000000000000000000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000000000000000000
00000000000000011101101100010111011011000101100010001101001001101110110000000000
00000000000000011101101100010111011011000101100010001101001001101110110000000000
00000000000000011101101100010111011011000101100010001101001001101110110000000000
00000000000000011101101100010111011011000101100010001101001001101110110000000000
00000000000000011101101100010111011011000101100010001101001001101110110000000000
00000000000000011101101100010111011011000101100010001101001001101110110000000000
00000000000000011101101100010111011011000101100010001101001001101110110000000000
00000000000000000000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000000000000000000
11 70
00000000000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000000000
00000001100101000110100011010111101101110010011001001101110110000000000
00000001100101000110100011010111101101110010011001001101110110000000000
00000001100101000110100011010111101101110010011001001101110110000000000
00000001100101000110100011010111101101110010011001001101110110000000000
00000001100101000110100011010111101101110010011001001101110110000000000
00000001100101000110100011010111101101110010011001001101110110000000000
00000000000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000000000
00000000000000000000000000000000000000000000000000000000000000000000000
 */