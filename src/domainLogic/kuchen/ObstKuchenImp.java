package domainLogic.kuchen;

import domainLogic.hersteller.HerstellerImp;
import domainLogic.kuchen.KuchenImp;
import vertrag.Allergen;
import vertrag.KuchenTyp;
import vertrag.Obstkuchen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class ObstKuchenImp extends KuchenImp implements Obstkuchen, java.io.Serializable {

    private final String obstsorte;


        public ObstKuchenImp(KuchenTyp kuchenType, HerstellerImp hersteller, BigDecimal preis, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, Date inspektionsdatum, int fachnummer, String obstsorte) {
            super(kuchenType, hersteller,preis, allergens, naehrwert, haltbarkeit, inspektionsdatum, fachnummer);
            this.obstsorte = obstsorte;
        }

        public synchronized String getObstsorte() {
            return this.obstsorte;
        }
    }





