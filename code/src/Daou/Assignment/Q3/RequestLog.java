package Daou.Assignment.Q3;

public record RequestLog(
        String userId,
        String path,
        int statusCode
) {}