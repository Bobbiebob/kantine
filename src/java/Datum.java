public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	public Datum(int dag, int maand, int jaar){
		if(bestaatDatum(dag, maand, jaar)){
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		}
		new Datum();
		}

	public Datum(){
		dag = 0;
		maand = 0;
		jaar = 0;
	}

	public boolean bestaatDatum(int dag, int maand, int jaar){
		if((dag >= 1 && dag <= 31) && (maand >= 1 && maand <= 12) && (jaar >= 1900 && jaar <= 2100)){
			if(((dag <= 30 && maand == 4) ||(dag <= 30 && maand == 6) ||(dag <= 30 && maand == 9) ||(dag <= 30 && maand == 8) ||(dag <= 30 && maand == 11))){
				return true;
			}
			else if(maand == 1 || maand == 3 || maand == 5 || maand == 7 || maand == 8 || maand == 10 || maand == 12){
				return true;
			}
			else if((!((jaar % 4) == 0)) && (dag <= 28 && maand == 2)){
				return true;
			}
			else if(dag <= 29 && maand == 2){
				return true;
			}
		}
		return false;
	}

	public String getDatumAsString() {
		return (dag + "-" + maand + "-" + jaar);
	}

	public int getDag() {
		return dag;
	}

		public void setDag(int dag) {
			this.dag = dag;
		}

		public int getMaand() {
			return maand;
		}

	public void setMaand(int maand) {
 	 	this.maand = maand;
 	 }

	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}
}
