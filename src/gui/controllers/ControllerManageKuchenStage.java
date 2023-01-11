package gui.controllers;

import domainLogic.*;
import gui.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

import static IO.SaveAndLoad.loadKuchenVerwaltung;
import static IO.SaveAndLoad.saveKuchenVerwaltung;


public class ControllerManageKuchenStage extends GUI {



    Alert allert = new Alert(Alert.AlertType.NONE);

    //Add Kuchen variables
    KuchenTyp kuchenTyp;
    HerstellerImp hersteller;
    BigDecimal price;

    int naehrwert;
    Duration duration;
    String topping1;
    String topping2;

    //delete Kuchen variables
    String deleteKuchen;
    //update Kuchen variables
    String updateKuchen;


    @FXML
    private Button buttonClickSubmit;
    @FXML
    private Button buttonClickDelete;
    @FXML
    private Button buttonClickUpdate;
    @FXML
    private Button buttonClickSave;
    @FXML
    private Button buttonClickLoad;

    @FXML
    private ChoiceBox<String> choiceBoxKuchenType;
    private final String[] typeKuchen = {"Kremkuchen","Obstkuchen","Obsttorte"};

    @FXML
    private TextField textFieldHersteller;
    @FXML
    private TextField textFieldPrice;



    @FXML
    private ChoiceBox<String> choiceBoxAllergen;
    private final String[] allergen = {"Gluten","Erdnuss","Haselnuss","Sesamsamen"};



    @FXML
    private TextField textFieldNaehrwert;
    @FXML
    private TextField textFieldDuration;
    @FXML
    private TextField textFieldTopping1;
    @FXML
    private TextField textFieldTopping2;
    @FXML
    private TextField textFieldDeleteKuchen;
    @FXML
    private TextField textFieldUpdateKuchen;


    @FXML
    private ListView<KuchenImp> myListView;

    KuchenVerwaltung kv = new KuchenVerwaltung();



    @FXML
    private void initialize() {
        choiceBoxKuchenType.setValue("Select KuchenType");
        choiceBoxKuchenType.getItems().addAll(typeKuchen);

        choiceBoxAllergen.setValue("Select Allergen");
        choiceBoxAllergen.getItems().addAll(allergen);


        buttonClickSubmit.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                kuchenTyp = KuchenTyp.valueOf(choiceBoxKuchenType.getValue());
                System.err.println(kuchenTyp);
                choiceBoxKuchenType.setValue("Select KuchenType");


                hersteller = new HerstellerImp( textFieldHersteller.getText());
                System.err.println(hersteller);
                textFieldHersteller.setText("");

                price = new BigDecimal(textFieldPrice.getText());
                System.err.println(price);
                textFieldPrice.setText("");


                Collection<Allergen> allerg = new LinkedList<>();
                allerg.add(Allergen.valueOf(choiceBoxAllergen.getValue()));
                System.err.println(choiceBoxAllergen.getValue());
                choiceBoxAllergen.setValue("Select Allergen");


                naehrwert = Integer.parseInt(textFieldNaehrwert.getText() );
                System.err.println(naehrwert);
                textFieldNaehrwert.setText("");

                Duration durr = Duration.ofDays(       Integer.parseInt( textFieldDuration.getText()     ) ) ;
                System.err.println(duration);
                textFieldDuration.setText("");

                topping1 = textFieldTopping1.getText();
                System.err.println(topping1);
                textFieldTopping1.setText("");

                topping2 = textFieldTopping2.getText();
                System.err.println(topping2);
                textFieldTopping2.setText("");



                kv.create(kuchenTyp, hersteller, price, allerg, naehrwert, durr, topping1, topping2);
                myListView.getItems().addAll(     (kv.read())[kv.read().length - 1]     );
                allert.setAlertType(Alert.AlertType.INFORMATION);
                allert.setContentText("Kuchen added.");
                allert.show();
            }
        });

        buttonClickDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                deleteKuchen = buttonClickDelete.getText();
                    int fachnummer = Integer.parseInt(textFieldDeleteKuchen.getText()) ;
                    kv.delete( fachnummer );
                myListView.getItems().remove(fachnummer);
                    allert.setAlertType(Alert.AlertType.INFORMATION);
                    allert.setContentText("Kuchen with fachnummer " + fachnummer + " deleted"  );
                    allert.show();
                    textFieldDeleteKuchen.setText("");
            }
        });


        buttonClickUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                updateKuchen = buttonClickUpdate.getText();
                int fachnummer = Integer.parseInt(textFieldUpdateKuchen.getText()) ;
                kv.update( fachnummer );
                myListView.refresh();
                allert.setAlertType(Alert.AlertType.INFORMATION);
                allert.setContentText("Kuchen with fachnummer " + fachnummer + " deleted"  );
                allert.show();
                textFieldDeleteKuchen.setText("");
            }
        });

        buttonClickSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                saveKuchenVerwaltung( kv);
            }
        });

        buttonClickLoad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                myListView.getItems().addAll(     (loadKuchenVerwaltung().read() )     );
            }
        });





    }
}



