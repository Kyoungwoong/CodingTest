package SWExpert.Heap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Heap {
    private static ArrayList<Integer> heap;

    public static void init(){
        heap = new ArrayList<>();
        heap.add(0);
    }

    public static void insert(int val){
        heap.add(val);
        int p = heap.size()-1;

        while(p > 1 && heap.get(p/2) < heap.get(p)){
            int temp = heap.get(p/2);
            heap.set(p/2, heap.get(p));
            heap.set(p, temp);
            p /= 2;
        }
    }

    public static int delete(){
        if(heap.size() == 1){
            return -1;
        }

        int delete = heap.get(1);
        heap.set(1, heap.get(heap.size()-1));
        heap.remove(heap.get(heap.size()-1));

        int pos = 1;
        while((pos*2) < heap.size()) {
            int max = heap.get(pos * 2);
            int maxPos = pos * 2;

            if ((pos * 2 + 1) < heap.size() && max < heap.get(pos * 2 + 1)) {
                max = heap.get(pos * 2 + 1);
                maxPos = pos * 2 + 1;
            }

            if (max < heap.get(pos))
                break;

            int tmp = heap.get(pos);
            heap.set(pos, max);
            heap.set(maxPos, tmp);
            pos = maxPos;
        }

        return delete;
    }

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("SW_Testcase/Heap/heap.txt"));
        StringBuilder sb;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            sb = new StringBuilder();
            int query = Integer.parseInt(br.readLine());

            init();

            for(int i = 0; i < query; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                switch (cmd){
                    case 1:
                        int operand = Integer.parseInt(st.nextToken());
                        insert(operand);
                        break;
                    case 2:
                        sb.append(delete() + " ");
                        break;
                }
            }
            System.out.println("#" + test_case + " " + sb);
        }
    }
}