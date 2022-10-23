package Dtos;

import Models.AccountTypes;

import java.util.ArrayList;

public class LoggedUserDto {
    private final int id;
    private final String login;
    private final AccountTypes accountType;
    private final ArrayList<String> markedFlights;

    public LoggedUserDto(int id, String login, AccountTypes accountType, ArrayList<String> markedFlights) {
        this.id = id;
        this.login = login;
        this.accountType = accountType;
        this.markedFlights = markedFlights;
    }

    public int getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public AccountTypes getAccountType() {
        return this.accountType;
    }

    public ArrayList<String> getMarkedFlights() {
        return this.markedFlights;
    }
}
