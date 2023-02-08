import domainLogic.Automat;
import domainLogic.hersteller.HerstellerImp;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;
import domainLogic.kuchen.KuchenImp;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static IO.jbp.SaveAndLoadJBP.*;
import static vertrag.Allergen.Erdnuss;
import static vertrag.KuchenTyp.Kremkuchen;

public class ExecuteSaveAndLoadJBP {


    public static void main(String[] args) throws Exception {

        KuchenVerwaltung kv = new KuchenVerwaltung();
        HerstellerVerwaltung hv = new HerstellerVerwaltung();
        Automat automat = new Automat(kv, hv);



        Collection<Allergen> a1 = new LinkedList<>();
        Duration d1;
        BigDecimal p1;
        a1.add(Erdnuss);
        d1 =  Duration.ofDays(1);
        p1 = new BigDecimal("1.5");
        HerstellerImp h1 = new HerstellerImp("h1");
        HerstellerImp h2 = new HerstellerImp("h2");


        automat.createHersteller("h1");
        automat.createHersteller("h2");
        automat.createKuchen(Kremkuchen, h1, p1,a1,1,d1,"vanilla");
        automat.createKuchen(Kremkuchen, h2, p1,a1,10,d1,"Choko");
        //saveJBP
         saveKuchenVerwaltungJBP(kv);


        //loadJBP
        List<KuchenImp> loadedItems  ;
        loadedItems = (List<KuchenImp>) loadKuchenVerwaltungJBP();

            for(int i=0;i<loadedItems.size();i++){
                System.out.println(loadedItems.get(i));
            }


    }
}
