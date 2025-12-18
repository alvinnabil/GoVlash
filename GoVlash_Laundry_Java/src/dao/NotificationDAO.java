package dao;

import java.sql.*;
import java.util.ArrayList;
import database.DBConnection;
import model.Notification;

public class NotificationDAO {
	
	private Connection conn;

    public NotificationDAO() {
        conn = DBConnection.getConnection();
    }

    public boolean insertNotification(Notification n) {
        String query = "INSERT INTO notifications (recipient_id, message, is_read) VALUES (?, ?, false)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, n.getRecipientId());
            ps.setString(2, n.getMessage());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Notification> getNotificationsByUser(int userId) {
        ArrayList<Notification> list = new ArrayList<>();
        String query = "SELECT * FROM notifications WHERE recipient_id=? ORDER BY notif_id DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Notification(
                    rs.getInt("notif_id"),
                    rs.getInt("recipient_id"),
                    rs.getString("message"),
                    rs.getTimestamp("created_at"),
                    rs.getBoolean("is_read")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean markAsRead(int notifId) {
        String query = "UPDATE notifications SET is_read=true WHERE notif_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, notifId);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteNotification(int notifId) {
        String query = "DELETE FROM notifications WHERE notif_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, notifId);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
