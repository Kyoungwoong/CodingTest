package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private static final int N = 4;
    private static int[] answer = {5, 2, 9, 4};

    public static Result query(int[] guess) {
        if (!isValid(guess)) return new Result(-1, -1);

        int strike = 0;
        int ball = 0;
        boolean[] used = new boolean[10];

        for (int i = 0; i < N; i++) {
            if (guess[i] == answer[i]) {
                strike++;
            } else {
                used[answer[i]] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            if (guess[i] != answer[i] && used[guess[i]]) {
                ball++;
            }
        }

        return new Result(strike, ball);
    }

    private static boolean isValid(int[] guess) {
        boolean[] seen = new boolean[10];
        for (int num : guess) {
            if (seen[num]) return false;
            seen[num] = true;
        }
        return true;
    }

    static class Result {
        int strike, ball;
        Result(int strike, int ball) {
            this.strike = strike;
            this.ball = ball;
        }
    }
}


public class Q1768 {
    static class Result {
        int strike, ball;
        Result(int strike, int ball) {
            this.strike = strike;
            this.ball = ball;
        }
    }

    private static final int N = 4;
    private static List<int[]> allCandidates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) {
            int guess = Integer.parseInt(br.readLine());
            int[] guessArray = new int[] {
                    guess / 1000,
                    (guess % 1000) / 100,
                    (guess % 100) / 10,
                    guess % 10
            };

            allCandidates = new ArrayList<>();
            int[] array = new int[N];
            boolean[] visited = new boolean[10];
            makeAllCandidates(0, array, visited);

            doUserImplementation(guessArray);
        }
    }

    private static void makeAllCandidates(int depth, int[] array, boolean[] visited) {
        if (depth == N) {
            allCandidates.add(array.clone());
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            array[depth] = i;
            makeAllCandidates(depth + 1, array, visited);
            visited[i] = false;
        }
    }

    public static void doUserImplementation(int[] guess) {
        System.out.println("========================");
        int count = 0;
        List<int[]> candidates = new ArrayList<>(allCandidates);

        while (!candidates.isEmpty()) {
            int[] tryGuess = candidates.get(0).clone();
            count++;
            Solution.Result response = Solution.query(tryGuess);

            String guessStr = Arrays.stream(tryGuess)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining());
            System.out.println("try: " + guessStr + ": \t" + response.strike + "strikes " + response.ball + "balls");

            if (response.strike == N) {
                System.arraycopy(tryGuess, 0, guess, 0, N);
                return;
            }

            List<int[]> nextCandidates = new ArrayList<>();
            for (int[] remain : candidates) {
                Result cmp = compare(tryGuess, remain);
                if (cmp.strike == response.strike && cmp.ball == response.ball) {
                    nextCandidates.add(remain);
                }
            }
            candidates = nextCandidates;
        }
    }

    private static Result compare(int[] guess, int[] remain) {
        int strike = 0, ball = 0;
        boolean[] visited = new boolean[N];
        Set<Integer> guessSet = new HashSet<>();

        for (int num : guess) guessSet.add(num);

        for (int i = 0; i < N; i++) {
            if (guess[i] == remain[i]) {
                strike++;
                guessSet.remove(guess[i]);
                visited[i] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && guessSet.contains(remain[i])) {
                ball++;
                guessSet.remove(remain[i]);
            }
        }

        return new Result(strike, ball);
    }
}
/*
5
8975
6142
5047
1860
5419
 */
