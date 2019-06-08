import java.text.DecimalFormat;
import java.util.*;

public class KantineSumilation {

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;
    private Persoon persoon;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;
    private static int student = 89;
    private static int docent = 10;
    private static int kantinemedewerker = 1;

    // artikelen
    private static final String[] artikelnamen = new String[]
        {"Koffie", "Broodje pinkakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[]{1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10000;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;



    /**
     * Constructor
     *
     */
    private KantineSumilation() {
        kantine = new Kantine();
        random = new Random();
        int[] hoeveelheden = getRandomArray(
            AANTAL_ARTIKELEN,
            MIN_ARTIKELEN_PER_SOORT,
            MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(
            artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setAanbod(kantineaanbod);
        simuleer(getRandomValue(1,14));
    }

    /**
     * Methode om een array van random getallen liggend tussen
     * min en max van de gegeven lengte te genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for(int i = 0; i < lengte ;i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl)
     * en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array
     * artikelnamen de bijhorende array van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for(int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    private void simuleer(int dagen) {
        // for lus voor dagen
        double[] omzet = new double[dagen];
        int[] aantal = new int[dagen];

        //opgave 5
        personGenerator(100);

        for(int i = 0; i < dagen; i++) {

            // bedenk hoeveel personen vandaag binnen lopen
            // opgave 4a
            int aantalpersonen = student + docent + kantinemedewerker;

            for(int j = 0; j < student; j++) {
                this.persoon = new Persoon.Student();
            }

            for(int j = 0; j < docent; j++) {
                this.persoon = new Persoon.Docent();
            }

            for(int j = 0; j < kantinemedewerker; j++) {
                this.persoon = new Persoon.KantineMedewerker();
            }


            // laat de personen maar komen...
            for(int j = 0; j < aantalpersonen; j++) {

                // maak persoon en dienblad aan, koppel ze
                // en bedenk hoeveel artikelen worden gepakt

	            Dienblad dienblad = new Dienblad(persoon);
                int aantalartikelen = getRandomValue(1,30) ;

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(
                    aantalartikelen, 0, AANTAL_ARTIKELEN-1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
	            kantine.loopPakSluitAan(persoon, artikelen);


            }

            // verwerk rij voor de kassa
			kantine.verwerkRijVoorKassa();

            // druk de dagtotalen af en hoeveel personen binnen
	        System.out.println("dag: " + i + " bedrag in kassa: " + valutaRoundingPrint(kantine.getKassa().hoeveelheidGeldInKassa()));
	        System.out.println("artikelen verkocht: " + kantine.getKassa().aantalArtikelen());

	        // zijn gekomen
			System.out.println("aantal klanten geweest: " + aantalpersonen);

            //omzet optellen
            omzet[i] = kantine.getKassa().hoeveelheidGeldInKassa();
            aantal[i] = kantine.getKassa().aantalArtikelen();

            // reset de kassa voor de volgende dag
            kantine.resetKassa();

            //enter invoegen voor de volgende dag
            System.out.println("");
        }
        // dag/week waarden afdrukken
        System.out.println("gemiddelde omzet " + valutaRoundingPrint(Administratie.berekenGemiddeldeOmzet(omzet)));
        System.out.println("gemiddelde aantal " + Administratie.berekenGemiddeldAantal(aantal));
        double[] dagomzetarray = Administratie.berekenDagOmzet(omzet);

        int counter = 1;
        for (double dagOmzet : dagomzetarray) {
            switch(counter){
                case 1: System.out.print("Maandag ");
                        break;
                case 2: System.out.print("Dinsdag ");
                        break;
                case 3: System.out.print("Woensdag ");
                        break;
                case 4: System.out.print("Donderdag ");
                        break;
                case 5: System.out.print("Vrijdag ");
                        break;
                case 6: System.out.print("Zaterdag ");
                        break;
                case 7: System.out.print("Zondag ");
                        break;
            }
            System.out.println("dag omzet " + valutaRoundingPrint(dagOmzet));
            counter++;
        }
    }

    private void personGenerator(int maxAmountToGenereate){
        int aantal = random.nextInt(maxAmountToGenereate);
        double leerling = aantal * 0.89;
        double leraar = aantal * 0.1;
        double dienbladVuller = aantal * 0.01;
        student = (int) leerling;
        docent = (int) leraar;
        kantinemedewerker = (int) dienbladVuller;
        if(student+docent+kantinemedewerker < aantal){
            kantinemedewerker += 1;
            if(student+docent+kantinemedewerker < aantal){
                student += 1;
            }
        }

    }

    private double valutaRoundingPrint(double money){
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(money));
    }

    public static void main(String[] args) {
        new KantineSumilation();
    }
}
