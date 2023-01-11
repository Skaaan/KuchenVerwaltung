package thread.simulation3;

import domainLogic.KuchenVerwaltung;
import thread.simulation2.Create2Thread;
import thread.simulation2.Delete2Thread;
import thread.simulation2.UpdateThread;



public class ExecuteSimulation3 {

    static KuchenVerwaltung kv = new KuchenVerwaltung();
    static int capacity = 5;

    public static void main(String[] args) {

        Create3Thread myThread1 = new Create3Thread(kv, capacity);
        UpdateThread upThread2 = new UpdateThread(kv);
        Delete3Thread myThread2 = new Delete3Thread(kv);

        myThread1.start();
        myThread1.start();
        myThread2.start();
        myThread2.start();
        upThread2.start();
        upThread2.start();


    }


}
