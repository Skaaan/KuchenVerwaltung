package decorator.boden;

import vertrag.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class MuerbeteigBodenImp extends KuchenBodenImp{

    private String name;

    public MuerbeteigBodenImp(){
    }


    public MuerbeteigBodenImp(String name, Duration duration, Collection<Allergen> allergen, int naehrwert, BigDecimal price){
        super(duration,  allergen, naehrwert,  price);
        this.name = name;
    }


    @Override
    public String toString() {
        return "MuerbeteigBodenImp{" +
                "name='" + name  +
                ", Duration=" + getDuration() +
                ", Allergen=" + getAllergen() +
                ", Naehrwert=" + getNaehrwert() +
                ", Price=" + getPrice() +
                '}';
    }

}
