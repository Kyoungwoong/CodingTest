package liveCodingTest.List;

public class Main {
    public static void main(String[] args) {

        myArrayListTest();


        myLinkedListTest();
    }

    private static void myLinkedListTest() {
        System.out.println("\n===== MyLinkedList Test =====");
        MyList<Integer> linkedList = new MyLinkedList<>();

        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.print();
        System.out.println("size: " + linkedList.size());         // 3
        System.out.println("get(0): " + linkedList.get(0));       // 10

        linkedList.add(1, 99);
        linkedList.print();
        System.out.println("after add(1, 99): " + linkedList.get(1)); // 99

        System.out.println("remove(2): " + linkedList.remove(2)); // 20
        System.out.println("size after remove: " + linkedList.size());

        linkedList.remove(Integer.valueOf(10));
        System.out.println("size after remove(10): " + linkedList.size());

        linkedList.clear();
        System.out.println("isEmpty after clear: " + linkedList.isEmpty());
    }

    private static void myArrayListTest() {
        System.out.println("===== MyArrayList Test =====");
        MyList<String> arrayList = new MyArrayList<>();

        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        System.out.println("size: " + arrayList.size());          // 3
        System.out.println("get(1): " + arrayList.get(1));        // B

        arrayList.add(1, "X");
        System.out.println("after add(1, X): " + arrayList.get(1)); // X

        System.out.println("remove(2): " + arrayList.remove(2)); // B
        System.out.println("size after remove: " + arrayList.size());

        arrayList.remove("A");
        System.out.println("size after remove(\"A\"): " + arrayList.size());

        arrayList.clear();
        System.out.println("isEmpty after clear: " + arrayList.isEmpty());
    }
}

/**
 ===== MyArrayList Test =====
 size: 3
 get(1): B
 after add(1, X): X
 remove(2): B
 size after remove: 3
 size after remove("A"): 2
 isEmpty after clear: true

 ===== MyLinkedList Test =====
 size: 3
 get(0): 10
 after add(1, 99): 99
 remove(2): 20
 size after remove: 3
 size after remove(10): 2
 isEmpty after clear: true
 */
