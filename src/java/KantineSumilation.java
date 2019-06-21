import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class KantineSumilation {

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal personen
    private static int student = 89;
    private static int docent = 10;
    private static int kantinemedewerker = 1;

    // artikelen
    private static final String[] artikelnamen = new String[]
        {"Koffie", "Broodje pinkakaas", "Broodje kaas", "Appelsap"};
    private int aantalArtikelen;

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

    // database spul
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY=
            Persistence.createEntityManagerFactory("KantineSimulatie");
    private EntityManager manager;

    /**
     * Constructor
     *
     */
    private KantineSumilation() {
        //start db verbinding & verwijder vorige run
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        //initialiseer sumilation
        kantine = new Kantine(manager);
        random = new Random();
        this.aantalArtikelen = artikelnamen.length;
        int[] hoeveelheden = getRandomArray(
            aantalArtikelen,
            MIN_ARTIKELEN_PER_SOORT,
            MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(
            artikelnamen, artikelprijzen, hoeveelheden);
        kantine.setAanbod(kantineaanbod);

        //run
        simuleer(getRandomValue(1,14));

        //stop db verbinding
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }

    /**
     * Methode om een array van random getallen liggend tussen
     * min en max van de gegeven lengte te genereren
     *
     * @param lengte Aantal getallen om te genereren
     * @param min Minimumwaarde
     * @param max Maximumwaarde
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
     * @param min Minimumwaarde
     * @param max Maximumwaarde
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array
     * artikelnamen de bijhorende array van artikelnamen te maken
     *
     * @param indexen Array met posities om uit kantineaanbod artikelarray te halen
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
     * @param dagen Aantal dagen om te simuleren
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
                Persoon persoon = new Persoon.Student();
                //genereer naam
                persoon.setVoornaam("Bertha");
                persoon.setAchternaam(Integer.toString((i+1)*j));

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(persoon, genereerArtikelen());
            }

            for(int j = 0; j < docent; j++) {
                Persoon persoon = new Persoon.Docent();
                //genereer naam
                persoon.setVoornaam("Jan");
                persoon.setAchternaam(Integer.toString((i+1)*j));

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(persoon, genereerArtikelen());
            }

            for(int j = 0; j < kantinemedewerker; j++) {
                Persoon persoon = new Persoon.KantineMedewerker();
                //genereer naam
                persoon.setVoornaam("Gerardus");
                persoon.setAchternaam(Integer.toString((i+1)*j));

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(persoon, genereerArtikelen());
            }


            // verwerk rij voor de kassa

            // oude variant: error hier opvangen
//            try{
//                kantine.verwerkRijVoorKassa();
//            } catch(TeWeinigGeldException e){
//                System.out.println(persoon.getVoornaam()+persoon.getAchternaam()+e.message);
//            } catch(Exception e){
//                System.out.println("Onverwachte fout, probeer opnieuw of roep de systeembeheerder!");
//            }
			kantine.verwerkRijVoorKassa();

            // druk de dagtotalen af en hoeveel personen binnen
	        System.out.println("\ndag " + (i+1) + " - bedrag in kassa: " + Administratie.valutaRoundingPrint(kantine.getKassa().hoeveelheidGeldInKassa()));
	        System.out.println("artikelen verkocht: " + kantine.getKassa().aantalArtikelen());

	        // zijn gekomen
			System.out.println("aantal klanten geweest: " + aantalpersonen);

            //omzet optellen
            omzet[i] = kantine.getKassa().hoeveelheidGeldInKassa();
            aantal[i] = kantine.getKassa().aantalArtikelen();

            // reset de kassa voor de volgende dag
            kantine.resetKassa();

            //enter invoegen voor de volgende dag
            System.out.println();
        }
        //opgave 4c dag/week waarden afdrukken
        System.out.println("gemiddelde omzet " + Administratie.valutaRoundingPrint(Administratie.berekenGemiddeldeOmzet(omzet)));
        System.out.println("gemiddelde aantal " + Administratie.valutaRoundingPrint(Administratie.berekenGemiddeldAantal(aantal))+"\n");
        double[] dagomzetarray = Administratie.berekenDagOmzet(omzet);

        //print totaalomzet voor specifieke dagen
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
            System.out.println("dag omzet: " + Administratie.valutaRoundingPrint(dagOmzet));
            counter++;
        }
    }

    private String[] genereerArtikelen(){

        // bedenk hoeveel artikelen worden gepakt
        int aantalartikelen = getRandomValue(1,8) ;

        // genereer de "artikelnummers", dit zijn indexen
        // van de artikelnamen
        int[] tepakken = getRandomArray(
                aantalartikelen, 0, aantalArtikelen-1);

        // vind de artikelnamen op basis van
        // de indexen hierboven
        return geefArtikelNamen(tepakken);

    }

    /**
     * opgave 5, deze methode genereerd met een max aan gegeven aantal, drie verschillende personen.
     * Dit gebeurt aan de hand van een kans die er in zit hoeveel een bepaald persoon voor mag komen
     * per 100 personen
     * @param maxAmountToGenereate nummer om onder te verdelen naar specifieke persoontypen
     */
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

    public static void main(String[] args) {
        new KantineSumilation();
    }
}
