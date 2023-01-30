package domainLogic;

import domainLogic.hersteller.HerstellerImp;
import domainLogic.hersteller.HerstellerVerwaltung;
import domainLogic.kuchen.KuchenImp;
import domainLogic.kuchen.KuchenVerwaltung;
import vertrag.Allergen;
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


    public Automat(KuchenVerwaltung kuchenAdministration, HerstellerVerwaltung herstellerVerwaltung) {
        this.kv = kuchenAdministration;
        this.hv = herstellerVerwaltung;
    }


    public void setDefaultCapacity(int defaultCapacity) {
        kv.setDefaultCapacity(defaultCapacity);
    }



    public synchronized void createHersteller(String name) {
            hv.create(name);
    }


    public synchronized void deleteHersteller(String hersteller) {
            hv.deleteHersteller(hersteller);
    }

    public synchronized HerstellerImp[] readListOfHersteller() {
        return hv.readListOfHersteller();
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




    public synchronized void create(KuchenImp k) throws IllegalArgumentException {
            kv.create(k);
        }




    public synchronized void createKuchen(KuchenTyp kuchentyp, HerstellerImp hersteller, BigDecimal price, Collection<Allergen> allergens, int naehrwert, Duration duration, String... topping) throws IllegalArgumentException {
        validateInput(kuchentyp, hersteller, allergens, naehrwert, duration, price, topping);
        if(!hv.containsHersteller(hersteller.getName())){
            throw new IllegalArgumentException("Hersteller (" + hersteller + ") does not exists.");
        }
        if (kuchentyp.equals(Kremkuchen) || kuchentyp.equals(Obstkuchen)) {
            if (topping.length > 1) {
                throw new IllegalArgumentException("With the given Kuchentyp only one Belag is allowed.");
            }
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
            throw new IllegalArgumentException("Maximum 2 Topping allowed");
        }
    }


    public synchronized void delete(int fachnummer) {
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


    /**
     *
     * @return available allergens in the automat
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



   public static void main(String[] args) {
        KuchenVerwaltung kv = new KuchenVerwaltung();
        HerstellerVerwaltung hv = new HerstellerVerwaltung();

        Automat automat = new Automat(kv, hv);

        automat.setDefaultCapacity(20);



        automat.createHersteller("h1");
       automat.createHersteller("h2");
       automat.createHersteller("h3");
       automat.createHersteller("Mohamed");

         Collection<Allergen> a1 = new LinkedList<>();
         a1.add(Allergen.Erdnuss);
         Duration d1  =  Duration.ofDays(1);
         BigDecimal p1 = new BigDecimal("1.5");
         HerstellerImp h1 = new HerstellerImp("h1");
       HerstellerImp h2 = new HerstellerImp("h3");
       HerstellerImp Mohamed = new HerstellerImp("Mohamed");

        automat.createKuchen(Kremkuchen,h1, p1, a1, 4,  d1, "vanilla" );
        automat.createKuchen(Kremkuchen,h1, p1, a1, 4,  d1, "vanilla" );
        automat.createKuchen(Kremkuchen,h2, p1, a1, 4,  d1, "vanilla" );
        automat.createKuchen(Kremkuchen,h2, p1, a1, 4,  d1, "vanilla" );
        automat.createKuchen(Kremkuchen,Mohamed, p1, a1, 4,  d1, "vanilla" );


        automat.delete(4);


        for(int i=0; i<  automat.readListOfHersteller().length; i++) {
            System.out.println( automat.readListOfHersteller()[i]);
        }

        System.out.println("-----------------------------------");

        automat.deleteHersteller("h3");

        for(int i=0; i<  automat.readListOfHersteller().length; i++) {
            System.out.println( automat.readListOfHersteller()[i]);
        }

        System.out.println("-----------------------------------");

        for(int i=0; i<  automat.readArrayOfKuchen().length; i++) {
            System.out.println( automat.readArrayOfKuchen()[i]);
        }

       System.out.println("------------Sorted-----------------------");

       for(int i=0; i<  automat.getArrayOfKuchenByHersteller("h1").length; i++) {
           System.out.println( automat.getArrayOfKuchenByHersteller("h1")[i]);
       }




    }








}