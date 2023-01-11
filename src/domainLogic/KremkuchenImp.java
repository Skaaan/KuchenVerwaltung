package domainLogic;

import vertrag.Allergen;
import vertrag.Kremkuchen;

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


    public KremkuchenImp(HerstellerImp hersteller, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, BigDecimal preis, Date inspektionsdatum, int fachnummer, String kremsorte) {
        super(hersteller, allergens, naehrwert, haltbarkeit, preis, inspektionsdatum, fachnummer);
        this.kremsorte = kremsorte;
    }


}
