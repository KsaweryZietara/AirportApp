package main.Dtos;

public class LoginUserDto {
    private final String login;
    private final String password;

    public LoginUserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }
}
