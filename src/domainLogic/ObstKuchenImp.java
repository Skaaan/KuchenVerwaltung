package domainLogic;

import vertrag.Allergen;
import vertrag.Obstkuchen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class ObstKuchenImp extends KuchenImp implements Obstkuchen, java.io.Serializable {

    private final String obstsorte;


        public ObstKuchenImp(HerstellerImp hersteller, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, BigDecimal preis, Date inspektionsdatum, int fachnummer, String obstsorte) {
            super(hersteller, allergens, naehrwert, haltbarkeit, preis, inspektionsdatum, fachnummer);
            this.obstsorte = obstsorte;
        }

        public synchronized String getObstsorte() {
            return this.obstsorte;
        }
    }





