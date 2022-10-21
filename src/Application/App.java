package Application;

import Data.DataRepository;
import Dto.CreateUserDto;
import Dto.LoggedUserDto;
import Dto.LoginUserDto;
import Models.AccountTypes;

import java.util.Scanner;

public class App {
    private DataRepository repository;
    private Scanner myObj = new Scanner(System.in);

    public App(DataRepository repository) {
        this.repository = repository;

        boolean active = true;
        while(active){
            active = displayMenu();
        }
    }

    public boolean displayMenu() {
        System.out.println("Pick a number:\n" +
                            "1 if you have an account\n" +
                            "2 if you want to register\n" +
                            "3 if you want to exit");
        String input = myObj.nextLine();

        while (validNumber(input, 0, 4) == -1){
            System.out.println("Invalid input pick number again.");
            input = myObj.nextLine();
        }

        int number = validNumber(input, 0, 4);

        if(number == 1){
            System.out.println("Enter login");
            String login = myObj.nextLine();
            System.out.println("Enter password");
            String password = myObj.nextLine();

            LoginUserDto user = new LoginUserDto(login, password);
            LoggedUserDto loggedUser = repository.validUser(user);

            if(loggedUser == null){
                System.out.println("Invalid data, click enter to continue");
                input = myObj.nextLine();
            }
            else if(loggedUser.getAccountType() == AccountTypes.USER){
                boolean active = true;
                while(active){
                    active = displayUserMenu();
                }
            }
            else if (loggedUser.getAccountType() == AccountTypes.ADMIN) {
                boolean active = true;
                while(active){
                    active = displayAdminMenu();
                }
            }
            return true;
        }
        else if (number == 2) {
            registerOption();
            return true;
        }
        return false;
    }

    private boolean displayUserMenu(){
        //TODO
        return false;
    }

    private boolean displayAdminMenu(){
        //TODO
        return false;
    }

    private void registerOption(){
        System.out.println("Enter login");
        String login = myObj.nextLine();
        System.out.println("Enter password");
        String password = myObj.nextLine();
        System.out.println("Enter email");
        String email = myObj.nextLine();
        AccountTypes type = AccountTypes.USER;

        CreateUserDto user = new CreateUserDto(login, password, email, type);
        repository.addUser(user);
    }

    private int validNumber(String s, int a, int b){
        try{
            int x = Integer.parseInt(s);
            if(x > a && x < b){
                return x;
            }else{
                return -1;
            }
        }catch (Exception e){
            return -1;
        }
    }
}
