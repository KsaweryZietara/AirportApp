package main.Data;

import main.Dtos.CreateUserDto;
import main.Dtos.LoggedUserDto;
import main.Dtos.LoginUserDto;
import main.Models.AccountTypes;
import main.Models.Flight;
import main.Models.User;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextFileRepository implements DataRepository {
    final String FLIGHTSPATH = "src/main/Resources/flights.txt";
    final String USERSPATH = "src/main/Resources/users.txt";
    ArrayList<Flight> flights = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    public TextFileRepository(){
        getFlights();
        getUsers();
    }

    @Override
    public void addMarkedFlight(int id, String flightNumber) {
        for (User u : users){
            if (id == u.getId()){
                u.addMarkedFlight(flightNumber);
            }
        }
        saveUsers();
    }

    @Override
    public void removeMarkedFlight(int id, String flightNumber) {
        for (User u : users){
            if(id == u.getId()){
                u.removeMarkedFlight(flightNumber);
            }
        }
        saveUsers();
    }

    @Override
    public LoggedUserDto validUser(LoginUserDto loginUserDto){
        for (User u : users){
            if(u.getLogin().equals(loginUserDto.getLogin()) &&
                    u.getPassword().equals(loginUserDto.getPassword())){
                return new LoggedUserDto(u.getId(), u.getLogin(), u.getAccountType(), u.getMarkedFlights());
            }
        }
        return null;
    }

    @Override
    public void addUser(CreateUserDto createUserDto) {
        User user = new User(getHighestId() + 1,
                                createUserDto.getLogin(),
                                createUserDto.getPassword(),
                                createUserDto.getEmail(),
                                createUserDto.getAccountType(),
                                new ArrayList<>());
        users.add(user);
        saveUsers();
    }

    @Override
    public void removeUser(int id) {
        users.removeIf(x -> x.getId() == id);
        saveUsers();
    }

    @Override
    public void addFlight(Flight flight) {
        flights.add(flight);
        saveFlights();
    }

    @Override
    public void removeFlight(String flightNumber) {
        flights.removeIf(x -> x.getFlightNumber().equals(flightNumber));
        saveFlights();
    }

    @Override
    public List<Flight> findFlights(String departureCity, String arrivalCity) {
        return flights.stream().filter(x ->
                                    x.getDepartureCity().equals(departureCity) &&
                                    x.getArrivalCity().equals(arrivalCity))
                                    .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Flight> userFlights(int id) {
        User user = users.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);

        return flights.stream()
                .filter(x -> user.getMarkedFlights().contains(x.getFlightNumber()))
                .toList();
    }

    private int getHighestId(){
        int max = 1;
        for (User u : users){
            if(u.getId() > max){
                max = u.getId();
            }
        }
        return max;
    }

    private void getUsers() {
        try {
            File file = new File(USERSPATH);
            Scanner myReader = new Scanner(file);

            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] arr = data.split(";");

                ArrayList<String> flights = new ArrayList<>();
                if (arr.length == 6){
                    String[] flightsArray = arr[5].split(",");
                    flights = new ArrayList<>(Arrays.asList(flightsArray));
                }

                AccountTypes account;
                if(arr[4].equals("ADMIN")){
                    account = AccountTypes.ADMIN;
                }
                else {
                    account = AccountTypes.USER;
                }

                User user = new User(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], account, flights);
                users.add(user);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void saveUsers(){
        try {
            FileWriter myWriter = new FileWriter(USERSPATH);
            for (User u : users){
                myWriter.write(u.toString() + "\n");
            }
            myWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void getFlights() {
        try {
            File file = new File(FLIGHTSPATH);
            Scanner myReader = new Scanner(file);

            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] arr = data.split(";");
                Flight flight = new Flight(arr[0], arr[1], arr[2], arr[3]);
                flights.add(flight);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void saveFlights(){
        try {
            FileWriter myWriter = new FileWriter(FLIGHTSPATH);
            for (Flight f : flights){
                myWriter.write(f.toString() + "\n");
            }
            myWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
