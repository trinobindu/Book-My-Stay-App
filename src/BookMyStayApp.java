
import java.util.*;

// Reservation request
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Inventory Service
class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Standard Room", 2);
        inventory.put("Deluxe Room", 1);
        inventory.put("Suite Room", 1);
    }

    // synchronized critical section
    public synchronized boolean allocateRoom(String type, String guest) {

        int available = inventory.getOrDefault(type, 0);

        if (available > 0) {
            inventory.put(type, available - 1);
            System.out.println("Room allocated to " + guest + " (" + type + ")");
            return true;
        } else {
            System.out.println("No room available for " + guest + " (" + type + ")");
            return false;
        }
    }

    public void displayInventory() {
        System.out.println("\nFinal Inventory State:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Concurrent Booking Processor
class BookingProcessor implements Runnable {

    private Queue<Reservation> bookingQueue;
    private RoomInventory inventory;

    public BookingProcessor(Queue<Reservation> queue, RoomInventory inventory) {
        this.bookingQueue = queue;
        this.inventory = inventory;
    }

    public void run() {

        while (true) {

            Reservation request;

            // synchronized queue access
            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) {
                    break;
                }
                request = bookingQueue.poll();
            }

            if (request != null) {
                inventory.allocateRoom(request.roomType, request.guestName);
            }

            try {
                Thread.sleep(200); // simulate processing delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("   Concurrent Booking Simulation");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();

        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Multiple guest requests
        bookingQueue.add(new Reservation("Alice", "Standard Room"));
        bookingQueue.add(new Reservation("Bob", "Standard Room"));
        bookingQueue.add(new Reservation("Charlie", "Standard Room"));
        bookingQueue.add(new Reservation("David", "Deluxe Room"));
        bookingQueue.add(new Reservation("Eva", "Suite Room"));

        // Multiple threads processing bookings
        Thread t1 = new Thread(new BookingProcessor(bookingQueue, inventory));
        Thread t2 = new Thread(new BookingProcessor(bookingQueue, inventory));
        Thread t3 = new Thread(new BookingProcessor(bookingQueue, inventory));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventory.displayInventory();

        System.out.println("\nAll booking requests processed safely.");
    }
}
