package domainLogic.hersteller;





import java.util.ArrayList;
import java.util.List;

public class HerstellerVerwaltung implements java.io.Serializable {

    public HerstellerVerwaltung(){
    }

    public List<HerstellerImp> listOfHersteller = new ArrayList<>();

    /**
     * adds a Hersteller to a List of Hersteller
     * @param name of the added Hersteller
     * @throws IllegalArgumentException it the added Hersteller already exists or the name of is null
     */
    public  void create(String name) throws IllegalArgumentException {
        for (HerstellerImp h : listOfHersteller) {
            if (h.getName().equals(name))  {
                throw new IllegalArgumentException("Name of Hersteller already exists!");
            } else if (  name == null)  {
                throw new NullPointerException("Name of Hersteller cant be null!");
            }
        }

        listOfHersteller.add(new HerstellerImp(name));
    }



    /**
     * remove a given Hersteller from the list of Hersteller
     * @param name Hersteller to be removed
     * @throws NullPointerException if the list of Hersteller is empty
     */
    public void deleteHersteller(String name) throws NullPointerException {
        for (HerstellerImp h : listOfHersteller) {
            if( h.getName().equals(name)  ){
                listOfHersteller.remove(h);
            }
        }
        if(listOfHersteller.size() == 0) throw new NullPointerException("HerstellerList is empty!");
    }


    /**
     *
     * @return array Of Hersteller from a list of Hersteller
     */
    public HerstellerImp[] readListOfHersteller(){
        Object[] temp = listOfHersteller.toArray();
        HerstellerImp[] allHersteller = new HerstellerImp[listOfHersteller.size()];
        for (int i = 0; i < listOfHersteller.size(); i++) {
            allHersteller[i] = (HerstellerImp) temp[i];
        }
        return allHersteller;
    }


    /**
     *
     * @return list of Hersteller (Helper Method for listHerstellerWithNumberOfKuchen() in Automat)
     */
    public synchronized List<HerstellerImp> getListOfHersteller() {
        List<HerstellerImp> herstellerListe;
        herstellerListe = this.listOfHersteller;
        return herstellerListe;
    }




    public  synchronized boolean containsHersteller(String name) {
        for (HerstellerImp h : listOfHersteller) {
            if (h.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }










}
