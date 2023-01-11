package thread.simulation1;

import domainLogic.KuchenImp;
import domainLogic.KuchenVerwaltung;

public class Delete1Thread extends Thread{

    KuchenVerwaltung kv;

    public Delete1Thread(KuchenVerwaltung kv){
        this.kv=kv;
    }




    @Override
    public  void run() {
            while (true) {
                synchronized(kv){
                    if (kv.read().length != 0) {
                    int randomIndex = (int) ((Math.random() * kv.read().length));  // generating random index from the list, Source: https://www.baeldung.com/java-random-list-element   //* kv.read().length :random int between 0 and array length
                    kv.delete(randomIndex);
                    System.err.println("Deleted Kuchen");
                }
                }
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                }
            }
        }



}