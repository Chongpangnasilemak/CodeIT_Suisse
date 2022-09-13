public class EntryRecord {
    private int quantity;
    private double price;
    private String time;

    public EntryRecord(int quantity, double price,String time) {
        this.quantity = quantity;
        this.price = price;
        this.time=time;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }
}
