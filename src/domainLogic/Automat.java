package domainLogic;

import domainLogic.hersteller.HerstellerImp;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenImp;
import domainLogic.kuchen.KuchenVerwaltung;
import vertrag.Allergen;
import vertrag.Kuchen;
import vertrag.KuchenTyp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

import static vertrag.KuchenTyp.Kremkuchen;
import static vertrag.KuchenTyp.Obstkuchen;


public class Automat implements Serializable {
    private final KuchenVerwaltung kv;
    private final HerstellerVerwaltung hv;


    public Automat(KuchenVerwaltung kuchenVerwaltung, HerstellerVerwaltung herstellerVerwaltung) {
        this.kv = kuchenVerwaltung;
        this.hv = herstellerVerwaltung;
    }


    public void setDefaultCapacity(int defaultCapacity) {
        kv.setDefaultCapacity(defaultCapacity);
    }

    public int getDefaultCapacity() {
        return kv.getDefaultCapacity();
    }


//---Hersteller side------

    public synchronized void createHersteller(String name) {
            hv.create(name);

    }


    public synchronized void deleteHersteller(String hersteller) {
            hv.deleteHersteller(hersteller);
    }

    public synchronized HerstellerImp[] readListOfHersteller() {
        return hv.readListOfHersteller();
    }


    public synchronized List<HerstellerImp> getListOfHersteller() {
       return hv.getListOfHersteller();
    }




public synchronized Map<HerstellerImp, Integer> listHerstellerWithNumberOfKuchen() {
        KuchenImp[] kuchenArray = kv.readArrayOfKuchen();
        Map<HerstellerImp, Integer> herstellerWithNumberOfCakes = new HashMap<>();
        List<HerstellerImp> allHersteller = hv.getListOfHersteller();
        for (HerstellerImp hersteller : allHersteller) {
            Integer integer0 = 0;
            herstellerWithNumberOfCakes.put(hersteller, integer0);
        }
        for (KuchenImp kuchen : kuchenArray) {
            for (Map.Entry<HerstellerImp, Integer> entry : herstellerWithNumberOfCakes.entrySet()) {
                if (entry.getKey().toString().equals(kuchen.getHersteller().toString())) {
                    entry.setValue(entry.getValue() + 1);
                }
            }
        }
        return herstellerWithNumberOfCakes;
    }


//---Kuchen side------


    public synchronized void create(KuchenImp k) throws IllegalArgumentException {
            kv.create(k);
        }




    public synchronized void createKuchen(KuchenTyp kuchentyp, HerstellerImp hersteller, BigDecimal price, Collection<Allergen> allergens, int naehrwert, Duration duration, String... topping) throws IllegalArgumentException {
        validateInput(kuchentyp, hersteller, allergens, naehrwert, duration, price, topping);
        if(!hv.containsHersteller(hersteller.getName())){
            throw new IllegalArgumentException("Hersteller (" + hersteller + ") does not exists.");
        }
        kv.create(kuchentyp, hersteller, price, allergens, naehrwert, duration, topping);
    }


    private void validateInput(KuchenTyp kuchentyp, HerstellerImp hersteller, Collection<Allergen> allergens, int naehrwert, Duration duration, BigDecimal price, String[] topping) {
        if (kuchentyp == null) {
            throw new IllegalArgumentException("Kuchentype cant be null");
        }
        if (hersteller == null) {
            throw new IllegalArgumentException("Hersteller cant be null");
        }
        if (allergens == null) {
            throw new IllegalArgumentException("Allergens cant be null");
        }
        if (naehrwert < 0) {
            throw new IllegalArgumentException("Naehrwert must be > 0");
        }
        if (duration == null) {
            throw new IllegalArgumentException("Haltbarkeit cant be null");
        }
        if (duration.isNegative()) {
            throw new IllegalArgumentException("Duration must be > 0");
        }
        if (price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("Price must be > 0");
        }
        if (topping == null) {
            throw new IllegalArgumentException("Topping cant be null");
        }
        if (topping.length > 2) {
            throw new IllegalArgumentException("Maximum 2 topping allowed");
        }
        if (kuchentyp.equals(Kremkuchen) || kuchentyp.equals(Obstkuchen)) {
            if (topping.length == 2) {
                throw new IllegalArgumentException("With " + (kuchentyp) + " only one topping is allowed.");
            }
        }
    }


    public synchronized void deleteKuchen(int fachnummer) {
        if (fachnummer < 0) {
            throw new IllegalArgumentException("Fachnummer must be > 0");
        } else {
            kv.deleteKuchen(fachnummer);
        }
    }

    public synchronized LinkedList<KuchenImp> getListOfKuchenByType(KuchenTyp kuchenType) {
        return kv.SortListOfKuchenByType(kuchenType);
    }

    public synchronized LinkedList<KuchenImp> getListOfKuchenByHersteller(String hersteller) {
        return kv.SortListOfKuchenByHersteller(hersteller);
    }

    public synchronized KuchenImp[] getArrayOfKuchenByHersteller(String hersteller) {
        return (kv.SortListOfKuchenByHersteller(hersteller)).toArray(new KuchenImp[0]);
    }


    public synchronized KuchenImp[] readArrayOfKuchen() {
        return kv.readArrayOfKuchen();
    }


    public synchronized List<KuchenImp> getListOfKuchen() {
       return kv.getListOfKuchen();
    }



    public synchronized Collection<Allergen> getAllergens() {
        ArrayList<Allergen> allergens = new ArrayList<>();
        for (int i=0; i< kv.getListOfKuchen().size(); i++) {
            if ( kv.getListOfKuchen().get(i) != null) {
                Collection<Allergen> allergene =  kv.getListOfKuchen().get(i).getAllergene();
                for (Allergen allerg : allergene) {
                    if(!allergens.contains(allerg))
                        allergens.add( allerg);
                }
            }
        }
        return allergens;
    }


    /**
     *
     * @return available allergens for input parameter true and not available allergens in the Automat for input parameter false
     */
    public synchronized Collection<Allergen> getAllergenList(boolean inAutomat) {
        if (inAutomat) {
            return kv.getAllergens();
        } else {
            Collection<Allergen> listOfAllergens = new LinkedList<>();
            Collection<Allergen> allergens = kv.getAllergens();
            for (Allergen allergen : Allergen.values()) {
                if (!allergens.contains(allergen)) {
                    listOfAllergens.add(allergen);
                }
            }
            return listOfAllergens;
        }
    }

    public synchronized void update(int fachnummer) throws NullPointerException {
        kv.update(fachnummer);
    }












}