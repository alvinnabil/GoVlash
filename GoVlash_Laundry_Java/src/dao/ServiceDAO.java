package dao;

import java.sql.*;
import java.util.ArrayList;
import database.DBConnection;
import model.Service;

public class ServiceDAO {
	
	private Connection conn;

    public ServiceDAO() {
        conn = DBConnection.getConnection();
    }

    public boolean insertService(Service s) {
        String query = "INSERT INTO services (name, description, price, duration_days) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, s.getName());
            ps.setString(2, s.getDescription());
            ps.setInt(3, s.getPrice());
            ps.setInt(4, s.getDurationDays());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateService(Service s) {
        String query = "UPDATE services SET name=?, description=?, price=?, duration_days=? WHERE service_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, s.getName());
            ps.setString(2, s.getDescription());
            ps.setInt(3, s.getPrice());
            ps.setInt(4, s.getDurationDays());
            ps.setInt(5, s.getServiceId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteService(int id) {
        String query = "DELETE FROM services WHERE service_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Service> getAllServices() {
        ArrayList<Service> list = new ArrayList<>();
        String query = "SELECT * FROM services ORDER BY service_id DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Service(
                    rs.getInt("service_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getInt("duration_days")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
