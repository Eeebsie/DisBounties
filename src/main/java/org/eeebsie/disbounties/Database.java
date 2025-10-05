package org.eeebsie.disbounties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
       private static Connection connection;

       // Initialize manually (instead of static block)
       public static void init() {
              try {
                     System.out.println("🟢 Loading MySQL driver...");
                     Class.forName("com.mysql.cj.jdbc.Driver");
                     System.out.println("✅ MySQL driver loaded successfully!");

                     String url = "jdbc:mysql://localhost:3306/mcquests";
                     String user = "root";
                     String password = "---";

                     connection = DriverManager.getConnection(url, user, password);
                     System.out.println("✅ Database connection established!");

                     // Create table if it doesn’t exist
                     String createTableSQL = """
                CREATE TABLE IF NOT EXISTS player_quests (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    uuid VARCHAR(36) NOT NULL,
                    quest_id VARCHAR(64) NOT NULL,
                    progress INT DEFAULT 0,
                    completed BOOLEAN DEFAULT FALSE,
                    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                );
            """;

                     try (Statement stmt = connection.createStatement()) {
                            stmt.executeUpdate(createTableSQL);
                            System.out.println("✅ Table 'player_quests' ready!");
                     }

              } catch (ClassNotFoundException e) {
                     System.err.println("❌ MySQL driver not found! Make sure mysql-connector-j is shaded or in the mod’s classpath!");
              } catch (SQLException e) {
                     System.err.println("❌ Database connection failed: " + e.getMessage());
              }
       }

       public static Connection getConnection() {
              if (connection == null) {
                     System.err.println("⚠️ Database connection not initialized! Call Database.init() first!");
              }
              return connection;
       }
}
