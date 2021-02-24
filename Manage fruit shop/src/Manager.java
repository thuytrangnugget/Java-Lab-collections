
import java.util.ArrayList;
import java.util.Hashtable;

public class Manager {
    static int menu() {
        System.out.println("============= WELCOME TO THE FRUIT SHOP =============");
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        int choice = Validation.checkInputIntLimit(1, 4);
        return choice;
    }
    
    static void fruitWithIDFound(ArrayList<Fruit> lf, String ID){
        for (Fruit fruit : lf) {
            if (ID.equalsIgnoreCase(fruit.getID())) {
                fruit.toString();
                return;
            }
        }
        System.out.println("This fruit doesn't exist");
    }
    
    static void createFruit(ArrayList<Fruit> lf) {
        while (true) {            
            System.out.println("Enter fruit ID: ");
            String fruitID = Validation.checkInputFruitID(lf);
            if (fruitID.equals("")) {
                return;
            }
            System.out.print("Enter fruit name: ");
            String name = Validation.checkInputString();
            System.out.print("Enter price: ");
            double price = Validation.checkInputDouble(); 
            System.out.print("Enter quantity: ");
            int quantity = Validation.checkInputInt();
            System.out.println("Enter origin: ");
            String origin = Validation.checkInputString();
            lf.add(new Fruit(fruitID, name, quantity, price, origin));
            if (!Validation.checkInputYN()) {
                return;
            }
        }
    }
    
    void listAllFruit(ArrayList<Fruit> lf) {
        for (Fruit f: lf) {
            f.toString();
        }
    }
    
    void displayListOrder(ArrayList<Order> lo) {
        double total = 0;
        System.out.printf("%15s | %15s | %15s | %15s\n", "Product", "Quantity", "Price", "Amount");
        for (Order order : lo) {
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", order.getFruitName(),
                    order.getQuantity(), order.getPrice(),
                    order.getPrice() * order.getQuantity());
            total += order.getPrice() * order.getQuantity();
        }
        System.out.println("Total: " + total);
        
    }
    
    void viewOrder(Hashtable<String, ArrayList<Order>> ht) {
        for (String name: ht.keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Order> or = ht.get(name);
            displayListOrder(or);
        }
    }
}
