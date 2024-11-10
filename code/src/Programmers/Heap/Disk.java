package Programmers.Heap;

import javax.lang.model.SourceVersion;
import java.util.*;

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

    static class Job implements Comparable<Job> {
        int requestTime, workingTime;

        public Job(int requestTime, int workingTime) {
            this.requestTime = requestTime;
            this.workingTime = workingTime;
        }

        @Override
        public int compareTo(Job j) {
//             if (this.workingTime == j.workingTime) {
//                 return this.requestTime - j.requestTime;
//             }

//             return this.workingTime - j.workingTime;
            if (this.requestTime == j.requestTime) {
                return this.workingTime - j.workingTime;
            }
            return this.requestTime - j.requestTime;
        }
    }


    public static void main(String[] args) {
//        prev();
        System.out.println(nov10());
    }

    private static int nov10() {
        PriorityQueue<Job> disk = new PriorityQueue<>();
        int len = jobs.length;

        for(int[] job: jobs) {
            disk.add(new Job(job[0], job[1]));
        }

        int time = 0; // 현재 시간
        int ans = 0; // 처리량 평균 구하기
        List<Job> execList = new ArrayList<>();

        while (!disk.isEmpty() || !execList.isEmpty()) {
            // 현재 시간에 요청된 작업을 모두 execList에 추가
            while (!disk.isEmpty() && time >= disk.peek().requestTime) {
                execList.add(disk.poll());
            }

            // 작업을 소요 시간 기준으로 정렬
            if (!execList.isEmpty()) {
                Collections.sort(execList, Comparator.comparingInt(job -> job.workingTime));
                Job exec = execList.remove(0);
                if (time < exec.requestTime) {
                    time = exec.requestTime;
                }
                time += exec.workingTime;
                ans += time - exec.requestTime;
            } else if (!disk.isEmpty()) {
                time = disk.peek().requestTime;
            }
        }

        return ans / len;
    }

    private static void prev() {
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
