import domainLogic.Automat;
import domainLogic.hersteller.HerstellerImp;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

import static IO.jos.SaveAndLoadJOS.loadAutomatJOS;
import static IO.jos.SaveAndLoadJOS.saveAutomatJOS;
import static vertrag.Allergen.Erdnuss;
import static vertrag.KuchenTyp.Kremkuchen;

public class ExecuteSaveAndLoadJOS {


    public static void main(String[] args) {

        KuchenVerwaltung kv = new KuchenVerwaltung();
        HerstellerVerwaltung hv = new HerstellerVerwaltung();


        Automat automat = new Automat(kv,hv);


        Collection<Allergen> a = new LinkedList<>();
        Duration d;
        BigDecimal p1;
        BigDecimal p2;
        BigDecimal p3;
        HerstellerImp h1;
        HerstellerImp h2;
        HerstellerImp h3;
        a.add(Erdnuss);
        d =  Duration.ofDays(1);
        p1 = new BigDecimal("1.5");
        p2 = new BigDecimal("0.99");
        p3 = new BigDecimal("3.40");
        h1 = new HerstellerImp("h1");
        h2 = new HerstellerImp("h2");
        hv.create("h1");
        hv.create("h2");
        //creating Objects in the KuchenVerwaltung
        automat.createKuchen(Kremkuchen, h1, p1,a,1,d,"vanillaKrem");
        automat.createKuchen(Kremkuchen, h2, p2,a,10,d,"ChokoKrem");
        automat.createKuchen(Kremkuchen, h2, p3,a,0,d,"nugatKrem");
        //serialisation
        saveAutomatJOS(automat);
        //deserialisation
        for(int i = 0; i < loadAutomatJOS().readArrayOfKuchen().length; i++) {
            System.out.println(loadAutomatJOS().readArrayOfKuchen()[i]);
        }

    }



}


