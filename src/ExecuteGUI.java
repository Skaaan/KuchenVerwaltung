import gui.GUI;
import javafx.application.Application;


public abstract class ExecuteGUI extends Application {
    // if runtime components are missing, add vm options -> --module-path "C:\Users\skan\IntelliJ IDEA Community Edition 2021.2.2\java\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml
   //  Path: javafx lib path in your Machine
    public static void main(String[] args) {
        Application.launch(GUI.class, args);
    }

}
