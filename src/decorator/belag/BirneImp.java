package decorator.belag;

import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class BirneImp extends BelagImp{

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

    public BirneImp(){
    }


    public BirneImp(String name, Duration duration, Collection<Allergen> allergen, int naehrwert, BigDecimal price){
        super(duration,  allergen, naehrwert,  price);
        this.name = name;
    }



}
