import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Customer customer = new Customer();
		//FastFood is-a FoodItem & Abstract class(FoodItem) cannot be instantiated
		//therefore superclass(FoodItem) references subclass(FastFood)
		//subclass : concrete class
		//Polymorphism
		FoodItem[] FastFoods = {
			    new FastFood("CheeseBurger", 8.00, 0, false),
			    new FastFood("Hot Dog", 7.50, 0, false),
			    new FastFood("Fried Chicken", 9.00, 0, false)
			};

		//superclass(FoodItem) references subclass(TraditionalMeal)
		FoodItem[] TraditionalMeals = {
				new TraditionalMeal("Bibimbap", 8.00, 0, false),
			    new TraditionalMeal("Kimchi Stew", 9.00, 0, false),
			    new TraditionalMeal("Bulgogi", 11.00, 0, false)
			};

		//superclass(FoodItem) references subclass(HealthyMeal)
		FoodItem[] HealthyMeals = {
			    new HealthyMeal("Tofu Salad", 8.00, 0, false),
			    new HealthyMeal("Grilled Fish", 9.00, 0, false),
			    new HealthyMeal("Brown Rice Bowl", 9.50, 0, false)
			};

		int choice; //customer's option(what to order, what to check)
		int control = 1; //control while loop
		int selection; //customer's selection in food categories
		int quantity; //quantity of food
		
		//Reference variables of abstract class(FoodItem)
		//Polymorphism
		FoodItem order1;
		FoodItem order2;
		FoodItem order3;

		while (control == 1) {
			System.out.println("\n--- Online Food Ordering ---");
			System.out.println("1. Order Fast Food");
			System.out.println("2. Order Traditional Meal");
			System.out.println("3. Order Healthy Meal");
			System.out.println("4. View orders");
			System.out.println("5. Checkout");
			System.out.println("6. Exit");
			System.out.println("Choose an option: ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.println("Select Fast Food: ");
				int i=1; //index
				//Objects LOOP: print each element in array FastFoods
				for (FoodItem items: FastFoods) {
					System.out.printf("%d. ", i);
					System.out.println(items); //invokes toString
					i++;
				}
				System.out.println("Enter food number: ");
				selection = scanner.nextInt(); 
				
				System.out.println("Quantity: ");
				quantity = scanner.nextInt();
				
				System.out.println("Add combo (+2.0 each)? (true/false): ");
				boolean combo = scanner.nextBoolean();
				
				//upcasting(FastFood -> FoodItem)
				order1 = new FastFood(
						FastFoods[selection-1].getName(),
						FastFoods[selection-1].getBasePrice(),
						quantity, combo);
				
				//add order1 to customer's order
				customer.addOrders(order1);
				
				System.out.println("Fast food added!");
				break;
				
			case 2:
				System.out.println("Select Traditional Meal: ");
				int j=1; //index
				//Objects LOOP: print each element in array TraditionalMeals
				for (FoodItem items: TraditionalMeals) {
					System.out.printf("%d. ", j); //invokes toString
					System.out.println(items);
					j++;
				}
				System.out.println("Enter food number: ");
				selection = scanner.nextInt();

				System.out.println("Quantity: ");
				quantity = scanner.nextInt();
				
				System.out.println("Add soup (+1.5 each)? (true/false): ");
				boolean soup = scanner.nextBoolean();
				
				//upcasting(TraditionalMeal -> FoodItem)
				order2 = new TraditionalMeal(
						TraditionalMeals[selection-1].getName(),
						TraditionalMeals[selection-1].getBasePrice(),
						quantity, soup);
				
				//add order2 to customer's order
				customer.addOrders(order2);
				
				System.out.println("Traditional meal added!");
				break;

			case 3:
				System.out.println("Select Healthy Meal: ");
				int k=1; //index
				//Objects LOOP: print each element in array HealthyMeals
				for (FoodItem items: HealthyMeals) {
					System.out.printf("%d. ", k);
					System.out.println(items); //invokes toString
					k++;
				}
				System.out.println("Enter food number: ");
				selection = scanner.nextInt();
				
				System.out.println("Quantity: ");
				quantity = scanner.nextInt();
				
				System.out.println("Add organic option (+1.0 each)? (true/false): ");
				boolean organic = scanner.nextBoolean();
				
				//upcasting(HealthyMeal -> FoodItem)
				order3 = new HealthyMeal(
						HealthyMeals[selection-1].getName(),
						HealthyMeals[selection-1].getBasePrice(),
						quantity, organic);
				
				//add order3 to customer's order
				customer.addOrders(order3);
				
				System.out.println("Healthy meal added!");
				break;

			case 4:
				System.out.println("Your orders:");
				//print customer's orders
				customer.viewOrders();
				break;

			case 5:
				//print total price
				System.out.printf("Total to pay: $%.2f\n", customer.checkout());
				System.out.println("Thank you for ordering!");
				break;
				
			case 6:
				System.out.println("\n Exiting . . . Goodbye!");
				control = 0;
				break;
			}
		}
		scanner.close();
	}

}
