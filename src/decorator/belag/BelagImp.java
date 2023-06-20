package decorator.belag;

import decorator.boden.KuchenBodenImp;
import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class BelagImp extends KuchenBodenImp {


    public BelagImp(){
    }

    public BelagImp(Duration duration, Collection<Allergen> allergen, int naehrwert, BigDecimal price){
        super(duration,  allergen, naehrwert,  price);
    }






    @Override
    public String toString() {
        return "KuchenClass{" +
                ", Duration=" + getDuration() +
                ", Allergen=" + getAllergen() +
                ", Naehrwert=" + getNaehrwert() +
                ", Price=" + getPrice() +
                '}';
    }


}
