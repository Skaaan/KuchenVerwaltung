package CLI;


import java.util.LinkedList;

import domainLogic.hersteller.HerstellerImp;

import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static IO.jos.SaveAndLoadJOS.loadAutomatJOS;
import static IO.jos.SaveAndLoadJOS.saveAutomatJOS;
import static vertrag.KuchenTyp.*;
import static vertrag.KuchenTyp.Obsttorte;


public class ConsoleImp implements Console {

    // Creating a Scanner object for every single input for mor clarity
    private static final Scanner scan= new Scanner(System.in);
    private static final Scanner myKuchenType = new Scanner(System.in);
    private static final Scanner myHersteller = new Scanner(System.in);
    private static final Scanner myPrice = new Scanner(System.in);
    private static final Scanner myAllergen = new Scanner(System.in);
    private static final Scanner myNaehrwert = new Scanner(System.in);
    private static final Scanner myDuration = new Scanner(System.in);
    private static final Scanner myTopping = new Scanner(System.in);
    private static final Scanner myPersistenz = new Scanner(System.in);
    private static final Scanner myDeleteHersteller = new Scanner(System.in);


    public static Scanner getScanner(){
        return scan;
    }

    public static void rundAddHersteller() throws IllegalStateException {
        try {
            System.out.println("[Herstellername] (creating new Hersteller): ");
            String hersteller = myHersteller.nextLine();  // Read user input
            automat.createHersteller(hersteller);
            System.out.println("Hersteller (" + hersteller + ") inserted" );

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }


    public static void runAddKuchen() throws IllegalStateException {
            try {
                System.out.println("Inserting a Kuchen(Input Kuchen Parameter following): ");

                System.out.println("[KuchenType] (Kremkuchen, Obstkuchen or Obsttorte): ");
                KuchenTyp typ = KuchenTyp.valueOf(myKuchenType.nextLine());

                System.out.println("[Hersteller]: ");
                String hersteller = myHersteller.nextLine();  // Read user input
                HerstellerImp h = new HerstellerImp(hersteller);

                System.out.println("[Price]: ");
                String doublePrice = myPrice.nextLine();  // Read user input
                BigDecimal price = new BigDecimal(doublePrice);

                Collection<Allergen> allergen = new LinkedList<>();
                System.out.println("[Allergen1],[Allergen2],[Allergen3],[Allergen4] (Allergen input is separated with [,] ) : ");
                String allerg = myAllergen.nextLine(); // Read user input
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

                System.out.println("[Naehrwert]: ");
                int naehrwert = myNaehrwert.nextInt();  // Read user input

                System.out.println("[Duration]: ");
                Duration duration = Duration.ofDays(Integer.parseInt(myDuration.nextLine()));

                //if typ ist Kremkuchen or Obstkuchen, the kuchen can have only one topping
                if( (typ == Kremkuchen) || (typ == Obstkuchen) ){
                    System.out.println("[Topping]: ");
                    String topping1 = myTopping.nextLine();
                    automat.createKuchen(typ, h, price, allergen, naehrwert, duration, topping1);
                    //if typ ist Obsttorte, the kuchen can have two topping
                } else if(typ == Obsttorte){
                    System.out.println("[Topping1] [Topping2]: ");
                    String topping = myTopping.nextLine(); // Read user input
                    String[] toppingParts = topping.split(" ");
                    String topping1  = toppingParts[0];
                    String topping2  = toppingParts[1];
                    automat.createKuchen(typ, h, price, allergen, naehrwert, duration, topping1, topping2);
                }

                System.out.println("Kuchen inserted");

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

    }




    public static void runShowHerstellerWithKuchen() {
        System.out.println("Hersteller with Kuchen:");

            System.out.println(automat.listHerstellerWithNumberOfKuchen() );

    }




    public static void runShowKuchenWithType() {
        System.out.println("Display of the cakes - possibly filtered by type, enter Type [Kremkuchen], [ObstKuchen] or [Obsttorte]:");
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



    public static void runShowAllergen() {
        System.out.println("Existing allergens:");
        System.out.println( automat.getAllergenList(true) );

        System.out.println("Not existing allergens:");
        System.out.println( automat.getAllergenList(false) );
        }








    public static void rundDeleteKuchen(){
        Scanner scan = getScanner();
        while (true) {
            try{
                System.out.print("[Fachnummer] delete a Kuchen with the given Fachnummer:");
                int fachnummer = scan.nextInt();
                automat.delete(fachnummer);
                System.out.println("Kuchen  deleted");
                break; }
            catch(IllegalArgumentException e){
                System.out.println("Enter valid Fachnummer (int!!):");
            }
        }
    }

//Todo ask pro why throws Exception
    public static void rundDeleteHersteller() {
        try {
            System.out.println("[Hersteller] delete a Hersteller: ");
            String hersteller = myDeleteHersteller.nextLine();
            automat.deleteHersteller(hersteller);
            System.out.println("Hersteller " + hersteller + " deleted");
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }





    public static void runUpdateKuchen(){
        Scanner scan = getScanner();
        System.out.print("[Fachnummer] Enter the Fachnummer of the Kuchen that you want to update:");
        automat.update(scan.nextInt());
    }


    public static void runPersistenzmodus(){
        System.out.println("Persistenzmodus:: ");
        System.out.println("[saveJOS] saves using JOS");
        System.out.println("[loadJOS] loads using JOS");
        String input = myPersistenz.nextLine();  // Read user input
        if(input.equals("saveJOS")){
            saveAutomatJOS(automat);
        } else if(input.equals("loadJOS")){
            for(int i = 0; i < loadAutomatJOS().readArrayOfKuchen().length; i++) {
                System.out.println(loadAutomatJOS().readArrayOfKuchen()[i]);
            }
        }


    }




    public static void execute() {
        System.out.println("""
                     :c Wechsel in den Einfügemodus
                     :d Wechsel in den Löschmodus
                     :r Wechsel in den Anzeigemodus
                     :u Wechsel in den Änderungsmodus
                     :p Wechsel in den Persistenzmodus
                    """);
        try(Scanner scan = new Scanner(System.in)) {
            do {
                System.out.println("enter Command");
                String token = scan.next();
                switch (token) {
                    case ":c" -> {
                        ConsoleImp.rundAddHersteller();
                        ConsoleImp.runAddKuchen();
                    }
                    case ":d" -> {
                        ConsoleImp.rundDeleteHersteller();
                        ConsoleImp.rundDeleteKuchen();
                    }
                    case ":u" -> {
                        ConsoleImp.runUpdateKuchen();
                        System.out.println("Kuchen up to date");
                    }
                    case ":r" -> {
                        ConsoleImp.runShowHerstellerWithKuchen();
                        ConsoleImp.runShowKuchenWithType();
                        ConsoleImp.runShowAllergen();
                    }
                    case ":p" -> {
                        ConsoleImp.runPersistenzmodus();
                    }
                }
            } while (true);
        }
    }






}