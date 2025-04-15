package Baekjoon.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
    static class Room implements Comparable<Room> {
        int start, end;

        public Room(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Room room) {
            if (this.end == room.end) {
                return this.start - room.start;
            }
            return this.end - room.end;
        }
    }

    public static void main(String[] args) throws IOException {
//        prev();
        april15();
    }

    private static void april15() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Room> list = new ArrayList<>();
        StringTokenizer st;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            Room newRoom = new Room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            list.add(newRoom);
        }

        Collections.sort(list);
        int ans = 0;
        int curTime = -1;
        for (Room room : list) {
            if (curTime <= room.start) {
                curTime = room.end;
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static void prev() throws IOException {
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
