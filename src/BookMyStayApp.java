
import java.util.*;

// Reservation request
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Centralized Inventory
class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decreaseRoom(String type) {
        int current = inventory.getOrDefault(type, 0);
        if (current > 0) {
            inventory.put(type, current - 1);
        }
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Booking Service
class BookingService {

    private Queue<Reservation> requestQueue;
    private RoomInventory inventory;
    private HashSet<String> allocatedRooms = new HashSet<>();
    private int roomCounter = 1;

    public BookingService(Queue<Reservation> queue, RoomInventory inventory) {
        this.requestQueue = queue;
        this.inventory = inventory;
    }

    public void processBookings() {

        while (!requestQueue.isEmpty()) {

            Reservation request = requestQueue.poll();
            String type = request.getRoomType();

            System.out.println("\nProcessing booking for " + request.getGuestName());

            if (inventory.getAvailability(type) > 0) {

                String roomId = generateRoomId(type);

                allocatedRooms.add(roomId);
                inventory.decreaseRoom(type);

                System.out.println("Reservation Confirmed!");
                System.out.println("Guest: " + request.getGuestName());
                System.out.println("Room Type: " + type);
                System.out.println("Allocated Room ID: " + roomId);

            } else {
                System.out.println("Sorry! No rooms available for " + type);
            }
        }
    }

    private String generateRoomId(String type) {
        String id;
        do {
            id = type.substring(0, 2).toUpperCase() + roomCounter++;
        } while (allocatedRooms.contains(id));
        return id;
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("   Reservation Confirmation");
        System.out.println("=================================");

        // Inventory setup
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Standard Room", 2);
        inventory.registerRoom("Deluxe Room", 1);
        inventory.registerRoom("Suite Room", 1);

        inventory.displayInventory();

        // Booking request queue
        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Alice", "Standard Room"));
        queue.add(new Reservation("Bob", "Deluxe Room"));
        queue.add(new Reservation("Charlie", "Standard Room"));
        queue.add(new Reservation("David", "Suite Room"));

        // Process bookings
        BookingService service = new BookingService(queue, inventory);
        service.processBookings();

        // Final inventory state
        inventory.displayInventory();
    }
}
