import javax.persistence.EntityManager;

public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod aanbod;

    private String[] artikelnamen;
    private double[] prijzen;
    private int[] voorraad;

    /**
     * Constructor
     */
    Kantine(EntityManager manager) {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij,manager);
        prijzen = new double[] {1.00,1.50,0.80,1.20,1.10,3.00,2.50,2.00,1.40,5.00};
        voorraad = new int[] {1000,1000,1000,200,200,100,100,100,600,50};
        artikelnamen = new String[] {"Koffie","Pils 0,33L","Thee","Frisdrank 0,5L","Water 0,5L","Broodje gezond","Bakje patat","Appeltaartpunt","Pizzapunt","Salade"};
        aanbod = new KantineAanbod(artikelnamen,prijzen,voorraad);
    }

    /**
     * In deze methode wordt een dienblad aangemaakt en sluit deze achteraan de rij
     *
     * @param artikelnamen array met gewenste artikelen
     * @param persoon de persoon die de artikelen wil kopen
     */
    void loopPakSluitAan(Persoon persoon,String[]artikelnamen) {
        Dienblad dienblad = new Dienblad(persoon);
        for(String artikel:artikelnamen){
            dienblad.voegToe(aanbod.getArtikel(artikel));

        }
        kassarij.sluitAchteraan(dienblad);

    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    void verwerkRijVoorKassa() {
        while(kassarij.erIsEenRij()) {
            Dienblad eersteInRij = kassarij.eerstePersoonInRij();
            kassa.rekenAf(eersteInRij);

        }
    }

    /**
     * Deze methode reset de bijgehouden telling van
     * het aantal artikelen en "leegt" de inhoud van de kassa.
     */
    void resetKassa() {
        kassa.resetKassa();
    }

    void setAanbod(KantineAanbod aanbod){
        this.aanbod = aanbod;
    }

    public KantineAanbod getAanbod(){
        return aanbod;
    }

    Kassa getKassa() {
        return kassa;
    }

    public KassaRij getKassarij() {
        return kassarij;
    }
}
