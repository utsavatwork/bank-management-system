package src.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
  private static final String URL = "jdbc:mysql://localhost:3306/bms_db";
  private static final String USER = "bank_manager";
  private static final String PASSWORD = "MeAtWork@9491";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
