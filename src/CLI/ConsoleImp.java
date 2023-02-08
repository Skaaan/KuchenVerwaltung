package CLI;


import java.util.LinkedList;

import IO.jos.SaveAndLoadJOS;
import domainLogic.Automat;
import domainLogic.hersteller.HerstellerImp;

import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static vertrag.KuchenTyp.*;
import static vertrag.KuchenTyp.Obsttorte;


public class ConsoleImp {

    private final Automat automat;
    private final SaveAndLoadJOS saveAndLoadJOS;


    // Creating a Scanner object for every single input for more clarity
    private final Scanner scan= new Scanner(System.in);
    private final Scanner myKuchenType = new Scanner(System.in);
     Scanner myHersteller = new Scanner(System.in);
    private final Scanner myDeleteHersteller = new Scanner(System.in);




    public ConsoleImp(Automat automat, SaveAndLoadJOS saveAndLoadJOS) {
        this.automat = automat;
        this.saveAndLoadJOS = saveAndLoadJOS;
    }


    public  Scanner getScanner(){
        return scan;
    }

    public String getInputHersteller(){
        System.out.println("[Herstellername] (creating new Hersteller): ");
        return myHersteller.nextLine();
    }

    public String runAddHersteller() throws IllegalStateException {
            String s = getInputHersteller();
            automat.createHersteller( s );
            System.out.println("Hersteller (" + s + ") inserted" );
            return s;
    }


    public void runAddKuchen() throws IllegalStateException {
        while(true) {
            try {
                if(automat.getListOfHersteller().size() == 0 ) {
                    System.out.println("Automat has no existing Hersteller yet, create first some Hersteller to make it possible adding a Kuchen");
                    break;
                }

                System.out.println("[Kuchen-Typ] [Herstellername] [Preis] [Naehrwert] [Duration] [Comma separated allergens, single comma for none] [[Obstsorte]] [[Kremsorte]] " +
                        "adds a kuchen: ");
                String[] inputKuchen  = scan.nextLine().split(" ");

                KuchenTyp typ = KuchenTyp.valueOf(inputKuchen[0]);

                String hersteller = inputKuchen[1];
                HerstellerImp h = new HerstellerImp(hersteller);

                String doublePrice = inputKuchen[2];
                BigDecimal price = new BigDecimal(doublePrice);

                int naehrwert = Integer.parseInt(inputKuchen[3]);

                Duration duration = Duration.ofDays(Integer.parseInt(inputKuchen[4]) );

                Collection<Allergen> allergen = new LinkedList<>();
                String allerg = inputKuchen[5];
                String[] allergParts = new String[4];
                allergParts = allerg.split(",");
                if (allergParts.length == 1) {
                    String allerg1 = allergParts[0];
                    if(allerg1.equals(",")) {
                        break;
                    }
                    allergen.add(Allergen.valueOf(allerg1));
                } else if (allergParts.length == 2) {
                    String allerg1 = allergParts[0];
                    String allerg2 = allergParts[1];
                    allergen.add(Allergen.valueOf(allerg1));
                    allergen.add(Allergen.valueOf(allerg2));
                } else if (allergParts.length == 3) {
                    String allerg1 = allergParts[0];
                    String allerg2 = allergParts[1];
                    String allerg3 = allergParts[2];
                    allergen.add(Allergen.valueOf(allerg1));
                    allergen.add(Allergen.valueOf(allerg2));
                    allergen.add(Allergen.valueOf(allerg3));
                } else if (allergParts.length == 4) {
                    String allerg1 = allergParts[0];
                    String allerg2 = allergParts[1];
                    String allerg3 = allergParts[2];
                    String allerg4 = allergParts[3];
                    allergen.add(Allergen.valueOf(allerg1));
                    allergen.add(Allergen.valueOf(allerg2));
                    allergen.add(Allergen.valueOf(allerg3));
                    allergen.add(Allergen.valueOf(allerg4));
                }

                //if typ ist Kremkuchen or Obstkuchen, the kuchen can have only one topping
                if ((typ == Kremkuchen) || (typ == Obstkuchen)) {
                    String topping1 = inputKuchen[6];
                    automat.createKuchen(typ, h, price, allergen, naehrwert, duration, topping1);
                    break;
                    //if typ ist Obsttorte, the kuchen can have two topping
                } else if (typ == Obsttorte) {
                    String topping1 = inputKuchen[6];
                    String topping2 = inputKuchen[7];
                    automat.createKuchen(typ, h, price, allergen, naehrwert, duration, topping1, topping2);
                    break;
                }

                System.out.println("Kuchen inserted");

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

    }








    public void runShowHerstellerWithKuchen() {
        System.out.println("Hersteller with Kuchen:");
        System.out.println(automat.listHerstellerWithNumberOfKuchen() );

    }




    public void runShowKuchenWithType() {
        System.out.println("kuchen [[Typ]]:");
        String input = myKuchenType.nextLine();
        switch (input) {
            case "Kremkuchen":
                for (int i = 0; i < automat.getListOfKuchenByType(Kremkuchen).size(); i++) {
                    System.out.println(automat.getListOfKuchenByType(Kremkuchen).get(i));
                }
                break;
            case "Obstkuchen":
                for (int i = 0; i < automat.getListOfKuchenByType(Obstkuchen).size(); i++) {
                    System.out.println(automat.getListOfKuchenByType(Obstkuchen).get(i));
                }
                break;
            case "Obsttorte":
                for (int i = 0; i < automat.getListOfKuchenByType(Obsttorte).size(); i++) {
                    System.out.println(automat.getListOfKuchenByType(Obsttorte).get(i));
                }
                break;
        }
    }



    public void runShowAllergen() {
        System.out.println("Existing allergens in Automat:");
        System.out.println( automat.getAllergenList(true) );

        System.out.println("Not existing allergens in Automat:");
        System.out.println( automat.getAllergenList(false) );
        }


    public void runDeleteKuchen(){
        Scanner scan = getScanner();
        while (true) {
            try{
                System.out.print("[Fachnummer]:");
                int fachnummer = scan.nextInt();
                automat.deleteKuchen(fachnummer);
                System.out.println("Kuchen deleted");
                break;
            }
            catch(IllegalArgumentException e) {
                System.out.println("Enter valid Fachnummer (Fachnummer must be > 0)");
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Kuchen with the given Fachnummer does not exist");
                break;
            }
            catch(InputMismatchException e) {
                scan.nextLine();
                System.out.println("Enter valid Fachnummer (Fachnummer must be an integer)");
            }
        }
    }



    public void runDeleteHersteller() {
        while(true) {
            try {
                System.out.println("[Hersteller]: ");
                String hersteller = myDeleteHersteller.nextLine();
                automat.deleteHersteller(hersteller);
                System.out.println("Hersteller (" + hersteller + ") deleted");
                break;
            } catch (NullPointerException e) {
                System.out.println("Automat has no existing Hersteller yet");
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("This Hersteller does not exist in the automat");
                break;
            }
        }
    }





    public void runUpdateKuchen() {
        Scanner scan = getScanner();
        System.out.print("[Fachnummer] Enter the Fachnummer of the Kuchen that you want to update:");
        int input = scan.nextInt();
        try {
            automat.update(input);
        } catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }


    public void runPersistenzmodus(){
            saveAndLoadJOS.saveAutomatJOS(automat);
        System.out.println("loaded Kuchen:");
            for(int i = 0; i < saveAndLoadJOS.loadAutomatJOS().readArrayOfKuchen().length; i++) {
                System.out.println(saveAndLoadJOS.loadAutomatJOS().readArrayOfKuchen()[i]);
            }
        System.out.println("loaded Hersteller:");

        for(int i = 0; i < saveAndLoadJOS.loadAutomatJOS().readListOfHersteller().length; i++) {
                System.out.println(saveAndLoadJOS.loadAutomatJOS().readListOfHersteller()[i]);
            }
        }







    public void execute() {
        try(Scanner scan = new Scanner(System.in)) {
            do {
                System.out.println("""
                     
                     :c Switch to insert mode
                     :d Switch to delete mode
                     :r Switch to display mode
                     :u Switch to change mode
                     :p Change to persistence mode
                     
                    """);
                System.out.println("enter Command");
                String token = scan.next();

                switch (token) {
                    case ":c" -> {
                        runAddHersteller();
                        runAddKuchen();
                    }
                    case ":d" -> {
                        runDeleteHersteller();
                        runDeleteKuchen();
                    }
                    case ":u" -> {
                        runUpdateKuchen();
                    }
                    case ":r" -> {
                            runShowHerstellerWithKuchen();
                            runShowKuchenWithType();
                            runShowAllergen();
                    }
                    case ":p" -> {
                        runPersistenzmodus();
                    }
                }
            } while (true);
        }
    }


}