abstract class Room {
    protected String roomType;
    protected double pricePerNight;

    public Room(String roomType, double pricePerNight) {
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public abstract void displayDetails();
}

class StandardRoom extends Room {
    static int availableRooms = 5;

    public StandardRoom() {
        super("Standard Room", 2000);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Price per Night: ₹" + pricePerNight);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("---------------------------");
    }
}

class DeluxeRoom extends Room {
    static int availableRooms = 3;

    public DeluxeRoom() {
        super("Deluxe Room", 3500);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Price per Night: ₹" + pricePerNight);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("---------------------------");
    }
}

class SuiteRoom extends Room {
    static int availableRooms = 2;

    public SuiteRoom() {
        super("Suite Room", 5000);
    }

    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Price per Night: ₹" + pricePerNight);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("---------------------------");
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       BOOK MY STAY APP");
        System.out.println("   Hotel Room Availability");
        System.out.println("=================================");

        Room standard = new StandardRoom();
        Room deluxe = new DeluxeRoom();
        Room suite = new SuiteRoom();

        standard.displayDetails();
        deluxe.displayDetails();
        suite.displayDetails();

        System.out.println("Thank you for checking room availability!");
    }
}
