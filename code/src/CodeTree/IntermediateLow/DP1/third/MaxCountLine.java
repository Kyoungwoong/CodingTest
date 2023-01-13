package CodeTree.IntermediateLow.DP1.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Pair{
    int x1, x2;
    public Pair(int x1, int x2){
        this.x1 = x1;
        this.x2 = x2;
    }
}

public class MaxCountLine {
    private static int n, ans = -1;
    // 초기 세팅 선분
    private static ArrayList<Pair> line = new ArrayList<>();
    // 선택된 선분
    private static ArrayList<Pair> selectedLine = new ArrayList<>();

    // 디버깅 용 print
    public static void print(int step){
        System.out.println("Round: " + step);
        System.out.println("std: " + line.get(step).x1 + " " + line.get(step).x2);
        for(int i = 0; i < (int)selectedLine.size(); i++){
            System.out.print(selectedLine.get(i).x1 + " " + selectedLine.get(i).x2 + " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void maxCountLine(){

        for(int i = 0; i < (int)line.size(); i++){
            // 값 할당하고 초기화
            selectedLine.clear();

            // 기준 선분. 무조건 이 선분을 포함한다.
            Pair stdPair = line.get(i);
            selectedLine.add(stdPair);

            for(int j = i+1; j < (int)line.size(); j++){
                Pair addPair = line.get(j);
                boolean isOkay = false;

                for(int k = 0; k < (int)selectedLine.size(); k++){
                    // 선분이 겹치지 않을 조건.
                    if(addPair.x1 > selectedLine.get(k).x2 || addPair.x2 < selectedLine.get(k).x1){
                        isOkay = true;
                    }else{
                        isOkay = false;
                        break;
                    }
                }
                if(isOkay){
                    selectedLine.add(addPair);
                }
            }
            ans = Math.max(ans, (int)selectedLine.size());
            // print(i);
        }
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            Pair newPair = new Pair(x1, x2);
            line.add(newPair);
        }

        maxCountLine();

        System.out.println(ans);
    }
}

