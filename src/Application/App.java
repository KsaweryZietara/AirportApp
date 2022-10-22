package Application;

import Data.DataRepository;
import Dtos.CreateUserDto;
import Dtos.LoggedUserDto;
import Dtos.LoginUserDto;
import Models.AccountTypes;
import Models.Flight;

import java.util.List;
import java.util.Scanner;

public class App {
    private final DataRepository repository;
    private final Scanner myObj = new Scanner(System.in);

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
        String input = myObj.nextLine();

        while (validNumber(input, 0, 4) == -1){
            System.out.println("Invalid input pick number again.");
            input = myObj.nextLine();
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
        String input = myObj.nextLine();

        while (validNumber(input, 0, 4) == -1){
            System.out.println("Invalid input pick number again.");
            input = myObj.nextLine();
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
        String input = myObj.nextLine();

        while (validNumber(input, 0, 6) == -1){
            System.out.println("Invalid input pick number again.");
            input = myObj.nextLine();
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
        String login = myObj.nextLine();
        System.out.println("Enter password");
        String password = myObj.nextLine();
        System.out.println("Enter email");
        String email = myObj.nextLine();
        System.out.println("Enter account type");
        String account = myObj.nextLine();

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
        String s = myObj.nextLine();
    }

    private void removeUserOption(){
        System.out.println("Enter user id");
        String id = myObj.nextLine();
        int number;

        try{
            number = Integer.parseInt(id);
        }catch (Exception ignored){
            System.out.println("Invalid id, click enter to continue");
            String s = myObj.nextLine();
            return;
        }

        repository.removeUser(number);
        System.out.println("User has been deleted, click enter to continue");
        String s = myObj.nextLine();
    }

    private void addFlightOption(){
        //TODO
    }

    private void removeFlightOption(){
        //TODO
    }

    private void findFlightOption(LoggedUserDto userDto){
        System.out.println("Enter departure city");
        String departureCity = myObj.nextLine();
        System.out.println("Enter arrival city");
        String arrivalCity = myObj.nextLine();

        List<Flight> flights = repository.findFlights(departureCity, arrivalCity);

        if(flights.size() == 0){
            System.out.println("There is no available flights, click enter to continue");
            String s = myObj.nextLine();
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
        String n = myObj.nextLine();

        while (validNumber(n, -1, flights.size() + 1) == -1){
            System.out.println("Invalid input pick number again.");
            n = myObj.nextLine();
        }

        int number = validNumber(n, -1, flights.size() + 1);

        if (number == 0){
            return;
        }

        repository.addMarkedFlight(userDto.getId(), flights.get(number - 1).getFlightNumber());

        System.out.println("Flight added, click enter to continue");
        String s = myObj.nextLine();
    }

    private void getMarkedFlightsOption(LoggedUserDto userDto){
        List<Flight> flights = repository.userFlights(userDto.getId());

        if(flights.size() == 0){
            System.out.println("There is no flights, click enter to continue");
            String s = myObj.nextLine();
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
        String n = myObj.nextLine();

        while (validNumber(n, -1, flights.size() + 1) == -1){
            System.out.println("Invalid input pick number again.");
            n = myObj.nextLine();
        }

        int number = validNumber(n, -1, flights.size() + 1);

        if (number == 0){
            return;
        }

        repository.removeMarkedFlight(userDto.getId(), flights.get(number - 1).getFlightNumber());

        System.out.println("Flight removed, click enter to continue");
        String s = myObj.nextLine();
    }

    private void loginOption(){
        System.out.println("Enter login");
        String login = myObj.nextLine();
        System.out.println("Enter password");
        String password = myObj.nextLine();

        LoginUserDto user = new LoginUserDto(login, password);
        LoggedUserDto loggedUser = repository.validUser(user);

        if(loggedUser == null){
            System.out.println("Invalid data, click enter to continue");
            String input = myObj.nextLine();
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
        String login = myObj.nextLine();
        System.out.println("Enter password");
        String password = myObj.nextLine();
        System.out.println("Enter email");
        String email = myObj.nextLine();
        AccountTypes type = AccountTypes.USER;

        CreateUserDto user = new CreateUserDto(login, password, email, type);
        repository.addUser(user);

        System.out.println("Account created, click enter to continue");
        String input = myObj.nextLine();
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
