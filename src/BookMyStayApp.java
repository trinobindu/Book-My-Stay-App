
import java.util.*;

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
        System.out.println("Room: " + roomType + " | Price: ₹" + pricePerNight);
    }
}

// Deluxe Room
class DeluxeRoom extends Room {

    public DeluxeRoom() {
        super("Deluxe Room", 3500);
    }

    public void displayDetails() {
        System.out.println("Room: " + roomType + " | Price: ₹" + pricePerNight);
    }
}

// Suite Room
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 5000);
    }

    public void displayDetails() {
        System.out.println("Room: " + roomType + " | Price: ₹" + pricePerNight);
    }
}

// Centralized Inventory using HashMap
class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }
}

// Search Service (Read-only access)
class SearchService {

    public void searchAvailableRooms(RoomInventory inventory, List<Room> rooms) {

        System.out.println("\nAvailable Rooms:");
        System.out.println("-----------------------");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            // Filter unavailable rooms
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available: " + available);
                System.out.println("-----------------------");
            }
        }
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("      Room Search Service");
        System.out.println("=================================");

        // Initialize rooms
        List<Room> rooms = new ArrayList<>();
        rooms.add(new StandardRoom());
        rooms.add(new DeluxeRoom());
        rooms.add(new SuiteRoom());

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Standard Room", 5);
        inventory.registerRoom("Deluxe Room", 0); // unavailable
        inventory.registerRoom("Suite Room", 2);

        // Guest searches available rooms
        SearchService search = new SearchService();
        search.searchAvailableRooms(inventory, rooms);

        System.out.println("\nSearch completed. No system data was modified.");
    }
}
