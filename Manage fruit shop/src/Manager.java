
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

public class Manager {
    static int menu() {
        System.out.println("============= WELCOME TO THE FRUIT SHOP =============");
        System.out.println("1. Create Fruit");
        System.out.println("2. Update quantity of fruit");
        System.out.println("3. View orders");
        System.out.println("4. View feedback");
        System.out.println("5. Create feedback");
        System.out.println("6. Shopping (for buyer)");
        System.out.println("7. Report");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
        int choice = Validation.checkInputIntLimit(1, 8);
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
            else {
                lf.add(new Fruit(fruitID, name, quantity, price, origin));
                System.out.println("Add " + name + " successfully");
            } 
            if (!Validation.checkInputYN()) {
                System.out.println("");
                return;
            }
        }
    }
    
    static void updateQuantityFruit(ArrayList<Fruit> lf) {
        if (lf.isEmpty()) {
            System.err.println("No item!");
            return;
        }
        displayListFruitwithID(lf);
        int quantity;
        while (true) {
            System.out.print("Enter fruit ID to update quantity: ");
            String ID = Validation.checkInputString();
            if(!Validation.checkIdExist(lf, ID)) {
                System.out.print("Enter quantity: ");
                quantity = Validation.checkInputInt();
                Fruit toUpdate = null;
                for (Fruit f : lf) {
                    if (f.getID().equals(ID)) {
                        f.setQuantity(f.getQuantity() + quantity);
                        toUpdate = f;
                    }
                }
                System.out.println("Update quantity of fruit with ID " + ID + " successfully:");
                System.out.printf("%-20s%-20s%-15.0f%-15s\n",
                        toUpdate.getName(), toUpdate.getOrigin(), toUpdate.getPrice(), toUpdate.getQuantity());
            } else {
                System.err.println("ID doesn't exist! You may want to create fruit first!");
            }
            if (!Validation.checkInputYN()) return;
        }
    }
    
    static void displayListOrder(ArrayList<Order> lo) {
        double total = 0;
        System.out.printf("%15s | %15s | %15s | %15s\n", "Product", "Quantity", "Price", "Amount");
        for (Order order : lo) {
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", order.getFruitName(),
                    order.getQuantity(), order.getPrice(),
                    order.getPrice() * order.getQuantity());
            total += order.getPrice() * order.getQuantity();
            order.setTotal(total);
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
        System.out.printf("%-10s%-20s%-20s%-15s%-15s\n", "Item", "Fruit name", "Origin", "Price($)", "Quantity");    
        for (Fruit fruit : lf) {
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-10d%-20s%-20s%-15.0f%-15s\n", countItem++,
                        fruit.getName(), fruit.getOrigin(), fruit.getPrice(), fruit.getQuantity());
            }
        }
    }
    
    static void displayListFruitwithID(ArrayList<Fruit> lf) {
        Collections.sort(lf, new SortbyName());
        System.out.printf("%-10s%-20s%-20s%-15s%-15s\n", "ID", "Fruit name", "Origin", "Price($)", "Quantity");    
        for (Fruit fruit : lf) {
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-10s%-20s%-20s%-15.0f%-15s\n", fruit.getID(),
                        fruit.getName(), fruit.getOrigin(), fruit.getPrice(), fruit.getQuantity());
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
                lo.add(new Order(fruit.getID(), fruit.getName(), quantity, fruit.getPrice()));
            }
            if (!Validation.checkInputYN()) break;
            System.out.println("");
        }
        displayListOrder(lo);
        System.out.println("Enter customer's name: ");
        String name = Validation.checkInputString(); 
        try {
            ArrayList<Order> o = ht.get(name);
            for (Order n : lo) {
            o.add(n);
        }
        } catch (NullPointerException e) {
            ht.put(name, lo);
        }
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
    
    //only available for customer
    static void createFeedback(Hashtable<String, ArrayList<Order>> ht, Hashtable<String, ArrayList<Feedback>> fb) {
        System.out.print("Enter your name: ");
        String name = Validation.checkInputString(); 
        if (ht.containsKey(name)) {
            ArrayList<Feedback> lo = new ArrayList<>();
            System.out.print("How was your experience? (Enter 1 for really Bad, 5 for very good): ");
            int rate = Validation.checkInputIntLimit(1, 5);
            System.out.println("Tell us what you think or press Enter to skip: ");
            Scanner sc = new Scanner(System.in);
            String feedback = sc.nextLine();
            if (feedback.equals("")) {
                return;
            } else {
                Feedback a = new Feedback(feedback, rate);
                try {
                    ArrayList<Feedback> o = fb.get(name);
                    for (Feedback n : o) {
                        o.add(n);
                    }
                } catch (NullPointerException e) {
                    fb.put(name, lo);
                }
                System.out.println("THANK YOU FOR YOUR FEEDBACK! SEE YOU AGAIN!");
            }
        } else {
            System.err.println("You are our new customer! Buy something first!");
        }
    }
    
    static void viewFeedback(Hashtable<String, ArrayList<Feedback>> fb) {
        if (fb.keySet().isEmpty()) {
            System.err.println("No feedback have created yet");
            System.out.println("");
        } else {
            for (String name: fb.keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Feedback> or = fb.get(name);
            displayListFeedback(or);
            }
        }
    }
    
    static void displayListFeedback(ArrayList<Feedback> fb) {
        for (Feedback f : fb) {
            System.out.println("Rating: " + f.getPoint());
            System.out.println(f.getFeedback());
        }
    }
    
    static void report(Hashtable<String, ArrayList<Order>> ht) {
        double totalAmount = 0;
        //ArrayList<Order> ls = ht.values();
        //total quantity and income from each fruit
        //spending by each customer
        //average rating
    }
}
