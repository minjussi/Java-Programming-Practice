
public abstract class FoodItem { //abstract class (superclass of 3 food categories)
	String name;
	double basePrice;
	int quantity;
	
	//constructor(name, basePrice, quantity)
	public FoodItem(String name, double basePrice, int quantity) {
		this.name = name;
		this.basePrice = basePrice;
		this.quantity = quantity;
	}
	
	//abstract method : no implementations
	//specifies in subclasses
	public abstract double calculatePrice();

	//return String representation of FoodItem object
	public String toString() {
		return String.format("%s ($%.2f)", getName(), getBasePrice());
	}

	//return name
	public String getName() {
		return name;
	}

	//set name
	public void setName(String name) {
		this.name = name;
	}

	//return base price
	public double getBasePrice() {
		return basePrice;
	}

	//set base price
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	//return quantity
	public int getQuantity() {
		return quantity;
	}

	//set quantity
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
