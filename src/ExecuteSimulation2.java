import domainLogic.Automat;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;
import thread.simulation2.Create2Thread;
import thread.simulation2.Delete2Thread;
import thread.simulation2.UpdateThread;

public class ExecuteSimulation2 {

    static KuchenVerwaltung kv = new KuchenVerwaltung();
    static HerstellerVerwaltung hv = new HerstellerVerwaltung();

    static Automat automat = new Automat(kv, hv);


    public static void main(String[] args) {
        automat.setDefaultCapacity(100);

        Create2Thread myThread1 = new Create2Thread(automat);
        UpdateThread upThread2 = new UpdateThread(automat);
        Delete2Thread myThread2 = new Delete2Thread(automat);

        myThread1.run();
        upThread2.run();
        myThread2.run();

    }



}
