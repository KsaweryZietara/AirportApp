package Models;

import java.util.ArrayList;

public class User {
    private int Id;
    private String Login;
    private String Password;
    private String Email;
    private AccountTypes AccountType;
    private ArrayList<String> MarkedFlights;

    public User(int id, String login, String password, String email, AccountTypes accountType,ArrayList<String> markedFlights) {
        this.Id = id;
        this.Login = login;
        this.Password = password;
        this.Email = email;
        this.AccountType = accountType;
        this.MarkedFlights = markedFlights;
    }

    public int getId() {
        return this.Id;
    }

    public String getLogin() {
        return this.Login;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getEmail() {
        return this.Email;
    }

    public AccountTypes getAccountType() {
        return this.AccountType;
    }

    public ArrayList<String> getMarkedFlights() {
        return this.MarkedFlights;
    }

    public String getUserString() {
        String flights = String.join(",", MarkedFlights);
        return Id + ";" +
                Login + ";" +
                Password + ";" +
                Email + ";" +
                AccountType + ";" +
                flights;
    }
}
