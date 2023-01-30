package IO.jos;


import domainLogic.Automat;


import java.io.*;


public class SaveAndLoadJOS {
// Source: https://crunchify.com/how-to-serialize-deserialize-list-of-objects-in-java-java-serialization-example/


    private static final String filename = "src/IO/jos/savedKuchenVerwaltung.txt";


//todo
    // signature use stream anstatt filename , for tests, so kannst du keine tests schreiben
    public static void serialize(String filename, Automat automat){
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(automat);
            out.close();
            fileOut.close();
            System.err.println("\nSavingJOS Successful... Checkout your specified output file..\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Automat deserialize(String filename){
        Automat automat = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            automat = (Automat) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return automat;
    }

    public static void saveAutomatJOS(Automat automat) {
        serialize(filename, automat);
    }

    public static Automat loadAutomatJOS() {
         return deserialize(filename);
    }




}
