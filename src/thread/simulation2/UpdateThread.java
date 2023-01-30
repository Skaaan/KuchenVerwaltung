package thread.simulation2;

import domainLogic.kuchen.KuchenVerwaltung;

public class UpdateThread extends Thread{

    public UpdateThread(KuchenVerwaltung kv){
        this.kv=kv;
    }


    KuchenVerwaltung kv;


    @Override
    public  void run() {
        while (true) {
            synchronized(kv){
                if (kv.readArrayOfKuchen().length != 0) {
                    int randomIndex = (int) ((Math.random() * kv.readArrayOfKuchen().length));  // generating random index from the list, Source: https://www.baeldung.com/java-random-list-element   //* kv.read().length :random int between 0 and array length
                    kv.update(randomIndex);
                    System.err.println("updated Kuchen");
                }
            }
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
            }
        }
    }





}
