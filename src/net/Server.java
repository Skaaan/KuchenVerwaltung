package net;

import CLI.ConsoleImp;
import IO.jos.SaveAndLoadJOS;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;
import domainLogic.Automat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static vertrag.KuchenTyp.*;

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
        HerstellerVerwaltung hv = new HerstellerVerwaltung();
        Automat automat = new Automat(kv, hv);
        SaveAndLoadJOS saveAndLoadJOS = new SaveAndLoadJOS(automat);
        ConsoleImp consoleImp = new ConsoleImp(automat, saveAndLoadJOS);




        while ((message = in.readLine()) != null) {

            switch (message) {
                case ":c" -> {
                    consoleImp.runAddHersteller();
                    consoleImp.runAddKuchen();
                    out.println("Hersteller created and Kuchen created");
                }
                case ":d" -> {
                    consoleImp.runDeleteHersteller();
                    consoleImp.runDeleteKuchen();
                    out.println("Hersteller deleted and Kuchen deleted");
                }
                case ":r" -> {
                    consoleImp.runShowHerstellerWithKuchen();
                    consoleImp.runShowKuchenWithType();
                    consoleImp.runShowAllergen();
                    out.println("list of Hersteller with Number of Kuchen read, list of Kuchen filtered by Type read and list of available and not available Allergens read");
                }
                case ":u" -> {
                    consoleImp.runUpdateKuchen();
                    out.println("Kuchen updated");
                }
                case ":p" -> {
                    consoleImp.runPersistenzmodus();
                    out.println("persistence mode ran");
                }
            }

        }

    }

}
