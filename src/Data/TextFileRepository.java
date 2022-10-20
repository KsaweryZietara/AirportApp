package Data;

import Models.Flight;
import Models.User;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextFileRepository implements DataRepository {
    final String FLIGHTSPATH = "flights.txt";
    final String USERSPATH = "users.txt";
    ArrayList<Flight> Flights = new ArrayList<>();
    ArrayList<User> Users = new ArrayList<>();

    public TextFileRepository(){
        getFlights();
        getUsers();
    }

    @Override
    public void addUser(User user) {
        Users.add(user);
        saveUsers();
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void addFlight(Flight flight) {
        Flights.add(flight);
        saveFlights();
    }

    @Override
    public void removeFlight(String flightNumber) {
        Flights.removeIf(x -> x.getFlightNumber().equals(flightNumber));
        saveFlights();
    }

    @Override
    public ArrayList<Flight> findFlights(String departureCity, String arrivalCity) {
        return Flights.stream().filter(x ->
                                    x.getDepartureCity().equals(departureCity) &&
                                    x.getArrivalCity().equals(arrivalCity))
                                    .collect(Collectors.toCollection(ArrayList::new));
    }

    private void getUsers() {
        try {
            File file = new File(USERSPATH);
            Scanner myReader = new Scanner(file);

            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] arr = data.split(";");

                String[] flightsArray = arr[3].split(",");
                ArrayList<String> flights = new ArrayList<>(Arrays.asList(flightsArray));

                User user = new User(arr[0], arr[1], arr[2], flights);
                Users.add(user);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void saveUsers(){
        try {
            FileWriter myWriter = new FileWriter(USERSPATH);
            for (User u : Users){
                myWriter.write(u.getUserString() + "\n");
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
                Flights.add(flight);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void saveFlights(){
        try {
            FileWriter myWriter = new FileWriter(FLIGHTSPATH);
            for (Flight f : Flights){
                myWriter.write(f.getFlightString() + "\n");
            }
            myWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}