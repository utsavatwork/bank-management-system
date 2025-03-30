package src.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
  private static Properties properties = new Properties();

  static {
    try (FileInputStream fis = new FileInputStream("config.properties")) {
      properties.load(fis);
    } catch (IOException e) {
      System.err.println("Error loading config file: " + e.getMessage());
    }
  }

  public static String getProperty(String key) {
    return properties.getProperty(key);
  }
}
