package controller;

import dao.ServiceDAO;
import model.Service;

public class ServiceController {
	
	private ServiceDAO serviceDAO;

    public ServiceController() {
        serviceDAO = new ServiceDAO();
    }

    public String addService(String name, String desc, String priceStr, String durationStr) {

        if(name.isEmpty()) return "Service name cannot be empty";
        if(desc.isEmpty()) return "Description cannot be empty";
        if(desc.length() > 250) return "Description too long";

        int price;
        int duration;

        try {
            price = Integer.parseInt(priceStr);
            duration = Integer.parseInt(durationStr);
        } catch (Exception e) {
            return "Price and Duration must be numbers";
        }

        if(price <= 0) return "Price must be > 0";
        if(duration < 1 || duration > 30) return "Duration must be 1–30 days";

        Service s = new Service(name, desc, price, duration);

        boolean success = serviceDAO.insertService(s);
        return success ? "SUCCESS" : "Failed to add service";
    }

    public boolean deleteService(int id) {
        return serviceDAO.deleteService(id);
    }

}
