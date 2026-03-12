
import java.util.*;

// Reservation class
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

// Inventory Service
class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    public boolean roomExists(String type) {
        return inventory.containsKey(type);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

// Invalid Booking Validator
class BookingValidator {

    public static void validateReservation(Reservation reservation, RoomInventory inventory) {

        if (reservation.getGuestName() == null || reservation.getGuestName().trim().isEmpty()) {
            throw new IllegalArgumentException("Guest name cannot be empty.");
        }

        if (reservation.getRoomType() == null || reservation.getRoomType().trim().isEmpty()) {
            throw new IllegalArgumentException("Room type must be provided.");
        }

        if (!inventory.roomExists(reservation.getRoomType())) {
            throw new IllegalArgumentException("Invalid room type selected.");
        }

        if (inventory.getAvailability(reservation.getRoomType()) <= 0) {
            throw new IllegalStateException("Selected room type is not available.");
        }
    }
}

// Booking Service
class BookingService {

    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processReservation(Reservation reservation) {

        try {

            // Validate booking input
            BookingValidator.validateReservation(reservation, inventory);

            // If validation passes
            System.out.println("Booking request accepted for: " + reservation.getGuestName());
            System.out.println("Requested Room: " + reservation.getRoomType());

        } catch (IllegalArgumentException | IllegalStateException e) {

            // Meaningful error message
            System.out.println("Booking Failed: " + e.getMessage());

        }
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("     Validation & Error Handling");
        System.out.println("=================================");

        // Setup inventory
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Standard Room", 2);
        inventory.registerRoom("Deluxe Room", 0);
        inventory.registerRoom("Suite Room", 1);

        BookingService service = new BookingService(inventory);

        // Example booking inputs
        Reservation r1 = new Reservation("Alice", "Standard Room"); // valid
        Reservation r2 = new Reservation("", "Deluxe Room");        // invalid guest
        Reservation r3 = new Reservation("Bob", "Luxury Room");     // invalid type
        Reservation r4 = new Reservation("Charlie", "Deluxe Room"); // unavailable

        service.processReservation(r1);
        service.processReservation(r2);
        service.processReservation(r3);
        service.processReservation(r4);

        System.out.println("\nSystem continues running safely.");
    }
}
