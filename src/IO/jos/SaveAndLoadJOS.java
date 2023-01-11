package IO.jos;


import domainLogic.KuchenVerwaltung;
import java.io.*;


public class SaveAndLoadJOS {
// Source: https://crunchify.com/how-to-serialize-deserialize-list-of-objects-in-java-java-serialization-example/


    private static final String filename = "src/IO/jos/savedKuchenVerwaltung.txt";



    public static void serialize(String filename, KuchenVerwaltung obj){
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.err.println("\nSavingJOS Successful... Checkout your specified output file..\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static KuchenVerwaltung deserialize(String filename){
        KuchenVerwaltung kv = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            kv = (KuchenVerwaltung)in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return kv;
    }

    public static void saveKuchenVerwaltungJOS(KuchenVerwaltung kv) {
        serialize(filename, kv);
    }

    public static KuchenVerwaltung loadKuchenVerwaltungJOS() {
         return deserialize(filename);
    }



}
