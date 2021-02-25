
import java.util.ArrayList;
import java.util.Collections;
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
                fruit.print();
                return;
            }
        }
        System.out.println("This fruit doesn't exist"
                + "");
    }
    
    static void createFruit(ArrayList<Fruit> lf) {
        displayListFruit(lf);
        while (true) {
            System.out.print("Enter fruit ID: ");
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
            System.out.print("Enter origin: ");
            String origin = Validation.checkInputString();
            if (!Validation.AreYouSureYN("Are you sure to create new fruit? (Y/N): ")) return;
            if (!Validation.checkInputYN()) {
                System.out.println("");
                return;
            } else lf.add(new Fruit(fruitID, name, quantity, price, origin));
            if (!Validation.checkInputYN()) {
                System.out.println("");
                return;
            }
        }
    }
    
    static void displayListOrder(ArrayList<Order> lo) {
        double total;
        System.out.printf("%15s | %15s | %15s | %15s\n", "Product", "Quantity", "Price", "Amount");
        for (Order order : lo) {
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", order.getFruitName(),
                    order.getQuantity(), order.getPrice(),
                    order.getPrice() * order.getQuantity());
            total = order.getTotal();
        }
        System.out.println("Total: " + total);
        
    }
    
    static void viewOrder(Hashtable<String, ArrayList<Order>> ht) {
        if (ht.keySet().isEmpty()) {
            System.err.println("No order have made yet");
            System.out.println("");
        }
        for (String name: ht.keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Order> or = ht.get(name);
            displayListOrder(or);
        }
    }
    
    static void displayListFruit(ArrayList<Fruit> lf) {
        int countItem = 1; 
        Collections.sort(lf, new SortbyName());
        System.out.printf("%-10s%-20s%-20s%-15s\n", "Item", "Fruit name", "Origin", "Price($)");    
        for (Fruit fruit : lf) {
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-10d%-20s%-20s%-15.0f\n", countItem++,
                        fruit.getName(), fruit.getOrigin(), fruit.getPrice());
            }
        }
    }
    
    static Fruit getFruitByItem(ArrayList<Fruit> lf, int item) {
        int countItem = 1;
        for (Fruit fruit : lf) {
            if (fruit.getQuantity() != 0) {
                countItem++;
            }
            if (countItem - 1 == item) {
                return fruit;
            }
        }
        return null;
    }

    
    static void shopping(ArrayList<Fruit> lf, Hashtable<String, ArrayList<Order>> ht) {
        double total;
        if (lf.isEmpty()) {
            System.err.println("No item!");
            return;
        }
        ArrayList<Order> lo = new ArrayList<>();
        displayListFruit(lf);
        while (true) {
            System.out.print("Enter item: ");
            int item = Validation.checkInputIntLimit(1, lf.size());
            Fruit fruit = getFruitByItem(lf, item);
            System.out.println("Enter quantity: ");
            int quantity = Validation.checkInputIntLimit(1, fruit.getQuantity());
            fruit.setQuantity(fruit.getQuantity() - quantity);
            if(!Validation.checkItemExist(lo, fruit.getID()) && Validation.AreYouSureYN("Complete this order?")) {
                updateOrder(lo, fruit.getID(), quantity);
            } else {
                System.out.println("You finished your order!");
                System.out.println("Order details: ");
                System.out.printf("%-20s%-20s%-15s\n", "Fruit name", "Origin", "Price($)");
                System.out.printf("%-20s%-20s%-15.0f\n", fruit.getName(), fruit.getOrigin(), fruit.getPrice());
                total = fruit.getPrice() * fruit.getQuantity();
                lo.add(new Order(fruit.getID(), fruit.getName(), fruit.getQuantity(), fruit.getPrice(), total));
            }
            if (!Validation.checkInputYN()) break;
            System.out.println("");
        }
        displayListOrder(lo);
        System.out.println("Enter customer's name: ");
        String name = Validation.checkInputString(); 
        ht.put(name, lo);
        System.out.println("Thank you, " + name + "! Your order is on the way.");
    }
    
    //If order exists, update order
    static void updateOrder(ArrayList<Order> lo, String id, int quantity) {
        for (Order order : lo) {
            if (order.getFruitID().equalsIgnoreCase(id)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
    }
}
