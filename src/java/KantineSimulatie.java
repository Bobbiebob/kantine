import java.util.Random;

public class KantineSimulatie {

    private static Kantine kantine;
    private static final int DAGEN = 7;

    /**
     * Constructor
     */
    public KantineSimulatie() {
        kantine = new Kantine();
    }

    /**
     * Deze methode simuleert een aantal dagen in het
     * verloop van de kantine
     *
     * @param dagen
     */
    public static void simuleer(int dagen) {

        // herhaal voor elke dag
        for(int i = 0; i<= dagen ;i++) {

            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...

            // for lus voor personen
            for(int j = 0; j < 10 + i; j++){
                // kantine.(...);
	            kantine.loopPakSluitAan();
            }

            // verwerk rij voor de kassa
			kantine.verwerkRijVoorKassa();

            // toon dagtotalen (artikelen en geld in kassa)
			System.out.println("aantal artikel in de kassa " + kantine.getAantalArtikelen());
			System.out.println("hoeveelhied geld in de kassa " + kantine.getGeldInKassa());
            // reset de kassa voor de volgende dag
	        kantine.resetKassa();
        }
    }

    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
        kantine = new Kantine();
        int dagen;

        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }

        simuleer(dagen);
    }
}
