package domainLogic.kuchen;

import domainLogic.hersteller.HerstellerImp;
import domainLogic.hersteller.HerstellerVerwaltung;
import vertrag.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static vertrag.KuchenTyp.Kremkuchen;
import static vertrag.KuchenTyp.Obstkuchen;
import static vertrag.KuchenTyp.Obsttorte;



public class KuchenVerwaltung implements java.io.Serializable {

    public int getDefaultCapacity() {
        return defaultCapacity;
    }

    public void setDefaultCapacity(int defaultCapacity) {
        this.defaultCapacity = defaultCapacity;
    }

    int defaultCapacity = 30;

    private ArrayList<KuchenImp> listOfKuchen = new ArrayList<KuchenImp>(defaultCapacity);

    public KuchenVerwaltung(ArrayList<KuchenImp> listOfKuchen) {
        this.listOfKuchen=listOfKuchen;
    }

    public KuchenVerwaltung() {
        listOfKuchen = new ArrayList<>(defaultCapacity);
    }




    /**
     * adds a predefined Kuchen to a list of Kuchen
     *
     * @param k predefined kuchen to add to a list of Kuchen
     * @throws IllegalArgumentException if the kuchen don't have a Hersteller
     */
    public synchronized void create(KuchenImp k) throws IllegalArgumentException {

        if (k.getHersteller() == null || k.getHersteller().getName() == null)
            throw new IllegalArgumentException("Kuchen don't has Hersteller!");

           if(listOfKuchen.size() < defaultCapacity ) {
               for (int i = 0; i < listOfKuchen.size() + 1; i++) {
                   k.setFachnummer(i);
               }
               listOfKuchen.add(k);
           } else {
                   System.out.println("KuchenVerwaltung is full, cannot insert Kuchen");
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
     * @throws IllegalArgumentException if the kuchen don't have a Hersteller
     * @throws IllegalArgumentException if the type of the kuchen is null
     */
    public synchronized void create(KuchenTyp kuchentyp, HerstellerImp hersteller,BigDecimal price, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, String... topping) throws IllegalArgumentException {      //Quelle:  https://stackoverflow.com/questions/44640485/implement-a-crud-in-spring-using-an-arraylist-of-a-class
        KuchenImp kuchen = null;

        if (hersteller == null || hersteller.getName() == null)
            throw new IllegalArgumentException("Kuchen don't has Hersteller!");
        else if (kuchentyp == null) {
            throw new IllegalArgumentException("Kuchentyp cant not be null");
        } else {
            for (int i = 0; i < listOfKuchen.size() + 1; i++) {    // warum nicht listOfKuchen.size()
                if (kuchentyp == Kremkuchen) {
                    kuchen = new KremkuchenImp(Kremkuchen, hersteller, price, allergens, naehrwert, haltbarkeit, new Date(), i, topping[0]);
                } else if (kuchentyp == Obstkuchen) {
                    kuchen = new ObstKuchenImp(Obstkuchen, hersteller, price, allergens, naehrwert, haltbarkeit, new Date(), i, topping[0]);
                } else if (kuchentyp == Obsttorte) {
                    kuchen = new ObstTorteImp(Obsttorte, hersteller, price, allergens, naehrwert, haltbarkeit, new Date(), i, topping[0], topping[1]);
                }

            }
        }

        if(listOfKuchen.size() < defaultCapacity ) {
            listOfKuchen.add(kuchen);
            System.out.println("Inserted object" );
        } else {
            System.out.println("KuchenVerwaltung is full, cannot insert Kuchen");
        }


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
     *
     * @return array Of kuchen from a list of kuchen
     */
    public synchronized KuchenImp[] readArrayOfKuchen() {
        Object[] temp = listOfKuchen.toArray();
        KuchenImp[] allKuchen = new KuchenImp[listOfKuchen.size()];
        for (int i = 0; i < listOfKuchen.size(); i++) {
            allKuchen[i] = (KuchenImp) temp[i];
        }
        return allKuchen;
    }


    /**
     * updates the Inspektiondate of a given Kremkuchen to todays date
     *
     * @param fachnummer of the Kremkuchen
     */
    public synchronized void update(int fachnummer) {
        for (KuchenImp k : listOfKuchen) {
            if (k.getFachnummer() == fachnummer) {
                k.setInspektionsdatum(new Date());
            } else throw new IndexOutOfBoundsException("Kuchen with Fachnummer (" + fachnummer + ") does not exist");
        }
    }


    /**
     * remove a given Kremkuchen from the list of Kremkuchen
     *
     * @param fachnummer of the Kuchen to be removed
     * @throws NullPointerException if the list of Kremkuchen is empty
     */
    public synchronized void deleteKuchen(int fachnummer) throws NullPointerException,IllegalArgumentException {

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


    /**
     *
     * @param kuchentyp for sorting the list
     * @return list of Kuchen with only kuchentyp (Kremkuchen, Obstkuchen or Obsttorte)
     */
    public synchronized LinkedList<KuchenImp> SortListOfKuchenByType(KuchenTyp kuchentyp) {
        LinkedList<KuchenImp> listOfKuchenByType = new LinkedList<>();

            for (KuchenImp kuchen : listOfKuchen) {
                if (kuchen != null) {
                    if ((kuchen.getKuchenType()).equals(kuchentyp) ) {
                        listOfKuchenByType.add(kuchen);
                    }
                }
            }

        return listOfKuchenByType;
    }


    /**
     *
     * @param hersteller for sorting the list
     * @return list of Kuchen with only given hersteller
     */
    public synchronized LinkedList<KuchenImp> SortListOfKuchenByHersteller(String hersteller) {
        LinkedList<KuchenImp> listOfKuchenByHersteller = new LinkedList<>();

        for (KuchenImp kuchen : listOfKuchen) {
            if (kuchen != null) {
                if ((kuchen.getHersteller().getName()).equals(hersteller) ) {
                    listOfKuchenByHersteller.add(kuchen);
                }
            }
        }

        return listOfKuchenByHersteller;
    }


    /**
     *
     * @return available allergens in the list of Kuchen
     */
    public synchronized Collection<Allergen> getAllergens() {
        ArrayList<Allergen> allergens = new ArrayList<>();
        for (Kuchen kuchen : listOfKuchen) {
            if (kuchen != null) {
                Collection<Allergen> allergene = kuchen.getAllergene();
                for (Allergen allerg : allergene) {
                    if(!allergens.contains(allerg))
                        allergens.add( allerg);
                }
            }
        }
        return allergens;
    }




    /* public static void main(String[] args) {

        KuchenVerwaltung kv = new KuchenVerwaltung();

        HerstellerVerwaltung hv = new HerstellerVerwaltung();

        Collection<Allergen> a1 = new LinkedList<>();
        a1.add(Allergen.Erdnuss);
        Collection<Allergen> a2 = new LinkedList<>();
        a1.add(Allergen.Gluten);
        Duration d1 = Duration.ofDays(1);
        BigDecimal p1 =  new BigDecimal("1.5");
        HerstellerImp h1 = new HerstellerImp("HerstellerNr1");
        HerstellerImp h2 = new HerstellerImp("HerstellerNr2");

        hv.create(  h1.getName()  );

        kv.create(Kremkuchen, h1 , p1, a1, 10, d1, "Vanille", "Karamell"  );
        kv.create(Kremkuchen, h2, p1, a2, 10, d1, "Kiwi", "Erdbeeren"  );
        kv.create(Kremkuchen, h1 , p1, a1, 10, d1, "Vanille", "Karamell"  );
        kv.create(Kremkuchen, h2, p1, a2, 10, d1, "Kiwi", "Erdbeeren"  );

        System.out.println(kv.readListOfKuchen().length);


        for(int i=0; i< hv.readListOfHersteller().length; i++){
            System.out.println(hv.readListOfHersteller()[i].toString());
        }

        for(int i=0; i< kv.readListOfKuchen().length; i++){
            System.out.println(kv.readListOfKuchen()[i].toString());
        }


        }

     */


    }






