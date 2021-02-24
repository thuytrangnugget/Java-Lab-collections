
import java.util.ArrayList;

public class Manager {
    static int menu() {
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
            }
        }
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
}
