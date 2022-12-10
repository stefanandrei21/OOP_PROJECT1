import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

    InputLoader inputLoader = new InputLoader();
    inputLoader.init("./basic_1.json");
    Input input = inputLoader.readData();
        System.out.println(input.getMovieList());
        System.out.println(input.getActions());
        System.out.println(input.getUserList());
//        File myFile = new File("filename.txt");
//        if (myFile.createNewFile()) {
//            System.out.println("File created: " + myFile.getName());
//        } else {
//            System.out.println("File already exists.");
//        }
    }
}
