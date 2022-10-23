package Dtos;

import Models.AccountTypes;

public class CreateUserDto {
    private final String login;
    private final String password;
    private final String email;
    private final AccountTypes accountType;

    public CreateUserDto(String login, String password, String email, AccountTypes accountTypes) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.accountType = accountTypes;
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
}
