package CodeTree.IntermediateLow.Simulation.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class EscapeMiro {
    private static int MAXSIZE = 100;
    private static int n, x, y;
    private static char[][] arr = new char[MAXSIZE][MAXSIZE];

    public static boolean isRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }

    public static int escapeMiro(int curX, int curY, int dir) {
        // 우측방향을 바라보고 시작
        int count = 0;
        int initX = curX, initY = curY, initDir = dir;

        while(true){
            // System.out.println("count " + count);
            // System.out.println("curX " + curX);
            // System.out.println("curY " + curY);
            // System.out.println("dir " + dir);
            // System.out.println();
            if(curX == initX && curY == initY && initDir == dir && count != 0){
                return -1;
            }
            if(curX >=1 && curX < n-1 && curY >= 1 && curY <n-1){
                if(arr[curX-1][curY] == '#' && arr[curX+1][curY]=='#'&&arr[curX][curY+1]=='#'&&arr[curX][curY-1]=='#'){
                    return -1;
                }
            }

            switch(dir){
                // 오른쪽
                case 0:
                    if(!isRange(curX, curY+1)){
                        count++;
                        return count;
                    }
                    else{
                        if(arr[curX][curY+1] == '#'){
                            dir = 3;
                            continue;
                        }
                        if(arr[curX+1][curY+1] == '#'){
                            count++;
                            curY += 1;
                        }
                        else{
                            count += 2;
                            dir = 1;
                            curX += 1;
                            curY += 1;
                        }
                    }

                    break;

                // 아래쪽
                case 1:
                    if(!isRange(curX+1, curY)){
                        count++;
                        return count;
                    }
                    else{
                        if(arr[curX+1][curY] == '#'){
                            dir = 0;
                            continue;
                        }

                        if(arr[curX+1][curY-1] == '#'){
                            count++;
                            curX += 1;
                        }
                        else{
                            count += 2;
                            dir = 2;
                            curX += 1;
                            curY -= 1;
                        }
                    }
                    break;
                // 왼쪽
                case 2:
                    if(!isRange(curX, curY-1)){
                        count++;
                        return count;
                    }
                    else{
                        if(arr[curX][curY-1] == '#'){
                            dir = 1;
                            continue;
                        }
                        if(arr[curX-1][curY-1] == '#'){
                            count++;
                            curY -= 1;
                        }
                        else{
                            count += 2;
                            dir = 3;
                            curX -= 1;
                            curY -= 1;
                        }
                    }

                    break;
                //위쪽
                case 3:
                    if(!isRange(curX-1, curY)){
                        count++;
                        return count;
                    }
                    else{
                        if(arr[curX-1][curY] == '#'){
                            dir = 2;
                            continue;
                        }
                        if(arr[curX-1][curY+1] == '#'){
                            count++;
                            curX -= 1;
                        }
                        else{
                            count += 2;
                            dir = 0;
                            curX -= 1;
                            curY += 1;
                        }
                    }
                    break;
            }
        }

    }

    public static void main(String[] args) throws IOException{
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j<n; j++) {
                // nextToken하면 띄어쓰기가 없으므로 이렇게 받으면 안됨
                // arr[i][j] = st.nextToken().charAt(0);
                // arr[i][j] = st.nextToken().charAt(j);
                arr[i][j] = str.charAt(j);
            }
        }

        System.out.println(escapeMiro(x-1, y-1, 0));
    }
}