package Daou.Assignment.MockExam3.Q3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * API 정보 분석기
 * LogAnalyzer
 */
public class LogAnalyzer {

    /**
     *  API 경로별 요청 집계 횟수
     */
    public Map<String, Integer> countByPath(List<RequestLog> logs) {
        validateLogs(logs);
        Map<String, Integer> result = new HashMap<>();
        for (RequestLog log : logs) {
            result.put(log.path(), result.getOrDefault(log.path(), 0) + 1);
            // 이것도 가능
            // result.merge(log.path(), 1, Integer::sum);
        }
        return result;
    }

    /**
     * 사용자별 요청 집계 횟수
     */
    public Map<String, Integer> countByUser(List<RequestLog> logs) {
        validateLogs(logs);
        Map<String, Integer> result = new HashMap<>();
        for (RequestLog log : logs) {
            result.put(log.userId(), result.getOrDefault(log.userId(), 0) + 1);
            // 이것도 가능
            // result.merge(log.path(), 1, Integer::sum);
        }
        return result;
    }

    /**
     * API 경로별로 요청한 사용자 ID
     */
    public Map<String, Set<String>> usersByPath(List<RequestLog> logs) {
        validateLogs(logs);
        Map<String, Set<String>> result = new HashMap<>();
        for (RequestLog log : logs) {
            result.computeIfAbsent(log.path(), k -> new HashSet<>()).add(log.userId());
        }
        return result;
    }

    /**
     * API 경로별로 HTTP 상태 코드 발생 횟수
     */
    public Map<String, Map<Integer, Integer>> countStatusByPath(List<RequestLog> logs) {
        validateLogs(logs);

        Map<String, Map<Integer, Integer>> result = new HashMap<>();

        for (RequestLog log : logs) {
            Map<Integer, Integer> statusMap =
                    result.computeIfAbsent(
                        log.path(),
                        k -> new HashMap<>()
                    );

            statusMap.merge(log.statusCode(), 1, Integer::sum);
        }

        return result;
    }

    /**
     * 요청 횟수가 가장 많은 사용자의 ID
     */
    public String findMostActiveUser(List<RequestLog> logs) {
        validateLogs(logs);
        Map<String, Integer> countingMap = countByUser(logs);

        int max = Integer.MIN_VALUE;
        String result = null;
        for (String userId: countingMap.keySet()) {
            if (max < countingMap.get(userId)) {
                max = countingMap.get(userId);
                result = userId;
            } else if (max == countingMap.get(userId)) {
                if (result.compareTo(userId) >= 0) {
                    result = userId;
                }
            }
        }
        return result;
    }

    private void validateLogs(List<RequestLog> logs) {
        if (logs == null) {
            throw new IllegalArgumentException(
                "logs must not be null"
            );
        }

        for (RequestLog log : logs) {
            if (log == null) {
                throw new IllegalArgumentException(
                    "log must not be null"
                );
            }

            if (log.userId() == null || log.userId().isBlank()) {
                throw new IllegalArgumentException(
                    "userId must not be null or blank"
                );
            }

            if (log.path() == null || log.path().isBlank()) {
                throw new IllegalArgumentException(
                    "path must not be null or blank"
                );
            }

            if (log.statusCode() < 100 || log.statusCode() > 599) {
                throw new IllegalArgumentException(
                    "status invalied"
                );
            }
        }
    }
}
