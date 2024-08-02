/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightreservationapplication;

/**
 *
 * @author sanja
 */
public class Flight {
    int flightNumber;
    int capacity;
    int bookedSeats;
    String source;
    String destination;
    int distance;

    public Flight(int flightNumber, int capacity, String source, String destination, int distance) {
        this.flightNumber = flightNumber;
        this.capacity = capacity;
        this.bookedSeats = 0;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }
}
