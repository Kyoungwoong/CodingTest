package Daou.Assignment.MockExam4.Q1;

import java.util.*;

public class Concert {

    private final String concertId;
    private final Map<String, SeatInfo> seatMap = new HashMap<>();
    // 필요한 필드 추가

    public Concert(String concertId, List<String> seatIds) {
        // 1. concertId null 및 공백 검증
        if (concertId == null || concertId.isBlank()) {
            throw new IllegalArgumentException("concertId must not be null or blank");
        }

        // 2. seatIds null 및 빈 리스트 검증
        if (seatIds == null || seatIds.isEmpty()) {
            throw new IllegalArgumentException("seatIds must not be null or empty");
        }

        // 3. 좌석 ID 검증 및 Map 저장
        seatIds.forEach(s -> {
            // 좌석 ID null 및 공백 검증
            if (s == null || s.isBlank()) {
                throw new IllegalArgumentException("seat id must not be null or blank");
            }
            // 중복 좌석 ID 검증
            if (contains(s)) {
                throw new IllegalArgumentException("Concert cannot be duplicated seatId");
            }
            
            // 최초 AVAILABLE 상태로 새 객체 생성 후 저장 (외부 변경 차단)
            seatMap.put(s, new SeatInfo(s, SeatStatus.AVAILABLE, null));    
        });

        this.concertId = concertId;
    }

    /**
     * 콘서트 좌석 예약
     * 예약 성공시 좌석의 상태는 RESERVED, 예약자는 userId
     * @param seatId
     * @param userId
     * @return 예약 성공 여부를 반환
     */
    public boolean reserve(String seatId, String userId) {
        validateConcert(seatId, userId);

        SeatInfo findSeat = getSeat(seatId);
        // 이미 예약 된 경우
        if (findSeat.status() == SeatStatus.RESERVED) {
            return false;
        }

        seatMap.put(seatId, new SeatInfo(seatId, SeatStatus.RESERVED, userId));
        return true;
    }

    /**
     * 예약 취소
     * 예약 취소시 AVAILABLE, 예약자는 null
     * @param seatId
     * @param userId
     * @throws IllegalArgumentExcpeion 존재하지 않는 좌석이나 userId가 null 또는 공백
     * @return 예약 취소 여부를 반환
     */
    public boolean cancel(String seatId, String userId) {
        validateConcert(seatId, userId);

        SeatInfo findSeat = getSeat(seatId);
        // 이미 예약 된 경우나 다른 사용자가 예약한 경우
        if (findSeat.status() == SeatStatus.AVAILABLE || !findSeat.reservedBy().equals(userId)) {
            return false;
        }

        seatMap.put(seatId, new SeatInfo(seatId, SeatStatus.AVAILABLE, null));
        return true;
    }

    public SeatInfo getSeat(String seatId) {
        if (!contains(seatId)) {
            throw new IllegalArgumentException("seatId not exists");
        }

        return seatMap.get(seatId);
    }

    public List<SeatInfo> getAvailableSeats() {
        return seatMap.values().stream()
                    .filter(info -> info.status() == SeatStatus.AVAILABLE)
                    .sorted(Comparator.comparing(SeatInfo::seatId))
                    .toList();
    }

    public int getReservedCount() {
        return (int) seatMap.values().stream()
                            .filter(info -> info.status() == SeatStatus.RESERVED)
                            .count();
    }

    private void validateConcert(String seatId, String userId) {
        if (!contains(seatId)) {
            throw new IllegalArgumentException("seatId not exists");
        }

        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId must not be null or empty");
        }
    }

    private boolean contains(String seatId) {
        return seatMap.containsKey(seatId);
    }
}