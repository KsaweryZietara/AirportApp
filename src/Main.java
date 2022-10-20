import Data.TextFileRepository;
import Models.Flight;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        TextFileRepository t = new TextFileRepository();
        //t.addFlight(new Flight("katowice", "warszawa", "ddddd", "32334234"));
        t.saveUsers();

    }
}