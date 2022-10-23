package main;

import main.Application.App;
import main.Data.DataRepository;
import main.Data.TextFileRepository;

public class Main {
    public static void main(String[] args) {
        DataRepository repository = new TextFileRepository();
        App app = new App(repository);
    }
}