import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineReservationSystem {

    // A map to store reservations with PNR as the key
    private static Map<String, ReservationData> reservations = new HashMap<>();

    public static void main(String[] args) {
        loginForm();
    }

    static class ReservationData {
        String pnr;
        String passengerName;
        String contactInfo;
        String trainNumber;
        String classType;
        String dateOfJourney;
        String departureStation;
        String destinationStation;
    }

    public static void loginForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your login ID and password:");
        String loginID = scanner.nextLine();
        String password = scanner.nextLine();

        if (isValidUser(loginID, password)) {
            System.out.println("Login successful! Welcome to the Reservation System.");
            showMainMenu();
        } else {
            System.out.println("Invalid login credentials. Please try again.");
            loginForm();
        }
    }

    public static boolean isValidUser(String loginID, String password) {
        // Implement your logic to validate login credentials here
        // For simplicity, assume a valid user with ID "admin" and password "123"
        return loginID.equals("admin") && password.equals("123");
    }

    public static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Main Menu - Please select an option:");
        System.out.println("1. Make a Reservation");
        System.out.println("2. Cancel a Reservation");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                reservationSystem();
                break;
            case 2:
                cancellationForm();
                break;
            case 3:
                System.out.println("Thank you for using the Online Reservation System. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
                showMainMenu();
        }
    }

    public static void reservationSystem() {
        Scanner scanner = new Scanner(System.in);
        ReservationData reservationData = new ReservationData();

        System.out.println("Welcome to the Reservation System.");
        System.out.println("Please enter your details:");

        System.out.print("Passenger Name: ");
        reservationData.passengerName = scanner.nextLine();

        System.out.print("Contact Information: ");
        reservationData.contactInfo = scanner.nextLine();

        System.out.print("Train Number: ");
        reservationData.trainNumber = scanner.nextLine();

        System.out.print("Class Type: ");
        reservationData.classType = scanner.nextLine();

        System.out.print("Date of Journey: ");
        reservationData.dateOfJourney = scanner.nextLine();

        System.out.print("Departure Station: ");
        reservationData.departureStation = scanner.nextLine();

        System.out.print("Destination Station: ");
        reservationData.destinationStation = scanner.nextLine();

        // Generate a random PNR (for simplicity, use a simple counter)
        reservationData.pnr = "PNR" + reservations.size();

        // Save the reservation to the map
        reservations.put(reservationData.pnr, reservationData);

        System.out.println("Reservation successful! Your PNR number is " + reservationData.pnr);
        showMainMenu();
    }

    public static void cancellationForm() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("To cancel your ticket, please provide your PNR number:");
        String pnrNumber = scanner.nextLine();

        ReservationData reservationData = reservations.get(pnrNumber);

        if (reservationData != null) {
            System.out.println("Reservation details for PNR " + pnrNumber + ":");
            System.out.println("Passenger Name: " + reservationData.passengerName);
            System.out.println("Train Number: " + reservationData.trainNumber);
            System.out.println("Class Type: " + reservationData.classType);
            System.out.println("Date of Journey: " + reservationData.dateOfJourney);
            System.out.println("Departure Station: " + reservationData.departureStation);
            System.out.println("Destination Station: " + reservationData.destinationStation);

            System.out.println("Press 'Y' to confirm cancellation or any other key to abort:");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("Y")) {
                reservations.remove(pnrNumber);
                System.out.println("Ticket cancellation successful.");
            } else {
                System.out.println("Cancellation not confirmed. Your ticket remains valid.");
            }
        } else {
            System.out.println("Invalid PNR number. Please try again.");
            cancellationForm();
        }

        showMainMenu();
    }
}
