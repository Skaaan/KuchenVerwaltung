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

    int defaultCapacity = 50;

    public ArrayList<KuchenImp> listOfKuchen = new ArrayList<KuchenImp>(defaultCapacity);

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
        if (k.getHersteller() == null || k.getHersteller().getName() == null) {
            throw new IllegalArgumentException("Hersteller cant be null");
        }
           if(listOfKuchen.size() < defaultCapacity ) {
               for (int i = 0; i < listOfKuchen.size() + 1; i++) {
                   k.setFachnummer(i);
               }
               listOfKuchen.add(k);
           } else {
               throw new IndexOutOfBoundsException("KuchenVerwaltung Out of Capacity");
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
     * @throws IndexOutOfBoundsException if listOfKuchen is out of capacity
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
        } else throw new IndexOutOfBoundsException("KuchenVerwaltung Out of Capacity");
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
     * @throws IndexOutOfBoundsException if there is no Kuchen for the given fachnummer
     */
    public synchronized void update(int fachnummer) throws IndexOutOfBoundsException {
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
     * @throws NullPointerException if there is kuchen is null for the given fachnummer
     */
    public synchronized void deleteKuchen(int fachnummer) throws NullPointerException,IllegalArgumentException {

        if (listOfKuchen.get(fachnummer) == null) {
            throw new IllegalArgumentException("Fachnummer" + fachnummer + "is empty");
        } else if (listOfKuchen.size() == 0) {
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







    }






