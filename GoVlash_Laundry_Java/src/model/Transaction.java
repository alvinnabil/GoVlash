package model;

import java.sql.Timestamp;

public class Transaction {
	
	private int transaction_id;
    private int service_id;
    private int customer_id;
    private Integer receptionist_id;
    private Integer laundry_staff_id;
    private Timestamp transaction_date;
    private String status;
    private int total_weight;
    private String notes;

    public Transaction(int transaction_id, int service_id, int customer_id, Integer receptionist_id, Integer laundry_staff_id,
                       Timestamp transaction_date, String status, int total_weight, String notes) {
        this.transaction_id = transaction_id;
        this.service_id = service_id;
        this.customer_id = customer_id;
        this.receptionist_id = receptionist_id;
        this.laundry_staff_id = laundry_staff_id;
        this.transaction_date = transaction_date;
        this.status = status;
        this.total_weight = total_weight;
        this.notes = notes;
    }

    public Transaction(int service_id, int customer_id, int total_weight, String notes) {
        this.service_id = service_id;
        this.customer_id = customer_id;
        this.total_weight = total_weight;
        this.notes = notes;
        this.status = "Pending";
    }

    // Getter & Setter

    public int getTransactionId() {
        return transaction_id;
    }

    public void setTransactionId(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getServiceId() {
        return service_id;
    }

    public void setServiceId(int service_id) {
        this.service_id = service_id;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getReceptionistId() {
        return receptionist_id;
    }

    public void setReceptionistId(Integer receptionist_id) {
        this.receptionist_id = receptionist_id;
    }

    public Integer getLaundryStaffId() {
        return laundry_staff_id;
    }

    public void setLaundryStaffId(Integer laundry_staff_id) {
        this.laundry_staff_id = laundry_staff_id;
    }

    public Timestamp getTransactionDate() {
        return transaction_date;
    }

    public void setTransactionDate(Timestamp transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalWeight() {
        return total_weight;
    }

    public void setTotalWeight(int total_weight) {
        this.total_weight = total_weight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
