package Daou.Assignment.Q2;

public class Main {

    public static void main(String[] args) {

        WaitingListManager manager = new WaitingListManager();

        manager.add(new Reservation("R101", "Kim", 2, 1000));
        manager.add(new Reservation("R102", "Lee", 1, 1200));
        manager.add(new Reservation("R103", "Kim", 1, 900));
        manager.add(new Reservation("R104", "Park", 2, 800));

        System.out.println(manager.getSortedWaitingList());

        System.out.println(manager.findByCustomer("Kim"));

        System.out.println(manager.pollNext(2));

        System.out.println(manager.size());
    }
}
