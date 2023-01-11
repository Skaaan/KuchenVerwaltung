package thread.simulation2;

import domainLogic.HerstellerImp;
import domainLogic.KuchenImp;
import domainLogic.KuchenVerwaltung;
import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class UpdateThread extends Thread{

    public UpdateThread(KuchenVerwaltung kv){
        this.kv=kv;
    }


    KuchenVerwaltung kv;


    @Override
    public  void run() {
        while (true) {
            synchronized(kv){
                if (kv.read().length != 0) {
                    int randomIndex = (int) ((Math.random() * kv.read().length));  // generating random index from the list, Source: https://www.baeldung.com/java-random-list-element   //* kv.read().length :random int between 0 and array length
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
