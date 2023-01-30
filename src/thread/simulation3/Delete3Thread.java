package thread.simulation3;

import domainLogic.kuchen.KuchenVerwaltung;
import domainLogic.kuchen.KuchenImp;

import java.util.Random;

public class Delete3Thread extends Thread{


        KuchenVerwaltung kv;

        public Delete3Thread(KuchenVerwaltung kv) {
            this.kv = kv;
        }

        private int randomInt() {
            Random randomNum = new Random();
            int randomInt = randomNum.nextInt(kv.readArrayOfKuchen().length);
            return randomInt;
        }


        @Override
        public void run() {
            while (true) {
                synchronized (kv) {
                    if (kv.readArrayOfKuchen().length != 0) {
                        int oldesKuchenFachnummer = getOldestKuchen(kv).getFachnummer();
                        for(int i = 0; i< kv.readArrayOfKuchen().length; i++) {
                            kv.deleteKuchen(oldesKuchenFachnummer);
                            System.err.println("Deleted oldest Kuchen with Fachnummer: " + oldesKuchenFachnummer + "!");
                        }
                    }
                    notify();
                }
            }
        }


        private KuchenImp getOldestKuchen(KuchenVerwaltung kv) {
            KuchenImp oldestKuchen = kv.readArrayOfKuchen()[0];
            for (int i = 0; i < kv.readArrayOfKuchen().length; i++) {
                if (oldestKuchen.getInspektionsdatum().compareTo(kv.readArrayOfKuchen()[i].getInspektionsdatum()) > 0) {
                    oldestKuchen = kv.readArrayOfKuchen()[i];
                }
            }
            return oldestKuchen;
        }

    }

