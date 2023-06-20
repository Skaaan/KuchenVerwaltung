package domainLogic;

import domainLogic.hersteller.HerstellerImp;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KremkuchenImp;
import domainLogic.kuchen.KuchenImp;
import domainLogic.kuchen.KuchenVerwaltung;
import domainLogic.kuchen.ObstKuchenImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static vertrag.KuchenTyp.*;

class AutomatTest {


    private KuchenVerwaltung kv, kvMock;
    private HerstellerVerwaltung hv, hvMock;
    private Automat automat, automatMock;

    private final HerstellerImp h1 = new HerstellerImp("h1");
    private final HerstellerImp h2 = new HerstellerImp("h2");
    private final HerstellerImp h3 = new HerstellerImp("h3");

    private final BigDecimal p1 = new BigDecimal(1);
    private final BigDecimal negativeP1 = new BigDecimal(-5);
    private Duration d1,negativeD1;

    private final Collection<Allergen> a1 = new LinkedList<>();
    private final Collection<Allergen> a2 = new LinkedList<>();
    private final int naehrwert1 = 1;
    private final int negativeNaehrwert1 = -8;



    @BeforeEach
    void setUp() {
        kv = new KuchenVerwaltung();
        hv = new HerstellerVerwaltung();
        automat = new Automat(kv, hv);

        kvMock = Mockito.mock(KuchenVerwaltung.class);
        hvMock = Mockito.mock(HerstellerVerwaltung.class);
        this.automatMock = new Automat(kvMock, hvMock);

        d1 = Duration.ofDays(2);
        negativeD1 = Duration.ofDays(-6);

        a1.add(Allergen.Erdnuss);
        a2.add(Allergen.Gluten);
        a1.add(Allergen.Haselnuss);
    }





    @Test
    public void testSetDefaultCapacity() {
        //tests getter and setter for defaultCapacity
        automat.setDefaultCapacity(10);
        assertEquals(10, kv.getDefaultCapacity());

        automat.setDefaultCapacity(20);
        assertEquals(20, kv.getDefaultCapacity());
    }

    @Test
     void testGetDefaultCapacity() {
        int expected = 50;
        int actual = automat.getDefaultCapacity();
        assertEquals(expected, actual);
    }


    @Test
    void create_OneHersteller() {
        // Tests if the passed Kremkuchen to the automat is not null
        //GIVEN
        automat.createHersteller("Skander");
        //WHEN & THEN
        HerstellerImp addedHersteller = automat.readListOfHersteller()[0];
        assertNotNull(addedHersteller);
    }


    @Test
    void create_ThreeeHersteller() {
        // Tests if the size of the HerstellerVerwaltung after passing 3 Hersteller
        //GIVEN
        automat.createHersteller("Skander");
        automat.createHersteller("Max");
        automat.createHersteller("Martha");
        //WHEN & THEN
        int HerstellerVerwaltungSize = hv.getListOfHersteller().size();
        assertEquals(HerstellerVerwaltungSize, 3);
    }


    @Test
    void create_Existing_Hersteller() {
        //GIVEN
        automat.createHersteller("Skander");
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, ()-> automat.createHersteller("Skander"))  ;
    }




    @Test
    void delete_OneHersteller() {
        // Tests the length of output after deleting the only 1 Hersteller in the list
        //GIVEN
        hv.create("h1");
        //WHEN
        hv.deleteHersteller("h1");
        HerstellerImp[] HerstellerArray = hv.readListOfHersteller();
        int HerstellerArrayLength = HerstellerArray.length;
        //THEN
        assertEquals(0,HerstellerArrayLength);
    }


    @Test
    void delete_Existing_Hersteller() {
        //GIVEN
        String name = "Hersteller1";
        //WHEN
        automatMock.deleteHersteller(name);
        //THEN
        verify(hvMock).deleteHersteller(name);
    }


    @Test
    void delete_NotExistingHersteller() {
        //GIVEN
        //see setUp()
        //WHEN & THEN
        assertThrows(NullPointerException.class, ()-> automat.deleteHersteller("h1"))  ;
    }



    @Test
    void deleteHersteller_AutomatWithNoHersteller() {
        //GIVEN
        //see setUp()
        //WHEN & THEN
        assertThrows(NullPointerException.class, () -> automat.deleteHersteller("test"));
    }



    @Test
    void readListOfHersteller_Automat_WithNoHersteller() {
        //GIVEN
        //see setUp()
        //WHEN
        int lengthOfHerstellerArray = automat.readListOfHersteller().length;
        //THEN
        assertEquals(0, lengthOfHerstellerArray);
    }


    @Test
    void readListOfHersteller_Automat_WithOneHersteller() {
        //GIVEN
        automat.createHersteller("h1");
        //WHEN
        int lengthOfHerstellerArray = automat.readListOfHersteller().length;
        //THEN
        assertEquals(1, lengthOfHerstellerArray);
    }


    @Test
    void readListOfHersteller_Automat_WithThreeHersteller() {
        //GIVEN
        automat.createHersteller("h1");
        automat.createHersteller("h2");
        automat.createHersteller("h3");
        //WHEN
        int lengthOfHerstellerArray = automat.readListOfHersteller().length;
        //THEN
        assertEquals(3, lengthOfHerstellerArray);
    }




    @Test
    void listHerstellerWithNumberOfKuchen_WithNoHersteller() {
        //GIVEN
        //See setUp()
        //WHEN
        when(hvMock.getListOfHersteller() ).thenReturn(new LinkedList<>());
        when(kvMock.readArrayOfKuchen() ).thenReturn(new KuchenImp[0]);
        Map result = automatMock.listHerstellerWithNumberOfKuchen();
        //THEN
        assertTrue(result.isEmpty());
    }

    @Test
    void listHerstellerWithKuchenHersteller_WithNoKuchen() {
        //GIVEN
        HerstellerImp h1 = new HerstellerImp("Hersteller1");
        HerstellerImp h2 = new HerstellerImp("Hersteller2");
        HerstellerImp h3 = new HerstellerImp("Hersteller3");

        List<HerstellerImp> HerstellerList = new LinkedList<>();
        HerstellerList.add(h1);
        HerstellerList.add(h2);
        HerstellerList.add(h3);

        when(hvMock.getListOfHersteller()).thenReturn(HerstellerList);
        when(kvMock.readArrayOfKuchen()).thenReturn(new KuchenImp[0]);

        //WHEN
        Map<HerstellerImp, Integer> result = automatMock.listHerstellerWithNumberOfKuchen();
        //THEN
        assertEquals(0, (int) result.get(h1));
        assertEquals(0, (int) result.get(h3));
        assertEquals(0, (int) result.get(h3));
    }



    @Test
    void ListHerstellerWithKuchen_Normal() {
        //GIVEN
        //See setUp()
        HerstellerImp h1 = new HerstellerImp("Hersteller1");
        HerstellerImp h2 = new HerstellerImp("Hersteller2");
        HerstellerImp h3 = new HerstellerImp("Hersteller3");
        List<HerstellerImp> herstListe = new LinkedList<>();
        herstListe.add(h1);
        herstListe.add(h2);
        herstListe.add(h3);
        when(hvMock.getListOfHersteller()).thenReturn(herstListe);

        KuchenImp kuchen1 = new ObstKuchenImp(Obstkuchen ,h3, null, a1, naehrwert1, d1, new Date(), 0, "Kiwi");
        KuchenImp kuchen2 = new ObstKuchenImp(Obstkuchen ,h2, null, a1, naehrwert1, d1, new Date(), 0, "Kiwi");
        KuchenImp kuchen3 = new ObstKuchenImp(Obstkuchen ,h2, null, a1, naehrwert1, d1, new Date(), 0, "Kiwi");
        KuchenImp kuchen4 = new ObstKuchenImp(Obstkuchen ,h2, null, a1, naehrwert1, d1, new Date(), 0, "Kiwi");
        KuchenImp[] kuchenListe = {kuchen1, kuchen2, kuchen3, kuchen4};
        when(kvMock.readArrayOfKuchen()).thenReturn(kuchenListe);
        //WHEN
        Map<HerstellerImp, Integer> result = automatMock.listHerstellerWithNumberOfKuchen();
        //THEN
        assertEquals(3, (int) result.get(h2));
    }


    @Test
    void create_predefined_Kuchen() {
        KuchenImp predefKuchen = new KremkuchenImp(Kremkuchen, h1, p1, a1, naehrwert1, d1, new Date(), 0, "NugatKrem");
        // Tests if the passed Kremkuchen to listOfKremkuchen is not null
        automat.create(predefKuchen);
        KuchenImp addedKuchen = automat.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }

    @Test
    void createKuchen_KuchenTyp_Kremkuchen() {
        // Tests if the passed Kremkuchen to listOfKremkuchen is not null

        //GIVEN
        //see setUp()
        automat.createHersteller("h1");
        //WHEN
        automat.createKuchen(Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme");
        KuchenImp addedKuchen = automat.getListOfKuchen().get(0);
        //THEN
        assertNotNull(addedKuchen);
    }

    @Test
    void createKuchen_KuchenTyp_ObstKuchen() {
        // Tests if the passed Obstkuchen to listOfKuchen not null is.
        //GIVEN
        //see setUp()
        automat.createHersteller("h1");
        //WHEN
        automat.createKuchen(Obstkuchen ,h1, p1, a1, naehrwert1, d1,  "ApfelCreme");
        KuchenImp addedKuchen = automat.getListOfKuchen().get(0);
        //THEN
        assertNotNull(addedKuchen);
    }


    @Test
    void createKuchen_KuchenTyp_ObstTorte() {
        // Tests if the passed Obsttorte to listOfKuchen not null is.
        //GIVEN
        //see setUp()
        automat.createHersteller("h1");
        //WHEN
        automat.createKuchen(Obsttorte ,h1, p1, a1, naehrwert1, d1, "ApfelCreme", "NugatCreme");
        KuchenImp addedKuchen = automat.getListOfKuchen().get(0);
        //THEN
        assertNotNull(addedKuchen);

        assertThrows(IllegalArgumentException.class, () ->   automat.createKuchen(Obstkuchen, null, p1, a1, naehrwert1, d1,  "ApfelCreme")  );
    }


    @Test
    void createKuchen_KuchenTyp_IsNull() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(null, h1, p1, a1, naehrwert1, d1,  "ApfelCreme")  );
    }

    @Test
    void createKuchen_Hersteller_IsNull() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () ->   automat.createKuchen(Obstkuchen, null, p1, a1, naehrwert1, d1,  "ApfelCreme")  );
    }

    @Test
    void createKuchen_Hersteller_NotExisting() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () ->   automat.createKuchen(Obstkuchen, h2, p1, a1, naehrwert1, d1,  "ApfelCreme")  );
    }

    @Test
    void createKuchen_Price_Negative() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () ->    automat.createKuchen(Obstkuchen, h1, negativeP1, a1, naehrwert1, d1,  "ApfelCreme") );
    }

    @Test
    void createKuchen_Allergen_isNull() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(Obstkuchen, h1, p1, null, naehrwert1, d1,  "ApfelCreme") );
    }

    @Test
    void  createKuchen_Naehrwert_IsNegative() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(Obstkuchen, h1, p1, a1, negativeNaehrwert1, d1,  "ApfelCreme") );
    }

    @Test
    void  createKuchen_Duration_IsNegative() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(Obstkuchen, h1, p1, a1, naehrwert1, negativeD1,  "ApfelCreme") );
    }

    @Test
    void  createKuchen_Duration_IsNull() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(Obstkuchen, h1, p1, a1, naehrwert1, null,  "ApfelCreme") );
    }

    @Test
    void createKuchen_Topping_IsNull() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(Obstkuchen, h1, p1, a1, naehrwert1, d1,  null) );
    }

    @Test
    void createKuchen__Topping_moreThanTwo() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(Obstkuchen, h1, p1, a1, naehrwert1, d1,  "NugatCrem", "ApfelCreme", "VanillaCrem") );
    }

    @Test
    void createKuchen_Topping_notMatchKuchenType_Kremkuchen() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(Kremkuchen, h1, p1, a1, naehrwert1, d1,  "ApfelCreme", "NugatCreme") );
    }

    @Test
    void createKuchen_Topping_notMatchKuchenType_Obstkuchen() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(Obstkuchen, h1, p1, a1, naehrwert1, d1,  "ApfelCreme", "NugatCreme") );
    }



    @Test
    void deleteKuchen_OneKuchen() {
        // Tests the length of output after deleting the only 1 Kremkuchen from automat
        //GIVEN
        //See setUp()
        automat.createHersteller("h1");
        automat.createKuchen(Kremkuchen ,h1, p1, a1, naehrwert1, d1,  "NugatCreme");
        //WHEN
        automat.deleteKuchen(0);
        KuchenImp[] automatArray = automat.readArrayOfKuchen();
        int automatArrayLength = automatArray.length;
        //THEN
        assertEquals(0,automatArrayLength);
    }


    @Test
    void deleteKuchen_TwoKuchen() {
        // Tests the length of output after deleting the only 1 Kremkuchen from automat
        //GIVEN
        //See setUp()
        automat.createHersteller("h1");
        automat.createKuchen(Kremkuchen ,h1, p1, a1, naehrwert1, d1,  "NugatCreme"); //fachnummer:0
        automat.createKuchen(Obsttorte ,h1, p1, a1, naehrwert1, d1,  "NugatCreme", "ApfelCreme"); //fachnummer:1
        //WHEN
        automat.deleteKuchen(0);  //After that the first Kuchen is deleted, the other Kuchen will take his fachnummer (0)
        automat.deleteKuchen(0);
        KuchenImp[] automatArray = automat.readArrayOfKuchen();
        int automatArrayLength = automatArray.length;
        //THEN
        assertEquals(0,automatArrayLength);
    }


    @Test
    void deleteKuchen_NotExistingKuchen() {
        //GIVEN
        //see setUp()
        //WHEN & THEN
        assertThrows(IndexOutOfBoundsException.class, () -> {
            automat.deleteKuchen(0);
        });
    }

    @Test
    void deleteKuchen_NegativeFachnummer() {
        //GIVEN
        //see setUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            automat.deleteKuchen(-5);
        });
    }


    @Test
    void getListOfKuchenByType_noType() {
        // Tests the size of the SortedListOfKuchen without any type(null)
        // Tests if the Kuchen in  the SortedList  match the given KuchenType Kremkuchen or not
        //GIVEN
        automat.createHersteller("h1");
        //WHEN
        automat.createKuchen(Kremkuchen ,h1, p1, a1, naehrwert1, d1,  "NugatCreme");

        LinkedList<KuchenImp> sortedList = automat.getListOfKuchenByType(null);
        //THEN
        assertEquals(0, sortedList.size());
    }



    @Test
    void getListOfKuchenByType_Kremkuchen() {
        //GIVEN
        //See setUp()
        //WHEN
        automatMock.getListOfKuchenByType(Kremkuchen);
        //THEN
        verify(kvMock).SortListOfKuchenByType(Kremkuchen);
    }


    @Test
    void getListOfKuchenByType_Obstkuchen() {
        //GIVEN
        //See setUp()
        //WHEN
        automatMock.getListOfKuchenByType(Obstkuchen);
        //THEN
        verify(kvMock).SortListOfKuchenByType(Obstkuchen);
    }


    @Test
    void getListOfKuchenByType_Obsttorte() {
        //GIVEN
        //See setUp()
        //WHEN
        automatMock.getListOfKuchenByType(Obsttorte);
        //THEN
        verify(kvMock).SortListOfKuchenByType(Obsttorte);
    }


    @Test
    void getListOfKuchenByHersteller_size() {
        // Tests the size of the SortedList after sorting kv1
        //GIVEN
        automat.createHersteller("h1");
        automat.createHersteller("h2");
        automat.createKuchen( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        automat.createKuchen( Obstkuchen ,h2, p1, a2, naehrwert1, d1, "MangoCreme"   );
        automat.createKuchen( Obstkuchen ,h1, p1, a1, naehrwert1, d1, "KiwiCreme");

        // Test sorting by Hersteller1
        LinkedList<KuchenImp> sortedList = automat.getListOfKuchenByHersteller("h1");
        assertEquals(2, sortedList.size());
    }


    @Test
    void getListOfKuchenByHersteller_normal() {
        // Tests the Kuchen in  the SortedList if they match the given Hersteller or not,(  Test sorting by h1)
        //GIVEN
        automat.createHersteller("h1");
        automat.createHersteller("h2");
        automat.createKuchen( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        automat.createKuchen( Obsttorte ,h2, p1, a1, naehrwert1, d1, "NugatCreme", "MangoCreme");

        //WHEN
        LinkedList<KuchenImp> sortedList = automat.getListOfKuchenByHersteller("h1");
        //THEN
        assertEquals("h1", sortedList.get(0).getHersteller().getName());
    }


    @Test
    void getArrayOfKuchenByHersteller_size() {
        //GIVEN
        automat.createHersteller("h1");
        automat.createHersteller("h2");
        automat.createHersteller("h3");
        automat.createKuchen( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        automat.createKuchen( Obstkuchen ,h2, p1, a1, naehrwert1, d1, "VanillaCreme"   );
        automat.createKuchen( Obsttorte ,h3, p1, a1, naehrwert1, d1, "ChocoCreme","KiwiCreme"   );
        automat.createKuchen( Obsttorte ,h1, p1, a2, naehrwert1, d1, "ChocoCreme","MangoCreme");
            //WHEN
            int lengthOfKuchenarrayByHersteller = automat.getArrayOfKuchenByHersteller("h1").length;
            //THEN
            assertEquals(2, lengthOfKuchenarrayByHersteller);
        }

    @Test
    void getAllergenList_InAutomat() {
        //GIVEN
        a1.add(Allergen.Gluten);
        automat.createHersteller("h1");
        automat.createKuchen( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme"   );
        //WHEN
        Collection<Allergen> allergens = automat.getAllergenList(true);
        //THEN
        assertTrue(allergens.contains(Allergen.Erdnuss));
    }



    @Test
    void update_Inspectiondate_Normal() {
        //GIVEN
        automat.createHersteller("h1");
        automat.createKuchen(Kremkuchen, h1, p1, a1, naehrwert1, d1, "NugatCreme");
        //WHEN & THEN
        assertDoesNotThrow(() -> {
            automat.update(0);
        });
    }


    @Test
    void update_Inspectiondate_NotExistingKuchen() {
        //GIVEN
        automat.createHersteller("h1");
        automat.createKuchen(Kremkuchen, h1, p1, a1, naehrwert1, d1, "NugatCreme");
        //WHEN & THEN
        assertThrows(IndexOutOfBoundsException.class, ()-> automat.update(1));
    }


    @Test
    public void test_InputWithOnlyNumbers() {
        //GIVEN
        String input = "1";
        //WHEN & THEN
        boolean result = automat.isNotOnlyNumbers(input);
        assertFalse(result);
    }


    @Test
    void test_GetAllergenListInAutomat() {
        //GIVEN
        automat.createHersteller("h1");
        automat.createKuchen(Kremkuchen, h1, p1, a1, naehrwert1, d1, "NugatCreme"); //a1 has Erdnuss and Haselnuss
        Collection<Allergen> allergens = automat.getAllergenList(true);
        assertEquals("[Erdnuss, Haselnuss]", allergens.toString());
    }



    @Test
    void test_GetAllergensWithAllergen() {
        automat.createHersteller("h1");
        automat.createKuchen(Kremkuchen, h1, p1, a1, naehrwert1, d1, "NugatCreme"); //a1 has Erdnuss and Haselnuss
        Collection<Allergen> allergens = automat.getAllergens();
        assertTrue(allergens instanceof ArrayList);
        assertTrue(allergens.contains(Allergen.Haselnuss));
    }







}


