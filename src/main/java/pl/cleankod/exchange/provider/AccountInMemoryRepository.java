package pl.cleankod.exchange.provider;

import pl.cleankod.exchange.core.domain.Account;
import pl.cleankod.exchange.core.domain.Money;
import pl.cleankod.exchange.core.gateway.AccountRepository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AccountInMemoryRepository implements AccountRepository {

    private final Set<Account> accounts = Set.of(
            new Account(
                    Account.Id.of("fa07c538-8ce4-11ec-9ad5-4f5a625cd744"),
                    Account.Number.of("65 1090 1665 0000 0001 0373 7343"),
                    Money.of("123.45", "PLN")
            ),
            new Account(
                    Account.Id.of("78743420-8ce9-11ec-b0d0-57b77255c208"),
                    Account.Number.of("75 1240 2034 1111 0000 0306 8582"),
                    Money.of("456.78", "EUR")
            )
    );

    private final Map<Account.Number, Account> accountsByAccountNumber = accounts.stream()
            .collect(Collectors.toMap(Account::number, Function.identity()));

    private final Map<Account.Id, Account> accountsById = accounts.stream()
            .collect(Collectors.toMap(Account::id, Function.identity()));

    @Override
    public Optional<Account> find(Account.Id id) {
        return Optional.ofNullable(accountsById.get(id));
    }

    @Override
    public Optional<Account> find(Account.Number number) {
        return Optional.ofNullable(accountsByAccountNumber.get(number));
    }
}
