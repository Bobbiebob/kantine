import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "artikel")
public class Artikel implements Serializable {

	@Id
	@Column(name = "id", unique = true, updatable = false,nullable = false)
	private long id;

	@Column(name="naam",nullable = false, unique = true)
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
