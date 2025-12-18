package model;

import java.sql.Timestamp;

public class Notification {

	private int notif_id;
    private int recipient_id;
    private String message;
    private Timestamp created_at;
    private boolean is_read;

    public Notification(int notif_id, int recipient_id, String message, Timestamp created_at, boolean is_read) {
        this.notif_id = notif_id;
        this.recipient_id = recipient_id;
        this.message = message;
        this.created_at = created_at;
        this.is_read = is_read;
    }

    public Notification(int recipient_id, String message) {
        this.recipient_id = recipient_id;
        this.message = message;
        this.is_read = false;
    }

    // Getter & Setter

    public int getNotifId() {
        return notif_id;
    }

    public void setNotifId(int notif_id) {
        this.notif_id = notif_id;
    }

    public int getRecipientId() {
        return recipient_id;
    }

    public void setRecipientId(int recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.created_at = created_at;
    }

    public boolean isRead() {
        return is_read;
    }

    public void setRead(boolean is_read) {
        this.is_read = is_read;
    }

}
