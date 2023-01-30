package CLI;

import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenVerwaltung;

public interface Console {

     HerstellerVerwaltung hv = new HerstellerVerwaltung();
     KuchenVerwaltung kv = new KuchenVerwaltung();

     domainLogic.Automat automat = new domainLogic.Automat(kv, hv);




}
