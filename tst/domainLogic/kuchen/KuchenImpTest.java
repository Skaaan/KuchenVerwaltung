package domainLogic.kuchen;

import domainLogic.hersteller.HerstellerImp;
import domainLogic.kuchen.KremkuchenImp;
import domainLogic.kuchen.KuchenImp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static vertrag.KuchenTyp.Kremkuchen;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KuchenImpTest {
    KuchenImp testKuchen;
    Collection<Allergen> allergens;
    HerstellerImp hersteller;
    int naehrwert;
    Duration duration;
    BigDecimal price;
    Date inspektionsDatum;
    int fachnummer;
    String nameHersteller;


    @BeforeAll
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
        testKuchen = new KremkuchenImp(Kremkuchen, hersteller, price, allergens, naehrwert, duration, inspektionsDatum, fachnummer, null);
    }


    @Test
    void getHersteller() {
        //GIVEN
        //see setUp()
        //WHEN
        String nameHerstellerActual = testKuchen.getHersteller().getName();
        //THEN
        assertEquals(nameHersteller, nameHerstellerActual);
    }


    @Test
    void getAllergene() {
        //GIVEN
        //see setUp()
        //WHEN
        Collection<Allergen> a1 = testKuchen.getAllergene();
        //THEN
        assertTrue(a1.contains(Allergen.Haselnuss) && a1.contains(Allergen.Gluten));
    }

    @Test
    void getNaehrwert() {
        //GIVEN
        //see setUp()
        //WHEN
        int naehrwertActual = testKuchen.getNaehrwert();
        //THEN
        assertEquals(naehrwert, naehrwertActual);
    }

    @Test
    void getHaltbarkeit() {
        //GIVEN
        //see setUp()
        //WHEN
        Duration durationActual = testKuchen.getHaltbarkeit();
        //THEN
        assertEquals(duration, durationActual);
    }

    @Test
    void getPreis() {
        //GIVEN
        //see setUp()
        //WHEN
        BigDecimal priceActual = testKuchen.getPreis();
        //THEN
        assertEquals(price, priceActual);
    }

    @Test
    void getInspektionsdatum() {
        //GIVEN
        //see setUp()
        //WHEN
        Date dateActual = testKuchen.getInspektionsdatum();
        //THEN
        assertEquals(inspektionsDatum.toString(), dateActual.toString());
    }

    @Test
    void getFachnummer() {
        //GIVEN
        //see setUp()
        //WHEN
        int fachnummerActual = testKuchen.getFachnummer();
        //THEN
        assertEquals(fachnummer, fachnummerActual);
    }


    @Test
    void setInspektionsDatum() {
        //GIVEN
        //see setUp()
        Date date = Calendar.getInstance().getTime();
        //WHEN
        testKuchen.setInspektionsdatum(date);
        Date actual = testKuchen.getInspektionsdatum();
        //THEN
        assertEquals(date, actual);
    }

    @Test
    void testToString() {
        //GIVEN
        //see setUp()
        //WHEN
        String actual = testKuchen.toString();
        //THEN
        assertNotNull(actual);
    }


}

