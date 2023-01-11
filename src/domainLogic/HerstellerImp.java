package domainLogic;

import vertrag.Hersteller;

import java.io.Serializable;

public class HerstellerImp implements Hersteller, java.io.Serializable {

    private String name;

    public HerstellerImp() {
    }

    public HerstellerImp(String name) {
        this.name = name;
    }



    @Override
    public String getName() {
        return this.name;
    }

    public String toString(){
        return this.name;
    }

}
