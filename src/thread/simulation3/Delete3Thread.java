package thread.simulation3;

import domainLogic.KuchenImp;
import domainLogic.KuchenVerwaltung;

import java.util.Random;

public class Delete3Thread extends Thread{


        KuchenVerwaltung kv;

        public Delete3Thread(KuchenVerwaltung kv) {
            this.kv = kv;
        }

        private int randomInt() {
            Random randomNum = new Random();
            int randomInt = randomNum.nextInt(kv.read().length);
            return randomInt;
        }


        @Override
        public void run() {
            while (true) {
                synchronized (kv) {
                    if (kv.read().length != 0) {
                        int oldesKuchenFachnummer = getOldestKuchen(kv).getFachnummer();
                        for(int i=0; i< kv.read().length; i++) {
                            kv.delete(oldesKuchenFachnummer);
                            System.err.println("Deleted oldest Kuchen with Fachnummer: " + oldesKuchenFachnummer + "!");
                        }
                    }
                    notify();
                }
            }
        }


        private KuchenImp getOldestKuchen(KuchenVerwaltung kv) {
            KuchenImp oldestKuchen = kv.read()[0];
            for (int i = 0; i < kv.read().length; i++) {
                if (oldestKuchen.getInspektionsdatum().compareTo(kv.read()[i].getInspektionsdatum()) > 0) {
                    oldestKuchen = kv.read()[i];
                }
            }
            return oldestKuchen;
        }

    }

