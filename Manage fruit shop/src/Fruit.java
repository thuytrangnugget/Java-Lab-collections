public class Fruit {
   private String ID;
   private String name;
   private int quantity;
   private double price;
   private String origin;

    public Fruit(String ID, String name, int quantity, double price, String origin) {
        this.ID = ID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.origin = origin;
    }

    public Fruit() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Fruit{" + "ID=" + this.getID() + ", name=" + this.getName() + ", quantity=" + this.getQuantity() + ", price=" + this.getPrice() + ", origin=" + this.getOrigin() + '}';
    }
    
    

}
