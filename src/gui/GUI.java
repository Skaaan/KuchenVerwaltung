package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;


public class GUI extends Application{




    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/sample/StartStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
        stage.setTitle("Choose CRUD option");
        stage.setScene(scene);
        stage.show();
    }

    public void AddHerstellerStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("fxml/sample/AddHerstellerStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hersteller Input");
        stage.setScene(scene);
        stage.show();
    }

    public void ManageKuchenStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("fxml/sample/ManageKuchenStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 825, 600);
        stage.setTitle("Adding a Kuchen");
        stage.setScene(scene);
        stage.show();
    }


}
