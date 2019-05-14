import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Dienblad {
    private ArrayList<Artikel> artikelen;
    private Persoon klant;

    /**
     * Constructor
     */
    public Dienblad() {
        artikelen = new ArrayList<Artikel>();
    }

    public Dienblad(Persoon klant){
        artikelen = new Arraylist<Artikel>();
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
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen() {
        return artikelen.size();
    }

    /**
     * Methode om de totaalprijs van de artikelen
     * op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
    public double getTotaalPrijs() {
        Iterator it = artikelen.iterator();
        int totaalprijs = 0;
        while (it.hasNext()){
            totaalprijs+=it.next().getPrijs();
        }
        return totaalprijs;
    }

    /**
     *
     * @return
     */

    public Persoon getKlant(){
        return klant;
    }

    public void setKlant(Persoon klant){
        this.klant = klant;
    }
}

