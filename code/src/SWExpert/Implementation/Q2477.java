package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2477 {
    static class Person implements Comparable<Person> {
        int idx, arriveTime;

        public Person(int idx, int arriveTime) {
            this.idx = idx;
            this.arriveTime = arriveTime;
        }

        @Override
        public int compareTo(Person person) {
            if (this.arriveTime == person.arriveTime) {
                return Integer.compare(this.idx, person.idx);
            }
            return Integer.compare(this.arriveTime, person.arriveTime);
        }
    }

    static class Reception implements Comparable<Reception> {
        int idx;

        public Reception(int idx) {
            this.idx = idx;
        }

        @Override
        public int compareTo(Reception o) {
            return Integer.compare(this.idx, o.idx);
        }
    }

    static class Working implements Comparable<Working> {
        int idx, customerIdx, endTime;

        public Working(int idx, int customerIdx, int endTime) {
            this.idx = idx;
            this.customerIdx = customerIdx;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Working o) {
            return this.endTime - o.endTime;
        }
    }

    static class Repair implements Comparable<Repair> {
        int idx;

        public Repair(int idx) {
            this.idx = idx;
        }

        @Override
        public int compareTo(Repair o) {
            return Integer.compare(this.idx, o.idx);
        }
    }

    static class WaitingRepair implements Comparable<WaitingRepair> {
        int customerId, endTime, receptionId;

        public WaitingRepair(int customerId, int endTime, int receptionId) {
            this.customerId = customerId;
            this.endTime = endTime;
            this.receptionId = receptionId;
        }

        @Override
        public int compareTo(WaitingRepair o) {
            if (this.endTime == o.endTime) {
                return Integer.compare(this.receptionId, o.receptionId);
            }
            return Integer.compare(endTime, o.endTime);
        }
    }


    private static int tc, N, M, K, A, B, ans = 0;
    private static List<Integer> a, b;
    private static Set<Integer> customers;
    private static PriorityQueue<Person> waiting;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken()); // 접수 창구 갯수
            M = Integer.parseInt(st.nextToken()); // 정비 창구 갯수
            K = Integer.parseInt(st.nextToken()); // 고객의 수
            A = Integer.parseInt(st.nextToken()) - 1; // 지갑을 두고 간 고객이 이용한 접수 창구번호
            B = Integer.parseInt(st.nextToken()) - 1; // 지갑을 두고 간 고객이 이용한 정비 창구번호

            st = new StringTokenizer(br.readLine());
            a = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                a.add(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            b = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                b.add(Integer.parseInt(st.nextToken()));
            }

            // tk: t 고객이 방문한 시간
            st = new StringTokenizer(br.readLine());
            waiting = new PriorityQueue<>();
            for (int i = 0; i < K; i++) {
                waiting.add(new Person(i, Integer.parseInt(st.nextToken())));
            }

            customers = new HashSet<>();
            // simulate
            int time = 0;
            PriorityQueue<Reception> remainReception = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                remainReception.add(new Reception(i));
            }
            PriorityQueue<Working> workingReception = new PriorityQueue<>();

            PriorityQueue<Repair> remainRepair = new PriorityQueue<>();
            for (int i = 0; i < M; i++) {
                remainRepair.add(new Repair(i));
            }
            PriorityQueue<WaitingRepair> waitingRepair = new PriorityQueue();

            // 접수 창구 프로세스
            while (!waiting.isEmpty()) {
                Person p = waiting.poll();
                time = Math.max(time, p.arriveTime);

                // 접수창구 자리가 없을 때
                while (true) {
                    if (workingReception.isEmpty()) {
                        break;
                    }
                    Working working = workingReception.peek();
                    if (working.endTime <= time) {
                        workingReception.poll();
                        // 끝난시간 저장.
                        waitingRepair.add(new WaitingRepair(working.customerIdx, working.endTime, working.idx));
                        // 다시 remain에 저장
                        remainReception.add(new Reception(working.idx));
                    } else {
                        if (remainReception.isEmpty()) {
                            workingReception.poll();
                            time = working.endTime;

                            // 끝난시간 저장.
                            waitingRepair.add(new WaitingRepair(working.customerIdx, working.endTime, working.idx));
                            // 다시 remain에 저장
                            remainReception.add(new Reception(working.idx));
                        } else {
                            break;
                        }
                    }
                }

                // 접수창구 받아서 처리
                Reception remain = remainReception.poll();
                System.out.println("현재시간: " + time +"\t고객: " + p.idx + " 접수창고: " + remain.idx + "사용");
                if (remain.idx == A) { // A를 사용한 고객 저장.
                    customers.add(p.idx);
                }
                workingReception.add(new Working(remain.idx, p.idx, time + a.get(remain.idx)));
            }
            // 남은 시간 동안의 접수 창구 프로세스
            while (!workingReception.isEmpty()) {
                Working working = workingReception.poll();
                waitingRepair.add(new WaitingRepair(working.customerIdx, working.endTime, working.idx));

                // TODO: remain처리 안해도 되지 않을까?
            }
            System.out.println();

            // 정비창구 처리
            PriorityQueue<Working> workingRepair = new PriorityQueue<>();
            time = 0;
            while (!waitingRepair.isEmpty()) {
                WaitingRepair waitingPerson = waitingRepair.poll();
                time = Math.max(time, waitingPerson.endTime);

                // 정비 창구 자리 없을 때
                while (true) {
                    if (workingRepair.isEmpty()) {
                        break;
                    }
                    Working working = workingRepair.peek();
                    if (working.endTime <= time) {
                        workingRepair.poll();
                        // 다시 remain에 저장
                        remainRepair.add(new Repair(working.idx));
                    } else {
                        if (remainRepair.isEmpty()) {
                            workingRepair.poll();
                            time = working.endTime;
                            remainRepair.add(new Repair(working.idx));
                        } else {
                            break;
                        }
                    }
                }

                // 정비 창구 처리
                Repair remain = remainRepair.poll();
                System.out.println("현재시간: " + time +"\t고객: " + waitingPerson.customerId + " 정비창고: " + remain.idx + "사용");
                if (remain.idx != B && customers.contains(waitingPerson.customerId)) {
                    customers.remove(waitingPerson.customerId);
                }
                workingRepair.add(
                        new Working(remain.idx, waitingPerson.customerId, time + b.get(remain.idx)));
            }
            // 남은 시간 동안의 정비 창구 프로세스
            // TODO: remain처리 안해도 되지 않을까?

            // ans
            int sum = 0;
            System.out.print("A를 사용하고 B를 사용한 customerId: \t");
            for (int customer : customers) {
                System.out.print(customer + "\t");
                sum += (customer + 1);
            }
            System.out.println();

            sb.append("#").append(t).append(" ").append(sum == 0 ? -1 : sum).append("\n");
        }
        System.out.println(sb);
    }
}
/*
10
1 1 2 1 1
5
7
7 10
2 2 6 1 2
3 2
4 2
0 0 1 2 3 4
2 1 4 2 1
2 5
1
0 1 3 10
4 1 10 3 1
4 6 4 8
1
0 3 4 4 5 6 9 9 9 10
2 2 8 2 1
10 3
2 9
0 2 3 3 4 6 6 7
3 2 10 1 2
5 5 8
3 5
0 0 4 7 8 8 9 9 10 10
4 2 30 4 2
3 2 2 10
2 6
0 2 2 4 5 6 7 9 9 15 15 16 17 18 18 19 19 22 23 24 24 24 25 25 25 26 27 27 28 29
5 2 70 5 1
6 6 6 4 5
5 6
0 0 0 1 1 5 6 8 10 12 12 14 15 15 17 17 18 18 19 19 22 24 26 26 28 30 30 31 32 32 32 33 33 33 34 35 35 35 37 38 40 40 41 42 46 46 47 48 48 51 53 54 55 56 56 57 59 60 61 61 63 63 64 65 65 66 67 67 70 70
4 3 100 1 3
9 9 5 2
8 7 8
0 3 5 6 10 12 13 14 15 15 19 19 20 20 21 22 22 23 23 26 26 26 26 30 31 33 33 35 36 39 39 39 40 40 41 41 42 43 43 45 47 48 49 50 50 51 51 51 51 51 52 54 58 58 59 60 60 60 60 61 61 62 62 63 63 66 69 69 69 70 71 72 73 73 74 75 76 76 79 81 82 82 82 85 87 87 88 91 91 91 94 94 96 96 96 98 98 99 99 100
5 3 100 1 1
9 10 3 5 3
8 8 10
0 0 0 0 1 1 3 3 4 5 7 8 8 9 9 11 11 13 14 15 16 17 17 18 19 19 22 22 23 23 25 26 26 27 27 30 30 34 34 36 36 38 41 44 44 45 45 47 47 49 50 50 51 53 53 58 61 62 62 63 64 65 67 67 69 70 72 73 75 76 77 80 80 80 81 81 83 83 83 84 87 87 89 89 89 90 90 91 92 93 93 93 93 95 95 98 99 100 100 100
 */
