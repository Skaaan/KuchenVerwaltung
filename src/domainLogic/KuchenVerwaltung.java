package domainLogic;

import vertrag.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static vertrag.KuchenTyp.Kremkuchen;
import static vertrag.KuchenTyp.Obstkuchen;
import static vertrag.KuchenTyp.Obsttorte;


public class KuchenVerwaltung implements java.io.Serializable {

    private final List<KuchenImp> listOfKuchen = new ArrayList<>();

    //https://stackoverflow.com/questions/6270132/create-a-custom-event-in-java/6270150#6270150   Hier


    public synchronized void create(KuchenImp k) throws IllegalArgumentException {

        if (k.getHersteller() == null || k.getHersteller().getName() == null)
            throw new IllegalArgumentException("Kuchen dont has Hersteller!");
        else {
            for (int i = 0; i < listOfKuchen.size() + 1; i++) {
                k.setFachnummer(i);
            }
            listOfKuchen.add(k);
        }
    }


    /**
     * adds a Kuchen to a list of Kuchen by choosing the type of added kuchen
     *
     * @param kuchentyp   of the added kuchen to the list
     * @param hersteller  of the added kuchen
     * @param price       of the added kuchen
     * @param allergens   of the added kuchen
     * @param naehrwert   of the added kuchen
     * @param haltbarkeit of the added kuchen
     * @param topping     of the added kuchen
     * @throws IllegalArgumentException if the kuchen dont has a Hersteller
     * @throws IllegalArgumentException if the type of the kuchen is null
     */
    public synchronized void create(KuchenTyp kuchentyp, HerstellerImp hersteller, BigDecimal price, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, String... topping) throws IllegalArgumentException {      //Quelle:  https://stackoverflow.com/questions/44640485/implement-a-crud-in-spring-using-an-arraylist-of-a-class
        KuchenImp kuchen = null;
        if (hersteller == null || hersteller.getName() == null)
            throw new IllegalArgumentException("Kuchen dont has Hersteller!");
        if (kuchentyp == null) {
            throw new IllegalArgumentException("Kuchentyp cant not be null");
        } else {
            for (int i = 0; i < listOfKuchen.size() + 1; i++) {    // warum nicht listOfKuchen.size()
                if (kuchentyp == Kremkuchen) {
                    kuchen = new KremkuchenImp(hersteller, allergens, naehrwert, haltbarkeit, price, new Date(), i, topping[0]);
                } else if (kuchentyp == Obstkuchen) {
                    kuchen = new ObstKuchenImp(hersteller, allergens, naehrwert, haltbarkeit, price, new Date(), i, topping[0]);
                } else if (kuchentyp == Obsttorte) {
                    kuchen = new ObstTorteImp(hersteller, allergens, naehrwert, haltbarkeit, price, new Date(), i, topping[0], topping[1]);
                }
            }
        }
        listOfKuchen.add(kuchen);
    }


    /**
     * gets the list of Kuchen from the kuchenVerwaltung (Helper Method for tests)
     *
     * @return listOfKuchen
     */
    public synchronized List<KuchenImp> getListOfKuchen() {
        List<KuchenImp> kuchenliste;
        kuchenliste = this.listOfKuchen;
        return kuchenliste;
    }


    /**
     * transform the list of kuchen to an array of kuchen
     *
     * @return array Of kuchen
     */
    public synchronized KuchenImp[] read() {
        Object[] temp = listOfKuchen.toArray();
        KuchenImp[] allKuchen = new KuchenImp[listOfKuchen.size()];
        for (int i = 0; i < listOfKuchen.size(); i++) {
            allKuchen[i] = (KuchenImp) temp[i];
        }
        return allKuchen;
    }


    /**
     * updates the inspektionsdatum of a given Kremkuchen to todays date
     *
     * @param fachnummer of the Kremkuchen
     */
    public synchronized void update(int fachnummer) {
        for (KuchenImp k : listOfKuchen) {
            if (k.getFachnummer() == fachnummer) {
                k.setInspektionsdatum(new Date());
            }
        }
    }


    /**
     * remove a given Kremkuchen from the list of Kremkuchen
     *
     * @param fachnummer of the Kuchen to be removed
     * @throws NullPointerException if the list of Kremkuchen is empty
     */
    public synchronized void delete(int fachnummer) throws NullPointerException {

        if (listOfKuchen.get(fachnummer) == null) {
            throw new IllegalArgumentException("Fachnummer" + fachnummer + "is empty");
        } else if (listOfKuchen.size() == 0) {    // why ???? //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            throw new NullPointerException("listOfKuchen is empty!");
        } else {
            listOfKuchen.remove(listOfKuchen.get(fachnummer));
            for(int i=fachnummer; i<listOfKuchen.size(); i++) {
                (listOfKuchen.get(i)).setFachnummer(i);
            }

        }
    }


}





