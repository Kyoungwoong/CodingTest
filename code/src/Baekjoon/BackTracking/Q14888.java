package Baekjoon.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q14888 {
    private static int N, MAX_VALUE = Integer.MIN_VALUE, MIN_VALUE = Integer.MAX_VALUE;
    private static int[] numbers;
    private static int[] opcode = new int[4];
    public static ArrayList<Integer> arr = new ArrayList<>();
    public static ArrayList<String> arrayList = new ArrayList<>();

    public static void dfs(int i, int now) {
//        System.out.println("i  now = " + i + " " + now);
        if (i == N) {
            MIN_VALUE = Math.min(MIN_VALUE, now);
            MAX_VALUE = Math.max(MAX_VALUE, now);
        } else {
            // 각 연산자에 대하여 재귀적으로 수행
            if (opcode[0] > 0) {
                opcode[0] -= 1;
                dfs(i + 1, now + arr.get(i));
                opcode[0] += 1;
            }
            if (opcode[1] > 0) {
                opcode[1] -= 1;
                dfs(i + 1, now - arr.get(i));
                opcode[1] += 1;
            }
            if (opcode[2] > 0) {
                opcode[2] -= 1;
                dfs(i + 1, now * arr.get(i));
                opcode[2] += 1;
            }
            if (opcode[3] > 0) {
                opcode[3] -= 1;
                dfs(i + 1, now / arr.get(i));
                opcode[3] += 1;
            }
        }
    }

    public static void getScore() {
        int ans = numbers[0], idx = 1;
        for (int i = 0; i < arrayList.size(); i++) {
            switch (arrayList.get(i)){
                case "+":
                    ans = ans + numbers[idx];
                    break;
                case "-":
                    ans = ans - numbers[idx];
                    break;
                case "*":
                    ans = ans * numbers[idx];
                    break;
                case "/":
                    ans = ans / numbers[idx];
                    break;
            }
            idx++;
        }
        MAX_VALUE = Math.max(MAX_VALUE, ans);
        MIN_VALUE = Math.min(MIN_VALUE, ans);
    }

    public static void myDfs(int count){
        if(count == N){
            getScore();
            return;
        }
        if(opcode[0] > 0){
            opcode[0]--;
            arrayList.add("+");
            myDfs(count + 1);
            arrayList.remove(arrayList.size() - 1);
            opcode[0]++;
        }
        if(opcode[1] > 0){
            opcode[1]--;
            arrayList.add("-");
            myDfs(count + 1);
            arrayList.remove(arrayList.size() - 1);
            opcode[1]++;
        }
        if (opcode[2] > 0) {
            opcode[2]--;
            arrayList.add("*");
            myDfs(count + 1);
            arrayList.remove(arrayList.size() - 1);
            opcode[2]++;
        }
        if (opcode[3] > 0) {
            opcode[3]--;
            arrayList.add("/");
            myDfs(count + 1);
            arrayList.remove(arrayList.size() - 1);
            opcode[3]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr.add(num);
            numbers[i] = num;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            opcode[i] = Integer.parseInt(st.nextToken());
        }

        // 순열(ㅈㅐ귀) 어떻게 하는지 노션 찾아보기 => 모든 상황
//        dfs(1, arr.get(0));
        myDfs(1);
        System.out.println(MAX_VALUE);
        System.out.println(MIN_VALUE);
    }
}
