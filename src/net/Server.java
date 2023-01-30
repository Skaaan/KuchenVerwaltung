package net;

import domainLogic.hersteller.HerstellerImp;
import domainLogic.kuchen.KuchenImp;
import domainLogic.kuchen.KuchenVerwaltung;
import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

//Source: https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip


public class Server {

    public void start(int port) throws IOException {
        System.out.println("Server started on port:" + port );
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message;

        KuchenVerwaltung kv = new KuchenVerwaltung();



        // Create a Scanner object
        Scanner myKuchenType = new Scanner(System.in);
        Scanner myHersteller = new Scanner(System.in);
        Scanner myPrice = new Scanner(System.in);
        Scanner myNaehrwert = new Scanner(System.in);
        Scanner myDuration = new Scanner(System.in);
        Scanner myTopping1 = new Scanner(System.in);
        Scanner myTopping2 = new Scanner(System.in);


        while ((message = in.readLine()) != null) {

            if(message.equals(":c")) {
                System.out.println("KuchenType (0-->Kremkuchen, 1-->Kremkuchen-->Obstkuchen, 2-->Obsttorte) : ");
                KuchenTyp typ = KuchenTyp.values()[Integer.parseInt(myKuchenType.nextLine())];

                System.out.println("Hersteller: ");
                String hersteller = myHersteller.nextLine();  // Read user input
                HerstellerImp h = new HerstellerImp(hersteller);

                System.out.println("Price: ");
                String doublePrice = myPrice.nextLine();  // Read user input
                BigDecimal price = new BigDecimal(doublePrice);

                Collection<Allergen> allergen = new LinkedList<>();
                Scanner myAllergen = new Scanner(System.in);  // Create a Scanner object
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

                out.println("Kuchen added ");
            } else if(message.equals(":d")) {
                kv.deleteKuchen(1);
                out.println("Kuchen deleted ");
            }else if(message.equals(":r")) {
                for (KuchenImp element : kv.readArrayOfKuchen()) {
                    System.out.println(element.toString() );
                }
            }  else if(message.equals(":u"))  {
                    kv.update(1);
                    out.println("updated ");
            }

                    }

            }

            }









