package Baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) { // T <= 100
            String cmd = br.readLine();
            int len = cmd.length(); // len <= 100000

            int N = Integer.parseInt(br.readLine()); // n 100000
            String arr = br.readLine();
            arr = arr.substring(1, arr.length()-1);
            StringTokenizer st = new StringTokenizer(arr, ",");

            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                arrayList.add(Integer.parseInt(st.nextToken()));
            }
            boolean check = true; // 정방향
            boolean finish = false;
            for (int i = 0; i < len; i++) {
                switch (cmd.charAt(i)) {
                    case 'R':
                        check = !check;
                        break;
                    case 'D':
                        if (arrayList.size() == 0) {
                            sb.append("error\n");
                            finish = true;
                            break;
                        }
                        if (check) {
                            arrayList.remove(0);
                        } else {
                            arrayList.remove(arrayList.size() - 1);
                        }
                        break;
                }
                if (finish) {
                    break;
                }
            }
            int size = arrayList.size();
            if(size == 0 || finish) {
                if (!finish) {
                    sb.append("[]\n");
                }
                continue;
            }

            if (!check) {
                Collections.reverse(arrayList);
            }

            sb.append("[");
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    sb.append(arrayList.get(i));
                } else {
                    sb.append(arrayList.get(i)+",");
                }

            }
            sb.append("]\n");
        }
        System.out.println(sb);
    }
}
