
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

    public void displayReservation() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType);
    }
}

// Booking History (stores confirmed bookings)
class BookingHistory {

    private LinkedList<Reservation> history = new LinkedList<>();

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
        System.out.println("Reservation stored in history: " + reservation.getReservationId());
    }

    // Display full booking history
    public void displayHistory() {

        System.out.println("\nBooking History");
        System.out.println("-----------------------");

        if (history.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        for (Reservation r : history) {
            r.displayReservation();
        }
    }

    public LinkedList<Reservation> getHistory() {
        return history;
    }
}

// Booking Report Service
class BookingReportService {

    public void generateReport(LinkedList<Reservation> history) {

        System.out.println("\nBooking Report Summary");
        System.out.println("-----------------------");

        HashMap<String, Integer> roomReport = new HashMap<>();

        for (Reservation r : history) {
            String type = r.getRoomType();
            roomReport.put(type, roomReport.getOrDefault(type, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : roomReport.entrySet()) {
            System.out.println(entry.getKey() + " Bookings: " + entry.getValue());
        }

        System.out.println("Total Reservations: " + history.size());
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("      Booking History System");
        System.out.println("=================================");

        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        Reservation r1 = new Reservation("ST101", "Alice", "Standard Room");
        Reservation r2 = new Reservation("DE102", "Bob", "Deluxe Room");
        Reservation r3 = new Reservation("SU103", "Charlie", "Suite Room");
        Reservation r4 = new Reservation("ST104", "David", "Standard Room");

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);
        history.addReservation(r4);

        // Admin views booking history
        history.displayHistory();

        // Admin generates report
        BookingReportService report = new BookingReportService();
        report.generateReport(history.getHistory());

        System.out.println("\nReport generated successfully.");
    }
}
