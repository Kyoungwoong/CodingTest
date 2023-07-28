package Baekjoon.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Meeting implements Comparable<Meeting> {
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

public class Q1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Meeting[] room = new Meeting[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            room[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(room);
        int able = 1;
        Meeting cur = room[0];
        for (int i = 1; i < N; i++) {
            if (room[i].start >= cur.end) {
                able++;
                cur = room[i];
            }
        }
        System.out.println(able);
    }
}
