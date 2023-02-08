package CLI;



import IO.jos.SaveAndLoadJOS;
import domainLogic.Automat;
import domainLogic.hersteller.HerstellerImp;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CLITest {
    private ConsoleImp consoleImp;
    private KuchenVerwaltung kvMock;
    private HerstellerVerwaltung hvMock;
    private Automat automat;
    private SaveAndLoadJOS saveAndLoadJOS;
    private Scanner mockScanner;






    @BeforeEach
    void setUp() {
        kvMock = mock(KuchenVerwaltung.class);
        hvMock = mock(HerstellerVerwaltung.class);

        automat = mock(Automat.class);
        consoleImp = new ConsoleImp(automat, saveAndLoadJOS);
        mockScanner = mock(Scanner.class);
    }



    @Test
    void getInputHerstellerTest() {
        when(mockScanner.nextLine()).thenReturn("testHersteller");
        consoleImp.myHersteller = mockScanner;

        String input = consoleImp.getInputHersteller();
        assertEquals("testHersteller", input);
    }

    @Test
    void runAddHerstellerTest() {
        when(mockScanner.nextLine()).thenReturn("testHersteller");
        consoleImp.myHersteller = mockScanner;

        String input = consoleImp.runAddHersteller();
        assertEquals("testHersteller", input);
        Mockito.verify(automat).createHersteller("testHersteller");
    }



    @Test
    void runShowHerstellerWith_HerstellerAndKuchen() {
        Map<HerstellerImp, Integer> herstellerWithNumberOfCakes = new HashMap<>();
        HerstellerImp hersteller1 = new HerstellerImp("Hersteller 1");
        HerstellerImp hersteller2 = new HerstellerImp("Hersteller 2");
        herstellerWithNumberOfCakes.put(hersteller1, 1);
        herstellerWithNumberOfCakes.put(hersteller2, 2);

        when(automat.listHerstellerWithNumberOfKuchen()).thenReturn(herstellerWithNumberOfCakes);
        consoleImp.runShowHerstellerWithKuchen();
        verify(automat).listHerstellerWithNumberOfKuchen();
    }


    @Test
    void runShowHerstellerWith_NoKuchen_NoHersteller() {
        Map<HerstellerImp, Integer> herstellerWithNumberOfCakes = new HashMap<>();
        when(automat.listHerstellerWithNumberOfKuchen()).thenReturn(herstellerWithNumberOfCakes);
        consoleImp.runShowHerstellerWithKuchen();
        verify(automat).listHerstellerWithNumberOfKuchen();
    }


















}







