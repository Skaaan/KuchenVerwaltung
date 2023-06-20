package decorator;

import vertrag.Allergen;
import vertrag.KuchenTeil;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public  class KuchenTeilImp implements KuchenTeil {

    private Collection<Allergen> allergen;
    private int naehrwert;
    private Duration duration;
    private BigDecimal price;

    public KuchenTeilImp(){
    }



    public KuchenTeilImp(Duration duration, Collection<Allergen> allergen, int naerwert, BigDecimal price){
        this.duration = duration;
        this.allergen = allergen;
        this.naehrwert = naerwert;
        this.price = price;
    }



    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public Collection<Allergen> getAllergen() {
        return allergen;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int getNaehrwert() {
        return naehrwert;
    }


    @Override
    public String toString() {
        return "KuchenTeil{" +
                "allergen=" + allergen +
                ", naehrwert=" + naehrwert +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}