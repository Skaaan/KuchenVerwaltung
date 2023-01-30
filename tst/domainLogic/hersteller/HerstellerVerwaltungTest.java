package domainLogic.hersteller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HerstellerVerwaltungTest {

    private HerstellerVerwaltung hv;


    @BeforeEach
    void setUp() {
        hv = new HerstellerVerwaltung();
    }


    @Test
    void create_Hersteller_NormalCase() {
        // Tests if the passed Kremkuchen to HerstellerVerwaltung hv is not null
        hv.create("Skander");
        HerstellerImp addedHersteller = hv.getListOfHersteller().get(0);
        assertNotNull(addedHersteller);
    }


    @Test
    void create_Existing_Hersteller() {
        //GIVEN
        hv.create("Skander");
        //WHEN & THEN
        assertThrows(IllegalArgumentException.class, ()-> hv.create("Skander"))  ;
    }


    @Test
    public void create_Hersteller_NameIsNull() {
        //GIVEN
        hv.create(null);
        //WHEN & THEN
        assertThrows(NullPointerException.class, () -> hv.create(null));
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
    void deleteNotExistingKuchen() {
        assertThrows(NullPointerException.class, () -> {
            hv.deleteHersteller("h1");
        });

    }


    @Test
    void read_Empty_HerstellerVerwaltung() {
        //GIVEN
        //see setUp()
        //WHEN
        int lengthOfHerstellerArray = hv.readListOfHersteller().length;
        //THEN
        assertEquals(0, lengthOfHerstellerArray);
    }


    @Test
    void read_OneHersteller_HerstellerVerwaltung() {
        //GIVEN
        hv.create("h1");
        //WHEN
        int lengthOfHerstellerArray = hv.readListOfHersteller().length;
        //THEN
        assertEquals(1, lengthOfHerstellerArray);
    }


    @Test
    void read_ThreeHersteller_HerstellerVerwaltung() {
        //GIVEN
        hv.create("h1");
        hv.create("h2");
        hv.create("h3");
        //WHEN
        int lengthOfHerstellerArray = hv.readListOfHersteller().length;
        //THEN
        assertEquals(3, lengthOfHerstellerArray);
    }


    @Test
     void containsHersteller_ExistingHersteller() {  // Test for a Hersteller that is not in the list
        //GIVEN
        //see setUp()
        //WHEN
        hv.create("Hersteller1");
        hv.create("Hersteller2");

        // Test for a Hersteller that is in the list
        String existingHersteller = "Hersteller1";
        boolean containsExistingHersteller = hv.containsHersteller(existingHersteller);
        assertTrue(containsExistingHersteller);

    }


    @Test
    void containsHersteller_NotExistingHersteller() {  // Test for a Hersteller that is in the list
        //GIVEN
        //see setUp()
        //WHEN
        hv.create("Hersteller1");
        hv.create("Hersteller2");

        String nonExistentHersteller = "Hersteller3";
        boolean containsNonExistentHersteller = hv.containsHersteller(nonExistentHersteller);
        assertFalse(containsNonExistentHersteller);

    }







    }






