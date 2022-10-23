package Models;

import java.util.ArrayList;

public class User {
    private int id;
    private String login;
    private String password;
    private String email;
    private AccountTypes accountType;
    private ArrayList<String> markedFlights;

    public User(int id, String login, String password, String email, AccountTypes accountType,ArrayList<String> markedFlights) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
        this.markedFlights = markedFlights;
    }

    public void addMarkedFlight(String flightNumber){
        markedFlights.add(flightNumber);
    }

    public void removeMarkedFlight(String flightNumber){
        markedFlights.removeIf(x -> x.equals(flightNumber));
    }

    public int getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public AccountTypes getAccountType() {
        return this.accountType;
    }

    public ArrayList<String> getMarkedFlights() {
        return this.markedFlights;
    }

    @Override
    public String toString() {
        String flights = String.join(",", markedFlights);
        return id + ";" +
                login + ";" +
                password + ";" +
                email + ";" +
                accountType + ";" +
                flights;
    }
}
