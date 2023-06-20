package decorator.boden;

import decorator.KuchenTeilImp;
import vertrag.Allergen;
import vertrag.KuchenBoden;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class KuchenBodenImp extends KuchenTeilImp implements KuchenBoden {


    public KuchenBodenImp(){
    }

    public KuchenBodenImp( Duration duration, Collection<Allergen> allergen, int naehrwert, BigDecimal price){
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
