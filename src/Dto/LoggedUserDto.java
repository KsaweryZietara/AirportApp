package Dto;

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
}
