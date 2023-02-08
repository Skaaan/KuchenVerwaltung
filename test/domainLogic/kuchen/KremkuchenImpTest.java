package domainLogic.kuchen;

import domainLogic.hersteller.HerstellerImp;
import domainLogic.kuchen.KremkuchenImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vertrag.KuchenTyp.Kremkuchen;

public class KremkuchenImpTest {
    KremkuchenImp testKuchen;
    Collection<Allergen> allergens;
    HerstellerImp hersteller;
    int naehrwert;
    Duration duration;
    BigDecimal price;
    Date inspektionsDatum;
    int fachnummer;
    String nameHersteller;
    String kremsorte;



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
        kremsorte = "VanillaKrem";
        testKuchen = new KremkuchenImp(Kremkuchen, hersteller, price, allergens, naehrwert, duration, inspektionsDatum, fachnummer, kremsorte);


    }

    @Test
    void getKremsorte() {
        //GIVEN
        // see setUp()
        //WHEN
        String kremsorteActual = testKuchen.getKremsorte();
        //THEN
        assertEquals(kremsorte, kremsorteActual);
    }
}