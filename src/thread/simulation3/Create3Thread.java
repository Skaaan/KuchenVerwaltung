package thread.simulation3;


import domainLogic.hersteller.HerstellerImp;
import domainLogic.kuchen.KuchenVerwaltung;
import domainLogic.kuchen.KuchenImp;

import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class Create3Thread extends Thread{

    private int capacity1;
     KuchenVerwaltung kv = new KuchenVerwaltung();


    public Create3Thread(KuchenVerwaltung kv, int capacity1) {
        this.kv = kv;
        this.capacity1 = capacity1;
    }

    public KuchenImp randomKuchen() {
        Random random = new Random();
        //generating random kuchenType
        KuchenTyp[] values0 = KuchenTyp.values();              // source: https://dirask.com/posts/Java-get-random-element-from-enum-VDK8np
        int length0 = values0.length;
        int randIndex0 = new Random().nextInt(length0);
        KuchenTyp randomKuchentyp = values0[randIndex0];
        //(HerstellerImp hersteller, Collection<Allergen> allergen, int naehrwert, Duration haltbarkeit, BigDecimal preis, Date inspektionsdatum, int fachnummer)
        List<Allergen> a1 = new LinkedList<>(Collections.singleton(Allergen.Erdnuss));
        int randomNaehrwert = random.nextInt(10) + 1; //random naehrwert from 1 to 10
        //source: https://stackoverflow.com/questions/66786965/how-can-i-turn-an-int-minutes-into-a-duration-in-java
        int myDays = random.nextInt(15) + 1;
        Duration durationInDays = Duration.ofDays(myDays);           // random haltbarkeit
        double myPrices = random.nextDouble(10) + 1; //random price from 1 to 10
        BigDecimal randomPrice = new BigDecimal(myPrices);
        //random fachnummer between 1 and the default capacity of the automat
        int randomFachnummer = random.nextInt(kv.getDefaultCapacity()  ) + 1;
        KuchenImp randomKuchen = new KuchenImp(randomKuchentyp, new HerstellerImp("h1"), randomPrice, a1, randomNaehrwert, durationInDays, new Date(), randomFachnummer  );
        return randomKuchen;
    }


    @Override
    public synchronized void run() {
        while (true) {
            synchronized (kv) {
                try {
                    kv.create(randomKuchen());
                    System.err.println("Created Kuchen");
                    if (kv.readArrayOfKuchen().length == capacity1) {
                        kv.wait();
                    }
                } catch (InterruptedException e) {
                }

            }
        }

    }





}