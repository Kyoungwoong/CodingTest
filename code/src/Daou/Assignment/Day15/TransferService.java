package Daou.Assignment.Day15;

import java.util.*;
import java.util.concurrent.*;

public class TransferService {

    private AccountRepository repository;

    private static final List<String> logs = new ArrayList<>();

    public TransferService(AccountRepository repository) {
        this.repository = repository;
    }

    public void transfer(
            Long fromId,
            Long toId,
            int amount
    ) {
        Account from = repository.findById(fromId);
        Account to = repository.findById(toId);

        if (amount < 0) {
            return;
        }

        if (from.getBalance() >= amount) {
            from.setBalance(
                    from.getBalance() - amount
            );

            repository.save(from);

            to.setBalance(
                    to.getBalance() + amount
            );

            repository.save(to);

            logs.add(
                    fromId + " -> " + toId + ": " + amount
            );
        }
    }

    public List<String> getLogs() {
        return logs;
    }

    public void transferAll(
            List<TransferRequest> requests
    ) {
        requests.parallelStream()
                .forEach(request ->
                        transfer(
                                request.getFromId(),
                                request.getToId(),
                                request.getAmount()
                        )
                );
    }
}
