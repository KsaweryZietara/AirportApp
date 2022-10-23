package main.Models;

public class Flight {
    private String departureCity;
    private String arrivalCity;
    private String airline;
    private String flightNumber;

    public Flight(String departureCity, String arrivalCity, String airline, String flightNumber){
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.airline = airline;
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public String getAirline() {
        return airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String toString(){
        return departureCity + ";" +
                arrivalCity + ";" +
                airline + ";" +
                flightNumber;
    }
}


