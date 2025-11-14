
public class TraditionalMeal extends FoodItem{ //TraditionalMeal is-a FoodItem (inheritance)
	boolean soup;
	
	//constructor(name, basePrice, quantity, soup)
	public TraditionalMeal(String name, double basePrice, int quantity, boolean soup) {
		super(name, basePrice, quantity); //call to superclass(FoodItem) constructor
		this.soup = soup;
	}

	//return soup
	public boolean isSoup() {
		return soup;
	}

	//set soup
	public void setSoup(boolean soup) {
		this.soup = soup;
	}
	
	//abstract method specifies in subclass
	//return price
	public double calculatePrice() {
		double price = getBasePrice() * getQuantity();
		if (soup){ //if customer selected
			price += 1.5 * getQuantity(); //add the price
		}
		return price;
	}

}
