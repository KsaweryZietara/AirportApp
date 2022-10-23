package main.Application;

import main.Data.DataRepository;
import main.Dtos.CreateUserDto;
import main.Dtos.LoggedUserDto;
import main.Dtos.LoginUserDto;
import main.Models.AccountTypes;
import main.Models.Flight;

import java.util.List;
import java.util.Scanner;

public class App {
    private final DataRepository repository;
    private final Scanner scanner = new Scanner(System.in);

    public App(DataRepository repository) {
        this.repository = repository;

        boolean active = true;
        while(active){
            active = menu();
        }
    }

    public boolean menu() {
        System.out.println("Pick a number:\n" +
                            "1.Login\n" +
                            "2.Register\n" +
                            "3.Exit");
        String input = scanner.nextLine();

        while (validNumber(input, 0, 4) == -1){
            System.out.println("Invalid input pick number again.");
            input = scanner.nextLine();
        }

        int number = validNumber(input, 0, 4);

        if(number == 1){
            loginOption();
            return true;
        }
        else if (number == 2) {
            registerOption();
            return true;
        }

        return false;
    }

    private boolean userMenu(LoggedUserDto userDto){
        System.out.println("Pick a number:\n" +
                "1.Search for flights\n" +
                "2.Your flights\n" +
                "3.Log out");
        String input = scanner.nextLine();

        while (validNumber(input, 0, 4) == -1){
            System.out.println("Invalid input pick number again.");
            input = scanner.nextLine();
        }

        int number = validNumber(input, 0, 4);

        if(number == 1){
            findFlightOption(userDto);
            return true;
        }
        else if (number == 2) {
            getMarkedFlightsOption(userDto);
            return true;
        }

        return false;
    }

    private boolean adminMenu(LoggedUserDto userDto){
        System.out.println("Pick a number:\n" +
                "1.Add user\n" +
                "2.Remove user\n" +
                "3.Add flight\n" +
                "4.Remove flight\n" +
                "5.Log out");
        String input = scanner.nextLine();

        while (validNumber(input, 0, 6) == -1){
            System.out.println("Invalid input pick number again.");
            input = scanner.nextLine();
        }

        int number = validNumber(input, 0, 6);

        switch (number) {
            case 1:
                addUserOption();
                return true;
            case 2:
                removeUserOption();
                return true;
            case 3:
                addFlightOption();
                return true;
            case 4:
                removeFlightOption();
                return true;
            default:
                return false;
        }
    }

    private void addUserOption(){
        System.out.println("Enter login");
        String login = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        System.out.println("Enter account type");
        String account = scanner.nextLine();

        AccountTypes accountType;

        if(account.equals("admin")){
            accountType = AccountTypes.ADMIN;
        }
        else {
            accountType = AccountTypes.USER;
        }

        CreateUserDto user = new CreateUserDto(login, password, email, accountType);
        repository.addUser(user);

        System.out.println("User has been added, click enter to continue");
        String s = scanner.nextLine();
    }

    private void removeUserOption(){
        System.out.println("Enter user id");
        String id = scanner.nextLine();
        int number;

        try{
            number = Integer.parseInt(id);
        }catch (Exception ignored){
            System.out.println("Invalid id, click enter to continue");
            String s = scanner.nextLine();
            return;
        }

        repository.removeUser(number);
        System.out.println("User has been deleted, click enter to continue");
        String s = scanner.nextLine();
    }

    private void addFlightOption(){
        System.out.println("Enter departure city");
        String departureCity = scanner.nextLine();
        System.out.println("Enter arrival city");
        String arrivalCity = scanner.nextLine();
        System.out.println("Enter airline");
        String airline = scanner.nextLine();
        System.out.println("Enter flightNumber");
        String flightNumber = scanner.nextLine();

        Flight flight = new Flight(departureCity, arrivalCity, airline, flightNumber);
        repository.addFlight(flight);

        System.out.println("Flight has been added, click enter to continue");
        String s = scanner.nextLine();
    }

    private void removeFlightOption(){
        System.out.println("Enter flight number");
        String flightNumber = scanner.nextLine();

        repository.removeFlight(flightNumber);

        System.out.println("Flight has been deleted, click enter to continue");
        String s = scanner.nextLine();
    }

    private void findFlightOption(LoggedUserDto userDto){
        System.out.println("Enter departure city");
        String departureCity = scanner.nextLine();
        System.out.println("Enter arrival city");
        String arrivalCity = scanner.nextLine();

        List<Flight> flights = repository.findFlights(departureCity, arrivalCity);

        if(flights.size() == 0){
            System.out.println("There is no available flights, click enter to continue");
            String s = scanner.nextLine();
            return;
        }

        for (int i = 0; i < flights.size(); ++i) {
            System.out.println(String.format("%o. %s -> %s\nAirline: %s\nFlight number: %s", i+1,
                    flights.get(i).getDepartureCity(),
                    flights.get(i).getArrivalCity(),
                    flights.get(i).getAirline(),
                    flights.get(i).getFlightNumber()));
        }

        System.out.println("\nPick 0 if you want to exit or number of the flight to add it to the marked flights");
        String n = scanner.nextLine();

        while (validNumber(n, -1, flights.size() + 1) == -1){
            System.out.println("Invalid input pick number again.");
            n = scanner.nextLine();
        }

        int number = validNumber(n, -1, flights.size() + 1);

        if (number == 0){
            return;
        }

        repository.addMarkedFlight(userDto.getId(), flights.get(number - 1).getFlightNumber());

        System.out.println("Flight added, click enter to continue");
        String s = scanner.nextLine();
    }

    private void getMarkedFlightsOption(LoggedUserDto userDto){
        List<Flight> flights = repository.userFlights(userDto.getId());

        if(flights.size() == 0){
            System.out.println("There is no flights, click enter to continue");
            String s = scanner.nextLine();
            return;
        }

        for (int i = 0; i < flights.size(); ++i) {
            System.out.println(String.format("%o. %s -> %s\nAirline: %s\nFlight number: %s", i+1,
                    flights.get(i).getDepartureCity(),
                    flights.get(i).getArrivalCity(),
                    flights.get(i).getAirline(),
                    flights.get(i).getFlightNumber()));
        }

        System.out.println("\nPick 0 if you want to exit or number of the flight to remove it from the marked flights");
        String n = scanner.nextLine();

        while (validNumber(n, -1, flights.size() + 1) == -1){
            System.out.println("Invalid input pick number again.");
            n = scanner.nextLine();
        }

        int number = validNumber(n, -1, flights.size() + 1);

        if (number == 0){
            return;
        }

        repository.removeMarkedFlight(userDto.getId(), flights.get(number - 1).getFlightNumber());

        System.out.println("Flight removed, click enter to continue");
        String s = scanner.nextLine();
    }

    private void loginOption(){
        System.out.println("Enter login");
        String login = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        LoginUserDto user = new LoginUserDto(login, password);
        LoggedUserDto loggedUser = repository.validUser(user);

        if(loggedUser == null){
            System.out.println("Invalid data, click enter to continue");
            String input = scanner.nextLine();
        }
        else if(loggedUser.getAccountType() == AccountTypes.USER){
            boolean active = true;
            while(active){
                active = userMenu(loggedUser);
            }
        }
        else if (loggedUser.getAccountType() == AccountTypes.ADMIN) {
            boolean active = true;
            while(active){
                active = adminMenu(loggedUser);
            }
        }
    }

    private void registerOption(){
        System.out.println("Enter login");
        String login = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        AccountTypes type = AccountTypes.USER;

        CreateUserDto user = new CreateUserDto(login, password, email, type);
        repository.addUser(user);

        System.out.println("Account created, click enter to continue");
        String input = scanner.nextLine();
    }

    private int validNumber(String s, int a, int b){
        try{
            int x = Integer.parseInt(s);
            if(x > a && x < b){
                return x;
            }else{
                return -1;
            }
        }catch (Exception e){
            return -1;
        }
    }
}
