import domainLogic.Automat;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;
import thread.simulation1.Create1Thread;
import thread.simulation1.Delete1Thread;

public class ExecuteSimulation1 {
     static KuchenVerwaltung kv = new KuchenVerwaltung();
    static HerstellerVerwaltung hv = new HerstellerVerwaltung();

    static Automat automat = new Automat(kv, hv);


    public static void main(String[] args) {
        Create1Thread myThread1 = new Create1Thread(automat);
        myThread1.start();

        Delete1Thread myThread2 = new Delete1Thread(automat);
        myThread2.start();
    }



}
