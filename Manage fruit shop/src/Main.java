
import java.util.ArrayList;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) {
        ArrayList<Fruit> lf = new ArrayList<>();
        lf.add(new Fruit("001", "Acerola", 10, 1.5, "America"));
        lf.add(new Fruit("002", "Apple", 10, 3, "Vietnam"));
        lf.add(new Fruit("003", "Apricot", 20, 4, "America"));
        lf.add(new Fruit("004", "Avocado", 15, 2, "Thailand"));
        lf.add(new Fruit("005", "Blackberries", 10, 2.4, "America"));
        lf.add(new Fruit("006", "Blackcurrant", 7, 8, "America"));
        lf.add(new Fruit("007", "Breadfruit", 10, 6, "America"));
        lf.add(new Fruit("008", "Cantaloupe", 10, 5, "America"));
        lf.add(new Fruit("009", "Carambola", 10, 9, "America"));
        Hashtable<String, ArrayList<Order>> ht = new Hashtable<>();
        Hashtable<String, ArrayList<Feedback>> feedback = new Hashtable<>();
        while (true) {
            int choice = Manager.menu();
            switch (choice) {
                case 1:
                    Manager.createFruit(lf);
                    break;
                case 2:
                    Manager.updateQuantityFruit(lf);
                    break;
                case 3:
                    Manager.viewOrder(ht);
                    break;
                case 4:
                    Manager.viewFeedback(feedback);
                    break;
                case 5:
                    Manager.createFeedback(ht, feedback);
                    break;
                case 6:
                    Manager.shopping(lf, ht);
                    break;
                case 7:
                    Manager.report(ht);
                    break;
                case 8:
                    return;
            }
        }
    }
    
}
