package Daou.Assignment.MockExam3.Q3;

public record RequestLog(
        String userId,
        String path,
        int statusCode
) {}