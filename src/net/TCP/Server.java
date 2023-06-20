package net.TCP;

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





public class Server {


    private final Scanner scan= new Scanner(System.in);

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
                    String addInput = scan.nextLine();
                    String[] s = addInput.split(" ");     //keine leerzeichen keine commands.
                    if (s.length == 1) {
                        try {
                            consoleImp.runAddHersteller(addInput);
                        }catch (IllegalArgumentException e){
                            out.println("Hersteller cant contain only numbers or only empty spaces ");
                        }
                    } else {
                        consoleImp.runAddKuchen(addInput);
                    }
                }
                case ":d" -> {
                    String addInput = scan.nextLine();
                    if(!automat.isNotOnlyNumbers(addInput)) {
                        consoleImp.runDeleteKuchen(  Integer.parseInt(addInput) );
                        out.println("Kuchen deleted");
                    } else if(automat.isNotOnlyNumbers(addInput) ) {
                        consoleImp.runDeleteHersteller( addInput );
                        out.println("Hersteller deleted");
                    }

                }
                case ":r" -> {
                    String addInput = scan.nextLine();

                    if (addInput.equals("hersteller")) {
                        consoleImp.runShowHerstellerWithKuchen();
                        out.println("list of Hersteller with Number of Kuchen read");

                    } else if (addInput.equals("kuchen Kremkuchen")){
                        consoleImp.runShowKuchenWithType(Kremkuchen);
                        out.println("list of Kuchen filtered by Type Kremkuchen read");
                    } else if (addInput.equals("kuchen Obstkuchen")){
                        consoleImp.runShowKuchenWithType(Obstkuchen);
                        out.println("list of Kuchen filtered by Type Obstkuchen read");
                    } else if (addInput.equals("kuchen Obsttorte")){
                        consoleImp.runShowKuchenWithType(Obsttorte);
                        out.println("list of Kuchen filtered by Type Obsttorte read");
                    } else if (addInput.equals("allergene i")){
                        consoleImp.runShowAllergen(true);
                    }  else if (addInput.equals("allergene e")){
                        consoleImp. runShowAllergen(true);
                    }

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
