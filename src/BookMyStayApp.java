
import java.util.*;

// Add-On Service class
class AddOnService {
    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void displayService() {
        System.out.println(serviceName + " - ₹" + price);
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map reservation ID -> list of services
    private HashMap<String, List<AddOnService>> reservationServices = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);

        System.out.println(service.getServiceName() + " added to reservation " + reservationId);
    }

    // Display services for reservation
    public void displayServices(String reservationId) {

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("\nSelected Add-On Services:");
        double total = 0;

        for (AddOnService s : services) {
            s.displayService();
            total += s.getPrice();
        }

        System.out.println("Total Add-On Cost: ₹" + total);
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("     Add-On Service Selection");
        System.out.println("=================================");

        // Example reservation ID (already confirmed booking)
        String reservationId = "ST101";

        AddOnServiceManager manager = new AddOnServiceManager();

        // Guest selects services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 800);
        AddOnService spa = new AddOnService("Spa Access", 1200);

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);
        manager.addService(reservationId, spa);

        // Display selected services and cost
        manager.displayServices(reservationId);

        System.out.println("\nCore booking and inventory remain unchanged.");
    }
}
