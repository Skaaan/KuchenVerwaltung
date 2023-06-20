package vertrag;

import domainLogic.hersteller.HerstellerImp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public interface KuchenTeil {


    Duration getDuration();
    Collection<Allergen> getAllergen();
    BigDecimal  getPrice();
    int getNaehrwert();
}
