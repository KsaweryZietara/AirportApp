package Dtos;

import Models.AccountTypes;

import java.util.ArrayList;

public class LoggedUserDto {
    private int Id;
    private String Login;
    private AccountTypes AccountType;
    private ArrayList<String> MarkedFlights;

    public LoggedUserDto(int id, String login, AccountTypes accountType, ArrayList<String> markedFlights) {
        this.Id = id;
        this.Login = login;
        this.AccountType = accountType;
        this.MarkedFlights = markedFlights;
    }

    public int getId() {
        return this.Id;
    }

    public String getLogin() {
        return this.Login;
    }

    public AccountTypes getAccountType() {
        return this.AccountType;
    }

    public ArrayList<String> getMarkedFlights() {
        return this.MarkedFlights;
    }
}
