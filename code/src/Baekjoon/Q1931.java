package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1931 {
    static class Meeting implements Comparable<Meeting>{
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting meeting) {
            if (this.end == meeting.end) {
                return this.start - meeting.start;
            } else {
                return this.end - meeting.end;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        PriorityQueue<Meeting> pq = new PriorityQueue<>();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        long ans = 0;
        int end = -1;
        while (!pq.isEmpty()) {
            Meeting now = pq.poll();
            if (now.start >= end) {
                ans++;
                end = now.end;
            }
        }

        System.out.println(ans);

    }
}
