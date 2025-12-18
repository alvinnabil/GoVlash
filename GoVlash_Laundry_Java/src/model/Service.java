package model;

public class Service {
	
	private int service_id;
    private String name;
    private String description;
    private int price;
    private int duration_days;

    public Service(int service_id, String name, String description, int price, int duration_days) {
        this.service_id = service_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration_days = duration_days;
    }

    public Service(String name, String description, int price, int duration_days) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration_days = duration_days;
    }

    // Getter & Setter

    public int getServiceId() {
        return service_id;
    }

    public void setServiceId(int service_id) {
        this.service_id = service_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDurationDays() {
        return duration_days;
    }

    public void setDurationDays(int duration_days) {
        this.duration_days = duration_days;
    }

}
