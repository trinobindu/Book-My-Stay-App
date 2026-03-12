
import java.util.HashMap;
import java.util.Map;

// Abstract Room class
abstract class Room {
    protected String roomType;
    protected double pricePerNight;

    public Room(String roomType, double pricePerNight) {
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public abstract void displayDetails();
}

// Standard Room
class StandardRoom extends Room {

    public StandardRoom() {
        super("Standard Room", 2000);
    }

    public void displayDetails() {
        System.out.println(roomType + " - Price per Night: ₹" + pricePerNight);
    }
}

// Deluxe Room
class DeluxeRoom extends Room {

    public DeluxeRoom() {
        super("Deluxe Room", 3500);
    }

    public void displayDetails() {
        System.out.println(roomType + " - Price per Night: ₹" + pricePerNight);
    }
}

// Suite Room
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 5000);
    }

    public void displayDetails() {
        System.out.println(roomType + " - Price per Night: ₹" + pricePerNight);
    }
}

// Centralized Room Inventory using HashMap
class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    // Register room types
    public void registerRoom(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Get available rooms
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (e.g., after booking)
    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, current + change);
    }

    // Display current inventory
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        System.out.println("-----------------------");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("   Centralized Room Inventory");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types
        inventory.registerRoom("Standard Room", 5);
        inventory.registerRoom("Deluxe Room", 3);
        inventory.registerRoom("Suite Room", 2);

        // Display inventory
        inventory.displayInventory();

        // Example update (simulate booking a Standard Room)
        System.out.println("\nBooking 1 Standard Room...");
        inventory.updateAvailability("Standard Room", -1);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nThank you for using Book My Stay!");
    }
}

