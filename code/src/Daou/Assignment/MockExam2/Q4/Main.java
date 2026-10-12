package Daou.Assignment.MockExam2.Q4;

import java.util.*;

class MemberKey {

    private String id;

    public MemberKey(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof MemberKey other)) {
            return false;
        }

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

public class Main {

    public static void main(String[] args) {

        Map<MemberKey, String> map = new HashMap<>();

        MemberKey key1 = new MemberKey("A001");
        MemberKey key2 = new MemberKey("A001");

        map.put(key1, "Kim");

        System.out.println("1: " + map.get(key2));

        key1.setId("B001");

        System.out.println("2: " + map.get(key1));
        System.out.println("3: " + map.get(key2));
        System.out.println("4: " + map.size());

        map.put(new MemberKey("B001"), "Lee");

        System.out.println("5: " + map.size());
        System.out.println(
            "6: " + map.get(new MemberKey("B001"))
        );
    }
}
