package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Q1242 {
    private static int T, N, M;
    private static final Map<Character, String> binary = new HashMap<>();
    private static final Map<String, Integer> CODE_MAP = new HashMap<>();

    static {
        binary.put('0', "0000"); binary.put('1', "0001"); binary.put('2', "0010");
        binary.put('3', "0011"); binary.put('4', "0100"); binary.put('5', "0101");
        binary.put('6', "0110"); binary.put('7', "0111"); binary.put('8', "1000");
        binary.put('9', "1001"); binary.put('A', "1010"); binary.put('B', "1011");
        binary.put('C', "1100"); binary.put('D', "1101"); binary.put('E', "1110");
        binary.put('F', "1111");

        String[] codes = {
                "0001101", "0011001", "0010011", "0111101", "0100011",
                "0110001", "0101111", "0111011", "0110111", "0001011"
        };
        for (int i = 0; i < 10; i++) {
            CODE_MAP.put(codes[i], i);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            Set<String> visited = new HashSet<>();
            int result = 0;

            String[] hexLines = new String[N];
            for (int i = 0; i < N; i++) {
                hexLines[i] = br.readLine();
            }

            for (int i = 0; i < N; i++) {
                StringBuilder binaryLine = new StringBuilder();
                for (char c : hexLines[i].toCharArray()) {
                    binaryLine.append(binary.get(c));
                }

                int end = binaryLine.lastIndexOf("1");
                while (end >= 55) {
                    int start = end - 55;
                    String chunk = binaryLine.substring(start, end + 1);
                    StringBuilder sbKey = new StringBuilder();
                    int[] numbers = new int[8];
                    boolean isValid = true;

                    for (int j = 0; j < 8; j++) {
                        String code = chunk.substring(j * 7, (j + 1) * 7);
                        if (!CODE_MAP.containsKey(code)) {
                            isValid = false;
                            break;
                        }
                        numbers[j] = CODE_MAP.get(code);
                        sbKey.append(code);
                    }

                    if (isValid && !visited.contains(sbKey.toString())) {
                        int sum = 0, verify = 0;
                        for (int j = 0; j < 8; j++) {
                            if (j % 2 == 0) verify += numbers[j] * 3;
                            else verify += numbers[j];
                            sum += numbers[j];
                        }
                        if (verify % 10 == 0) {
                            result += sum;
                            visited.add(sbKey.toString());
                        }
                    }
                    end = start - 1;
                }
            }
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }
}
