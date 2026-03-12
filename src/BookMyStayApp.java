
import java.util.*;

// Reservation class
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

// Inventory Service
class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    public void increaseRoom(String type) {
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Booking History
class BookingHistory {

    private List<String> history = new ArrayList<>();

    public void recordCancellation(String reservationId) {
        history.add("Cancelled: " + reservationId);
    }

    public void displayHistory() {
        System.out.println("\nBooking History Updates:");
        for (String h : history) {
            System.out.println(h);
        }
    }
}

// Cancellation Service
class CancellationService {

    private HashMap<String, Reservation> activeReservations;
    private RoomInventory inventory;
    private BookingHistory history;

    // rollback stack
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService(HashMap<String, Reservation> activeReservations,
                               RoomInventory inventory,
                               BookingHistory history) {
        this.activeReservations = activeReservations;
        this.inventory = inventory;
        this.history = history;
    }

    public void cancelReservation(String reservationId) {

        System.out.println("\nCancellation requested for: " + reservationId);

        if (!activeReservations.containsKey(reservationId)) {
            System.out.println("Error: Reservation does not exist.");
            return;
        }

        Reservation reservation = activeReservations.get(reservationId);

        // record room ID for rollback tracking
        rollbackStack.push(reservationId);

        // restore inventory
        inventory.increaseRoom(reservation.getRoomType());

        // remove reservation
        activeReservations.remove(reservationId);

        // update history
        history.recordCancellation(reservationId);

        System.out.println("Reservation cancelled successfully.");
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("   Booking Cancellation System");
        System.out.println("=================================");

        // Inventory setup
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Standard Room", 1);
        inventory.registerRoom("Deluxe Room", 1);

        // Active reservations
        HashMap<String, Reservation> activeReservations = new HashMap<>();

        Reservation r1 = new Reservation("ST101", "Alice", "Standard Room");
        Reservation r2 = new Reservation("DE102", "Bob", "Deluxe Room");

        activeReservations.put(r1.getReservationId(), r1);
        activeReservations.put(r2.getReservationId(), r2);

        // Booking history
        BookingHistory history = new BookingHistory();

        // Cancellation service
        CancellationService cancelService =
                new CancellationService(activeReservations, inventory, history);

        // Cancel a booking
        cancelService.cancelReservation("ST101");

        // Display results
        inventory.displayInventory();
        history.displayHistory();
    }
}
