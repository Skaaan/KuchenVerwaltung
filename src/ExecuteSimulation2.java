import domainLogic.kuchen.KuchenVerwaltung;
import thread.simulation2.Create2Thread;
import thread.simulation2.Delete2Thread;
import thread.simulation2.UpdateThread;

public class ExecuteSimulation2 {

    static int capacity = 10;
    static KuchenVerwaltung kv = new KuchenVerwaltung();


    public static void main(String[] args) {

        Create2Thread myThread1 = new Create2Thread(kv);
        UpdateThread upThread2 = new UpdateThread(kv);
        Delete2Thread myThread2 = new Delete2Thread(kv);

        myThread1.start();
        upThread2.start();
        myThread2.start();

    }



}
