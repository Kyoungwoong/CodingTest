package CodeTree.IntermediateMID.TreeSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Question implements Comparable<Question>{
    int p, l;
    public Question(int p, int l){
        this.p = p;
        this.l = l;
    }

    @Override
    public int compareTo(Question q){
        if(this.l == q.l){
            return this.p - q.p;
        }else{
            return this.l - q.l;
        }
    }
}

public class Recommend {
    private static int n, m, p, l;
    private static String cmd;
    private static TreeSet<Question> question = new TreeSet<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            question.add(new Question(p, l));
        }

        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();

            switch(cmd){
                case "ad":
                    p = Integer.parseInt(st.nextToken());
                    l = Integer.parseInt(st.nextToken());
                    question.add(new Question(p, l));
                    break;

                case "sv":
                    p = Integer.parseInt(st.nextToken());
                    l = Integer.parseInt(st.nextToken());
                    question.remove(new Question(p, l));
                    break;

                case "rc":
                    int x = Integer.parseInt(st.nextToken());
                    if(x == 1){
                        sb.append(question.last().p + "\n");
                    }else{
                        sb.append(question.first().p + "\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
    }
}
