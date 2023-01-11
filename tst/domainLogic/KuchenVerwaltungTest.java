package domainLogic;

import vertrag.Allergen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static vertrag.KuchenTyp.Kremkuchen;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class KuchenVerwaltungTest {

    KuchenVerwaltung v = new KuchenVerwaltung();
    private final Collection<Allergen> a1 = new LinkedList<>();
    private Duration d1;
    private BigDecimal p1;
     HerstellerImp h1;

    @BeforeEach
    void setUp() {
        a1.add(Allergen.Erdnuss);
        d1 =  Duration.ofDays(1);
        p1 = new BigDecimal("1.5");
        h1 = new HerstellerImp("h1");

    }

    @Test
        // Test  if the passed  vordefinierten Kuchen to listOfKuchen is not nul
    void create_Kremkuchen() { //
        KuchenImp addedKuchen = new KremkuchenImp(h1, a1, 4, d1, p1, new Date() ,0,"NugatKrem" );
        assertNotNull(addedKuchen);
    }

    @Test
    void create_KuchenTyp_Kremkuchen() {
        // Tests if the passed Kremkuchen to listOfKremkuchen is not null
        v.create(Kremkuchen ,h1, p1, a1, 4, d1, "NugatKrem");
        KuchenImp addedKuchen = v.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }

    @Test
    void create_KuchenTyp_ObstKuchen() {
        // Tests if the passed Kremkuchen to listOfKuchen not null is.
        v.create(Kremkuchen ,h1, p1, a1, 4, d1,  "Apfel");
        KuchenImp addedKuchen = v.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }

    @Test
    void create_KuchenTyp_ObstTorte() {
        // Tests if the passed Kremkuchen to listOfKuchen not null is.
        v.create(Kremkuchen ,h1, p1, a1, 4, d1, "Apfel", "NugatKrem");
        KuchenImp addedKuchen = v.getListOfKuchen().get(0);
        assertNotNull(addedKuchen);
    }



    @Test
    void read_LengthOneKuchen()  {
        // Tests the length of output of read() after creating only 1 Kremkuchen
        v.create(Kremkuchen ,h1, p1, a1, 4, d1,  "NugatKrem");
        KuchenImp[] KuchenArray = v.read();
        int KremkuchenArrayLength = KuchenArray.length;
        assertEquals(KremkuchenArrayLength, 1);
    }




    @Test
    void delete_OneKuchen() {
        // Tests the length of output after deleting the only 1 Kremkuchen in the list
        v.create(Kremkuchen ,h1, p1, a1, 4, d1,  "NugatKrem");
        v.delete(0);
        KuchenImp[] KuchenArray = v.read();
        int KuchenArrayLength = KuchenArray.length;
        assertEquals(0,KuchenArrayLength);
    }


    @Test
    void read(){
        KremkuchenImp addedKuchen = new KremkuchenImp(h1, a1, 4, d1, p1, new Date() ,0,"NugatKrem" );
        v.create(addedKuchen);
        v.create(addedKuchen);
        KremkuchenImp [] expectedArray = {addedKuchen,addedKuchen};
        KuchenImp[] resultArray = v.read();

        assertArrayEquals(expectedArray, resultArray);

    }















}