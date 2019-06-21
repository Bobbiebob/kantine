import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class KantineSimulatie {

    private Kantine kantine;
    private Persoon persoon;
    private String[] artikelen;
    private static final int DAGEN = 7;

    // database spul
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY=
            Persistence.createEntityManagerFactory("KantineSimulatie");
    private static EntityManager manager;

    /**
     * Constructor
     */
    private KantineSimulatie() {
        //start db verbinding & verwijder vorige run
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        this.kantine = new Kantine(manager);
        this.persoon = new Persoon();
        this.artikelen = new String[]{ "Koffie","Pils 0,33L","Thee","Frisdrank 0,5L"};

        persoon.setAchternaam("Naamloos");
        Betaalwijze pinpas = new Pinpas();
        pinpas.setSaldo(100);
        persoon.setBetaalwijze(pinpas);
    }

    /**
     * Deze methode simuleert een aantal dagen in het
     * verloop van de kantine
     *
     * @param dagen
     */
    private void simuleer(int dagen) {

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

            // oude variant: error hier opvangen
//            try{
//                kantine.verwerkRijVoorKassa();
//            } catch(TeWeinigGeldException e){
//                System.out.println(persoon.getVoornaam()+persoon.getAchternaam()+e.message);
//            } catch(Exception e){
//                System.out.println("Onverwachte fout: "+e.getClass.getSimpleName());
//            }
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
        //stop db verbinding
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }
}
