import java.util.LinkedList;

public class KassaRij {

    /**
     * Constructor
     */

    private LinkedList<Dienblad> kassarij;

    public KassaRij() {
        this.kassarij = new LinkedList<Dienblad>();
    }

    /**
     * Persoon sluit achter in de rij aan
     *
     * @param klant
     */
    public void sluitAchteraan(Dienblad klant) {
        kassarij.addLast(klant);
    }

    /**
     * Indien er een rij bestaat, de eerste klant uit
     * de rij verwijderen en retourneren.
     * Als er niemand in de rij staat geeft deze null terug.
     *
     * @return Eerste klant in de rij of null
     */
    public Dienblad eerstePersoonInRij() {
        if (kassarij.size()>0){
            return kassarij.removeFirst();
        }
        return null;
    }

    /**
     * Methode kijkt of er personen in de rij staan.
     *
     * @return Of er wel of geen rij bestaat
     */
    public boolean erIsEenRij() {
        if (kassarij.size()>0){
            return true;
        }
        return false;
    }
}