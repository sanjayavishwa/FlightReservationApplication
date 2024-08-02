/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightreservationapplication;

/**
 *
 * @author sanja
 */
import java.util.*;
import java.io.*;
public class ApplicationSystem {
    private List<Flight> flights = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();
    private Map<String, List<Integer>> destinations = new HashMap<>();
    private Map<String, List<Integer>> sources = new HashMap<>();

    private Flight getFlight(int flightNumber) {
        for (Flight flight : flights) {
            if (flight.flightNumber == flightNumber) {
                return flight;
            }
        }
        throw new RuntimeException("Flight Not found");
    }

    private List<Flight> getAllFlightsFromSource(String source) {
        List<Flight> result = new ArrayList<>();
        if (sources.containsKey(source)) {
            for (int flightNumber : sources.get(source)) {
                result.add(getFlight(flightNumber));
            }
        }
        return result;
    }

    private List<Flight> getAllFlightsToDestination(String destination) {
        List<Flight> result = new ArrayList<>();
        if (destinations.containsKey(destination)) {
            for (int flightNumber : destinations.get(destination)) {
                result.add(getFlight(flightNumber));
            }
        }
        return result;
    }

    private List<Integer> getTheShortestRoute(String source, String destination) {
        List<Integer> path = new ArrayList<>();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));

        for (String dest : destinations.keySet()) {
            distances.put(dest, Integer.MAX_VALUE);
            prev.put(dest, "");
        }

        distances.put(source, 0);
        pq.add(new Pair(0, source));

        while (!pq.isEmpty()) {
            String currentSource = pq.poll().value;
            int currentDistance = distances.get(currentSource);

            for (Flight flight : getAllFlightsFromSource(currentSource)) {
                int newDistance = flight.distance + currentDistance;
                if (newDistance < distances.get(flight.destination)) {
                    distances.put(flight.destination, newDistance);
                    prev.put(flight.destination, currentSource);
                    pq.add(new Pair(newDistance, flight.destination));
                }
            }
        }

        String target = destination;
        while (!prev.get(target).isEmpty()) {
            for (Flight flight : getAllFlightsToDestination(target)) {
                if (flight.source.equals(prev.get(target))) {
                    path.add(flight.flightNumber);
                    break;
                }
            }
            target = prev.get(target);
        }

        Collections.reverse(path);
        return path;
    }

    public void addFlight(int flightNumber, int capacity, String source, String destination, int distance) {
        Flight newFlight = new Flight(flightNumber, capacity, source, destination, distance);
        flights.add(newFlight);
        destinations.computeIfAbsent(destination, k -> new ArrayList<>()).add(flightNumber);
        sources.computeIfAbsent(source, k -> new ArrayList<>()).add(flightNumber);
        System.out.println("Flight " + flightNumber + " is added. Source: " + source + ". Destination: " + destination);
    }

    public void bookFlight(int flightNumber, String passengerName) {
        try {
            Flight resFlight = getFlight(flightNumber);
            if (resFlight.capacity > resFlight.bookedSeats) {
                Passenger passenger = new Passenger(passengerName, flightNumber);
                resFlight.bookedSeats++;
                System.out.println(passengerName + " booked the flight " + resFlight.flightNumber + " from " + resFlight.source + " to " + resFlight.destination + ".");
            } else {
                System.out.println("No available seats on flight " + flightNumber);
            }
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void displayAFlight(int flightNumber) {
        try {
            Flight flight = getFlight(flightNumber);
            System.out.println("#----------------------#");
            System.out.println("Flight Number: " + flight.flightNumber);
            System.out.println("Source: " + flight.source);
            System.out.println("Destination: " + flight.destination);
            System.out.println("Distance: " + flight.distance);
            System.out.println("Capacity: " + flight.capacity);
            System.out.println("Booked Seats: " + flight.bookedSeats);
            System.out.println("Available Seat Count: " + (flight.capacity - flight.bookedSeats));
            System.out.println("#----------------------#");
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void displayAllFlightsFromSource(String source) {
        List<Flight> flights = getAllFlightsFromSource(source);
        if (!flights.isEmpty()) {
            System.out.println("#----------------------#");
            System.out.println("Flights from " + source);
            for (Flight flight : flights) {
                System.out.println("Flight " + flight.flightNumber);
                System.out.println("#----------------------#");
            }
        } else {
            System.out.println("No flights from " + source);
        }
    }

    public void displayDirectFlightsToDestination(String destination) {
        List<Flight> flights = getAllFlightsToDestination(destination);
        if (!flights.isEmpty()) {
            System.out.println("#----------------------#");
            System.out.println("Direct flights to " + destination);
            for (Flight flight : flights) {
                System.out.println("Flight " + flight.flightNumber);
            }
        } else {
            System.out.println("No direct flights to " + destination);
        }
    }

    public void displayAvailableFlights() {
        System.out.println("#----------------------#");
        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.flightNumber);
            System.out.println("Source: " + flight.source);
            System.out.println("Destination: " + flight.destination);
            System.out.println("Distance: " + flight.distance);
            System.out.println("Capacity: " + flight.capacity);
            System.out.println("Available Seat Count: " + (flight.capacity - flight.bookedSeats));
        }
    }

    public void displayShortestRoute(String source, String destination) {
        List<Integer> path = getTheShortestRoute(source, destination);
        System.out.println("#----------------------#");
        System.out.println("Optimal path from " + source + " to " + destination + ":");
        for (int flightNumber : path) {
            System.out.print(flightNumber + " --> ");
        }
        System.out.println();
        System.out.println("#----------------------#");
    }

    private static class Pair {
        int key;
        String value;

        public Pair(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
