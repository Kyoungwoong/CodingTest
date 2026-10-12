package Daou.Assignment.MockExam2.Q10;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository {

    private final Map<String, Account> storeMap = new HashMap<>();

    /**
     * 이미 등록된 계좌일 경우 다시 저장하면 기존 계좌를 덮어 쓴다.
     */
    @Override
    public void save(Account account) {
        if (account == null) {
            throw new IllegalArgumentException(
                    "account must not be null"
            );
        }

        storeMap.put(account.getAccountId(), account);
    }

    @Override
    public Optional<Account> findById(String accountId) {
        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException(
                    "accountId must not be null"
            );
        }
        Account account = storeMap.get(accountId);
        if (account == null) {
            return Optional.empty();
        }

        return Optional.of(account);
    }
}
