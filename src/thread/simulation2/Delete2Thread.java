package thread.simulation2;

import domainLogic.KuchenImp;
import domainLogic.KuchenVerwaltung;

public class Delete2Thread extends Thread{

    KuchenVerwaltung kv;

    public Delete2Thread(KuchenVerwaltung kv){
        this.kv=kv;
    }




    @Override
    public  void run() {
        while (true) {
            synchronized(kv){
                if (kv.read().length != 0) {
                  int oldesKuchenFachnummer = getOldestKuchen(kv).getFachnummer();
                    kv.delete(oldesKuchenFachnummer );
                    System.err.println("Deleted oldest Kuchen with Fachnummer: "+ oldesKuchenFachnummer +"!");
                }
                notify();
            }
        }
    }



    private KuchenImp getOldestKuchen(KuchenVerwaltung kv) {
        KuchenImp oldestKuchen = kv.read()[0];
        for(int i = 0; i < kv.read().length; i++) {
            if (oldestKuchen.getInspektionsdatum().compareTo(kv.read()[i].getInspektionsdatum()) > 0) {
                oldestKuchen = kv.read()[i];
            }
        }
        return oldestKuchen;
    }





}