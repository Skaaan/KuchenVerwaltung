package domainLogic.kuchen;

import domainLogic.hersteller.HerstellerImp;
import vertrag.Allergen;
import vertrag.Kuchen;
import vertrag.KuchenTyp;
import vertrag.Verkaufsobjekt;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class KuchenImp implements Kuchen, Verkaufsobjekt, java.io.Serializable {


    private  KuchenTyp kuchenType = null;
    private HerstellerImp hersteller;
    private Collection<Allergen> allergen;
    private int naehrwert;
    private Duration haltbarkeit;
    private BigDecimal preis;
    private Date inspektionsdatum;
    private int fachnummer;

    public KuchenImp(){
    }



    public KuchenImp(KuchenTyp kuchenType, HerstellerImp hersteller, BigDecimal preis , Collection<Allergen> allergen, int naehrwert, Duration haltbarkeit, Date inspektionsdatum, int fachnummer){
        this.kuchenType = kuchenType;
        this.hersteller = hersteller;
        this.allergen = allergen;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.preis = preis;
        this.inspektionsdatum = inspektionsdatum;
        this.fachnummer = fachnummer;
    }



    @Override
    public synchronized HerstellerImp getHersteller() {
        return this.hersteller;
    }

    @Override
    public synchronized Collection<Allergen> getAllergene() {
        return this.allergen;
    }

    @Override
    public synchronized int getNaehrwert() {
        return this.naehrwert;
    }

    @Override
    public synchronized Duration getHaltbarkeit() {
        return this.haltbarkeit;
    }

    @Override
    public synchronized BigDecimal getPreis() {
        return this.preis;
    }

    public synchronized void setInspektionsdatum(Date inspektionsdatum) {
        this.inspektionsdatum = inspektionsdatum;
    }

    @Override
    public synchronized Date getInspektionsdatum() {
        return this.inspektionsdatum;
    }

    @Override
    public synchronized int getFachnummer() {
        return this.fachnummer;
    }

    public synchronized void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }


    public synchronized KuchenTyp getKuchenType() {
        return kuchenType;
    }


    @Override
    public String toString() {
        return "KuchenClass{" +
                "hersteller=" + hersteller +
                ", allergen=" + allergen +
                ", naehrwert=" + naehrwert +
                ", haltbarkeit=" + haltbarkeit +
                ", preis=" + preis +
                ", inspektionsdatum=" + inspektionsdatum +
                ", fachnummer=" + fachnummer +
                '}';
    }


}
