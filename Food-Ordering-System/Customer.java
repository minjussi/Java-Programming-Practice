
public class Customer {
	FoodItem[] orders = new FoodItem[9]; // has-a relationship (composition)
	int orderCount = 0; //index & notice the number of orders

	//Add orders array when a customer orders
	public void addOrders(FoodItem food) {
		orders[orderCount] = food; //insert value
		orderCount++; //if order is added, increase orderCount
	}

	//Show order list(print orders array)
	public void viewOrders() {
		if (orderCount == 0) { //no orders
			System.out.println("No orders yet\n");
		} else {
			//print each element in array orders with exact format
			for (int i = 0; i < orderCount; i++) {
				System.out.printf("- %d x %s = $%.2f\n", orders[i].getQuantity(), orders[i].getName(),
						orders[i].calculatePrice());
			}
		}

	}

	//Total price that customer should pay(return total price)
	public double checkout() {
		double total = 0.0; //initialize total price
		for (int i = 0; i < orderCount; i++) {
			total += orders[i].calculatePrice(); //add all total price in orders list
		}
		return total;
	}

	//return orders
	public FoodItem[] getOrders() {
		return orders;
	}

	//set orders
	public void setOrders(FoodItem[] orders) {
		this.orders = orders;
	}

}
