import Data.TextFileRepository;
import Dto.CreateUserDto;
import Models.AccountTypes;
import Models.Flight;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TextFileRepository t = new TextFileRepository();
        t.removeUser(2);
    }
}