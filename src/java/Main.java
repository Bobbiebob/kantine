public class Main {

    public static void main(String[] args) {

        Kantine kantine = new Kantine();
        kantine.loopPakSluitAan();
        System.out.println("Nieuw persoon sluit aan...");
        kantine.verwerkRijVoorKassa();
        System.out.println("Rij laten afrekenen...");
        System.out.println("Er zijn "+kantine.aantalArtikelen()+" artikelen afgerekend");
    }
}
