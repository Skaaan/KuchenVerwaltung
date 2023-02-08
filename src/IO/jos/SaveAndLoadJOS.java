package IO.jos;


import domainLogic.Automat;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;



import java.io.*;



public class SaveAndLoadJOS {


     Automat automat;

    public SaveAndLoadJOS(Automat automat) {
        this.automat = automat;
    }

    private static final String FILENAME = "src/IO/jos/savedKuchenVerwaltung.txt";

    public void serialize(OutputStream out, Automat automat){
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject(automat);
            objectOut.close();
            System.err.println("\nSavingJOS Successful... Checkout your specified output file..\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  Automat deserialize(InputStream in){
        Automat automat = null;
        try {
            ObjectInputStream objectIn = new ObjectInputStream(in);
            automat = (Automat) objectIn.readObject();
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return automat;
    }

    public void saveAutomatJOS(Automat automat) {
        try {
            FileOutputStream fileOut = new FileOutputStream(FILENAME);
            serialize(fileOut, automat);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Automat loadAutomatJOS() {
        try {
            FileInputStream fileIn = new FileInputStream(FILENAME);
            return deserialize(fileIn);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}