package Daou.Assignment.Q4;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class ProjectAccessManager {

    private final Map<String, Set<String>> accessMap = new HashMap<>();

    public void grantAccess(String projectId, String userId) {
        validateAccess(projectId, userId);
        accessMap.computeIfAbsent(projectId, k -> new HashSet<>()).add(userId);
    }

    public boolean revokeAccess(String projectId, String userId) {
        validateAccess(projectId, userId);
        if (!accessMap.containsKey(projectId)) {
            throw new IllegalStateException(
                "these project not exits: " + projectId
            );
        }
        return accessMap.get(projectId).remove(userId);
    }

    public Set<String> getUsers(String projectId) {
        validateProjectId(projectId);
        return accessMap.computeIfAbsent(projectId, k -> new HashSet<>());
    }

    public Set<String> getCommonUsers(String projectA, String projectB) {
        validateProjectId(projectA);
        validateProjectId(projectB);
        Set<String> accessProjectA = accessMap.get(projectA);
        Set<String> accessProjectB = accessMap.get(projectB);

        Set<String> result = new HashSet<>();
        for (String userId: accessProjectA) {
            if (accessProjectB.contains(userId)) {
                result.add(userId);
            }
        }
        return result;
    }

    public Set<String> getExclusiveUsers(String projectA, String projectB) {
        validateProjectId(projectA);
        validateProjectId(projectB);
        Set<String> accessProjectA = accessMap.get(projectA);
        Set<String> accessProjectB = accessMap.get(projectB);

        Set<String> result = new HashSet<>();
        for (String userId: accessProjectA) {
            if (!accessProjectB.contains(userId)) {
                result.add(userId);
            }
        }
        return result;
    }

    public Set<String> getAllUsers(String projectA, String projectB) {
        validateProjectId(projectA);
        validateProjectId(projectB);
        Set<String> accessProjectA = accessMap.get(projectA);
        Set<String> accessProjectB = accessMap.get(projectB);

        Set<String> result = new HashSet<>(accessProjectA);
        for (String userId: accessProjectB) {
            result.add(userId);
        }
        return result;
    }

    public boolean hasAccess(String projectId, String userId) {
        validateAccess(projectId, userId);
        return accessMap.get(projectId).contains(userId);
    }

    /**
     * 파라미터 검증
     * @param projectId
     * @param userId
     */
    private void validateAccess(String projectId, String userId) {
        validateProjectId(projectId);

        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException(
                "user id must not be null or blank"
            );
        }
    }

    private void validateProjectId(String projectId) {
        if (projectId == null || projectId.isBlank()) {
            throw new IllegalArgumentException(
                "project id must not be null or blank"
            );
        }
    }
}