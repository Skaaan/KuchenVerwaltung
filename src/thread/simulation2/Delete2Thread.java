package thread.simulation2;

import domainLogic.Automat;
import domainLogic.kuchen.KuchenVerwaltung;
import domainLogic.kuchen.KuchenImp;

public class Delete2Thread extends Thread {

    Automat automat;

    public Delete2Thread(Automat automat){
        this.automat=automat;
    }



    private KuchenImp getOldestKuchen(Automat automat) {
        KuchenImp oldestKuchen = null;
        for(int i = 0; i < automat.readArrayOfKuchen().length; i++) {
            if (oldestKuchen.getInspektionsdatum().compareTo(automat.readArrayOfKuchen()[i].getInspektionsdatum()) > 0) {
                oldestKuchen = automat.readArrayOfKuchen()[i];
            }
        }
        return oldestKuchen;
    }




    @Override
    public void run() {
        while (true) {
            synchronized (automat) {
                if (automat.readArrayOfKuchen().length == 0) {
                    try {
                        System.out.println("Delete Kuchen wait for Create Kuchen");
                        automat.wait();

                    } catch (InterruptedException e) {
                    }
                } else {
                    automat.deleteKuchen( getOldestKuchen(automat).getFachnummer() );
                    System.out.println(" Kuchen deleted: " );
                    automat.notify();

                }
            }
        }
    }












}