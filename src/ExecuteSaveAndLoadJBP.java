import domainLogic.HerstellerImp;
import domainLogic.KuchenImp;
import domainLogic.KuchenVerwaltung;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static IO.jbp.SaveAndLoadJBP.*;
import static IO.jos.SaveAndLoadJOS.loadKuchenVerwaltungJOS;
import static vertrag.Allergen.Erdnuss;
import static vertrag.KuchenTyp.Kremkuchen;

public class ExecuteSaveAndLoadJBP {


    public static void main(String[] args) throws Exception {
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
        //saveJBP
        saveKuchenVerwaltungJBP(obj);

        //loadJBP
        List<KuchenImp> loadedItems  ;
        loadedItems = loadKuchenVerwaltungJBP();

            for(int i=0;i<loadedItems.size();i++){
                System.out.println(loadedItems.get(i));
            }





    }
}
