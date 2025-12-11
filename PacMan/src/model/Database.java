package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String DB_PATH = "db/app.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;

    public static Connection getConnection() throws SQLException {
        // Создаём папку db, если её нет
        File dbFolder = new File("db");
        if (!dbFolder.exists()) {
            dbFolder.mkdirs();
        }

        return DriverManager.getConnection(DB_URL);
    }

    public static void initDatabase() {
        String createLeadersTable = """
            CREATE TABLE IF NOT EXISTS leaders (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE NOT NULL,
                score INTEGER NOT NULL
            );
        """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createLeadersTable);
            System.out.println("База данных успешно инициализирована.");

        } catch (SQLException e) {
            System.out.println("Ошибка при инициализации базы:");
            e.printStackTrace();
        }
    }
}
