public class KantineSimulatie {

    private Kantine kantine;
    private Persoon persoon;
    private String[] artikelen;
    public static final int DAGEN = 7;

    /**
     * Constructor
     */
    public KantineSimulatie() {
        this.kantine = new Kantine();
        this.persoon = new Persoon();
        this.artikelen = new String[]{ "Koffie","Pils 0,33L","Thee","Frisdrank 0,5L"};
    }

    /**
     * Deze methode simuleert een aantal dagen in het
     * verloop van de kantine
     *
     * @param dagen
     */

    public void simuleer(int dagen) {

        // herhaal voor elke dag
        for(int i = 0; i < dagen; i++) {

            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...

            // for lus voor personen
            for(int j = 0; j < 10 + i; j++){
                // kantine.(...);
                kantine.loopPakSluitAan(persoon, artikelen);
            }

            // verwerk rij voor de kassa
			kantine.verwerkRijVoorKassa();
            // toon dagtotalen (artikelen en geld in kassa)
			System.out.println("dag: " + i + " bedrag in kassa: " + kantine.getKassa().hoeveelheidGeldInKassa());
			System.out.println("artikelen verkocht: " + kantine.getKassa().aantalArtikelen());
            // reset de kassa voor de volgende dag
	        kantine.resetKassa();
        }
    }

    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
        int dagen;

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }

        new KantineSimulatie().simuleer(dagen);
    }
}
