package thread.simulation2;

import domainLogic.Automat;
import domainLogic.hersteller.HerstellerImp;
import domainLogic.kuchen.KuchenImp;
import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;


public class UpdateThread extends Thread {

    Automat automat;

    public UpdateThread(Automat automat){
        this.automat=automat;
    }


    public KuchenImp randomKuchen(){
        Random random = new Random();
        //generating random kuchenType
        KuchenTyp[] values0 = KuchenTyp.values();
        int length0 = values0.length;
        int randIndex0 = new Random().nextInt(length0);
        KuchenTyp randomKuchentyp = values0[randIndex0];
        List<Allergen> a1 = new LinkedList<>(Collections.singleton(Allergen.Erdnuss));

        int randomNaehrwert = random.nextInt(10) + 1; //random naehrwert from 1 to 10

        int myDays = random.nextInt(15) + 1;
        Duration durationInDays = Duration.ofDays(myDays);           // random haltbarkeit
        double myPrices = random.nextDouble(10) + 1; //random price between 1 and 10
        BigDecimal randomPrice = new BigDecimal(myPrices);
        //random fachnummer between 1 and the default capacity of the automat
        int randomFachnummer = random.nextInt(automat.getDefaultCapacity()  ) + 1;
        KuchenImp randomKuchen = new KuchenImp(randomKuchentyp, new HerstellerImp("h1"), randomPrice, a1, randomNaehrwert, durationInDays, new Date(), randomFachnummer  );
        return randomKuchen;
    }



    @Override
    public void run() {
        while (true) {
            synchronized (automat) {
                try {
                    if (automat.readArrayOfKuchen().length >= automat.getDefaultCapacity()) {
                        System.err.println("automat is full wait for kuchen delete");
                        automat.wait();
                    } else {
                        int randomIndex = (int) (Math.random() * automat.readArrayOfKuchen().length );
                        automat.update(randomIndex);
                    }
                } catch (InterruptedException e) {
                }
            }
        }
    }



}



