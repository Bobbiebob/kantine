public class Main {

    /**
     * deze klasse word momenteel gebruikt om de andere klassen te testen, hiervoor is er fake data aan gemaakt om de uit komsten van de andere klassen te kunnen controleren.
     * @param args
     */
    public static void main(String[] args) {

        Kantine kantine = new Kantine();
        String[] artikelen = {"Koffie","Bakje patat","Thee"};
        Datum datum = new Datum(56,23,2000);
        Datum datum1 = new Datum(31,3,2000);
        Datum datum2 = new Datum(31,1,2000);
        Datum datum3 = new Datum(31,2,2000);
        Datum datum4 = new Datum(28,2,2000);
        Datum datum5 = new Datum(29,2,2000);
        Persoon persoon = new Persoon(15465465, "henk", "tank", datum1, 'm');
        Dienblad dienblad = new Dienblad(persoon);


        kantine.loopPakSluitAan(persoon, artikelen);
        System.out.println("Nieuw persoon sluit aan...");
        kantine.verwerkRijVoorKassa();
        System.out.println("Rij laten afrekenen...");
        System.out.println("Er zijn "+kantine.getAantalArtikelen()+" artikelen afgerekend");
        System.out.println("persoon in kwestie: " + persoon.toString());

        /* hier worden de data geretouneerd, komt er de datum 0-0-0 uit dan houdt dit in dat er een foutieve data was ingevoerd.
           Voor verduidelijking van dit proces zie de klasse Datum
         */
        System.out.println(datum.getDatumAsString());
        System.out.println(datum1.getDatumAsString());
        System.out.println(datum2.getDatumAsString());
        System.out.println(datum3.getDatumAsString());
        System.out.println(datum4.getDatumAsString());
        System.out.println(datum5.getDatumAsString());
    }
}
