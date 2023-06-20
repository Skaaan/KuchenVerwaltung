package thread.simulation1; //  TODO   notifyObservers muss ohne Argument sein! hat prof gesagt!

import domainLogic.Automat;
import domainLogic.hersteller.HerstellerImp;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;
import domainLogic.kuchen.KuchenImp;
import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class Create1Thread extends Thread {


    Automat automat;

    public Create1Thread(Automat automat){
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
    public synchronized void run() {
        while (true) {
            if(automat.readArrayOfKuchen().length < automat.getDefaultCapacity()) {
                automat.create(randomKuchen());
                System.err.println("Created Kuchen");
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                }
            }
        }
    }




    }





