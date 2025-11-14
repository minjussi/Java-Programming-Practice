
public class HealthyMeal extends FoodItem{ //HealthyMeal is-a FoodItem (inheritance)
	boolean organic;
	
	//constructor(name, basePrice, quantity, organic)
	public HealthyMeal(String name, double basePrice, int quantity, boolean organic) {
		super (name, basePrice, quantity); //call to superclass(FoodItem) constructor
		this.organic = organic;
	}
	
	//return organic
	public boolean isOrganic() {
		return organic;
	}

	//set organic
	public void setOrganic(boolean organic) {
		this.organic = organic;
	}
	
	//abstract method specifies in subclass
	//return price
	public double calculatePrice() {
		double price = getBasePrice() * getQuantity();
		if (organic){ //if customer selected
			price += 1.0 * getQuantity(); //add the price
		}
		return price;
	}
}
