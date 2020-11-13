package entity;

public class Product {
	private int id;
	private String name;
	private String company;
	private int quantity;

	public Product() {
	}

	public Product(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public Product(int id, String name,int quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}
}
