package thread.simulation3;


import domainLogic.HerstellerImp;
import domainLogic.KuchenImp;
import domainLogic.KuchenVerwaltung;

import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class Create3Thread extends Thread{


    static KuchenVerwaltung kv = new KuchenVerwaltung();
    private int capacity1;

    public Create3Thread(KuchenVerwaltung kv, int capacity1) {
        this.kv = kv;
        this.capacity1 = capacity1;
    }

    public KuchenImp randomKuchen() {
        Random random = new Random();
        //(HerstellerImp hersteller, Collection<Allergen> allergen, int naehrwert, Duration haltbarkeit, BigDecimal preis, Date inspektionsdatum, int fachnummer)
        List<Allergen> a1 = new LinkedList<>(Collections.singleton(Allergen.Erdnuss));
        //generating random value from Kuchentyp enum
        KuchenTyp[] values0 = KuchenTyp.values();              // source: https://dirask.com/posts/Java-get-random-element-from-enum-VDK8np
        int length0 = values0.length;
        int randIndex0 = new Random().nextInt(length0);
        // KuchenTyp randomKuchentyp = values0[randIndex0];  // TODO: benuze ich spaeter in der Abgabe
        int randomNaehrwert = random.nextInt(10) + 1; //random naehrwert from 1 to 10
        //source: https://stackoverflow.com/questions/66786965/how-can-i-turn-an-int-minutes-into-a-duration-in-java
        int myDays = random.nextInt(15) + 1;
        Duration durationInDays = Duration.ofDays(myDays);           // random haltbarkeit
        double myPrices = random.nextDouble(10) + 1; //random price from 1 to 10
        BigDecimal randomPrice = new BigDecimal(myPrices);
        int x = 0;
        KuchenImp randomKuchen = new KuchenImp(new HerstellerImp("h1"), a1, randomNaehrwert, durationInDays, randomPrice, new Date(), x);
        return randomKuchen;
    }


    @Override
    public synchronized void run() {
        while (true) {
            synchronized (kv) {
                try {
                    kv.create(randomKuchen());
                    System.err.println("Created Kuchen");
                    if (kv.read().length == capacity1) {
                        kv.wait();
                    }
                } catch (InterruptedException e) {
                }

            }
        }

    }





}