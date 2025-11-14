import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ProductManage {

	public static List<Product> loadProducts(String fileName) {
        List<Product> productList = new ArrayList<>();
        try {
        	FileInputStream file = new FileInputStream(fileName);
        	Scanner buffer = new Scanner(file);
        	
            while (buffer.hasNext()) {
            	String line = buffer.nextLine();
				String parts[] = line.split(", ");
                if (parts.length == 5) {
                    String id = parts[0];
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    LocalDate expirationDate = LocalDate.parse(parts[3].trim());
                    int price = Integer.parseInt(parts[4]);

                    productList.add(new Product(id, name, quantity, expirationDate, price));
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return productList;
    }

    public static void saveProducts(List<Product> products, String fileName) {
    	try {
            FileOutputStream file = new FileOutputStream(fileName);
            PrintStream buffer = new PrintStream(file);

            for (Product p : products) {
                buffer.println(p.getId() + ", " + p.getName() + ", " + p.getQuantity() + ", " +
                               p.getExpirationDate() + ", " + p.getPrice());
            }

            buffer.close();
            file.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    public static void addProduct(Product newProduct, String fileName) {
    	List<Product> productList = loadProducts(fileName);
        productList.add(newProduct);
        saveProducts(productList, fileName);
    }

    public static List<Product> getProductExpiration(String fileName) {
    	List<Product> list = loadProducts(fileName);
        list.sort(Comparator.comparing(p -> p.expirationDate));
        return list;
    }

    public static boolean isProduct(String productId, String fileName) {
    	List<Product> list = loadProducts(fileName);
        for (Product p : list) {
            if (p.id.equals(productId)) {
                return true;
            }
        }
        return false;
    }

}
