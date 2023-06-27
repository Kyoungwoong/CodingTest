package ThisIsCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Score implements Comparable<Score>{
    int Korean, English, Math;
    String name;

    public Score(String name, int korean, int english, int math) {
        this.Korean = korean;
        this.English = english;
        this.Math = math;
        this.name = name;
    }

    @Override
    public int compareTo(Score s) {
        if (this.Korean == s.Korean) {
            if (this.English == s.English) {
                if (this.Math == s.Math) {
                    return s.name.compareTo(this.name);
                }
                return this.Math - s.Math;
            }
//            return this.English - s.English;
            return s.English - this.English;
        }else{
            return this.Korean - s.Korean;
        }
    }
}

public class KEM {
    private static int N;
    private static ArrayList<Score> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr.add(new Score(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(arr);

        for (int i = 0; i < N; i++) {
            System.out.println("arr.get(i).name = " + arr.get(i).name);
        }

    }
}

/*
12
Junkyu 50 60 100
Sangkeun 80 60 50
Sunyoung 80 70 100
Soong 50 60 90
Haebin 50 60 100
Kangsoo 60 80 100
Donghyuk 80 60 100
Sei 70 70 70
Wonseob 70 70 90
Sanghyun 70 70 80
nsj 80 80 80
Taewhan 50 60 90
 */
