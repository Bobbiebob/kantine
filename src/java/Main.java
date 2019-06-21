import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.DecimalFormat;
public class Main {

    /**
     * deze klasse word momenteel gebruikt om de andere klassen te testen, hiervoor is er fake data aan gemaakt om
     * de uit komsten van de andere klassen te kunnen controleren.
     * @param args
     */

    // database spul
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY=
            Persistence.createEntityManagerFactory("KantineSimulatie");
    private static EntityManager manager;

    public static void main(String[] args) {

        //start db verbinding
        manager = ENTITY_MANAGER_FACTORY.createEntityManager();

        Kantine kantine = new Kantine(manager);
        String[] artikelen = {"Koffie","Bakje patat","Thee"};
        Datum datum = new Datum(56,23,2000);
        Datum datum1 = new Datum(31,3,2000);
        Datum datum2 = new Datum(31,1,2000);
        Datum datum3 = new Datum(31,2,2000);
        Datum datum4 = new Datum(28,2,2000);
        Datum datum5 = new Datum(29,2,2000);

        /* hier worden de data geretouneerd, komt er de datum 0-0-0 uit dan houdt dit in dat er een foutieve data
           was ingevoerd.
           Voor verduidelijking van dit proces zie de klasse Datum
         */
        System.out.println(datum.getDatumAsString());
        System.out.println(datum1.getDatumAsString());
        System.out.println(datum2.getDatumAsString());
        System.out.println(datum3.getDatumAsString());
        System.out.println(datum4.getDatumAsString());
        System.out.println(datum5.getDatumAsString());

        Persoon.Student student = new Persoon.Student(123456,"Zwarte", "Piet",(new Datum(2,3,1998)),'m',321, "Genderwetenschap");
        student.setBetaalwijze(new Contant());
        student.getBetaalwijze().setSaldo(1);
        System.out.println("\n"+
                student.getStudentnummer()+"\n"+
                        student.getBsn_nummer()
        );
        System.out.println("\n"+student.toString()+"\n");
        kantine.loopPakSluitAan(student,artikelen);
        kantine.verwerkRijVoorKassa();
        System.out.println(kantine.getKassa().hoeveelheidGeldInKassa()+" Na studentbetaling in kassa");
        System.out.println("Nieuw saldo: "+student.getBetaalwijze().saldo);

        double[] tempDouble = {80.5, 80.9, 44.55, 94.48, 48.16, 49.77, 10.99, 81.5, 80.9, 44.55, 94.48, 48.16, 49.77,
                10.99, 82.5, 80.9, 44.55, 94.48, 48.16, 49.77, 10.99};
        int[] tempInt = {45, 56, 34, 39, 40, 31};
        int[] tempInt2 = {45, 56};
        int counter = 0;
        for (double totaal: Administratie.dagTotalenGemiddelde(tempDouble)) {
            System.out.println("dag " + counter + " totaal gemiddelde " + Administratie.valutaRoundingPrint(totaal));
            counter++;
        }
        counter = 0;
        for (double totaal: Administratie.berekenDagOmzet(tempDouble)) {
            System.out.println("dag " + counter + " totaal " + Administratie.valutaRoundingPrint(totaal));
            counter++;
        }
        System.out.println(Administratie.berekenGemiddeldAantal(tempInt2));
        System.out.println("rounded decimal " + Administratie.valutaRoundingPrint(Administratie.berekenGemiddeldeOmzet(tempDouble)));

        //stop db verbinding
        manager.close();
        ENTITY_MANAGER_FACTORY.close();
    }
}
