public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;
    private KantineAanbod aanbod;

    private String[] artikelnamen = {"Koffie","Pils 0,33L","Thee","Frisdrank 0,5L","Water 0,5L","Broodje gezond","Bakje patat","Appeltaartpunt","Pizzapunt","Salade"};
    private double[] prijzen = {1.00,1.50,0.80,1.20,1.10,3.00,2.50,2.00,1.40,5.00};
    private int[] voorraad = {1000,1000,1000,200,200,100,100,100,600,50};

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
        aanbod = new KantineAanbod(artikelnamen,prijzen,voorraad);
    }

    /**
     * In deze methode wordt een dienblad aangemaakt en sluit deze achteraan de rij
     *
     * @param artikelnamen array met gewenste artikelen
     * @param persoon de persoon die de artikelen wil kopen
     */
    public void loopPakSluitAan(Persoon persoon,String[]artikelnamen) {
        Dienblad dienblad = new Dienblad(persoon);
        for(String artikel:artikelnamen){
            dienblad.voegToe(aanbod.getArtikel(artikel));
        }
        kassarij.sluitAchteraan(dienblad);

    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while(kassarij.erIsEenRij()) {
            Dienblad eersteInRij = kassarij.eerstePersoonInRij();
            kassa.rekenAf(eersteInRij);

        }
    }

    /**
     * Deze methode telt het geld uit de kassa
     *
     * @return hoeveelheid geld in kassa
     */
    public double hoeveelheidGeldInKassa() {
       return kassa.hoeveelheidGeldInKassa();
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     *
     * @return het aantal gepasseerde artikelen
     */
    public int aantalArtikelen() {
        return kassa.aantalArtikelen();
    }

    /**
     * Deze methode reset de bijgehouden telling van
     * het aantal artikelen en "leegt" de inhoud van de kassa.
     */
    public void resetKassa() {
        kassa.resetKassa();
    }

    public void setAanbod(KantineAanbod aanbod){
        this.aanbod = aanbod;
    }

    public KantineAanbod getAanbod(){
        return aanbod;
    }
}