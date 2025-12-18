package controller;

import dao.TransactionDAO;
import model.Transaction;

public class TransactionController {
	
	private TransactionDAO transDAO;

    public TransactionController() {
        transDAO = new TransactionDAO();
    }

    // CUSTOMER create transaction
    public String createTransaction(int serviceId, int customerId, String weightStr, String notes) {

        int weight;

        try {
            weight = Integer.parseInt(weightStr);
        } catch (Exception e) {
            return "Weight must be a number";
        }

        if(weight < 2 || weight > 50)
            return "Weight must be between 2 and 50 kg";

        if(notes.length() > 250)
            return "Notes too long";

        Transaction t = new Transaction(serviceId, customerId, weight, notes);

        boolean success = transDAO.insertTransaction(t);
        return success ? "SUCCESS" : "Failed to create transaction";
    }

    // RECEPTIONIST assign Laundry Staff
    public boolean assignStaff(int transId, int receptionistId, int staffId) {
        return transDAO.assignTransaction(transId, receptionistId, staffId);
    }

    // LAUNDRY STAFF finish order
    public boolean finishTransaction(int transId) {
        return transDAO.markAsFinished(transId);
    }

}
