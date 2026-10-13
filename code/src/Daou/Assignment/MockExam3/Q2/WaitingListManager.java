package Daou.Assignment.MockExam3.Q2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 대기열 관리 클래스
 * WaitingListManager
 */
public class WaitingListManager {

    private final List<Reservation> waitingList = new ArrayList<>();
    /**
     * 정렬기준 priority asc, requestedAt asc, reservationId asc
     */
    private static final Comparator<Reservation> RESERVATION_ORDER =
        Comparator.comparingInt(Reservation::priority)
            .thenComparingLong(Reservation::requestedAt)
            .thenComparing(Reservation::reservationId);

    /**
     * 이미 같은 reservationId면 IllegalArgumentException
     * @param reservation
     */
    public void add(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException(
                "reservation must not be null"
            );
        }

        String reservationId = reservation.reservationId();
        validateId(reservationId);

        String customerName = reservation.customerName();
        validateCustomerName(customerName);

        int priority = reservation.priority();
        if (priority < 1) {
            throw new IllegalArgumentException(
                "priority must be at least 1"
            );
        }

        long requestedAt = reservation.requestedAt();
        if (requestedAt < 0) {
            throw new IllegalArgumentException(
                "requested At must be zero or positive number"
            );
        }

        // long count = waitingList.stream()
        //                         .filter(r -> r.reservationId().equals(reservationId))
        //                         .count();
        // if (count > 0) {
        //     throw new IllegalArgumentException(
        //         "reservation cannot be duplicated"
        //     );
        // }

        // short cut으로 빠르게 종료.
        boolean exists = waitingList.stream()
            .anyMatch(r -> r.reservationId().equals(reservationId));

        if (exists) {
            throw new IllegalArgumentException(
                "Duplicate reservationId"
            );
        }

        waitingList.add(reservation);
    }

    /**
     * 일치하는 예약이 있으면 삭제 후 true 반환. 없으면 false 반환
     * @param reservationId
     * @return
     */
    public boolean cancel(String reservationId) {
        validateId(reservationId);

        return waitingList.removeIf(r -> r.reservationId().equals(reservationId));
    }

    /**
     * 정렬기준 priority asc, requestedAt asc, reservationId asc
     * @return
     */
    public List<Reservation> getSortedWaitingList() {
        return waitingList.stream()
                            .sorted(RESERVATION_ORDER)
                            .toList();
    }

    /**
     * @param customerName
     * @return
     */
    public List<Reservation> findByCustomer(String customerName) {
        validateCustomerName(customerName);
        return waitingList.stream()
                        .filter(r -> r.customerName().equals(customerName))
                        .sorted(RESERVATION_ORDER)
                        .toList();
    }

    /**
     * 우선순위가 높은 예약 count개 반환
     * @param count
     * @return
     */
    public List<Reservation> pollNext(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(
                "count must be positive number"
            );
        }

        List<Reservation> sorted = getSortedWaitingList();
        int actualCount = Math.min(count, sorted.size());
        List<Reservation> result = new ArrayList<>(
            sorted.subList(0, actualCount)
        );

        waitingList.removeAll(result);

        return result;
        
    }

    public int size() {
        return waitingList.size();
    }

    private void validateId(String reservationId) {
        if (reservationId == null || reservationId.isBlank()) {
            throw new IllegalArgumentException(
                "reservation Id must not be null or blank"
            );
        }
    }

    private void validateCustomerName(String customerName) {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException(
                "customerName must not be null or blank"
            );
        }
    }
}