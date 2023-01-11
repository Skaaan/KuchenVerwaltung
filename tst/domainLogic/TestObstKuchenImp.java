package domainLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestObstKuchenImp {
    ObstKuchenImp testKuchen;
    Collection<Allergen> allergens;
    HerstellerImp hersteller;
    int naehrwert;
    Duration duration;
    BigDecimal price;
    Date inspektionsDatum;
    int fachnummer;
    String nameHersteller;
    String obstsorte;



    @BeforeEach
    void setUp() {
        nameHersteller = "maxMustermann";
        hersteller = new HerstellerImp(nameHersteller);
        allergens = new LinkedList<>();
        allergens.add(Allergen.Haselnuss);
        allergens.add(Allergen.Gluten);
        naehrwert = 1;
        duration = Duration.ofDays(2);
        price = new BigDecimal("1.99");
        inspektionsDatum = new Date();
        fachnummer = 0;
        obstsorte = "Apfel";
        testKuchen = new ObstKuchenImp(hersteller, allergens, naehrwert, duration, price, inspektionsDatum, fachnummer, obstsorte);


    }

    @Test
    void getObstsorte() {
        //GIVEN
        // see setUp()
        //WHEN
        String obstsorteActual = testKuchen.getObstsorte();
        //THEN
        assertEquals(obstsorte, obstsorteActual);
    }
}