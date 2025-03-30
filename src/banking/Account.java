package src.banking;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Represents a bank account associated with a customer.
 */
public class Account {
  private static final AtomicLong ACCOUNT_SEQ = new AtomicLong(100000); // Auto-incrementing account number

  private final String accountNumber;
  private final Customer customer;
  private double balance;
  private final AccountType accountType;

  /**
   * Creates a new account for a given customer.
   *
   * @param customer    The account holder
   * @param accountType Type of account (SAVINGS, CURRENT)
   * @param initialDeposit Initial deposit amount (can be 0)
   */
  public Account(Customer customer, AccountType accountType, double initialDeposit) {
    if (initialDeposit < 0) {
        throw new IllegalArgumentException("Initial deposit cannot be negative.");
    }
    this.accountNumber = "ACC" + ACCOUNT_SEQ.getAndIncrement();
    this.customer = customer;
    this.accountType = accountType;
    this.balance = initialDeposit;
  }

  // Getters
  public String getAccountNumber() { return accountNumber; }
  public Customer getCustomer() { return customer; }
  public double getBalance() { return balance; }
  public AccountType getAccountType() { return accountType; }

  /**
   * Deposits an amount into the account.
   * 
   * @param amount Amount to deposit
   */
  public void deposit(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Deposit amount must be greater than zero.");
    }
    balance += amount;
    System.out.println("Deposit successful. New balance: $" + balance);
  }

  /**
   * Withdraws an amount from the account if sufficient balance is available.
   *
   * @param amount Amount to withdraw
   */
  public void withdraw(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
    }
    if (amount > balance) {
      throw new IllegalArgumentException("Insufficient funds.");
    }
    balance -= amount;
    System.out.println("Withdrawal successful. Remaining balance: $" + balance);
  }

  @Override
  public String toString() {
    return "Account{" +
        "accountNumber='" + accountNumber + '\'' +
        ", customer=" + customer.getName() +
        ", balance=" + balance +
        ", accountType=" + accountType +
        '}';
  }
}
