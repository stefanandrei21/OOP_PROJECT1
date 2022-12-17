import DataBase.Input;
import DataBase.InputLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

    InputLoader inputLoader = new InputLoader();
  // inputLoader.init("D:\\My_homework\\checker\\resources\\in\\basic_10.json");
    inputLoader.init(args[0]);
    Input input = inputLoader.readData();
    ProgramWorkflow work = new ProgramWorkflow();
    work.setInput(input);
    List<Output> listToPrint = new ArrayList<Output>();
    listToPrint = work.work();






    CreateJsonFile myJsonFile = new CreateJsonFile(listToPrint);
    myJsonFile.work();

    }
}
