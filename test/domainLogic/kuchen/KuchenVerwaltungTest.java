package domainLogic.kuchen;

import domainLogic.hersteller.HerstellerImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;


import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static vertrag.KuchenTyp.*;

class KuchenVerwaltungTest {


    private KuchenVerwaltung kv;

    private final HerstellerImp hNull = new HerstellerImp(null);
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
    void KuchenVerwaltungConstructor() {
        //Test for Constructor of Kuchenverwaltung(kuchenList)
        ArrayList<KuchenImp> kuchenList = new ArrayList<>();
        KuchenImp kuchen = new KuchenImp();
        kuchenList.add(kuchen);
        KuchenVerwaltung kv = new KuchenVerwaltung(kuchenList);
        assertEquals(kuchenList, kv.listOfKuchen);
    }



    @Test
    void create_predefinedKuchen_Normal() {
        // Tests if the passed Kremkuchen to listOfKremkuchen is not null
        KuchenImp predefKuchen = new KremkuchenImp(Kremkuchen, h1, p1, a1, naehrwert1, d1, new Date(), 0, "NugatKrem");
        kv.create(predefKuchen);
        KuchenImp addedKuchen = kv.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }


    @Test
    void create_predefinedKuchen_HerstellerIsNull() {
        // Tests exception for Hersteller equal null
        KuchenImp predefKuchen = new KremkuchenImp(Kremkuchen, null, p1, a1, naehrwert1, d1, new Date(), 0, "NugatKrem");
        assertThrows(IllegalArgumentException.class, ()-> kv.create(predefKuchen) );
    }

    @Test
    void create_predefinedKuchen_HerstellerNameIsNull() {
        // Tests exception for Hersteller.name equal null
        KuchenImp predefKuchen = new KremkuchenImp(Kremkuchen, hNull, p1, a1, naehrwert1, d1, new Date(), 0, "NugatKrem");
        assertThrows(IllegalArgumentException.class, ()-> kv.create(predefKuchen) );
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
    void create_OutOfCapacity() {
        // Tests the exception thrown for a capacity of 1 after creating 2 Kuchen in the KuchenVerwaltung
        //GIVEN
        kv.setDefaultCapacity(1);
        //WHEN & THEN
        kv.create(Obsttorte ,h1, p1, a1, naehrwert1, d1, "Apfel", "NugatKrem");
        assertThrows(IndexOutOfBoundsException.class, ()->  kv.create(Kremkuchen ,h1, p1, a1, naehrwert1, d1, "Apfel", "NugatKrem") );
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
    void SortListOfKuchenByType_Size() {
        // Tests the size of the SortedList after sorting kv with Obstkuchen
        //GIVEN
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h1, p1, a2, naehrwert1, d1, "MangoCreme"   );
        kv.create( Obsttorte ,h1, p1, a1, naehrwert1, d1, "NugatCreme", "MangoCreme");
        kv.create( Obstkuchen ,h1, p1, a1, naehrwert1, d1, "KiwiCreme");
        //WHEN
        LinkedList<KuchenImp> sortedList = kv.SortListOfKuchenByType(Obstkuchen);
        //THEN
        assertEquals(2, sortedList.size());
    }

    @Test
    void SortListOfKuchenByType_Kremkuchen() {
        // Tests if the Kuchen in  the SortedList  match the given KuchenType Kremkuchen or not
        //GIVEN
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h1, p1, a2, naehrwert1, d2, "MangoCreme"   );
        //WHEN
        LinkedList<KuchenImp> sortedList = kv.SortListOfKuchenByType(Kremkuchen);
        //THEN
        assertEquals(Kremkuchen, sortedList.get(0).getKuchenType());
    }


    @Test
    void SortListOfKuchenByType_Obstkuchen() {
        // Tests if the Kuchen in  the SortedList  match the given KuchenType Obstkuchen or not
        //GIVEN
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h1, p1, a2, naehrwert1, d1, "MangoCreme"   );
        //WHEN
        LinkedList<KuchenImp> sortedList = kv.SortListOfKuchenByType(Obstkuchen);
        //THEN
        assertEquals(Obstkuchen, sortedList.get(0).getKuchenType());
    }


    @Test
    void SortListOfKuchenByType_Obsttorte() {
        // Tests the Kuchen in  he SortedList if they match the given KuchenType or not
        //GIVEN
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obsttorte ,h1, p1, a1, naehrwert1, d1, "NugatCreme", "MangoCreme");

        //WHEN
        LinkedList<KuchenImp> sortedList = kv.SortListOfKuchenByType(Obsttorte);
        //THEN
        assertEquals(Obsttorte, sortedList.get(0).getKuchenType());
    }

    @Test
    void SortListOfKuchenByHersteller_size() {
        // Tests the size of the SortedList after sorting kv

        //GIVEN
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h2, p1, a2, naehrwert1, d1, "MangoCreme"   );
        kv.create( Obstkuchen ,h1, p1, a1, naehrwert1, d1, "KiwiCreme");

        // Test sorting by Hersteller1
        LinkedList<KuchenImp> sortedList = kv.SortListOfKuchenByHersteller("Hersteller1");
        assertEquals(2, sortedList.size());
    }


    @Test
    void SortListOfKuchenByHersteller() {
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
    }

    @Test
    void GetAllergens_availableAllergens_OneAllergen() {
        // Tests if the list of allergens (kv.getAllergens())  contains the added allergens in the created Kuchen or not
        //GIVEN
        a1.add(Allergen.Erdnuss);
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h1, p1, a2, naehrwert1, d1, "MangoCreme"   );
        kv.create( Obsttorte ,h1, p1, a1, naehrwert1, d1, "NugatCreme", "MangoCreme");
        kv.create( Obstkuchen ,h1, p1, a1, naehrwert1, d1, "KiwiCreme");
        //WHEN
        Collection<Allergen> allergens = kv.getAllergens();
        //THEN
        assertTrue(allergens.contains(Allergen.Erdnuss));
    }


    @Test
    void GetAllergens_size_OneAllergen() {
        // Tests the size of kv.getAllergens()  (the size of the list of all available allergens in kv)

        //GIVEN
        a1.add(Allergen.Gluten);
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h1, p1, a2, naehrwert1, d1, "MangoCreme"   );
        kv.create( Obsttorte ,h1, p1, a1, naehrwert1, d1, "NugatCreme", "MangoCreme");
        kv.create( Obstkuchen ,h1, p1, a1, naehrwert1, d1, "KiwiCreme");
        //WHEN
        Collection<Allergen> allergens = kv.getAllergens();
        //THEN
        assertEquals(1, allergens.size());
    }


    @Test
    void GetAllergens_size_AllAllergens() {
        // Tests the size of kv.getAllergens()  (the size of the list of all available allergens in kv)

        //GIVEN
        a1.add(Allergen.Erdnuss);
        a1.add(Allergen.Gluten);
        a2.add(Allergen.Haselnuss);
        a1.add(Allergen.Sesamsamen);
        kv.create( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        kv.create( Obstkuchen ,h1, p1, a2, naehrwert1, d1, "MangoCreme"   );
        kv.create( Obsttorte ,h1, p1, a1, naehrwert1, d1, "NugatCreme", "MangoCreme");
        //WHEN
        Collection<Allergen> allergens = kv.getAllergens();
        //THEN
        assertEquals(4, allergens.size());
    }



}












