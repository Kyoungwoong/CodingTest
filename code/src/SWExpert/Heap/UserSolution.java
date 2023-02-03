package SWExpert.Heap;

import java.util.ArrayList;
import java.util.LinkedList;

public class UserSolution {

    class People{
        int id, income, idx;
        public People(int idx, int id, int income){
            this.idx = idx;
            this.id = id;
            this.income = income;
        }

    }

    ArrayList<People> heap;

    int heapCnt = 0;
    public void init() {
        heap = new ArrayList<>();
        heap.add(new People(0,-1,-1));
        heapCnt = 1;
    }

    public void addUser(int uID, int income) {
        People addPeople = new People(heapCnt, uID, income);
        heap.add(addPeople);
        heapify(addPeople);
        heapCnt++;
    }

    int getTop10(int[] result) {
        int size;
        if(heapCnt >= 11){
            size = 10;
        }else{
            size = heapCnt-1;
        }

        for(int i = 0; i < size; i++){
            result[i] = delete().id;
        }
        return size;
    }

    public void heapify(People addPeople){
        int p = heapCnt;
        while(p > 1){
            if(heap.get(p/2).income > heap.get(p).income){
                People temp = heap.get(p/2);
                heap.set(p/2, addPeople);
                heap.set(p, temp);
            }else if(heap.get(p/2).income == heap.get(p).income){
                if(heap.get(p/2).id < heap.get(p).id){
                    People temp = heap.get(p/2);
                    heap.set(p/2, addPeople);
                    heap.set(p, temp);
                }
            }
            p /= 2;
        }

    }

    public People delete(){
        if(heapCnt == 1){
            return null;
        }

        People deletePeople = heap.get(1);
        heap.set(1, heap.get(heapCnt-1));
        heap.remove(heapCnt-1);
        heapCnt--;

        int pos = 1;
        while((pos*2) < heapCnt){
            int min = heap.get(pos*2).idx;
            int minPos = pos*2;

            if(((pos*2+1)<heapCnt) && min > heap.get(pos*2+1).income) {
                min = heap.get(pos*2 +1).income;
                minPos = pos*2 +1;
            }

            //부모가 더 작으면 그만
            if(min > heap.get(pos).income)
                break;

            People tmp = heap.get(pos);
            heap.set(pos,heap.get(minPos));
            heap.set(minPos, tmp);
            pos = minPos;
        }

        return deletePeople;
    }
}