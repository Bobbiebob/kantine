public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt
     * en aan elkaar gekoppeld. Maak twee Artikelen aan
     * en plaats deze op het dienblad. Tenslotte sluit de
     * Persoon zich aan bij de rij voor de kassa.
     */
    public void loopPakSluitAan() {
        //todo: random naam kiezen uit lijst valide namen
        Persoon persoon = new Persoon(100000000 + (int)(Math.random()
                * ((999999999 - 100000000) + 1)),"Jan",
                "De Jong");
        Dienblad dienblad = new Dienblad(persoon);
        //todo: random kiezen uit lijst valide artikelen
        dienblad.voegToe(new Artikel("Soep",(float)0.50));
        dienblad.voegToe(new Artikel("Koffie",(float)0.60));
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
    public double getGeldInKassa(){
        return kassa.getGeldInKassa();
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     *
     * @return het aantal gepasseerde artikelen
     */
    public int getAantalArtikelen(){
       return kassa.getTotaalAantalArtikelen();
    }

    /**
     * Deze methode reset de bijgehouden telling van
     * het aantal artikelen en "leegt" de inhoud van de kassa.
     */
    public void resetKassa() {
        kassa.resetKassa();
    }
}
