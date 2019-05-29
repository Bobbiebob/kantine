import java.util.LinkedList;

class KassaRij {

    /**
     * Constructor
     */

    private LinkedList<Dienblad> kassarij;

    KassaRij() {
        this.kassarij = new LinkedList<Dienblad>();
    }

    /**
     * Persoon sluit achter in de rij aan
     *
     * @param klant
     */
    void sluitAchteraan(Dienblad klant) {
        kassarij.addLast(klant);
    }

    /**
     * Indien er een rij bestaat, de eerste klant uit
     * de rij verwijderen en retourneren.
     * Als er niemand in de rij staat geeft deze null terug.
     *
     * @return Eerste klant in de rij of null
     */
    Dienblad eerstePersoonInRij() {
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
    boolean erIsEenRij() {
        return !kassarij.isEmpty();
    }
}
