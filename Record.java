public class Record {
    private double cumulative_notional;
    private int cumulative_quantity;

    public Record(double price, int quantity) {
        this.cumulative_notional = price*quantity;
        this.cumulative_quantity = quantity;
    }
    public void insert(double price,int quantity){
        this.cumulative_notional += price*quantity;
        this.cumulative_quantity += quantity;
    }
    public void insert_cumulative(double cum_notional,int cum_quantity){
        this.cumulative_notional+=cum_notional;
        this.cumulative_quantity+=cum_quantity;
    }
    public double getCumulative_notional() {
        return cumulative_notional;
    }

    public int getCumulative_quantity() {
        return cumulative_quantity;
    }

}
