public class Persoon {
    private long bsn_nummer;
    private String voornaam;
    private String achternaam;
    private Datum geboorteDatum;
    private char geslacht;

    Persoon(long bsn_nummer, String voornaam, String achternaam, Datum geboorteDatum, char geslacht) {
        this.bsn_nummer = bsn_nummer;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
        setGeslacht(geslacht);
    }

    Persoon(long bsn_nummer, String voornaam, String achternaam) {
        this.bsn_nummer = bsn_nummer;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = new Datum();
        this.geslacht = 'x';
    }

    Persoon() {
        this.bsn_nummer = 0;
        this.voornaam = "";
        this.achternaam = "";
        this.geboorteDatum = new Datum();
        this.geslacht = 'x';
    }

    @Override
    public String toString() {
        return (bsn_nummer + ", " + voornaam + ", " + achternaam + ", " + getGeslacht() + ", " + getGeboorteDatum());
    }

    long getBsn_nummer() {
        return bsn_nummer;
    }

    public void setBsn_nummer(int bsn_nummer) {
        this.bsn_nummer = bsn_nummer;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    String getGeboorteDatum() {
        if (geboorteDatum.getDatumAsString().equals("0-0-0")) {
            return "onbekend";
        } else return geboorteDatum.getDatumAsString();
    }

    public void setGeboorteDatum(Datum geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    String getGeslacht() {
        if (geslacht == 'v') {
            return "vrouw";
        } else if (geslacht == 'm') {
            return "man";
        }
        return "onbekend";
    }

    void setGeslacht(char geslacht) {
        if (geslacht == 'v' || geslacht == 'm') {
            this.geslacht = geslacht;
        } else {
            this.geslacht = 'x';
        }
    }

    public static class Student extends Persoon {

        private int studentnummer;
        private String studie;

        public Student(long bsn_nummer, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, int studentnummer, String studie){
            super(bsn_nummer, voornaam, achternaam, geboorteDatum, geslacht);

            this.studentnummer = studentnummer;
            this.studie = studie;
        }

        public Student(){
            super();
            this.studentnummer=(int)(Math.random()*600000);
            this.studie= "NNB";
        }

        public int getStudentnummer(){
            return studentnummer;
        }

        public void setStudentnummer(int studentnummer){
            this.studentnummer = studentnummer;
        }

        public String getStudie(){
            return studie;
        }

        public void setStudie(String studie){
            this.studie = studie;
        }
    }

    public static class Docent extends Persoon {

        private String afkorting;
        private String afdeling;

        public Docent(long bsn_nummer, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, String afkorting, String afdeling){
            super(bsn_nummer, voornaam, achternaam, geboorteDatum, geslacht);

            this.afdeling = afdeling;
            this.afkorting = afkorting;
        }

        public Docent(){
            super();
            this.afdeling = "NNB";
            this.afkorting = "NNB";
        }

        public String getAfdeling(){
            return afdeling;
        }

        public void setAfdeling(String afdeling){
            this.afdeling = afdeling;
        }

        public String getAfkoring(){
            return afkorting;
        }

        public void setAfkorting(String afkorting){
            this.afkorting = afkorting;
        }
    }

    public static class KantineMedewerker extends Persoon {

        private boolean magBijKassa;
        private int medewerkersnummer;

        public KantineMedewerker(long bsn_nummer, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, boolean magBijKassa, int medewerkersnummer){
            super(bsn_nummer, voornaam, achternaam, geboorteDatum, geslacht);

            this.magBijKassa = magBijKassa;
            this.medewerkersnummer = medewerkersnummer;
        }

        public KantineMedewerker(){
            super();
            this.magBijKassa = false;
            this.medewerkersnummer = (int)(Math.random()*600000);
        }

        public boolean getMagKassa(){
            return magBijKassa;
        }

        public void setMagKassa(boolean magBijKassa){
            this.magBijKassa = magBijKassa;
        }

        public int getMedewerkersnummer(){
            return medewerkersnummer;
        }

        public void setMedewerkersnummer(int medewerkersnummer){
            this.medewerkersnummer = medewerkersnummer;
        }
    }
}
