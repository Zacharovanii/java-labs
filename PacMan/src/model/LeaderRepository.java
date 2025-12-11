package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaderRepository {

    // Добавление или обновление результата игрока
    public static void addLeader(String username, int score) {
        String sql = """
            INSERT INTO leaders (username, score)
            VALUES (?, ?)
            ON CONFLICT(username) DO UPDATE SET
                score = CASE WHEN excluded.score > leaders.score THEN excluded.score ELSE leaders.score END
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();

            System.out.println("Результат игрока успешно добавлен или обновлён!");

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении/обновлении результата:");
        }
    }

    // Получение топ-5 лидеров
    public static List<Leader> getTopLeaders() {
        List<Leader> leaders = new ArrayList<>();
        String sql = "SELECT username, score FROM leaders ORDER BY score DESC LIMIT 5";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                leaders.add(new Leader(rs.getString("username"), rs.getInt("score")));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении лидеров:");
        }
        return leaders;
    }
}
