
public class FastFood extends FoodItem{ //FastFood is-a FoodItem (inheritance)
	boolean combo;
	
	//constructor(name, basePrice, quantity, combo)
	public FastFood(String name, double basePrice, int quantity, boolean combo) {
		super(name, basePrice, quantity); //call to superclass(FoodItem) constructor 
		this.combo = combo;
	}
	
	//return combo
	public boolean isCombo() {
		return combo;
	}
	
	//set combo
	public void setCombo(boolean combo) {
		this.combo = combo;
	}

	//abstract method specifies in subclass
	//return price
	public double calculatePrice() {
		double price = getBasePrice() * getQuantity();
		if (combo){ //if customer selected
			price += 2.0 * getQuantity(); //add the price
		}
		return price;
	}

}
