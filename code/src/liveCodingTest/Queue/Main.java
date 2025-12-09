package liveCodingTest.Queue;

public class Main {
    public static void main(String[] args) {

        System.out.println("===== MyArrayQueue Test =====");
        testQueue(new MyArrayQueue<Integer>());

        System.out.println();
        System.out.println("===== MyCircularQueue Test =====");
//        testQueue(new MyCircularQueue<Integer>());
    }

    private static void testQueue(MyQueue<Integer> q) {
        System.out.println("[초기 상태]");
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("size   : " + q.size());
        System.out.println();

        System.out.println("[offer 3번: 10, 20, 30]");
        q.offer(10);
        q.offer(20);
        q.offer(30);
        System.out.println("size   : " + q.size());
        System.out.println("peek   : " + q.peek()); // 10 예상
        System.out.println("queue  : " + q);        // toString()
        System.out.println();

        System.out.println("[poll 1번]");
        Integer polled = q.poll();
        System.out.println("poll   : " + polled);   // 10 예상
        System.out.println("size   : " + q.size()); // 2
        System.out.println("peek   : " + q.peek()); // 20 예상
        System.out.println("queue  : " + q);
        System.out.println();

        System.out.println("[추가 offer 2번: 40, 50]");
        q.offer(40);
        q.offer(50);
        System.out.println("size   : " + q.size()); // 4
        System.out.println("peek   : " + q.peek()); // 20
        System.out.println("queue  : " + q);
        System.out.println();

        System.out.println("[모두 poll]");
        while (!q.isEmpty()) {
            System.out.println("poll: " + q.poll());
        }
        System.out.println("size   : " + q.size());
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println();

        System.out.println("[빈 큐에서 poll/peek 예외 테스트]");
        try {
            q.poll();
        } catch (RuntimeException e) {
            System.out.println("빈 큐 poll 예외 발생: " + e);
        }

        try {
            q.peek();
        } catch (RuntimeException e) {
            System.out.println("빈 큐 peek 예외 발생: " + e);
        }
    }
}

/**
 ===== MyArrayQueue Test =====
 [초기 상태]
 isEmpty: true
 size   : 0

 [offer 3번: 10, 20, 30]
 size   : 3
 peek   : 10
 queue  : [10, 20, 30]

 [poll 1번]
 poll   : 10
 size   : 2
 peek   : 20
 queue  : [20, 30]

 [추가 offer 2번: 40, 50]
 size   : 4
 peek   : 20
 queue  : [20, 30, 40, 50]

 [모두 poll]
 poll: 20
 poll: 30
 poll: 40
 poll: 50
 size   : 0
 isEmpty: true

 [빈 큐에서 poll/peek 예외 테스트]
 빈 큐 poll 예외 발생: java.lang.IllegalStateException: queue is empty
 빈 큐 peek 예외 발생: java.lang.IllegalStateException: queue is empty

 ===== MyCircularQueue Test =====
 [초기 상태]
 isEmpty: true
 size   : 0

 [offer 3번: 10, 20, 30]
 size   : 3
 peek   : 10
 queue  : [10, 20, 30]

 [poll 1번]
 poll   : 10
 size   : 2
 peek   : 20
 queue  : [20, 30]

 [추가 offer 2번: 40, 50]
 size   : 4
 peek   : 20
 queue  : [20, 30, 40, 50]

 [모두 poll]
 poll: 20
 poll: 30
 poll: 40
 poll: 50
 size   : 0
 isEmpty: true

 [빈 큐에서 poll/peek 예외 테스트]
 빈 큐 poll 예외 발생: java.lang.IllegalStateException: queue is empty
 빈 큐 peek 예외 발생: java.lang.IllegalStateException: queue is empty

 */