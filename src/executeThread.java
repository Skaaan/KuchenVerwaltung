import domainLogic.KuchenVerwaltung;
import thread.createThread;
import thread.deleteThread;

public class executeThread {
     static KuchenVerwaltung kv = new KuchenVerwaltung();

    public static void main(String[] args) {

        createThread myThread1 = new createThread(kv);
        myThread1.start();

        deleteThread myThread2 = new deleteThread(kv);
        myThread2.start();
    }



}
