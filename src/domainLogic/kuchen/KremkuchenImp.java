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

    private String topping;


    @Override
    public synchronized String getKremsorte() {
        return this.topping;
    }



    public KremkuchenImp(KuchenTyp kuchenType, HerstellerImp hersteller, BigDecimal preis, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, Date inspektionsdatum, int fachnummer, String topping) {
        super(kuchenType ,hersteller,preis, allergens, naehrwert, haltbarkeit, inspektionsdatum, fachnummer);
        this.topping = topping;
    }


}
