import domainLogic.KuchenVerwaltung;
import thread.simulation1.Create1Thread;
import thread.simulation1.Delete1Thread;

public class ExecuteSimulation1 {
     static KuchenVerwaltung kv = new KuchenVerwaltung();

    public static void main(String[] args) {

        Create1Thread myThread1 = new Create1Thread(kv);
        myThread1.start();

        Delete1Thread myThread2 = new Delete1Thread(kv);
        myThread2.start();
    }



}
