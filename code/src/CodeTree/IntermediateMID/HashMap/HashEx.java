package CodeTree.IntermediateMID.HashMap;

import java.util.HashMap;

public class HashEx {
    public static void main(String[] args) {
        HashMap<Integer, Integer> m = new HashMap<>(); // 정수를 관리할 hashmap을 선언합니다. => 빈 hashmap
        m.put(5, 6);
        m.put(2, 7);
        m.put(10, 1);

        if(m.containsKey(2)) {             // key가 2이 쌍이 hashmap에 있다면
            System.out.println(m.get(2));  // key가 2인 value값을 출력합니다.
        }

        m.remove(5);                       // key가 5인 쌍을 제거합니다.

        if(!m.containsKey(5)) {            // key가 5인 쌍이 hashmap에 없다면
            System.out.println("not exists!");
        }

        m.put(2, 10);                      // key가 2인 곳에 들어있는 value값을 10으로 변경합니다.
        System.out.println(m.get(2));      // key가 2인 value값(10)을 출력합니다.

        System.out.println(m.getOrDefault(8, -1));
    }
}
