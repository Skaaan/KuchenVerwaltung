package domainLogic.kuchen;

import domainLogic.hersteller.HerstellerImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;


import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static vertrag.KuchenTyp.*;

class KuchenVerwaltungTest {


    private KuchenVerwaltung kv;

    private final HerstellerImp h1 = new HerstellerImp("Hersteller1");
    private final HerstellerImp h2 = new HerstellerImp("Hersteller2");
    private final HerstellerImp h3 = new HerstellerImp("Hersteller3");

    private final BigDecimal p1 = new BigDecimal(1);
    private final BigDecimal p2 = new BigDecimal(2);
    private Duration d1,d2;

    private final Collection<Allergen> a1 = new LinkedList<>();
    private final Collection<Allergen> a2 = new LinkedList<>();
    private final int naehrwert1 = 1;
    private final int naehrwert2 = 2;

    private final String obstsorte1 = "KiwiCrem";
    private final String kremsorte1 = "NugatCrem";



    @BeforeEach
    void setUp() {
        kv = new KuchenVerwaltung();
        d1 = Duration.ofDays(2);
        d2 = Duration.ofDays(5);

        a1.add(Allergen.Erdnuss);
        a2.add(Allergen.Gluten);
        a1.add(Allergen.Haselnuss);

    }


    @Test
    public void testGetAndSetDefaultCapacity() {
        //tests getter and setter for defaultCapacity
        kv.setDefaultCapacity(10);
        assertEquals(10, kv.getDefaultCapacity());

        kv.setDefaultCapacity(20);
        assertEquals(20, kv.getDefaultCapacity());
    }


    @Test
    void create_predefined_Kuchen() {
        KuchenImp predefKuchen = new KremkuchenImp(Kremkuchen, h1, p1, a1, naehrwert1, d1, new Date(), 0, "NugatKrem");
        // Tests if the passed Kremkuchen to listOfKremkuchen is not null
        kv.create(predefKuchen);
        KuchenImp addedKuchen = kv.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }

    @Test
    void create_KuchenTyp_Kremkuchen() {
        // Tests if the passed Kremkuchen to listOfKremkuchen is not null
        kv.create(Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatKrem");
        KuchenImp addedKuchen = kv.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }

    @Test
    void create_KuchenTyp_ObstKuchen() {
        // Tests if the passed Kremkuchen to listOfKuchen not null is.
        kv.create(Obstkuchen ,h1, p1, a1, naehrwert1, d1,  "Apfel");
        KuchenImp addedKuchen = kv.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }

    @Test
    void create_KuchenTyp_ObstTorte() {
        // Tests if the passed Kremkuchen to listOfKuchen not null is.
        kv.create(Obsttorte ,h1, p1, a1, naehrwert1, d1, "Apfel", "NugatKrem");
        KuchenImp addedKuchen = kv.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }


    @Test
    void read_Empty_KuchenVerwaltung() {
        //GIVEN
        //see setUp()
        //WHEN
        int lengthOfKuchenarray = kv.readArrayOfKuchen().length;
        //THEN
        assertEquals(0, lengthOfKuchenarray);
    }

    @Test
    void read_OneKuchen_KuchenVerwaltung() {
        //GIVEN
        kv.create(Kremkuchen, h1, p1, a1, naehrwert1, d1, obstsorte1);
        //WHEN
        int lengthOfKuchenarray = kv.readArrayOfKuchen().length;
        //THEN
        assertEquals(1, lengthOfKuchenarray);
    }

    @Test
    void read_ThreeKuchenType_KuchenVerwaltung() {
        //GIVEN
        kv.create(Kremkuchen, h1, p1, a1, naehrwert1, d1, obstsorte1);
        kv.create(Obstkuchen, h1, p2, a1, naehrwert2, d1, obstsorte1);
        kv.create(Obsttorte, h1, p1, a1, naehrwert2, d1, obstsorte1, kremsorte1);
        //WHEN
        int lengthOfKuchenarray = kv.readArrayOfKuchen().length;
        //THEN
        assertEquals(3, lengthOfKuchenarray);
    }


    @Test
    void update_InspectiondatumNormal() {
        //GIVEN
        kv.create(Kremkuchen, h1, p1, a1, naehrwert1, d1, obstsorte1);
        //WHEN & THEN
        assertDoesNotThrow(() -> {
            kv.update(0);
        });
    }

    @Test
    void update_InspectiondateNotExistingKuchen() {
        //GIVEN
        kv.create(Kremkuchen, h1, p1, a1, naehrwert1, d1, obstsorte1);
        //WHEN & THEN
        assertThrows(IndexOutOfBoundsException.class, ()-> kv.update(1));
    }



    @Test
    void delete_OneKuchen() {
        // Tests the length of output after deleting the only 1 Kremkuchen in the list
        //GIVEN
        kv.create(Kremkuchen ,h1, p1, a1, naehrwert1, d1,  kremsorte1);
        //WHEN
        kv.deleteKuchen(0);
        KuchenImp[] KuchenArray = kv.readArrayOfKuchen();
        int KuchenArrayLength = KuchenArray.length;
        //THEN
        assertEquals(0,KuchenArrayLength);
    }


    @Test
    void delete_NotExistingKuchen_inGivenFachnummer() {
        //GIVEN
        kv.create(Kremkuchen, h1, p1, a1, naehrwert1, d1, obstsorte1);
        //WHEN & THEN
        assertThrows(IndexOutOfBoundsException.class, () -> {
            kv.deleteKuchen(1);
        });

    }

    @Test
    public void testSortListOfKuchenByType_Kremkuchen() {
       // Tests the size of the SortedList after sorting kv1
        // Tests if the Kuchen in  the SortedList  match the given KuchenType Kremkuchen or not

        //GIVEN
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h1, p1, a2, naehrwert1, d2, "MangoCreme"   );
        kv.create( Obsttorte ,h1, p1, a1, naehrwert1, d2, "NugatCreme", "MangoCreme");
        kv.create( Obstkuchen ,h1, p1, a1, naehrwert1, d1, "KiwiCreme");

        //WHEN
        LinkedList<KuchenImp> sortedList = kv.SortListOfKuchenByType(Kremkuchen);
        //THEN
        assertEquals(1, sortedList.size());
        assertEquals(Kremkuchen, sortedList.get(0).getKuchenType());
    }


    @Test
    public void testSortListOfKuchenByType_Obstkuchen() {
        // Tests the size of the SortedList after sorting kv1
        // Tests if the Kuchen in  the SortedList  match the given KuchenType Obstkuchen or not

        //GIVEN
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h1, p1, a2, naehrwert1, d1, "MangoCreme"   );
        kv.create( Obsttorte ,h1, p1, a1, naehrwert1, d1, "NugatCreme", "MangoCreme");
        kv.create( Obstkuchen ,h1, p1, a1, naehrwert1, d1, "KiwiCreme");

        //WHEN
        LinkedList<KuchenImp> sortedList = kv.SortListOfKuchenByType(Obstkuchen);
        //THEN
        assertEquals(2, sortedList.size());
        assertEquals(Obstkuchen, sortedList.get(0).getKuchenType());
        assertEquals(Obstkuchen, sortedList.get(1).getKuchenType());
    }


    @Test
    public void testSortListOfKuchenByType_Obsttorte() {
        // Tests the size of the SortedList after sorting kv1
        // Tests the Kuchen in  he SortedList if they match the given KuchenType or not

        //GIVEN
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h1, p1, a2, naehrwert1, d1, "MangoCreme"   );
        kv.create( Obsttorte ,h1, p1, a1, naehrwert1, d1, "NugatCreme", "MangoCreme");
        kv.create( Obstkuchen ,h1, p1, a1, naehrwert1, d1, "KiwiCreme");

        //WHEN
        LinkedList<KuchenImp> sortedList = kv.SortListOfKuchenByType(Obsttorte);
        //THEN
        assertEquals(1, sortedList.size());
        assertEquals(Obsttorte, sortedList.get(0).getKuchenType());
    }

    @Test
    public void testSortListOfKuchenByHersteller() {
        // Tests the size of the SortedList after sorting kv1
        // Tests the Kuchen in  the SortedList if they match the given Hersteller or not

        //GIVEN
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h2, p1, a2, naehrwert1, d1, "MangoCreme"   );
        kv.create( Obsttorte ,h3, p1, a1, naehrwert1, d1, "NugatCreme", "MangoCreme");
        kv.create( Obstkuchen ,h1, p1, a1, naehrwert1, d1, "KiwiCreme");

        // Test sorting by Hersteller1
        LinkedList<KuchenImp> sortedList = kv.SortListOfKuchenByHersteller("Hersteller1");
        assertEquals(2, sortedList.size());
        assertEquals("Hersteller1", sortedList.get(0).getHersteller().getName());
        assertEquals("Hersteller1", sortedList.get(1).getHersteller().getName());

        // Test sorting by Hersteller2
        sortedList = kv.SortListOfKuchenByHersteller("Hersteller2");
        assertEquals(1, sortedList.size());
        assertEquals("Hersteller2", sortedList.get(0).getHersteller().getName());

        // Test sorting by Hersteller3
        sortedList = kv.SortListOfKuchenByHersteller("Hersteller3");
        assertEquals(1, sortedList.size());
        assertEquals("Hersteller3", sortedList.get(0).getHersteller().getName());
    }


    @Test
    public void testGetAllergens() {
        // Tests if the list of allergens (kv1.getAllergens())  contains the added allergens in the created Kuchen or not
        // Tests the size of kv1.getAllergens()  (the size of the list of all available allergens in kv1)


        //GIVEN
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h1, p1, a2, naehrwert1, d1, "MangoCreme"   );
        kv.create( Obsttorte ,h1, p1, a1, naehrwert1, d1, "NugatCreme", "MangoCreme");
        kv.create( Obstkuchen ,h1, p1, a1, naehrwert1, d1, "KiwiCreme");

        //WHEN
        Collection<Allergen> allergens = kv.getAllergens();
        //THEN
        assertTrue(allergens.contains(Allergen.Erdnuss));
        assertTrue(allergens.contains(Allergen.Gluten));
        assertTrue(allergens.contains(Allergen.Haselnuss));
        assertEquals(3, allergens.size());
    }


}












