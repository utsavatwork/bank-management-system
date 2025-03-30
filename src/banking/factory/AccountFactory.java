package src.banking.factory;

import src.banking.Account;
import src.banking.AccountType;
import src.banking.Customer;

public class AccountFactory {
    public static Account createAccount(Customer owner, AccountType type, double initialDeposit) {
        return new Account(owner, type, initialDeposit);
    }
}
