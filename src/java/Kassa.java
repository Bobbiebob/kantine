import java.util.Iterator;

public class Kassa {

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
        double totaalPrijs = getTotaalPrijs(klant);
        int aantalArtikelen = getAantalArtikelen(klant);
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


    /**
     * Methode om de totaalprijs van de artikelen
     * op dienblad uit te rekenen
     *
     * @return
     */
    private double getTotaalPrijs(Dienblad klant) {
        Iterator<Artikel> it = klant.getArtikelen().iterator();
        int totaalprijs = 0;
        while (it.hasNext()){
            totaalprijs+=it.next().getPrice();
        }
        return totaalprijs;
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     * @param klant
     * @return int aantal artikelen
     */
    private int getAantalArtikelen(Dienblad klant){
        return klant.getArtikelen().size();
    }
}
