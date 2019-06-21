import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.math.*;

import static java.time.LocalDateTime.now;


public class Kassa {

    private KassaRij kassarij;
    private int totaalAantalArtikelen = 0;
    private double geldInKassa = 100;
    private double totaalToegevoegd = 0;
    private double totaalGegevenKorting = 0;

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
        Persoon persoon = klant.getKlant();
        int aantalArtikelen = klant.getArtikelen().size();
        Betaalwijze betaalwijze = persoon.getBetaalwijze();
        LocalDateTime datum = now();
        Factuur factuur = new Factuur(klant,datum);
        double totaalPrijs = factuur.getTotaal();

        //System.out.println(persoon.getVoornaam()+persoon.getAchternaam()+" heeft "+aantalArtikelen+" artikelen gepakt voor "+totaalPrijs);

        boolean genoegGeld = true;
        try {
            betaalwijze.betaal(Administratie.valutaRoundingPrint(totaalPrijs));
        } catch (TeWeinigGeldException e) {
            System.out.println(persoon.getVoornaam() + " " + persoon.getAchternaam() + " " + e.message);
            genoegGeld = false;
        } catch (Exception e) {
            System.out.println("Onverwachte fout: " + e.getClass().getSimpleName());
        } finally {
            if (genoegGeld) {
                totaalAantalArtikelen += aantalArtikelen;
                geldInKassa += totaalPrijs;
                totaalToegevoegd += totaalPrijs;
                totaalGegevenKorting += factuur.getGegevenKorting();
                factuur.setBetaald(true);

            }
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

    double getTotaalGegevenKorting(){
        return totaalGegevenKorting;
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
        totaalGegevenKorting = 0;
    }
}
