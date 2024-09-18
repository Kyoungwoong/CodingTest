package Programmers.Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Disk {
    private static int[][] jobs = {{0,3},{1,9},{2,6}}; // 9

    static class Process implements Comparable<Process> {
        int reqTime,duringTime;

        public Process(int reqTime, int duringTime) {
            this.reqTime = reqTime;
            this.duringTime = duringTime;
        }

        @Override
        public int compareTo(Process o) {
            return this.duringTime - o.duringTime;
        }
    }

    public static void main(String[] args) {
        // 요청시간이 빠른 것부터 정렬
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Process> pq = new PriorityQueue<>();

        int time = 0; // 현재 시간
        int totalTime = 0; // 작업 요청부터 종료까지 걸린 총 시간
        int idx = 0; // 처리할 작업 인덱스
        int completedJobs = 0; // 처리된 작업 수
        int len = jobs.length; // 작업 수

        while (completedJobs < len) {
            while (idx < len && jobs[idx][0] <= time) {
                pq.add(new Process(jobs[idx][0], jobs[idx][1]));
                idx++;
            }

            if (!pq.isEmpty()) {
                Process current = pq.poll();
                time += current.duringTime; // 현재 작업 처리 시간만큼 현재 시간 증가
                totalTime += time - current.reqTime; // 요청부터 종료까지 걸린 시간 누적
                completedJobs++; // 완료된 작업 수 증가
            } else {
                time = jobs[idx][0];
            }
        }

        System.out.println("answer = " + (totalTime / len));
    }
}
