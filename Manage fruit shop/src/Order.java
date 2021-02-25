public class Order {
    private String fruitID; 
    private String fruitName;
    private int quantity;
    private double price;
    private double total;
    
    public Order() {}

    public Order(String fruitID, String fruitName, int quantity, double price, double total) {
        this.fruitID = fruitID;
        this.fruitName = fruitName;
        this.quantity = 0;
        this.price = price;
        this.total = total;
    }

    public String getFruitID() {
        return fruitID;
    }

    public void setFruitID(String fruitID) {
        this.fruitID = fruitID;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
