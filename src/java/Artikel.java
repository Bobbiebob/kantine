public class Artikel{
	private String name;
	private double price;

	public Artikel(String name, double price) {
		this.name = name;
		this.price = price;

	}

	public Artikel(){	}

	@Override
	public String toString(){
		return (name + "," + price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
