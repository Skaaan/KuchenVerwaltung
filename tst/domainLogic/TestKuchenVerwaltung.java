package domainLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;
import vertrag.KuchenTyp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static vertrag.KuchenTyp.*;

class TestKuchenVerwaltung {

    private final String testname1 = "MaxMustermann";
    private final String testname2 = "JohnWick";
    private KuchenVerwaltung kv10;
    private KuchenVerwaltung kv1;
    private final KuchenTyp k1 = KuchenTyp.Obstkuchen;
    private final KuchenTyp k2 = KuchenTyp.Kremkuchen;
    private HerstellerImp h1 = new HerstellerImp(testname1);
    private HerstellerImp h2 = new HerstellerImp(testname2);
    private BigDecimal p1 = new BigDecimal(1);
    private BigDecimal p2 = new BigDecimal(2);
    private Duration d1, d2;
    private Collection<Allergen> a0 = new LinkedList<>();
    private Collection<Allergen> a1 = new LinkedList<>();
    private Collection<Allergen> a2 = new LinkedList<>();
    private int naehrwert1 = 1;
    private int naehrwert2 = 2;
    private int size10 = 10;
    private int size1 = 1;
    private int size0 = 0;
    private String obstsorte1 = "obstsorte1";
    private String obstsorte2 = "obstsorte2";
    private String kremsorte1 = "NugatKrem";
    private String kremsorte2 = "kremsorte2";


    @BeforeEach
    void setUp() {
        kv10 = new KuchenVerwaltung();
        kv1 = new KuchenVerwaltung();
        a1.add(Allergen.Erdnuss);
        a2.add(Allergen.Gluten);
        a2.add(Allergen.Erdnuss);
        d1 = Duration.ofDays(2);
        d2 = Duration.ofDays(2);
    }




    @Test
    void create_KuchenTyp_Kremkuchen() {
        // Tests if the passed Kremkuchen to listOfKremkuchen is not null
        kv1.create(Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatKrem");
        KuchenImp addedKuchen = kv1.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }

    @Test
    void create_KuchenTyp_ObstKuchen() {
        // Tests if the passed Kremkuchen to listOfKuchen not null is.
        kv1.create(Obstkuchen ,h1, p1, a1, naehrwert1, d1,  "Apfel");
        KuchenImp addedKuchen = kv1.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }

    @Test
    void create_KuchenTyp_ObstTorte() {
        // Tests if the passed Kremkuchen to listOfKuchen not null is.
        kv1.create(Kremkuchen ,h1, p1, a1, naehrwert1, d1, "Apfel", "NugatKrem");
        KuchenImp addedKuchen = kv1.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }


    @Test
    void read_Empty_KuchenVerwaltung() {
        //GIVEN
        //see setUp()
        //WHEN
        int lengthOfKuchenarray = kv10.read().length;
        //THEN
        assertEquals(0, lengthOfKuchenarray);
    }

    @Test
    void read_OneKuchen_KuchenVerwaltung() {
        //GIVEN
        kv1.create(k1, h1, p1, a1, naehrwert1, d1, obstsorte1);
        //WHEN
        int lengthOfKuchenarray = kv1.read().length;
        //THEN
        assertEquals(1, lengthOfKuchenarray);
    }

    @Test
    void read_ThreeKuchenType_KuchenVerwaltung() {
        //GIVEN
        kv1.create(Kremkuchen, h1, p1, a1, naehrwert1, d1, obstsorte1);
        kv1.create(Obstkuchen, h1, p1, a1, naehrwert1, d1, obstsorte1);
        kv1.create(Obsttorte, h1, p1, a1, naehrwert1, d1, obstsorte1, kremsorte1);
        //WHEN
        int lengthOfKuchenarray = kv1.read().length;
        //THEN
        assertEquals(3, lengthOfKuchenarray);
    }


    @Test
    void update_InspectiondatumNormal() {
        //GIVEN
        kv1.create(k1, h1, p1, a1, naehrwert1, d1, obstsorte1);
        //WHEN & THEN
        assertDoesNotThrow(() -> {
            kv1.update(0);
        });
    }

    @Test
    void update_InspectiondateNotExistingKuchen() {
        //GIVEN
        kv1.create(k1, h1, p1, a1, naehrwert1, d1, obstsorte1);
        //WHEN & THEN
        assertThrows(IndexOutOfBoundsException.class, ()-> kv1.update(1));
    }



    @Test
    void delete_OneKuchen() {
        // Tests the length of output after deleting the only 1 Kremkuchen in the list
        //GIVEN
        kv1.create(Kremkuchen ,h1, p1, a1, naehrwert1, d1,  kremsorte1);
        //WHEN
        kv1.delete(0);
        KuchenImp[] KuchenArray = kv1.read();
        int KuchenArrayLength = KuchenArray.length;
        //THEN
        assertEquals(0,KuchenArrayLength);
    }


    @Test
    void deleteNotExistingKuchen() {
        //GIVEN
        kv1.create(k1, h1, p1, a1, naehrwert1, d1, obstsorte1);
        //WHEN & THEN
        assertThrows(IndexOutOfBoundsException.class, () -> {
            kv1.delete(1);
        });

    }


}