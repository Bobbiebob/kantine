import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "artikel")
public class Artikel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artikelId", unique = true, updatable = false,nullable = false)
	private long artikelId;

	@Column(name="naam",nullable = false)
	private String name;

	@Column(name = "prijs")
	private double price;


	Artikel(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Artikel(){	}

	@Override
	public String toString(){
		return (name + "," + price);
	}

	String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
