import Application.App;
import Data.DataRepository;
import Data.TextFileRepository;
import Dto.CreateUserDto;
import Models.AccountTypes;
import Models.Flight;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataRepository repository = new TextFileRepository();
        App app = new App(repository);
    }
}