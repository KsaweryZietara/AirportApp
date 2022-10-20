package Models;

public class Flight {
    private String DepartureCity;
    private String ArrivalCity;
    private String Airline;
    private String FlightNumber;

    public Flight(String departureCity, String arrivalCity, String airline, String flightNumber){
        this.DepartureCity = departureCity;
        this.ArrivalCity = arrivalCity;
        this.Airline = airline;
        this.FlightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return DepartureCity;
    }

    public String getArrivalCity() {
        return ArrivalCity;
    }

    public String getAirline() {
        return Airline;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public String getFlightString(){
        return DepartureCity + ";" +
                ArrivalCity + ";" +
                Airline + ";" +
                FlightNumber;
    }
}


