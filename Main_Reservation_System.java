import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main_Reservation_System {
    private static int customerIdCounter = 1;
    private static int busIdCounter = 1;
    private static int reservationIdCounter = 1;

    private static Map<Integer, Customer> customers = new HashMap<>();
    private static Map<Integer, Bus> buses = new HashMap<>();
    private static Map<Integer, Reservation> reservations = new HashMap<>();
    private static Map<Integer, List<Integer>> busSeats = new HashMap<>();
    private static Map<Integer, Queue<Integer>> waitingList = new HashMap<>();

    // Customer Operations
    public static void registerCustomer(String name, String mobileNumber, String email, String city, int age) {
        Customer customer = new Customer(customerIdCounter++, name, mobileNumber, email, city, age);
        customers.put(customer.id, customer);
        System.out.println("Customer Registered: " + customer);
    }

    // Bus Operations
    public static void registerBus(String busNumber, int totalSeats, String startingPoint, String endingPoint,
            String startingTime, double fare) {
        Bus bus = new Bus(busIdCounter++, busNumber, totalSeats, startingPoint, endingPoint, startingTime, fare);
        buses.put(bus.id, bus);
        busSeats.put(bus.id, new ArrayList<>(Collections.nCopies(totalSeats, 0)));
        waitingList.put(bus.id, new ConcurrentLinkedQueue<>());
        System.out.println("Bus Registered: " + bus);
    }

    // Search Bus
    public static List<Bus> searchBuses(String startingPoint, String endingPoint) {
        List<Bus> result = new ArrayList<>();
        for (Bus bus : buses.values()) {
            if (bus.startingPoint.equalsIgnoreCase(startingPoint) && bus.endingPoint.equalsIgnoreCase(endingPoint)) {
                result.add(bus);
            }
        }
        return result;
    }

    // Reserve Seat
    public static void reserveSeat(int customerId, int busId) {
        List<Integer> seats = busSeats.get(busId);
        int seatNumber = seats.indexOf(0);
        if (seatNumber != -1) {
            seats.set(seatNumber, customerId);
            Reservation reservation = new Reservation(reservationIdCounter++, customerId, busId, seatNumber + 1,
                    new Date(seatNumber).toString(), "Reserved");
            reservations.put(reservation.id, reservation);
            notifyCustomer(customerId, "Reservation successful for seat number " + (seatNumber + 1));
            System.out.println("Reservation Made: " + reservation);
        } else {
            waitingList.get(busId).add(customerId);
            notifyCustomer(customerId, "All seats are currently booked. You are added to the waiting list.");
        }
    }

    // Cancel Reservation
    public static void cancelReservation(int reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservations.remove(reservationId);
            busSeats.get(reservation.busId).set(reservation.seatNumber - 1, 0);
            notifyCustomer(reservation.customerId, "Your reservation has been canceled.");
            System.out.println("Reservation Cancelled: " + reservation);

            Queue<Integer> waitingQueue = waitingList.get(reservation.busId);
            if (!waitingQueue.isEmpty()) {
                int nextCustomerId = waitingQueue.poll();
                reserveSeat(nextCustomerId, reservation.busId);
            }
        } else {
            System.out.println("Reservation ID not found.");
        }
    }

    // Notify Customer
    private static void notifyCustomer(int customerId, String message) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println("Notification sent to " + customer.name + ": " + message);
        }
    }

    // Display All Reservations
    public static void displayAllReservations() {
        for (Reservation reservation : reservations.values()) {
            System.out.println(reservation);
        }
    }

    public static void main(String[] args) {
        // Register Customers
        registerCustomer("John Doe", "1234567890", "john@example.com", "CityA", 25);
        registerCustomer("Jane Smith", "0987654321", "jane@example.com", "CityB", 30);

        // Register Buses
        registerBus("BUS101", 2, "CityA", "CityB", "10:00 AM", 50.0);
        registerBus("BUS102", 2, "CityA", "CityC", "12:00 PM", 60.0);

        // Search Buses
        System.out.println("Search Results:");
        for (Bus bus : searchBuses("CityA", "CityB")) {
            System.out.println(bus);
        }

        // Make Reservations
        reserveSeat(1, 1);
        reserveSeat(2, 1);
        reserveSeat(1, 2);

        // Display Reservations
        System.out.println("All Reservations:");
        displayAllReservations();

        // Cancel Reservation
        cancelReservation(1); // Cancel reservation with ID 1

        // Display Reservations after Cancellation
        System.out.println("Reservations after cancellation:");
        displayAllReservations();
    }
}
