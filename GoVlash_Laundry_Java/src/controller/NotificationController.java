package controller;

import dao.NotificationDAO;
import model.Notification;

public class NotificationController {
	
	 private NotificationDAO notifDAO;

	    public NotificationController() {
	        notifDAO = new NotificationDAO();
	    }

	    // ADMIN send notification
	    public boolean sendNotification(int customerId) {

	        String msg = "Your order is finished and ready for pickup. Thank you for choosing our service!";

	        Notification n = new Notification(customerId, msg);
	        return notifDAO.insertNotification(n);
	    }

	    public boolean markRead(int notifId) {
	        return notifDAO.markAsRead(notifId);
	    }

	    public boolean deleteNotification(int notifId) {
	        return notifDAO.deleteNotification(notifId);
	    }

}
