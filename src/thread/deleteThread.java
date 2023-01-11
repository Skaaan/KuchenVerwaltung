package thread;

import domainLogic.KuchenImp;
import domainLogic.KuchenVerwaltung;

public class deleteThread extends Thread{

    KuchenVerwaltung kv;

    public deleteThread(KuchenVerwaltung kv){
        this.kv=kv;
    }




    @Override
    public  void run() {
            while (true) {
                synchronized(kv){
                    if (kv.read().length != 0) {    // generating random index from the list, Source: https://www.baeldung.com/java-random-list-element
                    int randomIndex = (int) ((Math.random() * kv.read().length));    //* kv.read().length :random int between 0 and array length
                    kv.delete(randomIndex);
                    System.err.println("Deleted Kuchen");
                }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }



}