package src.banking;

import java.time.LocalDate;

public class Customer {
  /**
   * Customer name. Declared as `final` which ensures that name cannot be updated
   * for the given customer after creation.
   */
  private final String name;
  
  /**
   * Customer date of birth. Declared as `final` which ensures that date of birth
   * cannot be updated for the given customer after creation. 
   */
  private final LocalDate dateOfBirth;

  /**
   * Customer mobile number.
   */
  private String mobileNumber;

  /**
   * 
   * @param name
   * @param dateOfBirth
   * @param mobileNumber
   */
  public Customer(String name, LocalDate dateOfBirth, String mobileNumber) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.mobileNumber = mobileNumber;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }
  public String getName() {
    return name;
  }
  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  @Override
  public String toString() {
    return "Customer{name='" + name + "', dateOfBirth=" + dateOfBirth + ", mobileNumber='" + mobileNumber + "'}";
  }
}
