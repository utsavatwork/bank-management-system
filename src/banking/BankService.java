package src.banking;

import src.utils.DatabaseConfig;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class BankService {
    // Open a new account
    public void openAccount(Customer customer, AccountType accountType, double initialDeposit) {
      String sql = "INSERT INTO accounts (customer_name, date_of_birth, mobile_number, balance, account_type) VALUES (?, ?, ?, ?, ?)";

      try (Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, customer.getName());
        LocalDate dateOfBirth = customer.getDateOfBirth();
        stmt.setLong(2, dateOfBirth.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
        stmt.setString(3, customer.getMobileNumber());
        stmt.setDouble(4, initialDeposit);
        stmt.setString(5, accountType.name());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
          try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
              long accountNumber = rs.getLong(1);
              System.out.println("Account created successfully with Account Number: " + accountNumber);
            }
          }
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    // Retrieve an account
    public Account getAccount(long accountNumber) {
      String sql = "SELECT * FROM accounts WHERE account_number = ?";
      try (Connection conn = DatabaseConfig.getConnection();
          PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setLong(1, accountNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
          long dateOfBirth = rs.getLong("date_of_birth");
          Customer customer = new Customer(
            rs.getString("customer_name"),
            Instant.ofEpochMilli(dateOfBirth).atZone(ZoneOffset.UTC).toLocalDate(),
            rs.getString("mobile_number")
          );
          return new Account(customer, AccountType.valueOf(rs.getString("account_type")), rs.getDouble("balance"));
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return null;
    }

    // List all accounts
    public void listAccounts() {
      String sql = "SELECT * FROM accounts";
      try (Connection conn = DatabaseConfig.getConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
          System.out.println("Account Number: " + rs.getLong("account_number") +
              ", Name: " + rs.getString("customer_name") +
              ", Balance: " + rs.getDouble("balance") +
              ", Type: " + rs.getString("account_type"));
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
}
