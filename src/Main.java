package src;
import src.banking.Bank;
import src.utils.Logger;

public class Main {
  public static final Logger log = new Logger(Main.class);
  
  public static void main(String[] args) {
    log.info("Successfully started the server.");

    Bank bank = Bank.getInstance();
    
    
  }
}