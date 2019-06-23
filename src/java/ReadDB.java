import java.io.Serializable;
import java.lang.*;
import java.util.List;
import java.lang.Math;
import java.util.Random;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

class ReadDB implements Serializable{

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY=
			Persistence.createEntityManagerFactory("KantineSimulatie");
	private EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
	private Random random;
	private Long factuurNummerMax;
	int factuurNummer;

	Double getTotalRevenue(){
		Query query = manager.createQuery(
				"SELECT SUM(f.totaal) FROM Factuur f");
		Double result = (Double) query.getSingleResult();
		return result;
	}

	Double getTotalDiscount(){
		Query query = manager.createQuery(
				"SELECT SUM(f.gegevenKorting) FROM Factuur f");
		Double result = (Double) query.getSingleResult();
		return result;
	}

	Double getAVGRevenue(){
		Query query = manager.createQuery(
				"SELECT AVG(f.totaal) FROM Factuur f");
		Double result = (Double) query.getSingleResult();
		return result;
	}

	Double getFactuur(){
		random = new Random();
		Query numberId = manager.createQuery(
				"Select MAX(f.id) FROM Factuur f");
		factuurNummerMax = (Long) numberId.getSingleResult();
		do {
			factuurNummer = Math.abs(random.nextInt());
		} while(factuurNummerMax <= factuurNummer && factuurNummer >= 0);

		Query query = manager.createQuery(
				//"SET a := factuurNummer" +
						"SELECT f.gegevenKorting FROM Factuur f  WHERE f.id = 1");
		Double result = (Double) query.getSingleResult();
		return result;
	}


	String[] getTopThree(){
		String[] toReturn = new String[3];
		Query query = manager.createQuery(
			"SELECT f.datum, f.gegevenKorting, f.totaal FROM Factuur f order by totaal DESC");
		List<Object[]> resultList = query.getResultList();
		for(int i = 0; i < 3; i++){
			toReturn[i] = "Datum: " + resultList.get(i)[0] + " Gegeven Korting: " + resultList.get(i)[1] + " Factuur totaal: " + resultList.get(i)[2];
		}
		return toReturn;
	}

	String getArtikel(FactuurRegel factuurRegel){
		Query query = manager.createQuery(
				//"SET @i := factuurRegel;" +
						"SELECT artikelNaam FROM FactuurRegel Where factuurRegelId = 1");
		String result = (String) query.getSingleResult();
		return result;
	}
}
