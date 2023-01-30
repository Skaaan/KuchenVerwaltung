package domainLogic.kuchen;


import domainLogic.hersteller.HerstellerImp;
import domainLogic.kuchen.KuchenImp;
import vertrag.Allergen;
import vertrag.KuchenTyp;
import vertrag.ObstTorte;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class ObstTorteImp extends KuchenImp implements ObstTorte, java.io.Serializable {


    private final String obstsorte;
    private final String kremsorte;

    public ObstTorteImp(KuchenTyp kuchenType, HerstellerImp hersteller, BigDecimal preis, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, Date inspektionsdatum, int fachnummer, String obstsorte, String kremsorte) {
        super(kuchenType, hersteller, preis, allergens, naehrwert, haltbarkeit, inspektionsdatum, fachnummer);
        this.kremsorte = kremsorte;
        this.obstsorte = obstsorte;
    }

    public synchronized String getObstsorte() {
        return this.obstsorte;
    }

    public synchronized String getKremsorte() {
        return this.kremsorte;
    }

}
