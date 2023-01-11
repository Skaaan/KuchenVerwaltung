package domainLogic;


import vertrag.Allergen;
import vertrag.ObstTorte;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class ObstTorteImp extends KuchenImp implements ObstTorte, java.io.Serializable {


    private final String obstsorte;
    private final String kremsorte;

    public ObstTorteImp(HerstellerImp hersteller, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, BigDecimal preis, Date inspektionsdatum, int fachnummer, String obstsorte, String kremsorte) {
        super(hersteller, allergens, naehrwert, haltbarkeit, preis, inspektionsdatum, fachnummer);
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
