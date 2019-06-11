import java.text.DecimalFormat;
import java.util.Iterator;
import java.math.*;


public class Kassa {

    private KassaRij kassarij;
    private int totaalAantalArtikelen=0;
    private double geldInKassa=100;
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
    void rekenAf(Dienblad klant) {
        //gegevens halen
        double totaalPrijs = getTotaalPrijs(klant);
        int aantalArtikelen = getAantalArtikelen(klant);
        Persoon persoon = klant.getKlant();
        Betaalwijze betaalwijze = persoon.getBetaalwijze();

        //kortingscheck+toepassing
        Class[] interfaces = persoon.getClass().getInterfaces();

//        if(interfaces.length>0) {
//            if ((interfaces[0].getSimpleName()).equals("KortingskaartHouder")) {
//                double kortingspercentage = persoon.geefKortingsPercentage();
//                double maximum = 0;
//                if (persoon.heeftMaximum()) {
//                    maximum = persoon.geefMaximum();
//                }
//                double korting = totaalPrijs - (totaalPrijs * kortingspercentage);
//                totaalPrijs -= korting;
//            }
//        }

        //geld afnemen van klant, toevoegen aan kassa

        if(!betaalwijze.betaal(totaalPrijs)){
            System.out.println("Betaling niet gelukt");
        } else{
        totaalAantalArtikelen += aantalArtikelen;
        geldInKassa += totaalPrijs;
        totaalToegevoegd += totaalPrijs;
        }
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd,
     * vanaf het moment dat de methode resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    int aantalArtikelen() {
        return totaalAantalArtikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass
     * zijn gepasseerd, vanaf het moment dat de methode
     * resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    double hoeveelheidGeldInKassa() {
        return geldInKassa;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en
     * de totale hoeveelheid geld in de kassa.
     */
    void resetKassa() {
        geldInKassa -= totaalToegevoegd;
        totaalToegevoegd = 0;
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
        double totaalprijs = 0;
        while (it.hasNext()){
            Artikel artikel = it.next();
            totaalprijs+=artikel.getPrice();
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
