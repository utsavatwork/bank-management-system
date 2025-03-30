package src.banking;

import src.utils.ConfigLoader;
import src.utils.Logger;

/**
 * Singleton class "Bank". Ensuring only one bank entity is present throughout the application.
 */
public class Bank {
  private static final Logger log = new Logger(Bank.class);
    
  private static volatile Bank bankInstance; // Volatile for thread safety

  private final String name;
  private final String ifscCode;

  // Getter methods
  public String getName() {
    return name;
  }

  public String getIfscCode() {
      return ifscCode;
  }

  private Bank(String name, String ifscCode) {
    this.name = name;
    this.ifscCode = ifscCode;
    log.info("Bank instance created: " + name + " (" + ifscCode + ")");
  }

  public static Bank getInstance() {
    if (bankInstance == null) {
      synchronized (Bank.class) {
        if (bankInstance == null) {
          // Load values from ConfigLoader
          String bankName = ConfigLoader.getProperty("bank.name");
          String ifsc = ConfigLoader.getProperty("bank.ifsc");
          bankInstance = new Bank(bankName, ifsc);
        }
      }
    }
    return bankInstance;
  }

  @Override
  public String toString() {
    return "Bank{name:'" + this.getName() + "', ifscCode:'" + this.getIfscCode() + "'}";
  }
}
