package Data;

import Dtos.CreateUserDto;
import Dtos.LoggedUserDto;
import Dtos.LoginUserDto;
import Models.Flight;

import java.util.List;

public interface DataRepository {
    public void addMarkedFlight(int id, String flightNumber);
    public void removeMarkedFlight(int id, String flightNumber);
    public LoggedUserDto validUser(LoginUserDto loginUserDto);
    public void addUser(CreateUserDto createUserDto);
    public void removeUser(int id);
    public void addFlight(Flight flight);
    public void removeFlight(String flightNumber);
    public List<Flight> findFlights(String departureCity, String arrivalCity);
    public List<Flight> userFlights(int id);
}

