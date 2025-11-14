import java.time.LocalDate; // use LocalDate for expiration date (only YYYY-MM-DD)

public class Product {
	String id;
	String name;
	int quantity;
	LocalDate expirationDate;
	int price;
	
	// constructor
	public Product(String id, String name, int quantity, LocalDate expirationDate, int price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", expirationDate=" + expirationDate
				+ ", price=" + price + "]";
	}
	
	
}
