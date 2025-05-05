package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Session;
import database.Database;
import model.Speaker;

public class SessionDAO {
    public void saveSession(Session session) throws SQLException {
        String insertQuery = "INSERT INTO sessions (session_id, topic, start_time, end_time, speaker_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            
            stmt.setString(1, session.getSessionId());           // Set session_id
            stmt.setString(2, session.getTopic());               // Set topic
            stmt.setObject(3, session.getStartTime());           // Set start_time
            stmt.setObject(4, session.getEndTime());             // Set end_time
            stmt.setString(5, session.getSpeaker().getSpeakerId()); // Set speaker_id

            stmt.executeUpdate();
        }
    }
}
