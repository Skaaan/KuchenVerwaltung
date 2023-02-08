package domainLogic.hersteller;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class HerstellerImpTest {


    HerstellerImp h1 = new HerstellerImp("Hersteller1");

    @Test
    void testGetName() {
        assertEquals("Hersteller1", h1.getName());
    }

    @Test
    void testToString() {
        assertEquals("Hersteller1", h1.toString());
    }
}