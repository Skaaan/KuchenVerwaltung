package decorator.boden;

import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class HefeteigBodenImp extends KuchenBodenImp {

    private String name;

    @Override
    public String toString() {
        return "HefeteigBodenImp{" +
                "name='" + name  +
                ", Duration=" + getDuration() +
                ", Allergen=" + getAllergen() +
                ", Naehrwert=" + getNaehrwert() +
                ", Price=" + getPrice() +
                '}';
    }

    public HefeteigBodenImp(){
    }


    public HefeteigBodenImp(String name, Duration duration, Collection<Allergen> allergen, int naehrwert, BigDecimal price){
        super(duration,  allergen, naehrwert,  price);
        this.name = name;
    }


}
