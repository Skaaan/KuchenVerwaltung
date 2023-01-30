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


    private KuchenVerwaltung kv;
    private HerstellerVerwaltung hv;
    private Automat automat;

    private final HerstellerImp h1 = new HerstellerImp("h1");

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

        automat.createHersteller("h1");

        d1 = Duration.ofDays(2);
        negativeD1 = Duration.ofDays(-6);

        a1.add(Allergen.Erdnuss);
        a2.add(Allergen.Gluten);
        a1.add(Allergen.Haselnuss);

    }


    @Test
    public void testGetAndSetDefaultCapacity() {
        //tests getter and setter for defaultCapacity
        automat.setDefaultCapacity(10);
        assertEquals(10, kv.getDefaultCapacity());

        automat.setDefaultCapacity(20);
        assertEquals(20, kv.getDefaultCapacity());
    }


    @Test
    void create_Hersteller_NormalCase() {
        // Tests if the passed Kremkuchen to the automat is not null
        automat.createHersteller("Skander");
        HerstellerImp addedHersteller = automat.readListOfHersteller()[0];
        assertNotNull(addedHersteller);
    }


    @Test
    void create_Existing_Hersteller() {
        //GIVEN
        automat.createHersteller("Skander");
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, ()-> automat.createHersteller("Skander"))  ;
    }


    @Test
    public void create_Hersteller_NameIsNull() {
        //GIVEN
        automat.createHersteller(null);
        //WHEN & THEN
        assertThrows(NullPointerException.class, () -> automat.createHersteller(null));
    }

    //todo: why does not work
    /*
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
     */

    @Test
    void delete_Existing_Hersteller() {
        kv = Mockito.mock(KuchenVerwaltung.class);
        hv = Mockito.mock(HerstellerVerwaltung.class);
        this.automat = new Automat(kv, hv);

        //GIVEN
        String name = "Hersteller1";
        //WHEN
        automat.deleteHersteller(name);
        //THEN
        verify(hv).deleteHersteller(name);
    }


    @Test
    void delete_NotExistingHersteller() {
        assertThrows(NullPointerException.class, () -> {
            automat.deleteHersteller("h1");
        });
    }


/*
    //In the test, a mock of the HerstellerVerwaltung class is created using mock method from the Mockito library. The mock object is then assigned to the hv property of the test instance of the YourClass. The test then calls the deleteHersteller method and verifies that the deleteHersteller method of the mock object is called exactly once with the argument "test".
    @Test
    void deleteHersteller_validInput_shouldCallDeleteHerstellerMethod() {
        Automat hvMock = mock(Automat.class);
        Automat testInstance = new Automat(kv, hv);
        testInstance.createHersteller("test");
        automat = hvMock;

        testInstance.deleteHersteller("test");

        verify(hvMock, times(1)).deleteHersteller("test");
    }
 */



    @Test
    void deleteHersteller_emptyList_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> automat.deleteHersteller("test"));
    }



    @Test
    void read_Empty_HerstellerVerwaltung() {
        //GIVEN
        //see setUp()
        //WHEN
        int lengthOfHerstellerArray = automat.readListOfHersteller().length;
        //THEN
        assertEquals(0, lengthOfHerstellerArray);
    }


    @Test
    void read_OneHersteller_HerstellerVerwaltung() {
        //GIVEN
        automat.createHersteller("h1");
        //WHEN
        int lengthOfHerstellerArray = automat.readListOfHersteller().length;
        //THEN
        assertEquals(1, lengthOfHerstellerArray);
    }


    @Test
    void read_ThreeHersteller_HerstellerVerwaltung() {
        //GIVEN
        automat.createHersteller("h1");
        automat.createHersteller("h2");
        automat.createHersteller("h3");
        //WHEN
        int lengthOfHerstellerArray = automat.readListOfHersteller().length;
        //THEN
        assertEquals(3, lengthOfHerstellerArray);
    }


/*
        private KuchenImp[] kuchenArray = new KuchenImp[] {
                new KuchenImp("apple pie", new HerstellerImp("Hersteller A")),
                new KuchenImp("cheese cake", new HerstellerImp("Hersteller B")),
                new KuchenImp("strawberry tart", new HerstellerImp("Hersteller A"))
        };

        private HerstellerImp[] allHersteller = new HerstellerImp[] {
                new HerstellerImp("Hersteller A"),
                new HerstellerImp("Hersteller B"),
                new HerstellerImp("Hersteller C")
        };
 */



    @Test
    void listHerstellerWithNumberOfKuchen_WithoutHersteller() {

        //GIVEN
        kv = Mockito.mock(KuchenVerwaltung.class);
        hv = Mockito.mock(HerstellerVerwaltung.class);
        this.automat = new Automat(kv, hv);

        when(hv.getListOfHersteller() ).thenReturn(new LinkedList<>());
        when(kv.readArrayOfKuchen() ).thenReturn(new KuchenImp[0]);

        //WHEN
        Map result = automat.listHerstellerWithNumberOfKuchen();
        //THEN
        assertTrue(result.isEmpty());
    }

//todo: stopped here
    @Test
    void listHerstellerWithKuchenHerstellerWithoutCakes() {
        //GIVEN
        kv = Mockito.mock(KuchenVerwaltung.class);
        hv = Mockito.mock(HerstellerVerwaltung.class);
        this.automat = new Automat(kv, hv);

        HerstellerImp h1 = new HerstellerImp("Hersteller1");
        HerstellerImp h2 = new HerstellerImp("Hersteller2");
        HerstellerImp h3 = new HerstellerImp("Hersteller3");

        List<HerstellerImp> HerstellerList = new LinkedList<>();
        HerstellerList.add(h1);
        HerstellerList.add(h2);
        HerstellerList.add(h3);

        when(hv.getListOfHersteller()).thenReturn(HerstellerList);
        when(kv.readArrayOfKuchen()).thenReturn(new KuchenImp[0]);

        //WHEN
        Map<HerstellerImp, Integer> result = automat.listHerstellerWithNumberOfKuchen();
        //THEN
        assertEquals(0, (int) result.get(h1));
        assertEquals(0, (int) result.get(h3));
        assertEquals(0, (int) result.get(h3));
    }



    @Test
    void ListHerstellerWithKuchenNormal() {
        kv = Mockito.mock(KuchenVerwaltung.class);
        hv = Mockito.mock(HerstellerVerwaltung.class);
        this.automat = new Automat(kv, hv);

        //GIVEN
        HerstellerImp h1 = new HerstellerImp("Hersteller1");
        HerstellerImp h2 = new HerstellerImp("Hersteller2");
        HerstellerImp h3 = new HerstellerImp("Hersteller3");
        List<HerstellerImp> herstListe = new LinkedList<>();
        herstListe.add(h1);
        herstListe.add(h2);
        herstListe.add(h3);
        when(hv.getListOfHersteller()).thenReturn(herstListe);

        KuchenImp kuchen1 = new ObstKuchenImp(Obstkuchen ,h3, null, a1, naehrwert1, d1, new Date(), 0, "Kiwi");
        KuchenImp kuchen2 = new ObstKuchenImp(Obstkuchen ,h2, null, a1, naehrwert1, d1, new Date(), 0, "Kiwi");
        KuchenImp kuchen3 = new ObstKuchenImp(Obstkuchen ,h2, null, a1, naehrwert1, d1, new Date(), 0, "Kiwi");
        KuchenImp kuchen4 = new ObstKuchenImp(Obstkuchen ,h2, null, a1, naehrwert1, d1, new Date(), 0, "Kiwi");
        KuchenImp[] kuchenListe = {kuchen1, kuchen2, kuchen3, kuchen4};
        when(kv.readArrayOfKuchen()).thenReturn(kuchenListe);

        //WHEN
        Map<HerstellerImp, Integer> result = automat.listHerstellerWithNumberOfKuchen();

        //THEN
        assertEquals(0, (int) result.get(h1));
        assertEquals(3, (int) result.get(h2));
        assertEquals(1, (int) result.get(h3));
    }


//----


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
        //WHEN
        automat.createKuchen(Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme");
        KuchenImp addedKuchen = automat.getListOfKuchen().get(0);
        //THEN
        assertNotNull(addedKuchen);
    }

    @Test
    void createKuchen_KuchenTyp_ObstKuchen() {
        // Tests if the passed Kremkuchen to listOfKuchen not null is.

        //GIVEN
        //see setUp()
        //WHEN
        automat.createKuchen(Obstkuchen ,h1, p1, a1, naehrwert1, d1,  "ApfelCreme");
        KuchenImp addedKuchen = automat.getListOfKuchen().get(0);
        //THEN
        assertNotNull(addedKuchen);
    }

    @Test
    void createKuchen_KuchenTyp_ObstTorte() {
        // Tests if the passed Kremkuchen to listOfKuchen not null is.
        automat.createKuchen(Obsttorte ,h1, p1, a1, naehrwert1, d1, "ApfelCreme", "NugatCreme");
        KuchenImp addedKuchen = automat.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
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
    void createKuchen_Topping_IsNull() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(Obstkuchen, h1, p1, a1, naehrwert1, d1,  null) );
    }

    @Test
    void createKuchen_Topping_notMatchKuchenType() {
        //GIVEN
        //see SetUp()
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> automat.createKuchen(Kremkuchen, h1, p1, a1, naehrwert1, d1,  "ApfelCreme", "NugatCreme") );
    }



    @Test
    void delete_OneKuchen() {
        // Tests the length of output after deleting the only 1 Kremkuchen from automat
        //GIVEN
        automat.createKuchen(Kremkuchen ,h1, p1, a1, naehrwert1, d1,  "NugatCreme");
        //WHEN
        automat.delete(0);
        KuchenImp[] automatArray = automat.readArrayOfKuchen();
        int automatArrayLength = automatArray.length;
        //THEN
        assertEquals(0,automatArrayLength);
    }


    @Test
    void delete_TwoKuchen() {
        // Tests the length of output after deleting the only 1 Kremkuchen from automat
        //GIVEN
        automat.createKuchen(Kremkuchen ,h1, p1, a1, naehrwert1, d1,  "NugatCreme"); //fachnummer:0
        automat.createKuchen(Obsttorte ,h1, p1, a1, naehrwert1, d1,  "NugatCreme", "ApfelCreme"); //fachnummer:1
        //WHEN
        automat.delete(0);  //After that the first Kuchen is deleted, the other Kuchen will take his fachnummer (0)
        automat.delete(0);
        KuchenImp[] automatArray = automat.readArrayOfKuchen();
        int automatArrayLength = automatArray.length;
        //THEN
        assertEquals(0,automatArrayLength);
    }


    @Test
    void delete_NotExistingKuchen() {
        //GIVEN
        //see setUp()
        //WHEN & THEN
        assertThrows(IndexOutOfBoundsException.class, () -> {
            automat.delete(0);
        });

    }
    //todo stopped here








}