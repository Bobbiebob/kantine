import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;

public class Kassa {

    private KassaRij kassarij;
    private int totaalAantalArtikelen = 0;
    private double geldInKassa = 100;
    private double totaalToegevoegd = 0;
    private double totaalGegevenKorting = 0;
    private EntityManager manager;

    public Kassa(KassaRij kassarij, EntityManager manager) {
        this.kassarij = kassarij;
        this.manager = manager;

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
                create(factuur);

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

    /**
     * Create a new Factuur.
     *
     * @param object het object om toe te voegen aan de db
     */
    public void create(Object object) {
        EntityTransaction transaction = null;
        try {
            // Get a transaction, sla de factuur gegevens op en commit de transactie
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(object);
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
}
