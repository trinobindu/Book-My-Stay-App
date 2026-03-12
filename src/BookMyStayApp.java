
import java.util.*;

// Reservation class representing a booking request
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

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

// Booking Request Queue using FIFO
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    // Add request to queue
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // Display queued requests
    public void displayQueue() {
        System.out.println("\nCurrent Booking Requests (FIFO Order)");
        System.out.println("--------------------------------------");

        if (queue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        for (Reservation r : queue) {
            r.displayReservation();
        }
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("   Booking Request Queue (FIFO)");
        System.out.println("=================================");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Guests submitting booking requests
        Reservation r1 = new Reservation("Alice", "Standard Room");
        Reservation r2 = new Reservation("Bob", "Deluxe Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        // Add requests to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queue (arrival order preserved)
        bookingQueue.displayQueue();

        System.out.println("\nRequests are waiting for processing.");
        System.out.println("No room inventory has been modified yet.");
    }
}
