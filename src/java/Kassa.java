import java.util.Iterator;

public class Kassa {

    /**
     * Constructor
     */

    private KassaRij kassarij;
    private int totaalAantalArtikelen;
    private double geldInKassa=100.05;
    private double totaalToegevoegd=0;

    public Kassa(KassaRij kassarij) {
        this.kassarij = kassarij;

    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op.
     * Tel deze gegevens op bij de controletotalen die voor
     * de kassa worden bijgehouden. De implementatie wordt
     * later vervangen door een echte betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {
        double totaalPrijs = klant.getTotaalPrijs();
        int aantalArtikelen = klant.getAantalArtikelen();
        totaalAantalArtikelen+=aantalArtikelen;
        geldInKassa+=totaalPrijs;
        totaalToegevoegd+=totaalPrijs;
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd,
     * vanaf het moment dat de methode resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return totaalAantalArtikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass
     * zijn gepasseerd, vanaf het moment dat de methode
     * resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return geldInKassa;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en
     * de totale hoeveelheid geld in de kassa.
     */
    public void resetKassa() {
        geldInKassa-=totaalToegevoegd;
        totaalToegevoegd=0;
        totaalAantalArtikelen = 0;
    }

    public KassaRij getKassarij() {
        return kassarij;
    }

    public void setKassarij(KassaRij kassarij) {
        this.kassarij = kassarij;
    }

    public int getTotaalAantalArtikelen() {
        return totaalAantalArtikelen;
    }

    public void setTotaalAantalArtikelen(int totaalAantalArtikelen) {
        this.totaalAantalArtikelen = totaalAantalArtikelen;
    }

    public double getGeldInKassa() {
        return geldInKassa;
    }

    public void setGeldInKassa(double geldInKassa) {
        this.geldInKassa = geldInKassa;
    }

    public double getTotaalToegevoegd() {
        return totaalToegevoegd;
    }

    public void setTotaalToegevoegd(double totaalToegevoegd) {
        this.totaalToegevoegd = totaalToegevoegd;
    }
}
