package gui.controllers;

import domainLogic.Automat;
import domainLogic.hersteller.HerstellerImp;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenImp;
import domainLogic.kuchen.KuchenVerwaltung;
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

import static IO.jos.SaveAndLoadJOS.loadAutomatJOS;
import static IO.jos.SaveAndLoadJOS.saveAutomatJOS;
import static vertrag.KuchenTyp.Kremkuchen;
import static vertrag.KuchenTyp.Obstkuchen;


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

    //create Hersteller variables
    String createHersteller;
    //delete Hersteller variables
    String deleteHersteller;



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
    private Button buttonClickCreateHersteller;
    @FXML
    private Button buttonClickDeleteHersteller;

    @FXML
    private Button buttonClickSortByHersteller;

    @FXML
    private ChoiceBox<String> choiceBoxKuchenType;
    private final String[] typeKuchen = {"Kremkuchen","Obstkuchen","Obsttorte"};

    @FXML
    private TextField textFieldHersteller;
    @FXML
    private TextField textFieldPrice;


    @FXML
    private TextField textFieldAllergen;
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
    private TextField textFieldCreateHersteller;
    @FXML
    private TextField textFieldDeleteHersteller;

    @FXML
    private TextField textFieldSortByHersteller;

    @FXML
    private ListView<KuchenImp> myListViewKuchen;
    @FXML
    private ListView<String> myListViewHersteller;
    @FXML
    private ListView<KuchenImp> myListViewKuchenSortedByHersteller;


    KuchenVerwaltung kv = new KuchenVerwaltung();
    HerstellerVerwaltung hv = new HerstellerVerwaltung();

    Automat automat = new Automat(kv,hv);



    @FXML
    private void initialize() {
        choiceBoxKuchenType.setValue("Select KuchenType");
        choiceBoxKuchenType.getItems().addAll(typeKuchen);

        /*
        choiceBoxAllergen.setValue("Select Allergen");
        choiceBoxAllergen.getItems().addAll(allergen);
         */


        buttonClickCreateHersteller.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                createHersteller = buttonClickCreateHersteller.getText();
                String hersteller =textFieldCreateHersteller.getText() ;
                automat.createHersteller( hersteller );
                myListViewHersteller.getItems().add(hersteller);
                allert.setAlertType(Alert.AlertType.INFORMATION);
                allert.setContentText("Hersteller: (" + hersteller + ") created"  );
                allert.show();
                textFieldCreateHersteller.setText("");
            }
        });


        buttonClickDeleteHersteller.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                deleteHersteller = buttonClickDeleteHersteller.getText();
                String hersteller =textFieldDeleteHersteller.getText() ;
                automat.deleteHersteller( hersteller );
                myListViewHersteller.getItems().remove(hersteller);
                allert.setAlertType(Alert.AlertType.INFORMATION);
                allert.setContentText("Hersteller: (" + hersteller + ") deleted"  );
                allert.show();
                textFieldDeleteHersteller.setText("");
            }
        });


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


                Collection<Allergen> allergen = new LinkedList<>();
                System.out.println("[Allergen1],[Allergen2],[Allergen3],[Allergen4] (Allergen input is separated with [,] ) : ");
                String allerg = textFieldAllergen.getText(); // Read user input
                String[] allergParts = new String[4];
                allergParts = allerg.split(",");
                if(allergParts.length == 1){
                    String allerg1  = allergParts[0];
                    allergen.add(Allergen.valueOf(allerg1));
                } else if(allergParts.length == 2){
                    String allerg1  = allergParts[0];
                    String allerg2  = allergParts[1];
                    allergen.add(Allergen.valueOf(allerg1));
                    allergen.add(Allergen.valueOf(allerg2));
                } else if(allergParts.length == 3){
                    String allerg1  = allergParts[0];
                    String allerg2  = allergParts[1];
                    String allerg3  = allergParts[2];
                    allergen.add(Allergen.valueOf(allerg1));
                    allergen.add(Allergen.valueOf(allerg2));
                    allergen.add(Allergen.valueOf(allerg3));
                } else if(allergParts.length == 4){
                    String allerg1  = allergParts[0];
                    String allerg2  = allergParts[1];
                    String allerg3  = allergParts[2];
                    String allerg4  = allergParts[3];
                    allergen.add(Allergen.valueOf(allerg1));
                    allergen.add(Allergen.valueOf(allerg2));
                    allergen.add(Allergen.valueOf(allerg3));
                    allergen.add(Allergen.valueOf(allerg4));
                }
                System.err.println(allerg);
                textFieldAllergen.setText("");


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

                if(kuchenTyp.equals(Kremkuchen) || kuchenTyp.equals(Obstkuchen)) {
                    automat.createKuchen(kuchenTyp, hersteller, price, allergen, naehrwert, durr, topping1);
                } else {
                    automat.createKuchen(kuchenTyp, hersteller, price, allergen, naehrwert, durr, topping1, topping2);
                }

                myListViewKuchen.getItems().addAll(     (automat.readArrayOfKuchen())[automat.readArrayOfKuchen().length - 1]     );
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
                    automat.delete( fachnummer );
                    myListViewKuchen.getItems().remove(fachnummer);
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
                automat.update( fachnummer );
                myListViewKuchen.refresh();
                allert.setAlertType(Alert.AlertType.INFORMATION);
                allert.setContentText("Kuchen with fachnummer " + fachnummer + " updated"  );
                allert.show();
                textFieldDeleteKuchen.setText("");
            }
        });

        buttonClickSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                saveAutomatJOS( automat);
            }
        });

        buttonClickLoad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                myListViewKuchen.getItems().addAll(     (loadAutomatJOS().readArrayOfKuchen() )     );
            }
        });

        buttonClickSortByHersteller.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {

                myListViewKuchenSortedByHersteller.getItems().clear();
                hersteller = new HerstellerImp(textFieldSortByHersteller.getText() ) ;
                myListViewKuchenSortedByHersteller.getItems().addAll(  (automat.getArrayOfKuchenByHersteller(hersteller.getName()))  )    ;
                allert.setAlertType(Alert.AlertType.INFORMATION);
                allert.setContentText("List of Kuchen With Hersteller (" + hersteller.getName() + ")");
                allert.show();
            }
        });






    }
}



