import domainLogic.HerstellerImp;
import domainLogic.KuchenVerwaltung;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

import static IO.jos.SaveAndLoadJOS.loadKuchenVerwaltungJOS;
import static IO.jos.SaveAndLoadJOS.saveKuchenVerwaltungJOS;
import static vertrag.Allergen.Erdnuss;
import static vertrag.KuchenTyp.Kremkuchen;

public class ExecuteSaveAndLoadJOS {


    public static void main(String[] args) {
        KuchenVerwaltung obj = new KuchenVerwaltung();

        Collection<Allergen> a = new LinkedList<>();
        Duration d;
        BigDecimal p;
        HerstellerImp h;
        a.add(Erdnuss);
        d =  Duration.ofDays(1);
        p = new BigDecimal("1.5");
        h = new HerstellerImp("h1");
        //creating Objects in the KuchenVerwaltung
        obj.create(Kremkuchen, h, p,a,1,d,"vanilla");
        obj.create(Kremkuchen, h, p,a,10,d,"Choko");
        obj.create(Kremkuchen, h, p,a,0,d,"Haselnuss");
        //serialisation
        saveKuchenVerwaltungJOS(obj);
        //deserialisation
        for(int i = 0; i < loadKuchenVerwaltungJOS().read().length; i++) {
            System.out.println(loadKuchenVerwaltungJOS().read()[i]);
        }

    }



}
