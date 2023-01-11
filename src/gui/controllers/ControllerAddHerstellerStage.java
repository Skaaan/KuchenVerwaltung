package gui.controllers;

import domainLogic.HerstellerImp;
import domainLogic.HerstellerVerwaltung;
import gui.GUI;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class ControllerAddHerstellerStage extends GUI {




    Alert allert = new Alert(Alert.AlertType.NONE);
    String hersteller;
    @FXML
    private Button buttonClickSubmit;
    @FXML
    private TextField textFieldHersteller;


    @FXML
    private ListView<HerstellerImp> myListView;

    HerstellerVerwaltung hv = new HerstellerVerwaltung();

    @FXML
    private void initialize() {
        buttonClickSubmit.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                hersteller = textFieldHersteller.getText();
                System.err.println(hersteller);
                hv.create(hersteller);
                myListView.getItems().addAll(          (hv.read())[hv.read().length - 1]     );
                allert.setAlertType(Alert.AlertType.INFORMATION);
                allert.setContentText("Hersteller added.");
                allert.show();
                textFieldHersteller.setText("");
            }
        });
    }
}

