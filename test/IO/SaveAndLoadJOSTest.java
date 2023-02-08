package IO;

import static org.junit.jupiter.api.Assertions.*;
import static vertrag.KuchenTyp.Kremkuchen;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

import IO.jos.SaveAndLoadJOS;
import domainLogic.Automat;
import domainLogic.hersteller.HerstellerImp;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vertrag.Allergen;

public class SaveAndLoadJOSTest {



    private static final String FILENAME = "src/IO/jos/savedKuchenVerwaltung.txt";
    private SaveAndLoadJOS saveAndLoadJOS;
    private Automat automat;
    private KuchenVerwaltung kv;
    private HerstellerVerwaltung hv;
    private final HerstellerImp h1 = new HerstellerImp("h1");
    private final HerstellerImp h2 = new HerstellerImp("h2");

    private final BigDecimal p1 = new BigDecimal(1);
    private Duration d1;

    private final Collection<Allergen> a1 = new LinkedList<>();
    private final int naehrwert1 = 1;


    @BeforeEach
    void setUp() {
        kv = new KuchenVerwaltung();
        hv = new HerstellerVerwaltung();
        automat = new Automat(kv, hv);

        saveAndLoadJOS = new SaveAndLoadJOS(automat);

        d1 = Duration.ofDays(2);

        a1.add(Allergen.Erdnuss);
        a1.add(Allergen.Haselnuss);


    }



    @Test
    void saveAutomatJOS_TestFileNotNull() throws IOException {
        saveAndLoadJOS.saveAutomatJOS(automat);
        FileInputStream fileIn = new FileInputStream(FILENAME);
        assertNotNull(fileIn);
        fileIn.close();
    }

    @Test
    void loadAutomatJOS_savedAutomatNotNull() throws IOException {
        saveAndLoadJOS.saveAutomatJOS(automat);
        Automat loadedAutomat = saveAndLoadJOS.loadAutomatJOS();
        assertNotNull(loadedAutomat);
    }


    @Test
    void loadAutomatJOS_HerstellerName_AreSame() throws IOException {
        automat.createHersteller("h1");
        saveAndLoadJOS.saveAutomatJOS(automat);
        Automat loadedAutomat = saveAndLoadJOS.loadAutomatJOS();
        assertEquals(automat.getListOfHersteller().get(0).toString(), loadedAutomat.getListOfHersteller().get(0).toString());
    }

    @Test
    void loadAutomatJOS_KuchenValues_AreSame() throws IOException {
        automat.createHersteller("h1");
        automat.createKuchen( Kremkuchen ,h1, p1, a1, naehrwert1, d1, "NugatCreme");
        saveAndLoadJOS.saveAutomatJOS(automat);
        Automat loadedAutomat = saveAndLoadJOS.loadAutomatJOS();


        assertEquals(automat.getListOfHersteller().get(0).toString(), loadedAutomat.getListOfHersteller().get(0).toString());
    }



}
