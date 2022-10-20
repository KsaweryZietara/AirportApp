package Data;

import Models.Flight;
import Models.User;

import java.util.ArrayList;

public interface DataRepository {
    public void addUser(User user);
    public void removeUser(User user);
    public void addFlight(Flight flight);
    public void removeFlight(String flightNumber);

    public ArrayList<Flight> findFlights(String departureCity, String arrivalCity);
}

