import CLI.ConsoleImp;
import IO.jos.SaveAndLoadJOS;
import domainLogic.Automat;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;





public class ExecuteCLI {



    public static void main(String[] args) {
        HerstellerVerwaltung hv = new HerstellerVerwaltung();
        KuchenVerwaltung kv = new KuchenVerwaltung();

        Automat automat = new Automat(kv, hv);
        SaveAndLoadJOS saveAndLoadJOS = new SaveAndLoadJOS(automat);

        ConsoleImp consoleImp = new ConsoleImp(automat, saveAndLoadJOS);
        consoleImp.execute();
    }
}









