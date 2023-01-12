package net;

import CLI.Console;
import domainLogic.HerstellerImp;
import domainLogic.KremkuchenImp;
import domainLogic.KuchenImp;
import domainLogic.KuchenVerwaltung;
import domainLogic.HerstellerVerwaltung;

import vertrag.Allergen;

import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.util.*;

public class TcpServer {
    //Source: https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip


    KuchenVerwaltung v = new KuchenVerwaltung();
    private final Collection<Allergen> a1 = new LinkedList<Allergen>(Collections.singleton(Allergen.Erdnuss));
    private Duration d1 = Duration.ofDays(1);
    private BigDecimal p1 = new BigDecimal("1.5");
    HerstellerImp h1 = new HerstellerImp("h1");

    KuchenImp addedKuchen = new KremkuchenImp(h1, a1, 4, d1, p1, new Date() ,0,"NugatKrem" );







    public void main() throws IOException {


//----------------------


        //---------------------------------


        int port = 8888;
        ServerSocket serverSocket = new ServerSocket(port); //verbindet server mit client
        System.out.println("Server started on port " + port);
        Socket socket = serverSocket.accept();
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //sendet nachricht between server & client, printwriter in server sendet nachricht from server to client
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // yesta9bel nachricht from client
         String message  ;


         KuchenVerwaltung kv = new KuchenVerwaltung();
         Collection<Allergen> a = new LinkedList();
         Duration d1 = Duration.ofHours(2);
         BigDecimal b1 = new BigDecimal(2.5);
         HerstellerImp h1 = new HerstellerImp("logo");
         Date date = new Date();
         ArrayList allergen = new ArrayList(Collections.singleton(Allergen.Gluten));

        KuchenImp kuchen = new KremkuchenImp(h1, a1, 4, d1, p1, new Date() ,0,"NugatKrem" );





        while ( (message = in.readLine() )  != null) {
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
            }
        }
    }

