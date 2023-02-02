package CodeTree.IntermediateMID.HashSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Invite {
    private static int n, g;
    private static HashSet<Integer> invite = new HashSet<>();
    private static HashSet<Integer>[] group;
    private static ArrayList<Integer>[] belong;

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        group = new HashSet[g];
        Queue<Integer> q = new LinkedList<>();
        belong = new ArrayList[n+1];

        for(int i = 1; i <= n; i++){
            belong[i] = new ArrayList<>();
        }

        for(int i = 0; i < g; i++){
            st = new StringTokenizer(br.readLine());
            group[i] = new HashSet<>();
            int num = Integer.parseInt(st.nextToken());

            for(int j = 0; j < num; j++){
                int k = Integer.parseInt(st.nextToken());
                // 어느 그룹에 누가 있는지
                group[i].add(k);
                // k번째 애가 무슨 그룹에 있는지
                belong[k].add(i);
            }
            // q.add(i);
        }

        q.add(1);
        invite.add(1);
        while(!q.isEmpty()){
            int cur = q.poll();

            for(int i = 0; i < belong[cur].size(); i++){
                int gNum = belong[cur].get(i);
                group[gNum].remove(cur);

                if(group[gNum].size() == 1){
                    int pNum = new ArrayList<>(group[gNum]).get(0);
                    if(!invite.contains(pNum)){
                        invite.add(pNum);
                        q.add(pNum);
                    }
                }
            }
        }

        System.out.println(invite.size());
    }
}
