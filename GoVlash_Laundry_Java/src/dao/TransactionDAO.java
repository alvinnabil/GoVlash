package dao;

import java.sql.*;
import java.util.ArrayList;
import database.DBConnection;
import model.Transaction;

public class TransactionDAO {
	
	private Connection conn;

    public TransactionDAO() {
        conn = DBConnection.getConnection();
    }

    public boolean insertTransaction(Transaction t) {
        String query = "INSERT INTO transactions (service_id, customer_id, total_weight, notes, status) VALUES (?, ?, ?, ?, 'Pending')";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t.getServiceId());
            ps.setInt(2, t.getCustomerId());
            ps.setInt(3, t.getTotalWeight());
            ps.setString(4, t.getNotes());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Transaction> getTransactionsByCustomer(int customerId) {
        ArrayList<Transaction> list = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE customer_id=? ORDER BY transaction_id DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Transaction> getPendingTransactions() {
        ArrayList<Transaction> list = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE status='Pending' ORDER BY transaction_id DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Transaction> getAssignedToStaff(int staffId) {
        ArrayList<Transaction> list = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE laundry_staff_id=? ORDER BY transaction_id DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, staffId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean assignTransaction(int transId, int receptionistId, int staffId) {
        String query = "UPDATE transactions SET receptionist_id=?, laundry_staff_id=? WHERE transaction_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, receptionistId);
            ps.setInt(2, staffId);
            ps.setInt(3, transId);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean markAsFinished(int transId) {
        String query = "UPDATE transactions SET status='Finished' WHERE transaction_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, transId);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper: mapping ResultSet → Transaction Object
    private Transaction mapResultSet(ResultSet rs) throws SQLException {
        return new Transaction(
            rs.getInt("transaction_id"),
            rs.getInt("service_id"),
            rs.getInt("customer_id"),
            (Integer) rs.getObject("receptionist_id"),
            (Integer) rs.getObject("laundry_staff_id"),
            rs.getTimestamp("transaction_date"),
            rs.getString("status"),
            rs.getInt("total_weight"),
            rs.getString("notes")
        );
    }
	
	

}
