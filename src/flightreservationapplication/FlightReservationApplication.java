/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package flightreservationapplication;

/**
 *
 * @author sanja
 */
import java.util.Scanner;

public class FlightReservationApplication {
    static ApplicationSystem appsystem = new ApplicationSystem();

    public static void main(String[] args) {
        System.out.println("\t\t Welcome to Flight Reservation System  \t\t");

        char choice;
        Scanner scanner = new Scanner(System.in);
        do {
            displayMenu();
            choice = scanner.next().charAt(0);
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 'q':
                    System.out.println("Thanks for using FRS.");
                    break;
                case 'a':
                    addFlight(scanner);
                    break;
                case 'b':
                    bookFlight(scanner);
                    break;
                case 'k':
                    appsystem.displayAvailableFlights();
                    break;
                case 's':
                    sourceFlights(scanner);
                    break;
                case 'd':
                    destinationFlights(scanner);
                    break;
                case 'f':
                    flightDetails(scanner);
                    break;
                case 'm':
                    getOptimalPath(scanner);
                    break;
                case 'p':
                    addSampleFlights();
                    break;
                case 'r':
                    sampleRun();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 'q');
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println();
        System.out.println("_____________#######_______________");
        System.out.println("Enter Command:");
        System.out.println("q--> Quit.");
        System.out.println("a--> Add Flight.");
        System.out.println("b--> Book Flight.");
        System.out.println("k--> Display All Flights.");
        System.out.println("s--> Display Flights From Source.");
        System.out.println("d--> Display Flights to Destination.");
        System.out.println("f--> Display Flight Details.");
        System.out.println("m--> Get the Optimal Path.");
        System.out.println("p--> Add Sample Flights for testing.");
        System.out.println("r--> Sample Run.");
    }

    private static void addFlight(Scanner scanner) {
        System.out.println("Adding the Flight. Enter Details.");
        System.out.print("Flight Number: ");
        int flightNumber = scanner.nextInt();
        System.out.print("Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Source: ");
        String source = scanner.nextLine();
        System.out.print("Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Distance: ");
        int distance = scanner.nextInt();
        appsystem.addFlight(flightNumber, capacity, source, destination, distance);
    }

    private static void bookFlight(Scanner scanner) {
        System.out.println("Book a Flight. Enter Details.");
        System.out.print("Flight Number: ");
        int flightNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Passenger Name: ");
        String passengerName = scanner.nextLine();
        appsystem.bookFlight(flightNumber, passengerName);
    }

    private static void sourceFlights(Scanner scanner) {
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        appsystem.displayAllFlightsFromSource(source);
    }

    private static void destinationFlights(Scanner scanner) {
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        appsystem.displayDirectFlightsToDestination(destination);
    }

    private static void flightDetails(Scanner scanner) {
        System.out.print("Enter Flight Number: ");
        int flightNumber = scanner.nextInt();
        appsystem.displayAFlight(flightNumber);
    }

    private static void getOptimalPath(Scanner scanner) {
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        appsystem.displayShortestRoute(source, destination);
       
    }

    private static void addSampleFlights() {
        appsystem.addFlight(101, 150, "Colombo", "Tokyo", 2451);
        appsystem.addFlight(102, 200, "Colombo", "Paris", 1090);
        appsystem.addFlight(103, 180, "Tokyo", "Dubai", 954);
        appsystem.addFlight(104, 160, "Paris", "Tokyo", 2342);
        appsystem.addFlight(105, 220, "Dubai", "Colombo", 2845);
        appsystem.addFlight(106, 150, "Tokyo", "London", 1744);
    }

    private static void sampleRun() {
        addSampleFlights();
                
        appsystem.bookFlight(101, "Sanjaya");
        appsystem.bookFlight(102, "Dilshan");
        appsystem.displayAvailableFlights();
        appsystem.displayAllFlightsFromSource("Colombo");
        appsystem.displayDirectFlightsToDestination("Colombo");
        appsystem.displayAFlight(101);
        appsystem.displayShortestRoute("Colombo", "Dubai");
    }
}
