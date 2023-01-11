package domainLogic;



import java.util.ArrayList;
import java.util.List;

public class HerstellerVerwaltung implements java.io.Serializable {



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
            } if (  name == null)  {
                throw new IllegalArgumentException("Name of Hersteller cant be null!");
            }
        }
        HerstellerImp h1 = new HerstellerImp(name);
        listOfHersteller.add(h1);
    }



    /**
     * remove a given Hersteller from the list of Hersteller
     * @param h Hersteller to be removed
     * @throws NullPointerException if the list of Hersteller is empty
     */
    public void delete(HerstellerImp h) throws NullPointerException {
        if(listOfHersteller.size() == 0) throw new NullPointerException("Kuchenverwaltung ist leer!");
        listOfHersteller.remove(h);
    }



    public HerstellerImp[] read(){
        Object[] temp = listOfHersteller.toArray();
        HerstellerImp[] allHersteller = new HerstellerImp[listOfHersteller.size()];
        for (int i = 0; i < listOfHersteller.size(); i++) {
            allHersteller[i] = (HerstellerImp) temp[i];
        }
        return allHersteller;
    }








}
