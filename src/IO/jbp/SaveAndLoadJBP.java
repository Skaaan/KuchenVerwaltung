package IO.jbp;

import domainLogic.kuchen.KuchenImp;
import domainLogic.kuchen.KuchenVerwaltung;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

public class SaveAndLoadJBP { //TODO: BEAN SCHREIBEN


    private static final String filename1 ="src/IO/jbp/beanItems.xml";

    //save
    public static void write(KuchenVerwaltung kv, String filename) {
        try(XMLEncoder encoder=new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(filename))) ){
            encoder.writeObject(kv.getListOfKuchen());
            System.err.println("\nSavingJBP Successful... Checkout your specified output file..\n");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //load
    public static List<KuchenImp> read(String filename) throws Exception {
        List<KuchenImp> loadedList = null;
        try (XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(
                new FileInputStream(filename))) ){
            loadedList= (List<KuchenImp>) decoder.readObject();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return loadedList;
    }

    public static void saveKuchenVerwaltungJBP(KuchenVerwaltung kv) {
        write(kv, filename1);
    }

    public static List<KuchenImp> loadKuchenVerwaltungJBP() throws Exception {
        return read(filename1);
    }


}