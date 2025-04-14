package Programmers;

import java.util.*;
import java.io.*;

public class ParkingFee {
    private final static int DAYHOUR = 1439;
    static enum Type{
        IN, OUT
    }
    // 입차기록 저장용
    private static HashMap<Integer, Integer> result = new HashMap<>();
    private static HashMap<Integer, Integer> temp = new HashMap<>();

//    static int[] fees = {180, 5000, 10, 600};
//    static String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

//    static int[] fees = {120, 0, 60, 591};
//    static String[] records = {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};

    static int[] fees = {1, 461, 1, 10};
    static String[] records = {"00:00 1234 IN"};

    public static void main(String[] args) {
        // 기본시간     기본요금       단위시간         단위요금
        // fee[0]     fee[1]      fee[2]         fee[3]
        int len = records.length;
        StringTokenizer st;

        for(int i = 0; i < len; i++) {
            st = new StringTokenizer(records[i]);

            String timeS = st.nextToken();
            int time = Integer.parseInt(timeS.substring(0, 2)) * 60 + Integer.parseInt(timeS.substring(3));
//            System.out.println("now Time is: " + timeS + " " + Integer.parseInt(timeS.substring(0, 2)) + " " + Integer.parseInt(timeS.substring(3)) + " " + time);
            int num = Integer.parseInt(st.nextToken());
            Enum state = (st.nextToken().equals("IN")) ? Type.IN : Type.OUT;

            if (state == Type.IN) {
                temp.put(num, time);
                continue;
            }

            int prevTime = temp.get(num);
            temp.remove(num);

            int parkingTime = time - prevTime;

            result.put(num, result.getOrDefault(num, 0) + parkingTime);
        }

        for(Integer num: temp.keySet()) {
            int prevTime = temp.get(num);
            int parkingTime = DAYHOUR - prevTime;

            if(result.containsKey(num)) {
                result.put(num, result.get(num) + parkingTime);
            } else {
                result.put(num, parkingTime);
            }
        }

        for(Integer num: result.keySet()) {
            System.out.println("num: " + num + " parkingTime: " + result.get(num));
        }

        len = result.size();
        int idx = 0;
        int[] answer = new int[len];

        for(Integer num: result.keySet()) {
            int fee = fees[1];
            if(result.get(num) - fees[0] > 0) {
                if((result.get(num) - fees[0]) % fees[2] != 0) {
                    fee += ((result.get(num) - fees[0]) / fees[2] + 1) * fees[3];
                } else {
                    fee += ((result.get(num) - fees[0]) / fees[2]) * fees[3];
                }

            }
            // System.out.println("cur num: " + num + 
            //             " parking time: " + (result.get(num) - fees[0]) +" fee: " + fee);
            answer[idx++] = fee;
        }

        for (int num: answer) {
            System.out.println(num);
        }
    }
}