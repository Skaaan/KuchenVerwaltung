package gui.controllers;

import gui.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;



import java.io.IOException;

public class ControllerStartStage {

    @FXML
    private Button buttonClickAddHersteller;

    @FXML
    private Button buttonClickManageKuchen;

    @FXML
    private void initialize() {     // Source: https://stackoverflow.com/questions/34785417/javafx-fxml-controller-constructor-vs-initialize-method
        buttonClickAddHersteller.setOnAction(new EventHandler<ActionEvent>() {    //GUI Seite 27 //anonyme
            @Override
            public void handle(ActionEvent event) {
                try {
                    new GUI().AddHerstellerStage(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        buttonClickManageKuchen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new GUI().ManageKuchenStage(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });






    }


}
