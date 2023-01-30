import domainLogic.kuchen.KuchenVerwaltung;
import thread.simulation2.UpdateThread;
import thread.simulation3.Create3Thread;
import thread.simulation3.Delete3Thread;


public class ExecuteSimulation3 {
     int capacity = 5;
     KuchenVerwaltung kv = new KuchenVerwaltung();


    public void main(String[] args) {

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
