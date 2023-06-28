package ThisIsCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Card {
    private static int N;
    private static ArrayList<Integer> cards = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
            cards.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(cards);

        int ans = 0;
        while(cards.size() != 1){
            int a = cards.get(0);
            int b = cards.get(1);

            ans += a + b;
            cards.add(ans);

            cards.remove(0);
            cards.remove(0);

            Collections.sort(cards);
        }

        System.out.println(ans);
    }
    /*
    나동빈 풀이. 우선순위 큐 쓰는 것. 이게 더 좋은 풀이인듯.
    public static int n, result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 힙(Heap)에 초기 카드 묶음을 모두 삽입
        for (int i = 0; i < n; i++) {
            pq.offer(sc.nextInt());
        }

        // 힙(Heap)에 원소가 1개 남을 때까지
        while (pq.size() != 1) {
            // 가장 작은 2개의 카드 묶음 꺼내기
            int one = pq.poll();
            int two = pq.poll();
            // 카드 묶음을 합쳐서 다시 삽입
            int summary = one + two;
            result += summary;
            pq.offer(summary);
        }

        System.out.println(result);
    }
     */
}
/*
3
10
20
40
 */
