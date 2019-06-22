import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;

@Entity
@Table(name = "factuur")

public class Factuur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false,nullable = false)
    private Long id;

    @Column(name="datum",nullable=false)
    private LocalDateTime datum;

    @Column(name="gegeven_korting")
    private double gegevenKorting;

    @Column(name="totaal",nullable = false)
    private double totaal;

    public Factuur(){
        totaal = 0;
        gegevenKorting = 0;
    }

    public Factuur(Dienblad klant, LocalDateTime datum){
        this();
        this.datum = datum;

        verwerkBestelling(klant);
    }

    private void verwerkBestelling(Dienblad klant){

        //basisprijs artikelen
        double totaalPrijs = berekenTotaalPrijs(klant);
        Persoon persoon = klant.getKlant();

        //kortingscheck+toepassing
        Class[] interfaces = persoon.getClass().getInterfaces();
        if(interfaces.length>0) {
            if ((interfaces[0].getSimpleName()).equals("KortingskaartHouder")) {
                double kortingspercentage = persoon.geefKortingsPercentage();
                double maximum = 0;
                if (persoon.heeftMaximum()) {
                    maximum = persoon.geefMaximum();
                }
                double totaalkorting = totaalPrijs * kortingspercentage;

                //max korting check
                if(maximum>0 && maximum>totaalkorting){
                    totaalPrijs -= totaalkorting;
                    this.gegevenKorting = totaalkorting;
                }
                if (maximum>0 && maximum<totaalkorting){
                    totaalPrijs -=maximum;
                    this.gegevenKorting = maximum;
                }
                if (maximum == 0){
                    totaalPrijs -=totaalkorting;
                    this.gegevenKorting = totaalkorting;
                }
            }
        }

        this.totaal = Administratie.valutaRoundingPrint(totaalPrijs);
    }

    /**
     * Methode om de totaalprijs van de artikelen
     * op dienblad uit te rekenen
     *
     * @return
     */
    private double berekenTotaalPrijs(Dienblad klant){
        Iterator<Artikel> it = klant.getArtikelen().iterator();
        double totaalprijs = 0;
        while (it.hasNext()){
            Artikel artikel = it.next();
            totaalprijs+=artikel.getPrice();
        }
        return totaalprijs;
    }

    public double getGegevenKorting(){
        return gegevenKorting;
    }

    public double getTotaal(){
        return totaal;
    }

    public String toString(){
        String returnstring= "Bestelling ID: "+id+", datum: "+datum+", gegeven korting: "+gegevenKorting+", totaal betaald: "+totaal;
        return returnstring;
    }
}
