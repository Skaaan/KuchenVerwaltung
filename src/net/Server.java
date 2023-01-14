package net;

import CLI.Console;
import domainLogic.HerstellerImp;
import domainLogic.KremkuchenImp;
import domainLogic.KuchenImp;
import domainLogic.KuchenVerwaltung;
import domainLogic.HerstellerVerwaltung;

import vertrag.Allergen;
import vertrag.Hersteller;
import vertrag.KuchenTyp;

import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.util.*;

import static vertrag.KuchenTyp.Kremkuchen;

//Source: https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip


public class Server {

    public void start(int port) throws IOException {
        System.out.println("Server started on port:" + port );
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message;

        KuchenVerwaltung command = new KuchenVerwaltung();
        BigDecimal p1 = new BigDecimal(2.5);


        // Create a Scanner object
        Scanner myKuchenType = new Scanner(System.in);
        Scanner myHersteller = new Scanner(System.in);
        Scanner myPrice = new Scanner(System.in);
        Scanner myNaehrwert = new Scanner(System.in);
        Scanner myDuration = new Scanner(System.in);
        Scanner myTopping1 = new Scanner(System.in);
        Scanner myTopping2 = new Scanner(System.in);


        while ((message = in.readLine()) != null) {
            switch (message) {
                case ":c":

                    System.out.println("KuchenType (0-->Kremkuchen, 1-->Kremkuchen-->Obstkuchen, 2-->Obsttorte) : ");
                    KuchenTyp typ = KuchenTyp.values()[Integer.parseInt(myKuchenType.nextLine())];


                    System.out.println("Hersteller: ");
                    String hersteller = myHersteller.nextLine();  // Read user input
                    HerstellerImp h = new HerstellerImp(hersteller);


                    System.out.println("Price: ");
                    BigDecimal price = myPrice.nextBigDecimal();  // Read user input


                    Collection<Allergen> allergen = new LinkedList<>();
                    Scanner myAllergen = new Scanner(System.in);  // Create a Scanner object
                    System.out.println("Allergen: ");
                    String allergenInput = myAllergen.next();  // Read user input
                    allergen.add(Allergen.valueOf(allergenInput));


                    System.out.println("Naehrwert: ");
                    int naehrwert = myNaehrwert.nextInt();  // Read user input


                    System.out.println("Duration: ");
                    Duration duration = Duration.ofDays(       Integer.parseInt( myDuration.nextLine()    ) ) ;


                    System.out.println("Topping1: ");
                    String topping1 = myTopping1.nextLine();  // Read user input


                    System.out.println("Topping2: ");
                    String topping2 = myTopping2.nextLine();  // Read user input

                    command.create(typ, h, p1, allergen, naehrwert, duration, topping1,  topping2);


                    out.println("Kuchen added ");
                    break;
                case ":d":
                    command.delete(1);
                    out.println("Kuchen deleted ");
                    break;
                case ":u":
                    command.update(1);
                    out.println("updated ");
                    break;
                case ":r":

                    for(int i=0; i<command.read().length; i++) {
                        out.println(command.read()[i]);
                    }

                    break;


            }
        }
    }
}



/*
public class Server {



    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message;
        KuchenVerwaltung kv = new KuchenVerwaltung();
        Collection<Allergen> a1 = new LinkedList<Allergen>(Collections.singleton(Allergen.Erdnuss));
        Duration d1 = Duration.ofDays(1);
        BigDecimal p1 = new BigDecimal("1.5");
        HerstellerImp h1 = new HerstellerImp("h1");

        KuchenImp kuchen = new KremkuchenImp(h1, a1, 4, d1, p1, new Date(), 0, "NugatKrem");


        while ((message = in.readLine()) != null) {
            System.out.println("enter Command");
            System.out.println("Received Command: " + message);
            switch (message) {
                case ":c":
                    kv.create(kuchen);
                    kv.create(kuchen);
                    kv.create(kuchen);
                    out.println("Kuchen " + kuchen + " added ");
                    break;
                case ":d":
                    kv.delete(0);
                    out.println("Kuchen " + kuchen + " deleted ");
                    break;
                case ":u":
                    kv.update(1);
                    out.println("Kuchen " + kuchen + " updated ");
                    break;
                case ":r":
                    for (int i = 0; i < kv.read().length; i++) {
                        out.println(kv.read()[i]);
                    }
                    break;
            }

        }

    }
}

 */








       /* while ( (message = in.readLine() )  != null) {
            System.out.println("enter Command");
            System.out.println("Received Command: " + message);
                switch (message) {
                    case ":c":
                        kv.create(kuchen);
                        kv.create(kuchen);
                        kv.create(kuchen);
                        out.println("Kuchen " + kuchen + " added ");
                        break;
                    case ":d":
                        kv.delete(0);
                        out.println("Kuchen " + kuchen + " deleted ");
                        break;
                    case ":u":
                        kv.update(1);
                        out.println("Kuchen " + kuchen + " updated ");
                        break;
                    case ":r":
                        for(int i=0; i< kv.read().length; i++ ) {
                            out.println(kv.read()[i] );
                        }
                        break;
                }

        */



