package Dto;

public class LoginUserDto {
    private String Login;
    private String Password;

    public LoginUserDto(String login, String password) {
        this.Login = login;
        this.Password = password;
    }

    public String getLogin() {
        return this.Login;
    }

    public String getPassword() {
        return this.Password;
    }
}
