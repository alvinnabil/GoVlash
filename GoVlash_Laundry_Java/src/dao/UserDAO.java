package dao;

import java.sql.*;
import java.util.ArrayList;
import database.DBConnection;
import model.User;

public class UserDAO {
	
	private Connection conn;

    public UserDAO() {
        conn = DBConnection.getConnection();
    }

    // Insert user baru
    public boolean insertUser(User user) {
        String query = "INSERT INTO users (name, email, password, gender, dob, role) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getGender());
            ps.setDate(5, user.getDob());
            ps.setString(6, user.getRole());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get user by email (untuk login)
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("gender"),
                    rs.getDate("dob"),
                    rs.getString("role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Ambil semua user
    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        String query = "SELECT * FROM users ORDER BY user_id DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("gender"),
                    rs.getDate("dob"),
                    rs.getString("role")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
