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





public class ConsoleImp implements Console {   // This class is for managing inputs with scanners, and catching exceptions


    private static final List<Allergen> a1 = new LinkedList<>(Collections.singleton(Allergen.Erdnuss));
    private static final Duration d1 = Duration.ofDays(1);
    private static final BigDecimal p1 =new BigDecimal("1.5");
    private static final HerstellerImp h1 = new HerstellerImp("Skander");
    static KremkuchenImp  kuchen = new KremkuchenImp(h1, a1, 3, d1, p1, new Date(), 0, "NugatKrem"); // vordefiniertes Kuchen


    private static final Scanner scan= new Scanner(System.in);

    public static Scanner getScanner(){
        return scan;
    }




    public static void runAddHersteller(){
        Scanner scan = getScanner();
        System.err.print("[Herstellername] Enter a Hersteller: ");
        hv.create(scan.nextLine());
    }



    // runAddKuchen uses a vordefiniertes kuchen just for CLI prototype.
    public static void runAddKuchen(){
        kv.create(kuchen);
    }



    // runAddKuchen1 for later Oprations in the Beleg
    public static void runAddKuchen1(){
        Scanner scan = getScanner();
        while(true) {
            try {
                System.out.print("[Kuchen-Typ] [Herstellername] [Preis] [Naehrwert] [Haltbarkeit] [kommaseparierte Allergene, einzelnes" + "Komma für keine] [[Obstsorte]] [[Kremsorte]] Enter a Kuchen: ");
                String[] s = scan.nextLine().split(" ");

                KuchenTyp kuchentyp = KuchenTyp.valueOf(s[0]);
                HerstellerImp hersteller = new HerstellerImp(s[1]);
                BigDecimal price = BigDecimal.valueOf(Long.parseLong(s[2]));
                int naehrwert = parseInt(s[3]);
                Duration haltbarkeit = Duration.parse(s[4]);
                Set<Allergen> allergens = Collections.singleton(Allergen.valueOf(s[5]));
                String topping0 = valueOf(s[6]);
                String topping1 = valueOf(s[7]);


                kv.create(kuchentyp, hersteller, price, allergens, naehrwert, haltbarkeit, topping0, topping1);
                break; }
            catch(IllegalArgumentException e){
                System.out.println("Enter valid Kuchen");
            }
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
                System.out.println("Enter valid Fachnummer (int):");
            }
        }
    }

    public static void runUpdateKuchen(){
        Scanner scan = getScanner();
        System.out.print("[Fachnummer] Enter the Fachnummer of the Kuchen that you want to update:");
        kv.update(scan.nextInt());
    }


    public static void execute() {
        System.err.println("""
                     :c Wechsel in den Einfügemodus
                     :d Wechsel in den Löschmodus
                     :r Wechsel in den Anzeigemodus
                     :u Wechsel in den Änderungsmodus
                     :p Wechsel in den Persistenzmodus
                    """);
        try(Scanner scan = new Scanner(System.in)) {
            do {
                System.err.println("enter Command");
                String token = scan.next();
                switch (token) {
                    case ":c" -> {
                        ConsoleImp.runAddHersteller();
                        System.err.println("Hersteller inserted");
                        ConsoleImp.runAddKuchen();
                        System.err.println("Kuchen inserted");
                    }
                    case ":d" -> {
                        ConsoleImp.rundDeleteKuchen();
                        System.err.println("Kuchen deleted");
                    }
                    case ":u" -> {
                        ConsoleImp.runUpdateKuchen();
                        System.err.println("Kuchen up to date");
                    }
                    case ":r" -> ConsoleImp.runShow();
                }
            } while (true);
        }
    }




}