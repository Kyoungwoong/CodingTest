package Programmers;

import java.util.*;

class Kakao {
    int answer = 0;
    char[][] input;
    ArrayList<Pair> selected;

    class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int m, int n, String[] board) {
        input = new char[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                input[i][j] = board[i].charAt(j);
            }
        }

        game(m, n);

        return answer;
    }

    public void game(int m, int n) {
        boolean check = true;
        while(check) {
            check = false; // 하나라도 깨졌으면 check = true;로 변환
            selected = new ArrayList<>();

            System.out.println("=======================board=============================");
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(input[i][j]);
                }
                System.out.println();
            }

            for(int i = 0; i < m-1; i++) {
                for(int j = 0; j < n-1; j++) {
                    char cur = input[i][j];
                    if(cur != '0' &&
                            cur == input[i][j+1] &&
                            cur == input[i+1][j] &&
                            cur == input[i+1][j+1]) {

                        selected.add(new Pair(i, j));
                        selected.add(new Pair(i, j + 1));
                        selected.add(new Pair(i + 1, j));
                        selected.add(new Pair(i + 1, j + 1));
                        check = true;
                    }
                }
            }
            if(check) {
                reLocation(m, n);
            }
        }
    }

    public void reLocation(int m, int n) {
        for(Pair cur: selected) {
            if(input[cur.x][cur.y] != '0') {
                input[cur.x][cur.y] = '0';
                answer++;
            }
        }

        System.out.println("======================= before board=============================");
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(input[i][j]);
            }
            System.out.println();
        }

        // reLocation
        for(int i = m-2; i >= 0; i--) {
            for(int j = 0; j < n; j++) {
                int idx = 1;
                if(input[i][j] != '0' && input[i+idx][j] == '0') {
                    while(i+idx < m && input[i+idx][j] == '0') {
                        idx++;
                    }
                    input[i+idx-1][j] = input[i][j];
                    input[i][j] = '0';
                }
            }
        }
    }
}

public class Block {
    public static void main(String[] args) {
        Kakao sol = new Kakao();

        int m1 = 4;
        int n1 = 5;
        String[] board1 = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        int result1 = sol.solution(m1, n1, board1);
        System.out.println(result1);  // 14

        int m2 = 6;
        int n2 = 6;
        String[] board2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        int result2 = sol.solution(m2, n2, board2);
        System.out.println(result2);  // 15
    }
}


