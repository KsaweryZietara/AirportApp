package Models;

import java.util.ArrayList;

public class User {
    private String Login;
    private String Password;
    private String Email;
    private ArrayList<String> MarkedFlights;

    public User(String login, String password, String email, ArrayList<String> markedFlights) {
        this.Login = login;
        this.Password = password;
        this.Email = email;
        this.MarkedFlights = markedFlights;
    }

    public String getUserString() {
        String flights = String.join(",", MarkedFlights);
        return Login + ";" +
                Password + ";" +
                Email + ";" +
                flights;
    }
}
