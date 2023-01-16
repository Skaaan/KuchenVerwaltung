package CLI;


import java.util.LinkedList;

import domainLogic.KremkuchenImp;
import domainLogic.HerstellerImp;

import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;


import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;





public class ConsoleImp implements Console {

    // Creating a Scanner object
    private static final Scanner scan= new Scanner(System.in);
    private static final Scanner myKuchenType = new Scanner(System.in);
    private static final Scanner myHersteller = new Scanner(System.in);
    private static final Scanner myPrice = new Scanner(System.in);
    private static final Scanner myAllergen = new Scanner(System.in);
    private static final Scanner myNaehrwert = new Scanner(System.in);
    private static final Scanner myDuration = new Scanner(System.in);
    private static final Scanner myTopping1 = new Scanner(System.in);
    private static final Scanner myTopping2 = new Scanner(System.in);


    public static Scanner getScanner(){
        return scan;
    }

    public static void runAddKuchen() throws IllegalStateException {
            try {
                System.out.println("KuchenType (0-->Kremkuchen, 1-->Obstkuchen, 2-->Obsttorte) : ");
                KuchenTyp typ = KuchenTyp.values()[Integer.parseInt(myKuchenType.nextLine())];

                System.out.println("Hersteller: ");
                String hersteller = myHersteller.nextLine();  // Read user input
                HerstellerImp h = new HerstellerImp(hersteller);

                System.out.println("Price: ");
                String doublePrice = myPrice.nextLine();  // Read user input
                BigDecimal price = new BigDecimal(doublePrice);

                Collection<Allergen> allergen = new LinkedList<>();

                System.out.println("Allergen: ");
                String allergenInput = myAllergen.next();  // Read user input
                allergen.add(Allergen.valueOf(allergenInput));

                System.out.println("Naehrwert: ");
                int naehrwert = myNaehrwert.nextInt();  // Read user input

                System.out.println("Duration: ");
                Duration duration = Duration.ofDays(Integer.parseInt(myDuration.nextLine()));

                System.out.println("Topping1: ");
                String topping1 = myTopping1.nextLine();  // Read user input

                System.out.println("Topping2: ");
                String topping2 = myTopping2.nextLine();  // Read user input

                kv.create(typ, h, price, allergen, naehrwert, duration, topping1, topping2);
               hv.create(hersteller);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

    }


    public static void showHersteller() {
        for(int i=0; i< hv.read().length; i++ ){
            System.out.println(hv.read()[i] );
        }
    }

    public static void showKuchen() {
        for(int i=0; i< kv.read().length; i++ ){
            System.out.println(kv.read()[i] );
        }
    }


    public static void runShow(){
        showHersteller();
        showKuchen();
        //showAllergene
    }




    public static void rundDeleteKuchen(){
        Scanner scan = getScanner();
        while (true) {
            try{
                System.out.print("[Fachnummer] delete a Kuchen with the given Fachnummer:");
                int fachnummer = scan.nextInt();
                kv.delete(fachnummer);
                break; }
            catch(IllegalArgumentException e){
                System.out.println("Enter valid Fachnummer (int!!):");
            }
        }
    }

    public static void runUpdateKuchen(){
        Scanner scan = getScanner();
        System.out.print("[Fachnummer] Enter the Fachnummer of the Kuchen that you want to update:");
        kv.update(scan.nextInt());
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
                       /* ConsoleImp.runAddHersteller();
                        System.err.println("Hersteller inserted"); */
                        ConsoleImp.runAddKuchen();
                        System.out.println("Kuchen inserted");
                    }
                    case ":d" -> {
                        ConsoleImp.rundDeleteKuchen();
                        System.out.println("Kuchen deleted");
                    }
                    case ":u" -> {
                        ConsoleImp.runUpdateKuchen();
                        System.out.println("Kuchen up to date");
                    }
                    case ":r" -> ConsoleImp.runShow();
                }
            } while (true);
        }
    }




}