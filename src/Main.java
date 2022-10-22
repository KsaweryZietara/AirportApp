import Application.App;
import Data.DataRepository;
import Data.TextFileRepository;

public class Main {
    public static void main(String[] args) {
        DataRepository repository = new TextFileRepository();
        App app = new App(repository);
    }
}