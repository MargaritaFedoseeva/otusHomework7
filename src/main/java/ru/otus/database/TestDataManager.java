package ru.otus.database;

import com.google.inject.Singleton;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Singleton
public class TestDataManager {
  private final String url = System.getProperty("databaseUrl");
  private final String username = System.getProperty("databaseUsername");
  private final String password = System.getProperty("databasePassword");

  @SneakyThrows
  public void prepareWishlistDescription(String login, String description) {
    String sql = "UPDATE public.wishlists\n" +
        "SET description = ?\n" +
        "WHERE user_id IN (SELECT id FROM users WHERE username = ?) \n";
    try (Connection conn = DriverManager.getConnection(url, username, password);
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, description);
      ps.setString(2, login);
      ps.executeUpdate();
    }
  }
}
