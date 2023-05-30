package ThisIsCodingTest.Greedy;

// 회전판에 먹어야 할 N개의 음식.
// 음식에는 1부터 N까지의 번호.
// 1번부터 먹기 시작. 번호가 증가하는 순서대로 음식을 무지 앞으로
// 마지막 번호를 먹고 난 후에는 1번이 다시 옴
// 무지는 음식하나를 1초 동안 섭취한 후 남은 음식은 그대로 두고 다음 음식을 섭취.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Food implements Comparable<Food>{
    int time, index;

    public Food(int time, int index){
        this.time = time;
        this.index = index;
    }

    @Override
    public int compareTo(Food f){
        return this.time - f.time;
    }
}

class Solution {
        public ArrayList<Integer> ans = new ArrayList<>();
    // 시간 순으로 오름차순 정렬
    public PriorityQueue<Food> foods = new PriorityQueue<>();

    public int solution(int[] food_times, long k) {
        int answer = 0;
        int len = food_times.length;
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum += food_times[i];
            foods.add(new Food(food_times[i], i + 1));
        }

        // 더이상 먹을 음식이 없을 때
        if(sum <= k){
            return -1;
        }

        sum = 0; // 현재 시간
        // len
        int previous = 0; // 이전시간
        while(sum + (foods.peek().time - previous) * len <= k){
            Food now = foods.poll();
            sum += (now.time - previous) * len;
            len--;
            previous = now.time;
        }

        // 남은 음식 중에서 몇 번째 음식인지 확인하여 출력
        ArrayList<Food> result = new ArrayList<>();
        while (!foods.isEmpty()) {
            result.add(foods.poll());
        }
        // 음식의 번호 기준으로 정렬
        Collections.sort(result, new Comparator<Food>() {
            @Override
            public int compare(Food a, Food b) {
                return Integer.compare(a.index, b.index);
            }
        });

        return result.get((int) (k-sum)%len).index;
    }
}

// food_times [3,1,2]
// k = 5, result = 1