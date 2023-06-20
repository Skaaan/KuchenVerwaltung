package thread.simulation2;


import domainLogic.Automat;
import domainLogic.hersteller.HerstellerImp;
import domainLogic.kuchen.KuchenVerwaltung;
import domainLogic.kuchen.KuchenImp;

import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class Create2Thread  extends Thread {


    Automat automat;


    public Create2Thread(Automat automat) {
        this.automat = automat;
    }

    public KuchenImp randomKuchen() {
        Random random = new Random();
        //generating random kuchenType
        KuchenTyp[] values0 = KuchenTyp.values();
        int length0 = values0.length;
        int randIndex0 = new Random().nextInt(length0);
        KuchenTyp randomKuchentyp = values0[randIndex0];
        //(HerstellerImp hersteller, Collection<Allergen> allergen, int naehrwert, Duration haltbarkeit, BigDecimal preis, Date inspektionsdatum, int fachnummer)
        List<Allergen> a1 = new LinkedList<>(Collections.singleton(Allergen.Erdnuss));
        int randomNaehrwert = random.nextInt(10) + 1; //random naehrwert from 1 to 10
        int myDays = random.nextInt(15) + 1;
        Duration durationInDays = Duration.ofDays(myDays);           // random haltbarkeit
        double myPrices = random.nextDouble(10) + 1; //random price from 1 to 10
        BigDecimal randomPrice = new BigDecimal(myPrices);
        int x = 0;
        KuchenImp randomKuchen = new KuchenImp(randomKuchentyp, new HerstellerImp("h1"), randomPrice, a1, randomNaehrwert, durationInDays, new Date(), x);
        return randomKuchen;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (automat) {
                if (automat.readArrayOfKuchen().length >= automat.getDefaultCapacity()) {
                    System.err.println("automat is full wait for kuchen delete");
                    try {
                        automat.wait();
                    } catch (InterruptedException e) {
                    }
                } else {
                    automat.create(randomKuchen());
                    System.err.println("Created Kuchen");
                }
            }
        }
    }



}

