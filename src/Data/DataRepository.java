package Data;

import Dto.CreateUserDto;
import Dto.LoggedUserDto;
import Dto.LoginUserDto;
import Models.Flight;

import java.util.ArrayList;

public interface DataRepository {
    public LoggedUserDto validUser(LoginUserDto loginUserDto);
    public void addUser(CreateUserDto createUserDto);
    public void removeUser(int id);
    public void addFlight(Flight flight);
    public void removeFlight(String flightNumber);
    public ArrayList<Flight> findFlights(String departureCity, String arrivalCity);
}

