package domainLogic.kuchen;

import domainLogic.hersteller.HerstellerImp;
import vertrag.Allergen;
import vertrag.Kremkuchen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class KremkuchenImp extends KuchenImp implements Kremkuchen, java.io.Serializable {

    private String kremsorte;


    @Override
    public synchronized String getKremsorte() {
        return this.kremsorte;
    }

    public KremkuchenImp() {
    }


    public KremkuchenImp(KuchenTyp kuchenType, HerstellerImp hersteller, BigDecimal preis, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, Date inspektionsdatum, int fachnummer, String kremsorte) {
        super(kuchenType ,hersteller,preis, allergens, naehrwert, haltbarkeit, inspektionsdatum, fachnummer);
        this.kremsorte = kremsorte;
    }


}
