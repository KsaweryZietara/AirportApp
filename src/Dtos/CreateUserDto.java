package Dtos;

import Models.AccountTypes;

public class CreateUserDto {
    private String Login;
    private String Password;
    private String Email;
    private AccountTypes AccountType;

    public CreateUserDto(String login, String password, String email, AccountTypes accountTypes) {
        this.Login = login;
        this.Password = password;
        this.Email = email;
        this.AccountType = accountTypes;
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
}
