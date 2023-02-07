package CodeTree.IntermediateMID.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

class Wait implements Comparable<Wait>{
    int num, a, t, time = 0;
    public Wait(int num, int a, int t){
        this.num = num;
        this.a = a;
        this.t = t;
    }

    @Override
    public int compareTo(Wait w){
        if(this.a == w.a){
            return this.num - w.num;
        }else{
            return this.a - w.a;
        }
    }
}

class Person implements Comparable<Person>{
    int num, s, t;
    public Person(int num, int s, int t){
        this.num = num;
        this.s = s;
        this.t = t;
    }

    @Override
    public int compareTo(Person p){
        return this.num - p.num;
    }
}

public class Waiting {
    public static int n, waiting = 0;
    public static PriorityQueue<Wait> pq = new PriorityQueue<>();
    public static PriorityQueue<Person> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Wait(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int curTime;
        while(!pq.isEmpty() && q.isEmpty()){
            curTime = pq.peek().a;

            Person first = new Person(pq.peek().num, pq.peek().a, pq.peek().t);
            q.add(first);
            curTime = pq.peek().a;
            pq.poll();

            while(!q.isEmpty()){
                Person cur = q.poll();

                waiting = Math.max(waiting, curTime-cur.s);
                // System.out.println(curTime-cur.s);

                curTime += (cur.t);
                while(!pq.isEmpty() && pq.peek().a <= curTime){
                    Person addPerson = new Person(pq.peek().num, pq.peek().a, pq.peek().t);
                    q.add(addPerson);
                    pq.poll();
                }
            }
        }
        System.out.println(waiting);
    }
}
