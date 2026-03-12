
import java.io.*;
import java.util.*;

// Reservation class (Serializable)
class Reservation implements Serializable {
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
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}

// System State container
class SystemState implements Serializable {
    HashMap<String, Integer> inventory;
    HashMap<String, Reservation> reservations;

    public SystemState(HashMap<String, Integer> inventory,
                       HashMap<String, Reservation> reservations) {
        this.inventory = inventory;
        this.reservations = reservations;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "hotel_state.dat";

    // Save system state
    public void saveState(SystemState state) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(state);
            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving system state: " + e.getMessage());
        }
    }

    // Load system state
    public SystemState loadState() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("System state loaded successfully.");
            return (SystemState) in.readObject();

        } catch (Exception e) {
            System.out.println("No saved data found. Starting fresh.");
            return null;
        }
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("     Persistence & Recovery");
        System.out.println("=================================");

        PersistenceService persistence = new PersistenceService();

        // Try loading previous system state
        SystemState state = persistence.loadState();

        HashMap<String, Integer> inventory;
        HashMap<String, Reservation> reservations;

        if (state == null) {

            // Fresh system startup
            inventory = new HashMap<>();
            reservations = new HashMap<>();

            inventory.put("Standard Room", 5);
            inventory.put("Deluxe Room", 3);
            inventory.put("Suite Room", 2);

            Reservation r1 = new Reservation("ST101", "Alice", "Standard Room");
            Reservation r2 = new Reservation("DE102", "Bob", "Deluxe Room");

            reservations.put(r1.getReservationId(), r1);
            reservations.put(r2.getReservationId(), r2);

            System.out.println("New system state initialized.");

        } else {

            // Recover system state
            inventory = state.inventory;
            reservations = state.reservations;

            System.out.println("System state recovered from file.");
        }

        // Display current system state
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("\nCurrent Reservations:");
        for (Reservation r : reservations.values()) {
            r.display();
        }

        // Simulate system shutdown
        System.out.println("\nSaving system state before shutdown...");
        SystemState newState = new SystemState(inventory, reservations);
        persistence.saveState(newState);

        System.out.println("System shutdown complete.");
    }
}
