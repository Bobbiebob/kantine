
import java.util.Stack;

public class Dienblad {
    private Stack<Artikel> artikelen;
    private Persoon klant;

    /**
     * Constructor
     */
    public Dienblad() {
        artikelen = new Stack<>();
    }

    /**
     * Constructor
     * @param klant
     */
    public Dienblad(Persoon klant){
        artikelen = new Stack<>();
        this.klant = klant;
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
        artikelen.add(artikel);
    }

    /**
     * @return stack artikelen
     */
    public Stack<Artikel> getArtikelen() {
        return artikelen;
    }

    /**
     * Methode om persoon van het dienblad op te vragen
     * @return De persoon
     */
    public Persoon getKlant(){
        return klant;
    }

    /**
     * Methode om een klant toe te wijzen aan een klantloos dienblad
     * @param klant
     */
    public void setKlant(Persoon klant){
        this.klant = klant;
    }
}

