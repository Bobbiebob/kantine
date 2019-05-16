public class Persoon {
	private int bsn_nummer;
	private String voornaam;
	private String achternaam;
	private Datum geboorteDatum;
	private char geslacht;

	public Persoon(int bsn_nummer, String voornaam, String achternaam, Datum geboorteDatum, char geslacht) {
		this.bsn_nummer = bsn_nummer;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.geboorteDatum = geboorteDatum;
		setGeslacht(geslacht);
	}

	public Persoon(int bsn_nummer, String voornaam, String achternaam){
		this.bsn_nummer = bsn_nummer;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.geboorteDatum = new Datum();
		this.geslacht = 'x';
	}

	@Override
	public String toString(){
		return (bsn_nummer + ", " + voornaam + ", " + achternaam + ", " + getGeslacht() + ", " + getGeboorteDatum());
	}

	public int getBsn_nummer() {
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

	public String getGeboorteDatum() {
		if(geboorteDatum.getDatumAsString().equals("0-0-0")){
			return "onbekend";
		}
		else return geboorteDatum.getDatumAsString();
	}

	public void setGeboorteDatum(Datum geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}

	public String getGeslacht() {
		if(geslacht == 'v'){
			return "vrouw";
		}
		else if(geslacht == 'm') {
			return "man";
		}
		return "onbekend";
	}

	public void setGeslacht(char geslacht) {
		if(geslacht == 'v' || geslacht == 'm'){
			this.geslacht = geslacht;
		}
		else{
			this.geslacht = 'x';
		}
	}
}
